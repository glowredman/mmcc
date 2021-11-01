package glowredman.mmcc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;

public class MethodAction extends AbstractAction {
	
	private ActionListener action;
	
	public MethodAction(ActionListener action) {
		this.action = action;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		action.actionPerformed(e);
	}

	private static final long serialVersionUID = 6608068217528825813L;

}
