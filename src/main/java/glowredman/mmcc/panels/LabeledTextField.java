package glowredman.mmcc.panels;

import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LabeledTextField extends JPanel {
	
	private static final long serialVersionUID = 3840431008411414662L;
	public JTextField text;
	
	public LabeledTextField(String name) {
		JLabel label = new JLabel(name);
		text = new JTextField();
		label.setMinimumSize(new Dimension(75, 20));
		label.setPreferredSize(new Dimension(75, 20));
		label.setMaximumSize(new Dimension(75, 20));
		text.setMinimumSize(new Dimension(150, 20));
		text.setPreferredSize(new Dimension(150, 20));
		text.setMaximumSize(new Dimension(150, 20));
		this.add(label);
		this.add(text);
	}
	
	public String getText() {
		return text.getText();
	}
	
	public int getTextI(int prev) {
		try {
			return Integer.parseInt(text.getText());
		} catch (Exception e) {
			e.printStackTrace();
			return prev;
		}
	}
	
	public float getTextF(float prev) {
		try {
			return Float.parseFloat(text.getText());
		} catch (Exception e) {
			e.printStackTrace();
			return prev;
		}
	}
	
	public void setText(int t) {
		text.setText(String.valueOf(t));
	}
	
	public void setText(float t) {
		text.setText(String.valueOf(t));
	}
	
	public void setText(String t) {
		text.setText(t);
	}

}
