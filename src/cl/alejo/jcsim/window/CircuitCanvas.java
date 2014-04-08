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
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import javax.swing.JPanel;

import cl.alejo.jcsim.csim.circuit.Box;
import cl.alejo.jcsim.csim.circuit.Circuit;

public class CircuitCanvas extends JPanel {

	// El circuito asociado
	Circuit circuit;
	// La ventana que lo posee
	Window window;

	// **************************************************************************+
	// Todo lo que tiene que ver con transformaciones
	// **************************************************************************+

	// TRansformaciones necesarias
	AffineTransform transIdentity = new AffineTransform();
	AffineTransform transform = new AffineTransform();
	AffineTransform transZoom = new AffineTransform();
	AffineTransform transTranslate = new AffineTransform();

	// El zoom
	double zoom = 1;

	// La grilla
	boolean showGrid = false;
	int xGridOrigin;
	int yGridOrigin;

	// El viewport de esta ventana
	public Box boxViewport = new Box(0, 0, 0, 0);

	// Los colores
	Color colorBackground = Color.lightGray;
	Color colorNullCircuit = Color.orange;
	Color colorWireThreeState = Color.green;
	Color colorWireOn = Color.red;
	Color colorWireOff = Color.black;

	/**
	 * CircCanvas constructor comment.
	 */
	public CircuitCanvas(Circuit circuit, Window window) {
		super();
		this.circuit = circuit;

		// Cambiamos el area visible del circuito
		Dimension d = getSize();
		setViewport(0, 0, d.width, d.height);

		// Ponemos la identidad en identidad
		transIdentity.setToIdentity();
		transform.setToIdentity();
		transZoom.setToIdentity();
		transTranslate.setToIdentity();

		// Seteamos la ventana
		this.window = window;

		// Los listeners
		this.addMouseListener(window);
		this.addMouseMotionListener(window);
		this.addComponentListener(window);

	}

	/**
	 * Calcula el origen de donde debe empezar a dibujarse la grilla Creation
	 * date: (04/10/01 14:22:34)
	 */
	public void calculateGrid() {
		xGridOrigin = (boxViewport.getXi() - Circuit.gridTrunc(boxViewport.getXi()));
		yGridOrigin = (boxViewport.getYi() - Circuit.gridTrunc(boxViewport.getYi()));
		System.out.println(boxViewport.getXi() + "," + boxViewport.getYi());
		System.out.println(xGridOrigin + "," + yGridOrigin);
	}

	/**
	 * Computo la transformada del mundo Creation date: (10/04/01 18:00:27)
	 */
	public void computeTransform() {

		// Primero identidad
		transform.setToIdentity();
		transform.concatenate(transZoom);
		transform.concatenate(transTranslate);

	}

	public void drawCircuit(Graphics2D g2) {

		// Una nueva caja zoomeada
		Box boxZoomed = new Box(boxViewport);
		boxZoomed.scale(1.0 / zoom);

		this.circuit.paint(g2, boxZoomed);
	}

	/**
	 * Insert the method's description here. Creation date: (19/09/01 16:44:23)
	 * 
	 * @param gr
	 *            java.awt.Graphics2D
	 */
	public void drawGrid(Graphics2D gr) {
		gr.setColor(Color.lightGray);

		// El tama???o
		int width = Circuit.gridTrunc(boxViewport.getWidth()) + Circuit.GRIDSIZE;
		int height = Circuit.gridTrunc(boxViewport.getHeight() + Circuit.GRIDSIZE);
		// Primero una regilla
		for (int i = boxViewport.getXi(); i < height; i += Circuit.GRIDSIZE) {
			if ((i % 80) == 0) {
				gr.setColor(Color.darkGray);
				gr.drawLine(0, i, width, i);
				gr.setColor(Color.lightGray);
			} else
				gr.drawLine(0, i, width, i);
		}
		for (int i = yGridOrigin; i < width; i += Circuit.GRIDSIZE)
			if ((i % 80) == 0) {
				gr.setColor(Color.darkGray);
				gr.drawLine(i, 0, i, height);
				gr.setColor(Color.lightGray);
			} else
				gr.drawLine(i, 0, i, height);
	}

	/**
	 * Insert the method's description here. Creation date: (18/01/01 20:59:08)
	 */
	public Box extent() {
		return circuit.getExtent();
	}

	/**
	 * Devuelve x en coordenadas del circuito Creation date: (07/04/01 15:54:06)
	 * 
	 * @return int
	 * @param x
	 *            int
	 */
	public int getTransformedX(int x) {
		return (int) (((double) x) / zoom + boxViewport.getXi());
	}

