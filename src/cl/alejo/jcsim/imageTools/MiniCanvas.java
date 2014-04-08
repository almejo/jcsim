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
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MiniCanvas extends javax.swing.JPanel {
	List listImage;

	/**
	 * MiniCanvas constructor comment.
	 */
	public MiniCanvas() {
		super();
		listImage = new ArrayList();
	}

	/**
	 * Insert the method's description here. Creation date: (22/03/01 17:50:10)
	 * 
	 * @param image
	 *            java.awt.Image
	 */
	public void add(Image image) {
		listImage.add(image);
	}

	/**
	 * Insert the method's description here. Creation date: (20/03/01 18:04:44)
	 */

	public Dimension getPreferredSize() {
		return new Dimension(600, 400);
	}

	/**
	 * Insert the method's description here. Creation date: (20/03/01 18:12:23)
	 */
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
