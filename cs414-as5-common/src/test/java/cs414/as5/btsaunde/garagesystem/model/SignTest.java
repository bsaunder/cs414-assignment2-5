/**
 * 
 */
package cs414.as5.btsaunde.garagesystem.model;

import junit.framework.Assert;

import org.junit.Test;

/**
 * Tests the Sign.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
public class SignTest {

	/**
	 * Tests Sign Creation.
	 */
	@Test
	public void createSign() {
		Sign sign = new Sign();
		
		Assert.assertNotNull(sign);
	}
	
	/**
	 * Tests Setting Sign Text.
	 */
	@Test
	public void ifTextSetThenPass() {
		Sign sign = new Sign();
		sign.setText("Hello");
		
		Assert.assertEquals("Hello", sign.getText());
	}

}
