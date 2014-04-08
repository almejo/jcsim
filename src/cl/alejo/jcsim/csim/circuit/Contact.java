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
 * Un contacto es la unidad basica de un circuito. Son ellos
 * los que logicamente estan unidos entre si, y ademas contienen
 * los pines de las compuertas. Esta clase es abstracta para
 * que no dependa de como estan implementados los pines.
 * 
 *   
 */

package cl.alejo.jcsim.csim.circuit;

/**
 *  
 */
abstract class Contact extends Point implements java.io.Serializable {

	protected int _conectionMask = 0;

	/**
	 * Un contacto en la posicion (x,y)
	 * 
	 * @param x
	 *            La posicion en x
	 * @param y
	 *            La posicion en y
	 */
	public Contact(int x, int y) {
		super(x, y);
	}

	public boolean isVerticalMiddlePoint() {
		return _conectionMask == ((1 << Protoboard.NORTH) | (1 << Protoboard.SOUTH));
	}

	public boolean isHorizontalMiddlePoint() {
		return _conectionMask == ((1 << Protoboard.WEST) | (1 << Protoboard.EAST));
	}

	public boolean isConnected() {
		return _conectionMask != 0;
	}
}