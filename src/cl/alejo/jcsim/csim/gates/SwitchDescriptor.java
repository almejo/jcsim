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

public class SwitchDescriptor extends GateDescriptor {
	/**
	 * Clk constructor comment.
	 */
	public SwitchDescriptor(ParamSwitch params) {
		super();
		this.params = params;
		this.typeOfGate = GateDescriptor.GATE_TYPE_DEBUG;
		this.pointPin = new cl.alejo.jcsim.csim.circuit.Point[1];
		this.pointPin[0] = new Point(18, 10); // El pinIn 1
		this.sGateName = "SwitchDesc";
		this.pinCount = 1;
	}

	public void drawGate(Graphics2D gr, IconGate icon, int dx, int dy) {
		// Dibujemos el reloj
		gr.setColor(Color.blue);
		gr.fillRect(dx, dy, 28, 16);

		// Dibujamos las letras
		switch (((ParamSwitch) icon.gate.getParameters()).state) {
			case -1:
				gr.setColor(Color.red);
				gr.drawString("T", dx + 1, dy + 12);
				gr.setColor(Color.black);
				gr.drawString("1", dx + 11, dy + 12);
				gr.drawString("0", dx + 20, dy + 12);
				break;
			case 0:
				gr.setColor(Color.black);
				gr.drawString("T", dx + 1, dy + 12);
				gr.drawString("1", dx + 11, dy + 12);
				gr.setColor(Color.red);
				gr.drawString("0", dx + 20, dy + 12);
				break;
			case 1:
				gr.setColor(Color.black);
				gr.drawString("0", dx + 20, dy + 12);
				gr.drawString("T", dx + 1, dy + 12);
				gr.setColor(Color.red);
				gr.drawString("1", dx + 11, dy + 12);
				break;
		}
	}

	public Dimension getSize() {
		return new Dimension(32, 16);
	}

	public Gate make(Circuit circuit, GateParameters params) {
		return new Switch(circuit, this, (GateParameters) params.clone());
	}
}