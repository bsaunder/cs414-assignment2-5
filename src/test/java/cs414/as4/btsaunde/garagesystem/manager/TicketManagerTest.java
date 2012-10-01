/**
 * 
 */
package cs414.as4.btsaunde.garagesystem.manager;

import junit.framework.Assert;

import org.junit.Test;

import cs414.as4.btsaunde.garagesystem.config.GarageConfiguration;
import cs414.as4.btsaunde.garagesystem.model.Ticket;

/**
 * Tests for TicketManager.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 *
 */
public class TicketManagerTest {

	/**
	 * Tests TicketManager Creation.
	 */
	@Test
	public void ifCreatedThenPass() {
		TicketManager manager = TicketManager.getInstance();

		Assert.assertNotNull(manager);
	}

	/**
	 * Tests TicketManager Singleton.
	 */
	@Test
	public void ifSingletonThenPass() {
		TicketManager manager = TicketManager.getInstance();
		TicketManager manager2 = TicketManager.getInstance();

		Assert.assertNotNull(manager);
		Assert.assertNotNull(manager2);
		Assert.assertEquals(manager, manager2);
	}

	/**
	 * Test That a new Ticket is Created.
	 */
	@Test
	public void ifTicketCreatedThenPass() {
		// given
		GarageConfiguration config = GarageConfiguration.getInstance();
		config.setTotalSpaces(2);
		
		TicketManager manager = TicketManager.getInstance();
		
		// when
		Ticket ticket = manager.createNewTicket();
		
		// then
		Assert.assertNotNull(ticket);
		Assert.assertNotNull(ticket.getTicketId());
		Assert.assertNotNull(ticket.getTimeIssued());
	}
	
	/**
	 * Test That a new Ticket is Not Created when the Garage is Full
	 */
	@Test
	public void ifGarageFullThenFail() {
		// given
		GarageConfiguration config = GarageConfiguration.getInstance();
		config.setTotalSpaces(0); // No Room!
		
		TicketManager manager = TicketManager.getInstance();
		
		// when
		Ticket ticket = manager.createNewTicket();
		
		// then
		Assert.assertNull(ticket);
	}

}
