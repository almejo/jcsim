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

import cl.alejo.jcsim.csim.simulation.Agenda;
import cl.alejo.jcsim.csim.simulation.Event;

/**
 * Un evento de este tipo se usa para los pines de salida, para que programen su
 * valor de salida en el futuro
 */
class PinEvent extends Event implements java.io.Serializable {
	protected Pin _pin;
	protected Agenda _agenda;

	PinEvent(Pin pin, Agenda agenda) {
		super(agenda);
		_pin = pin;
		_agenda = agenda;
	}

	/**
	 * Cuando se invoca este evento, se cambia efectivamente el valor de salida
	 * del pin asociado
	 */
	public void happen() {
		_pin._outValue = _pin._programedValue;
		_pin.flow();
	}

	public String toString() {
		return "" + this._pin;
	}
}
