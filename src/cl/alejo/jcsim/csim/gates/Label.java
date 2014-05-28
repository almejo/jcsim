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

public class Label extends ConfigurableGate {
	public Label(Circuit circuit, GateDescriptor gateDescriptor, GateParameters parameters) {
		_parameters = parameters;
		_gateDescriptor = gateDescriptor;
		_circuit = circuit;
	}

	public void apply(int x, int y) {

		// REcupero los valores
		String text = ((ParamLabel) _parameters).text;

		// Lleno el arreglo y lo mando
		String[] txtparams = {"Label text"};
		String[] valparams = {text};
		config(txtparams, valparams);
	}

	public void fillParams(String[] params) {
		// Recupero los delay
		((ParamLabel) this._parameters).text = params[0];
	}
}