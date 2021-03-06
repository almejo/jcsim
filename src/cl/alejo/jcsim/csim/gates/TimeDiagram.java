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

public class TimeDiagram extends Gate {
	// La ventana del diagrama de tiempo
	transient DiagramFrame df;

	public TimeDiagram(Circuit circ, GateDescriptor gatedesc, GateParameters params) {
		this._gateDescriptor = gatedesc;
		this._circuit = circ;
		this._parameters = params;
		_pin = new Pin[pinCount()];
		for (int i = 0; i < pinCount(); i++)
			_pin[i] = new Pin(this, circ, (byte) i);
	}

	/**
	 * Insert the method's description here. Creation date: (16/04/01 18:36:33)
	 * 
	 * @param x
	 *            int
	 * @param y
	 *            int
	 */
	public void apply(int x, int y) {
		// si no existe, la creo
		if (df == null) {
			df = new DiagramFrame(this._circuit.getAgenda());
			df.add("1", _pin[0]);
			df.add("2", _pin[1]);
			df.add("3", _pin[2]);
			df.add("4", _pin[3]);
			df.pack();
			df.show();
		} else {
			df.show();
			df.start();
		}
	}

	/**
	 * Limpiamos un poco Creation date: (16/05/01 15:40:36)
	 */
	public void clean() {
		// El frame del diagrama
		if (df != null)
			this.df.end();
	}

	/**
	 * Insert the method's description here. Creation date: (24/04/01 10:40:48)
	 * 
	 * @return java.lang.Object
	 */
	public void config(String[] params) {
	}
}
