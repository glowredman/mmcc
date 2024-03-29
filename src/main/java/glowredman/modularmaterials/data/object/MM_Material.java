package glowredman.modularmaterials.data.object;

import java.util.ArrayList;
import java.util.List;

import glowredman.modularmaterials.data.object.sub.BlockProperties;
import glowredman.modularmaterials.data.object.sub.ChemicalState;
import glowredman.modularmaterials.data.object.sub.ColorProperties;
import glowredman.modularmaterials.data.object.sub.FluidProperties;
import glowredman.modularmaterials.data.object.sub.ItemProperties;
import glowredman.modularmaterials.data.object.sub.OreProperties;
import glowredman.modularmaterials.data.object.sub.TooltipProperties;

public class MM_Material {

	public String name = "material_" + this.hashCode();
	public String[] tagNames = new String[] {name};
	public boolean enabled = false;
	public List<String> enabledTypes = new ArrayList<>();
	public ColorProperties color = new ColorProperties();
	public int burnTime = 0;
	public float enchantPowerBonus = 0.0f;
	public int fireSpreadSpeed = 0;
	public int flammability = 0;
	public float jumpFactor = 1.0f;
	public int lightLevel = 0;
	public float resistance = 6.0f;
	public float speedFactor = 1.0f;
	public String noteblockInstrument = "harp";
	public ChemicalState state = ChemicalState.SOLID;
	public String texture = "metallic";
	public TooltipProperties tooltip = new TooltipProperties();
	public BlockProperties block = new BlockProperties();
	public FluidProperties fluid = new FluidProperties();
	public ItemProperties item = new ItemProperties();
	public OreProperties ore = new OreProperties();
	
	@Override
	public String toString() {
		return String.format("{name: %s, tagNames: %s, enabled: %b, enabledTypes: %s, color: %s, burnTime: %d, enchantPowerBonus: %f, fireSpreadSpeed: %d, flammability: %d, jumpFactor: %f, lightLevel: %d, resistance: %f, speedFactor: %f, noteblockInstrument: %s, state: %s, texture: %s, tooltip: %s, block: %s, fluid: %s, item: %s, ore: %s}",
				name, String.join(", ", tagNames), enabled, enabledTypes, color, burnTime, enchantPowerBonus, fireSpreadSpeed, flammability, jumpFactor, lightLevel, resistance, speedFactor, noteblockInstrument, state, texture, tooltip, block, fluid, item, ore);
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null || !(obj instanceof MM_Material)) {
			return false;
		}
		return this.name.equals(((MM_Material) obj).name);
	}

}
