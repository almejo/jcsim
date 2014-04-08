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
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;

import cl.alejo.jcsim.csim.circuit.Circuit;
import cl.alejo.jcsim.csim.circuit.Point;
import cl.alejo.jcsim.csim.dom.Gate;

public class FlagDescriptor extends GateDescriptor {
	/**
	 * Descriptor del led
	 */
	public FlagDescriptor() {
		super();

		// PArametros
		this.params = new ParamVoid();
		this.pointPin = new cl.alejo.jcsim.csim.circuit.Point[1];
		this.typeOfGate = GateDescriptor.GATE_TYPE_DEBUG;
		this.pointPin[0] = new Point(-7, 12); // El pinIn 1
		this.sGateName = "FlagDesc";
		this.pinCount = 1;
	}

	/**
	 * Insert the method's description here. Creation date: (04/01/01 11:05:28)
	 * 
	 * @param g
	 *            java.awt.Graphics2D
	 */
	public void drawGate(Graphics2D g, IconGate icon, int dx, int dy) {
		// Dibujemos el reloj
		int state = icon.gate.getPin()[0].getInValue();
		switch (state) {
		case 1:
			g.setColor(Color.red);
			break;
		case 0:
			g.setColor(Color.black);
			break;
		default:
			g.setColor(Color.green);
			break;
		}
		g.fillOval(dx + 7, dx + 7, 15, 15);

		// La base
		g.setColor(Color.darkGray);
		g.fillRect(dx + 10, dy + 20, 9, 6);
	}

	/**
	 * El tama???o del Flag Creation date: (27/03/01 16:54:02)
	 * 
	 * @return java.awt.Dimension
	 */
	public Dimension getSize() {
		return new Dimension(24, 24);
	}

	/**
	 * Insert the method's description here. Creation date: (26/06/01 21:27:43)
	 * 
	 * @return csim.Gate
	 * @param params
	 *            gates.GateParameters
	 */
	public Gate make(Circuit circuit, GateParameters params) {
		return new Flag(circuit, (GateDescriptor) this, (GateParameters) params.clone());
	}
}
