package cl.alejo.jcsim.csim.circuit;

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
 * Una clase que contiene el pinID y el gate al que pertenece.
 */

import java.io.Serializable;

import cl.alejo.jcsim.csim.dom.Gate;

public class PinGatePar implements Serializable {
	private byte _pinId = 0;

	private Gate _gate;

	/**
	 * Un par (pinID, Gate)
	 * 
	 * @param byte pinId El ID del pin que estamos guardando
	 * @param csim
	 *            .Gate el Gate a que pertenece el pin
	 */
	public PinGatePar(byte pinId, Gate gate) {
		_pinId = pinId;
		_gate = gate;
	}

	public Gate getGate() {
		return _gate;
	}

	public byte getPinId() {
		return _pinId;
	}

}