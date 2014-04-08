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
public class ParamNot extends GateParameters {

	// El tiempo de retardo
	int delay = 1;

	// El estatico
	public static ParamNot paramNotDefault = new ParamNot(1);

	/**
	 * ParamFFData constructor comment.
	 */
	public ParamNot() {
		super();
	}

	/**
	 * ParamNot constructor comment.
	 */
	public ParamNot(int delay) {
		super();
		this.delay = delay;
	}
}
