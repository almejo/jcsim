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

import java.io.Serializable;

/**
 * Listas circulares.
 */
public class Link implements Serializable {
	protected Link _next = this;

	public Link() {
	}

	/**
	 * Clona un link... este estara solito :)
	 * 
	 * @return java.lang.Object
	 */
	public Object clone() {
		Link link = null;
		try {
			link = (Link) super.clone();
			link._next = link;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return link;
	}

	/**
	 * Se extrae link de la lista que comienza con este link. El link estraido
	 * forma una lista de un elemento.
	 */
	public void delete(Link link) {
		Link prevlink = findPrevious(link);
		if (prevlink == null)
			Kit.Error("Link nulo", link);
		prevlink._next = link._next;
		link._next = link;
	}

	/**
	 * Busca el predecesor en una lista.
	 * 
	 * @return el link cuyo sucesor es ``this''.
	 */
	public Link findPrevious(Link link) {
		Link prevlink = this;

		do {
			Link nextLink = prevlink.next();
			if (nextLink == link)
				return prevlink;
			prevlink = nextLink;
		} while (prevlink != this);
		return null;
	}

	/**
	 * Une dos listas circulares. No hace nada si ambos links pertenecian a la
	 * misma lista.
	 */
	public void join(Link link) {
		if (findPrevious(link) == null) {
			Link savelink = link._next;
			link._next = _next;
			_next = savelink;
		}
	}

	/**
	 * Calcula el largo de una lista circular.
	 * 
	 * @return el numero de links en la lista circular.
	 */
	public int length() {
		Link link = _next;
		int len = 1;
		while (link != this) {
			link = link.next();
			len++;
		}
		return len;
	}

	/**
	 * Entrega el siguiente link en una lista circular.
	 * 
	 * @return el sucesor en esta lista.
	 */
	public Link next() {
		return _next;
	}

	public Link getNext() {
		return _next;
	}

	public void setNext(Link next) {
		_next = next;
	}

	public String toString() {
		return "" + this + " - " + _next;
	}

	public static String print(Link link) {
		Link prevlink = link;
		String ret = "";
		do {
			ret += prevlink;
			prevlink = prevlink.next();
		} while (prevlink != link);
		return ret;
	}
}