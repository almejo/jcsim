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
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.Serializable;

public abstract class IconGateDescriptor extends GateDescriptor implements Serializable {

	// La imagen
	transient public Image image;

	// La imagen del icono
	public IconImage iconImage;

	/**
	 * IconGateDescriptor constructor comment.
	 */
	public IconGateDescriptor() {
		super();
	}

	/**
	 * Dibuja la compuerta en el graphics Creation date: (23/04/01 3:23:10)
	 * 
	 * @param gr
	 *            java.awt.Graphics2D
	 */
	public void drawGate(Graphics2D gr, IconGate icon, int dx, int dy) {
		// Dibujo si se puede
		if (image != null) {
			// if ((this.iconImage.height / Circuit.gridsize) % 2 == 0)
			// dy -= Circuit.gridsize;
			gr.drawImage(image, dx, dy, null);
		}
	}

	/**
	 * Recupera el tama???o de la compuerta. Esto no toma en cuenta la rotacion
	 * de la compuerta. Para eso se debe usar el getRealSize() de IconGate.
	 * Creation date: (27/03/01 16:50:24)
	 * 
	 * @return java.awt.Dimension
	 */
	public Dimension getSize() {
		return new Dimension(this.iconImage.width, this.iconImage.height);
	}

	/**
	 * Insert the method's description here. Creation date: (04/01/01 11:09:34)
	 * 
	 * @param g
	 *            java.awt.Graphics2D
	 */
	public void paint(Graphics2D gr, IconGate icon) {

		// Dibujamos
		if (image == null) {
			// Creamos la nueva imagen
			image = new BufferedImage(iconImage.width, iconImage.height, BufferedImage.TYPE_INT_ARGB);
			int i = 0;
			// Imprimimos el icono en la imagen
			for (int _y = 0; _y < iconImage.height; _y++)
				for (int _x = 0; _x < iconImage.width; _x++)
					((BufferedImage) image).setRGB(_x, _y, iconImage.rgbImage[i++]);
		}

		// Ahora dibujo :)
		super.paint(gr, icon);
	}

	/**
	 * Returns a String that represents the value of this object.
	 * 
	 * @return a string representation of the receiver
	 */
	public String toString() {
		// Insert code to print the receiver here.
		// This implementation forwards the message to super. You may replace or
		// supplement this.
		return super.toString();
	}
}
