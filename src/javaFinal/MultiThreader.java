package javaFinal;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

//https://www.geeksforgeeks.org/multithreading-in-java/
public class MultiThreader extends Thread {
	WritableRaster sourceRaster;
	int forx;
	int fory;
	int[] sArray = new int[4];
	gameGame main = gameGame.mainGame;
	int x;
	int y;
	BufferedImage dest;
	int id;

	public MultiThreader(WritableRaster _sourceRaster, int _x, int _y, BufferedImage _dest,
			int _id) {
		id = _id;
		sourceRaster = _sourceRaster;
		x = _x;
		y = _y;
		dest = _dest;
	}
	public void run() {

		for (int forx = id; forx < sourceRaster.getWidth(); forx++) {
			for (int fory = id; fory < sourceRaster.getHeight(); fory++) {
				int[] sourcePixel = sourceRaster.getPixel(forx, fory, sArray);
				if (sourcePixel[3] > 0) {
					if (y + fory < main.yDimension) {
						if (x + forx < main.xDimension) {
					//destRaster.setPixel(x + forx, y + fory, sourcePixel);
					//https://stackoverflow.com/questions/3764226/convert-rbg-char-array-to-rgb-int-value-in-java
							int value = ((255 & 0xFF) << 24) | // alpha
							(((int) sourcePixel[0] & 0xFF) << 16) | // red
							(((int) sourcePixel[1] & 0xFF) << 8) | // green
							(((int) sourcePixel[2] & 0xFF) << 0); // blue
					//im not smart enough to use bitwise operators
							dest.setRGB(forx + x, fory + y, value);
						}
					}
				}
			}
		}
		try {
			this.finalize();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			System.out.println("didnt work asshole");
		}
	}
}

