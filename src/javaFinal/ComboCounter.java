package javaFinal;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ComboCounter extends GameObject {
	BufferedImage[] comboImages = new BufferedImage[10];
	static double scale = 1;
	public ComboCounter() {
		super();
		create();
	}

	public ComboCounter(int _x, int _y) {
		super(_x, _y);
		create();
	}
	public void create() {
		try {
			comboImages[0] = ImageIO.read(new File("./sprites/default.png"));
			comboImages[1] = ImageIO.read(new File("./sprites/ComboFont/1.png"));
			comboImages[2] = ImageIO.read(new File("./sprites/ComboFont/2.png"));
			comboImages[3] = ImageIO.read(new File("./sprites/ComboFont/3.png"));
			comboImages[4] = ImageIO.read(new File("./sprites/ComboFont/4.png"));
			comboImages[5] = ImageIO.read(new File("./sprites/ComboFont/5.png"));
			comboImages[6] = ImageIO.read(new File("./sprites/ComboFont/6.png"));
			comboImages[7] = ImageIO.read(new File("./sprites/ComboFont/7.png"));
			comboImages[8] = ImageIO.read(new File("./sprites/ComboFont/8.png"));
			comboImages[9] = ImageIO.read(new File("./sprites/ComboFont/9.png"));
			
			
		} catch (IOException e) {
			System.out.println("read of default sprite failed, make new image called default.png please :)");
			e.printStackTrace();
		}
	}
	public void step() {
		scale -= 0.03;
		scale = Util.clamp(scale, 1.0, 1.7);
	}
	public String getName() {
		return "Combo";	
	}
	public void paint(Graphics2D g2d,BufferedImage lol) {
		
	}
	public void paintGUI(Graphics2D g2d) {
		
		int __combo = Player.combo;
		int _combo = __combo;
		int[] comboArray = new int[6];
		//int subtractMe = Integer.numberOfTrailingZeros(_combo)-1;
		for(int i = (Integer.toString(_combo)).length(); i > 0 ; i--) {
			comboArray[i] = _combo % 10;
			//System.out.println(_combo);
			_combo/=10;
		}
		_combo = __combo;
		//System.out.println((Integer.toString(_combo)));
	

		BufferedImage comboImage = new BufferedImage(32*comboArray.length,64, ColorModel.TRANSLUCENT);
		Graphics2D comboGraphics = (Graphics2D) comboImage.getGraphics();
		
		for(int i = 1; i <= (Integer.toString(_combo)).length();i++) {
			//System.out.println(comboArray[i]);
			comboGraphics.drawImage(comboImages[comboArray[i]],32*i, 0, main);
		}
		int comboLength = (Integer.toString(_combo)).length();
		AffineTransform a = new AffineTransform();
		//a.translate(-comboImage.getWidth(),(-comboImage.getHeight()));
		a.shear(scale, scale);
		
		//a.scale(scale, scale);
		//g2d.setTransform(a);
		//g2d.drawImage(comboImage,x,y,main);
		int scaledx = (int) (x - (comboImage.getHeight()*(scale)));
		int scaledy = (int) y;
		int scaledWidth = (int) ((comboImage.getWidth()*(scale)));
		int scaledHeight = (int) ((comboImage.getHeight()*(scale)));
		//System.out.println(scaledx+ " " + scaledy+ " " +scaledWidth+ " " +scaledHeight+ " ");
		g2d.drawImage(comboImage,scaledx,scaledy,scaledWidth,scaledHeight,main);
		g2d.setTransform(new AffineTransform());
		//System.out.println(x + " " + y);
	}
	
}



