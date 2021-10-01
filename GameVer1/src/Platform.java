import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Platform {
	
	int x;
	int y;
	int width;
	int height;
	Rectangle hitBox;
	
	public Platform() {
		super();
		this.x = 0;
		this.y = 400;
		this.width = 1000;
		this.height = 50;
		
		hitBox = new Rectangle(x, y, width, height);
	}
	
	public Rectangle getRectangle() {return this.hitBox;}
	
	
	public void draw(Graphics2D gtd) {
		gtd.setColor(Color.BLACK);
		gtd.fillRect(x, y, width, height);
	}

}
