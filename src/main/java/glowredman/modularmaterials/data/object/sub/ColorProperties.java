package glowredman.modularmaterials.data.object.sub;

public class ColorProperties {
	
	public int red = 255;
	public int green = 255;
	public int blue = 255;
	public int alpha = 255;
	
	public ColorProperties() {}
	
	public ColorProperties(int red, int green, int blue, int alpha) {
		this.red = red;
		this.green = green;
		this.blue = blue;
		this.alpha = alpha;
	}
	
	public ColorProperties(int red, int green, int blue) {
		this.red = red;
		this.green = green;
		this.blue = blue;
	}
	
	public int getARGB() {
		return alpha << 24 | red << 16 | green << 8 | blue;
	}
	
	public int getRGB() {
		return red << 16 | green << 8 | blue;
	}
	
	@Override
	public String toString() {
		return String.format("{red: %d, green: %d, blue: %d, alpha: %d}", red, green, blue, alpha);
	}

}
