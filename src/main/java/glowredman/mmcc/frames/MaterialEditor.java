package glowredman.mmcc.frames;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import glowredman.mmcc.panels.IdentifierPanel;
import glowredman.mmcc.panels.LabeledComboBox;
import glowredman.mmcc.panels.LabeledTextField;
import glowredman.mmcc.panels.TagsPanel;
import glowredman.mmcc.panels.TopPanel;
import glowredman.modularmaterials.data.object.MM_Material;
import glowredman.modularmaterials.data.object.sub.AdvRarity;
import glowredman.modularmaterials.data.object.sub.ChemicalState;

public class MaterialEditor extends Editor<MM_Material> {
	
	private static final long serialVersionUID = -9095933751519104032L;
	//first column
	private IdentifierPanel enabled_identifier;
	private LabeledTextField name;
	private LabeledTextField burnTime;
	private LabeledTextField enchantPower;
	private LabeledTextField fireSpread;
	private LabeledTextField flammability;
	private LabeledTextField jumpFactor;
	private LabeledTextField lightLevel;
	private LabeledTextField resistance;
	private LabeledTextField speedFactor;
	private LabeledTextField texture;
	private LabeledComboBox<ChemicalState> state;
	private JButton color;
	private JTextArea tagNames;
	private JTextArea enabledTypes;
	//second column
	private LabeledTextField oFriction;
	private LabeledTextField oHardness;
	private LabeledTextField oJumpFactor;
	private LabeledTextField oLightLevel;
	private LabeledTextField oResistance;
	private LabeledTextField oSpeedFactor;
	private JCheckBox oRequiresTool;
	private LabeledTextField oMapColor;
	private LabeledTextField oMaterial;
	private LabeledTextField oSound;
	private TagsPanel oTags;
	private JButton tBackground;
	private JButton tBorderStart;
	private JButton tBorderEnd;
	private JTextArea tText;
	//third column
	private LabeledTextField bHardness;
	private LabeledTextField bFriction;
	private LabeledTextField bMapColor;
	private LabeledTextField bMaterial;
	private LabeledTextField bSound;
	private JCheckBox bRequiresTool;
	private JCheckBox bSticky;
	private TagsPanel bTags;
	private LabeledTextField iLifespan;
	private JCheckBox iFireResistant;
	private JCheckBox iFoil;
	private LabeledComboBox<AdvRarity> iRarity;
	private TagsPanel iTags;
	//Fourth column
	private LabeledTextField fBoilingTemp;
	private LabeledTextField fMeltingTemp;
	private LabeledTextField fCurrentTemp;
	private LabeledTextField gDensity;
	private LabeledTextField gViscosity;
	private LabeledTextField gLuminosity;
	private JCheckBox gPropagatesSkylightDown;
	private JCheckBox gPathfindable;
	private LabeledTextField lDensity;
	private LabeledTextField lViscosity;
	private LabeledTextField lLuminosity;
	private JCheckBox lPropagatesSkylightDown;
	private JCheckBox lPathfindable;
	private TagsPanel fTags;

