package cs414.as5.btsaunde.garagesystem.action;

import java.awt.event.ActionEvent;
import java.util.logging.Logger;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

import cs414.as5.btsaunde.garagesystem.model.Attendant;
import cs414.as5.btsaunde.garagesystem.security.Identity;

public class SummonAttendantAction extends AbstractAction {

	/**
	 * default Serial ID.
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
		this.logger.info("Summoning Attendant");
		Identity identity = Identity.getInstance();
		Attendant attendant = identity.getAttendant();

		String message = "Today's Attendant " + attendant.getName()
				+ " Has Been Summoned To this Kiosk.";
		JOptionPane.showMessageDialog(null, message, "Summon Attendant",
				JOptionPane.WARNING_MESSAGE);
	}

}