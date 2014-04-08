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
import cl.alejo.jcsim.csim.dom.ConfigurableGate;

public class Not extends ConfigurableGate {
	public Not(Circuit circ, GateDescriptor gatedesc, GateParameters parameters) {
		_parameters = parameters;
		_gateDescriptor = gatedesc;
		_circuit = circ;

		_pin = new NotPin[pinCount()];
		for (int i = 0; i < pinCount(); i++)
			_pin[i] = new NotPin(this, circ, (byte) i);
	}

	/**
	 * Insert the method's description here. Creation date: (30/04/01 3:59:17)
	 * 
	 * @param x
	 *            int
	 * @param y
	 *            int
	 */
	public void apply(int x, int y) {
		String[] txtparams = { "Tiempo de retardo" };
		String[] valparams = { "" + ((ParamNot) _parameters).delay };
		config(txtparams, valparams);
	}

	/**
	 * Insert the method's description here. Creation date: (16/04/01 19:00:50)
	 * 
	 * @param x
	 *            int
	 * @param y
	 *            int
	 */
	public void fillParams(String[] txtParams) {

		// Recupero el tiempo de retardo
		int newdelay = (int) Double.parseDouble(txtParams[0]);

		// Solo tiempos de retado mayores que 1
		if (newdelay >= 1)
			((ParamNot) _parameters).delay = newdelay;
	}
}