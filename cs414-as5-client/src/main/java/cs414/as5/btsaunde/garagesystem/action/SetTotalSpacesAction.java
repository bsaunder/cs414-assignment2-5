package cs414.as5.btsaunde.garagesystem.action;

import java.awt.event.ActionEvent;
import java.rmi.RemoteException;
import java.util.logging.Logger;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

import cs414.as5.btsaunde.garagesystem.config.KioskConfiguration;
import cs414.as5.btsaunde.garagesystem.enums.GarageStatus;
import cs414.as5.btsaunde.garagesystem.model.Sign;
import cs414.as5.btsaunde.garagesystem.view.DashboardWindow;

/**
 * Action for Updating the Parking Fee.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
public class SetTotalSpacesAction extends AbstractAction {

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
	public void actionPerformed(ActionEvent arg0) {
		try {
			KioskConfiguration config = KioskConfiguration.getInstance();
			Integer newCount = config.getTotalSpaces();

			String message = "Enter New Maximum Space Count";
			String totalSpaceString = JOptionPane.showInputDialog(null,
					message, "Set Total Spaces", JOptionPane.QUESTION_MESSAGE);

			if (totalSpaceString != null && !totalSpaceString.isEmpty()) {
				try {
					newCount = Integer.valueOf(totalSpaceString);
					config.setTotalSpaces(newCount);

					this.logger.info("Total Spaces Set: " + newCount);
					JOptionPane.showMessageDialog(null,
							"Maximum Space Count set to " + newCount,
							"Set Parking Fee Success",
							JOptionPane.INFORMATION_MESSAGE);

					// Update Garage Status
					if (config.getStatus() != GarageStatus.CLOSED) {
						if (config.getAvailableSpaces() < 1) {
							config.setStatus(GarageStatus.FULL);
						} else {
							config.setStatus(GarageStatus.OPEN);
						}
					}

					// Update Sign
					Sign sign = config.getSign();
					sign.setText(config.getStatus().toString());

					// Refresh Dashboard...
					DashboardWindow dashboard = DashboardWindow.getInstance();
					dashboard.update();
				} catch (NumberFormatException nfe) {
					this.logger.warning("Invalid Total Spaces Count Entered");
					JOptionPane
							.showMessageDialog(null,
									"Must Enter a Valid Number.",
									"Set Total Spaces Error",
									JOptionPane.ERROR_MESSAGE);
				}
			} else {
				this.logger.warning("No Parking Fee Entered");
				JOptionPane.showMessageDialog(null, "Must Enter a Number.",
						"Set Total Spaces Error", JOptionPane.ERROR_MESSAGE);
			}
		} catch (RemoteException re) {
			this.logger.warning("Server Error");
			JOptionPane.showMessageDialog(null, "Error Contacting the Server.");
		}

	}

}
