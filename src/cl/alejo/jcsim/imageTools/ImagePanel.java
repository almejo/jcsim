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
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.JPanel;

public class ImagePanel extends JPanel {
	Image image;

	/**
	 * ImagePanel constructor comment.
	 */
	public ImagePanel() {
		super();
	}

	/**
	 * Insert the method's description here. Creation date: (30/04/01 3:51:50)
	 * 
	 * @return java.awt.Dimension
	 */
	public Dimension getPreferredSize() {
		return new Dimension(200, 200);
	}

	/**
	 * Insert the method's description here. Creation date: (30/04/01 3:55:39)
	 * 
	 * @param gr1d
	 *            java.awt.Graphics
	 */
	public void paint(Graphics gr1d) {
		Graphics2D gr = (Graphics2D) gr1d;
		Dimension dim = getSize();

		// Limpio el cuestion
		gr.setBackground(Color.gray);
		gr.clearRect(0, 0, dim.width, dim.height);
		gr.drawImage(image, 0, 0, this);

	}
}
