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

public class SegmentDisplayPin extends Pin {
	SegmentDisplayPin(Gate gate, Circuit circ, byte pinId) {
		super(gate, circ, pinId);
	}

	byte computeDisplay(byte val0, byte val1, byte val2, byte val3) {
		byte res = 0;
		res += (val0 == 1 ? 1 : 0);
		res += (val1 == 1 ? 2 : 0);
		res += (val2 == 1 ? 4 : 0);
		res += (val3 == 1 ? 8 : 0);
		return res;

	}

	/**
	 * Cambio en el pin
	 * Creation date: (27/03/01 19:40:37)
	 */
	public void hasChanged() {
		Pin[] pin = _gate.getPin();
		((ParamSegmentDisplay) _gate.getParameters()).number = (int) computeDisplay(pin[0].getInValue(), pin[1].getInValue(), pin[2].getInValue(), pin[3].getInValue());
	}
}