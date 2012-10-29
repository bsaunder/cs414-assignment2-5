/**
 * 
 */
package cs414.as5.btsaunde.garagesystem.action;

import java.awt.event.ActionEvent;
import java.util.logging.Logger;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

import cs414.as5.btsaunde.garagesystem.security.Identity;
import cs414.as5.btsaunde.garagesystem.view.DashboardWindow;

/**
 * Logs out the Current Identity.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
public class LoginAction extends AbstractAction {

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
		this.logger.info("Logging In Attendant");

		String message = "Enter Your PIN";
		String pin = JOptionPane.showInputDialog(null, message, "Login",
				JOptionPane.QUESTION_MESSAGE);

		if (pin != null && !pin.isEmpty()) {
			Identity identity = Identity.getInstance();
			if (!identity.login(pin)) {
				this.logger.warning("Invalid PIN Entered");
				JOptionPane.showMessageDialog(null, "Invalid PIN.",
						"Login Error", JOptionPane.ERROR_MESSAGE);
			} else {
				this.logger.info("Attendant Logged In");
				JOptionPane.showMessageDialog(null, "Attendant Logged In.",
						"Login Success", JOptionPane.INFORMATION_MESSAGE);

				// Refresh Dashboard...
				DashboardWindow dashboard = DashboardWindow.getInstance();
				dashboard.update();
			}
		} else {
			this.logger.warning("No PIN Entered");
			JOptionPane.showMessageDialog(null, "Must Enter a PIN.",
					"Login Error", JOptionPane.ERROR_MESSAGE);
		}
	}

}
