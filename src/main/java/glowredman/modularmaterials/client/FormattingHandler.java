package glowredman.modularmaterials.client;

import java.util.List;

public class FormattingHandler {
	
	public static String listToString(List<String> list) {
		
		// no entries = []
		if(list == null || list.size() == 0) {
			return "[]";
		}
		
		// one entry = ["abc"]
		if(list.size() == 1) {
			return "[\"" + list.get(0) + "\"]";
		}
		
		// multiple entries = ["abc", "def", ...]
		String ret = "[\"" + list.get(0);
		for(int i = 1; i < list.size(); i++) {
			ret += "\", \"" + list.get(i);
		}
		return ret += "\"]";
	}
	
	public static String arrayToString(String[] array) {
		
		// no entries = []
		if(array == null || array.length == 0) {
			return "[]";
		}
		
		// one entry = ["abc"]
		if(array.length == 1) {
			return "[\"" + array[0] + "\"]";
		}
		
		// multiple entries = ["abc", "def", ...]
		String ret = "[\"" + array[0];
		for(int i = 1; i < array.length; i++) {
			ret += "\", \"" + array[i];
		}
		return ret += "\"]";
	}

}
