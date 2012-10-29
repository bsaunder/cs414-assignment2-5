/**
 * 
 */
package cs414.as5.btsaunde.garagesystem.service;

import java.rmi.RemoteException;
import java.sql.Date;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import cs414.as5.btsaunde.garagesystem.config.GarageConfiguration;
import cs414.as5.btsaunde.garagesystem.dao.EventDao;
import cs414.as5.btsaunde.garagesystem.dao.TicketDao;
import cs414.as5.btsaunde.garagesystem.enums.GarageStatus;
import cs414.as5.btsaunde.garagesystem.enums.PaymentType;
import cs414.as5.btsaunde.garagesystem.model.Event;
import cs414.as5.btsaunde.garagesystem.model.Ticket;
import cs414.as5.btsaunde.garagesystem.service.TicketServiceImpl;

/**
 * Tests for TicketManager.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
public class TicketServiceImplTest {

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
		TicketService manager = TicketServiceImpl.getInstance();

		Assert.assertNotNull(manager);
	}

	/**
	 * Tests TicketManager Singleton.
	 */
	@Test
	public void ifSingletonThenPass() {
		TicketService manager = TicketServiceImpl.getInstance();
		TicketService manager2 = TicketServiceImpl.getInstance();

		Assert.assertNotNull(manager);
		Assert.assertNotNull(manager2);
		Assert.assertEquals(manager, manager2);
	}

	/**
	 * Test That a new Ticket is Created.
	 * @throws RemoteException 
	 */
	@Test
	public void ifTicketCreatedThenPass() throws RemoteException {
		// given
		GarageConfiguration config = GarageConfiguration.getInstance();
		config.setTotalSpaces(2);

		TicketService manager = TicketServiceImpl.getInstance();

		// when
		Ticket ticket = manager.createNewTicket();

		// then
		Assert.assertNotNull(ticket);
		Assert.assertNotNull(ticket.getTicketId());
		Assert.assertNotNull(ticket.getTimeIssued());
	}

	/**
	 * Test That a new Ticket is Not Created when the Garage is Full
	 * @throws RemoteException 
	 */
	@Test
	public void ifGarageFullThenFail() throws RemoteException {
		// given
		GarageConfiguration config = GarageConfiguration.getInstance();
		config.setTotalSpaces(0); // No Room!

		TicketService manager = TicketServiceImpl.getInstance();

		// when
		Ticket ticket = manager.createNewTicket();

		// then
		Assert.assertNull(ticket);
	}

	/**
	 * Tests Verifying a Ticket that Exists
	 * @throws RemoteException 
	 */
	@Test
	public void ifTicketVerifiedThenTrue() throws RemoteException {
		// given
		TicketService manager = TicketServiceImpl.getInstance();
		Ticket ticket = manager.createNewTicket();

		// when
		String id = ticket.getTicketId();
		Boolean result = manager.verifyTicketId(id);

		// then
		Assert.assertTrue(result);
	}

	/**
	 * Tests Verifying a Ticket that Does Not Exist
	 * @throws RemoteException 
	 */
	@Test
	public void ifTicketNotVerifiedThenFalse() throws RemoteException {
		// given
		TicketService manager = TicketServiceImpl.getInstance();

		// when
		Boolean result = manager.verifyTicketId("123");

		// then
		Assert.assertFalse(result);
	}

	/**
	 * Tests Finding a Valid Ticket
	 * @throws RemoteException 
	 */
	@Test
	public void ifTicketFoundThenGet() throws RemoteException {
		// given
		TicketService manager = TicketServiceImpl.getInstance();
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
	 * @throws RemoteException 
	 */
	@Test
	public void ifTicketNotFoundThenNull() throws RemoteException {
		// given
		TicketService manager = TicketServiceImpl.getInstance();

		// when
		Ticket result = manager.getTicket("123");

		// then
		Assert.assertNull(result);
	}

	/**
	 * Tests Finalizing a Ticket
	 * @throws RemoteException 
	 */
	@Test
	public void ifTicketFinalizedThenPass() throws RemoteException{
		// given
		TicketService manager = TicketServiceImpl.getInstance();
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
