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
 * La clase de las compuertas que se pueden configurar con una ventana de
 * propiedades
 */
package cl.alejo.jcsim.csim.dom;

import cl.alejo.jcsim.window.SetupWindow;

public abstract class ConfigurableGate extends Gate {
	protected transient SetupWindow _setupWindow;

	public void config(String[] txtparams, String[] valparams) {
		if (_setupWindow == null) {
			_setupWindow = new SetupWindow(this, "Clock config", txtparams, valparams);
			_setupWindow.pack();
		}

		_setupWindow.show();

	}

	/**
	 * Debe ser implementado para que se llene la ventana de propiedades
	 */
	public abstract void fillParams(String[] params);
}