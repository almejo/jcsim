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
package cl.alejo.jcsim.window.action;

import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import cl.alejo.jcsim.csim.circuit.Circuit;
import cl.alejo.jcsim.window.Window;

public class ActionNewCircuit extends AbstractWinAction {

	public ActionNewCircuit(String text, String description, ImageIcon icon, KeyStroke stroke, Window window) {
		super(text, description, icon, stroke, window);
	}

	public ActionNewCircuit(String text, String description, ImageIcon icon, Window window) {
		super(text, description, icon, window);
	}

	public ActionNewCircuit(String text, String description, Window window) {
		super(text, description, window);
	}

	public void actionPerformed(ActionEvent e) {
		if (_window.getCircuit() != null && _window.getCircuit().isModified()) {
			int answer = askCircuitIsNotSaved();
			if (answer == JOptionPane.CANCEL_OPTION)
				return;
			if (answer == JOptionPane.YES_OPTION) {
				_window.saveAs();
			}
		}

		_window.setCircuit(new Circuit());
		_window.startRepaint();
	}

	private int askCircuitIsNotSaved() {
		return JOptionPane.showConfirmDialog(_window, "Circuit is not saved. Save it?", "Load...",
			JOptionPane.YES_NO_CANCEL_OPTION);
	}
}