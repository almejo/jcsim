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
import cl.alejo.jcsim.csim.circuit.Point;
import cl.alejo.jcsim.csim.dom.Gate;

public class AssocDescriptor extends IconGateDescriptor {
	// La matriz de comportamiento
	int[][] behavior;

	public AssocDescriptor(ParamAssocGate params) {
		super();

		// Los parametros
		this.params = params;

		// Usamos estos parametros
		int inPins = params.pinCount;

		// Asocio el comportamiento
		this.behavior = params.behavior;

		// Construyo las posicones
		this.pointPin = new Point[inPins + 1];

		// Los de entrada
		int dy = inPins * Circuit.GRIDSIZE / 2;
		for (int i = 0; i < inPins; i++) {
			this.pointPin[i] = new Point(-16, i * Circuit.GRIDSIZE - dy);
		}

		// El pin de salida
		this.pointPin[inPins] = new Point(16, 0);
		this.sGateName = "AssocDesc";
		this.pinCount = inPins + 1;
	}

	/**
	 * Calcula el valor asociativo Creation date: (02/04/01 18:08:08)
	 *
	 * @param val1 int
	 * @param val2 int
	 * @return int
	 */
	public int computeAssocVal(int val1, int val2) {
		return behavior[val1 + 1][val2 + 1];
	}

	public Gate make(Circuit circuit, GateParameters params) {
		return new GateAssoc(circuit, this, (GateParameters) params.clone());
	}
}
