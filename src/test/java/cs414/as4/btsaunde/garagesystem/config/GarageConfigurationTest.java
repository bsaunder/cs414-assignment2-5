/**
 * 
 */
package cs414.as4.btsaunde.garagesystem.config;

import junit.framework.Assert;

import org.junit.Test;

import cs414.as4.btsaunde.garagesystem.enums.GarageStatus;

/**
 * Tests for Garage.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
public class GarageConfigurationTest {

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
		garage.setTotalSpaces(0);

		Assert.assertNotNull(garage);
		Assert.assertEquals(GarageStatus.CLOSED, garage.getStatus());
		Assert.assertEquals(new Integer(0), garage.getTotalSpaces());
	}
}
