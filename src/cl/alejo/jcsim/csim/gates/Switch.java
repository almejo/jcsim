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

public class Switch extends Gate {
	public Switch(Circuit circ, GateDescriptor gatedesc, GateParameters params) {

		// Llenamoslos parametros
		this._parameters = params;
		this._gateDescriptor = gatedesc;

		// Creamos los pines
		_pin = new Pin[pinCount()];
		_pin[0] = new Pin(this, circ, (byte) 0);
		_pin[0].programOut((byte) ((ParamSwitch) params).state, 0);
		_pin[0].hasChanged();
		this._circuit = circ;
	}

	/**
	 * Insert the method's description here. Creation date: (16/04/01 19:06:23)
	 * 
	 * @param x
	 *            int
	 * @param y
	 *            int
	 */
	public void apply(int x, int y) {
		if (y >= -4 && y <= 4) {
			if (x >= -15 && x <= -6)
				((ParamSwitch) _parameters).state = -1;
			if (x >= -5 && x <= 4)
				((ParamSwitch) _parameters).state = 1;
			if (x >= 5 && x <= 14)
				((ParamSwitch) _parameters).state = 0;
		}

		// Programo el evento
		switch (((ParamSwitch) _parameters).state) {
		case 0:
			_pin[0].programOut((byte) 0, 0);
			break;
		case 1:
			_pin[0].programOut((byte) 1, 0);
			break;
		case -1:
			_pin[0].programOut((byte) Pin.THREE_STATE, 0);
			break;
		}
	}

	public void config(String[] parameters) {

	}
}