/**
 * 
 */
package cs414.as4.btsaunde.garagesystem.security;

import junit.framework.Assert;

import org.junit.Test;

import cs414.as4.btsaunde.garagesystem.model.Attendant;

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
		Assert.fail("Not Yet Impemented");
	}

	/**
	 * Tests that Identity is a Singleton.
	 */
	@Test
	public void ifSingletonThenPass() {
		Assert.fail("Not Yet Impemented");
	}

	/**
	 * Tests Setters/Getters
	 */
	@Test
	public void testSetters() {
		Assert.fail("Not Yet Impemented");
	}

	/**
	 * Tests Logging in the Valid Password
	 */
	public void ifValidCredentialsThenAuthenticate() {
		Assert.fail("Not Yet Impemented");
	}

	/**
	 * Tests Logging in with a Bad Password
	 */
	@Test
	public void ifInvalidCredentialsThenFail() {
		Assert.fail("Not Yet Impemented");
	}

	/**
	 * Tests Logout
	 */
	@Test
	public void ifLoggedOutThenPass() {
		Assert.fail("Not Yet Impemented");
	}
}
