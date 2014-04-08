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
public class ParamClk extends GateParameters {

	// El delay
	int delayUp, delayDown;

	// El estado del reloj
	byte state = 0;

	/**
	 * ParamClk constructor comment.
	 */
	public ParamClk() {
		super();
	}

	/**
	 * ParamClk constructor comment.
	 */
	public ParamClk(int delayUp, int delayDown) {
		super();

		// Cambiamos
		this.delayDown = delayDown;
		this.delayUp = delayUp;
	}
}