	public MaterialEditor(TopPanel<MM_Material> parent) {
		super(parent);
		this.setTitle("Edit materials.json Entry");
		this.setResizable(false);
		this.setLayout(new GridBagLayout());
		
		GridBagConstraints cst = new GridBagConstraints();
		
		//First Column
		enabled_identifier = new IdentifierPanel();
		cst.anchor = GridBagConstraints.NORTHWEST;
		cst.gridx = 0;
		cst.gridy = 0;
		this.add(enabled_identifier, cst);
		
		name = new LabeledTextField("Name");
		cst.gridy = 1;
		this.add(name, cst);
		
		burnTime = new LabeledTextField("Burn Time");
		cst.gridy = 2;
		this.add(burnTime, cst);
		
		enchantPower = new LabeledTextField("Enchantment Power Bonus");
		cst.gridy = 3;
		this.add(enchantPower, cst);
		
		fireSpread = new LabeledTextField("Fire Spread Speed");
		cst.gridy = 4;
		this.add(fireSpread, cst);
		
		flammability = new LabeledTextField("Flammability");
		cst.gridy = 5;
		this.add(flammability, cst);
		
		jumpFactor = new LabeledTextField("Jump Factor");
		cst.gridy = 6;
		this.add(jumpFactor, cst);
		
		lightLevel = new LabeledTextField("Light Level");
		cst.gridy = 7;
		this.add(lightLevel, cst);
		
		resistance = new LabeledTextField("Blast Resistance");
		cst.gridy = 8;
		this.add(resistance, cst);
		
		speedFactor = new LabeledTextField("Speed Factor");
		cst.gridy = 9;
		this.add(speedFactor, cst);
		
		texture = new LabeledTextField("Texture");
		cst.gridy = 10;
		this.add(texture, cst);
		
		state = new LabeledComboBox<>("State", ChemicalState.values());
		cst.gridy = 11;
		this.add(state, cst);
		
		color = new JButton("Color");
		color.addActionListener(this::color);
		cst.anchor = GridBagConstraints.CENTER;
		cst.gridy = 12;
		this.add(color, cst);
		
		tagNames = new JTextArea();
		JScrollPane tagNamesPanel = new JScrollPane(tagNames);
		tagNamesPanel.setBorder(BorderFactory.createTitledBorder("Tag Names"));
		tagNamesPanel.setMinimumSize(new Dimension(250, 100));
		tagNamesPanel.setPreferredSize(new Dimension(250, 100));
		tagNamesPanel.setMaximumSize(new Dimension(250, 100));
		cst.anchor = GridBagConstraints.NORTHWEST;
		cst.gridy = 13;
		this.add(tagNamesPanel, cst);
		
		enabledTypes = new JTextArea();
		JScrollPane enabledTypesPanel = new JScrollPane(enabledTypes);
		enabledTypesPanel.setBorder(BorderFactory.createTitledBorder("Enabled Types"));
		enabledTypesPanel.setMinimumSize(new Dimension(250, 300));
		enabledTypesPanel.setPreferredSize(new Dimension(250, 300));
		enabledTypesPanel.setMaximumSize(new Dimension(250, 300));
		cst.gridy = 14;
		this.add(enabledTypesPanel, cst);
		
		//Second column
		JPanel oreProps = new JPanel();
		GridBagConstraints oCst = new GridBagConstraints();
		oreProps.setBorder(BorderFactory.createTitledBorder("Ore Properties"));
		oreProps.setLayout(new GridBagLayout());
		
		oFriction = new LabeledTextField("Friction");
		oCst.anchor = GridBagConstraints.NORTHWEST;
		oCst.gridx = 0;
		oCst.gridy = 0;
		oreProps.add(oFriction, oCst);
		
		oHardness = new LabeledTextField("Hardness");
		oCst.gridy = 1;
		oreProps.add(oHardness, oCst);
		
		oJumpFactor = new LabeledTextField("Jump Factor");
		oCst.gridy = 2;
		oreProps.add(oJumpFactor, oCst);
		
		oLightLevel = new LabeledTextField("Light Level");
		oCst.gridy = 3;
		oreProps.add(oLightLevel, oCst);
		
		oResistance = new LabeledTextField("Blast Resistance");
		oCst.gridy = 4;
		oreProps.add(oResistance, oCst);
		
		oSpeedFactor = new LabeledTextField("Speed Factor");
		oCst.gridy = 5;
		oreProps.add(oSpeedFactor, oCst);
		
		oMapColor = new LabeledTextField("Map Color");
		oCst.gridy = 6;
		oreProps.add(oMapColor, oCst);
		
		oMaterial = new LabeledTextField("Material");
		oCst.gridy = 7;
		oreProps.add(oMaterial, oCst);
		
		oSound = new LabeledTextField("Sound type");
		oCst.gridy = 8;
		oreProps.add(oSound, oCst);
		
		oRequiresTool = new JCheckBox("Requires Tool For Drops");
		oCst.gridy = 9;
		oreProps.add(oRequiresTool, oCst);
		
		oTags = new TagsPanel();
		oTags.setMinimumSize(new Dimension(250, 173));
		oTags.setPreferredSize(new Dimension(250, 173));
		oTags.setMaximumSize(new Dimension(250, 173));
		oCst.gridy = 10;
		oreProps.add(oTags, oCst);
		
		cst.gridx = 1;
		cst.gridy = 0;
		cst.gridheight = 14;
		this.add(oreProps, cst);
		
		JPanel ttProps = new JPanel();
		GridBagConstraints tCst = new GridBagConstraints();
		ttProps.setBorder(BorderFactory.createTitledBorder("Tooltip Properties"));
		ttProps.setLayout(new GridBagLayout());
		
		tBackground = new JButton("Background");
		tBackground.addActionListener(this::background);
		tCst.anchor = GridBagConstraints.NORTH;
		tCst.gridx = 0;
		tCst.gridy = 0;
		ttProps.add(tBackground, tCst);
		
		tBorderStart = new JButton("Border Start");
		tBorderStart.addActionListener(this::borderStart);
		tCst.gridy = 1;
		ttProps.add(tBorderStart, tCst);
		
		tBorderEnd = new JButton("Border End");
		tBorderEnd.addActionListener(this::borderEnd);
		tCst.gridy = 2;
		ttProps.add(tBorderEnd, tCst);
		
		tText = new JTextArea();
		JScrollPane textPane = new JScrollPane(tText);
		textPane.setBorder(BorderFactory.createTitledBorder("Text"));
		textPane.setMinimumSize(new Dimension(250, 209));
		textPane.setPreferredSize(new Dimension(250, 209));
		textPane.setMaximumSize(new Dimension(250, 209));
		tCst.gridy = 3;
		ttProps.add(textPane, tCst);
		
		cst.gridx = 1;
		cst.gridy = 14;
		cst.gridheight = 1;
		this.add(ttProps, cst);
		
		//Third column
		JPanel bProps = new JPanel();
		GridBagConstraints bCst = new GridBagConstraints();
		bProps.setBorder(BorderFactory.createTitledBorder("Block Properties"));
		bProps.setLayout(new GridBagLayout());
		
		bHardness = new LabeledTextField("Hardness");
		bCst.anchor = GridBagConstraints.NORTHWEST;
		bCst.gridx = 0;
		bCst.gridy = 0;
		bProps.add(bHardness, bCst);
		
		bFriction = new LabeledTextField("Friction");
		bCst.gridy = 1;
		bProps.add(bFriction, bCst);
		
		bMapColor = new LabeledTextField("Map Color");
		bCst.gridy = 2;
		bProps.add(bMapColor, bCst);
		
		bMaterial = new LabeledTextField("Material");
		bCst.gridy = 3;
		bProps.add(bMaterial, bCst);
		
		bSound = new LabeledTextField("Sound Type");
		bCst.gridy = 4;
		bProps.add(bSound, bCst);
		
		bRequiresTool = new JCheckBox("Requires Tool For Drops");
		bCst.gridy = 5;
		bProps.add(bRequiresTool, bCst);
		
		bSticky = new JCheckBox("Sticky");
		bCst.gridy = 6;
		bProps.add(bSticky, bCst);
		
		bTags = new TagsPanel();
		bTags.setMinimumSize(new Dimension(250, 170));
		bTags.setPreferredSize(new Dimension(250, 170));
		bTags.setMaximumSize(new Dimension(250, 170));
		bCst.gridy = 7;
		bProps.add(bTags, bCst);
		
		cst.gridx = 2;
		cst.gridy = 0;
		cst.gridheight = 13;
		this.add(bProps, cst);

		JPanel iProps = new JPanel();
		GridBagConstraints iCst = new GridBagConstraints();
		iProps.setBorder(BorderFactory.createTitledBorder("Item Properties"));
		iProps.setLayout(new GridBagLayout());
		
		iLifespan = new LabeledTextField("Lifespan");
		iCst.anchor = GridBagConstraints.NORTHWEST;
		iCst.gridx = 0;
		iCst.gridy = 0;
		iProps.add(iLifespan, iCst);
		
		iFireResistant = new JCheckBox("Fire Resistant");
		iCst.gridy = 1;
		iProps.add(iFireResistant, iCst);
		
		iFoil = new JCheckBox("Holographic");
		iCst.gridy = 2;
		iProps.add(iFoil, iCst);
		
		iRarity = new LabeledComboBox<>("Rarity", AdvRarity.values());
		iCst.gridy = 3;
		iProps.add(iRarity, iCst);
		
		iTags = new TagsPanel();
		iTags.setMinimumSize(new Dimension(250, 271));
		iTags.setPreferredSize(new Dimension(250, 271));
		iTags.setMaximumSize(new Dimension(250, 271));
		iCst.gridy = 4;
		iProps.add(iTags, iCst);
		
		cst.gridx = 2;
		cst.gridy = 13;
		cst.gridheight = 2;
		this.add(iProps, cst);
		
		//Fourth column
		JPanel panel4 = new JPanel();
		GridBagConstraints cst4 = new GridBagConstraints();
		panel4.setLayout(new GridBagLayout());
		
		JPanel fProps = new JPanel();
		GridBagConstraints fCst = new GridBagConstraints();
		fProps.setBorder(BorderFactory.createTitledBorder("Fluid Properties"));
		fProps.setLayout(new GridBagLayout());
		
		fBoilingTemp = new LabeledTextField("Boiling Temperature");
		fCst.anchor = GridBagConstraints.NORTHWEST;
		fCst.gridx = 0;
		fCst.gridy = 0;
		fProps.add(fBoilingTemp, fCst);
		
		fMeltingTemp = new LabeledTextField("Melting Temperature");
		fCst.gridy = 1;
		fProps.add(fMeltingTemp, fCst);
		
		fCurrentTemp = new LabeledTextField("Current Temperature");
		fCst.gridy = 2;
		fProps.add(fCurrentTemp, fCst);

		JPanel gProps = new JPanel();
		GridBagConstraints gCst = new GridBagConstraints();
		gProps.setBorder(BorderFactory.createTitledBorder("Gas Properties"));
		gProps.setLayout(new GridBagLayout());
		
		gDensity = new LabeledTextField("Density");
		gCst.anchor = GridBagConstraints.NORTHWEST;
		gCst.gridx = 0;
		gCst.gridy = 0;
		gProps.add(gDensity, gCst);
		
		gViscosity = new LabeledTextField("Viscosity");
		gCst.gridy = 1;
		gProps.add(gViscosity, gCst);
		
		gLuminosity = new LabeledTextField("Light Level");
		gCst.gridy = 2;
		gProps.add(gLuminosity, gCst);
		
		gPropagatesSkylightDown = new JCheckBox("Propagates Skylight Down");
		gCst.gridy = 3;
		gProps.add(gPropagatesSkylightDown, gCst);
		
		gPathfindable = new JCheckBox("Pathfindable");
		gCst.gridy = 4;
		gProps.add(gPathfindable, gCst);
		
		fCst.gridy = 3;
		fProps.add(gProps, fCst);

		JPanel lProps = new JPanel();
		GridBagConstraints lCst = new GridBagConstraints();
		lProps.setBorder(BorderFactory.createTitledBorder("Liquid Properties"));
		lProps.setLayout(new GridBagLayout());
		
		lDensity = new LabeledTextField("Density");
		lCst.anchor = GridBagConstraints.NORTHWEST;
		lCst.gridx = 0;
		lCst.gridy = 0;
		lProps.add(lDensity, lCst);
		
		lViscosity = new LabeledTextField("Viscosity");
		lCst.gridy = 1;
		lProps.add(lViscosity, lCst);
		
		lLuminosity = new LabeledTextField("Light Level");
		lCst.gridy = 2;
		lProps.add(lLuminosity, lCst);
		
		lPropagatesSkylightDown = new JCheckBox("Propagates Skylight Down");
		lCst.gridy = 3;
		lProps.add(lPropagatesSkylightDown, lCst);
		
		lPathfindable = new JCheckBox("Pathfindable");
		lCst.gridy = 4;
		lProps.add(lPathfindable, lCst);
		
		fCst.gridy = 4;
		fProps.add(lProps, fCst);
		
		fTags = new TagsPanel();
		fTags.setMinimumSize(new Dimension(250, 325));
		fTags.setPreferredSize(new Dimension(250, 325));
		fTags.setMaximumSize(new Dimension(250, 325));
		fCst.gridy = 5;
		fProps.add(fTags, fCst);
		
		cst4.anchor = GridBagConstraints.NORTHEAST;
		cst4.gridx = 0;
		cst4.gridy = 0;
		panel4.add(fProps, cst4);
		
		JPanel buttonPanel = new JPanel();
		
		JButton doneButton = new JButton("Done");
		doneButton.addActionListener(this::save);
		buttonPanel.add(doneButton);
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(this::cancel);
		buttonPanel.add(cancelButton);
		
		cst4.gridy = 1;
		panel4.add(buttonPanel, cst4);
		
		cst.gridx = 3;
		cst.gridy = 0;
		cst.gridheight = 15;
		this.add(panel4, cst);
		
		this.pack();
	}
	
