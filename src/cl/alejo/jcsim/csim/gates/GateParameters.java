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

import java.io.Serializable;

public abstract class GateParameters implements Cloneable, Serializable {
	/**
	 * Parameters constructor comment.
	 */
	public GateParameters() {
		super();
	}

	public Object clone() {
		try {
			return super.clone();
		} catch (Exception e) {
			System.out.println("No se pudo clonar el parametro " + this);
		}
		return null;
	}
}
