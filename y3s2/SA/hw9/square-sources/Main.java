import Square.Model;
import Square.ViewController;

/**
 * Question 1
 * In the Square application rendering is achieved by the means of Listeners.
 * An ActionListener for the button handles the update of the output field. Once a user presses the "Square" button
 * the listener takes the value of the input field, puts it into the model and updates the output field value
 * accordingly to the newly fetched input.
 * A DocumentListener for the input field intersects any change of the input field made by user, resets the model
 * and clears the output field, so that no output would be shown to a user in the indeterminate state of the application.
 */

public class Main {
	public static void main(String[] args) {
		Model M = new Model();
		ViewController VC = new ViewController(M);
		VC.setVisible(true);
	}
}