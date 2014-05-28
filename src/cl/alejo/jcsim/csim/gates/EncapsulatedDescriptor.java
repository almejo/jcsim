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

import cl.alejo.jcsim.csim.circuit.Circuit;
import cl.alejo.jcsim.csim.dom.Gate;
import cl.alejo.jcsim.csim.dom.Pin;

public class EncapsulatedDescriptor extends IconGateDescriptor {

	// Numero de pines externos
	int outPinCount;

	// Las compuertas y sus parametros
	EncapsulatedGateData[] gateData;

	// LA tabla con lo pines externos
	int[][] connectOut;

	public EncapsulatedDescriptor() {
		params = new ParamVoid();
	}

	public Gate make(Circuit circuit, GateParameters params) {

		// Aqui debo crear una nueva compuerta encapsulada
		Encapsulated chip = new Encapsulated();

		// Primero, creo una tabla con todas las compuertas
		Gate[] gates = new Gate[gateData.length];
		for (int i = 0; i < gates.length; i++) {
			GateParameters paramsAux = gateData[i].params;
			gates[i] = gateData[i].gateDesc.make(circuit, (GateParameters) paramsAux.clone());
		}
		chip.gates = gates;

		// Asocio el circuito
		chip.actualizeCircuit(circuit);

		// Ahora genero la tabla de pines externos
		chip.setPin(new Pin[pinCount]);
		for (int i = 0; i < pinCount; i++) {
			chip.getPin()[i] = gates[connectOut[i][0]].getPin(connectOut[i][1]);
		}

		// Asocio el descriptor
		chip.setGateDescriptor(this);

		// Ahora reconecto
		{
			for (int i = 0; i < gateData.length; i++) {
				// Primero recorro el arreglo de datos y parametros
				int[][] connectTo = gateData[i].connectTo;

				// Ahora recorro todos los pines de ese gate conectandolos
				Gate gateA = gates[i];
				int pinCountGateA = gateA.pinCount();
				for (int pinId = 0; pinId < pinCountGateA; pinId++) {
					// REcupero el gate y el pin
					Gate gateB = gates[connectTo[pinId][0]];

					// Si las compuertas son distintas
					if (gateA != gateB) {
						Pin pinB = gateB.getPin(connectTo[pinId][1]);
						Pin pinA = gateA.getPin(pinId);
						// Los conecto si no son los mismos
						if (pinA != pinB)
							pinA.connect(pinB);
					}
				}
			}
		}

		// Ahora retorno la nueva compuerta
		return chip;
	}
}
