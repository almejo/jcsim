package cl.alejo.jcsim.csim.gates;

/**
 * jcsim
 * <p/>
 * Created on Jul 17, 2004
 * <p/>
 * This program is distributed under the terms of the GNU General Public License
 * The license is included in license.txt
 *
 * @author: Alejandro Vera
 */
public class ParamClk extends GateParameters {

	// El delay
	int delayUp, delayDown;

	// El estado del reloj
	byte state = 0;

	public ParamClk() {
		super();
	}

	public ParamClk(int delayUp, int delayDown) {
		super();

		// Cambiamos
		this.delayDown = delayDown;
		this.delayUp = delayUp;
	}
}
