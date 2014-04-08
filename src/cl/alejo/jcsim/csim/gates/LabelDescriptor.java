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
import cl.alejo.jcsim.csim.dom.Gate;

public class LabelDescriptor extends GateDescriptor {
	/**
	 * Clk constructor comment.
	 */
	public LabelDescriptor(ParamLabel params) {
		super();
		this.params = params;
		this.typeOfGate = GateDescriptor.GATE_TYPE_DEBUG;
		this.sGateName = "LabelDesc";
		this.pinCount = 0;
	}

	/**
	 * Insert the method's description here. Creation date: (23/04/01 3:28:14)
	 * 
	 * @param gr
	 *            java.awt.Graphics2D
	 * @param icon
	 *            gates.IconGate
	 */
	public void drawGate(Graphics2D gr, IconGate icon, int dx, int dy) {
		// Dibujamos las letras
		gr.setColor(Color.red);
		String text = ((ParamLabel) icon.gate.getParameters()).text;
		gr.drawString(text, -(text.length() / 2) * 5, 3);
	}

	/**
	 * Insert the method's description here. Creation date: (27/03/01 16:51:59)
	 */
	public Dimension getSize() {
		return new Dimension(32, 16);
	}

	/**
	 * Insert the method's description here. Creation date: (26/06/01 21:27:43)
	 * 
	 * @return csim.Gate
	 * @param params
	 *            gates.GateParameters
	 */
	public Gate make(Circuit circuit, GateParameters params) {
		return new Label(circuit, (GateDescriptor) this, (GateParameters) params.clone());
	}

	/**
	 * Insert the method's description here. Creation date: (04/01/01 11:05:28)
	 * 
	 * @param g
	 *            java.awt.Graphics2D
	 */
	public void paint(Graphics2D gr, IconGate icon) {

		// Transformamos el espacio
		pushMatrix(gr, icon);

		// Dibujemos el reloj
		drawGate(gr, icon, -14, -8);

		// Y dejamos como antes
		popMatrix(gr);
	}
}
