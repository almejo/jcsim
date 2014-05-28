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

public class StateDragSelection extends State {
	/**
	 * Mientras estamos haciendo dragging de una compuerta, ya sea clonada o sea
	 * moverla.
	 */
	public State drag(Window window, MouseEvent event) {
		return mouseMove(window, event);
	}

	/**
	 * Cuando dejamos de hacer drag, volvemos al inicio
	 */
	public State mouseClick(Window window, MouseEvent event) {

		winSrc.setNormalCursor();
		return STATE_INIT;
	}

	/**
	 * Evento invocado cuando el mouse entra a una ventana Creation date:
	 * (31/05/01 17:47:27)
	 *
	 * @param window jcsimwindow.JCSimWindow la ventana que genero el evento
	 * @param event  java.awt.event.MouseEvent el evento
	 * @return jcsimwindow.State
	 */
	public State mouseEntered(Window window, MouseEvent event) {
		// // Primero, la ventana anterior termina su drag
		// if (winDrop.getCircuit() != null)
		// winDrop.getCircuit().endDragGate();
		//
		// // Este es dropTarget
		// winDrop = window;
		// circDrop = window.getCircuit();
		//
		// // ahora comienzo el drag en esta ventana
		// if (circDrop != null)
		// circDrop.beginDragGate(pointSrc._x, pointSrc._y, iconTarget);
		//
		// // Le pongo el cursor adecuado
		// window.setDraggingCursor();
		//
		// // Listo
		return this;
	}

	/**
	 * Evento invocado cuando el mouse sale de una ventana Creation date:
	 * (31/05/01 17:47:27)
	 *
	 * @param window jcsimwindow.JCSimWindow la ventana que genero el evento
	 * @param event  java.awt.event.MouseEvent el evento
	 * @return jcsimwindow.State
	 */
	public State mouseExited(Window window, MouseEvent event) {
		// // Este es dropTarget
		// winDrop = winSrc;
		// circDrop = circSrc;
		//
		// // Le cambio el cursor
		// window.setNormalCursor();
		//
		// // Listo
		return this;
	}

	/**
	 * Mientras estamos haciendo dragging de una seleccion.
	 */
	public State mouseMove(Window window, MouseEvent event) {
		// Cambio de coordenadas
		pointDrop = getLocalCoords(window, event.getX(), event.getY());

		// Actualizo el cambio en el circuito
		if (winDrop.getCircuit() != null) {
			winDrop.getCircuit().dragSelection(pointDrop._x, pointDrop._y);
		}
		return this;
	}
}
