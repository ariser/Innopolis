package Square;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewController extends JFrame {
	private JTextField input = new JTextField(5);
	private JTextField output = new JTextField(10);
	private JButton sqrBtn = new JButton("Square");
	//
	private Model M;

	public ViewController(Model m) {
		M = m;

		// Layout
		JPanel content = new JPanel();
		content.add(input);
		content.add(sqrBtn);
		content.add(output);
		//
		this.setContentPane(content);
		this.pack();
		//
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		addSquareListener(new MyListener());
		addInputListener(new InputUpdateListener());
	}

	public String getNumber() {
		return input.getText();
	}

	public void setNumber(String v) {
		output.setText(v);
	}

	public void resetOutput() {
		output.setText("");
	}

	private void addSquareListener(ActionListener a) {
		sqrBtn.addActionListener(a);
	}

	private void addInputListener(DocumentListener listener) {
		input.getDocument().addDocumentListener(listener);
	}

	private class MyListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String userinput = getNumber();
			M.setValue(Integer.parseInt(userinput));
			setNumber(Integer.toString(M.square()));
		}
	}

	private class InputUpdateListener implements DocumentListener {
		@Override
		public void insertUpdate(DocumentEvent e) {
			M.reset();
			resetOutput();
		}

		@Override
		public void removeUpdate(DocumentEvent e) {
			M.reset();
			resetOutput();
		}

		@Override
		public void changedUpdate(DocumentEvent e) {
			M.reset();
			resetOutput();
		}
	}
}
