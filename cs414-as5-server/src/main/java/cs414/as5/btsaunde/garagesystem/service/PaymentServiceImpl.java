package cs414.as5.btsaunde.garagesystem.service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Logger;

import cs414.as5.btsaunde.garagesystem.config.GarageConfiguration;
import cs414.as5.btsaunde.garagesystem.model.Ticket;

/**
 * Payment Service for Handling Payments and Calculating Totals.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
public class PaymentServiceImpl extends UnicastRemoteObject implements
		PaymentService {

	/**
	 * Default Serial ID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Logger
	 */
	private static final Logger LOGGER = Logger.getAnonymousLogger();

	/**
	 * Singleton Instance.
	 */
	private static PaymentService instance;

	/**
	 * Get Ticket DAO.
	 * 
	 * @return Ticket DAO
	 */
	public static PaymentService getInstance() {
		try {
			if (PaymentServiceImpl.instance == null) {
				PaymentServiceImpl.instance = new PaymentServiceImpl();
			}
		} catch (RemoteException e) {
			PaymentServiceImpl.LOGGER
					.info("Error Occured while Creating Payment Service Remote Object.");

		}

		return PaymentServiceImpl.instance;
	}

	/**
	 * Default Constructor
	 * 
	 * @throws RemoteException
	 */
	private PaymentServiceImpl() throws RemoteException {
		super();
	}

	/**
	 * 15m Segment in Milliseconds
	 */
	private static final Long SEGMENT_LENGTH_MS = 900000L;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cs414.as5.btsaunde.garagesystem.service.PaymentService#computeFee(java
	 * .lang.String, java.lang.Long)
	 */
	public Double computeFee(String ticketId, Long end) throws RemoteException {
		TicketService manager = TicketServiceImpl.getInstance();
		GarageConfiguration config = GarageConfiguration.getInstance();
		Double fee = config.getParkingFee();
		Ticket ticket = manager.getTicket(ticketId);

		// Compute Total Time
		Long start = ticket.getTimeIssued();
		Long totalTime = end - start;

		PaymentServiceImpl.LOGGER.info("Ticket ID: " + ticketId
				+ " - Total Milliseconds: " + totalTime);

		// Get 15m Segments
		Long segments = totalTime / PaymentServiceImpl.SEGMENT_LENGTH_MS;
		Long remainder = totalTime % PaymentServiceImpl.SEGMENT_LENGTH_MS;
		if (remainder > 0) {
			segments = segments + 1;
		}

		// Compute Fee
		Double totalFee = fee * segments;

		return totalFee;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cs414.as5.btsaunde.garagesystem.service.PaymentService#cardIsValid(java
	 * .lang.String)
	 */
	public boolean cardIsValid(String cardNumber) {
		PaymentServiceImpl.LOGGER.info("Validated Card: " + cardNumber);
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cs414.as5.btsaunde.garagesystem.service.PaymentService#chargeCard(java
	 * .lang.String, java.lang.Double)
	 */
	public void chargeCard(String cardNumber, Double fee) {
		PaymentServiceImpl.LOGGER.info("Charged Card: " + cardNumber
				+ " - Amount: $" + fee);
	}
}
