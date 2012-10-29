/**
 * 
 */
package cs414.as5.btsaunde.garagesystem.util;

import java.util.Calendar;

import junit.framework.Assert;

import org.junit.Test;

/**
 * Tests Date Utils.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
public class DateUtilTest {

	/**
	 * Tests that Dates are Equal
	 */
	@Test
	public void datesEqualNoTimes() {
		Calendar cal1 = Calendar.getInstance();
		cal1.set(Calendar.MONTH, 1);
		cal1.set(Calendar.YEAR, 2012);
		cal1.set(Calendar.DAY_OF_MONTH, 1);

		Calendar cal2 = Calendar.getInstance();
		cal2.set(Calendar.MONTH, 1);
		cal2.set(Calendar.YEAR, 2012);
		cal2.set(Calendar.DAY_OF_MONTH, 1);

		Assert.assertTrue(DateUtil.datesEqual(cal1, cal2));
	}
	
	/**
	 * Tests that Dates are Equal
	 */
	@Test
	public void datesEqualWithTimes() {
		Calendar cal1 = Calendar.getInstance();
		cal1.set(Calendar.MONTH, 1);
		cal1.set(Calendar.YEAR, 2012);
		cal1.set(Calendar.DAY_OF_MONTH, 1);
		cal1.set(Calendar.HOUR, 4);

		Calendar cal2 = Calendar.getInstance();
		cal2.set(Calendar.MONTH, 1);
		cal2.set(Calendar.YEAR, 2012);
		cal2.set(Calendar.DAY_OF_MONTH, 1);
		cal2.set(Calendar.HOUR, 6);

		Assert.assertTrue(DateUtil.datesEqual(cal1, cal2));
	}
	
	/**
	 * Tests that Dates are Not Equal
	 */
	@Test
	public void datesNotEqualDifferentMoneth() {
		Calendar cal1 = Calendar.getInstance();
		cal1.set(Calendar.MONTH, 1);
		cal1.set(Calendar.YEAR, 2012);
		cal1.set(Calendar.DAY_OF_MONTH, 1);

		Calendar cal2 = Calendar.getInstance();
		cal2.set(Calendar.MONTH, 2);
		cal2.set(Calendar.YEAR, 2012);
		cal2.set(Calendar.DAY_OF_MONTH, 1);

		Assert.assertFalse(DateUtil.datesEqual(cal1, cal2));
	}
	
	/**
	 * Tests that Dates are Not Equal
	 */
	@Test
	public void datesNotEqualDifferentYear() {
		Calendar cal1 = Calendar.getInstance();
		cal1.set(Calendar.MONTH, 1);
		cal1.set(Calendar.YEAR, 2012);
		cal1.set(Calendar.DAY_OF_MONTH, 1);

		Calendar cal2 = Calendar.getInstance();
		cal2.set(Calendar.MONTH, 1);
		cal2.set(Calendar.YEAR, 2013);
		cal2.set(Calendar.DAY_OF_MONTH, 1);

		Assert.assertFalse(DateUtil.datesEqual(cal1, cal2));
	}
	
	/**
	 * Tests that Dates are Not Equal
	 */
	@Test
	public void datesNotEqualDifferentDay() {
		Calendar cal1 = Calendar.getInstance();
		cal1.set(Calendar.MONTH, 1);
		cal1.set(Calendar.YEAR, 2012);
		cal1.set(Calendar.DAY_OF_MONTH, 1);

		Calendar cal2 = Calendar.getInstance();
		cal2.set(Calendar.MONTH, 1);
		cal2.set(Calendar.YEAR, 2012);
		cal2.set(Calendar.DAY_OF_MONTH, 4);

		Assert.assertFalse(DateUtil.datesEqual(cal1, cal2));
	}

}
