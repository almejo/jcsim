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
package cl.alejo.jcsim.csim.circuit;

public class Point implements java.io.Serializable {
	public int _x, _y;

	/**
	 * Un nuevo punto en (x,y)
	 *
	 * @param x int  La coordenada x
	 * @param y int  La coordenada y
	 */

	public Point(int x, int y) {
		_x = x;
		_y = y;
	}

	/**
	 * Imprimimos punto
	 *
	 * @return java.lang.String El string con el punto
	 */
	public String toString() {
		return "(" + _x + "," + _y + ")";
	}
}
