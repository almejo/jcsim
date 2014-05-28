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

public abstract class AbstractWinAction extends AbstractAction {

	Window _window;

	public AbstractWinAction() {
	}

	public AbstractWinAction(String text, String description, Window window) {
		super(text);
		putValue(AbstractAction.SHORT_DESCRIPTION, description);
		this._window = window;
	}

	public AbstractWinAction(String text, String description, ImageIcon icon, Window window) {
		super(text, icon);
		putValue(AbstractAction.SHORT_DESCRIPTION, description);
		this._window = window;
	}

	public AbstractWinAction(String text, String description, ImageIcon icon, KeyStroke stroke, Window window) {

		this(text, description, icon, window);
		putValue(AbstractAction.SHORT_DESCRIPTION, description);
		putValue(AbstractAction.ACCELERATOR_KEY, stroke);
	}
}
