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
 * Subclase que simula un contacto que contiene un
 * Pin.
 *
 */
package cl.alejo.jcsim.csim.circuit;

import cl.alejo.jcsim.csim.dom.Pin;

public class ContactPin extends Contact {

	private PinList _pinList;

	private Pin _guidePin = null;

	/**
	 * Se crea un contactPin en la coordenada (x,y)
	 *
	 * @param x int La coordenada x
	 * @param y int
	 */
	public ContactPin(int x, int y) {
		super(x, y);
	}

	/**
	 * Verificamos si dos contactos son iguales
	 *
	 * @param o java.lang.Object El objeto con que compararemos
	 * @return boolean Si el punt es el mismo o no
	 */
	public boolean equals(Object o) {
		if (!(o instanceof Contact) || o == null) {
			return false;
		}

		return ((ContactPin) o)._x == _x && ((ContactPin) o)._y == _y;
	}

	/**
	 * Obtenemos el valor de los pines en ese contacto
	 *
	 * @return byte El valor
	 */
	public byte getVal() {
		if (_pinList == null) {
			return -1;
		}

		return ((Pin) _pinList.get(0)).getInValue();
	}

	public Pin getGuidePin() {
		return _guidePin;
	}

	public PinList getPinList() {
		return _pinList;
	}

	public void setGuidePin(Pin pin) {
		_guidePin = pin;
	}

	public void setPinList(PinList list) {
		_pinList = list;
	}

	public boolean hasPins() {
		return _pinList != null && _pinList.size() > 0;
	}
}
