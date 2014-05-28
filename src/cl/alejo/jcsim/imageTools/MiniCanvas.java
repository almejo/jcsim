package cl.alejo.jcsim.imageTools;

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

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MiniCanvas extends javax.swing.JPanel {
	List listImage;

	public MiniCanvas() {
		super();
		listImage = new ArrayList();
	}

	public void add(Image image) {
		listImage.add(image);
	}

	public Dimension getPreferredSize() {
		return new Dimension(600, 400);
	}

	public void paint(Graphics gr) {
		int i = 0;
		Dimension d = getSize();

		// Limpiamos el fondo
		gr.clearRect(0, 0, d.width, d.height);
		Iterator it = listImage.iterator();
		while (it.hasNext()) {
			Image image = (Image) it.next();
			gr.drawImage(image, i, 0, this);
			i += 50;
		}

		repaint();
	}
}
