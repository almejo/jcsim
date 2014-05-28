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
public class ParamFFData extends GateParameters {

	// El tiempo de retardo
	int delay = 1;

	public ParamFFData() {
		super();
	}

	public ParamFFData(int delay) {
		super();
		this.delay = delay;
	}
}
