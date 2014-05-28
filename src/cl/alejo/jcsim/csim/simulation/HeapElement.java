/**
 *
 * jcsim
 *
 * Created on Jul 17, 2004
 *
 * This program is distributed under the terms of the GNU General Public License
 * The license is included in license.txt
 *
 * @author: Luis Mateu
 *
 */
package cl.alejo.jcsim.csim.simulation;

public class HeapElement implements java.io.Serializable {

	public HeapElement() {
	}

	protected int ndx = -1;

	double key() {
		return 0;
	}

	public int getNdx() {
		return ndx;
	}

	public void setNdx(int ndx) {
		this.ndx = ndx;
	}
}