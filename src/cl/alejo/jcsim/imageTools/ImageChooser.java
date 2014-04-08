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
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;

import cl.alejo.jcsim.csim.gates.IconGateDescriptor;
import cl.alejo.jcsim.csim.gates.IconImage;

public class ImageChooser extends JFrame implements ActionListener {
	// Botones
	JButton buttLoad = new JButton("Load Image");
	JButton buttOk = new JButton("Ok");
	JButton buttCancel = new JButton("Cancel");

	// La imagen
	Image image;

	// El descriptor de la compuerta
	IconGateDescriptor gateDesc;

	// El panel
	ImagePanel imgPanel = new ImagePanel();

	// El filechooser
	JFileChooser fileChooser = new JFileChooser();

	/**
	 * ImageChooser constructor comment.
	 * 
	 * @param title
	 *            java.lang.String
	 */
	public ImageChooser(IconGateDescriptor gateDesc) {

		// el titulo
		super("ImageChooser");

		// El descriptor
		this.gateDesc = gateDesc;

		// La imagen
		this.image = ((IconGateDescriptor) gateDesc).image;

		// Agrego el canvas y la imagen
		imgPanel.image = this.image;
		getContentPane().add("Center", imgPanel);

		// Los botones
		getContentPane().add("Center", imgPanel);
		getContentPane().add("East", buttLoad);

		// Lo botones
		JPanel panel = new JPanel();
		panel.setLayout(new java.awt.GridLayout(0, 2));
		panel.add(buttOk);
		panel.add(buttCancel);

		// Los agrego
		getContentPane().add("South", panel);

		// Agrego los action listeners
		buttLoad.addActionListener(this);
		buttOk.addActionListener(this);
		buttCancel.addActionListener(this);

		// Listo
		pack();
		show();
	}

	/**
	 * Insert the method's description here. Creation date: (30/04/01 3:47:52)
	 * 
	 * @param event
	 *            java.awt.event.ActionEvent
	 */
	public void actionPerformed(ActionEvent event) {
		JButton button = (JButton) event.getSource();

		// Elijo

		if (button == buttLoad) {
			load();
		}
		if (button == buttOk) {
			setImage();
		}
		if (button == buttCancel) {
		}
	}

	/**
	 * Insert the method's description here. Creation date: (30/04/01 4:22:32)
	 */
	public void load() {
		try {

			// Preguntamos el nombre.
			int returnVal = fileChooser.showOpenDialog(this);

			// Si se aprovo el cambio, ok
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				// Elejimos ese archivo
				File file = fileChooser.getSelectedFile();
				// Debug
				System.out.println("loading: " + file.getName() + ".");

				// Abro la imagen
				image = java.awt.Toolkit.getDefaultToolkit().getImage(file.getPath());
				imgPanel.image = image;
				System.out.println("Loaded: " + file.getName() + ".");
			} else {
				System.out.println("load cancelled by user.");
			}
		} catch (Exception e) {
			System.out.println("Otra " + e);
		} finally {
		}
		;
	}

	/**
	 * Insert the method's description here. Creation date: (30/04/01 4:23:05)
	 */
	public void setImage() {
		int width;
		int height;

		// El tamano
		width = image.getWidth(this);
		height = image.getHeight(this);

		// Luego la imprimimos en el BufferedImage
		BufferedImage buffImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		java.awt.Graphics2D gr = (java.awt.Graphics2D) buffImage.getGraphics();
		gr.drawImage(image, 0, 0, null);

		// Ahora recuperamos el rgb
		int[] rgbImage = new int[width * height];
		int z = 0;
		for (int y = 0; y < height; y++)
			for (int x = 0; x < width; x++)
				rgbImage[z++] = buffImage.getRGB(x, y);
		System.out.println(rgbImage.length + " " + z);

		// Ahora creamos su imagen

		cl.alejo.jcsim.csim.gates.IconImage iconImage = new IconImage();
		iconImage.rgbImage = rgbImage;
		iconImage.width = width;
		iconImage.height = height;
		gateDesc.iconImage = iconImage;
		gateDesc.image = null;
	}
}
