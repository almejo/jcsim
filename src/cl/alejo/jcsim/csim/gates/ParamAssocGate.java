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
public class ParamAssocGate extends GateParameters {

	int delay = 1;

	int pinCount;

	int[][] behavior;

	/**
	 * ParamFFData constructor comment.
	 */
	public ParamAssocGate() {
		super();
	}

	/**
	 * ParamFFData constructor comment.
	 */
	public ParamAssocGate(int delay, int pinCount, int[][] behavior) {
		super();
		this.behavior = behavior;
		this.pinCount = pinCount;
		this.delay = delay;
	}
}
