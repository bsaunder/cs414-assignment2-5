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
public class SetParkingFeeAction extends AbstractAction {

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
		Double newFee = config.getParkingFee();

		String message = "Enter New 15 Minute Parking Fee";
		String feeString = JOptionPane.showInputDialog(null, message,
				"Set Parking Fee", JOptionPane.QUESTION_MESSAGE);

		if (feeString != null && !feeString.isEmpty()) {
			try {
				newFee = Double.valueOf(feeString);
				config.setParkingFee(newFee);

				this.logger.info("15m Parking Fee Set: " + newFee);
				JOptionPane.showMessageDialog(null,
						"15 Minute Parking Fee Set to $" + newFee,
						"Set Parking Fee Success",
						JOptionPane.INFORMATION_MESSAGE);

				// Refresh Dashboard...
				DashboardWindow dashboard = DashboardWindow.getInstance();
				dashboard.update();
			} catch (NumberFormatException nfe) {
				this.logger.warning("Invalid Parking Fee Entered");
				JOptionPane.showMessageDialog(null,
						"Must Enter a Valid Parking Fee.",
						"Set Parking Fee Error", JOptionPane.ERROR_MESSAGE);
			}
		} else {
			this.logger.warning("No Parking Fee Entered");
			JOptionPane.showMessageDialog(null, "Must Enter a Parking Fee.",
					"Set Parking Fee Error", JOptionPane.ERROR_MESSAGE);
		}
	}

}
