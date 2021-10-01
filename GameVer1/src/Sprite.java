import java.awt.Rectangle;

import javax.swing.JLabel;



public class Sprite {
	
	//data all sprites need
	protected int x,y;
	protected int width, height;
	protected String spriteImage;	
	protected Rectangle hitBox;
	
	protected JLabel label;
	
	
	
	//getters
	public int getX() {return x;}
	public int getY() {return y;}
	public int getWidth() {return width;}
	public int getHeight() {return height;}
	public String getSpriteImage() {return spriteImage;}


	//setters
	public void setX(int x) {
		this.x = x;
		this.hitBox.setLocation(this.x, this.y);
		this.label.setLocation(this.x, this.y);
	}
	
	public void setY(int y) {this.y = y;
	this.hitBox.setLocation(this.x, this.y);
	this.label.setLocation(this.x, this.y);
	}
	
	public void setWidth(int width) {
		this.width = width;
		this.hitBox.setSize(this.width, this.height);
	}
	
	public void setHeight(int height) {
		this.height = height;
		this.hitBox.setSize(this.width, this.height);
	}
	
	public void setSpriteImage(String spriteImage) {this.spriteImage = spriteImage;}
	
	public void setLabelReference(JLabel label) {this.label = label;}
	
	
	


	public Sprite() {
		super();
		this.x=0;
		this.y=0;
		this.width=0;
		this.height=0;
		this.spriteImage="";
		this.hitBox = new Rectangle(this.x, this.y, this.width, this.height);
	
	}

	public Sprite(int x, int y, int width, int height, String spriteImage) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.spriteImage = spriteImage;
		this.hitBox = new Rectangle(this.x, this.y, this.width, this.height);
	}
	
	//plaform constructor
	public Sprite(int x, int y, int width, int height) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.hitBox = new Rectangle(this.x, this.y, this.width, this.height);
		
	}
	
	
	public Sprite(int width, int height, String spriteImage) {
		super();
		this.x = 200; this.y = 200;
		this.width = width;
		this.height = height;
		this.spriteImage = spriteImage;
		this.hitBox = new Rectangle(this.x, this.y, this.width, this.height);
		
		
	}
	
	
	
	
	
	
}
