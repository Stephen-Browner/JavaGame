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
	
	private MainGame game;
	
	public void setMainGame(MainGame temp)		{this.game = temp;}
	
	public void setProjectile(Projectile temp) 	{this.projectile = temp;}//passing memory location of class to object
	
	public void setcurrentProjectiles(List<Projectile> temp) {this.currentProjectiles = temp;}
	
	//game = new MainGame();

	@Override
	public void run() {
		while (true) {
			
			
			int y = this.getY();
			int x = this.getX();
			
			if (game.currentBaddies.size() != 0) {
				for (int i = 0; i < game.currentBaddies.size(); i++) {
					x = game.currentBaddies.get(i).getX();
					x -= MainGame.level;
					
//					System.out.println(MainGame.currentBaddies.get(i).hitBox);
					if (x + game.currentBaddies.get(i).getWidth() < 0) {
						x = GameProperties.SCREEN_WIDTH;
					}
					
					game.currentBaddies.get(i).setX(x);
					
					
					
				}
				
				
				if (currentProjectiles != null) {
					
					for(int i = 0; i < currentProjectiles.size(); i++ ) {
						
						for(int j = 0; j < game.currentBaddies.size(); j++) {
							
							if(game.currentBaddies.get(j).hitBox.intersects(currentProjectiles.get(i).hitBox)) {
								//MainGame.currentBaddies.get(j).label = null;
								//MainGame.currentBaddies.get(j).setLabelReference(null);
								
								game.currentBaddies.get(j).label.setIcon(null);
								game.currentBaddies.remove(j);
								
								//currentProjectiles.get(i).label.setIcon(null);
								//currentProjectiles.remove(i);
								MainGame.updateScore();
								
							
							}
						}
						
					}
						
					//removes projectiles once off screen
					for(int i=0; i<currentProjectiles.size(); i++) {
						if(currentProjectiles.get(i).x > GameProperties.SCREEN_WIDTH) {
							currentProjectiles.get(i).label.setIcon(null);
							currentProjectiles.remove(i);
						}
					}
					
					//if baddies = 0, make more and increase level
					if(game.currentBaddies.size() == 0) {
						game.createBaddies();
						MainGame.level ++;
					}
					
				}

				
			} try {
				Thread.sleep(1000/60);
			} catch (Exception e) {
				//
			}
		}
		
	}

	

}
