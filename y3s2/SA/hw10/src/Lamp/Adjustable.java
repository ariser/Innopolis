package Lamp;

public interface Adjustable {
	boolean changeBy(double step);

	boolean changeTo(double finalValue);

	double getValue();
}
