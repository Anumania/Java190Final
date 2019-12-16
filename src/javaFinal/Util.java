package javaFinal;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

/*
 * entirely utility class, some of these should probably be put into other classes, but a lot of these can be used in multiple places and i found it suitable to put them all into the same class
 */
public class Util { // entirely utility class, for small universal methods that probably are already
					// in a java library somewhere but i know how to make
	/*
	 * Toggles the input boolean
	 * 
	 * @param in boolean to toggle
	 * 
	 * @return toggled boolean
	 */
	public static boolean toggle(boolean in) {
		if (in) {
			return false;
		}
		return true;
	}

	/*
	 * has no use outside of calculating the player and its rotation, plans to make
	 * this universally useful.
	 * 
	 * @param a target angle
	 * 
	 * @param b current angle
	 * 
	 * @return which direction to start turning
	 */
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

	/**
	 * "clamps" a value between 2 other values, returning a number between the 2,
	 * not by interpolation, but in the same way max and min work
	 * 
	 * @param value the value you want to clamp
	 * 
	 * @param max   the maximum value you want to return
	 * 
	 * @param min   the minimum value you want to return
	 * 
	 * @return The input clamped
	 */
	public static double clamp(double value, double min, double max) {
		double answer;
		answer = Math.max(value, min);
		answer = Math.min(answer, max);
		return answer;
	}

	/**
	 * int version of double clamp
	 * 
	 * @param value value to clamp
	 * @param min   minimum value to clamp to
	 * @param max   max value to clamp to
	 * @return value clamped between 2 parameters
	 */
	public static int clamp(int value, int min, int max) {
		int answer;
		answer = Math.max(value, min);
		answer = Math.min(answer, max);
		return answer;
	}

	/*
	 * A dumpster fire, i made this in my rampage to fix transparency and its not
	 * used to my knowledege
	 * 
	 * @return transparent image
	 * 
	 * @param x x position of image
	 * 
	 * @param y y position of image
	 * 
	 * @param source image to draw onto the destination image
	 * 
	 * @param dest the destination image which will be drawn on
	 * 
	 * @return destination image which has been drawn on, which is useless because
	 * it modifies the object and doesnt make a copy
	 */
	@Deprecated
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
	/*
	 * just like @see java.lang.Math #signum, but if the number is below 1 on either
	 * side of 0, it returns that instead of 1, this is useful for making object
	 * movements based off sign
	 * 
	 * @param a number to better sign
	 * 
	 * @returns -1,1, or anything inbetween
	 */

	public static double betterSign(double a) {
		Math.signum(a);
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