package glowredman.mmcc.panels;

import java.lang.reflect.Type;
import java.util.LinkedHashMap;

import com.google.gson.reflect.TypeToken;

import glowredman.mmcc.frames.TypeEditor;
import glowredman.modularmaterials.data.object.MM_Type;

public class TypePanel extends TopPanel<MM_Type> {

	private static final long serialVersionUID = -7774516960074130028L;

	public TypePanel() {
		super("types.json");
		this.editorFrame = new TypeEditor(this);
	}

	@Override
	protected Type getType() {
		return new TypeToken<LinkedHashMap<String, MM_Type>>() {}.getType();
	}

	@Override
	protected MM_Type getNew() {
		return new MM_Type();
	}

}
