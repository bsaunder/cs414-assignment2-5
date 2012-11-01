/**
 * 
 */
package cs414.as5.btsaunde.garagesystem.config;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import cs414.as5.btsaunde.garagesystem.dao.TicketDao;
import cs414.as5.btsaunde.garagesystem.enums.GarageStatus;
import cs414.as5.btsaunde.garagesystem.model.Ticket;

/**
 * Tests for Garage.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
public class GarageConfigurationTest {

	/**
	 * Clear the DAO Before the Tests.
	 */
	@Before
	public void clearDao() {
		TicketDao dao = TicketDao.getInstance();
		dao.clear();
	}

	/**
	 * Tests Garage Creation.
	 */
	@Test
	public void ifCreatedThenPass() {
		GarageConfiguration garage = GarageConfiguration.getInstance();

		Assert.assertNotNull(garage);
	}

	/**
	 * Tests Garage Singleton.
	 */
	@Test
	public void ifSingletonThenPass() {
		GarageConfiguration garage = GarageConfiguration.getInstance();
		GarageConfiguration garage2 = GarageConfiguration.getInstance();

		Assert.assertNotNull(garage);
		Assert.assertNotNull(garage2);
		Assert.assertEquals(garage, garage2);
	}

	/**
	 * Tests Garage Properties.
	 */
	@Test
	public void ifSetThenPass() {
		GarageConfiguration garage = GarageConfiguration.getInstance();
		garage.setStatus(GarageStatus.CLOSED);
		garage.setTotalSpaces(1);
		garage.setParkingFee(2.25);

		Assert.assertNotNull(garage);
		Assert.assertEquals(GarageStatus.CLOSED, garage.getStatus());
		Assert.assertEquals(new Integer(1), garage.getTotalSpaces());
		Assert.assertEquals(new Double(2.25), garage.getParkingFee());
	}

	/**
	 * Tests Available Spaces when there are no cars.
	 */
	@Test
	public void ifNoCarsThenAvailableEqualsTotal() {
		// given
		GarageConfiguration garage = GarageConfiguration.getInstance();
		garage.setTotalSpaces(1);

		// when
		Integer available = garage.getAvailableSpaces();

		// then
		Assert.assertEquals(new Integer(1), available);

	}

	/**
	 * Tests Available Spaces when tHere are Cars.
	 */
	@Test
	public void ifOneCarThenAvailableEqualsTotalMinusOne() {
		// given
		GarageConfiguration garage = GarageConfiguration.getInstance();
		garage.setTotalSpaces(2);

		TicketDao dao = TicketDao.getInstance();
		dao.add(new Ticket());

		// when
		Integer available = garage.getAvailableSpaces();

		// then
		Assert.assertEquals(new Integer(1), available);
	}
}
