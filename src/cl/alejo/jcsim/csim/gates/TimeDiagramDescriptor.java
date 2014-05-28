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
import cl.alejo.jcsim.csim.circuit.Point;
import cl.alejo.jcsim.csim.dom.Gate;

public class TimeDiagramDescriptor extends IconGateDescriptor {
	/**
	 * AndDesc constructor comment.
	 */
	public TimeDiagramDescriptor() {
		super();
		this.pointPin = new cl.alejo.jcsim.csim.circuit.Point[4];
		this.typeOfGate = GateDescriptor.GATE_TYPE_DEBUG;
		this.pointPin[0] = new Point(-10, -12); // El pinIn 1
		this.pointPin[1] = new Point(-10, -4); // El pinIn 1
		this.pointPin[2] = new Point(-10, 4); // El pinIn 1
		this.pointPin[3] = new Point(-10, 12); // El pinIn 1
		this.sGateName = "TimeDiagramDesc";
		this.pinCount = 4;
	}

	public Gate make(Circuit circuit, GateParameters params) {
		return new TimeDiagram(circuit, this, (GateParameters) params.clone());
	}
}