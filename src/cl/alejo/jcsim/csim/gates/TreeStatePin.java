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

public class TreeStatePin extends Pin {

	TreeStatePin(Gate gate, Circuit circ, byte pinId) {
		super(gate, circ, pinId);
	}

	public void hasChanged() {
		System.out.println(_gate.getPin()[0].getInValue());
		byte newVal = computeTreeState(_gate.getPin()[0].getInValue());
		_gate.getPin()[1].programOut(newVal, ((ParamTreeState) _gate.getParameters()).getDelay());
	}

	private byte computeTreeState(byte b) {
		return b == 1 ? getInValue() : Pin.THREE_STATE;
	}
}