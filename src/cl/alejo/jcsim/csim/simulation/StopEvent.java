/**
 *
 * jcsim
 *
 * Created on Jul 17, 2004
 *
 * This program is distributed under the terms of the GNU General Public License
 * The license is included in license.txt
 *
 * @author: Luis Mateu
 *
 */
package cl.alejo.jcsim.csim.simulation;

class StopEvent extends Event {

	public StopEvent(Agenda agen) {
		super(agen);
	}

	public void happen() {
		_agenda.stop();
	}
}