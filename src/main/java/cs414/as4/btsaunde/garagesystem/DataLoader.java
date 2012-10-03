package cs414.as4.btsaunde.garagesystem;

import java.util.Calendar;
import java.util.Random;

import cs414.as4.btsaunde.garagesystem.dao.EventDao;
import cs414.as4.btsaunde.garagesystem.dao.TicketDao;
import cs414.as4.btsaunde.garagesystem.enums.PaymentType;
import cs414.as4.btsaunde.garagesystem.model.Event;
import cs414.as4.btsaunde.garagesystem.model.Ticket;
import cs414.as4.btsaunde.garagesystem.service.PaymentService;

/**
 * Loads Test Data into the Application on Startup.
 * 
 * @author Bryan Saunders <bsaunder@redhat.com>
 * 
 */
public class DataLoader {

	public void loadData(int eventcount) {
		EventDao eventDao = EventDao.getInstance();
		TicketDao ticketDao = TicketDao.getInstance();
		PaymentService paymentService = new PaymentService();

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
			
			Double fee = paymentService.computeFee(ticketId, paid.getTimeInMillis());
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
