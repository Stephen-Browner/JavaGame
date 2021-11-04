import java.awt.Rectangle;
import java.util.List;
import java.awt.Graphics;

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
				for (int i = 0; i < MainGame.currentBaddies.size(); i++) {
					x = MainGame.currentBaddies.get(i).getX();
					x -= 1;
					
//					System.out.println(MainGame.currentBaddies.get(i).hitBox);
					if (x + MainGame.currentBaddies.get(i).getWidth() < 0) {
						x = GameProperties.SCREEN_WIDTH;
					}
					
					MainGame.currentBaddies.get(i).setX(x);
					
					
					
				}
				
				
				if (currentProjectiles != null) {
					
					for(int i = 0; i < currentProjectiles.size(); i++ ) {
						
						for(int j = 0; j < MainGame.currentBaddies.size(); j++) {
							
							if(MainGame.currentBaddies.get(j).hitBox.intersects(currentProjectiles.get(i).hitBox)) {
								//MainGame.currentBaddies.get(j).label = null;
								//MainGame.currentBaddies.get(j).setLabelReference(null);
								
								MainGame.currentBaddies.get(j).label.setIcon(null);
								MainGame.currentBaddies.remove(j);
								
								currentProjectiles.get(i).label.setIcon(null);
								currentProjectiles.remove(i);
								
								
//								for (int k = 0; k < MainGame.currentBaddies.size(); k++) {
//									MainGame.currentBaddies.get(j).repaint();
//								}
								
								
								//currentProjectiles.remove(i);
							}
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
