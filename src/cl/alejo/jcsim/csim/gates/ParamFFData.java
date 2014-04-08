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
public class ParamFFData extends GateParameters {

	// El tiempo de retardo
	int delay = 1;

	/**
	 * ParamFFData constructor comment.
	 */
	public ParamFFData() {
		super();
	}

	/**
	 * ParamFFData constructor comment.
	 */
	public ParamFFData(int delay) {
		super();
		this.delay = delay;
	}
}
