/**
 * 
 */
package cs414.as4.btsaunde.garagesystem.service;

import java.util.Calendar;
import java.util.Map;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import cs414.as4.btsaunde.garagesystem.DataLoader;
import cs414.as4.btsaunde.garagesystem.dao.EventDao;
import cs414.as4.btsaunde.garagesystem.util.DateUtil;

/**
 * Tests the Report Service.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
public class ReportServiceTest {

	/**
	 * Start date for Tests
	 */
	private Calendar startDate;

	/**
	 * End date for Tests
	 */
	private Calendar endDate;

	/**
	 * Sets up Tests
	 */
	@Before
	public void setup() {
		this.startDate = Calendar.getInstance();
		this.startDate.set(Calendar.MONTH, 9);
		this.startDate.set(Calendar.YEAR, 2012);
		this.startDate.set(Calendar.DAY_OF_WEEK, 1);

		this.endDate = Calendar.getInstance();
		this.endDate.set(Calendar.MONTH, 9);
		this.endDate.set(Calendar.YEAR, 2012);
		this.endDate.set(Calendar.DAY_OF_WEEK, 7);

		EventDao eventDao = EventDao.getInstance();
		eventDao.clear();
	}

	/**
	 * Tests Getting the Businest Day
	 */
	@Test
	public void getBusiestDay() {
		// Load Test Data
		DataLoader loader = new DataLoader();
		Calendar activeDate = Calendar.getInstance();
		activeDate.set(Calendar.MONTH, 9);
		activeDate.set(Calendar.YEAR, 2012);
		for (int i = 1; i < 8; i++) {
			activeDate.set(Calendar.DAY_OF_WEEK, i);
			loader.loadData(2, activeDate);
		}
		loader.loadData(2, activeDate);

		Calendar result = ReportService.getBusiestDay(this.startDate,
				this.endDate);

		Assert.assertTrue(DateUtil.datesEqual(activeDate, result));
	}

	/**
	 * Tests Getting the Average Stay Per Day
	 */
	@Test
	public void getAverageStayPerDay() {
		// Load Test Data
		DataLoader loader = new DataLoader();
		Calendar activeDate = Calendar.getInstance();
		activeDate.set(Calendar.MONTH, 9);
		activeDate.set(Calendar.YEAR, 2012);
		for (int i = 1; i < 8; i++) {
			activeDate.set(Calendar.DAY_OF_WEEK, i);
			loader.loadData(2, activeDate);
		}
		
		Map<String, Double> results = ReportService.getAverageStayPerDay(this.startDate,
				this.endDate);
		
		// Should all be 20
		for(String key : results.keySet()){
			Assert.assertEquals(new Double(20), results.get(key));
		}
	}
	
	/**
	 * Tests Getting the Average Cars Per Day
	 */
	@Test
	public void getAveragePerDay() {
		// Load Test Data
		DataLoader loader = new DataLoader();
		Calendar activeDate = Calendar.getInstance();
		activeDate.set(Calendar.MONTH, 9);
		activeDate.set(Calendar.YEAR, 2012);
		for (int i = 1; i < 8; i++) {
			activeDate.set(Calendar.DAY_OF_WEEK, i);
			loader.loadData(2, activeDate);
		}
		
		Map<String, Double> results = ReportService.getAveragePerDay(this.startDate,
				this.endDate);
		
		// Should all be 2
		for(String key : results.keySet()){
			Assert.assertEquals(new Double(2), results.get(key));
		}
	}
}