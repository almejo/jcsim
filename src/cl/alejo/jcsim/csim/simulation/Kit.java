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

/**
 * Procedimientos varios que no se en que clase deberian ir.
 */
public class Kit {
	/**
	 * Genera un excepcion para detener la ejecucion. Se ocupa cuando se detecta
	 * un error y no se sabe como seguir. Exception nunca debe atraparse con un
	 * catch.
	 * 
	 * @param String
	 *            msg Un mensaje explicativo del error.
	 * @param Object
	 *            object El objeto causante del error.
	 */
	public static void Error(String msg, Object object) {
		throw new Error("Application Error: " + msg + "\n" + "fault object: [" + object + "]");
	}
}
