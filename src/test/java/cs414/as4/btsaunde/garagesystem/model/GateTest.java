package cs414.as4.btsaunde.garagesystem.model;

import junit.framework.Assert;

import org.junit.Test;

/**
 * Tests the Gate
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
public class GateTest {

	/**
	 * Tests Creating a Gate
	 */
	@Test
	public void createGate() {
		Gate gate = new Gate();

		Assert.assertNotNull(gate);
	}

	/**
	 * Tests Opening a Gate
	 */
	@Test
	public void openGate() {
		Gate gate = new Gate();
		gate.openGate();

		Assert.assertTrue(gate.isOpen());
	}

	/**
	 * Tests Closing a Gate
	 */
	@Test
	public void closeGate() {
		Gate gate = new Gate();
		gate.closeGate();

		Assert.assertFalse(gate.isOpen());
	}

}
