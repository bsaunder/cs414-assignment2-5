package cs414.as4.btsaunde.garagesystem.model;

/**
 * Represents an Attendant.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
public class Attendant {

	/**
	 * Name.
	 */
	private String name;

	/**
	 * Login PIN.
	 */
	private Integer pin;

	/**
	 * Creates an Attendant.
	 * 
	 * @param name
	 *            Name.
	 */
	public Attendant(String name) {
		super();
		this.name = name;
	}

	/**
	 * Get the name.
	 * 
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Set the name.
	 * 
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Get the pin.
	 * 
	 * @return the pin
	 */
	public Integer getPin() {
		return this.pin;
	}

	/**
	 * Set the pin.
	 * 
	 * @param pin
	 *            the pin to set
	 */
	public void setPin(Integer pin) {
		this.pin = pin;
	}

}
