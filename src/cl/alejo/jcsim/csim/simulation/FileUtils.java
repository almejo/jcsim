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
package cl.alejo.jcsim.csim.simulation;

import java.io.File;

public class FileUtils {
	public final static String JPEG_EXTENSION = "jpeg";

	public final static String JPG_EXTENSION = "jpg";

	public final static String GIF_EXTENSION = "gif";

	public final static String TIFF_EXTENSION = "tiff";

	public final static String TIF_EXTENSION = "tif";

	public final static String CIRCUIT_EXTENSION = "cir";

	/*
	 * Obtenemos la extenci???n de un archivo
	 */
	public static String getExtension(File file) {
		String ext = "";
		String name = file.getName();
		int i = name.lastIndexOf('.');
		if (i > 0 && i < name.length() - 1) {
			ext = name.substring(i + 1).toLowerCase();
		}
		return ext;
	}
}