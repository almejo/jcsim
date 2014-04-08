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

	/**
	 * AssocDescriptor constructor comment.
	 */
	public AssocDescriptor(ParamAssocGate params) {
		super();

		// Los parametros
		this.params = params;

		// Usamos estos parametros
		int inPins = ((ParamAssocGate) params).pinCount;

		// Asocio el comportamiento
		this.behavior = ((ParamAssocGate) params).behavior;

		// Construyo las posicones
		this.pointPin = new Point[inPins + 1];

		// Los de entrada
		int dy = (int) (inPins * Circuit.GRIDSIZE / 2);
		for (int i = 0; i < inPins; i++)
			this.pointPin[i] = new Point(-16, i * Circuit.GRIDSIZE - dy);

		// El pin de salida
		this.pointPin[inPins] = new Point(16, 0);
		this.sGateName = "AssocDesc";
		this.pinCount = inPins + 1;
	}

	/**
	 * Calcula el valor asociativo Creation date: (02/04/01 18:08:08)
	 * 
	 * @return int
	 * @param val1
	 *            int
	 * @param val2
	 *            int
	 */
	public int computeAssocVal(int val1, int val2) {
		return behavior[val1 + 1][val2 + 1];
	}

	/**
	 * Insert the method's description here. Creation date: (26/06/01 21:27:43)
	 * 
	 * @return csim.Gate
	 * @param params
	 *            gates.GateParameters
	 */
	public Gate make(Circuit circuit, GateParameters params) {

		// Creamos un nuevo gate
		return new GateAssoc(circuit, (GateDescriptor) this, (GateParameters) params.clone());
	}
}
