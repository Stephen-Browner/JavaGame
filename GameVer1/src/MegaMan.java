import java.awt.Color;
import java.awt.Graphics2D;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class MegaMan extends Sprite implements Runnable {
	
	//gravity and jumping constants <-- move these to game properties later
	public static final int GRAVITY = 1;
	public static final int JUMP_ACCEL_Y = -9;
	public static final int JUMP_ACCEL_X = 3;
	public static final int MAX_JUMP_TIME = 2;
	public static final int COLLISION_BUFFER = 7; //used to buffer collisions
	
	
	//velocity vars
	private int vx = 0;
	private int vy = 0;
	
	private boolean jumpAttempt = false;
	private boolean onGround = false;
	private int jumpTime = 0;
	
	
	private Platform platform;
	Platform[] platforms;
	private JLabel projectileLabel;
	private ImageIcon projectileImage;
	
	private MainGame game;
	public void setMainGame(MainGame temp)		{this.game = temp;}
	
	
	
	
	
	public void setPlatform(Platform temp) {this.platform = temp;}
	
	public void setPlatforms(Platform[] temp) {this.platforms = temp;}
	
	public Projectile shoot() {
		Projectile projectile = new Projectile(this.x, this.y, 16, 13, "res/tinyProjectile.png");
		projectileLabel = new JLabel();
		projectileImage = new ImageIcon( getClass().getResource( projectile.getSpriteImage() ) );
		projectileLabel.setIcon(projectileImage);
		projectileLabel.setSize(projectile.getWidth(), projectile.getHeight());
		projectileLabel.setLocation(this.x, this.y);
		projectile.setLabelReference(projectileLabel);
		
		return projectile;
		
	}


	
	public MegaMan(int x, int y, int width, int height, String spriteImage) {
		super(x, y, width, height, spriteImage);
	}
	


	public void setJump(boolean jump) {
		this.jumpAttempt = jump;
	}
	
	@Override
	public void run() {
		
		while(true) {
			int y = this.getY();
			int x = this.getX();
			this.vy += GRAVITY;
			int py = platform.getY();
			int projectileX;
			
			
			if(y > GameProperties.SCREEN_HEIGHT) {
				game.gameOver();
				
				
			}
			//projectile movement loop
			if (game.currentProjectiles.size() != 0) {
				for (int i = 0; i < game.currentProjectiles.size(); i++ ) {
					projectileX = game.currentProjectiles.get(i).getX();
					projectileX += 5;
					
					game.currentProjectiles.get(i).setX(projectileX);
				}
			}
			
			
			
			//platform movement
			for (int i = 0; i < 4; i++) {
				int px = platforms[i].getX();
				px -= 1;
				if (px + platforms[i].getWidth() < 0) {
					px = GameProperties.SCREEN_WIDTH;
				}
				platforms[i].setX(px);
			}
			
			for (int i = 0; i < 4; i++) {
				
				if(this.hitBox.intersects(platforms[i].hitBox)){
					
					
					
					//checking where collision is happening
					//+15 at end to act as a buffer
					if (y > platforms[i].y - platforms[i].height && y < (platforms[i].y - platforms[i].height)+15) {
						vy = 0; //reset our velocity
						vx = 0;
					}else {
					
						if (x >= platforms[i].x - platforms[i].width && x < platforms[i].x) {
							x = platforms[i].x - platforms[i].width;
						} 
						
						if (x <= platforms[i].x + platforms[i].width && x > platforms[i].x) {
							x = platforms[i].x + platforms[i].width;
						}
					}
		
					
				}
				
			}
			
			
			
			if(vy == 0) {
				onGround = true; //we are on the ground if our y vel is 0
			} else {
				onGround = false;
			}
			
			if(jumpAttempt) {
				if(onGround) {
					jumpTime = MAX_JUMP_TIME;
					vy = JUMP_ACCEL_Y;
					vx = JUMP_ACCEL_X;
				} else if(jumpTime > 0) {
					vy += JUMP_ACCEL_Y;
				}else {
					//nothing
				}
				jumpTime --;
			}
			
			y += this.vy;
			x += this.vx;
			this.setX(x);
			this.setY(y);
			
			
			try {
				Thread.sleep(1000/60);
			} catch (Exception e) {
				//
			}
		}
		
	}
	
	
	
	
}
	
	
	
	
	
	
	

