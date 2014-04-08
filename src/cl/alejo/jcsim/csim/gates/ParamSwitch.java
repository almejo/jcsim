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
public class ParamSwitch extends GateParameters {

	// El estado del switch
	int state = -1;

	/**
	 * ParamFFData constructor comment.
	 */
	public ParamSwitch() {
		super();
	}

	/**
	 * ParamNot constructor comment.
	 */
	public ParamSwitch(int state) {
		super();
		this.state = state;
	}
}
