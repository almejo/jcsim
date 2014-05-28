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
public class ParamSegmentDisplay extends GateParameters {

	// El tiempo de retardo
	int number = 1;

	public ParamSegmentDisplay() {
		super();
	}

	public ParamSegmentDisplay(int number) {
		super();
		this.number = number;
	}
}
