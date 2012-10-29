/**
 * 
 */
package cs414.as5.btsaunde.garagesystem.security;

import junit.framework.Assert;

import org.junit.Test;

import cs414.as5.btsaunde.garagesystem.model.Attendant;

/**
 * Tests for Security Identity.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
public class IdentityTest {

	/**
	 * Test Identity Creation.
	 */
	@Test
	public void ifCreatedThenPass() {
		// given
		Attendant attendant = new Attendant("Fred");
		attendant.setPin(1234);

		// when
		Identity identity = Identity.getNewInstance(attendant);

		// then
		Assert.assertNotNull(identity);
	}

	/**
	 * Tests that a New Instance is Created.
	 */
	@Test
	public void ifNewInstanceCreatedThenPass() {
		// given
		Attendant attendant = new Attendant("Fred");
		attendant.setPin(1234);

		// when
		Identity identity = Identity.getNewInstance(attendant);

		// then
		Assert.assertNotNull(identity);
	}

	/**
	 * Tests that Identity is a Singleton.
	 */
	@Test
	public void ifSingletonThenPass() {
		// given
		Attendant attendant = new Attendant("Fred");
		attendant.setPin(1234);

		// when
		Identity identity = Identity.getNewInstance(attendant);
		Identity identity2 = Identity.getInstance();

		// then
		Assert.assertNotNull(identity);
		Assert.assertNotNull(identity2);
		Assert.assertEquals(identity, identity2);
	}

	/**
	 * Tests Setters/Getters
	 */
	@Test
	public void testSetters() {
		// given
		Attendant attendant = new Attendant("Fred");
		attendant.setPin(1234);
		Identity identity = Identity.getNewInstance(attendant);

		// when
		Attendant attendant2 = new Attendant("Bob");
		attendant2.setPin(4567);

		identity.setAttendant(attendant2);

		// then
		Assert.assertEquals(attendant2, identity.getAttendant());
	}

	/**
	 * Tests Logging in the Valid Password
	 */
	public void ifValidCredentialsThenAuthenticate() {
		// given
		Attendant attendant = new Attendant("Fred");
		attendant.setPin(1234);
		Identity identity = Identity.getNewInstance(attendant);

		// when
		Boolean result = identity.login("1234");

		// then
		Assert.assertTrue(identity.isAuthenticated());
		Assert.assertTrue(result);
	}

	/**
	 * Tests Logging in with a Bad Password
	 */
	@Test
	public void ifInvalidCredentialsThenFail() {
		// given
		Attendant attendant = new Attendant("Fred");
		attendant.setPin(1234);
		Identity identity = Identity.getNewInstance(attendant);

		// when
		Boolean result = identity.login("4561");

		// then
		Assert.assertFalse(identity.isAuthenticated());
		Assert.assertFalse(result);
	}

	/**
	 * Tests Logout
	 */
	@Test
	public void ifLoggedOutThenPass() {
		// given
		Attendant attendant = new Attendant("Fred");
		attendant.setPin(1234);
		Identity identity = Identity.getNewInstance(attendant);

		identity.login("1234");
		Assert.assertTrue(identity.isAuthenticated());

		// when
		identity.logout();

		// then
		Assert.assertFalse(identity.isAuthenticated());
	}
}
