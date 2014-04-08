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
package cl.alejo.jcsim.test;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToolBar;

public class TestListeners extends JFrame implements KeyListener {

	public TestListeners() {
		buildButtons();
		getContentPane().add(new JPanel());
		addKeyListener(this);
		pack();
		show();
	}

	public static void main(String[] args) {
		new TestListeners();
	}

	private void buildButtons() {
		JToolBar tlbMain = new JToolBar();
		JPanel panel = new JPanel();
		panel.add(tlbMain);
		getContentPane().add(panel, BorderLayout.NORTH);
		tlbMain.add(new JButton("hol"));
		tlbMain.addSeparator();

	}

	public void keyTyped(KeyEvent e) {
	}

	public void keyPressed(KeyEvent e) {
		System.out.println(e);
	}

	public void keyReleased(KeyEvent e) {
	}
}
