package javaFinal;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

public class util { // entirely utility class, for small universal methods that probably are already
					// in a java library somewhere but i know how to make
	public static boolean toggle(boolean in) {
		if (in) {
			return false;
		}
		return true;
	}

	public static double betterAngle(double a, double b) {
		if (a == 270.0 && b < 90.0) {
			return -1.0;
		} else if (a == 0 && b >= 270.0) {
			return 1.0;
		}
		else {
			return Math.signum(a - b);
		}
	}

	/*
	 * "clamps" a value between 2 other values
	 * 
	 * @param value the value you want to clamp
	 * 
	 * @param max the maximum value you want to return
	 * 
	 * @param min the minimum value you want to return
	 * 
	 * @return The input clamped
	 */
	public static double clamp(double value, double min, double max) {
		double answer;
		answer = Math.max(value, min);
		answer = Math.min(answer, max);
		return answer;
	}

	/*
	 * makes a color transparent in an image, and returns an image with
	 * transparentcy over the color.
	 * 
	 * @return transparent image
	 */
	public static BufferedImage drawToImageCorrectly(int x, int y, BufferedImage source, BufferedImage dest) {

		// long startTime = System.nanoTime();
		gameGame main = gameGame.mainGame;
		//WritableRaster destRaster = dest.getRaster();
		WritableRaster sourceRaster = source.getRaster();
		// dest.setRGB(x, y, rgb);\
		
		// int[] dArray = new int[4];
		// int[] sArray = new int[4];
		//int numBands = source.getNumBands();
		int numBands = 3;
		//source.getRGB(numBands, numBands, numBands, numBands, null, numBands, numBands);
		int[] sArray = new int[(source.getWidth() * source.getHeight()) * numBands];
		int[] sourcePixels = source.getRGB(0, 0, source.getWidth(), source.getHeight(), sArray,0,1);
		int[] pixelColor = new int[numBands];

		for (int i = 0; i < sourcePixels.length; i++) {
			switch (i % numBands) {
			case 0:
				pixelColor[0] = sourcePixels[i];
				break;
			case 1:
				pixelColor[1] = sourcePixels[i];
				break;
			case 2:
				pixelColor[2] = sourcePixels[i];
				int value = ((255 & 0xFF) << 24) | // alpha 
						(((int)	pixelColor[0] & 0xFF) << 16) | // red 
						(((int) pixelColor[1] & 0xFF) << 8) | // green 
						(((int) pixelColor[2] & 0xFF) << 0); // blue
				dest.setRGB(x + sourcePixels.length % source.getWidth(),
						y + (int) (sourcePixels.length / source.getHeight()), 0x00000000);

				// System.out.println(pixelColor[0]);
				// System.out.println("i got here");
				break;
			}
		}


		long endTime = System.nanoTime();
		// System.out.println((endTime - startTime) / 1000); //for timing how long this
		// takes
		// dest.setData(destRaster);

		// BufferedImage out = new BufferedImage(dest.getWidth(gameGame.mainGame),
		// dest.getHeight(gameGame.mainGame),
		// BufferedImage.TYPE_INT_RGB);

		//dest.setData(destRaster);
		int[] bruh = new int[5000];
		for (int i = 0; i < bruh.length; i++) {
			bruh[i] = 100;
		}
		Graphics destGraphics = dest.getGraphics();
		destGraphics.setColor(Color.red);
		dest.setRGB(0, 0, 100, 50, bruh, 0, 2);
		dest.setRGB(30, 30, 0x00000000);

		dest.setData(sourceRaster);

		// destGraphics.fillRect(0, 0, 500, 500);

		return dest;

	}

}




/*
 * Class v = Editor.class; v.newInstance();
 */