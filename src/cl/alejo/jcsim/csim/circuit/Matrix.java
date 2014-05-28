package cl.alejo.jcsim.csim.circuit;

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
 * Clase que simula una matriz poco densa, la cual se llenara de puntos, y para
 * esto utiliza dos listas enlazadas, cada una ordenada segun un eje de la
 * matriz. El orden es necesario para ejecutar una busqueda eficiente sobre
 * ellas, durante las operaciones mas frecuentes
 *
 * @author: Alejandro Vera
 */

import java.util.*;

public class Matrix implements java.io.Serializable {

	private Box _extentBox = new Box();

	private Point _previous;

	private Point _next;

	private Point _hit;

	public static final int EXIST = 0;

	public static final int NOTHING = 4;

	public static final int BETWEEN = 1;

	public static final int MAX = 2;

	public static final int MIN = 3;

	private List _verticalPointList = new ArrayList();

	private List _horizontalPointList = new ArrayList();

	private Comparator _comparatorXY = new ComparatorXY();

	private Comparator _comparatorYX = new ComparatorYX();

	/**
	 * Agregamos un punto punto a las listas, luego de agregarlo, es necesario
	 * saber si existia.
	 *
	 * @param point circuit.Point el punto a insertar en la matriz
	 */
	public void add(Point point) {
		int ndx = Collections.binarySearch(_verticalPointList, point, _comparatorXY);
		if (ndx >= 0)
			return;
		int ins = -ndx - 1;
		_verticalPointList.add(ins, point);
		ndx = Collections.binarySearch(_horizontalPointList, point, _comparatorYX);
		ins = -ndx - 1;
		_horizontalPointList.add(ins, point);
	}

	/**
	 * El metodo que devuelve la extension del circuito
	 *
	 * @return csimgui.Box la caja que contiene el "Extent"
	 */
	public Box extend() {
		_extentBox.setEmpty();
		for (Iterator iter = _verticalPointList.iterator(); iter.hasNext(); ) {
			Point p = (Point) iter.next();
			_extentBox.extend(p._x, p._y);
		}
		return _extentBox;
	}

	/**
	 * Verifica si existe un punto en la coordenada (x,y) y devuelve la
	 * condicion de este punto: EXIST si existe un punto NOTHING no habia nada
	 * BETWEEN esta entre otros dos MAX es el mayor de la lista MIN es el menor
	 * de la lista
	 * <p/>
	 * La busqueda es hecha sobre la lista que guarda los puntos hordenados
	 * horizontalmente
	 *
	 * @param x int La coordenada x
	 * @param y int La coordenada y
	 * @return int La condicion del punto
	 */
	// TODO: esta feo
	public int findHorizontal(int x, int y) {
		int ndx = Collections.binarySearch(_horizontalPointList, new Point(x, y), _comparatorYX);
		int ins = (ndx >= 0) ? ndx : -ndx - 1;

		// Point p1=(Point)(((ins-1)>=0)?h.get(ins-1):null);
		// Point
		// p2=(Point)((ndx>=0)?((ins<(hPointList.size()-1))?hPointList.get(ins+1):null):((ins<hPointList.size())?hPointList.get(ins):null));
		// busquemos los extremos
		// previous=(p1!=null && p1.y==y)?p1:null;
		// next=(p2!=null && p2.y==y)?p2:null;
		_previous = _next = null;
		Point p1 = null;
		Point p2 = null;
		int prevPos = ins - 1;
		int hitPos = ins;
		int nextPos = (ndx >= 0) ? ins + 1 : ins;
		if (prevPos >= 0) {
			p1 = (Point) _horizontalPointList.get(prevPos);
			_previous = (p1._y == y) ? p1 : null;
		}

		if (ndx >= 0) {
			_hit = (Point) _horizontalPointList.get(hitPos);
		} else {
			_hit = null;
		}
		if (nextPos < _horizontalPointList.size()) {
			p2 = (Point) _horizontalPointList.get(nextPos);
			_next = (p2._y == y) ? p2 : null;
		}

		if (ndx >= 0) {
			return EXIST;
		}

		if (_previous != null && _next != null) {
			return BETWEEN;
		}

		if (_previous == null && _next == null) {
			return NOTHING;
		}

		if (_previous == null) {
			return MIN;
		}
		return MAX;
	}

