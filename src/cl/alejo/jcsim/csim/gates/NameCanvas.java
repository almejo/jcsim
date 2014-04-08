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
 * Un panel que tiene los nombres de las entradas de un diagrama de tiempo
 *  
 */
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class NameCanvas extends JPanel implements MouseListener {

	private JPanel _panel;

	// la lista de nombres de los pines
	private static final int WIDTH_DELTA = 8;

	private static final int HEIGHT_DELTA = 20;

	private List listNames = new LinkedList();

	public NameCanvas(JPanel parent) {
		_panel = parent;
		addMouseListener(this);
	}

	/**
	 * Insert the method's description here. Creation date: (23/04/01 7:32:13)
	 * 
	 * @param label
	 *            java.lang.String
	 */
	public void add(String label) {

		// Agrego el label a la lista
		listNames.add(label);
	}

	/**
	 * Insert the method's description here. Creation date: (23/04/01 7:34:25)
	 * 
	 * @return java.awt.Dimension
	 */
	public Dimension getPreferredSize() {

		// Calculo el alto
		int height = listNames.size() * HEIGHT_DELTA;

		// El ancho
		int widht = 0;

		// Recorro y busco el mayor
		Iterator iter = listNames.iterator();
		while (iter.hasNext()) {
			String name = (String) iter.next();
			int newWidht = name.length() * WIDTH_DELTA;
			if (widht < newWidht)
				widht = newWidht;
		}
		if (widht < 50) {
			widht = 50;
		}
		if (height < getHeight()) {
			height = getHeight();
		}

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

		// dibujo los nombres
		int i = 12;
		Iterator iter = listNames.iterator();
		while (iter.hasNext()) {
			gr.drawString((String) iter.next(), 10, i);
			i += HEIGHT_DELTA;
		}
	}

	/**
	 * Insert the method's description here. Creation date: (23/04/01 7:34:25)
	 * 
	 * @return java.awt.Dimension
	 */

	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() < 2)
			return;
		if (e.getY() / HEIGHT_DELTA <= listNames.size()) {

			int n = e.getY() / HEIGHT_DELTA;
			String name = (String) listNames.get(n);
			String newName = (String) JOptionPane.showInputDialog((Component) getParent(), "Ingrese el nuevo label",
				name);
			replaceName(n, newName);
			setSize(getMinimumSize());
			repaint();
			_panel.revalidate();
		}
	}

	private void replaceName(int n, String newName) {
		if (newName != null) {
			listNames.remove(n);
			listNames.add(n, newName);
		}
	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

}