	/**
	 * Devuelve x en coordenadas del circuito Creation date: (07/04/01 15:54:21)
	 * 
	 * @return int
	 * @param y
	 *            int
	 */
	public int getTransformedY(int y) {
		return (int) (((double) y) / zoom + boxViewport.getYi());
	}

	/**
	 * Insert the method's description here. Creation date: (18/01/01 21:02:08)
	 * 
	 * @return circuit.Box
	 */
	public Box getViewport() {
		return boxViewport;
	}

	public void paint(Graphics g) {

		// Casteamos el graphic
		Dimension d = getSize();
		Graphics2D gr = (Graphics2D) g;

		// Todo ocurre si el circuito existe
		if (circuit != null) {

			// Limpiamos el fondo
			gr.setBackground(colorBackground);
			gr.clearRect(0, 0, d.width, d.height);

			// Dibujamos la grilla
			if (showGrid)
				drawGrid(gr);

			// Recupero la antigua transformada
			AffineTransform transNew = new AffineTransform(transform);
			transNew.concatenate(gr.getTransform());
			// CAmbiamos la transformacion del circuito para
			// TRasladar y zoomear
			gr.setTransform(transNew);

			// Dibujamos el circuito
			if (this.circuit != null)
				drawCircuit(gr);

			// DEvolvemos al principio
			gr.setTransform(transIdentity);
		} else {

			// Limpiamos el fondo con otro color

			gr.setBackground(colorNullCircuit);
			gr.clearRect(0, 0, d.width, d.height);
		}
	}

	/**
	 * Cambia el color de fondo del canvas Creation date: (15/10/01 13:16:05)
	 */
	public void setBgcolor() {
		colorBackground = javax.swing.JColorChooser.showDialog(this, "Background Color...", colorBackground);
	}

	/**
	 * Attachea un circuito a este canvas Creation date: (26/03/01 20:52:23)
	 * 
	 * @param circuit
	 *            circuit.Circuit - El circuito
	 */
	public void setCircuit(Circuit circuit) {
		this.circuit = circuit;
	}

	/**
	 * Insert the method's description here. Creation date: (04/10/01 2:17:46)
	 * 
	 * @param active
	 *            boolean
	 */
	public void setGrid(boolean flag) {
		showGrid = flag;
	}

	/**
	 * Insert the method's description here. Creation date: (16/01/01 22:53:25)
	 * 
	 * @param x
	 *            int
	 * @param y
	 *            int
	 */
	public void setViewport(int xi, int yi, int xf, int yf) {
		this.boxViewport
			.set(Circuit.gridTrunc(xi), Circuit.gridTrunc(yi), Circuit.gridTrunc(xf), Circuit.gridTrunc(yf));
	}

	/**
	 * Insert the method's description here. Creation date: (16/01/01 22:57:28)
	 * 
	 * @param xi
	 *            int
	 * @param yi
	 *            int
	 */
	public void setViewportCenter(int xi, int yi) {

		// Los anchos para los desplazamientos
		int width = boxViewport.getWidth();
		int height = boxViewport.getHeight();

		// Desplazo la esquina
		int x = xi - width / 2;
		int y = yi - height / 2;

		// seteo la esquina
		setViewportCorner(x, y);

		// Debug
		System.out.println(xi + " " + yi);
		System.out.println(x + " " + y);

	}

	/**
	 * Insert the method's description here. Creation date: (09/04/01 19:18:07)
	 * 
	 * @param x
	 *            int
	 * @param y
	 *            int
	 */
	public void setViewportCorner(int x, int y) {

		// La caja
		boxViewport.moveTo(x, y);
		System.out.println("Trasladado a " + x + "," + y);
		// La transformada
		transTranslate.setToIdentity();
		transTranslate.translate(-x, -y); // Despues traslado

		// Y recalculo
		computeTransform();

		// Y cambio la grilla
		calculateGrid();
	}

	/**
	 * Insert the method's description here. Creation date: (16/01/01 22:42:26)
	 * 
	 * @param y
	 *            int
	 */
	public void setViewportHeight(int y) {
		// La caja
		boxViewport.setYf(boxViewport.getYi() + Circuit.gridTrunc(y));

	}

	/**
	 * Insert the method's description here. Creation date: (16/01/01 22:42:06)
	 * 
	 * @param x
	 *            int
	 */
	public void setViewportWidth(int x) {
		boxViewport.setXf(boxViewport.getXi() + Circuit.gridTrunc(x));
	}

	/**
	 * Cambiamos el zoom del circuito Creation date: (06/04/01 12:14:06)
	 * 
	 * @param zoom
	 *            double
	 */
	public void setZoom(double zoom) {

		// El zoom
		this.zoom = zoom;
		transZoom.setToIdentity();
		transZoom.scale(zoom, zoom);

		// Cambiado el mundo
		computeTransform();
	}

}
