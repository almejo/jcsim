package cl.alejo.jcsim.imageTools;

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
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

import javax.swing.JButton;

import cl.alejo.jcsim.csim.circuit.Circuit;
import cl.alejo.jcsim.csim.dom.Gate;
import cl.alejo.jcsim.csim.gates.AssocDescriptor;
import cl.alejo.jcsim.csim.gates.Clk;
import cl.alejo.jcsim.csim.gates.ClkDescriptor;
import cl.alejo.jcsim.csim.gates.FFData;
import cl.alejo.jcsim.csim.gates.FFDataDescriptor;
import cl.alejo.jcsim.csim.gates.Flag;
import cl.alejo.jcsim.csim.gates.FlagDescriptor;
import cl.alejo.jcsim.csim.gates.GateAssoc;
import cl.alejo.jcsim.csim.gates.GateDescriptor;
import cl.alejo.jcsim.csim.gates.IconGate;
import cl.alejo.jcsim.csim.gates.IconGateCompilable;
import cl.alejo.jcsim.csim.gates.IconGateDescriptor;
import cl.alejo.jcsim.csim.gates.IconImage;
import cl.alejo.jcsim.csim.gates.Label;
import cl.alejo.jcsim.csim.gates.LabelDescriptor;
import cl.alejo.jcsim.csim.gates.Not;
import cl.alejo.jcsim.csim.gates.NotDescriptor;
import cl.alejo.jcsim.csim.gates.ParamAssocGate;
import cl.alejo.jcsim.csim.gates.ParamClk;
import cl.alejo.jcsim.csim.gates.ParamFFData;
import cl.alejo.jcsim.csim.gates.ParamLabel;
import cl.alejo.jcsim.csim.gates.ParamNot;
import cl.alejo.jcsim.csim.gates.ParamSegmentDisplay;
import cl.alejo.jcsim.csim.gates.ParamSwitch;
import cl.alejo.jcsim.csim.gates.ParamTreeState;
import cl.alejo.jcsim.csim.gates.ParamVoid;
import cl.alejo.jcsim.csim.gates.SegmentDisplay;
import cl.alejo.jcsim.csim.gates.SegmentDisplayDescriptor;
import cl.alejo.jcsim.csim.gates.Switch;
import cl.alejo.jcsim.csim.gates.SwitchDescriptor;
import cl.alejo.jcsim.csim.gates.TemplateGate;
import cl.alejo.jcsim.csim.gates.TemplateGateDescriptor;
import cl.alejo.jcsim.csim.gates.TimeDiagram;
import cl.alejo.jcsim.csim.gates.TimeDiagramDescriptor;
import cl.alejo.jcsim.csim.gates.TreeState;
import cl.alejo.jcsim.csim.gates.TreeStateDescriptor;

public class CreateMenu extends javax.swing.JFrame implements java.awt.event.ActionListener {

	// ************************************************
	// Behaviors de las compuertas asociativas
	// ************************************************

	int[][] behaviorAnd;
	int[][] behaviorOr;

	// El canvas
	MiniCanvas canvas;

	// El boton
	JButton button = new JButton("Print");
	JButton button2 = new JButton("Ok");

	// El circuito
	Circuit circuit = new Circuit();

	// La componente
	Gate not;
	IconGate iconNot;
	GateDescriptor descNot;

	// La componente
	Gate treeState;
	IconGate iconTreeState;
	GateDescriptor descTreeState;

	// Las asociativas AND
	Gate aAnd2, aAnd3, aAnd4;
	GateDescriptor descAAnd2, descAAnd3, descAAnd4;
	IconGate iconAAnd2, iconAAnd3, iconAAnd4;

	// Las asociativas or
	Gate aOr2, aOr3, aOr4;
	GateDescriptor descAOr2, descAOr3, descAOr4;
	IconGate iconAOr2, iconAOr3, iconAOr4;

	// Diagrama de tiempo
	TimeDiagram diagram;
	GateDescriptor descDiagram;
	IconGate iconDiagram;
	Image imageDiagram;

	// el FFData
	Gate ffdata;
	IconGate iconFFData;
	GateDescriptor descFFData;

	// La plantilla
	Gate template;
	IconGate iconTemplate;
	GateDescriptor descTemplate;

	// La plantilla
	Gate label;
	IconGate iconLabel;
	GateDescriptor descLabel;

	// La imagen

