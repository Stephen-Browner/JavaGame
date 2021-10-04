import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.JLabel;

public class MegaMan extends Sprite implements Runnable {
	
	//gravity and jumping constants <-- move these to game properties later
	public static final int GRAVITY = 1;
	public static final int JUMP_ACCEL_Y = -7;
	public static final int JUMP_ACCEL_X = 5;
	public static final int MAX_JUMP_TIME = 2;
	
	
	//velocity vars
	private int vx = 0;
	private int vy = 0;
	
	private boolean jumpAttempt = false;
	private boolean onGround = false;
	private int jumpTime = 0;
	
	private Platform platform;
	
	
	
	
	
	public void setPlatform(Platform temp) {this.platform = temp;}//passing memory location of class to object

//	public void setMegaManLabel(JLabel temp) {
//		this.megaManLabel = temp;
//	}
		
	
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
			
		
			//Rectangle pHitBox = platform.getRectangle();
			//System.out.println(pHitBox);
			
			//the object isn't being created in time I think? Not quite sure.
			
			//System.out.println(platform.getRectangle());
			//set test ground plane. replace with collision check later
			if(this.hitBox.intersects(platform.hitBox) || this.y > 400 ){
				//y = py; //we can't go under this point
				vy = 0; //reset our velocity
				vx = 0;
				
			}
			
			detectPlatformCollision();
			
			//System.out.println(platform.hitBox);
			//System.out.println(this.hitBox);
			
			
			//System.out.println(this.hitBox);
			
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
	
	private void detectPlatformCollision(){
	if (this.hitBox.intersects(platform.hitBox)) {
			System.out.println("Colitioned");
		}
		
	}
	
	public void draw(Graphics2D gtd) {
		gtd.setColor(Color.BLACK);
		gtd.fillRect(x, y, width, height );
	}
}
	
	
	
	
	
	
	

