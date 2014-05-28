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

public class TemplateGateDescriptor extends IconGateDescriptor {

	public TemplateGateDescriptor() {
		super();
		this.pinCount = 0;
		this.typeOfGate = GateDescriptor.GATE_TYPE_DEBUG;
		this.sGateName = "TemplateGateDescriptor";
	}

	public Gate make(Circuit circuit, GateParameters params) {
		return new TemplateGate(circuit, this, (GateParameters) params.clone());
	}
}