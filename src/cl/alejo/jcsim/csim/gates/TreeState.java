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
package cl.alejo.jcsim.csim.gates;

import cl.alejo.jcsim.csim.circuit.Circuit;
import cl.alejo.jcsim.csim.dom.Gate;
import cl.alejo.jcsim.csim.dom.Pin;

public class TreeState extends Gate {

	public TreeState(Circuit circ, GateDescriptor gatedesc, GateParameters params) {
		_gateDescriptor = gatedesc;
		_parameters = params;
		_circuit = circ;

		// Pines
		// 0 : entrada
		// 1 : salida
		// 2 : clock
		_pin = new Pin[pinCount()];
		for (int i = 0; i < pinCount(); i++)
			_pin[i] = new Pin(this, circ, (byte) i);

		// El pin que controla el valor del pin de salida
		_pin[2] = new TreeStatePin(this, circ, (byte) 2);
	}

	public void apply(int x, int y) {
	}
}