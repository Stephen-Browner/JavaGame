import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;



public class MainGame extends JFrame implements ActionListener, KeyListener {
	
	
	
	
	//character storage class
	private MegaMan megaMan;
	
	private Platform platform;
	private Platform platform2;
	
	//enemy storage class
	
	//threads
	private Thread megaManThread;
	
	
	//labels to show graphics
	private JLabel megaManLabel;
	private ImageIcon megaManImage;
	
	Platform[] platforms; //array of platforms
	
	//container to contain elements
	private Container content;
	
	
	//GUI set up
	public MainGame() {
		
		
		
		//title
		super("Mega Man");
		setSize(GameProperties.SCREEN_WIDTH,GameProperties.SCREEN_HEIGHT);
		//platforms = new Platform[13];
		
		megaMan = new MegaMan(100, 100, 70, 70, "res/standing.gif"); //new object
		platform = new Platform();
		//platform2 = new Platform(0, 300, 500, 50);
		
		//makeWalls();
		
		
		megaManLabel = new JLabel(); //label for object
		
		
		
		//System.out.println(megaMan.getSpriteImage());
		megaManImage = new ImageIcon( getClass().getResource( megaMan.getSpriteImage() ) );
		megaManLabel.setIcon(megaManImage);
		megaManLabel.setSize(megaMan.getWidth(), megaMan.getHeight());
		megaMan.setLabelReference(megaManLabel);
		megaMan.setPlatform(platform);
		
		
		content = getContentPane();
		content.setBackground(Color.gray);
		setLayout(null);
		
		
		
		//adding elements
		this.add(megaManLabel);
		
		
		
	
		
		
		content.addKeyListener(this);
		content.setFocusable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		megaManThread = new Thread(megaMan, "MegaMan Thread");
		
		
		 //is has a hotbox. this is good. I'm jsut passing it wrong
		megaManThread.start();
		System.out.println(platform.hitBox);
	}
	
//	public void makeWalls() {
//		for(int i = 50; i< 650; i+= 50) {
//			int index = 0;
//			Platform platform = new Platform(i, 600, 50, 50);
//			platforms[index] = platform;
//			index ++;
//			
//			System.out.println(platforms[index]);
//		}
//	}
	

	public void paint(Graphics g) {
		
		super.paint(g);
		
		Graphics2D gtd = (Graphics2D) g;
		platform.draw(gtd);
		megaMan.draw(gtd);
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
