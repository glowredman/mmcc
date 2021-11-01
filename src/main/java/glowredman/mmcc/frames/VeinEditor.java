package glowredman.mmcc.frames;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import glowredman.mmcc.panels.IdentifierPanel;
import glowredman.mmcc.panels.LabeledTextField;
import glowredman.mmcc.panels.TopPanel;
import glowredman.modularmaterials.data.object.MM_OreVein;

public class VeinEditor extends Editor<MM_OreVein> {
	
	private static final long serialVersionUID = -1060546302330071806L;
	private IdentifierPanel enabled_identifier;
	private LabeledTextField primary;
	private LabeledTextField secondary;
	private LabeledTextField inbetween;
	private LabeledTextField sporadic;
	private JCheckBox invertBiomes;
	private JTextArea biomes;
	private LabeledTextField name;
	private LabeledTextField density;
	private JTextField minY;
	private JTextField maxY;
	private LabeledTextField size;
	private LabeledTextField weight;
	private JCheckBox invertDimensions;
	private JTextArea dimensions;

	public VeinEditor(TopPanel<MM_OreVein> parent) {
		super(parent);
		this.setTitle("Edit oreveins.json Entry");
		this.setResizable(false);
		this.setLayout(new GridBagLayout());
		
		GridBagConstraints cst = new GridBagConstraints();
		
		// First Column
		enabled_identifier = new IdentifierPanel();
		cst.anchor = GridBagConstraints.WEST;
		cst.gridx = 0;
		cst.gridy = 0;
		this.add(enabled_identifier, cst);
		
		primary = new LabeledTextField("Primary");
		cst.gridy = 1;
		this.add(primary, cst);
		
		secondary = new LabeledTextField("Secondary");
		cst.gridy = 2;
		this.add(secondary, cst);
		
		inbetween = new LabeledTextField("Inbetween");
		cst.gridy = 3;
		this.add(inbetween, cst);
		
		sporadic = new LabeledTextField("Sporadic");
		cst.gridy = 4;
		this.add(sporadic, cst);
		
		JPanel biomesPanel = new JPanel();
		biomesPanel.setLayout(new BoxLayout(biomesPanel, BoxLayout.Y_AXIS));
		biomesPanel.setBorder(BorderFactory.createTitledBorder("Biomes"));
		invertBiomes = new JCheckBox("Invert");
		biomes = new JTextArea();
		JScrollPane biomesScrollPane = new JScrollPane(biomes);
		biomesScrollPane.setMinimumSize(new Dimension(250, 500));
		biomesScrollPane.setPreferredSize(new Dimension(250, 500));
		biomesScrollPane.setMaximumSize(new Dimension(250, 500));
		biomesPanel.add(invertBiomes);
		biomesPanel.add(biomesScrollPane);
		cst.gridy = 5;
		this.add(biomesPanel, cst);
		
		JButton doneButton = new JButton("Done");
		doneButton.addActionListener(this::save);
		cst.anchor = GridBagConstraints.EAST;
		cst.gridy = 6;
		this.add(doneButton, cst);
		
		//Second Column
		name = new LabeledTextField("Name");
		cst.anchor = GridBagConstraints.WEST;
		cst.gridx = 1;
		cst.gridy = 0;
		this.add(name, cst);
		
		density = new LabeledTextField("Density");
		cst.gridy = 1;
		this.add(density, cst);
		
		JPanel yPanel = new JPanel();
		JLabel yLabel = new JLabel("Height Range");
		yLabel.setMinimumSize(new Dimension(75, 20));
		yLabel.setPreferredSize(new Dimension(75, 20));
		yLabel.setMaximumSize(new Dimension(75, 20));
		yLabel.setToolTipText("Height Range");
		minY = new JTextField();
		minY.setMinimumSize(new Dimension(30, 20));
		minY.setPreferredSize(new Dimension(30, 20));
		minY.setMaximumSize(new Dimension(30, 20));
		minY.setToolTipText("Minimum Height");
		JLabel toLabel = new JLabel("-");
		maxY = new JTextField();
		maxY.setMinimumSize(new Dimension(30, 20));
		maxY.setPreferredSize(new Dimension(30, 20));
		maxY.setMaximumSize(new Dimension(30, 20));
		maxY.setToolTipText("Maximum Height");
		yPanel.add(yLabel);
		yPanel.add(minY);
		yPanel.add(toLabel);
		yPanel.add(maxY);
		cst.gridy = 2;
		this.add(yPanel, cst);
		
		size = new LabeledTextField("Size");
		cst.gridy = 3;
		this.add(size, cst);
		
		weight = new LabeledTextField("Weight");
		cst.gridy = 4;
		this.add(weight, cst);
		
		JPanel dimensionsPanel = new JPanel();
		dimensionsPanel.setLayout(new BoxLayout(dimensionsPanel, BoxLayout.Y_AXIS));
		dimensionsPanel.setBorder(BorderFactory.createTitledBorder("Dimensions"));
		invertDimensions = new JCheckBox("Invert");
		dimensions = new JTextArea();
		JScrollPane dimensionsScrollPane = new JScrollPane(dimensions);
		dimensionsScrollPane.setMinimumSize(new Dimension(250, 500));
		dimensionsScrollPane.setPreferredSize(new Dimension(250, 500));
		dimensionsScrollPane.setMaximumSize(new Dimension(250, 500));
		dimensionsPanel.add(invertDimensions);
		dimensionsPanel.add(dimensionsScrollPane);
		cst.gridy = 5;
		this.add(dimensionsPanel, cst);
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(this::cancel);
		cst.gridy = 6;
		this.add(cancelButton, cst);
		
		this.pack();
	}
	
	protected void save(ActionEvent e) {
		object.biomes = stringAsList(biomes.getText());
		object.density = density.getTextI(object.density);
		object.dimensions = stringAsList(dimensions.getText());
		object.enabled = enabled_identifier.getEnabledState();
		object.inbetween = inbetween.getText();
		object.invertBiomes = invertBiomes.isSelected();
		object.invertDimensions = invertDimensions.isSelected();
		try { object.maxY = Integer.parseInt(maxY.getText()); } catch (Exception ex) { ex.printStackTrace(); }
		try { object.minY = Integer.parseInt(minY.getText()); } catch (Exception ex) { ex.printStackTrace(); }
		object.name = name.getText();
		object.primary = primary.getText();
		object.secondary = secondary.getText();
		object.size = size.getTextI(object.size);
		object.sporadic = sporadic.getText();
		object.weight = weight.getTextI(object.weight);
		parent.set(enabled_identifier.getKey(), object);
		parent.unlock();
		this.setVisible(false);
	}

	@Override
	public void setObject(MM_OreVein o) {
		enabled_identifier.setEnabledState(o.enabled);
		enabled_identifier.setKey(identifier);
		primary.setText(o.primary);
		secondary.setText(o.secondary);
		inbetween.setText(o.inbetween);
		sporadic.setText(o.sporadic);
		invertBiomes.setSelected(o.invertBiomes);
		biomes.setText(listAsString(o.biomes));
		name.setText(o.name);
		density.setText(o.density);
		minY.setText(String.valueOf(o.minY));
		maxY.setText(String.valueOf(o.maxY));
		size.setText(o.size);
		weight.setText(o.weight);
		invertDimensions.setSelected(o.invertDimensions);
		dimensions.setText(listAsString(o.dimensions));
		object = o;
	}

}
