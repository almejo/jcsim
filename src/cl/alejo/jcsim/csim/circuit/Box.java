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

package cl.alejo.jcsim.csim.circuit;

public class Box implements java.io.Serializable {
	private static final int MIN_INT = (int) 0x80000000;

	private static final int MAX_INT = (int) 0x7fffffff;

	public static final short GRIDSIZE = 8; // taman~o de la grilla, potencia de

	protected int _xi;

	protected int _yi;

	protected int _xf;

	protected int _yf;

	/**
	 * Creates an empty box
	 */
	public Box() {
		_xi = _yi = MAX_INT;
		_xf = _yf = MIN_INT;
	}

	/**
	 * A new box.
	 *
	 * @param xi int El x inicial
	 * @param yi int El y inicial
	 * @param xf int El x final
	 * @param yf int El x final
	 */
	public Box(int xi, int yi, int xf, int yf) {

		_xi = truncateToGrid(xi);
		_xf = truncateToGrid(xf);
		_yi = truncateToGrid(yi);
		_yf = truncateToGrid(yf);

		if (_xi > _xf) {
			int aux = _xf;
			_xf = _xi;
			_xi = aux;
		}

		if (_yi > _yf) {
			int aux = _yf;
			_yf = _yi;
			_yi = aux;
		}
	}

	/**
	 * A new box using an existing one
	 */
	public Box(Box box) {
		_xi = truncateToGrid(box._xi);
		_yi = truncateToGrid(box._yi);
		_xf = truncateToGrid(box._xf);
		_yf = truncateToGrid(box._yf);
	}

	/**
	 * Alinea una coordenada con la grilla
	 *
	 * @param value int La coordenada a alinear
	 * @return int El valor de coord alineado con la grilla
	 */
	public static int ceil(int value) {
		return (value & ((GRIDSIZE - 1)));
	}

	/**
	 * Testea si un punto esta incluido en la caja
	 *
	 * @param x int La coordenada x a revisar.
	 * @param y int La coordenada y a revisar.
	 * @return boolean True si el punto esta dentro de la caja
	 */
	public boolean contains(int x, int y) {
		return (x <= _xf) && (x >= _xi) && (y <= _yf) && (y >= _yi);
	}

	/**
	 * Devuelve true si alguno de los puntos esta dentro de la caja. Creation
	 * date: (12/01/01 21:01:05)
	 *
	 * @param box circuit.Box
	 * @return java.lang.Boolean
	 */
	public boolean containsSomeCorner(Box box) {
		return contains(box._xi, box._yi) || contains(box._xf, box._yi) || contains(box._xi, box._yf) || contains(box._xf, box._yf);
	}

	/**
	 * Verifica que <i>point </i> este contenido en la caja
	 * <p/>
	 * Creation date: (14/01/01 17:00:17)
	 *
	 * @param p circuit.Point El punto a comprobar
	 * @return boolean
	 */
	public boolean contains(Point p) {
		return contains(p._x, p._y);
	}

	/**
	 * Extiende la caja para que incluya el punto (x,y)
	 *
	 * @param x int Coordenada x
	 * @param y int Coordenada y
	 */
	public void extend(int x, int y) {
		x = truncateToGrid(x);
		y = truncateToGrid(y);

		if (_xf < x) {
			_xf = x;
		}
		if (_xi > x) {
			_xi = x;
		}
		if (_yi > y) {
			_yi = y;
		}
		if (_yf < y) {
			_yf = y;
		}
	}

	/**
	 * Extiende la caja para que contenga a la otra
	 *
	 * @param box Box La segunda caja
	 */
	public void extend(Box box) {
		if (box._xi < box._xf) {
			extend(box._xi, box._yi);
			extend(box._xf, box._yi);
			extend(box._xi, box._yf);
			extend(box._xf, box._yf);
		}
	}

	/**
	 * Busca el centro de la caja
	 * <p/>
	 * Creation date: (17/04/01 14:04:19)
	 *
	 * @return circuit.Point El centro de la caja
	 */
	public Point getCenter() {
		return new Point(_xi + getWidth() / 2, _yi + getHeight() / 2);
	}

	/**
	 * Devuelve la altura de la caja Creation date: (14/01/01 16:12:08)
	 *
	 * @return double
	 */
	public int getHeight() {
		return Math.abs(_yf - _yi);
	}

	/**
	 * Devuelve el ancho de la caja Creation date: (14/01/01 16:11:45)
	 *
	 * @return double
	 */
	public int getWidth() {
		return Math.abs(_xf - _xi);
	}

	public boolean inBorder(Box box) {
		return box._xi < _xi || box._xf > _xf || box._yi < _yi || box._yf > _yf;
	}

	/**
	 * Devuelve una caja que es la interseccion de this con box Creation date:
	 * (09/04/01 17:01:06)
	 *
	 * @param box circuit.Box
	 * @return circuit.Box
	 */
	public Box intersection(Box box) {
		int xi;
		int yi;
		int xf;
		int yf;

		if (_xi > box._xi) {
			xi = _xi;
		} else {
			xi = box._xi;
		}

		if (_yi > box._yi) {
			yi = _yi;
		} else {
			yi = box._yi;
		}

		if (_xf < box._xf) {
			xf = _xf;
		} else {
			xf = box._xf;
		}

		if (_yf < box._yf) {
			yf = _yf;
		} else {
			yf = box._yf;
		}

		return new Box(xi, yi, xf, yf);
	}

