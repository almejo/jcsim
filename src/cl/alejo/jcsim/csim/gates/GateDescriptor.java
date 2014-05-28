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

import cl.alejo.jcsim.csim.circuit.Circuit;
import cl.alejo.jcsim.csim.circuit.Point;
import cl.alejo.jcsim.csim.dom.Gate;

import java.awt.*;
import java.awt.geom.AffineTransform;

public abstract class GateDescriptor implements java.io.Serializable {
	// Los tipos de Gate que existen
	public static final int GATE_TYPE_NORMAL = 0;
	public static final int GATE_TYPE_DEBUG = 1;

	// El nombre de la compuerta
	public String sGateName;

	// Las posicones de los pines
	public Point[] pointPin;

	// La cantidad de pines
	public int pinCount = 0;

	// Los parametros para crear un nuevo gate
	GateParameters params;

	// Las transformadas
	AffineTransform transOld;
	AffineTransform transActive;

	// El tipo
	public int typeOfGate = 0;

	/**
	 * GateDescriptor constructor comment.
	 */
	public GateDescriptor() {
		super();
	}

	/**
	 * GateDescriptor constructor comment.
	 */
	public GateDescriptor(String sGateName) {
		// EL archivo grafico
		this.sGateName = sGateName;
	}

	public abstract void drawGate(Graphics2D gr, IconGate icon, int dx, int dy);

	/**
	 * para obtener el tamano de este icono Creation date: (27/03/01 16:47:38)
	 *
	 * @return java.awt.Dimension
	 */
	abstract Dimension getSize();

	public Gate make(Circuit circuit) {
		return make(circuit, params);
	}

	public Gate make(Circuit circuit, Gate gate) {
		return make(circuit, gate.getParameters());
	}

	public abstract Gate make(Circuit circuit, GateParameters params);

	public void paint(Graphics2D gr, IconGate icon) {

		// Obtengo el tamano del icono
		Dimension dim = icon.getSize();

		// Transformamos el espacio
		pushMatrix(gr, icon);

		// Dibujemos el reloj
		drawGate(gr, icon, -Circuit.gridTrunc(dim.width / 2), -Circuit.gridTrunc(dim.height / 2));

		// Y dejamos como antes
		popMatrix(gr);
	}

	public int pinCount() {
		return pinCount;
	}

	public void popMatrix(Graphics2D gr) {
		// Y dejamos como antes
		gr.setTransform(transOld);
	}

	public void pushMatrix(Graphics2D gr, IconGate icon) {
		// Transformamos el espacio

		transOld = gr.getTransform();
		transActive = new AffineTransform(gr.getTransform());
		transActive.concatenate(icon.transform);
		gr.setTransform(transActive);
	}

}
