package nbsdfgbjhds;

import java.awt.Color;

import processing.core.PApplet;

public class Main extends PApplet {
	
	public void draw() {
		switch (estado) {
		case 0:
			original.loadPixels();
			resul.loadPixels();
			int[] pix = original.pixels;
			for (int x = 0; x < original.width; x++) {
				for (int y = 0; y < original.height; y++) {
					int loc = x + y * original.width;
					Color micl = new Color(pix[loc]);
					int r = micl.getRed();
					int g = micl.getGreen();
					int b = micl.getBlue();
					float gris = (r + g + b) / 4;
					// System.out.println(gris);
					if (x < mouseX)
						resul.pixels[loc] = color(gris);
					else
						resul.pixels[loc] = pix[loc];
				}
			}
			resul.updatePixels();
			original.updatePixels();
			break;
		case 1:

}
