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
import java. awt. Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;



public class MainGame extends JFrame implements ActionListener, KeyListener {
	
	//score
	private static int score;
	
	public static int level;
	
	
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
	private static JLabel scoreLabel;
	private JLabel gameOverLabel;
	private JLabel backgroundLabel;
	private JLabel backgroundLabel2;
	private JLabel backgroundLabel3;
	private ImageIcon backgroundImageIcon;
	private ImageIcon backgroundImageIcon2;
	
	
	
	public List<Projectile> currentProjectiles = new ArrayList <Projectile>();
	public List<Baddie> currentBaddies = new ArrayList <Baddie>();
	
	Platform[] platforms; //array of platforms
	
	//container to contain elements
	private Container content;
	
	int index = 0;
	int bxLocation = 0;
	int byLocation = 0;
	
	private JButton startButton;
	private JButton nextLevelButton;
	
	//GUI set up
	public MainGame() {
		
		
		
		//title
		super("Mega Man");
		setSize(GameProperties.SCREEN_WIDTH,GameProperties.SCREEN_HEIGHT);
		score = 0;
		level = 1;
	
		megaMan = new MegaMan(100, 100, 70, 70, "res/standing.gif"); //new object
		
		backgroundImageIcon = new ImageIcon(getClass().getResource("res/cloud.png"));
		backgroundImageIcon2 = new ImageIcon(getClass().getResource("res/cloud2.png"));
		
		backgroundLabel = new JLabel(backgroundImageIcon);
		backgroundLabel.setSize(200, 200);
		backgroundLabel.setLocation(0, -60);
		
		backgroundLabel2 = new JLabel(backgroundImageIcon2);
		backgroundLabel2.setSize(200, 200);
		backgroundLabel2.setLocation(250, 0);
		
		backgroundLabel3 = new JLabel(backgroundImageIcon);
		backgroundLabel3.setSize(200, 200);
		backgroundLabel3.setLocation(550, -30);
		
		//backgroundLabel.setSize(800, 800);
		//backgroundLabel.setVisible(true);
		
		
		createBaddies();
		
		
		Platform[] platforms = new Platform[15];
		
		for(int i = 100; i< 1050; i+= 200) {
			
			platform = new Platform(i, 400 , 120, 70, "res/platform2.jpg");
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
		scoreLabel = new JLabel("Score: " + score);
		
		 //+ Integer.toString(score)
		//scoreLabel.setText("Score: ");
		scoreLabel.setForeground(Color.BLACK);
		scoreLabel.setLocation(400, -25);
		scoreLabel.setSize(100, 100);
		
		
		
		//scoreLabel.setLocation(100, 100);

		
		
		
		//System.out.println(megaMan.getSpriteImage());
		megaManImage = new ImageIcon( getClass().getResource( megaMan.getSpriteImage() ) );
		megaManLabel.setIcon(megaManImage);
		megaManLabel.setSize(megaMan.getWidth(), megaMan.getHeight());
		megaMan.setLabelReference(megaManLabel);
		megaMan.setPlatform(platform);
		megaMan.setPlatforms(platforms);
		
		baddie.setcurrentProjectiles(currentProjectiles);
		


		content = getContentPane();
		//content.setBackground(Color.gray);
		setLayout(null);
		//setContentPane(new JLabel(new ImageIcon ("res/background.gif")));
		
		
		
		//adding elements
		content.setBackground(new Color (135, 206, 235));
		
		this.add(megaManLabel);
		this.add(scoreLabel);
		
		
		System.out.println(scoreLabel.getLocation());
		

	
		content.addKeyListener(this);
		content.setFocusable(true);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		megaManThread = new Thread(megaMan, "MegaMan Thread");
		baddieThread = new Thread(baddie, "Baddie Thread");
	
		//megaManThread.start();
		//baddieThread.start();
		gameStart();
		
		baddie.setMainGame(this);
		megaMan.setMainGame(this);
		
		
		
		
		//System.out.println(backgroundLabel.getIcon());
		//System.out.println(backgroundLabel);
		
		this.add(backgroundLabel);
		this.add(backgroundLabel2);
		this.add(backgroundLabel3);

		
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
			if (mmx < 0) {
				mmx = 0;
			} 
		}
		
		if (e.getKeyCode() == KeyEvent.VK_D) {	
			mmx += GameProperties.CHARACTER_STEP;
			if (mmx > (GameProperties.SCREEN_WIDTH - megaMan.getWidth()) ) {
				mmx = GameProperties.SCREEN_WIDTH - megaMan.getWidth();
			}
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
		if (e.getSource() == startButton) {
			megaManThread.start();
			baddieThread.start();
			content.remove(startButton);
			//startButton = null;
		}
		
	}
	
	//triggers on program start. When clicked it starts the threads
	public void gameStart() {
		startButton = new JButton("Start Game");
		startButton.setSize(100, 100);
		startButton.setLocation(350, 100);
		startButton.addActionListener(this);
		startButton.setFocusable(false);
		this.add(startButton);
		//startButton.
	}
	
	
	//triggers when enemy dies
	public static void updateScore(){
		score += 100;
		scoreLabel.setText("Score: " + score);
	}
	
	public void gameOver() {
		gameOverLabel = new JLabel("GAME OVER");
		gameOverLabel.setForeground(Color.BLACK);
		gameOverLabel.setLocation(400, 100);
		gameOverLabel.setSize(300, 300);
		gameOverLabel.setFont(new Font("Serif", Font.BOLD, 20));
		gameOverLabel.setVisible(false);
		this.add(gameOverLabel);
		gameOverLabel.setVisible(true);
		//megaManThread.stop();
		//baddieThread.stop();
		
		
		//this.repaint();
	}
	
	
	//makes enemy arraylist
	public void createBaddies() {
		for (int i = 0; i < 6; i++) {
			
			bxLocation = randInt(300, 800);
			byLocation = randInt(150, 300);
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
	}

	
	
	public static void main(String[] args) {//MAIN GAME
		// TODO Auto-generated method stub
		
		MainGame myGame = new MainGame();
		myGame.setVisible(true);
		
		
		//myGame.megaManThread.start();
		//myGame.baddieThread.start();
		
	

	}

}
