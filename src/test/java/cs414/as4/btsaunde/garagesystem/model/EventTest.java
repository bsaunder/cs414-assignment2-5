/**
 * 
 */
package cs414.as4.btsaunde.garagesystem.model;

import junit.framework.Assert;

import org.junit.Test;

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
		
		Ticket ticket = new Ticket();
		event.setTicket(ticket);
		Assert.assertEquals(ticket, event.getTicket());
	}

}
