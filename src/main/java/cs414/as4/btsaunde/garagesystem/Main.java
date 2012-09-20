package cs414.as4.btsaunde.garagesystem;

import java.util.logging.Logger;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import cs414.as4.btsaunde.garagesystem.config.GarageConfiguration;
import cs414.as4.btsaunde.garagesystem.enums.GarageStatus;
import cs414.as4.btsaunde.garagesystem.model.Attendant;
import cs414.as4.btsaunde.garagesystem.security.Identity;
import cs414.as4.btsaunde.garagesystem.view.Kiosk;

/**
 * Main Class that Starts the Application.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
public class Main {

	/**
	 * Logger
	 */
	private static Logger logger = Logger.getAnonymousLogger();

	/**
	 * Starts the Application.
	 * 
	 * @param args
	 *            Command Line Arguments
	 */
	public static void main(String[] args) {
		Main.logger.info("Starting Saunders Parking System");

		Main.setLookAndFeel();

		// Load Attendant Data
		Main.logger.info("Loading Attendant...");
		Attendant attendant = new Attendant("Fred");
		attendant.setPin(1234);

		Identity.getNewInstance(attendant);

		// Set Initial GarageConfiguration
		GarageConfiguration config = GarageConfiguration.getInstance();
		config.setStatus(GarageStatus.OPEN);
		config.setTotalSpaces(1);
		config.setParkingFee(1.00);

		Main.logger.info("Attendant Loaded: " + attendant.getName()
				+ " (PIN - " + attendant.getPin() + ")");

		Kiosk garageKiosk = Kiosk.getInstance();
		garageKiosk.setVisible(true);
	}

	/**
	 * Sets the System Look and Feel.
	 */
	private static void setLookAndFeel() {
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (Exception e) {
		    // If Nimbus is not available, fall back to cross-platform
		    try {
		        UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		    } catch (Exception ex) {
		        // Ignore It
		    }
		}
	}

}
