package cs414.as4.btsaunde.garagesystem.dao;

import java.util.LinkedList;

import cs414.as4.btsaunde.garagesystem.model.Ticket;

public class TicketDao extends LinkedList<Ticket> {

	/**
	 * Default Serial ID.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Singleton Instance.
	 */
	private static TicketDao instance;

	/**
	 * Private Constructor.
	 */
	private TicketDao() {

	}

	/**
	 * Get Ticket DAO.
	 * 
	 * @return Ticket DAO
	 */
	public static TicketDao getInstance() {
		if (TicketDao.instance == null) {
			TicketDao.instance = new TicketDao();
		}

		return TicketDao.instance;
	}

}
