package cs414.as4.btsaunde.garagesystem.enums;

/**
 * Report Types.
 * 
 * @author Bryan Saunders <bsaunder@redhat.com>
 * 
 */
public enum ReportType {

	AVG_DAY("Avg Cars per Day"), AVG_HOUR("Avg Cars per Hour"), AVG_STAY_DAY(
			"Avg Stay per Day"), AVG_STAY_HOUR("Avg Stay per Hour"), MOST_DAY(
			"Most Cars in Single Day");

	/**
	 * String Value
	 */
	private String value;

	/**
	 * Constructor
	 * 
	 * @param value
	 *            String Value
	 */
	private ReportType(String value) {
		this.value = value;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return this.value;
	}
}
