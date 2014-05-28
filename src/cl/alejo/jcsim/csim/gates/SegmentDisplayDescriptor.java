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

public class SegmentDisplayDescriptor extends GateDescriptor {
	String[] chars = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F"};

	/**
	 * AndDesc constructor comment.
	 */
	public SegmentDisplayDescriptor(ParamSegmentDisplay params) {
		super();
		this.params = params;
		this.pointPin = new cl.alejo.jcsim.csim.circuit.Point[4];
		this.typeOfGate = GateDescriptor.GATE_TYPE_DEBUG;
		this.pointPin[0] = new Point(-10, -12); // El pinIn 1
		this.pointPin[1] = new Point(-10, -4); // El pinIn 1
		this.pointPin[2] = new Point(-10, 4); // El pinIn 1
		this.pointPin[3] = new Point(-10, 12); // El pinIn 1
		this.sGateName = "SegementDisplayDesc";
		this.pinCount = 4;
	}

	public void drawGate(Graphics2D gr, IconGate icon, int dx, int dy) {
		int _dx = Circuit.gridTrunc(dx);
		int _dy = Circuit.gridTrunc(dy);

		// Dibujemos el lector
		int state = ((ParamSegmentDisplay) icon.gate.getParameters()).number;
		gr.setColor(Color.blue);
		gr.fillRect(_dx + 0, _dy + 0, 24, 40);
		drawNumber(gr, state, _dx, _dy);
	}

	public void drawNumber(Graphics2D gr, int n, int dx, int dy) {
		gr.setColor(Color.red);

		// Prendo los segmentos
		// gr.drawString(chars[n], dx + 10, dy + 20);
		switch (n) {
			case 0:
				gr.drawLine(dx + 5, dy + 5, dx + 20, dy + 5);
				gr.drawLine(dx + 5, dy + 5, dx + 5, dy + 30);
				gr.drawLine(dx + 5, dy + 30, dx + 20, dy + 30);
				gr.drawLine(dx + 20, dy + 5, dx + 20, dy + 30);
				break;
			case 1:
				gr.drawLine(dx + 20, dy + 5, dx + 20, dy + 30);
				break;
			case 2:
				gr.drawLine(dx + 5, dy + 5, dx + 20, dy + 5);
				gr.drawLine(dx + 20, dy + 5, dx + 20, dy + 17);
				gr.drawLine(dx + 20, dy + 17, dx + 5, dy + 17);
				gr.drawLine(dx + 5, dy + 17, dx + 5, dy + 30);
				gr.drawLine(dx + 5, dy + 30, dx + 20, dy + 30);
				break;
			case 3:
				gr.drawLine(dx + 5, dy + 5, dx + 20, dy + 5);
				gr.drawLine(dx + 5, dy + 17, dx + 20, dy + 17);
				gr.drawLine(dx + 5, dy + 30, dx + 20, dy + 30);
				gr.drawLine(dx + 20, dy + 5, dx + 20, dy + 30);
				break;
			case 4:
				gr.drawLine(dx + 5, dy + 5, dx + 5, dy + 17);
				gr.drawLine(dx + 5, dy + 17, dx + 20, dy + 17);
				gr.drawLine(dx + 20, dy + 5, dx + 20, dy + 30);
				break;
			case 5:
				gr.drawLine(dx + 5, dy + 5, dx + 20, dy + 5);
				gr.drawLine(dx + 5, dy + 5, dx + 5, dy + 17);
				gr.drawLine(dx + 20, dy + 17, dx + 5, dy + 17);
				gr.drawLine(dx + 20, dy + 17, dx + 20, dy + 30);
				gr.drawLine(dx + 5, dy + 30, dx + 20, dy + 30);
				break;
			case 6:
				gr.drawLine(dx + 5, dy + 5, dx + 20, dy + 5);
				gr.drawLine(dx + 5, dy + 17, dx + 20, dy + 17);
				gr.drawLine(dx + 5, dy + 5, dx + 5, dy + 30);
				gr.drawLine(dx + 5, dy + 30, dx + 20, dy + 30);
				gr.drawLine(dx + 20, dy + 17, dx + 20, dy + 30);
				break;
			case 7:
				gr.drawLine(dx + 5, dy + 5, dx + 20, dy + 5);
				gr.drawLine(dx + 20, dy + 5, dx + 20, dy + 30);
				break;
			case 8:
				gr.drawLine(dx + 5, dy + 5, dx + 20, dy + 5);
				gr.drawLine(dx + 5, dy + 5, dx + 5, dy + 30);
				gr.drawLine(dx + 5, dy + 30, dx + 20, dy + 30);
				gr.drawLine(dx + 20, dy + 5, dx + 20, dy + 30);
				gr.drawLine(dx + 5, dy + 17, dx + 20, dy + 17);
				break;
			case 9:
				gr.drawLine(dx + 5, dy + 5, dx + 20, dy + 5);
				gr.drawLine(dx + 5, dy + 5, dx + 5, dy + 17);
				gr.drawLine(dx + 5, dy + 17, dx + 20, dy + 17);
				gr.drawLine(dx + 20, dy + 5, dx + 20, dy + 30);
				break;
			case 10: // A
				gr.drawLine(dx + 5, dy + 5, dx + 20, dy + 5);
				gr.drawLine(dx + 5, dy + 5, dx + 5, dy + 30);
				gr.drawLine(dx + 5, dy + 17, dx + 20, dy + 17);
				gr.drawLine(dx + 20, dy + 5, dx + 20, dy + 30);
				break;
			case 11: // B
				gr.drawLine(dx + 5, dy + 17, dx + 20, dy + 17);
				gr.drawLine(dx + 5, dy + 5, dx + 5, dy + 30);
				gr.drawLine(dx + 5, dy + 30, dx + 20, dy + 30);
				gr.drawLine(dx + 20, dy + 17, dx + 20, dy + 30);
				break;
			case 12: // C
				gr.drawLine(dx + 5, dy + 5, dx + 20, dy + 5);
				gr.drawLine(dx + 5, dy + 5, dx + 5, dy + 30);
				gr.drawLine(dx + 5, dy + 30, dx + 20, dy + 30);
				break;
			case 13: // D
				gr.drawLine(dx + 5, dy + 17, dx + 20, dy + 17);
				gr.drawLine(dx + 5, dy + 17, dx + 5, dy + 30);
				gr.drawLine(dx + 5, dy + 30, dx + 20, dy + 30);
				gr.drawLine(dx + 20, dy + 5, dx + 20, dy + 30);
				break;
			case 14: // E
				gr.drawLine(dx + 5, dy + 5, dx + 20, dy + 5);
				gr.drawLine(dx + 5, dy + 17, dx + 20, dy + 17);
				gr.drawLine(dx + 5, dy + 30, dx + 20, dy + 30);
				gr.drawLine(dx + 5, dy + 5, dx + 5, dy + 30);
				break;
			case 15:
				gr.drawLine(dx + 5, dy + 5, dx + 20, dy + 5);
				gr.drawLine(dx + 5, dy + 17, dx + 20, dy + 17);
				gr.drawLine(dx + 5, dy + 5, dx + 5, dy + 30);
				break;
		}
	}

	/**
	 * El tamaño del Flag Creation date: (27/03/01 16:54:02)
	 *
	 * @return java.awt.Dimension
	 */
	public Dimension getSize() {
		return new Dimension(24, 40);
	}

	public Gate make(Circuit circuit, GateParameters params) {
		return new SegmentDisplay(circuit, this, new ParamSegmentDisplay(0));
	}
}
