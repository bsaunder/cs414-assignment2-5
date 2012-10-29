/**
 * 
 */
package cs414.as5.btsaunde.garagesystem.util;

import java.util.Calendar;
import java.util.logging.Logger;

/**
 * Date Service for Working with Dates.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
public final class DateUtil {

	/**
	 * Logger
	 */
	private static final Logger LOGGER = Logger.getAnonymousLogger();

	/**
	 * Determines if the Two Dates are Equal, Ignoring the Time.
	 * 
	 * @param date1
	 *            Date 1
	 * @param date2
	 *            Date 2
	 * @return True of the D/M/Y are the same
	 */
	public static Boolean datesEqual(final Calendar date1, final Calendar date2) {
		if (date1 != null && date2 != null) {
			LOGGER.info("Comparing Dates: " + date1.getTime().toString()
					+ " - " + date2.getTime().toString());

			return date1.get(Calendar.MONTH) == date2.get(Calendar.MONTH)
					&& date1.get(Calendar.DAY_OF_MONTH) == date2
							.get(Calendar.DAY_OF_MONTH)
					&& date1.get(Calendar.YEAR) == date2.get(Calendar.YEAR);
		} else {
			return false;
		}
	}
}
