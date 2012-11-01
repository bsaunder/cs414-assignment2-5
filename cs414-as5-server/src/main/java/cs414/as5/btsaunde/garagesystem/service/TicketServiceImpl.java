package cs414.as5.btsaunde.garagesystem.service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;
import java.util.logging.Logger;

import cs414.as5.btsaunde.garagesystem.dao.EventDao;
import cs414.as5.btsaunde.garagesystem.dao.TicketDao;
import cs414.as5.btsaunde.garagesystem.enums.PaymentType;
import cs414.as5.btsaunde.garagesystem.model.Event;
import cs414.as5.btsaunde.garagesystem.model.Ticket;

public class TicketServiceImpl extends UnicastRemoteObject implements
		TicketService {

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
	private static TicketService instance;

	/**
	 * Ticket DAO.
	 */
	private TicketDao ticketDao;

	/**
	 * Event DAO.
	 */
	private EventDao eventDao;

	/**
	 * Ticket Number Counter
	 */
	private Integer ticketNumber = 1;

	/**
	 * Private Constructor.
	 */
	private TicketServiceImpl() throws RemoteException {
		this.ticketDao = TicketDao.getInstance();
		this.eventDao = EventDao.getInstance();
	}

	/**
	 * Get Ticket Service.
	 * 
	 * @return TicketService
	 */
	public static TicketService getInstance() {
		try {
			if (TicketServiceImpl.instance == null) {
				TicketServiceImpl.instance = new TicketServiceImpl();
			}
		} catch (RemoteException e) {
			TicketServiceImpl.LOGGER.severe("Error Occured while Creating Ticket Service Remote Object.");
			e.printStackTrace();
		}

		return TicketServiceImpl.instance;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cs414.as5.btsaunde.garagesystem.service.TicketService#createNewTicket()
	 */
	public Ticket createNewTicket() {
		Ticket ticket = new Ticket();

		Date issued = new Date();
		ticket.setTimeIssued(issued.getTime());

		// String id = UUID.randomUUID().toString();
		String id = "SPS" + this.ticketNumber++;
		ticket.setTicketId(id);

		// Save Ticket
		boolean saved = this.ticketDao.add(ticket);

		if (saved) {
			// Log Ticket Event
			Event event = new Event();
			event.setTicket(ticket);
			event.setTimeIssued(issued);

			this.eventDao.add(event);

			return ticket;
		} else {
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cs414.as5.btsaunde.garagesystem.service.TicketService#verifyTicketId(
	 * java.lang.String)
	 */
	public boolean verifyTicketId(String ticketId) {
		Ticket ticket = this.ticketDao.findTicketById(ticketId);
		return ticket != null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cs414.as5.btsaunde.garagesystem.service.TicketService#getTicket(java.
	 * lang.String)
	 */
	public Ticket getTicket(String ticketId) {
		return this.ticketDao.findTicketById(ticketId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cs414.as5.btsaunde.garagesystem.service.TicketService#finalizeTicket(
	 * cs414.as5.btsaunde.garagesystem.model.Ticket,
	 * cs414.as5.btsaunde.garagesystem.enums.PaymentType, java.lang.Double,
	 * java.lang.Long)
	 */
	public void finalizeTicket(Ticket ticket, PaymentType paymentType,
			Double totalFee, Long timePaid) {

		Event event = this.eventDao.findEventByTicket(ticket);

		event.setTimePaid(timePaid);
		event.setTotalFee(totalFee);
		event.setPaymentType(paymentType);

		this.ticketDao.remove(ticket);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cs414.as5.btsaunde.garagesystem.service.TicketService#finalizeTicket(
	 * java.lang.String, cs414.as5.btsaunde.garagesystem.enums.PaymentType,
	 * java.lang.Double, java.lang.Long)
	 */
	public void finalizeTicket(String ticketId, PaymentType paymentType,
			Double totalFee, Long timePaid) {

		Ticket ticket = this.getTicket(ticketId);
		this.finalizeTicket(ticket, paymentType, totalFee, timePaid);
	}
}
