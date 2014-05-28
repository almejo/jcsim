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
package cl.alejo.jcsim.csim.gates;

import cl.alejo.jcsim.csim.circuit.Circuit;
import cl.alejo.jcsim.csim.dom.ConfigurableGate;
import cl.alejo.jcsim.csim.dom.Pin;

public class Clk extends ConfigurableGate {
	public Clk(Circuit circ, GateDescriptor gatedesc, GateParameters params) {

		// REscato el descriptor
		this._gateDescriptor = gatedesc;

		// Creo el arreglo de pines
		_pin = new Pin[pinCount()];

		// Asocio el circuito
		this._circuit = circ;

		// Los parametros
		this._parameters = params;

		// Y creo los pines
		_pin[0] = new ClkPin(this, circ, (byte) 0);
		_pin[0].hasChanged();
	}

	public void apply(int x, int y) {

		// REcupero los valores
		int delayUp = ((ParamClk) _parameters).delayUp;
		int delayDown = ((ParamClk) _parameters).delayDown;

		// Lleno el arreglo y lo mando
		String[] txtparams = {"up period", "down period"};
		String[] valparams = {"" + delayUp, "" + delayDown};
		config(txtparams, valparams);
	}

	public void clean() {

		// Limpiando
		System.out.println("Limpiando CLK");
		((ClkPin) _pin[0]).clkEvent.cancel();
	}

	public void fillParams(String[] params) {

		// Recupero los delay
		int newdelayUp = (int) Double.parseDouble(params[0]);
		int newdelayDown = (int) Double.parseDouble(params[1]);

		// Solo tiempos de retado mayores que 1
		if (newdelayUp >= 1)
			((ParamClk) this._parameters).delayUp = newdelayUp;
		// Solo tiempos de retado mayores que 1
		if (newdelayDown >= 1)
			((ParamClk) this._parameters).delayDown = newdelayDown;
	}
}