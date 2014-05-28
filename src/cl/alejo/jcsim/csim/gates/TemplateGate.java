package cl.alejo.jcsim.csim.gates;

/**
 *
 * jcsim
 *
 * Created on Jul 17, 2004
 *
 * This program is distributed under the terms of the GNU General Public License
 * The license is included in license.txt
 *
 * @author: Alejandro Vera
 *
 */

import cl.alejo.jcsim.csim.circuit.*;
import cl.alejo.jcsim.csim.dom.Gate;
import cl.alejo.jcsim.csim.dom.Pin;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TemplateGate extends Gate {

	// Los pines externos
	int[][] connectOut;

	public TemplateGate(Circuit circuit, GateDescriptor gateDesc, GateParameters params) {
		this._gateDescriptor = gateDesc;
		this._circuit = circuit;
		this._parameters = params;
	}

	public void apply(int x, int y) {
	}

	public Gate compile(Circuit circuit, Box box) {

		// Primero busco todas las compuertas
		List listCtt = circuit.findContacts(box);

		// Borramos todos aquellos que no sean terminales
		removeFalseTerminals(listCtt);

		// Ahora los gates
		List listGates = circuit.findGates(listCtt);

		// Ahora recupero toda la informacion de las compuertas
		EncapsulatedGateData[] gateData = new EncapsulatedGateData[listGates.size()];
		getGateParams(gateData, listGates);

		// Ahora debo crear la matriz de conecciones
		createInnerPinTables(listGates, gateData);

		// Y ahora el arreglo de pines del icono nuevo
		int outPinCount = createOutterPinTable(listCtt, listGates);

		// Duplico las tablas
		int connectOutAux[][] = new int[connectOut.length][2];

		// Copiio
		for (int i = 0; i < connectOut.length; i++) {
			connectOutAux[i][0] = connectOut[i][0];
			connectOutAux[i][1] = connectOut[i][1];
		}
		EncapsulatedDescriptor encapsulatedDesc = getNewEncapsulatedDescriptor(box, listCtt, gateData, outPinCount);

		// ahora hago un make de la compuerta y devuelvo un icono
		return encapsulatedDesc.make(circuit);
	}

	public EncapsulatedDescriptor getNewEncapsulatedDescriptor(Box box, List listCtt, EncapsulatedGateData[] gateData,
															   int outPinCount) {

		// Ahora creo un descriptor de mi compuerta y lo lleno
		EncapsulatedDescriptor encapsulatedDesc = new EncapsulatedDescriptor();
		encapsulatedDesc.connectOut = connectOut;
		encapsulatedDesc.outPinCount = outPinCount;
		encapsulatedDesc.gateData = gateData;
		encapsulatedDesc.iconImage = ((IconGateDescriptor) this._gateDescriptor).iconImage;

		// ************************************************************
		// Ahora creo la matriz de posiciones para los pines
		// ************************************************************
		cl.alejo.jcsim.csim.circuit.Point[] posPin = new cl.alejo.jcsim.csim.circuit.Point[outPinCount];
		IconGate icon = (IconGate) box;
		// y la lleno con los nuevos puntos
		for (int i = 0; i < posPin.length; i++) {
			ContactPin ctt = (ContactPin) listCtt.get(i);
			int x = ctt._x;
			int y = ctt._y;
			Point point = icon.toGateCoords(x, y);
			posPin[i] = new Point(point._x, point._y);
		}

		// Los agrego al descriptor
		encapsulatedDesc.pointPin = posPin;
		encapsulatedDesc.pinCount = outPinCount;

		// Agrego los parametros mula
		encapsulatedDesc.params = new ParamVoid();
		return encapsulatedDesc;
	}

	/**
	 * Aqui se crea una tabla de pines Creation date: (23/06/01 18:13:32)
	 */
	public void createInnerPinTables(List listGates, EncapsulatedGateData[] gateData) {
		// Ahora la vamos llenando
		// Para cada gate
		int currentGate = 0;
		Iterator iterGate = listGates.iterator();
		while (iterGate.hasNext()) {
			Gate gate = (Gate) iterGate.next();
			int[][] connectTo = gateData[currentGate].connectTo;

			// ahora por cada pin
			int pcount = gate.pinCount();
			for (int pinId = 0; pinId < pcount; pinId++) {
				Pin pin = gate.getPin(pinId);

				// Tomo el siguiente
				Pin nextPin = (Pin) pin.next();

				// Busco el indice del gate en la lista
				int ndxGate = getGateIndex(nextPin.getGate(), listGates);

				// Ahora tengo que saber que pin es ese
				// Dentro de la compuerta
				int ndxPin = getPinIndex(nextPin);

				// Y ahora lo agrego a la tabla
				connectTo[pinId][0] = ndxGate;
				connectTo[pinId][1] = ndxPin;
			}

			// Avanzo el numero de gate
			currentGate++;
		}
	}

	public int createOutterPinTable(List listCtt, List listGates) {

		// La tabla
		connectOut = new int[listCtt.size()][2];

		// El indice de la tabla
		int ndxTable = 0;

		// Ahora la vamos llenando
		// Para cada gate
		Iterator iterCtt = listCtt.iterator();
		while (iterCtt.hasNext()) {
			ContactPin ctt = (ContactPin) iterCtt.next();

			// Busco los pines attachados a este contacto
			List listCttAux = _circuit.getProtoboard().findAttachedContacts(ctt);
			List listPin = ((ProtoboardPin) _circuit.getProtoboard()).getAttachedPins(listCttAux);
			if (listPin.size() > 0) {
				Pin pin = (Pin) listPin.get(0);
				int ndxPin = getPinIndex(pin);
				int ndxGate = getGateIndex(pin.getGate(), listGates);
				// Llenamos y aumentamos el indice
				connectOut[ndxTable][0] = ndxGate;
				connectOut[ndxTable][1] = ndxPin;
				ndxTable++;
			}
		}
		return ndxTable;
	}

	public void removeFalseTerminals(List listCtt) {

		// La lista a remover
		List listCttNotTerminal = new ArrayList();

		// Recorremos probando los terminales
		Iterator iter = listCtt.iterator();
		while (iter.hasNext()) {
			ContactPin ctt = (ContactPin) iter.next();
			if (!_circuit.isTerminal(ctt))
				listCttNotTerminal.add(ctt);
		}

		// Ahora quito todo lo que no quiero
		listCtt.removeAll(listCttNotTerminal);
	}

	public static int getGateIndex(Gate gate, List listGates) {
		return listGates.indexOf(gate);
	}

	public void getGateParams(EncapsulatedGateData[] gateData, List listGates) {

		// Recorro la lista sacando los parametros
		Iterator iter = listGates.iterator();
		int i = 0;

		// Ciclo
		while (iter.hasNext()) {
			Gate gate = (Gate) iter.next();
			int[][] connectTo = new int[gate.pinCount()][2];
			gateData[i++] = new EncapsulatedGateData(gate.getGateDescriptor(), (GateParameters) gate.getParameters()
					.clone(), connectTo);
			// El indice aumenta en la cantidad de pines de la compuerta

		}
	}

	public int getPinIndex(Pin pin) {
		if (pin == null) {
			return -1;
		}

		// Buscamos
		int max = pin.getGate().pinCount();
		for (int j = 0; j < max; j++) {
			if (pin == pin.getGate().getPin(j)) {
				return j;
			}
		}
		// Fallo
		return -1;
	}
}
