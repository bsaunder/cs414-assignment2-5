package cs414.as4.btsaunde.garagesystem.dao;

import java.util.LinkedList;

import cs414.as4.btsaunde.garagesystem.model.Event;

public class EventDao extends LinkedList<Event> {
	
	/**
	 * Default Serial ID.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Singleton Instance.
	 */
	private static EventDao instance;

	/**
	 * Private Constructor.
	 */
	private EventDao() {
	}

	/**
	 * Get Ticket DAO.
	 * 
	 * @return Ticket DAO
	 */
	public static EventDao getInstance() {
		if (EventDao.instance == null) {
			EventDao.instance = new EventDao();
		}

		return EventDao.instance;
	}
}
