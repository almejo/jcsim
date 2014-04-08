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
import cl.alejo.jcsim.csim.circuit.Circuit;
import cl.alejo.jcsim.csim.dom.Gate;
import cl.alejo.jcsim.csim.dom.Pin;

public class AssocPin extends Pin {
	/**
	 * El constructor... nada de interesante
	 */
	public AssocPin(Gate gate, Circuit circuit, byte pinId) {
		super(gate, circuit, pinId);
	}

	/**
	 * Se ejecuta cuando un pin ha cambiado de valor Creation date: (02/04/01
	 * 18:03:49)
	 */
	public void hasChanged() {

		// Recuperamos el arreglo de pines
		Pin[] pin = _gate.getPin();
		int pincount = _gate.pinCount();
		AssocDescriptor gateDesc = (AssocDescriptor) _gate.getGateDescriptor();

		// Tomamos el primer valor
		int val = pin[0].getInValue();

		// Y calculamos el resto
		for (int i = 1; i < pincount - 1; i++)
			val = gateDesc.computeAssocVal(val, pin[i].getInValue());

		// Programamos la salida
		pin[pincount - 1].programOut((byte) val, ((ParamAssocGate) _gate.getParameters()).delay);
	}
}
