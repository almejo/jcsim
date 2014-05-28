package cl.alejo.jcsim.csim.gates;

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

import cl.alejo.jcsim.csim.dom.Pin;
import cl.alejo.jcsim.csim.simulation.Agenda;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class DiagramFrame extends JFrame implements ActionListener {
	private DiagramCanvas dCanvas = new DiagramCanvas();
	private NameCanvas nCanvas;
	private RulerCanvas rCanvas;
	private JPanel panel = new JPanel();

	// El boton para el print
	private JButton buttPrint = new JButton("Print");

	// El evento de dibujo
	private PlotEvent plotEvent;

	public DiagramFrame(Agenda agenda) {
		super("Timming diagram");

		nCanvas = new NameCanvas(panel);
		rCanvas = new RulerCanvas();
		panel.setLayout(new BorderLayout());
		panel.add(dCanvas, BorderLayout.CENTER);
		panel.add(nCanvas, BorderLayout.WEST);
		panel.add(rCanvas, BorderLayout.SOUTH);

		getContentPane().add(panel);

		// Eventos de ventana
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				plotEvent.cancel();
			}
		});

		// Creo y agrego el boton
		// buttPrint.addActionListener(this);
		// getContentPane().add("East", buttPrint);
		plotEvent = new PlotEvent(agenda, dCanvas);
		plotEvent.program(0);
	}

	public void actionPerformed(ActionEvent event) {

		// Veo si es el boton de print
		JButton button = (JButton) event.getSource();

		// Si es el boton imprimo
		if (button == buttPrint) {
		}
	}

	public void add(String label, Pin pin) {
		nCanvas.add(label);
		dCanvas.add(pin);
	}

	public void end() {
		// Primero cancelamos el evento
		if (plotEvent != null)
			this.plotEvent.cancel();

		// Y cerramos
		dispose();
	}

	public void start() {
		plotEvent.program(0);
	}
}
