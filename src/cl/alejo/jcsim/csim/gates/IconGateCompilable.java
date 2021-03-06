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
import java.awt.geom.AffineTransform;

public class IconGateCompilable extends IconGate {
	/**
	 * IconGateCompilable constructor comment.
	 */
	public IconGateCompilable() {
		super();
	}

	/**
	 * IconGateCompilable constructor comment.
	 * 
	 * @param gate
	 *            csim.Gate
	 */
	public IconGateCompilable(cl.alejo.jcsim.csim.dom.Gate gate) {
		super(gate);
	}

	/**
	 * Insert the method's description here. Creation date: (25/06/01 21:25:56)
	 */
	public IconGate compile(cl.alejo.jcsim.csim.circuit.Circuit circuit) {
		try {
			// Creamos uno nuevo
			IconGate icon = new IconGate();

			// La compuerta
			icon.gate = gate.compile(circuit, (cl.alejo.jcsim.csim.circuit.Box) this);

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
	 * Insert the method's description here. Creation date: (26/06/01 23:28:21)
	 */
	public IconGate make(cl.alejo.jcsim.csim.circuit.Circuit circuit) {
		try {
			// Creamos uno nuevo
			IconGate icon = new IconGateCompilable();

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
			System.out.println("IconGateCompilable::make");
			return null;
		}
	}
}
