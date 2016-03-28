package Lamp.LightBulbs;

import Lamp.Adjustable;
import Lamp.LightBulb;

public class LEDLightBulb extends LightBulb implements Adjustable {
	private static final double MAX_CAPACITY = 1;
	private static final double MIN_CAPACITY = 0;
	private static final double MIN_STEP_ABS = 0.1;

	private double capacity;

	public LEDLightBulb() {
		capacity = isOn() ? MAX_CAPACITY : MIN_CAPACITY;
	}

	@Override
	public boolean changeBy(double step) {
		double oldCapacity = capacity;
		step = normalizeStep(step);
		capacity = normalizeCapacity(capacity + step);
		if (capacity != oldCapacity) {
			if (capacity == MIN_CAPACITY) off();
			else on();
			return true;
		}
		return false;
	}

	@Override
	public boolean changeTo(double finalValue) {
		double oldCapacity = capacity;
		capacity = normalizeCapacity(finalValue);
		if (capacity != oldCapacity) {
			if (capacity == MIN_CAPACITY) off();
			else on();
			return true;
		}
		return false;
	}

	@Override
	public double getValue() {
		return capacity;
	}

	@Override
	public boolean on() {
		boolean success = super.on();
		if (success) changeBy(MIN_STEP_ABS);
		return success;
	}

	@Override
	public boolean off() {
		boolean success = super.off();
		if (success) changeTo(MIN_CAPACITY);
		return success;
	}

	private double normalizeStep(double step) {
		if (step > 0) return Math.max(step, MIN_STEP_ABS);
		else return Math.min(step, -MIN_STEP_ABS);
	}

	private double normalizeCapacity(double capacity) {
		return Math.max(Math.min(capacity, MAX_CAPACITY), MIN_CAPACITY);
	}
}
