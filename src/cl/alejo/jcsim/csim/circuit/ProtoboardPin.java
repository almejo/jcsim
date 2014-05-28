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
 */

import cl.alejo.jcsim.csim.dom.Gate;
import cl.alejo.jcsim.csim.dom.Pin;

import java.awt.*;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ProtoboardPin extends Protoboard {
	/**
	 * ProtoPin constructor comment.
	 */
	public ProtoboardPin() {
		super();

	}

	/**
	 * Inserta un Pin en la posicion X,Y del Protoboard y lo conecta con todos
	 * aquellos pines que compartan los cables. Creation date: (06/09/96
	 * 12:08:12 a.m.)
	 *
	 * @param x int coordenadas donde insertar el pin
	 * @param y int
	 */
	public void addPin(byte pinId, Gate gate, int x, int y) {

		ContactPin contact = (ContactPin) poke(x, y);

		if (contact.getPinList() == null)
			contact.setPinList(new PinList());

		PinList pinList = contact.getPinList();

		if (!pinList.contains(pinId, gate)) {
			pinList.add(pinId, gate);
		}
		reconnect(contact);
	}

	public void attachContacts(Contact contact, List contactsList) {
		ContactPin contactPin = (ContactPin) contact;

		if (contactsList.contains(contactPin))
			return;

		contactsList.add(contactPin);

		_matrix.findHorizontal(contactPin._x, contactPin._y);
		Contact cttHprevious = (Contact) _matrix.previous();
		Contact cttHnext = (Contact) _matrix.next();

		_matrix.findVertical(contactPin._x, contactPin._y);
		Contact cttVprevious = (Contact) _matrix.previous();
		Contact cttVnext = (Contact) _matrix.next();

		if (isConnected(contactPin, EAST))
			attachContacts(cttHnext, contactsList);
		if (isConnected(contactPin, WEST))
			attachContacts(cttHprevious, contactsList);
		if (isConnected(contactPin, NORTH))
			attachContacts(cttVnext, contactsList);
		if (isConnected(contactPin, SOUTH))
			attachContacts(cttVprevious, contactsList);

		return;
	}

	/**
	 * obtiene la lista de pines que tiene el los contactos de la lista Creation
	 * date: (20/09/96 04:50:24 p.m.)
	 *
	 * @param contactsList List
	 * @return java.util.List
	 */
	public List getAttachedPins(List contactsList) {

		List pinList = new LinkedList();

		for (Iterator iterator = contactsList.iterator(); iterator.hasNext(); ) {
			ContactPin ctt = (ContactPin) iterator.next();
			if (ctt.getPinList() != null) {
				pinList.addAll(ctt.getPinList().list());
			}
		}
		return pinList;
	}

	public Contact makeContact(int x, int y) {
		return new ContactPin(x, y);
	}

	public void paint(Graphics2D canvas, Box boxViewport) {

		ContactPin previousContact = null;
		canvas.setColor(Color.GREEN);
		for (Iterator iterH = _matrix.iteratorX(); iterH.hasNext(); ) {
			ContactPin currentContact = (ContactPin) iterH.next();

			if (previousContact != null && isConnected(previousContact, EAST) && boxViewport.isIntersected(previousContact, currentContact)) {
				drawWire(canvas, previousContact, currentContact);
			}

			drawContact(canvas, currentContact);
			previousContact = currentContact;

			if (currentContact._y > boxViewport._yf) {
				break;
			}
		}

		previousContact = null;

		for (Iterator iterV = _matrix.iteratorY(); iterV.hasNext(); ) {
			ContactPin currentContact = (ContactPin) iterV.next();

			canvas.setColor(getGuideColor(currentContact));
			if (previousContact != null && isConnected(previousContact, NORTH) && boxViewport.isIntersected(previousContact, currentContact)) {
				drawWire(canvas, previousContact, currentContact);
			}

			previousContact = currentContact;
			if (currentContact._x > boxViewport._xf) {
				break;
			}
		}
	}

	private void drawContact(Graphics2D canvas, ContactPin contact) {
		int connections = countConnections(contact);
		if (connections == 2 || connections == 3) {
			return;
		}
		Color color = canvas.getColor();
		canvas.setColor(getGuideColor(contact));
		canvas.fillRect(contact._x + Circuit.HALF_GRIDSIZE - 3, contact._y + Circuit.HALF_GRIDSIZE - 3, 7, 7);
		canvas.setColor(color);
	}

	private void drawWire(Graphics2D canvas, ContactPin previousContact, ContactPin currentContact) {
		Color color = canvas.getColor();
		canvas.setColor(getGuideColor(currentContact));
		int xi = previousContact._x + Circuit.HALF_GRIDSIZE;
		int yi = previousContact._y + Circuit.HALF_GRIDSIZE;
		int xf = currentContact._x + Circuit.HALF_GRIDSIZE;
		int yf = currentContact._y + Circuit.HALF_GRIDSIZE;
		canvas.drawLine(xi, yi, xf, yf);
		canvas.setColor(color);
	}

	private Color getGuideColor(ContactPin contact) {
		if (contact.getGuidePin() != null) {
			switch (contact.getGuidePin().getInValue()) {
				case 0:
					return Color.BLACK;
				case 1:
					return Color.RED;
				case -1:
					return Color.GREEN;
			}
		}
		return Color.GREEN;
	}

	public void reconnect(List contactList) {

		List listPin = reconnectContactPins(contactList);

		Pin firstPin = null;

		if (listPin.size() > 0) {
			firstPin = (Pin) listPin.get(0);
		}

		setGuidePin(firstPin, contactList);

	}

	private List reconnectContactPins(List contactList) {
		List listPin = getAttachedPins(contactList);

		if (listPin.size() > 0) {
			Iterator iterPin = listPin.iterator();

			Pin pinFirst = (Pin) iterPin.next();
			pinFirst.disconnect();

			while (iterPin.hasNext()) {
				Pin pin = (Pin) iterPin.next();
				pin.disconnect();
				pinFirst.connect(pin);
			}
		}
		return listPin;
	}

	private void setGuidePin(Pin firstPin, List contactList) {
		Iterator cttIter = contactList.iterator();
		while (cttIter.hasNext()) {
			ContactPin contact = (ContactPin) cttIter.next();
			contact.setGuidePin(firstPin);
		}
	}

	public void removePin(byte pinId, Gate gate, int x, int y) {
		ContactPin contact = (ContactPin) poke(x, y);

		PinList pinList = contact.getPinList();

		if (pinList == null)
			return;

		if (pinList.contains(pinId, gate)) {
			pinList.remove(pinId, gate);
		}

		reconnect(contact);
		testContact(contact);
	}

	/**
	 * Este metodo comprueba que un contacto sea relevante. Un contacto es
	 * relevante cuando, tiene pines, o bien, es una esquina o el extremo de un
	 * cable. Si el contacto no cumple esas
	 *
	 * @param contact newgui.Contact
	 */
	public void testContact(Contact contact) {

		if (((ContactPin) contact).hasPins()) {
			return;
		}

		if (contact.isHorizontalMiddlePoint() || contact.isVerticalMiddlePoint() || !contact.isConnected()) {
			deleteContact(contact); // El nodo no era trascendente
		}
	}
}
