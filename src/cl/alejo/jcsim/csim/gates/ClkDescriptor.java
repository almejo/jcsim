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

import java.awt.*;

public class ClkDescriptor extends GateDescriptor {

	public ClkDescriptor(ParamClk params) {
		super();
		this.params = params;
		this.pointPin = new cl.alejo.jcsim.csim.circuit.Point[1];
		this.typeOfGate = GateDescriptor.GATE_TYPE_NORMAL;
		this.pointPin[0] = new Point(10, 10); // El pinIn 1
		this.sGateName = "ClkDesc";
		this.pinCount = 1;
	}

	public void drawGate(Graphics2D gr, IconGate icon, int dx, int dy) {
		// Dibujemos el reloj
		gr.setColor(Color.blue);
		gr.fillRect(dx, dy, 16, 16);
		if (icon.gate.getPin()[0].getInValue() == 1)
			gr.setColor(Color.red);
		else if (icon.gate.getPin()[0].getInValue() == 0)
			gr.setColor(Color.black);
		else
			gr.setColor(Color.green);
		gr.fillRect(dx + 3, dy + 3, 10, 10);
	}

	public void drawImage(Graphics2D graphics, IconGate icon) {
		// Dibujemos el reloj
		graphics.setColor(Color.blue);
		graphics.fillRect(0, 0, 20, 20);
		if (icon.gate.getPin()[0].getInValue() == 1)
			graphics.setColor(Color.red);
		else if (icon.gate.getPin()[0].getInValue() == 0)
			graphics.setColor(Color.black);
		else
			graphics.setColor(Color.green);
		graphics.fillRect(3, 3, 14, 14);
	}

	public Dimension getSize() {
		return new Dimension(16, 16);
	}

	public Gate make(Circuit circuit, GateParameters params) {
		return new Clk(circuit, this, (GateParameters) params.clone());
	}
}
