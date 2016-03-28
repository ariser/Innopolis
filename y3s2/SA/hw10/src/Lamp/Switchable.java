package Lamp;

public interface Switchable {
	/**
	 * Toggles the state
	 *
	 * @return whether the toggling was successful
	 */
	boolean toggle();

	/**
	 * @return whether the turning on was successful
	 */
	boolean on();

	/**
	 * @return whether the turning off was successful
	 */
	boolean off();

	boolean isOn();
}
