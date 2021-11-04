import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;



public class MainGame extends JFrame implements ActionListener, KeyListener {
	
	
	
	
	//character storage class
	private MegaMan megaMan;
	private Platform platform;
	private Baddie baddie;

	//enemy storage array
	
	
	
	//threads
	private Thread megaManThread;
	private Thread baddieThread;
	
	
	//labels to show graphics
	private JLabel megaManLabel;
	private ImageIcon megaManImage;
	private JLabel platformLabel;
	private ImageIcon platformImage;
	private JLabel baddieLabel;
	private ImageIcon baddieImage;
	
	
	public static List<Projectile> currentProjectiles = new ArrayList <Projectile>();
	public static List<Baddie> currentBaddies = new ArrayList <Baddie>();
	
	Platform[] platforms; //array of platforms
	
	//container to contain elements
	private Container content;
	
	int index = 0;
	int bxLocation = 0;
	int byLocation = 0;
	
	
	//GUI set up
	public MainGame() {
		
		
		
		//title
		super("Mega Man");
		setSize(GameProperties.SCREEN_WIDTH,GameProperties.SCREEN_HEIGHT);
	
		megaMan = new MegaMan(100, 100, 70, 70, "res/standing.gif"); //new object
		
		
		for (int i = 0; i < 1; i++) {
			
			bxLocation = randInt(300, 900);
			byLocation = randInt(0, 350);
			baddie = new Baddie(bxLocation, byLocation, 39, 39, "res/bigbadjava.png");
			
			
			baddieLabel = new JLabel();
			baddieImage = new ImageIcon( getClass().getResource( baddie.getSpriteImage() ) );
			baddieLabel.setIcon(baddieImage);
			baddieLabel.setSize(baddie.getWidth(), baddie.getHeight());
			baddieLabel.setLocation(baddie.x, baddie.y);
			baddie.setLabelReference(baddieLabel);
			this.add(baddieLabel);
			currentBaddies.add(baddie);
			
		}
		
		Platform[] platforms = new Platform[15];
		
		for(int i = 100; i< 600; i+= 200) {
			
			platform = new Platform(i, 400 , 70, 70, "res/platform.jpg");
			platforms[index] = platform;
			
			
			platformLabel = new JLabel();
			platformImage = new ImageIcon( getClass().getResource( platform.getSpriteImage() ) );
			platformLabel.setIcon(platformImage);
			platformLabel.setSize(platform.getWidth(), platform.getHeight());
			platformLabel.setLocation(platform.x, platform.y);
			platform.setLabelReference(platformLabel);
			this.add(platformLabel);
			
			System.out.println(platform.getX() + platform.getY());
			index ++;
			
			
		}
		
		
		megaManLabel = new JLabel(); //label for object

		
		
		
		//System.out.println(megaMan.getSpriteImage());
		megaManImage = new ImageIcon( getClass().getResource( megaMan.getSpriteImage() ) );
		megaManLabel.setIcon(megaManImage);
		megaManLabel.setSize(megaMan.getWidth(), megaMan.getHeight());
		megaMan.setLabelReference(megaManLabel);
		megaMan.setPlatform(platform);
		megaMan.setPlatforms(platforms);
		
		baddie.setcurrentProjectiles(currentProjectiles);
		


		content = getContentPane();
		content.setBackground(Color.gray);
		setLayout(null);
		
		
		
		//adding elements
		this.add(megaManLabel);

	
		content.addKeyListener(this);
		content.setFocusable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		megaManThread = new Thread(megaMan, "MegaMan Thread");
		baddieThread = new Thread(baddie, "Baddie Thread");
	
		megaManThread.start();
		baddieThread.start();
		

		
	}
	
	public void makeWalls() {
		for(int i = 50; i< 650; i+= 50) {
			
			Platform platform = new Platform(i, 600, 50, 50, "res/platform.jpg");
			platforms[index] = platform;
			index ++;
			
			System.out.println(platforms[index]);
		}
	}
	
	//random number generator used for enemy placement
	public int randInt(int Min, int Max) {
	    return Min + (int)(Math.random() * (Max - Min + 1));
	}
	

	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int mmx = megaMan.getX();
		int mmy = megaMan.getY();
		
		
		//left and right move
		if(e.getKeyCode() == KeyEvent.VK_A) {
			mmx -= GameProperties.CHARACTER_STEP;
		}
		
		if (e.getKeyCode() == KeyEvent.VK_D) {
			mmx += GameProperties.CHARACTER_STEP;
		}
		
		megaMan.setX(mmx);
		
		//jump
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			megaMan.setJump(true);
		}
		
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			
			Projectile tempProjectile = megaMan.shoot();
			currentProjectiles.add(tempProjectile);
			
			for (int i = 0; i < currentProjectiles.size(); i++) {
				this.add(currentProjectiles.get(i).label);
			}
			
			//this.repaint();
			
			
			
			
			
		}

	}
		
	

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_SPACE) {
		      megaMan.setJump(false);
		    }
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args) {//MAIN GAME
		// TODO Auto-generated method stub
		
		MainGame myGame = new MainGame();
		myGame.setVisible(true);
	

	}

}
