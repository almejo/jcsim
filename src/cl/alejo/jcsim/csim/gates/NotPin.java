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

public class NotPin extends Pin {
	NotPin(Gate gate, Circuit circ, byte pinId) {
		super(gate, circ, pinId);
	}

	final byte computeNot(byte val) {
		return (byte) (val == 0 ? 1 : 0);
	}

	public void hasChanged() {
		byte newVal = computeNot(((Not) _gate).getPin()[0].getInValue());
		((Not) _gate).getPin()[1].programOut(newVal, ((ParamNot) _gate.getParameters()).delay);
	}
}