	private void color(ActionEvent e) {
		Color c = JColorChooser.showDialog(this, "Edit Material Color", new Color(object.color.red, object.color.green, object.color.blue, object.color.alpha), true);
		if(c != null) {
			object.color.alpha = c.getAlpha();
			object.color.blue = c.getBlue();
			object.color.green = c.getGreen();
			object.color.red = c.getRed();
		}
	}
	
	private void background(ActionEvent e) {
		Color col;
		try {
			col = new Color(Integer.parseUnsignedInt(object.tooltip.background, 16), true);
		} catch (Exception ex) {
			ex.printStackTrace();
			col = new Color(0xF0100010, true);
		}
		Color c = JColorChooser.showDialog(this, "Edit Tooltip Background Color", col, true);
		if(c != null) {
			object.tooltip.background = Integer.toHexString(c.getRGB());
		}
	}
	
	private void borderStart(ActionEvent e) {
		Color col;
		try {
			col = new Color(Integer.parseUnsignedInt(object.tooltip.borderStart, 16), true);
		} catch (Exception ex) {
			ex.printStackTrace();
			col = new Color(0x505000FF, true);
		}
		Color c = JColorChooser.showDialog(this, "Edit Tooltip Border Start Color", col, true);
		if(c != null) {
			object.tooltip.borderStart = Integer.toHexString(c.getRGB());
		}
	}
	
