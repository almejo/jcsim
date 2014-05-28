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
package cl.alejo.jcsim.csim.simulation;

/**
 * Un evento. Esta clase se debe extender para redefinir el procedimiento
 * happen() y asi ejecutar una accion cuando el evento sucede.
 */
public abstract class Event extends HeapElement {

	protected double _time;

	protected Agenda _agenda = null;


	public Event() {
	}

	/**
	 * Crea un nuevo evento asociado a una agenda de eventos
	 *
	 * @param agenda Tools.Agenda  la agenda asociada a este evento
	 */
	public Event(Agenda agenda) {
		_agenda = agenda;
		ndx = -1;
	}

	/**
	 * Desprograma el evento de la agenda
	 */
	public final void cancel() {
		if (isProgrammed()) {
			_agenda.deleteEvent(this);
		}
	}

	/**
	 * Es invocado por la agenda cuando el evento ocurre. Redefinir este metodo
	 * para realizar una accion cuando ocurra este evento.
	 */
	public abstract void happen();

	/**
	 * Indica si el evento esta programado. Si el evento en cuestion
	 *
	 * @return true Si este evento esta programado
	 */

	public final boolean isProgrammed() {
		return _agenda.isProgrammed(this);
	}

	/**
	 * Retorna la llave del evento, que en este caso corresponde al tiempo de
	 * ocurrencia.
	 *
	 * @return _time El tiempo de ocurrencia.
	 */
	public double key() {
		return _time;
	}

	/**
	 * Le resta <i>time </i> al tiempo en que ocurrira este evento
	 *
	 * @param time double  El tiempo a restar
	 */

	public final void substract(double time) {
		_time -= time;
	}

	/**
	 * Programa este evento en la agenda de trabajo.
	 *
	 * @param delay double En cuanto tiempo mas se debe invocar el evento
	 */
	public final void program(double delay) {
		if (isProgrammed())
			cancel();

		_time = _agenda.currentTime() + delay;
		_agenda.programEvent(this);
	}

	/**
	 * Retorna el tiempo de ocurrencia.
	 *
	 * @return _time El tiempo de ocurrencia.
	 */
	public double getTime() {
		return _time;
	}

	/**
	 * Setea el tiempo de ocurrencia
	 */

}