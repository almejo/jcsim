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

import cl.alejo.jcsim.csim.simulation.FileUtils;

import javax.swing.filechooser.FileFilter;
import java.io.File;

public class CircuitFilter extends FileFilter {
	/**
	 * CircuitFilter constructor comment.
	 */
	public CircuitFilter() {
		super();
	}

	/**
	 * Metodo para ver si un archivo se muestra en la caja de dialogo
	 */
	public boolean accept(File f) {

		// Buscamos la extension
		String extension = FileUtils.getExtension(f);

		// Si es un archivo de circuito, lo mostramos
		return f.isDirectory() || extension.equals(FileUtils.CIRCUIT_EXTENSION);
	}

	/**
	 * getDescription method comment.
	 */
	public String getDescription() {
		return "Archivos de JCSim (*.cir)";
	}
}
