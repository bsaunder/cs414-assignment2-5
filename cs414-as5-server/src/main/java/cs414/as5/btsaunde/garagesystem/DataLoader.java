package cs414.as5.btsaunde.garagesystem;

import java.rmi.RemoteException;
import java.util.Calendar;
import java.util.Random;

import cs414.as5.btsaunde.garagesystem.dao.EventDao;
import cs414.as5.btsaunde.garagesystem.dao.TicketDao;
import cs414.as5.btsaunde.garagesystem.enums.PaymentType;
import cs414.as5.btsaunde.garagesystem.model.Event;
import cs414.as5.btsaunde.garagesystem.model.Ticket;
import cs414.as5.btsaunde.garagesystem.service.PaymentService;
import cs414.as5.btsaunde.garagesystem.service.PaymentServiceImpl;

/**
 * Loads Test Data into the Application on Startup.
 * 
 * @author Bryan Saunders <bsaunder@redhat.com>
 * 
 */
public class DataLoader {

	/**
	 * Loads the Specified Number of Events Between 9/1/12 and 9/9/12 with a
	 * Random Distribution
	 * 
	 * @param eventcount
	 *            Number of Events
	 * @throws RemoteException 
	 */
	public void loadData(int eventcount) throws RemoteException {
		EventDao eventDao = EventDao.getInstance();
		TicketDao ticketDao = TicketDao.getInstance();
		PaymentService service = PaymentServiceImpl.getInstance();

		// Random Number Generator
		Random generator = new Random();

		// Create some Events
		for (int i = 0; i < eventcount; i++) {

			Integer day = generator.nextInt(9) + 1;
			Calendar issued = Calendar.getInstance();
			issued.set(2012, 8, day, 11, 00);

			Integer minute = generator.nextInt(59) + 1;
			Calendar paid = Calendar.getInstance();
			paid.set(2012, 8, day, 11, minute);

			Ticket ticket = new Ticket();
			String ticketId = "SPSF0" + i;
			ticket.setTicketId(ticketId);
			ticket.setTimeIssued(issued.getTimeInMillis());
			ticketDao.add(ticket);

			Event event = new Event();
			event.setTicket(ticket);
			event.setTimeIssued(issued.getTime());
			event.setTimePaid(paid.getTime());

			Double fee = service.computeFee(ticketId, paid.getTimeInMillis());
			event.setTotalFee(fee);

			if (i % 2 == 0) {
				event.setPaymentType(PaymentType.CASH);
			} else {
				event.setPaymentType(PaymentType.CREDIT);
			}

			ticketDao.remove(ticket);
			eventDao.add(event);
		}

		// Remove any Tickets, Just to be Safe.
		ticketDao.clear();
	}

	/**
	 * Load the Specified Number of Events for the Specified Date. Each Loaded
	 * Event lasts 20 Minutes.
	 * 
	 * @param eventcount
	 *            Number of Events
	 * @param date
	 *            Date
	 * @throws RemoteException 
	 */
	public void loadData(int eventcount, Calendar date) throws RemoteException {
		EventDao eventDao = EventDao.getInstance();
		TicketDao ticketDao = TicketDao.getInstance();
		PaymentService service = PaymentServiceImpl.getInstance();

		// Create some Events
		for (int i = 0; i < eventcount; i++) {

			Ticket ticket = new Ticket();
			String ticketId = "SPST0" + i;
			ticket.setTicketId(ticketId);
			ticket.setTimeIssued(date.getTimeInMillis());
			ticketDao.add(ticket);

			Calendar paid = Calendar.getInstance();
			paid.setTime(date.getTime());
			paid.set(Calendar.MINUTE, date.get(Calendar.MINUTE) + 20);

			Event event = new Event();
			event.setTicket(ticket);
			event.setTimeIssued(date.getTime());
			event.setTimePaid(paid.getTime());

			Double fee = service.computeFee(ticketId, paid.getTimeInMillis());
			event.setTotalFee(fee);

			if (i % 2 == 0) {
				event.setPaymentType(PaymentType.CASH);
			} else {
				event.setPaymentType(PaymentType.CREDIT);
			}

			ticketDao.remove(ticket);
			eventDao.add(event);
		}

		// Remove any Tickets, Just to be Safe.
		ticketDao.clear();
	}
}
