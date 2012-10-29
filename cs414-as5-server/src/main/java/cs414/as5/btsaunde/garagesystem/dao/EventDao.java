package cs414.as5.btsaunde.garagesystem.dao;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

import cs414.as5.btsaunde.garagesystem.model.Event;
import cs414.as5.btsaunde.garagesystem.model.Ticket;
import cs414.as5.btsaunde.garagesystem.util.DateUtil;

/**
 * Event DAO to Store Events.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
public class EventDao extends LinkedList<Event> {

	/**
	 * Default Serial ID.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Logger
	 */
	private Logger logger = Logger.getAnonymousLogger();

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
	 * Get Event DAO.
	 * 
	 * @return Event DAO
	 */
	public static EventDao getInstance() {
		if (EventDao.instance == null) {
			EventDao.instance = new EventDao();
		}

		return EventDao.instance;
	}

	/**
	 * Finds the Event that the Ticket Belongs To.
	 * 
	 * @param ticket
	 *            Ticket to Search On
	 * @return Matching Event or null if No Event found
	 */
	public Event findEventByTicket(Ticket ticket) {
		for (Event event : this) {
			if (event.getTicket().equals(ticket)) {
				return event;
			}
		}

		return null;
	}

	/**
	 * Finds the Events that took Place on the Specified Date
	 * 
	 * @param searchDate
	 *            Date to Search For
	 * @return Event List or Empty List
	 */
	public List<Event> findEventByDateIssued(Calendar searchDate) {
		List<Event> events = new LinkedList<Event>();

		for (Event event : this) {
			Date eventDate = event.getTimeIssued();
			this.logger.info("Checking Event Issued On: " + eventDate.toString());
			Calendar eventCalendar = Calendar.getInstance();
			eventCalendar.setTime(eventDate);

			if (DateUtil.datesEqual(eventCalendar, searchDate)) {
				this.logger.info("Adding Event to EventList: " + event);
				events.add(event);
			}
		}

		return events;
	}
}
