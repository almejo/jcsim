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

import java.util.Iterator;

public final class Agenda implements java.io.Serializable {
	private double _currentTime = 0;
	private Heap _heap;
	private StopException _stopException = null;
	private StopEvent _stopEvent;

	/**
	 * Crea una agenda sin eventos programados.
	 *
	 * @author: Alejandro Vera.
	 */
	public Agenda() {
		_stopEvent = new StopEvent(this);
		_heap = new Heap();
	}

	/**
	 * Entrega el momento actual de la simulacion. Esto es igual al momento en
	 * que se extrajo el ultimo evento
	 *
	 * @returns El tiempo actual de la simulacion.
	 */
	public double currentTime() {
		return _currentTime;
	}

	/**
	 * Borra un evento de la simulacion.
	 *
	 * @param event Event  El evento a eliminar
	 */
	public void deleteEvent(Event event) {
		_heap.delete(event);
	}

	/**
	 * Verifica si un evento esta programado o no en esta agenda
	 *
	 * @param event Event El evento
	 * @returns True si el evento esta programado
	 */
	public boolean isProgrammed(Event event) {
		return _heap.findElement(event) >= 0;
	}

	/**
	 * Determina si la simulacion esta activa o no.
	 *
	 * @returns boolean True si la simulacion esta activa
	 */
	public boolean isRunning() {
		return _stopException != null;
	}

	/**
	 * Programa un evento en la agenda
	 *
	 * @param event Event El evento
	 */
	public void programEvent(Event event) {
		_heap.insert(event);
	}

	/**
	 * Vuelve el tiempo a cero. Es util invocar de vez en cuando este
	 * procedimiento para que no haya desborde de tiempo.
	 */
	public void resetTime() {
		for (Iterator iterator = _heap.seeElements(); iterator.hasNext(); ) {
			((Event) iterator.next()).substract(_currentTime);
		}
		_currentTime = 0;
	}

	/**
	 * Avanza la simulacion extrayendo hasta numev eventos. Si numev es
	 * negativo, se avanza la simulacion sin importar el numero de eventos.
	 *
	 * @param double ticks Las unidades de tiempo a simular
	 */
	public void runTime(double ticks) {
		setStop(ticks);
		try {
			while (true) {
				Event nextev = (Event) _heap.extractMin();
				_currentTime = nextev.getTime();
				nextev.happen();
			}
		} catch (EmptyHeapException ex) {
			throw new AgendaException("Agenda vacia: " + this);
		} catch (StopException s) {
		} finally {
			_stopException = null;
		}
	}

	/**
	 * Define cuanto tiempo mas correra la simulacion.
	 *
	 * @param delay double Tiempo en que se producira la detencion del circuito
	 */
	public void setStop(double delay) {
		_stopException = new StopException();
		_stopEvent.program(delay);
	}

	/**
	 * Detiene la simulacion.
	 */
	public void stop() {
		if (!isRunning())
			throw new AgendaException("Current agenda is not running:" + this);
		throw _stopException;
	}

}
