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

import cl.alejo.jcsim.csim.circuit.Box;
import cl.alejo.jcsim.csim.circuit.Circuit;
import cl.alejo.jcsim.csim.circuit.Point;
import cl.alejo.jcsim.csim.dom.Gate;
import cl.alejo.jcsim.csim.dom.Pin;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

public class IconGate extends Box {

	// La compuerta que contiene
	protected Gate gate;

	// La componente de rotacion
	protected int rotation = 0;

	// Las transformaciones
	protected AffineTransform transform = new AffineTransform();

	protected AffineTransform transRotate = new AffineTransform();

	protected AffineTransform transTranslate = new AffineTransform();

	public IconGate() {
		super();
	}

	public IconGate(Gate gate) {
		super();

		// Asignamos nuestro gate
		this.gate = gate;

		// Limpiamos las transformaciones
		this.transform.setToIdentity();
		this.transRotate.setToIdentity();
		this.transTranslate.setToIdentity();
	}

	public void apply(int x, int y) {
		// Y Aplicamos el click en el lugar adecuado
		Point point = toGateCoords(x, y);
		gate.apply(point._x, point._y);
	}

	public void clean() {
		gate.clean();
	}

	public IconGate compile() {
		return make();
	}

	public boolean contains(int x, int y) {
		// El centro
		Point pointA = getCenter();

		// Obtengo el punto en coordenas del gate
		// Y la gracia es que esta rotado
		Point pointB = toGateCoords(x, y);

		// Lo traslado devuelta
		pointB._x += pointA._x;
		pointB._y += pointA._y;

		// Y ahora busco
		return super.contains(pointB._x, pointB._y);
	}

	public void drawGate(Graphics2D gr) {

		// Rescatamos el descriptor de la compuerta y lo usamos para pintar
		GateDescriptor gateDesc = gate.getGateDescriptor();

		// Invocamos el paint
		gateDesc.drawGate(gr, this, 0, 0);
	}

	public Pin getPin(int thePin) {
		return gate.getPin(thePin);
	}

	public Point getPinPos(int pinId) {
		return transformedPoint(getPointPin()[pinId]);
	}

	public Point[] getPointPin() {
		return gate.getGateDescriptor().pointPin;
	}

	/**
	 * el tama???o del icono Creation date: (27/03/01 16:57:33)
	 *
	 * @return java.awt.Dimension
	 */
	public Dimension getRotatedSize() {
		// Recupero el tamano
		Dimension dim = gate.getGateDescriptor().getSize();

		// Lo transformo
		Point2D p = new Point2D.Double(dim.width, dim.height);
		transRotate.transform(p, p);

		// Creo el nuevo tamano
		return new Dimension((int) Math.abs(p.getX()), (int) Math.abs(p.getY()));
	}

	public Dimension getSize() {
		try {
			return gate.getGateDescriptor().getSize();
		} catch (Exception e) {
			System.out.println("nada");
		}
		return null;
	}

	public IconGate make() {
		try {
			// Creamos uno nuevo
			return make(gate.getCircuit());
		} catch (Exception e) {
			System.out.println("IconGate::make");
			return null;
		}
	}

	public IconGate make(Circuit circuit) {
		try {
			// Creamos uno nuevo
			IconGate icon = new IconGate();

			// La compuerta
			icon.gate = gate.getGateDescriptor().make(circuit, gate);

			// y le cambiamos sus variables de instancia
			icon.rotation = rotation;

			// Y las transformaciones
			icon.transform = (AffineTransform) transform.clone();
			icon.transTranslate = (AffineTransform) transTranslate.clone();
			icon.transRotate = (AffineTransform) transRotate.clone();

			// Lo devuelvo
			return icon;
		} catch (Exception e) {
			System.out.println("IconGate::make");
			return null;
		}
	}

	/**
	 * Movemos la compuerta
	 * Creation date: (06/04/01 13:32:17)
	 *
	 * @param x int
	 * @param y int
	 */
	public void moveTo(int x, int y) {
		Dimension dim = this.getSize();
		int _x = (int) (x - dim.getWidth() / 2);
		int _y = (int) (y - dim.getHeight() / 2);
		super.moveTo(_x, _y);
		setTranslate(x, y);
	}

	/**
	 * Pintamos la compuerta enla pantalla
	 * Creation date: (09/01/01 17:19:36)
	 *
	 * @param gr java.awt.Graphics2D
	 */
	public void paint(Graphics2D gr) {
		gate.getGateDescriptor().paint(gr, this);
	}

	public int pinCount() {
		return gate.pinCount();
	}

	/**
	 * Entrego una imagen de la compuerta, la imagen que se arrastra
	 * <p/>
	 * Creation date: (23/04/01 4:07:31)
	 */
	public BufferedImage printImage() {

		// Creo la imagen

		Point2D p = new Point2D.Double(getSize().width, getSize().height);
		transRotate.transform(p, p);

		// Los anchos y altos transformados
		int width = (int) Math.abs(p.getX());
		int height = (int) Math.abs(p.getY());

		// Los originales
		int widthOri = getSize().width;
		int heightOri = getSize().height;

		// Creamos la imagen con los tama???os rotados
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

		// Tomo su graphics
		Graphics2D gr = (Graphics2D) image.getGraphics();
		// Un rectangulo para depuracion
		gr.drawRect(0, 0, width - 1, height - 1);

		// Lo transformamos
		gr.translate((int) (width / 2.0), (int) (height / 2.0));
		gr.rotate(Math.PI / 2 * this.rotation);

		// Rescatamos el descriptor de la compuerta y lo usamos para pintar
		GateDescriptor gateDesc = gate.getGateDescriptor();

		// Invocamos el paint
		gateDesc.drawGate(gr, this, -(int) (widthOri / 2.0), -(int) (heightOri / 2.0));
		// Listo
		return image;
	}