	Image imageNot;
	Image imageTreeState;
	Image imageFFData;
	Image imageAssocAnd2;
	Image imageAssocAnd3;
	Image imageAssocAnd4;
	Image imageAssocOr2;
	Image imageAssocOr3;
	Image imageAssocOr4;
	Image imagePattern;

	/**
	 * CreateAnd constructor comment.
	 */
	public CreateMenu() {
		super();

		// Eventos de ventana
		addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(java.awt.event.WindowEvent e) {
				System.exit(0);
			}
		});

		// ********************************************
		// Comportamiento de las compuertas asociativas
		// ********************************************
		behaviorAnd = new int[3][3];
		behaviorOr = new int[3][3];
		// Llenamos la tabla del And
		behaviorOr[0][0] = -1;
		behaviorOr[0][1] = 0;
		behaviorOr[0][2] = 1;
		behaviorOr[1][0] = 0;
		behaviorOr[1][1] = 0;
		behaviorOr[1][2] = 1;
		behaviorOr[2][0] = 1;
		behaviorOr[2][1] = 1;
		behaviorOr[2][2] = 1;

		// Llenamos la tabla del Or
		behaviorAnd[0][0] = -1;
		behaviorAnd[0][1] = 0;
		behaviorAnd[0][2] = 1;
		behaviorAnd[1][0] = 0;
		behaviorAnd[1][1] = 0;
		behaviorAnd[1][2] = 0;
		behaviorAnd[2][0] = 1;
		behaviorAnd[2][1] = 0;
		behaviorAnd[2][2] = 1;

		// *************************************************************
		// Los parametros
		// *************************************************************
		ParamFFData paramFFData = new ParamFFData(1);
		ParamAssocGate paramAssocAnd2 = new ParamAssocGate(1, 2, behaviorAnd);
		ParamAssocGate paramAssocAnd3 = new ParamAssocGate(1, 3, behaviorAnd);
		ParamAssocGate paramAssocAnd4 = new ParamAssocGate(1, 4, behaviorAnd);
		ParamAssocGate paramAssocOr2 = new ParamAssocGate(1, 2, behaviorOr);
		ParamAssocGate paramAssocOr3 = new ParamAssocGate(1, 3, behaviorOr);
		ParamAssocGate paramAssocOr4 = new ParamAssocGate(1, 4, behaviorOr);
		ParamNot paramNot = new ParamNot(1);
		ParamTreeState paramTreeState = new ParamTreeState(1);
		ParamSwitch paramSwitch = new ParamSwitch();
		ParamLabel paramLabel = new ParamLabel();
		ParamSegmentDisplay paramDisp = new ParamSegmentDisplay(0);

		// Llenamos los descriptores

		descNot = new NotDescriptor((ParamNot) paramNot.clone());
		descTreeState = new TreeStateDescriptor((ParamTreeState) paramTreeState.clone());
		descFFData = new FFDataDescriptor((ParamFFData) paramFFData.clone());
		descDiagram = new TimeDiagramDescriptor();
		descTemplate = new TemplateGateDescriptor();
		descLabel = new LabelDescriptor(paramLabel);

		// Asociativas
		descAAnd2 = new AssocDescriptor((ParamAssocGate) paramAssocAnd2);
		// Un and de 2 entradas
		descAAnd3 = new AssocDescriptor((ParamAssocGate) paramAssocAnd3);
		// Un and de 2 entradas
		descAAnd4 = new AssocDescriptor((ParamAssocGate) paramAssocAnd4);
		// Un and de 2 entradas
		descAOr2 = new AssocDescriptor((ParamAssocGate) paramAssocOr2);
		// Un and de 2 entradas
		descAOr3 = new AssocDescriptor((ParamAssocGate) paramAssocOr3);
		// Un and de 2 entradas
		descAOr4 = new AssocDescriptor((ParamAssocGate) paramAssocOr4);
		// Un and de 2 entradas

		// Las compuertas

		not = new Not(circuit, descNot, paramNot);
		ffdata = new FFData(circuit, descFFData, paramFFData);
		aAnd2 = new GateAssoc(circuit, descAAnd2, paramAssocAnd2);
		aAnd3 = new GateAssoc(circuit, descAAnd3, paramAssocAnd3);
		aAnd4 = new GateAssoc(circuit, descAAnd4, paramAssocAnd4);
		aOr2 = new GateAssoc(circuit, descAOr2, paramAssocOr2);
		aOr3 = new GateAssoc(circuit, descAOr3, paramAssocOr3);
		aOr4 = new GateAssoc(circuit, descAOr4, paramAssocOr4);
		diagram = new TimeDiagram(circuit, descDiagram, new ParamVoid());
		template = new TemplateGate(circuit, descTemplate, new ParamVoid());
		label = new Label(circuit, descLabel, paramLabel);
		treeState = new TreeState(circuit, descTreeState, paramTreeState);

		// Asociamos el icon
		iconNot = new IconGate(not);
		iconTreeState = new IconGate(treeState);
		iconAAnd2 = new IconGate(aAnd2);
		iconAAnd3 = new IconGate(aAnd3);
		iconAAnd4 = new IconGate(aAnd4);
		iconAOr2 = new IconGate(aOr2);
		iconAOr3 = new IconGate(aOr3);
		iconAOr4 = new IconGate(aOr4);
		iconDiagram = new IconGate(diagram);
		iconFFData = new IconGate(ffdata);
		iconTemplate = new IconGateCompilable(template);
		iconLabel = new IconGate(label);

		// Las imagenes

		imageNot = java.awt.Toolkit.getDefaultToolkit().getImage("image/not.png");
		imageTreeState = java.awt.Toolkit.getDefaultToolkit().getImage("image/treestate.png");
		imageAssocAnd2 = java.awt.Toolkit.getDefaultToolkit().getImage("image/Assocand2.gif");
		imageAssocAnd3 = java.awt.Toolkit.getDefaultToolkit().getImage("image/Assocand3.gif");
		imageAssocAnd4 = java.awt.Toolkit.getDefaultToolkit().getImage("image/Assocand4.gif");
		imageAssocOr2 = java.awt.Toolkit.getDefaultToolkit().getImage("image/AssocOr2.gif");
		imageAssocOr3 = java.awt.Toolkit.getDefaultToolkit().getImage("image/AssocOr3.gif");
		imageAssocOr4 = java.awt.Toolkit.getDefaultToolkit().getImage("image/AssocOr4.gif");
		imageDiagram = java.awt.Toolkit.getDefaultToolkit().getImage("image/Diagram.png");
		imageFFData = java.awt.Toolkit.getDefaultToolkit().getImage("image/ffdata.jpg");
		imagePattern = java.awt.Toolkit.getDefaultToolkit().getImage("image/Pattern.gif");

		// Inicializo el circuito
		// circuit.setName("menu.cir");

		// ********************************************************************************
		// Compuertas sin imagenes
		// ********************************************************************************

		// ********************************************************************************
		// El reloj
		// ********************************************************************************
		ParamClk paramClk = new ParamClk(300, 300);
		GateDescriptor descClk = new ClkDescriptor((ParamClk) paramClk.clone());
		Gate clk = new Clk(circuit, descClk, paramClk);
		IconGate iconClk = new IconGate(clk);
		circuit.addIconGate(iconClk, 100, 50);

		// El switch
		GateDescriptor descSwitch = new SwitchDescriptor((ParamSwitch) paramSwitch.clone());
		Gate switch1 = new Switch(circuit, descSwitch, paramSwitch);
		IconGate iconSwitch = new IconGate(switch1);
		circuit.addIconGate(iconSwitch, 300, 50);

		// Una banderita
		GateDescriptor descFlag = new FlagDescriptor();
		Gate flag = new Flag(circuit, descFlag, new ParamVoid());
		IconGate iconFlag = new IconGate(flag);
		circuit.addIconGate(iconFlag, 200, 50); // Superfluo

		// El lector de 7 segmentos
		GateDescriptor descDisp = new SegmentDisplayDescriptor((ParamSegmentDisplay) paramDisp.clone());
		Gate disp = new SegmentDisplay(circuit, descDisp, paramDisp);
		IconGate iconDisp = new IconGate(disp);
		circuit.addIconGate(iconDisp, 250, 50); // Superfluo */

		// Agrego el label
		circuit.addIconGate(iconLabel, 300, 100);

		// El canvas
		canvas = new MiniCanvas();

		// Agregamos las imagenes

		canvas.add(imageNot);
		canvas.add(imageAssocAnd2);
		canvas.add(imageAssocAnd3);
		canvas.add(imageAssocAnd4);
		canvas.add(imageAssocOr2);
		canvas.add(imageAssocOr3);
		canvas.add(imageAssocOr4);
		canvas.add(imageDiagram);
		canvas.add(imageFFData);
		canvas.add(imageTreeState);
		canvas.add(imagePattern);

		// La interfaz
		button.addActionListener(this);
		button2.addActionListener(this);
		getContentPane().add(java.awt.BorderLayout.CENTER, canvas);
		getContentPane().add(java.awt.BorderLayout.EAST, button);
		getContentPane().add(java.awt.BorderLayout.SOUTH, button2);
		pack();
		show();
	}

	/**
	 * Insert the method's description here. Creation date: (20/03/01 18:21:18)
	 * 
	 * @param event
	 *            java.awt.event.ActionEvent
	 */
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == button) {
			createGateFile(iconNot, imageNot);
			createGateFile(iconAAnd2, imageAssocAnd2);
			createGateFile(iconAAnd3, imageAssocAnd3);
			createGateFile(iconAAnd4, imageAssocAnd4);
			createGateFile(iconAOr2, imageAssocOr2);
			createGateFile(iconAOr3, imageAssocOr3);
			createGateFile(iconAOr4, imageAssocOr4);
			createGateFile(iconDiagram, imageDiagram);
			createGateFile(iconFFData, imageFFData);
			createGateFile(iconTemplate, imagePattern);
			createGateFile(iconTreeState, imageTreeState);

			// Los ponemos

			circuit.addIconGate(iconNot, 150, 50);
			circuit.addIconGate(iconAAnd2, 30, 120);
			circuit.addIconGate(iconAAnd3, 80, 120);
			circuit.addIconGate(iconAAnd4, 130, 120);
			circuit.addIconGate(iconAOr2, 30, 170);
			circuit.addIconGate(iconAOr3, 80, 170);
			circuit.addIconGate(iconAOr4, 130, 170);
			circuit.addIconGate(iconDiagram, 170, 170);
			circuit.addIconGate(iconFFData, 200, 170);
			circuit.addIconGate(iconTemplate, 300, 170);
			circuit.addIconGate(iconTreeState, 450, 170);

			// Lo cierro
			// circuit.STATIC_CIRC = true;
			try {
				FileOutputStream out = new FileOutputStream("menu.cir");
				ObjectOutputStream enc = new ObjectOutputStream(out);

				// FileOutputStream out = new FileOutputStream("circuit.xml");
				// XMLEncoder enc = new XMLEncoder(out);

				enc.writeObject(circuit);
				enc.flush();
				enc.close();
				out.close();
			} catch (Exception e) {
				System.out.println(e);
			}
			System.out.println("Ready.");
		} else if (event.getSource() == button2) {
			System.exit(0);
		}
	}

	/**
	 * Insert the method's description here. Creation date: (22/03/01 16:57:58)
	 * 
	 * @param circuit
	 *            circuit.Circuit
	 * @param gate
	 *            csim.Gate
	 * @param image
	 *            java.awt.Image
	 * @param name
	 *            java.lang.String
	 */
	public void createGateFile(IconGate icon, Image image) {
		int width;
		int height;

		// El tamano
		width = image.getWidth(this);
		height = image.getHeight(this);

		// Luego la imprimimos en el BufferedImage
		BufferedImage buffImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		java.awt.Graphics2D gr = (java.awt.Graphics2D) buffImage.getGraphics();
		gr.drawImage(image, 0, 0, null);

		// Ahora recuperamos el rgb
		int[] rgbImage = new int[width * height];
		int z = 0;
		for (int y = 0; y < height; y++)
			for (int x = 0; x < width; x++)
				rgbImage[z++] = buffImage.getRGB(x, y);
		System.out.println(rgbImage.length + " " + z);
		/*
		 * for (int i = 0; i < width * height; i++) { if (i % width == 0)
		 * System.out.println(); System.out.print(rgbImage[i]); }
		 */

		// Ahora creamos su imagen
		cl.alejo.jcsim.csim.gates.IconImage iconImage = new IconImage();
		iconImage.rgbImage = rgbImage;
		iconImage.width = width;
		iconImage.height = height;
		IconGateDescriptor iconGateDesc = (IconGateDescriptor) icon.getGate().getGateDescriptor();
		iconGateDesc.iconImage = iconImage;
	}

	/**
	 * Insert the method's description here. Creation date: (20/03/01 18:11:11)
	 * 
	 * @param args
	 *            java.lang.String[]
	 */
	public static void main(String[] args) {
		new CreateMenu();
	}
}
