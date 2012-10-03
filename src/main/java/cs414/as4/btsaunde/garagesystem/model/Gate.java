package cs414.as4.btsaunde.garagesystem.model;

/**
 * Represents a Single Gate.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
public class Gate {

	/**
	 * Gate Status
	 */
	private boolean open;

	/**
	 * Get the open.
	 * 
	 * @return the open
	 */
	public boolean isOpen() {
		return this.open;
	}

	/**
	 * Set the open.
	 * 
	 * @param open
	 *            the open to set
	 */
	private void setOpen(boolean open) {
		this.open = open;
	}

	/**
	 * Open the Gate
	 */
	public void openGate() {
		this.setOpen(true);
	}

	/**
	 * Close the Gate
	 */
	public void closeGate() {
		this.setOpen(false);
	}
}
