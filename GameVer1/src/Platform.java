import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Platform extends Sprite {
	
//	int x;
//	int y;
//	int width;
//	int height;
//	Rectangle hitBox;
	
	public Platform(int x, int y, int width, int height, String spriteImage) {
		super(x, y, width, height, spriteImage);
	}
	
	public Rectangle getRectangle() {return this.hitBox;}
	
	
	

}
