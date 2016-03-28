package Lamp;


public abstract class LightBulb implements Switchable, Breakable {
	private boolean isOn = false;
	private boolean isBroken = false;

	@Override
	public boolean toggle() {
		return isOn ? off() : on();
	}

	@Override
	public boolean on() {
		if (isBroken() || isOn()) {
			return false;
		}

		isOn = true;
		return true;
	}

	@Override
	public boolean off() {
		if (isBroken() || !isOn()) {
			return false;
		}

		isOn = false;
		return true;
	}

	@Override
	public boolean isOn() {
		return isOn;
	}

	@Override
	public boolean isBroken() {
		return isBroken;
	}

	@Override
	public void broke() {
		isOn = false;
		isBroken = true;
	}
}
