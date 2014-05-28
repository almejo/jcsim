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

import cl.alejo.jcsim.csim.dom.Gate;
import cl.alejo.jcsim.csim.simulation.Agenda;

public class ClkEvent extends cl.alejo.jcsim.csim.simulation.Event {
	// El reloj
	Clk clk;

	public ClkEvent(Gate gate, Agenda agen) {
		super(agen);

		// Asignamos
		this.clk = (Clk) gate;
	}

	/**
	 * Cuando se ejecuta, se tiene que hacer el hasChanged Loego vuelve a
	 * reprogramarse Creation date: (26/03/01 21:24:13)
	 */
	public void happen() {

		// Recupero los valores
		int state = ((ParamClk) clk.getParameters()).state;
		int delayDown = ((ParamClk) clk.getParameters()).delayDown;
		int delayUp = ((ParamClk) clk.getParameters()).delayUp;

		// Reprogramamos el cambio para el reloj
		if (state == 0) {
			program(delayDown);
		} else {
			program(delayUp);
		}

		// Ahora cambiamos el valor del reloj para ahora.
		clk.getPin()[0].hasChanged();

		// Cambiamos el valor del reloj
		((ParamClk) clk.getParameters()).state = (byte) (state == 0 ? 1 : 0);
	}
}
