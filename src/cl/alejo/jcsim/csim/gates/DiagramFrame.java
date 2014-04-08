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
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import cl.alejo.jcsim.csim.dom.Pin;
import cl.alejo.jcsim.csim.simulation.Agenda;

public class DiagramFrame extends JFrame implements ActionListener {
	private DiagramCanvas dCanvas = new DiagramCanvas();
	private NameCanvas nCanvas;
	private RulerCanvas rCanvas;
	private JPanel panel = new JPanel();

	// El boton para el print
	private JButton buttPrint = new JButton("Print");

	// El evento de dibujo
	private PlotEvent plotEvent;

	/**
	 * DiagramFrame constructor comment.
	 */
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

	/**
	 * Insert the method's description here. Creation date: (27/04/01 11:20:38)
	 * 
	 * @param event
	 *            java.awt.event.ActionEvent
	 */
	public void actionPerformed(ActionEvent event) {

		// Veo si es el boton de print
		JButton button = (JButton) event.getSource();

		// Si es el boton imprimo
		if (button == buttPrint) {
		}
	}

	/**
	 * Insert the method's description here. Creation date: (23/04/01 7:41:41)
	 * 
	 * @param label
	 *            java.lang.String
	 * @param pin
	 *            csim.Pin
	 */
	public void add(String label, Pin pin) {
		nCanvas.add(label);
		dCanvas.add(pin);
	}

	/**
	 * Insert the method's description here. Creation date: (16/05/01 15:42:46)
	 */
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
