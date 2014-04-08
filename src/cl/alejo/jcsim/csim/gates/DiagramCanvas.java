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
import java.awt.Graphics2D;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JPanel;

import cl.alejo.jcsim.csim.dom.Pin;

public class DiagramCanvas extends JPanel implements ComponentListener {
	List listPin = new LinkedList();
	// En donde
	int x = 0;

	// Arreglo con los valores antiguos de cada pin
	int[][] values;

	/**
	 * DiagramCanvas constructor comment.
	 */
	public DiagramCanvas() {
		super();
		values = new int[4][1000];
	}

	/**
	 * Insert the method's description here. Creation date: (23/04/01 8:19:54)
	 * 
	 * @param pin
	 *            csim.Pin
	 */
	public void add(Pin pin) {
		listPin.add(pin);
	}

	/**
	 * Insert the method's description here. Creation date: (01/05/01 18:23:12)
	 * 
	 * @param event
	 *            java.awt.event.ComponentEvent
	 */
	public void componentHidden(ComponentEvent event) {
	}

	/**
	 * Insert the method's description here. Creation date: (01/05/01 18:22:31)
	 * 
	 * @param event
	 *            java.awt.event.ComponentEvent
	 */
	public void componentMoved(ComponentEvent event) {
	}

	/**
	 * Insert the method's description here. Creation date: (01/05/01 18:23:37)
	 * 
	 * @param event
	 *            java.awt.event.ComponentEvent
	 */
	public void componentResized(ComponentEvent event) {
		// El canvas
		Object target = event.getComponent();

		// Veamos
		if (target == this) {
			int width = getSize().width;

			// Creo un nuevo arreglo
			int[][] newvalues = new int[4][width];

			// Copio todos los valores antiguos en los nuevos
			for (int j = 0; j < 4; j++) {
				for (int i = 0; i < width; i++) {
					newvalues[j][i] = values[j][i];
				}
			}

			// Y lo cambio
			values = newvalues;
		}
	}

	/**
	 * Insert the method's description here. Creation date: (01/05/01 18:21:52)
	 * 
	 * @param event
	 *            java.awt.event.ComponentEvent
	 */
	public void componentShown(ComponentEvent event) {
	}

	/**
	 * Insert the method's description here. Creation date: (23/04/01 8:38:40)
	 * 
	 * @param gr1
	 *            java.awt.Graphics
	 */
	public void paint(java.awt.Graphics gr1d) {
		Graphics2D gr = (Graphics2D) gr1d;
		gr.setBackground(Color.gray);
		gr.clearRect(0, 0, getSize().width, getSize().height);

		// Y dibujo los valores guardados
		int width = getSize().width;
		int alt = 12;
		gr.setColor(Color.green);
		for (int j = 0; j < 4; j++) {
			for (int i = 0; i < width; i++) {
				int val = values[j][i];
				switch (val) {
				case 1:
					gr.setColor(Color.green);
					gr.drawLine(i, alt - 10, i + 1, alt - 10);
					break;
				case 0:
					gr.setColor(Color.green);
					gr.drawLine(i, alt, i + 1, alt);
					break;
				default:
					gr.setColor(Color.red);
					gr.drawLine(i, alt - 5, i + 1, alt - 5);
					break;
				}

				// Llenamos el adecuado
				if (i > 0)
					if (values[j][i] != values[j][i - 1])
						gr.drawLine(i, alt - 10, i, alt);
			}
			alt += 20;
		}
	}

	/**
	 * Insert the method's description here. Creation date: (17/04/01 17:18:31)
	 */
	public void plot() {
		// System.out.println("Plotting");
		Graphics2D gr = (Graphics2D) getGraphics();

		// El fondo
		int i = 12;
		if (x > getSize().width)
			x = 0;
		// Borro un poco adelante
		gr.setBackground(Color.gray);
		gr.clearRect(x, 0, 20, getSize().height);

		// Dibujo la linea roja
		gr.setColor(Color.red);
		gr.drawLine(x + 2, 0, x + 2, getSize().height);
		gr.setColor(Color.gray);
		gr.drawLine(x + 1, 0, x + 1, getSize().height);
		Iterator iter = listPin.iterator();
		int ndxVal = 0;
		while (iter.hasNext()) {
			Pin pin = (Pin) iter.next();
			gr.setColor(Color.green);
			int val = pin.getInValue();
			switch (val) {
			case 1:
				gr.drawLine(x, i - 10, x + 1, i - 10);
				break;
			case 0:
				gr.drawLine(x, i, x + 1, i);
				break;
			default:
				gr.setColor(Color.red);
				gr.drawLine(x, i - 5, x + 1, i - 5);
				break;
			}

			// Guardo el antiguo
			values[ndxVal][x] = val;

			// Llenamos el adecuado
			// Llenamos el adecuado
			if (x > 0)
				if (values[ndxVal][x - 1] != Pin.THREE_STATE && values[ndxVal][x] != values[ndxVal][x - 1])
					gr.drawLine(x, i - 10, x, i);
			ndxVal++;
			i += 20;
		}
		x += 1;
	}
}
