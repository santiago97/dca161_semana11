import java.awt.Color;
import processing.core.*;

public class Maniapp extends PApplet {
	private PImage original, resul;
	int estado;
	public void setup() {
		original = loadImage("../data/emma.jpg");
		int ancho = original.width;
		int alto = original.height;
		size(ancho, alto);
		resul = createImage(ancho, alto, RGB);
		colorMode(RGB);
	}
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
			original.loadPixels();
			resul.loadPixels();
			pix = original.pixels;
			for (int x = 0; x < original.width; x++) {
				for (int y = 0; y < original.height; y++) {
					int loc = x + y * original.width;
					Color micl = new Color(pix[loc]);
					int r = micl.getRed();
					int g = micl.getGreen();
					int b = micl.getBlue();
					float gris = (r + g + b) / 4;
					// System.out.println(gris);
					if (x < mouseX) {
						if (gris < 127)
							resul.pixels[loc] = color(0);
						else
							resul.pixels[loc] = color(255);
					} else
						resul.pixels[loc] = pix[loc];
				}
			}
			resul.updatePixels();
			original.updatePixels();
			break;
		case 2:
			original.loadPixels();
			resul.loadPixels();
			pix = original.pixels;
			int divs = 40;
			for (int x = 0; x < original.width; x += original.width / divs) {
				for (int y = 0; y < original.height; y += original.height / divs) {
					int loc = x + y * original.width;
					Color micl = new Color(pix[loc]);
					int r = micl.getRed();
					int g = micl.getGreen();
					int b = micl.getBlue();
					for (int xx = x; xx < x + original.width / divs; xx++) {
						for (int yy = y; yy < y + original.height / divs; yy++) {
							int locloc = xx + yy * original.width;
							if (x < mouseX) {
								resul.pixels[locloc] = color(r, g, b);
							} else
								resul.pixels[locloc] = pix[locloc];
						}
					}
				}
			}
			resul.updatePixels();
			original.updatePixels();
			break;
		case 3:
			original.loadPixels();
			resul.loadPixels();
			pix = original.pixels;
			divs = 40;
			for (int x = 0; x < original.width; x += original.width / divs) {
				for (int y = 0; y < original.height; y += original.height / divs) {
					int loc = x + y * original.width;
					int promR = 0;
					int promG = 0;
					int promB = 0;
					for (int xx = x; xx < x + original.width / divs; xx++) {
						for (int yy = y; yy < y + original.height / divs; yy++) {
							int locloc = xx + yy * original.width;
							Color micl = new Color(pix[locloc]);
							int r = micl.getRed();
							int g = micl.getGreen();
							int b = micl.getBlue();
							promR += r;
							promG += g;
							promB += b;
						}
					}
					int tam = (original.width / divs) * (original.height / divs);
					int col = color(promR / tam, promG / tam, promB / tam);
					for (int xx = x; xx < x + original.width / divs; xx++) {
						for (int yy = y; yy < y + original.height / divs; yy++) {
							int locloc = xx + yy * original.width;
							if (x < mouseX) {
								resul.pixels[locloc] = col;
							} else
								resul.pixels[locloc] = pix[locloc];
						}
					}
				}
			}
			resul.updatePixels();
			original.updatePixels();
			break;
		case 4: //INCOMPLETO
			original.loadPixels();
			resul.loadPixels();
			pix = original.pixels;
			divs = 40;
			for (int x = 0; x < original.width; x += original.width / divs) {
				for (int y = 0; y < original.height; y += original.height / divs) {
					int loc = x + y * original.width;
					Color micl = new Color(pix[loc]);
					int r = micl.getRed();
					int g = micl.getGreen();
					int b = micl.getBlue();
					float[] hsb = Color.RGBtoHSB(r, g, b, null);
					
					colorMode(HSB);
					
					float br = map(dist(x,y,original.width/2, original.height/2),0,dist(x,y,original.width, original.height),hsb[2],0);
					
					resul.pixels[loc] = color(hsb[0], hsb[1], br);
					colorMode(RGB);
					
				}
			}
			resul.updatePixels();
			original.updatePixels();
			break;
		}
		image(resul, 0, 0);
		// image(original, 0, 0);
		stroke(0);
		strokeWeight(3);
		line(mouseX, 0, mouseX, height);
	}
	public void keyPressed() {
		if (keyCode == RIGHT) {
			estado++;
			if (estado > 4)
				estado = 0;
		}
		if (keyCode == LEFT) {
			estado--;
			if (estado < 0)
				estado = 4;
		}
	}
}