	/**
	 * Recalculamos la transformacion de la compuerta Creation date: (06/04/01
	 * 12:01:25)
	 */
	public void recalculateTransformation() {

		// Primero la identidad
		transform.setToIdentity();
		transform.concatenate(transTranslate);
		transform.concatenate(transRotate);
	}

	/**
	 * Recalculamos la transformacion de la compuerta Creation date: (06/04/01
	 * 12:01:25)
	 */
	public void recalculateTransformation(int x, int y) {

		// Primero la identidad
		transform.setToIdentity();
		transform.concatenate(transTranslate);
		transform.translate(x, y);
		transform.concatenate(transRotate);
	}

	public void rotate() {
		// primero lo desactivo
		gate.getCircuit().desactivate(this);
		// roto
		setRotation();
		// Y lo vuelvo a activar
		gate.getCircuit().activate(this);
	}

	public void rotate(int x, int y) {
		// primero lo desactivo
		gate.getCircuit().desactivate(this);
		// roto
		java.awt.geom.Point2D pt = setRotation(x, y);
		// Ahora lo muevo
		moveTo((int) pt.getX(), (int) pt.getY());
		// Y lo vuelvo a activar
		gate.getCircuit().activate(this);
	}

	public Point rotatedPoint(int x, int y) {
		java.awt.geom.Point2D.Double p2d = new java.awt.geom.Point2D.Double(x, y);
		try {
			// Primero un punto2d
			transRotate.inverseTransform(p2d, p2d);
		} catch (Exception e) {
			System.out.println("No invertible");
		}
		return new Point((int) p2d.getX(), (int) p2d.getY());
	}

	public Point rotatedPointInv(int x, int y) {
		java.awt.geom.Point2D.Double p2d = new java.awt.geom.Point2D.Double(x, y);
		try {
			// Primero un punto2d
			transRotate.transform(p2d, p2d);
		} catch (Exception e) {
			System.out.println("No invertible");
		}
		return new Point((int) p2d.getX(), (int) p2d.getY());
	}

	/**
	 * Le cambiamos el Circuito Creation date: (07/05/01 14:51:06)
	 */
	public void setCircuit(Circuit circuit) {
		gate.actualizeCircuit(circuit);
	}

	/**
	 * Cambiamos la rotacion de la compuerta Creation date: (06/04/01 12:30:57)
	 */
	public void setRotation() {

		// La guardo
		this.rotation = (this.rotation + 1) % 4;

		// Cambio la transformacion
		transRotate.setToIdentity();
		transRotate.rotate(Math.toRadians(this.rotation * 90));

		// Y recalculo
		recalculateTransformation();
	}

	/**
	 * Cambiamos la rotacion de la compuerta Creation date: (06/04/01 12:30:57)
	 */
	public java.awt.geom.Point2D setRotation(int x, int y) {

		// Cambio las coordenadas a coordenadas locales
		int _x = Circuit.gridTrunc(x);
		int _y = Circuit.gridTrunc(y);

		// Roto
		setRotation();

		// Una transformacion de rotacion para rotar el centro
		AffineTransform transRotTemp = new AffineTransform();
		transRotTemp.setToIdentity();
		transRotTemp.rotate(Math.toRadians(90));

		// Cambio la transformacion de traslacion
		Point ptC = new cl.alejo.jcsim.csim.circuit.Point((int) this.transTranslate.getTranslateX(),
				(int) transTranslate.getTranslateY());
		Point pt = new Point(_x - ptC._x, _y - ptC._y);
		Point ptRot = new cl.alejo.jcsim.csim.circuit.Point(ptC._x + pt._x, ptC._y + pt._y);
		Point2D.Double p2d = new Point2D.Double(-pt._x, -pt._y);
		transRotTemp.transform(p2d, p2d);
		p2d = new java.awt.geom.Point2D.Double(Circuit.gridTrunc((int) p2d.getX()), Circuit.gridTrunc((int) p2d.getY()));
		// El nuevo centro donde debe estar ubicada la compuerta
		Point2D.Double ptNewCenter = new Point2D.Double(ptRot._x + p2d.x + Circuit.GRIDSIZE, ptRot._y + p2d.y);

		// Retornamos el punto
		return ptNewCenter;
	}

	/**
	 * Calcula la transformada de translacion Creation date: (06/04/01 13:11:08)
	 *
	 * @param x double
	 * @param y double
	 */
	public void setTranslate(double x, double y) {
		// Cambio la transformacion
		transTranslate.setToIdentity();
		transTranslate.translate(x, y);

		// Y recalculo
		recalculateTransformation();
	}

	public Point toGateCoords(int x, int y) {

		// TRansformo el punto a coordenadas de la compuerta
		Point pointA = getCenter();
		Point pointB = new Point(x, y);
		pointB._x -= pointA._x;
		pointB._y -= pointA._y;
		Point pointC = rotatedPoint(pointB._x, pointB._y);

		// Listo
		return pointC;
	}

	public String toString() {
		return getClass().getName();
	}

	public Point transformedPoint(int x, int y) {
		// Primero un punto2d
		java.awt.geom.Point2D.Double p2d = new java.awt.geom.Point2D.Double(x, y);
		transform.transform(p2d, p2d);
		return new Point((int) p2d.getX(), (int) p2d.getY());
	}

	public Point transformedPoint(Point p) {
		// Primero un punto2d
		java.awt.geom.Point2D.Double p2d = new java.awt.geom.Point2D.Double(p._x, p._y);
		transform.transform(p2d, p2d);
		return new Point((int) p2d.getX(), (int) p2d.getY());
	}

	public Gate getGate() {
		return gate;
	}

}