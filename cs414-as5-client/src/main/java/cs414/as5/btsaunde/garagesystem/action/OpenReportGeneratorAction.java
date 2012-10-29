package cs414.as5.btsaunde.garagesystem.action;

import java.awt.event.ActionEvent;
import java.util.logging.Logger;

import javax.swing.AbstractAction;

import cs414.as5.btsaunde.garagesystem.view.ReportBuilder;

public class OpenReportGeneratorAction extends AbstractAction {

	/**
	 * Default Serial ID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Logger
	 */
	private Logger logger = Logger.getAnonymousLogger();

	/**
	 * Called when the Action is Triggered.
	 */
	public void actionPerformed(ActionEvent e) {
		this.logger.info("Starting ReportBuilder");

		ReportBuilder builder = new ReportBuilder();
		builder.setVisible(true);
	}

}
