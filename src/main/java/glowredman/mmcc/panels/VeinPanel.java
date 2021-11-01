package glowredman.mmcc.panels;

import java.lang.reflect.Type;
import java.util.LinkedHashMap;

import com.google.gson.reflect.TypeToken;

import glowredman.mmcc.frames.VeinEditor;
import glowredman.modularmaterials.data.object.MM_OreVein;

public class VeinPanel extends TopPanel<MM_OreVein> {

	private static final long serialVersionUID = -2342409049924673489L;

	public VeinPanel() {
		super("oreveins.json");
		this.editorFrame = new VeinEditor(this);
	}

	@Override
	protected Type getType() {
		return new TypeToken<LinkedHashMap<String, MM_OreVein>>() {}.getType();
	}

	@Override
	protected MM_OreVein getNew() {
		return new MM_OreVein();
	}

}
