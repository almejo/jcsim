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
package cl.alejo.jcsim.csim.dom;

import cl.alejo.jcsim.csim.circuit.Circuit;
import cl.alejo.jcsim.csim.simulation.Event;
import cl.alejo.jcsim.csim.simulation.Link;

public class Pin extends Link implements java.io.Serializable {
	public static final byte THREE_STATE = -1;

	protected byte _inValue = THREE_STATE;
	protected byte _outValue = THREE_STATE;
	protected byte _programedValue = THREE_STATE;
	protected byte _id = -1;
	protected Event _event;

	protected Gate _gate;
	protected Circuit _circuit;

	public Pin() {

	}

	public Pin(Gate theGate, Circuit circ, byte id) {
		_gate = theGate;
		_circuit = circ;
		_id = id;
	}

	/**
	 * Conecta un pin a la lista que contiene a este
	 *
	 * @param pin csim.Pin El pin a conectar
	 */
	public final void connect(Pin pin) {
		join(pin);
		flow();
	}

	/**
	 * Desconecta este pin. El resto de los pines continua conectado
	 */
	public final void disconnect() {
		Pin succpin = (Pin) next();
		delete(this);
		succpin.flow();
		flow();
	}

	// Otros Metodos privados
	// ***** ******* ********

	final void flow() {
		// TODO: refactorizar este metodo
		byte value = THREE_STATE;
		byte hotValue = THREE_STATE;
		Pin pin = this;
		do {
			// Si el pin tiene un valor de salida
			if (pin._outValue != THREE_STATE) {
				// Si el pin esta programado
				if (!pin._event.isProgrammed()) {
					// Si encontramos un valor que difiera del
					// valor del resto de los pines
					if (hotValue != THREE_STATE && hotValue != pin._outValue) {
						System.err.println("Corto-circuito!");
						break;
					} else {
						hotValue = pin._outValue;
					}
				} else {
					value = pin._outValue;
				}
			}
			pin = (Pin) pin.next();
		} while (pin != this);

		// Elejimos cual sera el valor de esta
		// lista de pines
		// si existe un hotVal, se usa ese, sino, el valor de los pines sin
		// programar
		byte lineVal = (hotValue != THREE_STATE) ? hotValue : value;

		// Recorremos la lista
		pin = this;
		do {
			// si el pin tiene un valor de entrada distinto al de la linea
			// y su valor de salida no es threeState
			// y su valor programado no es threeState
			// entonces ha cambiado
			if (lineVal != pin._inValue && pin._outValue == THREE_STATE && pin._programedValue == THREE_STATE) {
				pin._inValue = lineVal;
				pin.hasChanged();
			} else {
				pin._inValue = lineVal;
			}

			// Avanzamos al siguiente
			pin = (Pin) pin.next();
		} while (pin != this);
	}

	/**
	 * Obtiene el valor de entrada de un pin.
	 */
	public final byte getInValue() {
		return _inValue;
	}

	/**
	 * Redefinir este procedimiento cuando se desea llevar a cabo alguna accion
	 * cuando el valor de entrada de un pin cambie.
	 */
	public void hasChanged() {
	}

	/**
	 * Programa un cambio en la salida en <i>delay</i> unidades de tiempo
	 *
	 * @param delay Cuanto tiempo mas para que ocurra
	 */
	public final void programOut(byte newValue, int delay) {
		if (_circuit == null)
			return;
		if (_programedValue != newValue) {
			if (_event == null) {
				_event = new PinEvent(this, _circuit.getAgenda());
			}
			_programedValue = newValue;
			if (_programedValue != _outValue) {
				_event.program(delay);
			}
		}
	}

	/**
	 * Este metodo se hace necesario para poder copiar un icono de un circuito a
	 * otro... Ebemos primero remover todo rastro del circuito antiguo
	 *
	 * @param circuit circuit.Circuit
	 */
	public void setCircuit(Circuit circuit) {
		_circuit = circuit;

		if (_event != null) {
			_event.cancel();
		}
	}

	/**
	 * Le asociamos un gate Esto es porque cuando se clona un pin, este queda
	 * asociado al gate original. Asi esta funcion debe ser llamada
	 * inmediatamente despues del clone();
	 *
	 * @param gate csim.Gate La compuerta nueva del pin
	 */
	public void setGate(Gate gate) {
		_gate = gate;
	}

	public Gate getGate() {
		return _gate;
	}
}
