package glowredman.mmcc.panels;

import java.lang.reflect.Type;
import java.util.LinkedHashMap;

import com.google.gson.reflect.TypeToken;

import glowredman.mmcc.frames.MaterialEditor;
import glowredman.modularmaterials.data.object.MM_Material;

public class MaterialPanel extends TopPanel<MM_Material> {

	private static final long serialVersionUID = -6408366295724301578L;

	public MaterialPanel() {
		super("materials.json");
		this.editorFrame = new MaterialEditor(this);
	}

	@Override
	protected Type getType() {
		return new TypeToken<LinkedHashMap<String, MM_Material>>() {}.getType();
	}

	@Override
	protected MM_Material getNew() {
		return new MM_Material();
	}

}
