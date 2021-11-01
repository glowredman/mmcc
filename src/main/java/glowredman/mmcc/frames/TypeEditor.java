package glowredman.mmcc.frames;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

import glowredman.mmcc.panels.IdentifierPanel;
import glowredman.mmcc.panels.LabeledComboBox;
import glowredman.mmcc.panels.LabeledTextField;
import glowredman.mmcc.panels.TagsPanel;
import glowredman.mmcc.panels.TopPanel;
import glowredman.modularmaterials.data.object.MM_Type;
import glowredman.modularmaterials.data.object.sub.Category;
import glowredman.modularmaterials.data.object.sub.ChemicalState;

public class TypeEditor extends Editor<MM_Type> {
	
	private static final long serialVersionUID = 8785693131214416569L;
	private IdentifierPanel enabled_identifier;
	private LabeledTextField name;
	private LabeledComboBox<Category> category;
	private LabeledComboBox<ChemicalState> state;
	private LabeledTextField burnTime;
	private LabeledTextField enchantPower;
	private LabeledTextField fireSpread;
	private LabeledTextField flammability;
	private LabeledTextField jumpFactor;
	private LabeledTextField lightLevel;
	private LabeledTextField resistance;
	private LabeledTextField speedFactor;
	private JCheckBox hasTooltip;
	private LabeledTextField syntax;
	private TagsPanel tags;

	public TypeEditor(TopPanel<MM_Type> parent) {
		super(parent);
		this.setTitle("Edit types.json Entry");
		this.setResizable(false);
		this.setLayout(new GridBagLayout());
		
		//First Column
		GridBagConstraints cst = new GridBagConstraints();
		cst.anchor = GridBagConstraints.SOUTHWEST;
		
		enabled_identifier = new IdentifierPanel();
		cst.gridx = 0;
		cst.gridy = 0;
		this.add(enabled_identifier, cst);
		
		category = new LabeledComboBox<>("Category", Category.values());
		cst.gridy = 1;
		this.add(category, cst);
		
		state = new LabeledComboBox<>("State", ChemicalState.values());
		cst.gridy = 2;
		this.add(state, cst);
		
		name = new LabeledTextField("Tag Name");
		cst.gridy = 3;
		this.add(name, cst);
		
		syntax = new LabeledTextField("Name Syntax");
		cst.gridy = 4;
		this.add(syntax, cst);
		
		hasTooltip = new JCheckBox("Has Tooltip");
		cst.gridy = 5;
		this.add(hasTooltip, cst);
		
		JPanel mPanel = new JPanel(new GridBagLayout());
		mPanel.setBorder(BorderFactory.createTitledBorder("Multiplier"));
		
		GridBagConstraints mCst = new GridBagConstraints();
		
		burnTime = new LabeledTextField("Burn Time");
		mCst.gridx = 0;
		mCst.gridy = 0;
		mPanel.add(burnTime, mCst);
		
		enchantPower = new LabeledTextField("Enchantment Power Bonus");
		mCst.gridy = 1;
		mPanel.add(enchantPower, mCst);
		
		fireSpread = new LabeledTextField("Fire Spread Speed");
		mCst.gridy = 2;
		mPanel.add(fireSpread, mCst);
		
		flammability = new LabeledTextField("Flammability");
		mCst.gridy = 3;
		mPanel.add(flammability, mCst);
		
		jumpFactor = new LabeledTextField("Jump Factor");
		mCst.gridy = 4;
		mPanel.add(jumpFactor, mCst);
		
		lightLevel = new LabeledTextField("Light Level");
		mCst.gridy = 5;
		mPanel.add(lightLevel, mCst);
		
		resistance = new LabeledTextField("Blast Resistance");
		mCst.gridy = 6;
		mPanel.add(resistance, mCst);
		
		speedFactor = new LabeledTextField("Speed Factor");
		mCst.gridy = 7;
		mPanel.add(speedFactor, mCst);
		
		cst.gridy = 6;
		this.add(mPanel, cst);
		
		JButton doneButton = new JButton("Done");
		doneButton.addActionListener(this::save);
		cst.anchor = GridBagConstraints.EAST;
		cst.gridy = 7;
		this.add(doneButton, cst);
		
		//Second Column
		tags = new TagsPanel();
		cst.anchor = GridBagConstraints.SOUTHWEST;
		cst.gridx = 1;
		cst.gridy = 0;
		cst.gridheight = 7;
		this.add(tags, cst);
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(this::cancel);
		cst.gridy = 7;
		this.add(cancelButton, cst);
		
		this.pack();
	}

	@Override
	public void setObject(MM_Type o) {
		enabled_identifier.setEnabledState(o.enabled);
		enabled_identifier.setKey(identifier);
		category.setSelected(o.category);
		state.setSelected(o.state);
		name.setText(o.tagName);
		syntax.setText(o.nameSyntax);
		hasTooltip.setSelected(o.hasTooltip);
		burnTime.setText(o.burnTimeMultiplier);
		enchantPower.setText(o.enchantPowerBonusMultiplier);
		fireSpread.setText(o.fireSpreadSpeedMultiplier);
		flammability.setText(o.flammabilityMultiplier);
		jumpFactor.setText(o.jumpFactorMultiplier);
		lightLevel.setText(o.lightLevelMultiplier);
	    resistance.setText(o.resistanceMultiplier);
	    speedFactor.setText(o.speedFactorMultiplier);
		object = o;
	}

	@Override
	protected void save(ActionEvent e) {
		object.burnTimeMultiplier = burnTime.getTextF(object.burnTimeMultiplier);
		object.category = category.getSelected();
		object.enabled = enabled_identifier.getEnabledState();
		object.enchantPowerBonusMultiplier = enchantPower.getTextF(object.enchantPowerBonusMultiplier);
		object.fireSpreadSpeedMultiplier = fireSpread.getTextF(object.fireSpreadSpeedMultiplier);
		object.flammabilityMultiplier = flammability.getTextF(object.flammabilityMultiplier);
		object.hasTooltip = hasTooltip.isSelected();
		object.jumpFactorMultiplier = jumpFactor.getTextF(object.jumpFactorMultiplier);
		object.lightLevelMultiplier = lightLevel.getTextF(object.lightLevelMultiplier);
		object.nameSyntax = syntax.getText();
		object.resistanceMultiplier = resistance.getTextF(object.resistanceMultiplier);
		object.speedFactorMultiplier = speedFactor.getTextF(object.speedFactorMultiplier);
		object.state = state.getSelected();
		object.tagName = name.getText();
		object.tags = tags.getTags();
		parent.set(enabled_identifier.getKey(), object);
		parent.unlock();
		this.setVisible(false);
	}

}
