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

public class SegmentDisplay extends Gate {
	public SegmentDisplay(Circuit circuit, GateDescriptor gateDescriptor, GateParameters parameters) {
		// Los parametros
		_gateDescriptor = gateDescriptor;
		_parameters = parameters;
		_circuit = circuit;

		// Creo los pines

		_pin = new Pin[pinCount()];
		for (int i = 0; i < pinCount(); i++) {
			_pin[i] = new SegmentDisplayPin(this, circuit, (byte) i);
		}
	}

	/**
	 * Se ejecuta cuando se doble clickea una compuerta. Creation date:
	 * (16/04/01 18:36:33)
	 *
	 * @param x int
	 * @param y int
	 */
	public void apply(int x, int y) {
	}
}
