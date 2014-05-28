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

public class NotDescriptor extends IconGateDescriptor {
	/**
	 * AndDesc constructor comment.
	 */
	public NotDescriptor(GateParameters params) {
		super();
		this.params = params;
		this.pointPin = new Point[2];
		this.pointPin[0] = new Point(-16, 0); // El pinIn
		this.pointPin[1] = new Point(16, 0); // El pinOut
		this.sGateName = "NotDesc";
		this.pinCount = 2;
	}

	public Gate make(Circuit circuit, GateParameters params) {

		// La compuerta
		return new Not(circuit, this, (GateParameters) params.clone());
	}
}
