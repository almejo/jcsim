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

import cl.alejo.jcsim.window.Window;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ActionCopy extends AbstractWinAction {

	public ActionCopy(String text, String description, ImageIcon icon, KeyStroke stroke, Window window) {
		super(text, description, icon, stroke, window);
	}

	public ActionCopy(String text, String description, ImageIcon icon, Window window) {
		super(text, description, icon, window);
	}

	public ActionCopy(String text, String description, Window window) {
		super(text, description, window);
	}

	public void actionPerformed(ActionEvent e) {
		_window.getCircuit().copy();
	}
}