	/**
	 * Devuelve true si la caja toca en algun punto a la segunda
	 * <p/>
	 * Creation date: (29/08/96 04:58:31 p.m.)
	 *
	 * @param box GUI.Box
	 * @return boolean
	 */
	public boolean isIntersected(Box box) {
		return !contains(box._xi, box._yi) && !contains(box._xf, box._yi) && !contains(box._xf, box._yf) && !contains(box._xi, box._yf);
	}

	/**
	 * Devuelve true si la caja es atravesada por una linea entre <i>ctt1 </i> y
	 * <i>ctt2 </ctt2>
	 * <p/>
	 * Creation date: (29/08/96 04:58:31 p.m.)
	 *
	 * @param ctt1 Contact El primer contacto
	 * @param ctt2 Contact El segundo contacto
	 * @return boolean
	 */
	public boolean isIntersected(Contact ctt1, Contact ctt2) {

		if (contains(ctt1) || contains(ctt2)) {
			return true;
		}

		if (ctt1._x <= _xi && ctt2._x >= _xf && ctt1._y >= _yi && ctt1._y <= _yf) {
			return true;
		}

		return ctt1._y <= _yi && ctt2._y >= _yf && ctt1._x >= _xi && ctt1._x <= _xf;
	}

	/**
	 * Desplaza la caja horizontalmente
	 * <p/>
	 * Creation date: (09/04/01 18:42:22)
	 *
	 * @param deltaX int el desplazamiento en X
	 */
	public void moveHorizontal(int deltaX) {
		_xi += deltaX;
		_xf += deltaX;
	}

	/**
	 * Desplaza la caja verticalmente Creation date: (09/04/01 18:42:22)
	 *
	 * @param deltaY int El desplazamiento
	 */
	public void moveVertical(int deltaY) {
		_yi += deltaY;
		_yf += deltaY;
	}

	/**
	 * Cambia el ancho y el alto de la caja Creation date: (11/08/01 0:35:45)
	 *
	 * @param width int El nuevo ancho
	 * @param heght int El nuevo alto
	 */
	public void resize(int width, int heght) {
		_xi -= ((width - getWidth()) / 2);
		_yf -= ((heght - getHeight()) / 2);
	}

	/**
	 * Zoomeamos una caja Creation date: (07/04/01 16:20:31)
	 *
	 * @param zoom double
	 */
	public void scale(double zoom) {
		_xf = _xi + (int) (getWidth() * zoom);
		_yf = _yi + (int) (getHeight() * zoom); // */
	}

	public void set(int xi, int yi, int xf, int yf) {
		_xi = xi;
		_yi = yi;
		_xf = xf;
		_yf = yf;
	}

	/**
	 * Iguala con otra caja Creation date: (11/01/01 22:56:10)
	 *
	 * @param box Box La caja a la que debe parecerce
	 */
	public void set(Box box) {
		_xf = box._xf;
		_yf = box._yf;
		_xi = box._xi;
		_yi = box._yi;
	}

	/**
	 * Traslada la caja hacia un punto especifico
	 *
	 * @param x int La posicion en x
	 * @param y int La posicion en y
	 */
	public void moveTo(int x, int y) {
		int width = getWidth();
		int height = getHeight();
		_xi = x;
		_yi = y;
		_xf = _xi + width;
		_yf = _yi + height;
	}

	/**
	 * Vacia la caja
	 */
	public void setEmpty() {
		_xi = _yi = MAX_INT;
		_xf = _yf = MIN_INT;
	}

	public String toString() {
		String ret = "\n(" + getWidth() + "," + getHeight() + ")\n     (" + _xi + "," + _yi + ")_______________(" + this._xf + "," + this._yi + ")\n";
		ret += "          |               |\n";
		ret += "    (" + this._xi + "," + this._yf + ")_______________(" + this._xf + "," + this._yf + ")\n";
		return ret;
	}

	/**
	 * Trunca la coordenada para alinearla con la grilla
	 *
	 * @param value int La coordenada a alinear
	 * @return int El valor de coord alineado con la grilla
	 */
	private static int truncateToGrid(int value) {
		return (value & (~(GRIDSIZE - 1)));
	}

	public int getXf() {
		return _xf;
	}

	public int getXi() {
		return _xi;
	}

	public int getYf() {
		return _yf;
	}

	public int getYi() {
		return _yi;
	}

	public void setYf(int i) {
		_yf = i;
	}

	public void setXf(int i) {
		_xf = i;
	}

	public void setXi(int xi) {
		_xi = xi;
	}

	public void setYi(int yi) {
		_yi = yi;
	}

	public boolean isEmpty() {
		return _xf <= _xi && _yf <= _yi;
	}

	public boolean contains(Box box) {
		return contains(box._xi, box._yi) && contains(box._xf, box._yi) && contains(box._xi, box._yf) && contains(box._xf, box._yf);
	}

	public Box getClone() {
		return new Box(_xi, _yi, _xf, _yf);
	}

}