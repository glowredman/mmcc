package glowredman.mmcc.frames;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

import glowredman.mmcc.panels.IdentifierPanel;
import glowredman.mmcc.panels.LabeledTextField;
import glowredman.mmcc.panels.TagsPanel;
import glowredman.mmcc.panels.TopPanel;
import glowredman.modularmaterials.data.object.MM_OreVariant;

public class VariantEditor extends Editor<MM_OreVariant> {
	
	private static final long serialVersionUID = -8604490226423285097L;
	private IdentifierPanel enabled_identifier;
	private JCheckBox falling;
	private LabeledTextField name;
	private LabeledTextField block;
	private LabeledTextField texture;
	private LabeledTextField syntax;
	private TagsPanel tags;

	public VariantEditor(TopPanel<MM_OreVariant> parent) {
		super(parent);
		this.setTitle("Edit orevariants.json Entry");
		this.setResizable(false);
		this.setLayout(new GridBagLayout());
		
		GridBagConstraints cst = new GridBagConstraints();
		cst.anchor = GridBagConstraints.WEST;
		
		enabled_identifier = new IdentifierPanel();
		cst.gridx = 0;
		cst.gridy = 0;
		this.add(enabled_identifier, cst);
		
		falling = new JCheckBox("Obeys Gravity");
		cst.gridy = 1;
		this.add(falling, cst);
		
		name = new LabeledTextField("Variant Name");
		cst.gridy = 2;
		this.add(name, cst);
		
		block = new LabeledTextField("Base Block");
		cst.gridy = 3;
		this.add(block, cst);
		
		texture = new LabeledTextField("Base Texture");
		cst.gridy = 4;
		this.add(texture, cst);
		
		syntax = new LabeledTextField("Name Syntax");
		cst.gridy = 5;
		this.add(syntax, cst);
		
		tags = new TagsPanel();
		cst.gridy = 6;
		this.add(tags, cst);
		
		JPanel panel = new JPanel();
		JButton doneButton = new JButton("Done");
		doneButton.addActionListener(this::save);
		panel.add(doneButton);
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(this::cancel);
		panel.add(cancelButton);
		cst.anchor = GridBagConstraints.CENTER;
		cst.gridy = 7;
		this.add(panel, cst);
		
		this.pack();
	}

	@Override
	public void setObject(MM_OreVariant o) {
		enabled_identifier.setEnabledState(o.enabled);
		enabled_identifier.setKey(identifier);
		falling.setSelected(o.falling);
		name.setText(o.variantName);
		block.setText(o.baseBlock);
		texture.setText(o.baseTexture);
		syntax.setText(o.nameSyntax);
		tags.setTags(o.tags);
		object = o;
	}

	@Override
	protected void save(ActionEvent e) {
		object.baseBlock = block.getText();
		object.baseTexture = texture.getText();
		object.enabled = enabled_identifier.getEnabledState();
		object.falling = falling.isSelected();
		object.nameSyntax = syntax.getText();
		object.tags = tags.getTags();
		object.variantName = name.getText();
		parent.set(enabled_identifier.getKey(), object);
		parent.unlock();
		this.setVisible(false);
	}

}
