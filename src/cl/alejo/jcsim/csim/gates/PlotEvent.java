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

	/**
	 * PlotEvent constructor comment.
	 * 
	 * @param agen
	 *            tools.Agenda
	 */
	public PlotEvent(Agenda agen) {
		super(agen);
	}

	/**
	 * PlotEvent constructor comment.
	 * 
	 * @param agen
	 *            tools.Agenda
	 */
	public PlotEvent(Agenda agen, DiagramCanvas dCanvas) {
		super(agen);
		this.dcanvas = dCanvas;
	}

	/**
	 * Insert the method's description here. Creation date: (17/04/01 17:17:30)
	 */
	public void happen() {
		dcanvas.plot();
		program(100);
	}
}
