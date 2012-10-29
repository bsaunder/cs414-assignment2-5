package cs414.as5.btsaunde.garagesystem.service;

import java.rmi.RemoteException;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import cs414.as5.btsaunde.garagesystem.config.GarageConfiguration;
import cs414.as5.btsaunde.garagesystem.dao.TicketDao;
import cs414.as5.btsaunde.garagesystem.enums.GarageStatus;
import cs414.as5.btsaunde.garagesystem.model.Ticket;

/**
 * Payment Service Tests.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
public class PaymentServiceImplTest {

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
	 * @throws RemoteException 
	 */
	@Test
	public void computeMillisecondFee() throws RemoteException {
		// given
		Long issued = System.currentTimeMillis();

		Ticket ticket = new Ticket();
		ticket.setTicketId("123");
		ticket.setTimeIssued(issued);

		TicketDao dao = TicketDao.getInstance();
		dao.add(ticket);

		Long end = issued + 1;

		PaymentService service = PaymentServiceImpl.getInstance();
		
		// when
		Double fee = service.computeFee("123", end);

		// then
		Assert.assertNotNull(fee);
		Assert.assertEquals(1.0, fee);
	}

	/**
	 * Computes the Fee for 15 Minutes
	 * @throws RemoteException 
	 */
	@Test
	public void computeSingleSegmentFee() throws RemoteException {
		// given
		Long issued = System.currentTimeMillis();

		Ticket ticket = new Ticket();
		ticket.setTicketId("123");
		ticket.setTimeIssued(issued);

		TicketDao dao = TicketDao.getInstance();
		dao.add(ticket);

		Long end = issued + (15 * 60000);
		
		PaymentService service = PaymentServiceImpl.getInstance();

		// when
		Double fee = service.computeFee("123", end);

		// then
		Assert.assertNotNull(fee);
		Assert.assertEquals(1.0, fee);
	}

	/**
	 * Computes the Fee for 7 Minutes
	 * @throws RemoteException 
	 */
	@Test
	public void computeHalfSegmentFee() throws RemoteException {
		// given
		Long issued = System.currentTimeMillis();

		Ticket ticket = new Ticket();
		ticket.setTicketId("123");
		ticket.setTimeIssued(issued);

		TicketDao dao = TicketDao.getInstance();
		dao.add(ticket);

		Long end = issued + (7 * 60000);
		
		PaymentService service = PaymentServiceImpl.getInstance();

		// when
		Double fee = service.computeFee("123", end);

		// then
		Assert.assertNotNull(fee);
		Assert.assertEquals(1.0, fee);
	}

	/**
	 * Computes the Fee for 23 Minutes
	 * @throws RemoteException 
	 */
	@Test
	public void computeOneAndHalfSegmentFee() throws RemoteException {
		// given
		Long issued = System.currentTimeMillis();

		Ticket ticket = new Ticket();
		ticket.setTicketId("123");
		ticket.setTimeIssued(issued);

		TicketDao dao = TicketDao.getInstance();
		dao.add(ticket);

		Long end = issued + (23 * 60000);
		
		PaymentService service = PaymentServiceImpl.getInstance();

		// when
		Double fee = service.computeFee("123", end);

		// then
		Assert.assertNotNull(fee);
		Assert.assertEquals(2.0, fee);
	}

	/**
	 * Computes the Fee for 30 Minutes
	 * @throws RemoteException 
	 */
	@Test
	public void computeTwoSegmentFee() throws RemoteException {
		// given
		Long issued = System.currentTimeMillis();

		Ticket ticket = new Ticket();
		ticket.setTicketId("123");
		ticket.setTimeIssued(issued);

		TicketDao dao = TicketDao.getInstance();
		dao.add(ticket);

		Long end = issued + (30 * 60000);
		
		PaymentService service = PaymentServiceImpl.getInstance();

		// when
		Double fee = service.computeFee("123", end);

		// then
		Assert.assertNotNull(fee);
		Assert.assertEquals(2.0, fee);
	}

	/**
	 * Tests Validating a Card.
	 * @throws RemoteException 
	 */
	@Test
	public void ifCardValidThenPass() throws RemoteException {
		PaymentService service = PaymentServiceImpl.getInstance();
		Assert.assertTrue(service.cardIsValid("1234"));
	}

}
