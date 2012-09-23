package cs414.as4.btsaunde.garagesystem.action;

import java.awt.event.ActionEvent;
import java.util.logging.Logger;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

import cs414.as4.btsaunde.garagesystem.config.GarageConfiguration;
import cs414.as4.btsaunde.garagesystem.view.DashboardWindow;

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
		GarageConfiguration config = GarageConfiguration.getInstance();
		Integer newCount = config.getTotalSpaces();

		String message = "Enter New Maximum Space Count";
		String totalSpaceString = JOptionPane.showInputDialog(null, message,
				"Set Total Spaces", JOptionPane.QUESTION_MESSAGE);

		if (totalSpaceString != null && !totalSpaceString.isEmpty()) {
			try {
				newCount = Integer.valueOf(totalSpaceString);
				config.setTotalSpaces(newCount);

				this.logger.info("Total Spaces Set: " + newCount);
				JOptionPane.showMessageDialog(null, "Maximum Space Count set to " + newCount,
						"Set Parking Fee Success", JOptionPane.INFORMATION_MESSAGE);

				// Refresh Dashboard...
				DashboardWindow dashboard = DashboardWindow.getInstance();
				dashboard.refreshFromConfig();
			} catch (NumberFormatException nfe) {
				this.logger.warning("Invalid Total Spaces Count Entered");
				JOptionPane.showMessageDialog(null,
						"Must Enter a Valid Number.", "Set Total Spaces Error",
						JOptionPane.ERROR_MESSAGE);
			}
		} else {
			this.logger.warning("No Parking Fee Entered");
			JOptionPane.showMessageDialog(null, "Must Enter a Number.",
					"Set Total Spaces Error", JOptionPane.ERROR_MESSAGE);
		}
	}

}
