package glowredman.mmcc.panels;

import java.lang.reflect.Type;
import java.util.LinkedHashMap;

import com.google.gson.reflect.TypeToken;

import glowredman.mmcc.frames.VariantEditor;
import glowredman.modularmaterials.data.object.MM_OreVariant;

public class VariantPanel extends TopPanel<MM_OreVariant> {

	private static final long serialVersionUID = 1834797922759218551L;

	public VariantPanel() {
		super("orevariants.json");
		this.editorFrame = new VariantEditor(this);
	}

	@Override
	protected Type getType() {
		return new TypeToken<LinkedHashMap<String, MM_OreVariant>>() {}.getType();
	}

	@Override
	protected MM_OreVariant getNew() {
		return new MM_OreVariant();
	}

}
