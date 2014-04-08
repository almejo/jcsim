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

public class ComparatorXY implements java.util.Comparator, java.io.Serializable {
	/**
	 * ComparatorXY constructor comment.
	 */
	public ComparatorXY() {
		super();
	}

	/**
	 * compare method comment.
	 */
	public int compare(Object o1, Object o2) {
		Point p1 = (Point) o1;
		Point p2 = (Point) o2;

		if (p1._x == p2._x && p1._y == p2._y)
			return 0;
		if (p1._x < p2._x || p1._x == p2._x && p1._y < p2._y)
			return -1;
		return 1;

	}
}