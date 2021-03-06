/**
 * 
 */
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
 * Changes the Status of the Garage.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
public class ChangeGarageStatusAction extends AbstractAction {

	/**
	 * Default Serial ID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Logger
	 */
	private Logger logger = Logger.getAnonymousLogger();

	/**
	 * New Garage Status
	 */
	private GarageStatus status;

	/**
	 * Creates a new Action Object.
	 * 
	 * @param newStatus
	 *            Status to Set the Garage to when the Action Executes
	 */
	public ChangeGarageStatusAction(GarageStatus newStatus) {
		this.status = newStatus;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent arg0) {
		try{
			KioskConfiguration config = KioskConfiguration.getInstance();
			config.setStatus(this.status);

			this.logger.info("Garage Status Set To: " + this.status);
			
			// Update Sign
			Sign sign = config.getSign();
			sign.setText(config.getStatus().toString());

			// Refresh Dashboard...
			DashboardWindow dashboard = DashboardWindow.getInstance();
			dashboard.update();
		}catch(RemoteException re){
			this.logger.warning("Server Error");
			JOptionPane.showMessageDialog(null, "Error Contacting the Server.");
		}
	}

}
