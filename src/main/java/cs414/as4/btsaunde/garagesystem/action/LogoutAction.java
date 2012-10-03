/**
 * 
 */
package cs414.as4.btsaunde.garagesystem.action;

import java.awt.event.ActionEvent;
import java.util.logging.Logger;

import javax.swing.AbstractAction;

import cs414.as4.btsaunde.garagesystem.security.Identity;
import cs414.as4.btsaunde.garagesystem.view.DashboardWindow;

/**
 * Logs out the Current Identity.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
public class LogoutAction extends AbstractAction {

	/**
	 * Default Serial ID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Logger
	 */
	private Logger logger = Logger.getAnonymousLogger();

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent ae) {
		this.logger.info("Logging Out Attendant");
		Identity identity = Identity.getInstance();
		identity.logout();

		// Refresh Dashboard...
		DashboardWindow dashboard = DashboardWindow.getInstance();
		dashboard.update();
	}

}
