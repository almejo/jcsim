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
package cl.alejo.jcsim.window;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class MenuText {

	private static final String BUNDLE_NAME = "cl.alejo.jcsim.window.MenuText"; //$NON-NLS-1$

	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);

	private MenuText() {
	}

	private static String getString(String key) {
		try {
			return RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException e) {
			return '!' + key + '!';
		}
	}

	public static String fileNewWindow() {
		return getString("file.newWindow");
	}

	public static String fileNewWindowToolTip() {
		return getString("file.newWindow");
	}
}
