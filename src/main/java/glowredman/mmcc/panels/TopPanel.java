package glowredman.mmcc.panels;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import glowredman.mmcc.MethodAction;
import glowredman.mmcc.frames.Editor;

public abstract class TopPanel<T> extends JPanel {
	
	private static final long serialVersionUID = -5275091548038115322L;
	protected Editor<T> editorFrame;
	private final JScrollPane scrollPane;
	protected final JList<String> list;
	private final DefaultListModel<String> listModel;
	private Map<String, T> map;
	private final String fileName;
	protected boolean addNew;
	
	public TopPanel(String fileName) {
		this.map = new LinkedHashMap<>();
		this.listModel = new DefaultListModel<>();
		this.list = new JList<>(listModel);
		this.scrollPane = new JScrollPane(list);
		this.fileName = fileName;
		
		lock();

		list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		
		this.setEnabled(false);
		this.setBorder(BorderFactory.createTitledBorder(fileName));
		this.setPreferredSize(new Dimension(150, 400));
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.add(scrollPane);

		setKeyBindings(this::add,    "add",    KeyEvent.VK_F1, KeyEvent.VK_INSERT, KeyEvent.VK_PLUS, KeyEvent.VK_ADD);
		setKeyBindings(this::edit,   "edit",   KeyEvent.VK_F2, KeyEvent.VK_ENTER);
		setKeyBindings(this::delete, "delete", KeyEvent.VK_F3, KeyEvent.VK_DELETE, KeyEvent.VK_MINUS, KeyEvent.VK_SUBTRACT, KeyEvent.VK_BACK_SPACE);
		setKeyBindings(this::up,     "up",     KeyEvent.VK_F4);
		setKeyBindings(this::down,   "down",   KeyEvent.VK_F5);
	}
	
	private void add(ActionEvent e) {
		if(list.getSelectedIndices().length != 1) return;
		
		lock();
		addNew = true;
		
		editorFrame.identifier = "";
		editorFrame.setObject(this.getNew());
		editorFrame.setVisible(true);
		
	}
	
	private void edit(ActionEvent e) {
		int[] selected = list.getSelectedIndices();
		if(selected.length != 1) return;
		
		lock();
		addNew = false;
		
		editorFrame.identifier = listModel.elementAt(selected[0]);
		editorFrame.setObject(map.get(editorFrame.identifier));
		editorFrame.setVisible(true);
	}
	
	private void delete(ActionEvent e) {
		int min = list.getMinSelectionIndex();
		int max = list.getMaxSelectionIndex();
		if(min == -1) return;
		listModel.removeRange(min, max);
		if(listModel.size() > 0) {
			if(min == listModel.size()) {
				list.setSelectedIndex(min - 1);
			} else {
				list.setSelectedIndex(min);
			}
		}
	}
	
	private void up(ActionEvent e) {
		int[] selection = list.getSelectedIndices();
		if(selection.length == 1 && selection[0] > 0) {
			int oldPos = selection[0];
			int newPos = oldPos - 1;
			String selected = listModel.get(oldPos);
			String other = listModel.get(newPos);
			listModel.set(newPos, selected);
			listModel.set(oldPos, other);
			list.setSelectedIndex(newPos);
		}
	}
	
	private void down(ActionEvent e) {
		int[] selection = list.getSelectedIndices();
		if(selection.length == 1 && selection[0] < listModel.size() - 1) {
			int oldPos = selection[0];
			int newPos = oldPos + 1;
			String selected = listModel.get(oldPos);
			String other = listModel.get(newPos);
			listModel.set(newPos, selected);
			listModel.set(oldPos, other);
			list.setSelectedIndex(newPos);
		}
	}
	
	public void unlock() {
		this.list.setEnabled(true);
	}
	
	public void lock() {
		this.list.setEnabled(false);
	}
	
	public void set(String identifier, T value) {
		if(addNew) {
			listModel.insertElementAt(identifier, list.getSelectedIndex() + 1);
		} else {
			listModel.set(list.getSelectedIndex(), identifier);
		}
		map.put(identifier, value);
	}
	
	private Map<String, T> getSorted() {
		Map<String, T> newMap = new LinkedHashMap<>();
		for(int i = 0; i < listModel.size(); i++) {
			String element = listModel.get(i);
			newMap.put(element, map.get(element));
		}
		return newMap;
	}
	
	protected abstract Type getType();
	
	protected abstract T getNew();
	
	public void read(File configDir) {
		try {
			Gson gson = new Gson();
			map.clear();
			listModel.clear();
			map = gson.fromJson(new BufferedReader(new FileReader(new File(configDir, fileName), StandardCharsets.UTF_8)), getType());
			if(map == null) map = new LinkedHashMap<>();
			listModel.addAll(map.keySet());
			unlock();
			this.setEnabled(true);
			System.out.println("Found " + fileName + " with " + map.size() + " entries");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void write(File confFile, String dateAndTime) {
		try {
			File currentFile = new File(confFile, fileName);
			String newFileName = "mmcc_backup/" + fileName.replace(".json", "") + "-before-" + dateAndTime + ".json";
			Files.copy(currentFile.toPath(), confFile.toPath().resolve(newFileName), StandardCopyOption.COPY_ATTRIBUTES);
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			BufferedWriter w = new BufferedWriter(new FileWriter(currentFile, StandardCharsets.UTF_8));
			w.write(gson.toJson(getSorted(), getType()));
			w.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void setKeyBindings(ActionListener action, String name, int... keys) {
		for(int i : keys) {
			this.list.getInputMap().put(KeyStroke.getKeyStroke(i, 0, true), name);
		}
		this.list.getActionMap().put(name, new MethodAction(action));
	}

}
