package Lamp;

public class AdjustableLamp extends Lamp implements Adjustable {
	public <B extends LightBulb & Adjustable, S extends Switch & Adjustable> AdjustableLamp(B bulb, S aSwitch) {
		super(bulb, aSwitch);
	}

	@Override
	public boolean changeBy(double step) {
		Adjustable aSwitch = (Adjustable) this.aSwitch;
		Adjustable bulb = (Adjustable) this.bulb;

		if (aSwitch.changeBy(step)) {
			if (bulb.changeTo(aSwitch.getValue())) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean changeTo(double finalValue) {
		Adjustable aSwitch = (Adjustable) this.aSwitch;
		Adjustable bulb = (Adjustable) this.bulb;

		if (aSwitch.changeTo(finalValue)) {
			if (bulb.changeTo(aSwitch.getValue())) {
				return true;
			}
		}
		return false;
	}

	@Override
	public double getValue() {
		return ((Adjustable) aSwitch).getValue();
	}
}
