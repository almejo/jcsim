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
import cl.alejo.jcsim.csim.dom.Gate;
import cl.alejo.jcsim.csim.dom.Pin;

public class ClkPin extends Pin implements Cloneable {

	// Un evento de reloj
	ClkEvent clkEvent;

	ClkPin(Gate gate, Circuit circuit, byte pinId) {
		// Creo todo lo anterior
		super(gate, circuit, pinId);

		// Recupero el valor del delay
		int delayUp = ((ParamClk) gate.getParameters()).delayUp;

		// EL evento de reloj
		clkEvent = new ClkEvent(gate, circuit.getAgenda());

		// Y lo hechamos a andar
		clkEvent.program(delayUp);
	}

	public void hasChanged() {
		// Programamos el cambio para ahora
		programOut(((ParamClk) _gate.getParameters()).state, 0);
	}

	public void setCircuit(Circuit circuit) {

		// Ponemos el circuito
		super.setCircuit(circuit);

		// Arreglamos este evento de clk
		if (clkEvent != null)
			clkEvent.cancel();

		// ahora seteamos el circuito del pin
		if (circuit != null) {
			// Creamos un nuevo evento
			clkEvent = new ClkEvent(this._gate, circuit.getAgenda());

			// Le asignamos el gate
			clkEvent.clk = (Clk) this._gate;

			// programamos el evento del reloj
			clkEvent.program(0);

			// Y lo programamos
			hasChanged();
		}
	}
}