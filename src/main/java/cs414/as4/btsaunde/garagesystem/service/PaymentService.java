package cs414.as4.btsaunde.garagesystem.service;

import java.util.logging.Logger;

import cs414.as4.btsaunde.garagesystem.config.GarageConfiguration;
import cs414.as4.btsaunde.garagesystem.manager.TicketManager;
import cs414.as4.btsaunde.garagesystem.model.Ticket;

/**
 * Payment Service for Handling Payments and Calculating Totals.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
public class PaymentService {

	/**
	 * Logger
	 */
	private Logger logger = Logger.getAnonymousLogger();

	/**
	 * 15m Segment in Milliseconds
	 */
	private static final Long SEGMENT_LENGTH_MS = 900000L;

	/**
	 * Computes the Total Fee for a Given Ticket ID
	 * 
	 * @param ticketId
	 *            Ticket ID
	 * @return Total Fee
	 */
	public Double computeFee(String ticketId, Long end) {
		TicketManager manager = TicketManager.getInstance();
		GarageConfiguration config = GarageConfiguration.getInstance();
		Double fee = config.getParkingFee();
		Ticket ticket = manager.getTicket(ticketId);

		// Compute Total Time
		Long start = ticket.getTimeIssued();
		Long totalTime = end - start;

		this.logger.info("Ticket ID: " + ticketId + " - Total Milliseconds: "
				+ totalTime);

		// Get 15m Segments
		Long segments = totalTime / PaymentService.SEGMENT_LENGTH_MS;
		Long remainder = totalTime % PaymentService.SEGMENT_LENGTH_MS;
		if (remainder > 0) {
			segments = segments + 1;
		}

		// Compute Fee
		Double totalFee = fee * segments;

		return totalFee;
	}

	/**
	 * Validates Card Number
	 * 
	 * @param cardNumber
	 *            Card Number to Validate
	 * @return Always True for Testing Purposes.
	 */
	public boolean cardIsValid(String cardNumber) {
		this.logger.info("Validated Card: " + cardNumber);
		return true;
	}

	/**
	 * Charges the Card
	 * 
	 * @param cardNumber
	 *            Card to Charge
	 * @param fee
	 *            Fee to Charge
	 */
	public void chargeCard(String cardNumber, Double fee) {
		this.logger.info("Charged Card: " + cardNumber + " - Amount: $" + fee);
	}
}
