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

import java.awt.Image;

public class SelectionContainer {

	Circuit _circuit;
	Image _image;
	Box _box;
	Point _point;

	public SelectionContainer(Circuit circuit, Image image, Box box) {
		_circuit = circuit;
		_image = image;
		_box = box;
	}

	public Box getBox() {
		return _box;
	}

	public Circuit getCircuit() {
		return _circuit;
	}

	public Image getImage() {
		return _image;
	}

	public void setBox(Box box) {
		_box = box;
	}

	public void setCircuit(Circuit circuit) {
		_circuit = circuit;
	}

	public void setImage(Image image) {
		_image = image;
	}

	public SelectionContainer getClone() {
		return new SelectionContainer(_circuit.getClone(), _image, _circuit.getExtent().getClone());
	}
}
