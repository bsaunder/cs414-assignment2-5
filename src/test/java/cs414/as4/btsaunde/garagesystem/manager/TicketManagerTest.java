/**
 * 
 */
package cs414.as4.btsaunde.garagesystem.manager;

import java.sql.Date;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import cs414.as4.btsaunde.garagesystem.config.GarageConfiguration;
import cs414.as4.btsaunde.garagesystem.dao.EventDao;
import cs414.as4.btsaunde.garagesystem.dao.TicketDao;
import cs414.as4.btsaunde.garagesystem.enums.GarageStatus;
import cs414.as4.btsaunde.garagesystem.enums.PaymentType;
import cs414.as4.btsaunde.garagesystem.model.Event;
import cs414.as4.btsaunde.garagesystem.model.Ticket;

/**
 * Tests for TicketManager.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
public class TicketManagerTest {

	/**
	 * Clear the DAO & Reset Config Before the Tests.
	 */
	@Before
	public void clearDao() {
		TicketDao dao = TicketDao.getInstance();
		dao.clear();

		GarageConfiguration config = GarageConfiguration.getInstance();
		config.setStatus(GarageStatus.OPEN);
		config.setTotalSpaces(1);
		config.setParkingFee(1.00);
	}

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

	/**
	 * Tests Verifying a Ticket that Exists
	 */
	@Test
	public void ifTicketVerifiedThenTrue() {
		// given
		TicketManager manager = TicketManager.getInstance();
		Ticket ticket = manager.createNewTicket();

		// when
		String id = ticket.getTicketId();
		Boolean result = manager.verifyTicketId(id);

		// then
		Assert.assertTrue(result);
	}

	/**
	 * Tests Verifying a Ticket that Does Not Exist
	 */
	@Test
	public void ifTicketNotVerifiedThenFalse() {
		// given
		TicketManager manager = TicketManager.getInstance();

		// when
		Boolean result = manager.verifyTicketId("123");

		// then
		Assert.assertFalse(result);
	}

	/**
	 * Tests Finding a Valid Ticket
	 */
	@Test
	public void ifTicketFoundThenGet() {
		// given
		TicketManager manager = TicketManager.getInstance();
		Ticket ticket = manager.createNewTicket();

		// when
		String id = ticket.getTicketId();
		Ticket result = manager.getTicket(id);

		// then
		Assert.assertNotNull(result);
		Assert.assertEquals(ticket, result);
	}

	/**
	 * Tests Finding a Non-Existent Ticket
	 */
	@Test
	public void ifTicketNotFoundThenNull() {
		// given
		TicketManager manager = TicketManager.getInstance();

		// when
		Ticket result = manager.getTicket("123");

		// then
		Assert.assertNull(result);
	}

	/**
	 * Tests Finalizing a Ticket
	 */
	@Test
	public void ifTicketFinalizedThenPass(){
		// given
		TicketManager manager = TicketManager.getInstance();
		Ticket ticket = manager.createNewTicket();
		String ticketId = ticket.getTicketId();
		
		EventDao eventDao = EventDao.getInstance();
		TicketDao ticketDao = TicketDao.getInstance();
		
		Date issued = new Date(0);
		
		// when
		manager.finalizeTicket(ticketId, PaymentType.CASH, 4.55, issued.getTime());
		eventDao.findEventByTicket(ticket);
		
		// then
		Assert.assertNull(ticketDao.findTicketById(ticketId));
		Event event = eventDao.findEventByTicket(ticket);
		Assert.assertNotNull(event);
		Assert.assertEquals(ticketId, event.getTicket().getTicketId());
		Assert.assertEquals(PaymentType.CASH, event.getPaymentType());
		Assert.assertEquals(4.55, event.getTotalFee());
		Assert.assertEquals(issued, event.getTimePaid());
	}
}
