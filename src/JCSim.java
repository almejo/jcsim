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
import cl.alejo.jcsim.window.Window;

public class JCSim {
	public static void main(String[] args) throws Exception {
		Window window = new Window(Circuit.load("menu.cir"));
		window.init();
		window.pack();
		window.show();
		window.startRepaint();
	}
}