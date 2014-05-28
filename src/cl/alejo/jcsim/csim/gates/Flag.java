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

public class Flag extends Gate {
	public Flag(Circuit circ, GateDescriptor gatedesc, GateParameters params) {
		// Los parametros
		this._gateDescriptor = gatedesc;
		this._parameters = params;
		this._circuit = circ;
		_pin = new Pin[pinCount()];
		_pin[0] = new Pin(this, circ, (byte) 0);
	}

	public void apply(int x, int y) {
	}
}
