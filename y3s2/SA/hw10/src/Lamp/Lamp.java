package Lamp;

public class Lamp implements Switchable, Breakable, Fixable {
	protected LightBulb bulb;
	protected Switch aSwitch;

	private boolean isLit;

	public Lamp(LightBulb bulb, Switch aSwitch) {
		isLit = false;
		this.bulb = bulb;
		this.aSwitch = aSwitch;
	}

	public void setBulb(LightBulb bulb) {
		this.bulb = bulb;
	}

	@Override
	public boolean toggle() {
		return isLit ? off() : on();
	}

	@Override
	public boolean on() {
		if (isOn()) {
			System.out.println("Already on.");
			return false;
		}

		if (aSwitch.on()) {
			if (bulb.on()) {
				isLit = true;
				return true;
			} else {
				if (bulb.isBroken()) {
					System.out.println("The light bulb is broken.");
				} else {
					System.out.println("Something went wrong.");
				}
			}
		} else {
			if (aSwitch.isBroken()) {
				System.out.println("The switch is broken.");
			} else {
				System.out.println("Something went wrong.");
			}
		}

		return false;
	}

	@Override
	public boolean off() {
		if (!isLit) {
			System.out.println("Already off.");
			return false;
		}

		if (aSwitch.off()) {
			bulb.off();
			isLit = false;
			return true;
		} else {
			if (aSwitch.isBroken()) {
				System.out.println("The switch is broken.");
			} else {
				System.out.println("Something went wrong.");
			}
		}

		return false;
	}

	@Override
	public boolean isOn() {
		return isLit;
	}

	@Override
	public void fix() {
		if (!isBroken()) {
			System.out.println("Ain't broken.");
			return;
		}

		if (aSwitch.isBroken()) {
			aSwitch.fix();
		}

		if (bulb.isBroken()) {
			System.out.println("THe bulb is broken. Replace it.");
		}
	}

	@Override
	public boolean isBroken() {
		return bulb.isBroken() || aSwitch.isBroken();
	}

	@Override
	public void broke() {
		// broke 'em all
		bulb.broke();
		aSwitch.broke();
	}
}
