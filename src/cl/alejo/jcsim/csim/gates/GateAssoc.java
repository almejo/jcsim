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
import cl.alejo.jcsim.csim.dom.ConfigurableGate;

public class GateAssoc extends ConfigurableGate {
	/**
	 * Insert the method's description here. Creation date: (02/04/01 18:25:12)
	 * 
	 * @param delay
	 *            int
	 * @param circuit
	 *            circuit.Circuit
	 * @param gateDesc
	 *            gates.GateDescriptor
	 */
	public GateAssoc(Circuit circuit, GateDescriptor gateDesc, GateParameters params) {
		// Los parametros
		this._parameters = params;
		this._circuit = circuit; // El circuito
		this._gateDescriptor = gateDesc; // El descriptor

		// Creamos los pines
		_pin = new AssocPin[pinCount()];
		for (int i = 0; i < pinCount(); i++)
			_pin[i] = new AssocPin(this, circuit, (byte) i);

		// Hacemos que uno de los pines cambie
		_pin[0].hasChanged();
	}

	/**
	 * Insert the method's description here. Creation date: (16/04/01 19:00:50)
	 * 
	 * @param x
	 *            int
	 * @param y
	 *            int
	 */
	public void apply(int x, int y) {
		String[] txtparams = { "tiempo de retardo" };
		String[] valparams = { "" + ((ParamAssocGate) _parameters).delay };
		config(txtparams, valparams);
	}

	/**
	 * Insert the method's description here. Creation date: (16/04/01 19:00:50)
	 * 
	 * @param x
	 *            int
	 * @param y
	 *            int
	 */
	public void fillParams(String[] txtParams) {

		// Recupero el tiempo de retardo
		int newdelay = (int) Double.parseDouble(txtParams[0]);

		// Solo tiempos de retado mayores que 1
		if (newdelay >= 1)
			((ParamAssocGate) _parameters).delay = newdelay;
	}
}