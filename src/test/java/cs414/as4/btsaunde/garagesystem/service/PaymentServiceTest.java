package cs414.as4.btsaunde.garagesystem.service;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import cs414.as4.btsaunde.garagesystem.config.GarageConfiguration;
import cs414.as4.btsaunde.garagesystem.dao.TicketDao;
import cs414.as4.btsaunde.garagesystem.enums.GarageStatus;
import cs414.as4.btsaunde.garagesystem.model.Ticket;

/**
 * Payment Service Tests.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
public class PaymentServiceTest {

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
	 * Computes the Fee for 1 Millisecond
	 */
	@Test
	public void computeMillisecondFee() {
		// given
		Long issued = System.currentTimeMillis();

		Ticket ticket = new Ticket();
		ticket.setTicketId("123");
		ticket.setTimeIssued(issued);

		TicketDao dao = TicketDao.getInstance();
		dao.add(ticket);

		PaymentService service = new PaymentService();
		Long end = issued + 1;

		// when
		Double fee = service.computeFee("123", end);

		// then
		Assert.assertNotNull(fee);
		Assert.assertEquals(1.0, fee);
	}

	/**
	 * Computes the Fee for 15 Minutes
	 */
	@Test
	public void computeSingleSegmentFee() {
		// given
		Long issued = System.currentTimeMillis();

		Ticket ticket = new Ticket();
		ticket.setTicketId("123");
		ticket.setTimeIssued(issued);

		TicketDao dao = TicketDao.getInstance();
		dao.add(ticket);

		PaymentService service = new PaymentService();
		Long end = issued + (15 * 60000);

		// when
		Double fee = service.computeFee("123", end);

		// then
		Assert.assertNotNull(fee);
		Assert.assertEquals(1.0, fee);
	}

	/**
	 * Computes the Fee for 7 Minutes
	 */
	@Test
	public void computeHalfSegmentFee() {
		// given
		Long issued = System.currentTimeMillis();

		Ticket ticket = new Ticket();
		ticket.setTicketId("123");
		ticket.setTimeIssued(issued);

		TicketDao dao = TicketDao.getInstance();
		dao.add(ticket);

		PaymentService service = new PaymentService();
		Long end = issued + (7 * 60000);

		// when
		Double fee = service.computeFee("123", end);

		// then
		Assert.assertNotNull(fee);
		Assert.assertEquals(1.0, fee);
	}

	/**
	 * Computes the Fee for 23 Minutes
	 */
	@Test
	public void computeOneAndHalfSegmentFee() {
		// given
		Long issued = System.currentTimeMillis();

		Ticket ticket = new Ticket();
		ticket.setTicketId("123");
		ticket.setTimeIssued(issued);

		TicketDao dao = TicketDao.getInstance();
		dao.add(ticket);

		PaymentService service = new PaymentService();
		Long end = issued + (23 * 60000);

		// when
		Double fee = service.computeFee("123", end);

		// then
		Assert.assertNotNull(fee);
		Assert.assertEquals(2.0, fee);
	}

	/**
	 * Computes the Fee for 30 Minutes
	 */
	@Test
	public void computeTwoSegmentFee() {
		// given
		Long issued = System.currentTimeMillis();

		Ticket ticket = new Ticket();
		ticket.setTicketId("123");
		ticket.setTimeIssued(issued);

		TicketDao dao = TicketDao.getInstance();
		dao.add(ticket);

		PaymentService service = new PaymentService();
		Long end = issued + (30 * 60000);

		// when
		Double fee = service.computeFee("123", end);

		// then
		Assert.assertNotNull(fee);
		Assert.assertEquals(2.0, fee);
	}

	/**
	 * Tests Validating a Card.
	 */
	@Test
	public void ifCardValidThenPass() {
		PaymentService service = new PaymentService();

		Assert.assertTrue(service.cardIsValid("1234"));
	}

}
