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

import cl.alejo.jcsim.csim.simulation.Agenda;

public class PlotEvent extends cl.alejo.jcsim.csim.simulation.Event {
	DiagramCanvas dcanvas;

	public PlotEvent(Agenda agen) {
		super(agen);
	}

	public PlotEvent(Agenda agen, DiagramCanvas dCanvas) {
		super(agen);
		this.dcanvas = dCanvas;
	}

	public void happen() {
		dcanvas.plot();
		program(100);
	}
}
