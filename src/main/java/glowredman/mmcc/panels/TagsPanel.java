package glowredman.mmcc.panels;

import java.awt.Dimension;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import glowredman.mmcc.frames.Editor;

public class TagsPanel extends JScrollPane {
	
	private static final long serialVersionUID = -8193585166429016225L;
	private JTextArea tags;
	
	public TagsPanel() {
		tags = new JTextArea();
		this.setViewportView(tags);
		this.setBorder(BorderFactory.createTitledBorder("Tags"));
		this.setMinimumSize(new Dimension(250, 400));
		this.setPreferredSize(new Dimension(250, 400));
		this.setMaximumSize(new Dimension(250, 400));
	}
	
	public void setTags(List<String> tags) {
		this.tags.setText(Editor.listAsString(tags));
	}
	
	public List<String> getTags() {
		return Editor.stringAsList(tags.getText());
	}

}
