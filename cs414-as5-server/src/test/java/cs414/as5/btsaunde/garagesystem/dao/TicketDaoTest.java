/**
 * 
 */
package cs414.as5.btsaunde.garagesystem.dao;

import java.rmi.RemoteException;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import cs414.as5.btsaunde.garagesystem.config.GarageConfiguration;
import cs414.as5.btsaunde.garagesystem.model.Ticket;
import cs414.as5.btsaunde.garagesystem.service.TicketService;
import cs414.as5.btsaunde.garagesystem.service.TicketServiceImpl;

/**
 * Ticket DAO Tests.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
public class TicketDaoTest {

	/**
	 * Clear the DAO Before the Tests.
	 */
	@Before
	public void clearDao() {
		TicketDao dao = TicketDao.getInstance();
		dao.clear();
	}

	/**
	 * Tests Dao Creation.
	 */
	@Test
	public void ifCreatedThenPass() {
		TicketDao dao = TicketDao.getInstance();

		Assert.assertNotNull(dao);
	}

	/**
	 * Tests Dao Singleton.
	 */
	@Test
	public void ifSingletonThenPass() {
		TicketDao dao = TicketDao.getInstance();
		TicketDao dao2 = TicketDao.getInstance();

		Assert.assertNotNull(dao);
		Assert.assertNotNull(dao2);
		Assert.assertEquals(dao, dao2);
	}

	/**
	 * Test Adding a Ticket
	 */
	@Test
	public void ifTicketAddedThenPass() {
		// given
		GarageConfiguration garage = GarageConfiguration.getInstance();
		garage.setTotalSpaces(1);

		TicketDao dao = TicketDao.getInstance();
		Ticket ticket = new Ticket();

		// when
		Boolean result = dao.add(ticket);

		// then
		Assert.assertTrue(result);
	}

	/**
	 * Test Adding a Ticket
	 */
	@Test
	public void ifTicketNotAddedThenPass() {
		// given
		GarageConfiguration garage = GarageConfiguration.getInstance();
		garage.setTotalSpaces(1);

		TicketDao dao = TicketDao.getInstance();
		Ticket ticket = new Ticket();
		Ticket ticket2 = new Ticket();

		// when
		dao.add(ticket);
		Boolean result = dao.add(ticket2);

		// then
		Assert.assertFalse(result);
	}

	/**
	 * Tests Getting a Ticket that Exists
	 * @throws RemoteException 
	 */
	@Test
	public void ifTicketFoundThenGetTicket() throws RemoteException {
		// given
		TicketService manager = TicketServiceImpl.getInstance();
		Ticket ticket = manager.createNewTicket();

		TicketDao dao = TicketDao.getInstance();

		// when
		Ticket result = dao.findTicketById(ticket.getTicketId());

		// then
		Assert.assertEquals(ticket.getTicketId(), result.getTicketId());
	}

	/**
	 * Tests Finding a Ticket that Does not Exist.
	 */
	@Test
	public void ifTicketNotFoundThenNull() {
		// given
		TicketDao dao = TicketDao.getInstance();

		// when
		Ticket result = dao.findTicketById("123");

		// then
		Assert.assertNull(result);
	}

}
