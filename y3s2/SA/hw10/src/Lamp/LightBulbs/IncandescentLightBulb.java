package Lamp.LightBulbs;

import Lamp.LightBulb;

public class IncandescentLightBulb extends LightBulb {
	private static final int TIMES_SWITCHED_BEFORE_BROKE = 10;
	private int timesSwitched = 0;

	@Override
	public boolean on() {
		boolean success = super.on();
		if (success) {
			timesSwitched++;
			if (timesSwitched >= TIMES_SWITCHED_BEFORE_BROKE) {
				brokeWithSparkles();
			}
		}
		return success;
	}

	@Override
	public boolean off() {
		boolean success = super.off();
		if (success) {
			timesSwitched++;
			if (timesSwitched >= TIMES_SWITCHED_BEFORE_BROKE) {
				broke();
			}
		}
		return success;
	}

	private void brokeWithSparkles() {
		System.out.println("Puff! The bulb exploded.");
		broke();
	}
}
