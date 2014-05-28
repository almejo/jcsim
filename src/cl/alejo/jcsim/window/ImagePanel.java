package cl.alejo.jcsim.window;

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

import javax.swing.*;
import java.awt.*;

public class ImagePanel extends JPanel {
	Image image;

	public ImagePanel() {
		super();
	}

	public Dimension getPreferredSize() {
		return new Dimension(200, 200);
	}

	public void paint(Graphics gr1d) {
		Graphics2D gr = (Graphics2D) gr1d;
		Dimension dim = getSize();

		// Limpio el cuestion
		gr.setBackground(Color.gray);
		gr.clearRect(0, 0, dim.width, dim.height);
		gr.drawImage(image, 0, 0, this);

		// Redibujo
		repaint();
	}
}
