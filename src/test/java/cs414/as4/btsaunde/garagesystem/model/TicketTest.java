/**
 * 
 */
package cs414.as4.btsaunde.garagesystem.model;

import junit.framework.Assert;

import org.junit.Test;

/**
 * Tests for Ticket.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 *
 */
public class TicketTest {

	/**
	 * Tests Ticket Creation.
	 */
	@Test
	public void createTicket() {
		Ticket ticket = new Ticket();
		Assert.assertNotNull(ticket);
		
		ticket.setTicketId("1");
		ticket.setTimeIssued(1234L);
		
		Assert.assertEquals("1", ticket.getTicketId());
		Assert.assertEquals(new Long(1234L), ticket.getTimeIssued());
		
	}

}
