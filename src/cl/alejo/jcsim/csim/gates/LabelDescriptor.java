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
import cl.alejo.jcsim.csim.dom.Gate;

import java.awt.*;

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

	public void drawGate(Graphics2D gr, IconGate icon, int dx, int dy) {
		// Dibujamos las letras
		gr.setColor(Color.red);
		String text = ((ParamLabel) icon.gate.getParameters()).text;
		gr.drawString(text, -(text.length() / 2) * 5, 3);
	}

	public Dimension getSize() {
		return new Dimension(32, 16);
	}

	public Gate make(Circuit circuit, GateParameters params) {
		return new Label(circuit, this, (GateParameters) params.clone());
	}

	public void paint(Graphics2D gr, IconGate icon) {

		// Transformamos el espacio
		pushMatrix(gr, icon);

		// Dibujemos el reloj
		drawGate(gr, icon, -14, -8);

		// Y dejamos como antes
		popMatrix(gr);
	}
}
