package glowredman.mmcc;

import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.net.URI;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.KeyStroke;
import javax.swing.UIManager;

import glowredman.mmcc.panels.MaterialPanel;
import glowredman.mmcc.panels.TopPanel;
import glowredman.mmcc.panels.TypePanel;
import glowredman.mmcc.panels.VariantPanel;
import glowredman.mmcc.panels.VeinPanel;
import glowredman.modularmaterials.data.object.MM_Material;
import glowredman.modularmaterials.data.object.MM_OreVariant;
import glowredman.modularmaterials.data.object.MM_OreVein;
import glowredman.modularmaterials.data.object.MM_Type;

public class Main extends JFrame {
	
	private static final long serialVersionUID = -1962144914785470334L;
	private File configDir;
	private JButton openButton;
	private JProgressBar progress;
	private TopPanel<MM_Material> materialsPanel;
	private TopPanel<MM_Type> typesPanel;
	private TopPanel<MM_OreVariant> variantsPanel;
	private TopPanel<MM_OreVein> veinsPanel;
	
	public Main() {
		//frame setup
		super("Modular Materials Config Creator");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new GridBagLayout());
		this.setResizable(false);
		
		GridBagConstraints cst = new GridBagConstraints();
		
		// --- MATERIALS ---
		materialsPanel = new MaterialPanel();
		cst.gridx = 0;
		cst.gridy = 0;
		this.add(materialsPanel, cst);
		
		// --- TYPES ---
		typesPanel = new TypePanel();
		cst.gridx = 1;
		this.add(typesPanel, cst);
		
		// --- VARIANTS ---
		variantsPanel = new VariantPanel();
		cst.gridx = 2;
		this.add(variantsPanel, cst);
		
		// --- VEINS ---
		veinsPanel = new VeinPanel();
		cst.gridx = 3;
		this.add(veinsPanel, cst);
		
		// --- BOTTOM ---
		JPanel bottomPanel = new JPanel();
		
		progress = new JProgressBar(0, 4);
		progress.setPreferredSize(new Dimension(340, 23));
		bottomPanel.add(progress);
		
		JButton helpButton = new JButton("Help");
		helpButton.addActionListener(this::help);
		bottomPanel.add(helpButton);
		
		openButton = new JButton("Open Config Folder");
		openButton.addActionListener(this::open);
		bottomPanel.add(openButton);
		
		JButton saveButton = new JButton("Save");
		saveButton.addActionListener(this::save);
		bottomPanel.add(saveButton);
		
		cst.gridwidth = 4;
		cst.gridx = 0;
		cst.gridy = 1;
		this.add(bottomPanel, cst);
		
		//keybindings
		this.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
		.put(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK), "save");
		this.getRootPane().getActionMap().put("save", new MethodAction(this::save));
		this.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
		.put(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_DOWN_MASK), "open");
		this.getRootPane().getActionMap().put("open", new MethodAction(this::open));
		
		//pack & show frame
		this.pack();
		this.setVisible(true);
	}
	
	private void help(ActionEvent e) {
		try {
			Desktop.getDesktop().browse(new URI("https://github.com/glowredman/modularmaterials/wiki/Modular-Materials-Config-Creator"));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	private void open(ActionEvent e) {
		JFileChooser fc = new JFileChooser();
		fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		if(fc.showOpenDialog(openButton) == JFileChooser.APPROVE_OPTION) {
			configDir = fc.getSelectedFile();
			new File(configDir, "mmcc_backup").mkdir();
			materialsPanel.read(configDir);
			typesPanel.read(configDir);
			variantsPanel.read(configDir);
			veinsPanel.read(configDir);
		}
	}
	
	private void save(ActionEvent e) {
		if(configDir == null) return;
		String now = String.format("%1$tF_%1$tH.%1$tM.%1$tS", System.currentTimeMillis());
		progress.setValue(0);
		materialsPanel.write(configDir, now);
		progress.setValue(1);
		typesPanel.write(configDir, now);
		progress.setValue(2);
		variantsPanel.write(configDir, now);
		progress.setValue(3);
		veinsPanel.write(configDir, now);
		progress.setValue(4);
	}

    public static void main(String[] args) {
    	EventQueue.invokeLater(() -> {
    		try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				new Main();
			} catch (Exception e) {
				e.printStackTrace();
			}
    	});
    }
	
}
