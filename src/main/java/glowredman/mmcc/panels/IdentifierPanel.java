package glowredman.mmcc.panels;

import java.awt.Dimension;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class IdentifierPanel extends JPanel {
	
	private static final long serialVersionUID = -40591255661383007L;
	private JTextField key;
	private JCheckBox enabled;
	
	public IdentifierPanel() {
		JLabel identLabel = new JLabel("Identifier");
		key = new JTextField();
		enabled = new JCheckBox();
		identLabel.setMinimumSize(new Dimension(75, 20));
		identLabel.setPreferredSize(new Dimension(75, 20));
		identLabel.setMaximumSize(new Dimension(75, 20));
		key.setMinimumSize(new Dimension(125, 20));
		key.setPreferredSize(new Dimension(125, 20));
		key.setMaximumSize(new Dimension(125, 20));
		enabled.setToolTipText("Enabled");
		this.add(identLabel);
		this.add(key);
		this.add(enabled);
	}
	
	public boolean getEnabledState() {
		return enabled.isSelected();
	}
	
	public void setEnabledState(boolean b) {
		enabled.setSelected(b);
	}
	
	public String getKey() {
		return key.getText();
	}
	
	public void setKey(String t) {
		key.setText(t);
	}

}
