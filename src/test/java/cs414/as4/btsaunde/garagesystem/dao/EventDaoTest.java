/**
 * 
 */
package cs414.as4.btsaunde.garagesystem.dao;

import junit.framework.Assert;

import org.junit.Test;

/**
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
public class EventDaoTest {

	/**
	 * Tests Dao Creation.
	 */
	@Test
	public void ifCreatedThenPass() {
		EventDao dao = EventDao.getInstance();

		Assert.assertNotNull(dao);
	}

	/**
	 * Tests Dao Singleton.
	 */
	@Test
	public void ifSingletonThenPass() {
		EventDao dao = EventDao.getInstance();
		EventDao dao2 = EventDao.getInstance();

		Assert.assertNotNull(dao);
		Assert.assertNotNull(dao2);
		Assert.assertEquals(dao, dao2);
	}

}
