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

public class FFDataPin extends Pin {

	FFDataPin(Gate gate, Circuit circuit, byte pinId) {
		super(gate, circuit, pinId);
	}

	public void hasChanged() {
		if (getInValue() == 0) {
			_gate.getPin()[1].programOut(_gate.getPin()[0].getInValue(), ((ParamFFData) _gate.getParameters()).delay);
		}
	}
}