	/**
	 * Verifica si existe un punto en la coordenada (x,y) y devuelve la
	 * condicion de este punto: EXIST si existe un punto NOTHING no habia nada
	 * BETWEEN esta entre otros dos MAX es el mayor de la lista MIN es el menor
	 * de la lista
	 * <p/>
	 * La busqueda es hecha sobre la lista que guarda los puntos hordenados
	 * verticalmente
	 *
	 * @param x int La coordenada x
	 * @param y int La coordenada y
	 * @return int La condicion del punto
	 */
	public int findVertical(int x, int y) {
		int ndx = Collections.binarySearch(_verticalPointList, new Point(x, y), _comparatorXY);

		int ins = (ndx >= 0) ? ndx : -ndx - 1;

		/*
		 * // Calculo los candidatos a previous y next Point p1 = (Point) (((ins
		 * - 1) >= 0) ? vListPoint.get(ins - 1) : null); Point p2 = (Point)
		 * ((ndx >= 0) ? ((ins < (vListPoint.size() - 1)) ? vListPoint.get(ins +
		 * 1) : null) : ((ins < vListPoint.size()) ? vListPoint.get(ins) :
		 * null)); // busquemos los extremos previous = (p1 != null && p1.x ==
		 * x) ? p1 : null; next = (p2 != null && p2.x == x) ? p2 : null; //
		 */
		_previous = _next = null;
		Point p1 = null;
		Point p2 = null;
		int prevPos = ins - 1;
		int hitPos = ins;
		int nextPos = (ndx >= 0) ? ins + 1 : ins;
		if (prevPos >= 0) {
			p1 = (Point) _verticalPointList.get(prevPos);
			_previous = (p1._x == x) ? p1 : null;
		}
		if (ndx >= 0) {
			_hit = (Point) _verticalPointList.get(hitPos);
		} else {
			_hit = null;
		}
		if (nextPos < _verticalPointList.size()) {
			p2 = (Point) _verticalPointList.get(nextPos);
			_next = (p2._x == x) ? p2 : null;
		} // */
		if (ndx >= 0) {
			return EXIST;
		}
		if (_previous != null && _next != null) {
			return BETWEEN;
		}
		if (_previous == null && _next == null) {
			return NOTHING;
		}
		if (_previous == null) {
			return MIN;
		}
		return MAX;
	}

	/**
	 * Busca el punto existente en (x,y) y en caso de que existe, lo devolvemos
	 *
	 * @param x int Coordenada x
	 * @param y int Coordenada y
	 * @return newgui.Point El punto
	 */
	public Point get(int x, int y) {
		int ndx = Collections.binarySearch(_verticalPointList, new Point(x, y), _comparatorXY);
		if (ndx >= 0) {
			return (Point) _verticalPointList.get(ndx);
		}
		return null;
	}

	/**
	 * Este metodo devuelve una list de puntos que estan entre P1 y P2 sin
	 * incluirlos Supuestos: point1 y point2 son distintos y estan en una vertical o una
	 * horizontal point1 <point2 Creation date: (14/09/96 09:14:29 p.m.)
	 *
	 * @param point1 newgui.Point El primer punto
	 * @param point2 newgui.Point El segundo punto
	 * @return java.util.List
	 */
	public List getPointList(Point point1, Point point2) {
		List list;
		Comparator comparator;
		List listPoint = new LinkedList();

		if (point1._y == point2._y) {
			list = _horizontalPointList;
			comparator = _comparatorYX;
		} else {
			list = _verticalPointList;
			comparator = _comparatorXY;
		}

		int ndx = Collections.binarySearch(list, point1, comparator);
		ndx++;
		Point point = (Point) list.get(ndx++);
		while (point != null && point != point2) {
			listPoint.add(point);
			point = (Point) list.get(ndx++);
		}
		return listPoint;
	}

	/**
	 * Devuelve el ultimo punto encontrado encontrado la ultima ves que se hiso
	 * findVertical o findHorizontal
	 *
	 * @return csimgui.Point El punto
	 */
	public Point hit() {
		return _hit;
	}

	/**
	 * Obtenemos un iterador sobre la lista horizontal
	 *
	 * @return java.util.Iterator El iterador
	 */
	public Iterator iteratorX() {
		return _horizontalPointList.iterator();
	}

	/**
	 * Obtenemos un iterador sobre la lista vertical
	 *
	 * @return java.util.Iterator El iterador
	 */
	public Iterator iteratorY() {
		return _verticalPointList.iterator();
	}

	/**
	 * Devuelve punto siguiente al ultimo punto encontrado la ultima ves que se
	 * hiso findV o findH
	 *
	 * @return csimgui.Point El punto encontrado
	 */
	public Point next() {
		return _next;
	}

	/**
	 * Devuelve punto anterior al ultimo punto encontrado la ultima ves que se
	 * hiso findV o findH
	 *
	 * @return csimgui.Point El punto
	 */
	public Point previous() {
		return _previous;
	}

	/**
	 * Borramos un punto de la matriz
	 *
	 * @param point Point punto a borrar
	 */
	public void remove(Point point) {
		int indexV = Collections.binarySearch(_verticalPointList, point, _comparatorXY);

		if (indexV < 0) {
			return;
		}

		int indexH = Collections.binarySearch(_horizontalPointList, point, _comparatorYX);

		_verticalPointList.remove(indexV);
		_horizontalPointList.remove(indexH);
	}
}