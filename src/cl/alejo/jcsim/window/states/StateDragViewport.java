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

public class StateDragViewport extends State {
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
			cl.alejo.jcsim.csim.circuit.Point pointDesp = new cl.alejo.jcsim.csim.circuit.Point(-pointDrop._x
				+ pointSrc._x, -pointDrop._y + pointSrc._y);
			cl.alejo.jcsim.csim.circuit.Point pointCorner = new cl.alejo.jcsim.csim.circuit.Point(pointDesp._x
				+ winDrop.getCanvas().boxViewport.getXi(), pointDesp._y + winDrop.getCanvas().boxViewport.getYi());
			// circuit.Point point = new circuit.Point(pointDrop.x -
			// winDrop.canvas.boxViewport.xi, pointDrop.y -
			// winDrop.canvas.boxViewport.yi);
			// circuit.Point pointCorner = new circuit.Point(pointSrc.x -
			// point.x, pointSrc.y - point.y);
			System.out.println("PointSrc= " + pointSrc);
			System.out.println("PointDrop = " + pointDrop);
			System.out.println("Corner = " + winDrop.getCanvas().boxViewport.getXi() + ","
				+ winDrop.getCanvas().boxViewport.getYi());
			System.out.println("PointDesp = " + pointDesp);
			System.out.println("PointCorner= " + pointCorner);

			// Cambio el viewport
			winDrop.getCanvas().setViewportCorner(pointCorner._x, pointCorner._y);

			// TErmino el dragging
			circDrop.endDragViewport();

			// REfresco las barras
			winDrop.refreshScrollbars();
		}
		// Fin
		window.setNormalCursor();
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
		/*
		 * // Primero, la ventana anterior termina su drag if (winDrop.circuit
		 * != null) winDrop.circuit.endDragGate();
		 * 
		 * // Este es dropTarget winDrop = window; circDrop = window.circuit;
		 * 
		 * // ahora comienzo el drag en esta ventana if (circDrop != null)
		 * circDrop.beginDragGate(pointSrc.x, pointSrc.y, iconTarget);
		 * 
		 * // Le pongo el cursor adecuado window.setDraggingCursor();
		 * 
		 * // Listo
		 */
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
		/*
		 * // Este es dropTarget winDrop = winSrc; circDrop = circSrc;
		 * 
		 * // Le cambio el cursor window.setNormalCursor();
		 * 
		 * // Listo
		 */
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
			winDrop.getCircuit().dragViewport(pointDrop._x, pointDrop._y);

		// listo
		return this;
	}
}
