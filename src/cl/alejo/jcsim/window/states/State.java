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

import cl.alejo.jcsim.csim.circuit.Circuit;
import cl.alejo.jcsim.csim.circuit.Point;
import cl.alejo.jcsim.csim.circuit.SelectionContainer;
import cl.alejo.jcsim.csim.gates.IconGate;
import cl.alejo.jcsim.window.Window;

import java.awt.event.MouseEvent;

public abstract class State {

	public final static State STATE_INIT = new StateInit();
	protected final static State STATE_CONNECT = new StateConnectWires();
	protected final static State STATE_SELECT = new StateSelect();
	protected final static State STATE_DRAG_GATE = new StateDragGate();
	protected final static State STATE_DRAG_VIEWPORT = new StateDragViewport();
	protected final static State STATE_DRAG_SELECTION = new StateDragSelection();

	protected static cl.alejo.jcsim.csim.circuit.Point pointSrc = null; // El
	// inicio
	// del
	// dnd
	protected static cl.alejo.jcsim.csim.circuit.Point pointDrop = null; // El
	// final
	// del
	// dnd
	protected static int button_modiff = 0; // El modificador
	protected static Circuit circSrc = null; // El circuito fuente
	protected static Circuit circDrop = null; // El circuito del drop
	protected static Window winDrop = null;

	protected static Window winSrc = null;
	protected static IconGate _iconTarget = null;
	protected static IconGate iconClone = null;
	protected static boolean isContact = false;

	protected static cl.alejo.jcsim.csim.circuit.Point pointDelta = null;

	protected final static int MODIF_NORMAL = 0;
	protected final static int MODIF_ALT = 3;
	protected final static int MODIF_SHIFT = 1;
	protected final static int MODIF_CONTROL = 2;

	/**
	 * Al hacer drag en este estado Creation date: (03/04/01 16:40:39)
	 *
	 * @return jcsimwindow.State
	 */
	public State drag(Window window, MouseEvent event) {
		return this;
	}

	/**
	 * Convierte coordenadas de la ventana fuente a coordenadas de del circuito
	 * en la ventana destino Creation date: (01/06/01 0:12:16)
	 *
	 * @param winSrc  jcsimwindow.JCSimWindow ventana fuente
	 * @param winDrop jcsimwindow.JCSimWindow ventana destino
	 * @return circuit.Point
	 */
	public Point getCoords(Window winSrc, Window winDrop, int _x, int _y) {

		// Primero, obtenemos las posiciones de las ventanas
		// c/r a la pantalla
		java.awt.Point pointSrc = winSrc.getLocation();
		java.awt.Point pointDrop = winDrop.getLocation();

		// Obtenemos el punto en relacion a la pantalla
		int x = _x + pointDrop.x;
		int y = _y + pointDrop.y;

		// Ahora lo convierto a coordenadas de la ventana destino
		x = x - pointSrc.x;
		y = y - pointSrc.y;

		// y ahora lo convierto a coordenas de
		x = winSrc.getCanvas().getTransformedX(x);
		y = winSrc.getCanvas().getTransformedY(y);

		// Listo
		return new Point(x, y);
	}

	/**
	 * Convierte coordenadas de la ventana fuente a coordenadas de del circuito
	 * en la ventana destino Creation date: (01/06/01 0:12:16)
	 *
	 * @param window jcsimwindow.JCSimWindow
	 * @param x      int
	 * @param y      int
	 * @return circuit.Point
	 */
	public Point getLocalCoords(Window window, int x, int y) {

		// y ahora lo convierto a coordenas de
		int _x = Circuit.gridTrunc(window.getCanvas().getTransformedX(x));
		int _y = Circuit.gridTrunc(window.getCanvas().getTransformedY(y));

		// Listo
		return new Point(_x, _y);
	}

	/**
	 * DEvuelve el modificador del mouse Creation date: (31/05/01 20:47:00)
	 *
	 * @param event java.awt.event.MouseEvent
	 * @return int
	 */
	public int getModif(MouseEvent event) {
		if (event.isControlDown())
			return MODIF_CONTROL;
		else if (event.isShiftDown())
			return MODIF_SHIFT;
		else if (event.isAltDown())
			return MODIF_ALT;
		return MODIF_NORMAL;
	}

	/**
	 * DEvuelve el punto donde comienza el drag and drop en coordenadas del
	 * circuito Creation date: (31/05/01 20:44:30)
	 *
	 * @param window jcsimwindow.JCSimWindow
	 * @param event  java.awt.event.MouseEvent
	 * @return circuit.Point
	 */
	public Point getSrcPoint(Window window, MouseEvent event) {
		int x = cl.alejo.jcsim.csim.circuit.Circuit.gridTrunc(window.getCanvas().getTransformedX(event.getX()));
		int y = cl.alejo.jcsim.csim.circuit.Circuit.gridTrunc(window.getCanvas().getTransformedY(event.getY()));

		// Devuelvo el nuevo punto
		return new Point(x, y);
	}

	public State mouseClick(Window window, MouseEvent event) {
		return this;
	}

	public State mouseDoubleClick(Window window, MouseEvent event) {
		return this;
	}

	/**
	 * Evento cuando se suelta el mouse Creation date: (05/04/01 17:32:43)
	 *
	 * @param window jcsimwindow.JCSimWindow
	 * @param event  java.awt.event.MouseEvent
	 */
	public State mouseDown(Window window, MouseEvent event) {
		return this;
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
		return this;
	}

	/**
	 * Mientras estamos moviendo el mouse
	 */
	public State mouseMove(Window window, MouseEvent event) {
		return this;
	}

	public State mouseUp(Window window, MouseEvent event) {
		return this;
	}

	protected SelectionContainer _selectionTarget;
}
