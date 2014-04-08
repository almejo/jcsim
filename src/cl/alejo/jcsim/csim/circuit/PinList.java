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
 * una clase que contiene objetos del tipo
 * PinGatePar. Por motivos de eficiencia solo
 * crea una lista cuando hay mas de un par pin/gate
 * 
 */
package cl.alejo.jcsim.csim.circuit;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import cl.alejo.jcsim.csim.dom.Gate;

public class PinList implements Serializable {
	private PinGatePar _pingate;
	private List _pinList = null;

	/**
	 * Agrego un pin a esta pseudo lista de pines
	 * 
	 * @param int pinId El ID del pin
	 * @para csim.Gate El Gate
	 */
	public void add(int pinId, Gate gate) {
		PinGatePar pingate = new PinGatePar((byte) pinId, gate);

		if (_pingate == null)
			_pingate = pingate;
		else {
			if (_pinList == null)
				_pinList = new LinkedList();
			_pinList.add(pingate);
		}
	}

	/**
	 * Buscamos si el par existe
	 * 
	 * @return boolean
	 * @param pinGate
	 *            circuit.PinGatePar
	 */
	public boolean contains(byte pinId, Gate gate) {
		PinGatePar pingateAux;

		if (_pingate != null && equals(_pingate, pinId, gate))
			return true;

		if (_pinList != null) {
			for (Iterator iterator = _pinList.iterator(); iterator.hasNext();) {
				pingateAux = (PinGatePar) iterator.next();
				if (equals(pingateAux, pinId, gate))
					return true;
			}
		}
		return false;
	}

	private boolean equals(PinGatePar pingateAux, byte pinId, Gate gate) {
		return pinId == pingateAux.getPinId() && gate == pingateAux.getGate();
	}

	/**
	 * Retorna un par de pin/gate espefico
	 * 
	 * @return circuit.PinGatePar el par
	 * @param index
	 *            int El indice
	 */
	public Object get(int index) {

		if (index < 0)
			return null;

		if (index == 0) {
			PinGatePar pingate = _pingate;
			return pingate.getGate().getPin(pingate.getPinId());
		}

		int index2 = index++;

		if (_pinList != null) {
			PinGatePar pingate = (PinGatePar) _pinList.get(index2);
			return pingate.getGate().getPin(pingate.getPinId());
		}
		return null;
	}

	public int indexOf(byte pinId, Gate gate) {
		PinGatePar pingateAux;

		if (_pingate != null && equals(_pingate, pinId, gate))
			return 0;

		if (_pinList != null) {
			int ndx = 1;
			for (Iterator iterator = _pinList.iterator(); iterator.hasNext();) {
				pingateAux = (PinGatePar) iterator.next();
				if (equals(pingateAux, pinId, gate))
					return ndx;
				ndx++;
			}
		}

		return -1;

	}

	/**
	 * Veo si esta pseudolista esta vacia Es tan facil como ver si el primer par
	 * esta vacio
	 * 
	 * @return boolean Si existe un pin en esta lista
	 */
	public boolean isEmpty() {
		return _pingate == null;
	}

	/**
	 * Devuelve una lista con todos los pines que contiene
	 * 
	 * @return java.util.List
	 */
	public List list() {
		List list = new LinkedList();

		if (_pingate != null) {
			_pingate.getGate().getPinsAt(_pingate.getPinId(), list);

			if (_pinList != null) {
				for (Iterator iter = _pinList.iterator(); iter.hasNext();) {
					PinGatePar pinGate = (PinGatePar) iter.next();
					pinGate.getGate().getPinsAt(pinGate.getPinId(), list);
				}
			}
		}
		return list;
	}

	/**
	 * Sacamos un elemento de la lista
	 * 
	 * @return circuit.PinGatePar El par removido
	 * @param byte pinId El ID del pin
	 * @param csim
	 *            .Gate El gate
	 */
	public Object remove(byte pinId, Gate gate) {
		return remove(indexOf(pinId, gate));
	}

	/**
	 * Sacamos un elemento de la lista
	 * 
	 * @return circuit.PinGatePar El par removido
	 * @param byte pinId El ID del pin
	 * @param csim
	 *            .Gate El gate
	 */
	public Object remove(int i) {

		if (i < 0)
			return null;

		PinGatePar pingate;
		if (i == 0) {
			pingate = _pingate;
			_pingate = removeAt(0);
		} else {
			pingate = removeAt(i++);
		}

		if (pingate == null)
			return null;

		return pingate.getGate().getPin(pingate.getPinId());
	}

	private PinGatePar removeAt(int index) {
		PinGatePar pingate = null;
		if (_pinList != null) {
			pingate = (PinGatePar) _pinList.remove(index);
			if (_pinList.size() < 1)
				_pinList = null;
		}
		return pingate;
	}

	/**
	 * La cantidad de elemtentos Creation date: (16/07/01 2:24:45)
	 * 
	 * @return int
	 */
	public int size() {

		int size = 0;

		if (_pingate != null)
			size++;

		if (_pinList != null)
			size += _pinList.size();

		return size;
	}
}
