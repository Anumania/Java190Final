package javaFinal;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

/*
 * entirely utility class, some of these should probably be put into other classes, but a lot of these can be used in multiple places and i found it suitable to put them all into the same class
 */
public class Util { // entirely utility class, for small universal methods that probably are already
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
		long startTime = System.nanoTime();
		GameGame main = GameGame.mainGame;
		WritableRaster destRaster = dest.getRaster();
		// System.out.println(destRaster);
		WritableRaster sourceRaster = source.getRaster();

		// int[] dArray = new int[4];
		// int[] sArray = new int[4];

		// MultiThreader object = new MultiThreader(sourceRaster, x, y, dest, 0);
		// object.start();


		long endTime = System.nanoTime();
		// System.out.println((endTime - startTime) / 1000); //for timing how long this
		// takes
		// dest.setData(destRaster);

		// BufferedImage out = new BufferedImage(dest.getWidth(gameGame.mainGame),
		// dest.getHeight(gameGame.mainGame),
		// BufferedImage.TYPE_INT_RGB);

		dest.setData(destRaster);

		return dest;
	}

	public static double betterSign(double a) {
		if (a >= 1.0) {
			return 1.0;
		} else if (a <= -1.0) {
			return -1.0;
		} else {
			return a;
		}
	}

}




/*
 * Class v = Editor.class; v.newInstance();
 */