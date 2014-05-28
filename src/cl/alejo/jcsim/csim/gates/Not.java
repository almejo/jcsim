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
	public Not(Circuit circuit, GateDescriptor gatedesc, GateParameters parameters) {
		_parameters = parameters;
		_gateDescriptor = gatedesc;
		_circuit = circuit;

		_pin = new NotPin[pinCount()];
		for (int i = 0; i < pinCount(); i++) {
			_pin[i] = new NotPin(this, circuit, (byte) i);
		}
	}

	public void apply(int x, int y) {
		String[] txtparams = {"Tiempo de retardo"};
		String[] valparams = {"" + ((ParamNot) _parameters).delay};
		config(txtparams, valparams);
	}

	public void fillParams(String[] txtParams) {

		// Recupero el tiempo de retardo
		int newdelay = (int) Double.parseDouble(txtParams[0]);

		// Solo tiempos de retado mayores que 1
		if (newdelay >= 1) {
			((ParamNot) _parameters).delay = newdelay;
		}
	}
}