	private void borderEnd(ActionEvent e) {
		Color col;
		try {
			col = new Color(Integer.parseUnsignedInt(object.tooltip.borderEnd, 16), true);
		} catch (Exception ex) {
			ex.printStackTrace();
			col = new Color(0x5028007F, true);
		}
		Color c = JColorChooser.showDialog(this, "Edit Tooltip Border End Color", col, true);
		if(c != null) {
			object.tooltip.borderEnd = Integer.toHexString(c.getRGB());
		}
	}

	@Override
	public void setObject(MM_Material o) {
		bFriction.setText(o.block.friction);
		bHardness.setText(o.block.hardness);
		bMapColor.setText(o.block.mapColor);
		bMaterial.setText(o.block.material);
		bRequiresTool.setSelected(o.block.requiresToolForDrops);
		bSound.setText(o.block.sound);
		bSticky.setSelected(o.block.sticky);
		bTags.setTags(o.block.tags);
		burnTime.setText(o.burnTime);
		enabled_identifier.setEnabledState(o.enabled);
		enabled_identifier.setKey(identifier);
		enabledTypes.setText(listAsString(o.enabledTypes));
		enchantPower.setText(o.enchantPowerBonus);
		fBoilingTemp.setText(o.fluid.boilingTemperature);
		fCurrentTemp.setText(o.fluid.currentTemperature);
		fireSpread.setText(o.fireSpreadSpeed);
		flammability.setText(o.flammability);
		fMeltingTemp.setText(o.fluid.meltingTemperature);
		fTags.setTags(o.fluid.tags);
		gDensity.setText(o.fluid.gas.density);
		gLuminosity.setText(o.fluid.gas.luminosity);
		gPathfindable.setSelected(o.fluid.gas.isPathfindable);
		gPropagatesSkylightDown.setSelected(o.fluid.gas.propagatesSkylightDown);
		gViscosity.setText(o.fluid.gas.viscosity);
		iFireResistant.setSelected(o.item.isFireResistant);
		iFoil.setSelected(o.item.isFoil);
		iLifespan.setText(o.item.lifespan);
		iRarity.setSelected(o.item.rarity);
		iTags.setTags(o.item.tags);
		jumpFactor.setText(o.jumpFactor);
		lDensity.setText(o.fluid.liquid.density);
		lightLevel.setText(o.lightLevel);
		lLuminosity.setText(o.fluid.liquid.luminosity);
		lPathfindable.setSelected(o.fluid.liquid.isPathfindable);
		lPropagatesSkylightDown.setSelected(o.fluid.liquid.propagatesSkylightDown);
		lViscosity.setText(o.fluid.liquid.viscosity);
		name.setText(o.name);
		oFriction.setText(o.ore.friction);
		oHardness.setText(o.ore.hardness);
		oJumpFactor.setText(o.ore.jumpFactor);
		oLightLevel.setText(o.ore.lightLevel);
		oMapColor.setText(o.ore.mapColor);
		oMaterial.setText(o.ore.material);
		oRequiresTool.setSelected(o.ore.requiresToolForDrops);
		oResistance.setText(o.ore.resistance);
		oSound.setText(o.ore.sound);
		oSpeedFactor.setText(o.ore.speedFactor);
		oTags.setTags(o.ore.tags);
		resistance.setText(o.resistance);
		speedFactor.setText(o.speedFactor);
		state.setSelected(o.state);
		tagNames.setText(arrayAsString(o.tagNames));
		texture.setText(o.texture);
		tText.setText(arrayAsString(o.tooltip.text));
		object = o;
	}

