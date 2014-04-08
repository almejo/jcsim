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

	/**
	 * Insert the method's description here. Creation date: (26/06/01 22:12:47)
	 * 
	 * @return java.lang.Object
	 */
	public Object clone() {
		try {
			return super.clone();
		} catch (Exception e) {
			System.out.println("No se pudo clonar el parametro " + this);
		}
		return null;
	}
}
