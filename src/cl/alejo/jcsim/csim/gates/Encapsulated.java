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

import cl.alejo.jcsim.csim.dom.Gate;
import cl.alejo.jcsim.csim.dom.Pin;
import cl.alejo.jcsim.window.ImageChooser;

import java.util.List;

public class Encapsulated extends Gate {

	// El conjunto de compuertas
	Gate[] gates;

	public Encapsulated() {
		super();
	}

	/**
	 * Abrimos la ventana de eleccion de imagen.
	 *
	 * @param x int Coordenada x
	 * @param y int Coordenada y
	 */
	public void apply(int x, int y) {

		new ImageChooser((IconGateDescriptor) this._gateDescriptor);
	}

	/**
	 * Agrega todos los pines internos conecados a este pin. En el caso base,
	 * solo agrega el mismo pin Creation date: (16/07/01 12:29:17)
	 *
	 * @param pinId   int
	 * @param listPin java.util.List
	 */

	public void getPinsAt(int pinId, List listPin) {

		/*
		 * Pin pin = getPin(pinId); if (!listPin.contains(pin))
		 * listPin.add(pin); //
		 */

		Pin pinAux;

		// Rescato las tablas
		EncapsulatedDescriptor gateDesc = (EncapsulatedDescriptor) this._gateDescriptor;
		int[][] connectOut = gateDesc.connectOut;
		EncapsulatedGateData[] gateData = gateDesc.gateData;

		// Ahora tengo que recorrer la lista de tonteras
		// Agregando a la lista
		int ndxGate = connectOut[pinId][0];
		int ndxPin = connectOut[pinId][1];

		// Rescato el primer gate
		Gate gate = gates[ndxGate];
		Pin pinFirst = gate.getPin(ndxPin);

		// Agrego sus pines
		gate.getPinsAt(ndxPin, listPin);
		do {
			// busco el siguiente
			int[][] connectTo = gateData[ndxGate].connectTo;
			ndxGate = connectTo[ndxPin][0];
			ndxPin = connectTo[ndxPin][1];
			gate = gates[ndxGate];
			pinAux = gate.getPin(ndxPin);

			// Lo invoco recursivamente
			gate.getPinsAt(ndxPin, listPin);
		} while (pinFirst != pinAux);
	}
}
