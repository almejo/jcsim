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

	/**
	 * Insert the method's description here. Creation date: (16/04/01 19:06:23)
	 * 
	 * @param x
	 *            int
	 * @param y
	 *            int
	 */
	public void apply(int x, int y) {

		// REcupero los valores
		String text = new String(((ParamLabel) _parameters).text);

		// Lleno el arreglo y lo mando
		String[] txtparams = { "Label text" };
		String[] valparams = { text };
		config(txtparams, valparams);
	}

	/**
	 * Insert the method's description here. Creation date: (16/04/01 19:06:23)
	 * 
	 * @param x
	 *            int
	 * @param y
	 *            int
	 */
	public void fillParams(String[] params) {

		// Recupero los delay
		String text = (String) params[0];
		((ParamLabel) this._parameters).text = text;
	}
}