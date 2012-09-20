package cs414.as4.btsaunde.garagesystem.action;

import java.awt.event.ActionEvent;
import java.util.logging.Logger;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

import cs414.as4.btsaunde.garagesystem.model.Attendant;
import cs414.as4.btsaunde.garagesystem.security.Identity;

public class SummonAttendantAction extends AbstractAction {

	/**
	 * default Serial ID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Garage Attendant.
	 */
	private Attendant attendant;

	/**
	 * Logger
	 */
	private Logger logger = Logger.getAnonymousLogger();

	/**
	 * Creates Summon Attendant Action
	 * 
	 * @param attendant
	 *            Attendant
	 */
	public SummonAttendantAction() {
		Identity identity = Identity.getInstance();
		this.attendant = identity.getAttendant();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent ae) {
		this.logger.info("Summoning Attendant");
		String message = "Today's Attendant " + this.attendant.getName()
				+ " Has Been Summoned To this Kiosk.";
		JOptionPane.showMessageDialog(null, message, "Summon Attendant",
				JOptionPane.WARNING_MESSAGE);
	}

}
