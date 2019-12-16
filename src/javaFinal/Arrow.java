package javaFinal;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * the arrow object, the player spawns these based on the bpm
 * @author jonah
 *
 */
public class Arrow extends GameObject {
	private double scale = 1;
	int direction = 0;
	public static final int UP = 0;
	public static final int RIGHT = 1;
	public static final int DOWN = 2;
	public static final int LEFT = 3;
	public int speed = 1;
	private boolean failed = false;
	private int angle;
	/**
	 * direction of incoming arrow specified by the constants {@link #UP} {@link #DOWN} {@link #LEFT} {@link #RIGHT}
	 * @param _direction lol
	 */
	public Arrow(int _direction) { // up = 0, right = 1, down = 2, left = 3; (these are where the arrow comes from)
		super();
		direction = _direction;
		create();
		
	}
	/**
	 * same as [{@link #Arrow(int)} but also allows a speed to be specified.
	 * @param _direction  {@link #UP} {@link #DOWN} {@link #LEFT} {@link #RIGHT}
	 * @param _speed speed for arrow to go
	 */
	public Arrow(int _direction, double _speed) { // up = 0, right = 1, down = 2, left = 3; (these are where the arrow
													// comes from)
		super();
		direction = _direction;
		speed = (int) _speed;
		create();
	}
	/**
	 * this is here for testing, dont use this
	 * @param _x x position to start object at
	 * @param _y y position to start object at
	 */
	public Arrow(int _x, int _y) { // dont use this one :)
		super(_x, _y);
		create();
	}
	/**
	 * create step, handles direction and sprite.
	 */
	public void create() {
		xsize = 32;
		ysize = 32;
		try {
			objectImage = ImageIO.read(new File("./sprites/Arrow.png"));
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
		angle = direction*90;
		//System.out.println(angle);
	}
	/**
	 * all arrow logic, score increase is handled here instead of at the player to prevent the player from cycling through the steplist each frame.
	 */
	public void step() {
		if(failed) {
			angle+=10;
			alive = false;
			Player.combo = 0;
			Player.comboMult = 1.0;
		}
		if (alive) {
			if (GameObject.checkCollision(this, Player.me.x + Player.me.xsize / 2, Player.me.y + Player.me.ysize / 2)) {
				score -= 100;
				if (Player.timeSinceAction < 8) {
					// System.out.println((int) Player.me.direction + " " + direction * 90);
					if (((int) Player.me.direction) == direction * 90) {
					alive = false;
						Player.increaseCombo(score);
					}
				}
			}
		switch (direction) {
		case 0:
			y += speed;
			if(y > Player.me.y +50) failed = true;
			break;
		case 1:
			x -= speed;
			if(x < Player.me.x -50) failed = true;
			break;
		case 2:
			y -= speed;
			if(y < Player.me.y -50) failed = true;
			break;
		case 3:
			x += speed;
			if(x > Player.me.x +50) failed = true;
			break;
			}
		} else {
			scale += 0.1;
			if (scale > 3) {
				GameGame.kill(this);
			}
			if (x < 0 || x > main.xDimension) {
				GameGame.kill(this);
			}
			if (y < 0 || y > main.yDimension) {
				GameGame.kill(this);
			}

		}
	}
	/**
	 * {@inheritDoc}
	 */
	public String getName() {
		return "Arrow";
	}
	/**
	 * this handles all cool effects that go on per arrow
	 * 
	 * @param g2d copy of the graphics from {@link GameGame#paint(java.awt.Graphics)}
	 * @param imageLayer seperate image for effects
	 */
	public void paint(Graphics2D g2d, BufferedImage imageLayer) {

		AffineTransform a = new AffineTransform();
		a.translate(x + xsize / 2, y + ysize / 2);
		a.rotate(Math.toRadians(angle + 180)); // S2: rotate around anchor
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
	/**
	 * this is from a dark time where i tried to rewrite the rendering, i'm too scared to delete it, but its probably not being used
	 */
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
/**
 * same as {@link #makeImageTransparent()}
 * @param amount amount to increase transparency on image.
 * @param image image to make partially transparent
 * @return partially transparent image
 */
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

}
