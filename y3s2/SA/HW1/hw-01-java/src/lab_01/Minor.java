package lab_01;

public class Minor extends Uber {
	public Minor() { super(y); y = y + 3; }
	
	public static void main(String[] args) {
		new Minor();
		System.out.println(y);
	}
}
