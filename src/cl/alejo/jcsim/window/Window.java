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

// TODO externalizar todos los strings de esta clase
package cl.alejo.jcsim.window;

import cl.alejo.jcsim.csim.circuit.Box;
import cl.alejo.jcsim.csim.circuit.Circuit;
import cl.alejo.jcsim.csim.circuit.SelectionContainer;
import cl.alejo.jcsim.csim.gates.IconGate;
import cl.alejo.jcsim.window.action.*;
import cl.alejo.jcsim.window.states.State;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InvalidClassException;

/**
 * La ventana donde correran los circuitos Creation date: (11/12/00 15:43:32)
 *
 * @author:
 */
public class Window extends JFrame implements ActionListener, ItemListener, AdjustmentListener, ComponentListener,
		MouseListener, MouseMotionListener {

	private static final KeyStroke ACCELERATOR_COPY = KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.CTRL_DOWN_MASK);
	private static final KeyStroke ACCELERATOR_PASTE = KeyStroke.getKeyStroke(KeyEvent.VK_V, KeyEvent.CTRL_DOWN_MASK);
	private static final KeyStroke ACCELERATOR_CUT = KeyStroke.getKeyStroke(KeyEvent.VK_X, KeyEvent.CTRL_DOWN_MASK);
	private static final KeyStroke ACCELERATOR_PAUSE = KeyStroke.getKeyStroke(KeyEvent.VK_P, KeyEvent.CTRL_DOWN_MASK);
	private static final KeyStroke ACCELERATOR_START = KeyStroke.getKeyStroke(KeyEvent.VK_R, KeyEvent.CTRL_DOWN_MASK);
	private static final KeyStroke ACCELERATOR_NEW = KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.ALT_DOWN_MASK);
	private static final KeyStroke ACCELERATOR_CLONE = KeyStroke.getKeyStroke(KeyEvent.VK_Y, KeyEvent.ALT_DOWN_MASK);
	private static final KeyStroke ACCELERATOR_LOAD = KeyStroke.getKeyStroke(KeyEvent.VK_L, KeyEvent.ALT_DOWN_MASK);
	private static final KeyStroke ACCELERATOR_SAVE = KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.ALT_DOWN_MASK);
	private static final KeyStroke ACCELERATOR_SAVE_AS = KeyStroke.getKeyStroke(KeyEvent.VK_Z, KeyEvent.ALT_DOWN_MASK);
	private static final KeyStroke ACCELERATOR_CLOSE_WINDOW = KeyStroke.getKeyStroke(KeyEvent.VK_W,
			KeyEvent.ALT_DOWN_MASK);

	private static final String ICON_DIRECTORY = "icons" + File.separator;
	private static final String CURSOR_DIRECTORY = "cursors" + File.separator;

	private static final Toolkit DEFAULT_TOOLKIT = Toolkit.getDefaultToolkit();
	private static final Dimension DEFAULT_WINDOW_SIZE = new Dimension(600, 600);

	private JMenuBar _mbMain;
	private JMenu _mnFile;
	private JMenuItem _mnNew;
	private JMenuItem _mnCloseCircuit;
	private JMenuItem _mnLoad;
	private JMenuItem _mnSave;
	private JMenuItem _mnSaveAs;
	private JMenuItem _mnQuitAll;
	private JMenu _mnEdit;
	private JMenuItem _mnCut;
	private JMenuItem _mnCopy;
	private JMenuItem _mnPaste;
	private JMenu _mnView;
	private JCheckBoxMenuItem _mnGrid;
	private JMenuItem _mnBgColor;
	private JMenu _mnZoom;
	private JRadioButtonMenuItem _mnZoom1;
	private JRadioButtonMenuItem _mnZoom2;
	private JRadioButtonMenuItem _mnZoom3;
	private JRadioButtonMenuItem _mnZoom4;
	private JRadioButtonMenuItem _mnZoom5;
	private JRadioButtonMenuItem _mnZoom6;
	private JMenu _mnSimulation;
	private JMenuItem _mnPlay;
	private JMenuItem _mnPause;
	private JMenu _mnWindow;
	private JMenuItem _mnNewWindow;
	private JMenuItem _mnCloseWindow;
	private JMenuItem _mnCloneWindow;

	private CircuitCanvas _canvas;

	private JScrollBar _verticalScroll;
	private JScrollBar _horizontalScroll;

	private Circuit _circuit;

	// *************************************************************
	// La ToolBar
	// *************************************************************

	// Las imagenes de los botonoes
	private static final ImageIcon IMG_NEW = new ImageIcon(ICON_DIRECTORY + "new.gif");
	private static final ImageIcon IMG_OPEN = new ImageIcon(ICON_DIRECTORY + "open.gif");
	private static final ImageIcon IMG_SAVE = new ImageIcon(ICON_DIRECTORY + "save.gif");
	private static final ImageIcon IMG_CUT = new ImageIcon(ICON_DIRECTORY + "cut.gif");
	private static final ImageIcon IMG_COPY = new ImageIcon(ICON_DIRECTORY + "copy.gif");
	private static final ImageIcon IMG_PASTE = new ImageIcon(ICON_DIRECTORY + "paste.gif");
	private static final ImageIcon IMG_RUN = new ImageIcon(ICON_DIRECTORY + "run.gif");
	private static final ImageIcon IMG_STOP = new ImageIcon(ICON_DIRECTORY + "stop.gif");

	private final Action NEW_WIN_ACTION = new ActionNewWindow("Nueva",
			"Crear una nueva ventana con un circuito en blanco", this);
	private final Action COPY_ACTION = new ActionCopy("copy", "copia lo que existe en el ?rea de seleccion", IMG_COPY,
			ACCELERATOR_COPY, this);
	private final Action PASTE_ACTION = new ActionPaste("paste", "Pega la ultima copia", IMG_PASTE, ACCELERATOR_PASTE,
			this);
	private final Action CUT_ACTION = new ActionCut("Cut", "Recorta un trozo de circuito", IMG_CUT, ACCELERATOR_CUT,
			this);
	private final Action PAUSE_SIMULATION_ACTION = new ActionPauseSimulation("pause simulation",
			"Pone pausa a la simulacion", IMG_STOP, ACCELERATOR_PAUSE, this);
	private final Action START_SIMULATION_ACTION = new ActionStartSimulation("start simulation",
			"Hace andar la simulacion", IMG_RUN, ACCELERATOR_START, this);
	private final Action NEW_CIRCUIT_ACTION = new ActionNewCircuit("new circuit", "Un nuevo circuito en blanco",
			IMG_NEW, ACCELERATOR_NEW, this);
	private final Action CLONE_WINDOW_ACTION = new ActionCloneWindow("Clone",
			"Crea una copia de la ventana usando el mismo circuito", null, ACCELERATOR_CLONE, this);
	private final Action CLOSE_WINDOW_ACTION = new ActionCloseWindow("Close", "Cierra esta ventana", null,
			ACCELERATOR_CLOSE_WINDOW, this);

	private final Action LOAD_CIRCUIT_ACTION = new ActionLoadCircuit("Load", "Carga un circuito", IMG_OPEN,
			ACCELERATOR_LOAD, this);
	private final Action SAVE_ACTION = new ActionSaveCircuit("Save", "Guarda un circuito", IMG_SAVE, ACCELERATOR_SAVE,
			this);
	private final Action SAVE_AS_ACTION = new ActionSaveAs("Save as...", "Guarda el circuito con un nombre diferente",
			null, ACCELERATOR_SAVE_AS, this);

	// Los botones
	private JButton _buttonNew;
	private JButton _buttonOpen;
	private JButton _buttonSave;
	private JButton _buttonCut;
	private JButton _buttonCopy;
	private JButton _buttonPaste;
	private JButton _buttonStart;
	private JButton _buttonStop;

	static State state = State.STATE_INIT;
	private IconGate iconTarget = null;
	public int xi = 0;
	public int yi = 0;

	private Cursor _cursorWrenchOpen;
	private Cursor _cursorWrenchClose;
	private Cursor _cursorWrenchCloseConnection;
	private Cursor _cursorWrenchCloseClone;
	private Cursor _cursorWrenchCloseMove;
	private Cursor _cursorWrenchOpenDelete;
	private Cursor _cursorWrenchCloseDelete;

	// *************************************************************
	// Variables para el manejo de archivos
	// *************************************************************
	private JFileChooser fileChooser;

	static final int OK = 0;
	static final int CANCEL = 1;
	static final int NO = 3;
	static final int ERROR = 2;

	private int recursivo = -1;

	public Window(Circuit circuit) {
		super();
		if (circuit != null) {
			_circuit = circuit;
			circuit.addWindow(this);
			setTitle(circuit.getName());
		}
		buildInterface();
	}

	public void actionPerformed(ActionEvent ev) {
		try {
			JMenuItem mnItem = (JMenuItem) ev.getSource();
			if (mnItem == _mnQuitAll)
				System.exit(0);

			// ******************************************************
			// El menu de view
			// ******************************************************
			if (mnItem == _mnGrid) {
				_canvas.setGrid(_mnGrid.isSelected());
			}
			if (mnItem == _mnBgColor) {
				_canvas.setBgcolor();
			}

			// ******************************************************
			// El menu de zoom
			// ******************************************************

			if (mnItem == _mnZoom1) {
				setZoom(0.25);
			}
			if (mnItem == _mnZoom2) {
				setZoom(0.5);
			}
			if (mnItem == _mnZoom3) {
				setZoom(1.0);
			}
			if (mnItem == _mnZoom4) {
				setZoom(1.5);
			}
			if (mnItem == _mnZoom5) {
				setZoom(2.0);
			}
			if (mnItem == _mnZoom6) {
				setZoom(4.0);
			}

			refreshScrollbars();
		} catch (Exception e) {
			System.out.println("action performed:" + e);
		}
	}

	/**
	 * Creation date: (12/01/01 21:33:49)
	 *
	 * @param ev java.awt.event.AdjustmentEvent
	 */
	public void adjustmentValueChanged(AdjustmentEvent ev) {
		System.out.println("Adjustement changued: " + ev.getAdjustmentType());
		switch (ev.getAdjustmentType()) {
			case AdjustmentEvent.UNIT_INCREMENT:
			case AdjustmentEvent.UNIT_DECREMENT:
			case AdjustmentEvent.BLOCK_INCREMENT:
			case AdjustmentEvent.BLOCK_DECREMENT:
			case AdjustmentEvent.TRACK:
				int hValue = _horizontalScroll.getValue();
				int vValue = _verticalScroll.getValue();
				// canvas.setViewportCorner(hValue, vValue);
				setViewportPosition(hValue, vValue);
		}
		// Refrescamos las barras
		refreshScrollbars();
	}

	/**
	 * Un mensage de alerta
	 *
	 * @param msg   java.lang.String el mensage
	 * @param title java.langStrin el titulo de la ventana
	 */
	private void alert(String msg, String title) {
		JOptionPane.showConfirmDialog(this, msg, title, JOptionPane.OK_OPTION);
	}

	/**
	 * Construye la barra de herramientas Creation date: (22/03/01 15:32:57)
	 */
	private void buildButtons() {
		_buttonNew = newToolbarButton(NEW_CIRCUIT_ACTION);
		_buttonOpen = newToolbarButton(LOAD_CIRCUIT_ACTION);
		_buttonSave = newToolbarButton(SAVE_ACTION);
		_buttonCut = newToolbarButton(CUT_ACTION);
		_buttonCopy = newToolbarButton(COPY_ACTION);
		_buttonPaste = newToolbarButton(PASTE_ACTION);
		_buttonStart = newToolbarButton(START_SIMULATION_ACTION);
		_buttonStop = newToolbarButton(PAUSE_SIMULATION_ACTION);

		START_SIMULATION_ACTION.setEnabled(false);
		CUT_ACTION.setEnabled(false);

		JToolBar tlbMain = new JToolBar();
		getContentPane().add(tlbMain, BorderLayout.NORTH);
		tlbMain.add(_buttonNew);
		tlbMain.add(_buttonOpen);
		tlbMain.add(_buttonSave);
		tlbMain.addSeparator();
		tlbMain.add(_buttonCut);
		tlbMain.add(_buttonCopy);
		tlbMain.add(_buttonPaste);
		tlbMain.addSeparator();
		tlbMain.add(_buttonStart);
		tlbMain.add(_buttonStop);
		tlbMain.addSeparator();

	}

	private JButton newToolbarButton(Action action) {
		JButton _buttonNew = new JButton();
		_buttonNew.setAction(action);
		_buttonNew.setText("");
		return _buttonNew;
	}

	private void buildCursors() {

		_cursorWrenchOpen = newCursor(CURSOR_DIRECTORY + "cursorWrenchOpen.gif", "wrenchOpen", 12, 0);
		_cursorWrenchClose = newCursor(CURSOR_DIRECTORY + "cursorWrenchClose.gif", "wrenchClose", 8, 0);
		_cursorWrenchCloseClone = newCursor(CURSOR_DIRECTORY + "cursorWrenchCloseClone.gif", "wrenchClone", 8, 0);
		_cursorWrenchCloseConnection = newCursor(CURSOR_DIRECTORY + "cursorWrenchCloseConnect.gif", "wrenchConnect", 8, 0);
		_cursorWrenchCloseMove = newCursor(CURSOR_DIRECTORY + "cursorWrenchCloseMove.gif", "wrenchMove", 8, 0);
		_cursorWrenchCloseDelete = newCursor(CURSOR_DIRECTORY + "cursorWrenchCloseDelete.gif", "wrenchCloseDelete", 8, 0);
		_cursorWrenchOpenDelete = newCursor(CURSOR_DIRECTORY + "cursorWrenchOpenDelete.gif", "wrenchOpenDelete", 8, 0);

	}

	private Cursor newCursor(String filename, String name, int x, int y) {
		return DEFAULT_TOOLKIT.createCustomCursor(DEFAULT_TOOLKIT.getImage(filename), new Point(x, y), name);
	}

	private void buildFileChooser() {
		fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File("."));
		fileChooser.setFileFilter(new CircuitFilter());
	}


	private void buildInterface() {

		// setIconImage(java.awt.Toolkit.getDefaultToolkit().getImage("icons/jcsim.png"));
		buildMenus();
		buildButtons();
		buildScrollbars();
		buildFileChooser();
		buildCursors();
		setSize(DEFAULT_WINDOW_SIZE);
		setCursor(_cursorWrenchOpen);
		builCanvas();
		addComponentListener(this);

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				closeWindow();
			}
		});
	}

	private void builCanvas() {
		_canvas = new CircuitCanvas(_circuit, this);
		getContentPane().add(_canvas, BorderLayout.CENTER);
	}

	/**
	 * Construye los menus de la ventana Creation date: (22/03/01 15:33:07)
	 */
	private void buildMenus() {
		_mbMain = new JMenuBar();
		setJMenuBar(_mbMain);
		// TODO falta hacer lo mismo con el resto
		_mnFile = newMenu("File", KeyEvent.VK_F, _mbMain);
		_mnNew = newMenuItem(NEW_CIRCUIT_ACTION, _mnFile);
		_mnLoad = newMenuItem(LOAD_CIRCUIT_ACTION, _mnFile);
		_mnSave = newMenuItem(SAVE_ACTION, _mnFile);
		_mnSaveAs = newMenuItem(SAVE_AS_ACTION, _mnFile);

		_mnFile.addSeparator();

		_mnCloseCircuit = newMenuItem(CLOSE_WINDOW_ACTION, _mnFile);

		_mnQuitAll = new JMenuItem("Quit");
		_mnQuitAll.addActionListener(this);
		_mnFile.add(_mnQuitAll);

		_mnEdit = newMenu("Edit", KeyEvent.VK_E, _mbMain);

		_mnCut = newMenuItem(CUT_ACTION, _mnEdit);
		_mnCopy = newMenuItem(COPY_ACTION, _mnEdit);
		_mnPaste = newMenuItem(PASTE_ACTION, _mnEdit);

		_mnView = newMenu("View", KeyEvent.VK_V, _mbMain);

		_mnGrid = new JCheckBoxMenuItem("Grid");
		_mnGrid.setSelected(false);
		_mnGrid.addActionListener(this);
		_mnView.add(_mnGrid);
		_mnBgColor = new JMenuItem("Background color...");
		_mnBgColor.addActionListener(this);
		_mnView.add(_mnBgColor);

		_mnZoom = newMenu("Zoom", KeyEvent.VK_Z, _mbMain);

		_mnZoom1 = new JRadioButtonMenuItem("25%");
		_mnZoom1.addActionListener(this);
		_mnZoom.add(_mnZoom1);
		_mnZoom2 = new JRadioButtonMenuItem("50%");
		_mnZoom2.addActionListener(this);
		_mnZoom.add(_mnZoom2);
		_mnZoom3 = new JRadioButtonMenuItem("100%");
		_mnZoom3.setSelected(true);
		_mnZoom3.addActionListener(this);
		_mnZoom.add(_mnZoom3);
		_mnZoom4 = new JRadioButtonMenuItem("150");
		_mnZoom4.addActionListener(this);
		_mnZoom.add(_mnZoom4);
		_mnZoom5 = new JRadioButtonMenuItem("200");
		_mnZoom5.addActionListener(this);
		_mnZoom.add(_mnZoom5);
		_mnZoom6 = new JRadioButtonMenuItem("400");
		_mnZoom6.addActionListener(this);

		ButtonGroup btGroup = new ButtonGroup();
		btGroup.add(_mnZoom1);
		btGroup.add(_mnZoom2);
		btGroup.add(_mnZoom3);
		btGroup.add(_mnZoom4);
		btGroup.add(_mnZoom5);
		btGroup.add(_mnZoom6);
		_mnZoom.add(_mnZoom6);

		_mnSimulation = newMenu("Simulation", KeyEvent.VK_R, _mbMain);

		_mnPlay = newMenuItem(START_SIMULATION_ACTION, _mnSimulation);
		_mnPause = newMenuItem(PAUSE_SIMULATION_ACTION, _mnSimulation);

		_mnWindow = newMenu("Window", KeyEvent.VK_W, _mbMain);

		_mnNewWindow = newMenuItem(NEW_WIN_ACTION, _mnWindow);
		_mnCloneWindow = newMenuItem(CLONE_WINDOW_ACTION, _mnWindow);
		_mnCloseWindow = newMenuItem(CLOSE_WINDOW_ACTION, _mnWindow);
	}

	private JMenu newMenu(String title, int mnemonic, JMenuBar menuBar) {
		JMenu menu = new JMenu(title);
		menu.setMnemonic(mnemonic);
		menuBar.add(menu);
		return menu;
	}

	private JMenuItem newMenuItem(Action action, JMenu menu) {
		JMenuItem menuItem = new JMenuItem();
		menuItem.setAction(action);
		menu.add(menuItem);
		return menuItem;
	}

	/**
	 * Creamos las barras de scroll Creation date: (22/03/01 16:40:35)
	 */
	public void buildScrollbars() {
		// Agregamos las barras de scroll;
		_verticalScroll = new JScrollBar(JScrollBar.VERTICAL);
		_horizontalScroll = new JScrollBar(JScrollBar.HORIZONTAL);

		/*
		 * // Le ponemos los bordes scrHorizontal.setMinimum(0);
		 * scrHorizontal.setMaximum(1000); scrVertical.setMinimum(0);
		 * scrVertical.setMaximum(1000);
		 */
		// Los listener
		_horizontalScroll.addAdjustmentListener(this);
		_verticalScroll.addAdjustmentListener(this);

		// Los ponemos en la ventana
		getContentPane().add(_verticalScroll, BorderLayout.EAST);
		getContentPane().add(_horizontalScroll, BorderLayout.SOUTH);
	}

	public int closeCircuit() {
		if (_circuit != null) {
			if (_circuit.isModified()) {
				int n = askToReplace();
				if (n == JOptionPane.YES_OPTION) {
					return saveAs();
				} else if (n == JOptionPane.CANCEL_OPTION) {
					return CANCEL;
				}
			}
			_circuit.removeWindow(this);
			setCircuit(null);
		}
		repaint();
		return OK;
	}

	private int askToReplace() {
		return JOptionPane.showConfirmDialog(this, "Circuit is not saved. Save it?", "Load...", JOptionPane.YES_NO_CANCEL_OPTION);
	}

	public void closeWindow() {
		if (closeCircuit() != CANCEL) {
			dispose();
		}
	}

	public void componentHidden(ComponentEvent ev) {
	}

	public void componentMoved(ComponentEvent ev) {

	}

	public void componentResized(ComponentEvent ev) {
		Object target = ev.getComponent();

		if (target == _canvas) {
			_canvas.setViewportWidth(_canvas.getWidth());
			_canvas.setViewportHeight(_canvas.getHeight());

			if (_canvas.showGrid) {
				_canvas.calculateGrid();
			}
		}

		if (_verticalScroll != null && _horizontalScroll != null) {
			refreshScrollbars();
		}
	}

	public void componentShown(ComponentEvent ev) {
		try {
			refreshScrollbars();
		} catch (Exception e) {
			System.out.println("componentShown:" + e);
		}
	}

	public Dimension getPreferredSize() {
		return new Dimension(500, 500);
	}

	public void init() {
		setBackground(Color.white);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}

	public void itemStateChanged(ItemEvent ev) {
	}

	public void cloneWindow() {
		if (getCircuit() == null) {
			JOptionPane.showMessageDialog(this, "There's no circuit to clone window.", "Erro",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		Window window = new Window(getCircuit());
		getCircuit().addWindow(window);
		window.pack();
		window.show();
	}

	/**
	 * Crea una nueva ventana con un circuito en blanco
	 * Creation date: (25/03/01
	 * 20:25:05)
	 */
	public void newWindow() {
		Circuit circuit = new Circuit();
		Window window = new Window(circuit);
		circuit.addWindow(window);
		circuit.startTimer();
		window.pack();
		window.show();
	}

	/**
	 * Carga un circuito en memoria Creation date: (25/03/01 20:25:05)
	 */
	public void load() {
		try {
			if (_circuit != null && _circuit.isModified()) {
				int n = askToReplace();
				if (n == JOptionPane.YES_OPTION) {
					saveAs();
				} else if (n == JOptionPane.CANCEL_OPTION) {
					return;
				}
			}
			int returnVal = fileChooser.showOpenDialog(this);

			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = fileChooser.getSelectedFile();
				setCircuit(Circuit.load(file.getPath()));
				startRepaint();
				System.out.println("Loaded: " + file.getName() + ".");
			} else {
				System.out.println("load cancelled by user.");
			}
		} catch (FileNotFoundException e) {
			alert("File not found. ", "Error");
		} catch (InvalidClassException e) {
			alert("El archivo no es compatible con la version actual de JCSim", "Error");
		} catch (Exception e) {
			System.out.println("Otra " + e);
		}
		refreshScrollbars();
	}

	public void mouseClicked(MouseEvent event) {
		if (event.getClickCount() >= 2) {
			state = state.mouseDoubleClick(this, event);
		} else {
			state = state.mouseClick(this, event);
		}
	}

	public void mouseDragged(MouseEvent ev) {
		state = state.drag(this, ev);
	}

	public void mouseEntered(MouseEvent event) {
		state = state.mouseEntered(this, event);
	}

	public void mouseExited(MouseEvent event) {
		state = state.mouseExited(this, event);
	}

	public void mouseMoved(MouseEvent event) {
		state = state.mouseMove(this, event);
	}

	public void mousePressed(MouseEvent event) {
		CircuitCanvas canvas = (CircuitCanvas) event.getSource();
		if (canvas == _canvas) {
			state = state.mouseDown(this, event);
		}
	}

	public void mouseReleased(MouseEvent event) {
		if (event.getSource() == _canvas) {
			state = state.mouseUp(this, event);
		}
	}

	/**
	 * Crea una nueva ventana con un circuito vacio
	 * Creation date: (08/04/01 16:55:13)
	 */
	private void newWin() {
		Circuit circuit = new Circuit();

		Window window = new Window(circuit);
		circuit.addWindow(window);
		circuit.startTimer();
		window.pack();
		window.show();
	}

	public void refreshScrollbars() {
		try {
			recursivo++;

			// System.out.println("Entering RefreshScrollBar: recursive level-------> "
			// + recursivo);
			if (recursivo > 0) {
				recursivo--;
				// System.out.println(
				// "Exiting RefreshScrollBar: recursive level-------> " +
				// recursivo);
				return;
			}
			// El calculo de proporciones
			Box boxViewport = new Box(_canvas.getViewport());
			boxViewport.scale(1.0 / _canvas.zoom);
			Box boxWorld = new Box(boxViewport);
			Box boxExtent;

			// El extent se calcula solo si hay circuito
			if (_circuit != null) {
				boxExtent = _circuit.getExtent();
			} else {
				boxExtent = new Box(0, 0, 0, 0);
			}

			// SI el circuito no esta vacio
			if (boxExtent.getXi() < boxExtent.getXf()) {
				boxWorld.extend(boxExtent);
			}

			// Calculamos todo en vertical
			int worldYMin = boxWorld.getYi();
			int worldYMax = boxWorld.getYf();
			int valueY = boxViewport.getYi();
			int visibleAmountY = boxViewport.getHeight();

			// En horizontal
			int worldXMin = boxWorld.getXi();
			int worldXMax = boxWorld.getXf();
			int valueX = boxViewport.getXi();
			int visibleAmountX = boxViewport.getWidth();

			// No me acuerdo
			int blockY = 0;
			int blockX = 0;

			// Un poco de debug
			// System.out.println("Extent:" + boxExtent);
			// System.out.println("Viewport:" + boxViewport);
			// System.out.println("world:" + boxWorld);
			// System.out.println("Max-min:" + (worldXMax - worldXMin));
			// System.out.println("Max:" + worldXMax);
			// System.out.println("Min:" + worldXMin);
			// System.out.println("VisibleAmoutn:" + visibleAmountX); // */
			// System.out.println("--------------------->value:" +
			// _horizontalScroll.getValue());
			// */

			// Cambiamos
			_verticalScroll.setMaximum(worldYMax);
			_verticalScroll.setMinimum(worldYMin);
			_horizontalScroll.setMaximum(worldXMax);
			_horizontalScroll.setMinimum(worldXMin);

			// Las areas visibles
			_horizontalScroll.setVisibleAmount(visibleAmountX);
			_verticalScroll.setVisibleAmount(visibleAmountY);

			// ahora las posiciones

			_horizontalScroll.setValue(valueX);
			_verticalScroll.setValue(valueY);

			// *****************************************
			// seteamos el blockincrement
			// *****************************************
			// Si podemos avanzar varios pasos o no
			// Horizontal
			if (boxWorld.getWidth() >= (2 * visibleAmountX)) {
				blockX = visibleAmountX;
			} else {
				blockX = (boxWorld.getWidth() / 2);
			}
			// Seteamos
			_horizontalScroll.setBlockIncrement(blockX);

			// Vertical
			if (boxWorld.getHeight() >= (2 * visibleAmountY)) {
				blockY = visibleAmountY;
			} else {
				blockY = (boxWorld.getHeight() / 2);
			}
			// Seteamos
			_verticalScroll.setBlockIncrement(blockY);
			// System.out.println("Exiting RefreshScrollBar: recursive level-------> "
			// + recursivo);

			// Salimos de la recursion
			recursivo--;
		} catch (Exception e) {
			// System.out.println("RecalculateScrollbars::" + e);
		}
	}

	/**
	 * El usuario a elegido grabar el circuito con el nombre actual
	 * Creation date: (25/03/01 20:24:35)
	 */
	public int save() {
		System.out.println("saving: " + _circuit.getName() + ".");
		try {
			int answer;
			if (_circuit != null) {
				if (_circuit.getName() == null) {
					answer = fileChooser.showSaveDialog(this);
					if (answer == JFileChooser.APPROVE_OPTION) {
						File file = fileChooser.getSelectedFile();

						answer = JOptionPane.YES_OPTION;
						if (file.exists()) {
							answer = JOptionPane.showConfirmDialog(this, "Circuit already exist. Overwrite anyway?", "Save as...", JOptionPane.YES_NO_OPTION);
						}
						if (answer == JOptionPane.YES_OPTION) {
							System.out.println("saving: " + file.getName() + ".");
							_circuit.setName(file.getName());
							setTitle(file.getName());
							_circuit.save(file.getPath());
							System.out.println("saved: " + file.getName() + ".");
						} else if (answer == JOptionPane.CANCEL_OPTION) {
							return CANCEL;
						} else {
							return NO;
						}
					}
				}
			}
			_circuit.save(_circuit.getName());
		} catch (IOException e) {
			System.out.println("error saving circuit " + _circuit.getName() + ". " + e);
			JOptionPane.showMessageDialog(this, "Error Saving circuit.", "Error", JOptionPane.ERROR_MESSAGE);
		}
		return OK;
	}

	/**
	 * Grabamos con otro nombre Creation date: (25/03/01 20:24:48)
	 */
	public int saveAs() {
		try {
			int returnVal = fileChooser.showSaveDialog(this);

			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = fileChooser.getSelectedFile();

				int option = JOptionPane.YES_OPTION;
				if (file.exists()) {
					option = JOptionPane.showConfirmDialog(this, "Circuit already exist. Overwrite anyway?", "Save as...",
							JOptionPane.YES_NO_OPTION);
				}
				if (option == JOptionPane.YES_OPTION) {
					System.out.println("saving: " + file.getName() + ".");
					_circuit.setName(file.getName());
					setTitle(file.getName());
					_circuit.save(file.getPath());
					System.out.println("saved: " + file.getName() + ".");
					_circuit.actualizeWindowsTitles();
				}
			} else {
				System.out.println("save cancelled by user.");
				return CANCEL;
			}
		} catch (IOException e) {
			alert("Error Saving circuit.", "Error");
		}
		return OK;
	}

	/**
	 * Asignamos un circuito a esta ventana y al canvas
	 * Creation date: (08/04/01 16:52:00)
	 *
	 * @param circuit circuit.Circuit El nuevo circuiot de la ventana
	 */
	public void setCircuit(Circuit circuit) {
		if (_circuit != null) {
			_circuit.removeWindow(this);
		}
		if (circuit != null) {
			circuit.removeWindow(this);
		}

		_circuit = circuit;
		_canvas.setCircuit(circuit);

		if (circuit != null)
			setTitle(circuit.getName());

		if (circuit != null)
			circuit.addWindow(this);
	}

	public void setCloneCursor() {
		_canvas.setCursor(_cursorWrenchCloseClone);
	}

	public void setCloseDeleteCursor() {
		_canvas.setCursor(_cursorWrenchCloseDelete);
	}

	public void setConnectCursor() {
		_canvas.setCursor(_cursorWrenchCloseConnection);
	}

	public void setDraggingCursor() {
		_canvas.setCursor(_cursorWrenchClose);
	}

	public void setMoveCursor() {
		_canvas.setCursor(_cursorWrenchCloseMove);
	}

	public void setNormalCursor() {
		_canvas.setCursor(_cursorWrenchOpen);
	}

	public void setOpenDeleteCursor() {
		// Cambio el cursor
		_canvas.setCursor(_cursorWrenchOpenDelete);
	}

	public void setTitle(String title) {
		super.setTitle(title == null ? "untitled.cir" : title);
	}

	public void setViewportPosition(int hValue, int vValue) {
		Box boxViewport = new Box(_canvas.getViewport());
		boxViewport.scale(1.0 / _canvas.zoom);
		Box boxWorld = new Box(boxViewport);
		Box boxExtent;

		if (_circuit != null) {
			boxExtent = _circuit.getExtent();
		} else {
			boxExtent = new Box(0, 0, 0, 0);
		}

		if (boxExtent.getXi() < boxExtent.getXf()) {
			boxWorld.extend(boxExtent);
		}

		_canvas.setViewportCorner(hValue, vValue);
	}

	/**
	 * Cambia el zoom del circuito
	 * Creation date: (06/04/01 12:17:10)
	 *
	 * @param zoom double
	 */
	public void setZoom(double zoom) {
		_canvas.setZoom(zoom);
	}

	public void startRepaint() {
		if (_circuit != null) {
			_circuit.startTimer();
		}
	}

	/**
	 * Echa a andar la simulacion del circuito
	 * Creation date: (05/06/01 18:19:20)
	 */
	public void playSimulation() {
		if (_circuit != null) {
			_circuit.startSimulation();
		}

		invertPausePlay();
	}

	private void invertPausePlay() {
		START_SIMULATION_ACTION.setEnabled(!START_SIMULATION_ACTION.isEnabled());
		PAUSE_SIMULATION_ACTION.setEnabled(!PAUSE_SIMULATION_ACTION.isEnabled());
	}

	/**
	 * Echa a andar la simulacion del circuito
	 * Creation date: (05/06/01 18:19:20)
	 */
	public void pauseSimulation() {
		if (_circuit != null) {
			_circuit.stopSimulation();
		}

		invertPausePlay();
	}

	public Circuit getCircuit() {
		return _circuit;
	}

	public CircuitCanvas getCanvas() {
		return _canvas;
	}

	public IconGate getIconTarget() {
		return iconTarget;
	}

	public void setIconTarget(IconGate gate) {
		iconTarget = gate;
	}

	public String getApplicationName() {
		return getName();
	}

	public JMenu getFileMenu() {
		return _mnFile;
	}

	public JFrame getFrame() {
		return this;
	}

	public void loadFile(String pathname) {
	}

	public void setSelectionTarget(SelectionContainer container) {
		_circuit.setSelectionTarget(container);
	}
}
