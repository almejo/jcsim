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

public class FFDataDescriptor extends IconGateDescriptor {

	public FFDataDescriptor(ParamFFData params) {
		super();
		this.params = params;
		this.pointPin = new Point[3];
		this.pointPin[0] = new Point(-16, 0); // El pinIn
		this.pointPin[1] = new Point(16, 0); // El pinOut
		this.pointPin[2] = new Point(0, 8); // El pinOut
		this.sGateName = "FFdateDesc";
		this.pinCount = 3;
	}

	public Gate make(Circuit circuit, GateParameters params) {
		return new FFData(circuit, this, (GateParameters) params.clone());
	}
}
