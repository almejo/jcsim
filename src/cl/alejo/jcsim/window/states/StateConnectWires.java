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

import cl.alejo.jcsim.window.Window;

public class StateConnectWires extends State {
	/**
	 * drag method comment.
	 */
	public State drag(Window window, MouseEvent event) {

		// Busco el punto donde estoy
		pointDrop = getLocalCoords(winDrop, event.getX(), event.getY());

		// Si el circuito no es el mismo, todo se hace en coordenadas
		// de la venta fuente.
		if (circDrop != circSrc)
			pointDrop = getCoords(winSrc, winSrc, event.getX(), event.getY());

		// Actualizo el cambio en el circuito
		if (winSrc.getCircuit() != null)
			winSrc.getCircuit().dragConnection(pointDrop._x, pointDrop._y);

		// Listo
		return this;
	}

	/**
	 * mouseUp method comment.
	 */
	public State mouseClick(Window window, MouseEvent event) {
		pointDrop = getCoords(winSrc, winDrop, event.getX(), event.getY());

		// Si el circuito no es el mismo, todo se hace en coordenadas
		// de la venta fuente.
		if (circDrop != circSrc)
			pointDrop = getCoords(winSrc, winSrc, event.getX(), event.getY());

		// conectamos
		circSrc.endConnect();

		// Recalculo las barras de scroll
		circSrc.refreshScrollBars();

		// Y Ahora comienzo la proxima coneccion
		circSrc.beginConnect(pointDrop._x, pointDrop._y);

		// fin
		return this;
	}

	/**
	 * Insert the method's description here. Creation date: (12/06/01 14:49:41)
	 * 
	 * @param window
	 *            jcsimwindow.JCSimWindow
	 * @param event
	 *            java.awt.event.MouseEvent
	 */
	public State mouseDoubleClick(Window window, MouseEvent event) {
		pointDrop = getCoords(winSrc, winDrop, event.getX(), event.getY());

		// Si el circuito no es el mismo, todo se hace en coordenadas
		// de la venta fuente.
		if (circDrop != circSrc)
			pointDrop = getCoords(winSrc, winSrc, event.getX(), event.getY());

		// conectamos
		circSrc.endConnect();

		// Recalculo las barras de scroll
		circSrc.refreshScrollBars();

		// El cursor
		winSrc.setNormalCursor();
		winDrop.setNormalCursor();

		// fin
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

		// Este es dropTarget
		winDrop = window;

		// Si hay un circuito
		if (window.getCircuit() != null)
			circDrop = window.getCircuit();

		// Listo
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
		// Ponemos el dropTarget en el fuente
		winDrop = winSrc;
		circDrop = circSrc;

		// Y devolvemos
		return this;
	}

	/**
	 * drag method comment.
	 */
	public State mouseMove(Window window, MouseEvent event) {

		// Busco el punto donde estoy
		pointDrop = getLocalCoords(winDrop, event.getX(), event.getY());

		// Si el circuito no es el mismo, todo se hace en coordenadas
		// de la venta fuente.
		if (circDrop != circSrc)
			pointDrop = getCoords(winSrc, winSrc, event.getX(), event.getY());

		// Actualizo el cambio en el circuito
		if (winSrc.getCircuit() != null)
			winSrc.getCircuit().dragConnection(pointDrop._x, pointDrop._y);

		// Listo
		return this;
	}

	/**
	 * mouseUp method comment.
	 */
	public State mouseUp(Window window, MouseEvent event) {
		/*
		 * pointDrop = getLocalCoords(winDrop, event.getX(), event.getY());
		 * 
		 * // Si el circuito no es el mismo, todo se hace en coordenadas // de
		 * la venta fuente. if (circDrop != circSrc) pointDrop =
		 * getLocalCoords(winSrc, event.getX(), event.getY());
		 * 
		 * // conectamos circSrc.endConnect(pointDrop.x, pointDrop.y);
		 * 
		 * // Recalculo las barras de scroll circSrc.refreshScrollBars();
		 * 
		 * // El cursor winSrc.setNormalCursor();
		 * 
		 * // fin return stateInit;
		 */
		return this;
	}
}
