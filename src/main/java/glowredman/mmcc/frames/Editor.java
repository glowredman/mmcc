package glowredman.mmcc.frames;

import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.KeyStroke;

import glowredman.mmcc.MethodAction;
import glowredman.mmcc.panels.TopPanel;

public abstract class Editor<T> extends JFrame implements WindowListener {

	private static final long serialVersionUID = 9104601877871319607L;
	public String identifier;
	protected T object;
	private T clipboard;
	protected final TopPanel<T> parent;

	public Editor(TopPanel<T> parent) {
		this.parent = parent;
		this.addWindowListener(this);
		this.getRootPane().getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_DOWN_MASK | InputEvent.SHIFT_DOWN_MASK), "copy");
		this.getRootPane().getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_DOWN_MASK | InputEvent.SHIFT_DOWN_MASK), "paste");
		this.getRootPane().getActionMap().put("copy", new MethodAction(this::copy));
		this.getRootPane().getActionMap().put("paste", new MethodAction(this::paste));
	}

	@Override
	public void windowClosing(WindowEvent e) {
		parent.unlock();
	}
	
	private void copy(ActionEvent e) {
		clipboard = object;
	}
	
	private void paste(ActionEvent e) {
		setObject(clipboard);
	}
	
	protected void cancel(ActionEvent e) {
		parent.unlock();
		this.setVisible(false);
	}
	
	protected abstract void save(ActionEvent e);

	public abstract void setObject(T o);
	
	// --- Helper methods ---

	public static String listAsString(List<String> l) {
		if (l.size() == 0) {
			return "";
		}
		String s = l.get(0);
		for (int i = 1; i < l.size(); i++) {
			s += "\n" + l.get(i);
		}
		return s;
	}
	
	public static List<String> stringAsList(String s) {
		if(s.isEmpty()) {
			return new ArrayList<>();
		} else {
			return Arrays.asList(s.split("\n"));
		}
	}

	public static String arrayAsString(String[] a) {
		if (a.length == 0) {
			return "";
		}
		String s = a[0];
		for (int i = 1; i < a.length; i++) {
			s += "\n" + a[i];
		}
		return s;
	}
	
	public static String[] stringAsArray(String s) {
		return s.split("\n");
	}

	@Override
	public void windowOpened(WindowEvent e) {}

	@Override
	public void windowClosed(WindowEvent e) {}

	@Override
	public void windowIconified(WindowEvent e) {}

	@Override
	public void windowDeiconified(WindowEvent e) {}

	@Override
	public void windowActivated(WindowEvent e) {}

	@Override
	public void windowDeactivated(WindowEvent e) {}

}
