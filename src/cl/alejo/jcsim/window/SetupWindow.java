package cl.alejo.jcsim.window;

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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import cl.alejo.jcsim.csim.dom.ConfigurableGate;
import cl.alejo.jcsim.csim.dom.Gate;

public class SetupWindow extends JFrame implements ActionListener {
	JPanel panelField = new JPanel();
	JPanel panelButtons = new JPanel();
	JTextField[] fldParam;
	JLabel[] labelParamName;
	JButton buttOk;
	JButton buttCancel;

	// La compuerta a modificar
	Gate gate;
	int nparams = 0;

	// El icono
	ImageIcon imgIcon = new ImageIcon("images/Diagram.jpg");

	/**
	 * SetupWindow constructor comment.
	 * 
	 * @param title
	 *            java.lang.String
	 */
	public SetupWindow(Gate gate, String title, String[] txtParams, String[] current) {
		super(title);

		// Guardo la compueta
		this.gate = gate;

		// El largo
		nparams = txtParams.length;

		// El panel de fields
		panelField.setLayout(new java.awt.GridLayout(nparams * 2, 0));

		// El arreglo
		fldParam = new JTextField[nparams];
		labelParamName = new JLabel[fldParam.length];
		for (int i = 0; i < nparams; i++) {
			fldParam[i] = new JTextField(current[i], 10);
			labelParamName[i] = new JLabel(txtParams[i]);
			panelField.add(labelParamName[i]);
			panelField.add(fldParam[i]);
		}

		// Los botones
		panelButtons.setLayout(new java.awt.GridLayout(0, 2));
		panelButtons.add((buttOk = new JButton("Ok")));
		panelButtons.add((buttCancel = new JButton("Cancel")));
		buttOk.addActionListener(this);
		buttCancel.addActionListener(this);

		// El panel del icono
		JLabel labelIcon = new JLabel(imgIcon);

		// Agrego el panel con los textfields
		getContentPane().add("East", labelIcon);
		getContentPane().add("Center", panelField);
		getContentPane().add("South", panelButtons);
	}

	/**
	 * Insert the method's description here. Creation date: (24/04/01 8:08:49)
	 * 
	 * @param event
	 *            java.awt.event.ActionEvent
	 */
	public void actionPerformed(ActionEvent event) {

		// Rescato el boton.
		JButton button = (JButton) event.getSource();

		// VEo que hago
		if (button == buttOk) {
			// recojo los parametros
			String[] newParams = new String[nparams];
			System.out.println("Setting parameters");
			for (int i = 0; i < nparams; i++) {
				newParams[i] = fldParam[i].getText();
				System.out.println("	" + labelParamName[i].getText() + ": " + newParams[i]);
			}

			// la configuro si es que todavia existe
			if (gate != null)
				((ConfigurableGate) gate).fillParams(newParams);

			// Y me voy
			dispose();
		} else if (button == buttCancel) {
			dispose();
		}
	}
}
