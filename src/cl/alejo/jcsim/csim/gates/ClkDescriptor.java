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

public class ClkDescriptor extends GateDescriptor {
	/**
	 * Clk constructor comment.
	 */
	public ClkDescriptor(ParamClk params) {
		super();
		this.params = params;
		this.pointPin = new cl.alejo.jcsim.csim.circuit.Point[1];
		this.typeOfGate = GateDescriptor.GATE_TYPE_NORMAL;
		this.pointPin[0] = new Point(10, 10); // El pinIn 1
		this.sGateName = "ClkDesc";
		this.pinCount = 1;
	}

	/**
	 * Insert the method's description here. Creation date: (23/04/01 3:31:13)
	 * 
	 * @param gr
	 *            java.awt.Graphics2D
	 * @param icon
	 *            gates.IconGate
	 */
	public void drawGate(Graphics2D gr, IconGate icon, int dx, int dy) {
		// Dibujemos el reloj
		gr.setColor(Color.blue);
		gr.fillRect(dx + 0, dy + 0, 16, 16);
		if (icon.gate.getPin()[0].getInValue() == 1)
			gr.setColor(Color.red);
		else if (icon.gate.getPin()[0].getInValue() == 0)
			gr.setColor(Color.black);
		else
			gr.setColor(Color.green);
		gr.fillRect(dx + 3, dy + 3, 10, 10);
	}

	/**
	 * Insert the method's description here. Creation date: (23/04/01 3:31:13)
	 * 
	 * @param gr
	 *            java.awt.Graphics2D
	 * @param icon
	 *            gates.IconGate
	 */
	public void drawImage(Graphics2D gr, IconGate icon) {
		// Dibujemos el reloj
		gr.setColor(Color.blue);
		gr.fillRect(0, 0, 20, 20);
		if (icon.gate.getPin()[0].getInValue() == 1)
			gr.setColor(Color.red);
		else if (icon.gate.getPin()[0].getInValue() == 0)
			gr.setColor(Color.black);
		else
			gr.setColor(Color.green);
		gr.fillRect(3, 3, 14, 14);
	}

	/**
	 * Insert the method's description here. Creation date: (27/03/01 16:51:59)
	 */
	public Dimension getSize() {
		return new Dimension(16, 16);
	}

	/**
	 * Insert the method's description here. Creation date: (26/06/01 21:27:43)
	 * 
	 * @return csim.Gate
	 * @param params
	 *            gates.GateParameters
	 */
	public Gate make(Circuit circuit, GateParameters params) {
		return new Clk(circuit, (GateDescriptor) this, (GateParameters) params.clone());
	}
}
