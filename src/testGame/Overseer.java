package testGame;
//oversees everything
import java.awt.Color;
import java.awt.Graphics2D;

public class Overseer extends gameObject {
	public Overseer() {
		super();
	}
	public Overseer(int hruhx, int hruhy) {
		super(hruhx,hruhy);
	}
	

	public void step() {
	}
	public String getName() {
		return "gameObject";	
	}
	public void paint(Graphics2D g2d) {

		g2d.setColor(Color.BLUE);
		g2d.drawRect(x, y, xsize, ysize);

	}

}
