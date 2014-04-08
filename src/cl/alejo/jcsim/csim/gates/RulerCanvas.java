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
 * Un panel que tiene los nombres de las 
 * entradas de un diagrama de tiempo
 * 
 */
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JPanel;

public class RulerCanvas extends JPanel {
	List listNames = new LinkedList();

	/**
	 * Insert the method's description here. Creation date: (23/04/01 7:34:25)
	 * 
	 * @return java.awt.Dimension
	 */
	public Dimension getPreferredSize() {

		// Calculo el alto
		int height = 20;

		// El ancho
		int widht = 0;

		// Listo
		return new Dimension(widht, height);
	}

	/**
	 * Insert the method's description here. Creation date: (23/04/01 7:45:38)
	 * 
	 * @param gr
	 *            java.awt.Graphics2D
	 */
	public void paint(java.awt.Graphics gr1d) {
		Graphics2D gr = (Graphics2D) gr1d;

		// Limpio el fondo
		gr.setBackground(Color.gray);
		gr.clearRect(0, 0, getSize().width, getSize().height);

		// Dibujo la regla
		gr.setColor(new Color(180, 180, 180));
		gr.drawLine(20, 5, getSize().width, 5);
		for (int i = 20; i < getSize().width; i += 5)
			gr.drawLine(i, 3, i, 7);
	}

	/**
	 * Insert the method's description here. Creation date: (23/04/01 7:34:25)
	 * 
	 * @return java.awt.Dimension
	 */
	public Dimension PreferredSize() {

		// Calculo el alto
		int height = listNames.size() * 20;

		// El ancho
		int widht = 0;

		// Recorro y busco el mayor
		Iterator iter = listNames.iterator();
		while (iter.hasNext()) {
			String name = (String) iter.next();
			int newWidht = name.length() * 8;
			if (widht < newWidht)
				widht = newWidht;
		}
		return new Dimension(widht, height);
	}
}
