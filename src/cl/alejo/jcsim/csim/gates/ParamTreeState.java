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
public class ParamTreeState extends GateParameters {

	private int _delay = 1;

	// El estatico
	public static ParamTreeState PARAM_TREE_STATE_DEFAULT = new ParamTreeState(1);

	/**
	 * ParamFFData constructor comment.
	 */
	public ParamTreeState() {
		super();
	}

	/**
	 * ParamNot constructor comment.
	 */
	public ParamTreeState(int delay) {
		super();
		_delay = delay;
	}

	void setDelay(int delay) {
		_delay = delay;
	}

	int getDelay() {
		return _delay;
	}
}
