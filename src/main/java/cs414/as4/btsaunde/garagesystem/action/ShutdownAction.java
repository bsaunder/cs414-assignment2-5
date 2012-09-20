/**
 * 
 */
package cs414.as4.btsaunde.garagesystem.action;

import java.awt.event.ActionEvent;
import java.util.logging.Logger;

import javax.swing.AbstractAction;

/**
 * Action to Shutdown the System.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
public class ShutdownAction extends AbstractAction {

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
		this.logger.info("Shutting Down System");
		System.exit(0);
	}

}
