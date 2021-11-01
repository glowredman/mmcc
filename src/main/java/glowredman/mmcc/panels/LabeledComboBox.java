package glowredman.mmcc.panels;

import java.awt.Dimension;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LabeledComboBox<E> extends JPanel {
	
	private static final long serialVersionUID = 1747551096358857336L;
	private JComboBox<E> box;
	
	public LabeledComboBox(String text, E[] values) {
		JLabel label = new JLabel(text);
		box = new JComboBox<>(values);
		label.setMinimumSize(new Dimension(75, 20));
		label.setPreferredSize(new Dimension(75, 20));
		label.setMaximumSize(new Dimension(75, 20));
		label.setToolTipText(text);
		box.setMinimumSize(new Dimension(150, 20));
		box.setPreferredSize(new Dimension(150, 20));
		box.setMaximumSize(new Dimension(150, 20));
		box.setToolTipText(text);
		this.add(label);
		this.add(box);
	}
	
	public void setSelected(E value) {
		box.setSelectedItem(value);
	}
	
	@SuppressWarnings("unchecked")
	public E getSelected() {
		return (E) box.getSelectedItem();
	}

}
