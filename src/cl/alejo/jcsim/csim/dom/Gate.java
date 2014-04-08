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
package cl.alejo.jcsim.csim.dom;

import java.io.Serializable;
import java.util.List;

import cl.alejo.jcsim.csim.circuit.Box;
import cl.alejo.jcsim.csim.circuit.Circuit;
import cl.alejo.jcsim.csim.gates.GateDescriptor;
import cl.alejo.jcsim.csim.gates.GateParameters;

abstract public class Gate implements Serializable, Cloneable {

	protected Pin _pin[];
	protected GateDescriptor _gateDescriptor;
	protected Circuit _circuit;
	protected GateParameters _parameters;

	public void clean() {
	}

	/**
	 * Este metodo solo sirve para que lo ejecute la compuerta template... ella
	 * es la unica que lo implementa
	 * 
	 * @param Circuit
	 *            circuit El circuito donde debe buscar
	 * @param Box
	 *            box
	 */
	public Gate compile(Circuit circuit, Box box) {
		circuit.getClass();
		box.getClass();
		return null;
	}

	/**
	 * El metodo que se invocara cuando uno hace "doble click" en una compuerta
	 * este metodo se usa para invocar acciones sobre la compuerta
	 * 
	 * Recibe la posicion donde ocurre el evento realtivo a la compuerta
	 * 
	 * @param int x La posicion horizontal del evento
	 * @param int y La posicion vertical del evento
	 */
	public abstract void apply(int x, int y);

	public Pin getPin(int pinId) {
		return _pin[pinId];
	}

	public void getPinsAt(int pinId, List listPin) {
		Pin pin = getPin(pinId);

		if (!listPin.contains(pin))
			listPin.add(pin);
	}

	/**
	 * Entrega true si esta compuerta es una compuerta normal
	 */
	public boolean isNormal() {
		return _gateDescriptor.typeOfGate == cl.alejo.jcsim.csim.gates.GateDescriptor.GATE_TYPE_NORMAL;
	}

	/**
	 * Entrega la cantidad de pines que contiene el descriptor de la compuerta
	 */
	public int pinCount() {
		if (_gateDescriptor != null)
			return _gateDescriptor.pinCount();
		return 0;
	}

	/**
	 * Le asocia un nuevo circuito a esta compuerta y luego asocia este circuito
	 * a los pines del Gate
	 * 
	 * @param circuit
	 *            El nuevo circuito del
	 */
	public void actualizeCircuit(Circuit circuit) {
		_circuit = circuit;
		actualizePins(circuit);
	}

	private void actualizePins(Circuit circuit) {
		for (int i = 0; i < pinCount(); i++)
			_pin[i].setCircuit(circuit);

		for (int i = 0; i < pinCount(); i++)
			_pin[i].hasChanged();
	}

	public Circuit getCircuit() {
		return _circuit;
	}

	public GateDescriptor getGateDescriptor() {
		return _gateDescriptor;
	}

	public GateParameters getParameters() {
		return _parameters;
	}

	public Pin[] getPin() {
		return _pin;
	}

	public void setParameters(GateParameters parameters) {
		_parameters = parameters;
	}

	public void setPin(Pin[] pins) {
		_pin = pins;
	}

	public void setGateDescriptor(GateDescriptor descriptor) {
		_gateDescriptor = descriptor;
	}

	public void setCircuit(Circuit circuit) {
		_circuit = circuit;
	}
}
