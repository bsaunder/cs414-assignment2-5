package cs414.as5.btsaunde.garagesystem.security;

import cs414.as5.btsaunde.garagesystem.model.Attendant;

/**
 * Represents a security Identity Within the Application.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 */
public class Identity {

	/**
	 * Singleton Instance.
	 */
	private static Identity instance;

	/**
	 * Attendant of the Identity.
	 */
	private Attendant attendant;

	/**
	 * Authentication Status.
	 */
	private Boolean authenticated;

	private Identity(final Attendant newAttendant) {
		this.authenticated = false;
		this.attendant = newAttendant;
	}

	/**
	 * Get an Instance of the Current Identity.
	 * 
	 * @return Current Identity.
	 */
	public static Identity getNewInstance(final Attendant newAttendant) {
		Identity.instance = new Identity(newAttendant);
		return Identity.instance;
	}

	/**
	 * Get an Instance of the Current Identity.
	 * 
	 * @return Current Identity.
	 */
	public static Identity getInstance() {
		return Identity.instance;
	}

	/**
	 * Get the attendant.
	 * 
	 * @return the attendant
	 */
	public Attendant getAttendant() {
		return this.attendant;
	}

	/**
	 * Set the attendant.
	 * 
	 * @param attendant
	 *            the attendant to set
	 */
	public void setAttendant(Attendant attendant) {
		this.attendant = attendant;
	}

	/**
	 * Get the authenticated.
	 * 
	 * @return the authenticated
	 */
	public Boolean isAuthenticated() {
		return this.authenticated;
	}

	/**
	 * Set the authenticated.
	 * 
	 * @param authenticated
	 *            the authenticated to set
	 */
	private void setAuthenticated(Boolean authenticated) {
		this.authenticated = authenticated;
	}

	/**
	 * Logs out the Current User.
	 */
	public void logout() {
		this.setAuthenticated(false);
	}

	/**
	 * Checks the PIN provided and Logs in the Current Attendant if the PIN is
	 * Valid.
	 * 
	 * @param pin
	 *            Entered PIN
	 * @return true if the User is Authenticated.
	 */
	public boolean login(String pin) {
		String attendantPin = this.attendant.getPin().toString();
		if (attendantPin.equalsIgnoreCase(pin)) {
			this.setAuthenticated(true);
		}

		return this.isAuthenticated();
	}

}
