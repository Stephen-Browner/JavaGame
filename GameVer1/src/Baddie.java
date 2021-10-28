import java.awt.Rectangle;
import java.util.List;

public class Baddie extends Sprite implements Runnable {
	
	public Baddie(int x, int y, int width, int height, String spriteImage) {
		super(x, y, width, height, spriteImage);
	}
	
	public Rectangle getRectangle() {return this.hitBox;}
	
	private Projectile projectile;
	private List<Projectile> currentProjectiles;
	
	public void setProjectile(Projectile temp) {this.projectile = temp;}//passing memory location of class to object
	
	public void setcurrentProjectiles(List<Projectile> temp) {this.currentProjectiles = temp;}
	
	

	@Override
	public void run() {
		while (true) {
			
			
			int y = this.getY();
			int x = this.getX();
			
			if (MainGame.currentBaddies.size() != 0) {
				for (int i = 0; i < 7; i++) {
					x = MainGame.currentBaddies.get(i).getX();
					x -= 1;
					if (x + MainGame.currentBaddies.get(i).getWidth() < 0) {
						x = GameProperties.SCREEN_WIDTH;
					}
					MainGame.currentBaddies.get(i).setX(x);
					
					if (currentProjectiles != null) {
						
						System.out.println(currentProjectiles.get(i));
						if(this.hitBox.intersects(currentProjectiles.get(i).hitBox)){
							System.out.println("Collision");	
						}
						
					}
					
				}
				
//			for (int i = 0; i < currentProjectiles.size(); i++) {
//					
//			
//			
//			}
				
			} try {
				Thread.sleep(1000/60);
			} catch (Exception e) {
				//
			}
		}
		
	}

	

}
