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
public class ParamSegmentDisplay extends GateParameters {

	// El tiempo de retardo
	int number = 1;

	/**
	 * ParamFFData constructor comment.
	 */
	public ParamSegmentDisplay() {
		super();
	}

	/**
	 * ParamNot constructor comment.
	 */
	public ParamSegmentDisplay(int number) {
		super();
		this.number = number;
	}
}
