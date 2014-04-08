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
import java.io.Serializable;

public class EncapsulatedGateData implements Serializable {

	// EL gate
	GateDescriptor gateDesc;

	// Los parametros para un gate
	GateParameters params;

	// El indice en la tabla de Gates y Pines
	int[][] connectTo;

	/**
	 * EncapsulatedGateData constructor comment.
	 */
	public EncapsulatedGateData(EncapsulatedGateData gateData) {
		this.gateDesc = gateData.gateDesc;
		this.params = gateData.params;
		this.connectTo = gateData.connectTo;
	}

	/**
	 * EncapsulatedGateData constructor comment.
	 */
	public EncapsulatedGateData(GateDescriptor gateDesc, GateParameters params, int[][] connectTo) {
		this.gateDesc = gateDesc;
		this.params = params;
		this.connectTo = connectTo;
	}
}
