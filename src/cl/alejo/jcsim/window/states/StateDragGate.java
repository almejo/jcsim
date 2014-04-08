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
import java.awt.event.MouseEvent;

import cl.alejo.jcsim.csim.gates.IconGateCompilable;
import cl.alejo.jcsim.window.Window;

public class StateDragGate extends State {
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

		// Solo si hay donde soltarlo
		if (circDrop != null) {

			// Cambio de coordenadas
			pointDrop = getLocalCoords(winDrop, event.getX(), event.getY());

			// DEvuelvo el cursor al original
			window.setNormalCursor();

			// Clono el circuito
			if (_iconTarget != null) {
				// OBtengo el nuevo icono

				// refactorizar
				if (button_modiff == MODIF_SHIFT && _iconTarget instanceof IconGateCompilable) {
					iconClone = ((IconGateCompilable) _iconTarget).compile(circDrop);
				} else
					iconClone = _iconTarget.make(circDrop);

				// Y le pego el nuevo icono si
				// no es un circuito estatico
				if (!circDrop._modificable)
					circDrop.addIconGate(iconClone, pointDrop._x - pointDelta._x, pointDrop._y - pointDelta._y);
			}

			// Si tenia control presionado, borro el original
			if (button_modiff != MODIF_CONTROL && button_modiff != MODIF_SHIFT && circSrc == circDrop
				&& !circSrc._modificable)
				circSrc.delete(_iconTarget);

			// Termino el drag en el circuito
			if (circDrop != null)
				circDrop.endDragGate();
		}
		// Fin

		// Recalculo las barras de scroll
		if (circSrc != null)
			circSrc.refreshScrollBars();
		if (circDrop != null && circDrop != circSrc)
			circDrop.refreshScrollBars();

		// Limpio
		iconClone = null;
		return STATE_INIT;
	}

	/**
	 * Evento invocado cuando el mouse entra a una ventana Creation date:
	 * (31/05/01 17:47:27)
	 * 
	 * @return jcsimwindow.State
	 * @param window
	 *            jcsimwindow.JCSimWindow la ventana que genero el evento
	 * @param event
	 *            java.awt.event.MouseEvent el evento
	 */
	public State mouseEntered(Window window, MouseEvent event) {
		// Primero, la ventana anterior termina su drag
		if (winDrop.getCircuit() != null)
			winDrop.getCircuit().endDragGate();

		// Este es dropTarget
		winDrop = window;
		circDrop = window.getCircuit();

		// ahora comienzo el drag en esta ventana
		if (circDrop != null)
			circDrop.beginDragGate(pointSrc._x, pointSrc._y, _iconTarget);

		window.setDraggingCursor();

		return this;
	}

	/**
	 * Evento invocado cuando el mouse sale de una ventana Creation date:
	 * (31/05/01 17:47:27)
	 * 
	 * @return jcsimwindow.State
	 * @param window
	 *            jcsimwindow.JCSimWindow la ventana que genero el evento
	 * @param event
	 *            java.awt.event.MouseEvent el evento
	 */
	public State mouseExited(Window window, MouseEvent event) {
		winDrop = winSrc;
		circDrop = circSrc;
		window.setNormalCursor();
		return this;
	}

	/**
	 * Mientras estamos haciendo dragging de una compuerta, ya sea clonada o sea
	 * moverla.
	 */
	public State mouseMove(Window window, MouseEvent event) {
		// Cambio de coordenadas
		pointDrop = getLocalCoords(window, event.getX(), event.getY());

		// Actualizo el cambio en el circuito
		if (winDrop.getCircuit() != null)
			winDrop.getCircuit().dragGate(pointDrop._x - pointDelta._x, pointDrop._y - pointDelta._y);

		// listo
		return this;
	}
}
