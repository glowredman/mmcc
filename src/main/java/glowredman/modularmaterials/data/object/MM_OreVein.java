package glowredman.modularmaterials.data.object;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MM_OreVein {
	
	public String name = "vein_" + this.hashCode();
	public boolean enabled = false;
	public String primary = "example";
	public String secondary = "example";
	public String inbetween = "example";
	public String sporadic = "example";
	public int density = 2;
	public int minY = 5;
	public int maxY = 250;
	public int size = 32;
	public int weight = 100;
	public boolean invertDimensions = false;
	public List<String> dimensions = Arrays.asList("minecraft:overworld");
	public boolean invertBiomes = true;
	public List<String> biomes = new ArrayList<>();
	
}