	@Override
	protected void save(ActionEvent e) {
		object.block.friction = bFriction.getTextF(object.block.friction);
		object.block.hardness = bHardness.getTextF(object.block.hardness);
		object.block.mapColor = bMapColor.getText();
		object.block.material = bMaterial.getText();
		object.block.requiresToolForDrops = bRequiresTool.isSelected();
		object.block.sound = bSound.getText();
		object.block.sticky = bSticky.isSelected();
		object.block.tags = bTags.getTags();
		object.burnTime = burnTime.getTextI(object.burnTime);
		object.enabled = enabled_identifier.getEnabledState();
		object.enabledTypes = stringAsList(enabledTypes.getText());
		object.enchantPowerBonus = enchantPower.getTextF(object.enchantPowerBonus);
		object.fireSpreadSpeed = fireSpread.getTextI(object.fireSpreadSpeed);
		object.flammability = flammability.getTextI(object.flammability);
		object.fluid.boilingTemperature = fBoilingTemp.getTextI(object.fluid.boilingTemperature);
		object.fluid.currentTemperature = fCurrentTemp.getTextI(object.fluid.currentTemperature);
		object.fluid.gas.density = gDensity.getTextI(object.fluid.gas.density);
		object.fluid.gas.isPathfindable = gPathfindable.isSelected();
		object.fluid.gas.luminosity = gLuminosity.getTextI(object.fluid.gas.luminosity);
		object.fluid.gas.propagatesSkylightDown = gPropagatesSkylightDown.isSelected();
		object.fluid.gas.viscosity = gViscosity.getTextI(object.fluid.gas.viscosity);
		object.fluid.liquid.density = lDensity.getTextI(object.fluid.liquid.density);
		object.fluid.liquid.isPathfindable = lPathfindable.isSelected();
		object.fluid.liquid.luminosity = lLuminosity.getTextI(object.fluid.liquid.luminosity);
		object.fluid.liquid.propagatesSkylightDown = lPropagatesSkylightDown.isSelected();
		object.fluid.liquid.viscosity = lViscosity.getTextI(object.fluid.liquid.viscosity);
		object.fluid.meltingTemperature = fMeltingTemp.getTextI(object.fluid.meltingTemperature);
		object.fluid.tags = fTags.getTags();
		object.item.isFireResistant = iFireResistant.isSelected();
		object.item.isFoil = iFoil.isSelected();
		object.item.lifespan = iLifespan.getTextI(object.item.lifespan);
		object.item.rarity = iRarity.getSelected();
		object.item.tags = iTags.getTags();
		object.jumpFactor = jumpFactor.getTextF(object.jumpFactor);
		object.lightLevel = lightLevel.getTextI(object.lightLevel);
		object.name = name.getText();
		object.ore.friction = oFriction.getTextF(object.ore.friction);
		object.ore.hardness = oHardness.getTextF(object.ore.hardness);
		object.ore.jumpFactor = oJumpFactor.getTextF(object.ore.jumpFactor);
		object.ore.mapColor = oMapColor.getText();
		object.ore.material = oMaterial.getText();
		object.ore.requiresToolForDrops = oRequiresTool.isSelected();
		object.ore.resistance = oResistance.getTextF(object.ore.resistance);
		object.ore.sound = oSound.getText();
		object.ore.speedFactor = oSpeedFactor.getTextF(object.ore.speedFactor);
		object.ore.tags = oTags.getTags();
		object.resistance = resistance.getTextF(object.resistance);
		object.speedFactor = speedFactor.getTextF(object.speedFactor);
		object.state = state.getSelected();
		object.tagNames = stringAsArray(tagNames.getText());
		object.texture = texture.getText();
		object.tooltip.text = stringAsArray(tText.getText());
		parent.set(enabled_identifier.getKey(), object);
		parent.unlock();
		this.setVisible(false);
	}

}
