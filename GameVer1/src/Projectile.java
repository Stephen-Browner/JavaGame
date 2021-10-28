import java.awt.Rectangle;

public class Projectile extends Sprite {
	
	public Projectile(int x, int y, int width, int height, String spriteImage) {
		super(x, y, width, height, spriteImage);
	}
	
	public Rectangle getRectangle() {return this.hitBox;}
	
}
