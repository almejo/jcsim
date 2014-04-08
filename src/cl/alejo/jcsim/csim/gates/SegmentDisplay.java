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

public class SegmentDisplay extends Gate {
	public SegmentDisplay(Circuit circ, GateDescriptor gateDesc, GateParameters params) {
		// Los parametros
		this._gateDescriptor = gateDesc;
		this._parameters = params;
		this._circuit = circ;

		// Creo los pines

		_pin = new Pin[pinCount()];
		for (int i = 0; i < pinCount(); i++)
			_pin[i] = new SegmentDisplayPin(this, circ, (byte) i);
	}

	/**
	 * Se ejecuta cuando se doble clickea una compuerta. Creation date:
	 * (16/04/01 18:36:33)
	 * 
	 * @param x
	 *            int
	 * @param y
	 *            int
	 */
	public void apply(int x, int y) {
	}
}
