/**
 *
 * jcsim
 *
 * Created on Jul 17, 2004
 *
 * This program is distributed under the terms of the GNU General Public License
 * The license is included in license.txt
 *
 * @author: Luis Mateu
 *
 */
package cl.alejo.jcsim.csim.simulation;

import java.util.ArrayList;
import java.util.Iterator;

public class Heap implements java.io.Serializable {
	protected static int MIN_SIZE = 100;
	protected HeapElement[] array;
	protected int _size;

	/**
	 * Crea un nuevo heap
	 */
	public Heap() {
		array = new HeapElement[MIN_SIZE + 1];
		_size = 0;
	}

	/**
	 * Borra <i>element</i> del heap
	 *
	 * @param element HeapElement  del Heap
	 */
	public void delete(HeapElement element) {
		int i = element.ndx;
		if (i >= 0) {
			array[i] = array[_size];
			_size--;
			siftup(i);
		}
	}

	/**
	 * Extrae el primer elemento del heap
	 *
	 * @return HeapElement El elemento con el menor tiempo
	 */
	public HeapElement extractMin() throws EmptyHeapException {
		HeapElement element = null;

		if (_size < 1) {
			throw new EmptyHeapException();
		}

		element = array[1];
		array[1] = array[_size];
		array[1].ndx = 1;
		_size--;
		siftup(1);

		element.ndx = -1;
		return element;
	}

	/**
	 * Busca el indice de <i>element</i> y retorna el indice dentro del arreglo
	 *
	 * @param element HeapElement El elemento a buscar
	 * @return int index El indice
	 */
	public int findElement(HeapElement element) {
		for (int j = 1; j <= _size; j++) {
			if (array[j] == element) {
				return j;
			}
		}
		return -1;
	}

	public void insert(HeapElement element) {
		_size++;

		ifHeapFullMakeItLarger();

		int j = organizeElements(element.key());
		array[j] = element;
		array[j].ndx = j;
	}

	/**
	 * Organiza los elementos del arreglo luego deuna insercion
	 *
	 * @param key double  La llave del elemento insertados
	 */
	private int organizeElements(double key) {
		int i;
		int j1;
		for (j1 = _size; j1 > 1; j1 = i) {
			i = j1 / 2;
			if (array[i].key() <= key) {
				break;
			}
			array[j1] = array[i];
			array[j1].ndx = j1;
		}
		return j1;
	}

	private void ifHeapFullMakeItLarger() {
		int i;
		if (_size == array.length) {
			HeapElement[] aux = new HeapElement[array.length * 2];
			for (i = 1; i < array.length; i++) {
				aux[i] = array[i];
			}
			array = aux;
		}
	}

	/**
	 * Recupera el elemento con el menor tiempo en el Heap
	 *
	 * @return HeapElement element El primer elemento del Heap
	 */
	public HeapElement getFirstElement() throws EmptyHeapException {
		if (_size < 1) {
			throw new EmptyHeapException();
		}
		return array[1];
	}

	/**
	 * Devuelve un iterador sobre el heap
	 */
	public Iterator seeElements() {

		ArrayList list = new ArrayList();
		for (int i = 0; i < array.length; i++) {
			list.add(array[i]);
		}
		return list.iterator(); // new HeapEnumeration(array, _size);
	}

	public void siftup(int i) {
		HeapElement tempr;
		int j;
		while ((j = 2 * i) <= _size) {
			if (j < _size && array[j].key() > array[j + 1].key()) {
				j++;
			}
			if (array[i].key() > array[j].key()) {
				tempr = array[j];
				array[j] = array[i];
				array[j].ndx = j;
				array[i] = tempr;
				array[i].ndx = i;
				i = j;
			} else {
				break;
			}
		}
	}

	/**
	 * Imprime los elementos del heap
	 *
	 * @return java.lang.String msg El string con los eventos
	 */
	public String toString() {
		String msg = "Heap: ";
		for (int i = 1; i < _size; i++) {
			msg += "" + i + " " + array[i].key() + " " + array[i] + " -- ";
		}
		return msg;
	}

	public int getSize() {
		return _size;
	}

}
