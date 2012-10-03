package cs414.as4.btsaunde.garagesystem.model;

import junit.framework.Assert;

import org.junit.Test;

import cs414.as4.btsaunde.garagesystem.model.Attendant;

/**
 * Tests for Attendant.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
public class AttendantTest {

	/**
	 * Tests Attendant Creation.
	 */
	@Test
	public void ifCreatedThenPass() {
		Attendant attendant = new Attendant("Fred");

		Assert.assertNotNull(attendant);
		Assert.assertEquals("Fred", attendant.getName());
	}

	/**
	 * Tests Pin Setup.
	 */
	@Test
	public void ifPinSetThenPass() {
		Attendant attendant = new Attendant("Fred");
		Assert.assertNotNull(attendant);

		attendant.setPin(1234);
		Assert.assertEquals(new Integer(1234), attendant.getPin());
	}
	
	/**
	 * Tests Name Setup.
	 */
	@Test
	public void ifNameSetThenPass() {
		Attendant attendant = new Attendant("Fred");
		Assert.assertNotNull(attendant);

		attendant.setName("Joe");
		Assert.assertEquals("Joe", attendant.getName());
	}
}
