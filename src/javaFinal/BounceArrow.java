package javaFinal;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BounceArrow extends gameObject {
	private double scale = 1;
	int direction = 0;
	public static final int UP = 0;
	public static final int RIGHT = 1;
	public static final int DOWN = 2;
	public static final int LEFT = 3;
	public int speed = 1;
	private boolean bounced = false;
	private boolean bouncing = false;
	private double yspd;
	private double xspd;

	public BounceArrow(int _direction) { // up = 0, right = 1, down = 2, left = 3; (these are where the arrow comes from)
		super();
		direction = _direction;
		create();
		
	}

	public BounceArrow(int _direction, double _speed) { // up = 0, right = 1, down = 2, left = 3; (these are where the arrow
													// comes from)
		super();
		direction = _direction;
		speed = (int) _speed;
		create();
	}

	public BounceArrow(int _x, int _y) { // dont use this one :)
		super(_x, _y);
		create();
	}
	
	public void create() {
		xsize = 32;
		ysize = 32;
		try {
			objectImage = ImageIO.read(new File("./sprites/BounceArrow.png"));
		} catch (IOException e) {
			System.out.println("read of arrow.png failed, try harder next time");
			e.printStackTrace();
		} // 1024
			// Shape a = new
			// System.out.println(direction);

		// makeImageTransparent();

		switch (direction) {
		case 0:
			x = Player.me.x;
			y = 0;
			break;
		case 1:
			x = Player.me.x + main.yDimension / 2;
			y = Player.me.y;
			break;
		case 2:
			x = Player.me.x;
			y = main.yDimension;
			break;
		case 3:
			x = Player.me.x - main.yDimension / 2;
			y = Player.me.y;
			break;

		}
	}
	
	public void step() {
		if (bounced && bouncing) {
			xspd = nextMove(xspd);
			yspd = nextMove(yspd);

			System.out.println(xspd + " " + yspd);
			if (yspd == 0 && xspd == 0) {
				alive = true;
				bouncing = false;
			}
		}
		if (alive) {
			if (gameObject.checkCollision(this, Player.me.x + Player.me.xsize / 2, Player.me.y + Player.me.ysize / 2)) {
				if (Player.timeSinceAction < 8) {
					// System.out.println((int) Player.me.direction + " " + direction * 90);
					if (((int) Player.me.direction) == direction * 90) {
						Player.timeSinceAction = 100;
						if (bounced != true) {
							startBounce(24);
						}
						else {
							alive = false;
						}
					}
				}
			}
		switch (direction) {
		case 0:
				y += speed - yspd;
			break;
			case 1:
				x -= speed + xspd;
			break;
		case 2:
				y -= speed + yspd;
			break;
		case 3:
				x += speed - xspd;
			break;
			}
		}

		if (!alive && !bouncing && bounced) {
			scale += 0.1;
			if (scale > 3) {
				gameGame.kill(this);
			}
		}
		if (x < 0 || x > main.xDimension) {
			gameGame.kill(this);
		}
		if (y < 0 || y > main.yDimension) {
			gameGame.kill(this);
		}

		}
	

	public String getName() {
		return "BounceArrow";
	}
	
	public void paint(Graphics2D g2d, BufferedImage imageLayer) {

		AffineTransform a = new AffineTransform();
		a.translate(x + xsize / 2, y + ysize / 2);
		a.rotate(Math.toRadians((90 * direction) + 180)); // S2: rotate around anchor
		a.translate(-(x + xsize / 2), -(y + ysize / 2));
		a.translate(x + xsize / 2.0, y + ysize / 4.0);
		a.scale(scale, scale);
		a.translate(-(x + xsize / 2), -(y + ysize / 4));

		// g2d.setComposite(AlphaComposite.SrcOver);
		g2d.setTransform(a);
		// g2d.clearRect(x, y, xsize, ysize);

		// AlphaComposite ac = java.awt.AlphaComposite.getInstance(AlphaComposite.CLEAR,
		// 0.0f);
		// g2d.setComposite(ac);
		// objectImage = Transparency.makeImageTranslucent(objectImage, 0.5);
		// g2d.drawImage(objectImage, (int) x, (int) y, Color.white, main);
		// imageLayer = util.drawToImageCorrectly(x, y, objectImage, imageLayer);
		// imageLayer.getGraphics().drawImage(objectImage, x, y, main);
		// Composite og = g2d.getComposite();
		// AlphaComposite ac = java.awt.AlphaComposite.getInstance(AlphaComposite.CLEAR,
		// 0.5f);
		//
		// Graphics2D imageGraphics = (Graphics2D) imageLayer.getGraphics();
		// g2d.setComposite(ac);
		//imageLayer.((Graphics2D)getGraphics()).setComposite(ac);
		int scaledScale = (int) (scale * 255) - 255;
		objectImage = makeImagePartiallyTransparent((255 - scaledScale), objectImage);
		g2d.drawImage(objectImage, x, y, main);

		// g2d.fillRect(x, y, 30, 30);
		// g2d.setComposite(og);
		// imageGraphics.drawRect(0, 0, 500, 500);
		// g2d.drawImage
		g2d.setTransform(new AffineTransform());

	}

	public void makeImageTransparent() {

		WritableRaster outRaster = null;
		outRaster = objectImage.getRaster();
		// System.out.println(outRaster);

		int[] dArray = new int[4];
		int[] iArray = new int[4];
		BufferedImage image2 = new BufferedImage(32, 32, BufferedImage.TYPE_INT_ARGB);
		WritableRaster image2Raster = image2.getRaster();
		for (int forx = 0; forx < outRaster.getWidth(); forx++) {
			for (int fory = 0; fory < outRaster.getHeight(); fory++) {
				int[] testPixel = outRaster.getPixel(forx, fory, dArray);
				int[] AlphaPixels = new int[4]; // make it right size
				boolean[] white = { false, false, false, false };
				for (int i = 0; i < testPixel.length; i++) {
					if (testPixel[i] == 255) {
						white[i] = true;
					} // if all values are 255, it is white, and the transparency should be set to
						// full
					AlphaPixels[i] = testPixel[i];
				}
				// System.out.println((AlphaPixels)[0] + " " + AlphaPixels[1] + " " +
				// AlphaPixels[2]);
				// System.out.println(testPixel[0] + " " + testPixel[1] + " " + testPixel[2]);
				AlphaPixels[3] = (white[0] && white[1] && white[2]) ? 0 : 255;
				// System.out.println(white[0] + " " + white[1] + " " + white[2]);
				// System.out.println(AlphaPixels[3]);
				// System.out.println((white[0] && white[1] && white[2]));
				image2Raster.setPixel(forx, fory, AlphaPixels);
			}
		}

		int[] bruh = image2Raster.getPixel(0, 0, iArray);
		// System.out.println(bruh.length);
		image2.setData(image2Raster);
		objectImage = image2;

		// testy.
		// System.out.println(testy.getTransparency());
		// System.out.println(ColorModel.BITMASK);
		// System.out.println(ColorModel.OPAQUE);
		// System.out.println(ColorModel.TRANSLUCENT);
		// System.out.println(objectImage.getRaster().getPixel(8, 2, new int[4])[0]);

		// BandedSampleModel n = new BandedSampleModel(DataBuffer.TYPE_INT), 32, 32, 4);
		// WritableRaster test = new WritableRaster(null, null);

		// SampleModel n = new SampleModel(DataBuffer.TYPE_INT,32,32,4);
		// WritableRaster test = new WritableRaster(null, new Point(););
		// objectImage.
		// double[] array = null;
		// test.getPixels(0, 0, xsize - 1, ysize - 1, array);

		// System.out.println(array);
	}

	public BufferedImage makeImagePartiallyTransparent(int amount, BufferedImage image) {
		if (amount > 255 || amount < 0) {
			amount = 0;
		}
		WritableRaster outRaster = null;
		int[] dArray = new int[image.getWidth() * image.getHeight() * 4];
		int[] iArray = new int[4];
		// BufferedImage image2 = new BufferedImage(32, 32,
		// BufferedImage.TYPE_INT_ARGB);
		WritableRaster imageRaster = image.getRaster();
		imageRaster.getPixels(0, 0, image.getWidth(), image.getHeight(), dArray);
		for (int i = 3; i < dArray.length; i += 4) {
			if (dArray[i] != 0) {
				dArray[i] = amount;
			}
		}
		imageRaster.setPixels(0, 0, image.getWidth(), image.getHeight(), dArray);
		return image;

	}

	public void startBounce(int bounceSpeed) {
		switch (direction) {
		case 0:
			yspd += bounceSpeed;
			break;
		case 1:
			xspd -= bounceSpeed;
			break;
		case 2:
			yspd -= bounceSpeed;
			break;
		case 3:
			xspd += bounceSpeed;
			break;
		}
		bounced = true;
		bouncing = true;
	}

	public double nextMove(double a) {
		double val = util.betterSign(a);
		if (Math.abs(val) < 0.03) {
			return 0;
		}
		return a + (-util.betterSign(a));
		// xspd += (-util.betterSign(xspd) / 10);
	}

}
