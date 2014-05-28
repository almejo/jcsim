package cl.alejo.jcsim.window.states;

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

import cl.alejo.jcsim.window.Window;

import java.awt.event.MouseEvent;

public class StateSelect extends State {

	public State drag(Window window, MouseEvent event) {
		int x = window.getCanvas().getTransformedX(event.getX());
		int y = window.getCanvas().getTransformedY(event.getY());
		window.getCircuit().dragSelect(x, y);
		return this;
	}

	public State mouseUp(Window window, MouseEvent event) {
		window.getCircuit().endSelect();
		window.refreshScrollbars();
		window.setNormalCursor();

		return STATE_INIT;
	}
}
