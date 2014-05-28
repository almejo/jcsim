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
import cl.alejo.jcsim.window.CircuitCanvas;
import cl.alejo.jcsim.window.Window;

import javax.swing.*;
import java.awt.event.MouseEvent;

public class StateInit extends State {

	public State drag(Window window, MouseEvent event) {

		if (circSrc != null && _selectionTarget != null) {
			System.out.println("" + pointSrc._x + ", " + pointSrc._y + " la seleccion: " + _selectionTarget);
			circSrc.beginDragSelection(pointSrc._x, pointSrc._y, _selectionTarget);
			return STATE_DRAG_SELECTION;
		}

		if ((isContact || _iconTarget == null) && circSrc != null && button_modiff == MODIF_SHIFT) {
			circSrc.beginConnect(pointSrc._x, pointSrc._y);
			return STATE_CONNECT;
		}

		if (!isContact && _iconTarget != null) {
			window.getCircuit().beginDragGate(pointSrc._x, pointSrc._y, _iconTarget);
			return STATE_DRAG_GATE;
		}

		// si no hay un gate y no hay un contacto
		if (_iconTarget == null && button_modiff == MODIF_CONTROL) {
			window.getCircuit().beginDragViewport(pointSrc._x, pointSrc._y);
			return STATE_DRAG_VIEWPORT;
		}

		if (_iconTarget == null) {
			window.getCircuit().beginSelect(pointSrc._x, pointSrc._y);
			return STATE_SELECT;
		}
		return this;
	}

	public State mouseDoubleClick(Window window, MouseEvent event) {

		// Recupero el canvas
		CircuitCanvas canvas = window.getCanvas();

		// El click
		if (window.getCircuit() != null) {
			// Donde ocurre
			int x = canvas.getTransformedX(event.getX());
			int y = canvas.getTransformedY(event.getY());
			Circuit circuit = window.getCircuit();

			// Si es el boton izquierdo
			if (SwingUtilities.isLeftMouseButton(event)) {
				if (circuit.peekV(x, y) || circuit.peekH(x, y)) {
					circuit.disconnect(x, y);
				} else {
					if (_iconTarget != null) {
						if (button_modiff == MODIF_ALT)
							circuit.delete(_iconTarget);
						else if (button_modiff == MODIF_SHIFT)
							_iconTarget.rotate(x, y);
						else
							circuit.apply(x, y);
					}
				}
			}

			// Recalculamos las barras cuando hemos hecho un
			// doble click, en caso de haber borrado algo.
			window.refreshScrollbars();
		}
		return this;
	}

	/**
	 * Cuando presiono el boton del mouse, me quedo aqui adecuado.
	 */
	public State mouseDown(Window window, MouseEvent event) {

		// Solo si hay un circuito
		if (window.getCircuit() != null) {
			// *********************************
			// Llenamos los parametros iniciles
			// *********************************

			// Circuitos y puntos de draganddrop
			circDrop = circSrc = window.getCircuit();
			winDrop = State.winSrc = window;
			pointSrc = getSrcPoint(window, event);

			// estado del teclado
			button_modiff = getModif(event);

			// Icono en ese punto
			_iconTarget = circSrc.findIcon(pointSrc._x, pointSrc._y);
			window.setIconTarget(_iconTarget);

			_selectionTarget = circSrc.findSelection(pointSrc._x, pointSrc._y);
			window.setSelectionTarget(_selectionTarget);

			// Si existen cables
			isContact = circSrc.peek(pointSrc._x, pointSrc._y);

			// LA distancia del punto al centro
			if (_iconTarget != null) {
				pointDelta = _iconTarget.toGateCoords(pointSrc._x, pointSrc._y);
				java.awt.geom.Point2D.Double p2d = new java.awt.geom.Point2D.Double(pointDelta._x, pointDelta._y);
				pointDelta = _iconTarget.rotatedPointInv((int) p2d.getX(), (int) p2d.getY());
			}

			// Y cambio el icono
			switch (button_modiff) {
				case MODIF_CONTROL:
					if (window.getIconTarget() != null)
						window.setCloneCursor();
					break;
				case MODIF_SHIFT:
					window.setCloseDeleteCursor();
					break;
				default:
					if (window.getIconTarget() != null)
						window.setMoveCursor();
					else
						window.setConnectCursor();
					break;
			}
		}
		return this;
	}

	/**
	 * Cuando levantamos el mouse, nos vamos al estado inicial
	 */
	public State mouseUp(Window window, MouseEvent event) {
		window.setNormalCursor();
		window.getCircuit().cleanSelection();
		return this;
	}
}
