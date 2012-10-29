/**
 * 
 */
package cs414.as5.btsaunde.garagesystem.model;

import java.util.Date;

import junit.framework.Assert;

import org.junit.Test;

import cs414.as5.btsaunde.garagesystem.enums.PaymentType;

/**
 * Event Tests.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 *
 */
public class EventTest {

	/**
	 * Test Event Creation.
	 */
	@Test
	public void createEvent() {
		Event event = new Event();
		Assert.assertNotNull(event);
		
		Long issued = System.currentTimeMillis();
		Long paid = System.currentTimeMillis();
		
		Ticket ticket = new Ticket();
		ticket.setTicketId("123");
		ticket.setTimeIssued(issued);
		
		event.setTicket(ticket);
		event.setPaymentType(PaymentType.CASH);
		event.setTimeIssued(issued);
		event.setTimePaid(paid);
		event.setTotalFee(4.55);
		
		Assert.assertEquals(ticket, event.getTicket());
		Assert.assertEquals(PaymentType.CASH, event.getPaymentType());
		Assert.assertEquals(new Date(issued), event.getTimeIssued());
		Assert.assertEquals(new Date(paid), event.getTimePaid());
		Assert.assertEquals(4.55, event.getTotalFee());
	}

}
