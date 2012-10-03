/**
 * 
 */
package cs414.as4.btsaunde.garagesystem.dao;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import cs414.as4.btsaunde.garagesystem.config.GarageConfiguration;
import cs414.as4.btsaunde.garagesystem.enums.GarageStatus;
import cs414.as4.btsaunde.garagesystem.manager.TicketManager;
import cs414.as4.btsaunde.garagesystem.model.Event;
import cs414.as4.btsaunde.garagesystem.model.Ticket;

/**
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
public class EventDaoTest {

	/**
	 * Setup Test Class
	 */
	@BeforeClass
	public static void setup() {
		GarageConfiguration config = GarageConfiguration.getInstance();
		config.setStatus(GarageStatus.OPEN);
		config.setTotalSpaces(1);
		config.setParkingFee(1.00);
	}

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
		EventDao dao = EventDao.getInstance();

		Assert.assertNotNull(dao);
	}

	/**
	 * Tests Dao Singleton.
	 */
	@Test
	public void ifSingletonThenPass() {
		EventDao dao = EventDao.getInstance();
		EventDao dao2 = EventDao.getInstance();

		Assert.assertNotNull(dao);
		Assert.assertNotNull(dao2);
		Assert.assertEquals(dao, dao2);
	}

	/**
	 * Tests the Event is Returned when the Ticket is Found
	 */
	@Test
	public void ifTicketFoundGetEvent() {
		// given
		TicketManager manager = TicketManager.getInstance();
		Ticket ticket = manager.createNewTicket();

		EventDao dao = EventDao.getInstance();

		// when
		Event result = dao.findEventByTicket(ticket);

		// then
		Assert.assertNotNull(result);
		Assert.assertEquals(ticket, result.getTicket());
	}

	/**
	 * Tests that Null is Returned when the Ticket is Not Found
	 */
	@Test
	public void ifTicketNotFoundThenNull() {
		// given
		Ticket ticket = new Ticket();

		EventDao dao = EventDao.getInstance();

		// when
		Event result = dao.findEventByTicket(ticket);

		// then
		Assert.assertNull(result);
	}
}
