import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Display extends JPanel{
	
	public static boolean hero = false;
	public static boolean town = false;
	public static boolean hideout = false;
	
	private static JFrame frame;
	private static JPanel stats;
	private static JLabel map;
	private static JScrollPane scrollPane;
	private static JTextArea console;
	private static JTextArea playerStats;
	
	public static String dispString = "";
	public static int lines = 0;
	
	public static Town satbury;
	public static Town clacton;
	public static Town kelna;
	public static Town lockinge;
	public static Town bredon;
	public static Hideout ho1;
	public static Hideout ho2;
	public static Hideout ho3;
	public static Hideout ho4;
	public static Hideout ho5;
	public static Graphics g;
	//double buffer
	//final BufferedImage bi1;
	//button pannels
	//private static JPanel buttonsPanel
	//button
	private static JButton exit;
	private static JButton save;
	private static JButton load;
	
	private static BufferedImage bufferedImage;
	
	Point location = new Point(147,147);
	
	
	public static void main(String[] args) {
		//final Display frame = new Display();
		/////
		//KeyListener listener = new MyKeyListener();
		//addKeyListener(listener);
		/////
		
		//initListeners(); //Keyboard shit
		
		KeyListener keyListeners = new KeyListener() {
        public void keyPressed(KeyEvent e) {
        	if(hero) {Hero.keyPressed(e);}
        	if(town) {Town.keyPressed(e);}
        	if(hideout) {Hideout.keyPressed(e);}
        	
        }

        public void keyReleased(KeyEvent e) {
        	 
        }

        public void keyTyped(KeyEvent e) {
            
        }
    };
    
		frame = new JFrame();
		// intiating the frame
		frame.setTitle("Bitter Bandits");
		frame.setLayout(null);
		frame.setLocation(0,0);
		frame.setSize(1080,750);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addKeyListener(keyListeners);
		frame.setVisible(true);

		/*/initialing character dot
				component = new CircleComponent(20);
		        component.setLocation(20,220);
		        component.setSize(component.getPreferredSize());
		        
		        frame.add(component);
		        frame.repaint();
		 *///////////
		map = new JLabel();
		// Live Here
		drawImage("Home.png", 31,207,21,21);
		
		// Ranch
		drawImage("Town.png", 143,325,21,21);
		
		// town 2
		drawImage("Town.png", 136,626,21,21);
		
		// town 3
		drawImage("Town.png", 360,395,21,21);
		
		// town 4
		drawImage("Town.png", 619,360,21,21);
		
		// town 5
		drawImage("Town.png", 556,577,21,21);
		
		// Hideout 1
		drawImage("Rock.png", 269,332,21,21);
		
		// Hideout 2
		drawImage("Rock.png", 262,479,21,21);
		
		// Hideout 3
		drawImage("Rock.png", 514,367,21,21);
		
		// Hideout 4
		drawImage("Rock.png", 584,500,21,21);
		
		// MEGA BOSS
		drawImage("Rock.png", 651,655,21,21);
		
		/// drawing images
		//drawImage("Map_Background.png", 10,185,700,525);
		
		// Hat
		drawImage("Hat1.png", 885,310,30,17);
		// Gun
		drawImage("Gun1.png", 960,425,30,17);
		// Cowboy
		drawImage("Cowboy.png", 720,300,350,300);

		//
		
		///
		
		//stats
		stats = new JPanel();
		frame.add(stats);
		stats.setLayout(null);
		stats.addKeyListener(keyListeners);
		
		stats.setLocation(720, 10);
		stats.setSize(350,600);
		stats.setBorder(BorderFactory.createLineBorder(Color.black));
		
		playerStats = new JTextArea("");
		playerStats.setSize(350,250);
		playerStats.setFocusable(true);
		playerStats.setLayout(null);
		playerStats.setLineWrap(true);
		playerStats.setEditable(false);
		
		stats.add(playerStats);
		
		// console
		// console, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
		
		console = new JTextArea("");
		console.setEditable(false);
		console.setFocusable(true);
		console.setLayout(null);
		console.setLineWrap(true);
		
		scrollPane = new JScrollPane();
		scrollPane.setSize(700,165);
		scrollPane.setLocation(10, 10);
		scrollPane.setVisible(true);
		scrollPane.setLayout(null);
		
		//frame.add(console);
		//console.add(scrollPane)
		scrollPane.add(console);
		frame.add(scrollPane);
 
		console.setSize(700, 165);

		console.addKeyListener(keyListeners);
		//console.setRows( );

		//exit button
		exit = new JButton();
		exit.addKeyListener(keyListeners);
		frame.add(exit);
		
		exit.setVisible(true);
		exit.setLocation(720, 620);
		exit.setText("exit");
		exit.setFocusable(true);
		exit.setSize(110,90);
		
		//save button
		save = new JButton();
		save.addKeyListener(keyListeners);
		frame.add(save);
		
		save.setVisible(true);
		save.setLocation(840, 620);
		save.setText("save");
		save.setFocusable(true);
		save.setSize(110,90);
		
		//load button
		load = new JButton();
		frame.add(load);
		load.addKeyListener(keyListeners);
		
		load.setVisible(true);
		load.setLocation(960, 620);
		load.setText("load");
		load.setFocusable(true);
		load.setSize(110,90);
		
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frame.dispose();
				System.exit(0);
			}

		});
		
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.print("OK");
			}
		});
		
		load.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.print("OK");
			}
		});
		hero = true;
		
		satbury = new Town("Satubury",19,20,10,15,10,15,20,"Why, I know. Those bandits have a hideout in the east.");
		clacton = new Town("Clacton",18,63,15,30,15,20,22,"Those Bandits, they are northeast a bit aways.");
		kelna = new Town("Kelna",50,30,20,75,20,75,24,"You are tracking those bandits? Thank God. They have a hideout to the east.");
		lockinge = new Town("Lockinge",87,25,25,100,25,100,26,"Damn those bandits. They have a hideout to the south of here.");
		bredon = new Town("Bredon",78,56,30,150,30,150,28,"You have to take out their leader to get rid of the bandits. Go to the southeast.");

		ho1 = new Hideout(37,20,3, 20,"You cannot defeat me. Someone in a town to the southwest might know where my brothern are.");
		ho2 = new Hideout(31,42,3,22,"My allies are near. That brat in the town to the northeast better not rat out their location.");
		ho3 = new Hideout(72,26,4,24,"Go east to the next town. Maybe someone there can stop you.");
		ho4 = new Hideout(82,45,5,26,"My boss will destroy you. In the town to the southeast someone will let you to him.");
		ho5 = new Hideout(95,70,1,28,"You have destoyed my entire life work. YOU....");
		//double buffer
		
		Hero.setAge(18);
		Hero.setX(3);
		Hero.setY(3);
		Hero.setCurrentHealth(20);
		Hero.setMoney(50);
		Hero.setAttack(10);
		Hero.setStyle(10);
		Hero.setMaxHealth(20);
		
		Hero.intro1();

		updateStats();
	}

	
	public static void drawImage(String imagePath, int location_x, int location_y, int size_x, int size_y ){
		try {
			bufferedImage = ImageIO.read(new File(imagePath));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		JLabel image = new JLabel(new ImageIcon(bufferedImage));
		//image.setLayout(null);
		frame.add(image);
		
		image.setLocation(location_x, location_y);
		image.setSize(size_x, size_y);
		//image.setBorder(BorderFactory.createLineBorder(Color.black));

	}

	public static void output(String s) {
		dispString += s+ "%n";
		if(lines<10) {
			lines++;
		} else {
			int loc = dispString.indexOf("%n");
			dispString = dispString.substring(loc+2);
		}
		console.setText(String.format(dispString));

		
	}
	/*@Override
	public  void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		
		//drawImage("Gun1.png",(Hero.getX()*7)+10,(Hero.getY()*7)+185+7,25,25);
		//paint(g);
		
		//up
		drawImage("Gun1.png",(Hero.getX()*7)+10,(Hero.getY()*7)+185+7,25,25);
		
			
			
		}*/ 
	public static void move(String move) {    // player move
		
		int direction = 0; //0 = none , 1 = up , 2 = down , 3 = right , 4 = left
		if (move.equals("up")){
			direction = 1;
		}
		else if (move.equals("down")){
			direction = 2;
		}else if(move.equals("right")){
			direction = 3;
		}else if(move.equals("left")){
			direction = 4;
		}
		
		
		
		switch(direction){
		
		case(1) : {
			
			
			//up
			drawImage("Gun1.png",(Hero.getX()*7)+10,(Hero.getY()*7)+185+7,25,25);
			break;
			
		}
		case(2): {
			drawImage("Gun1.png",(Hero.getX()*7)+10,(Hero.getY()*7)+185-7,25,25);
	
			
			
			break;
		}
		case (3):{
			
			drawImage("Gun1.png",(Hero.getX() * 7)+10+7,(Hero.getY()*7)+185,25,25);
			
			break;
		}
		case(4): {
		
			drawImage("Gun1.png",(Hero.getX() * 7)+10-7 ,(Hero.getY()*7)+185,25,25);
			
			break;
		}
		}
	
	}
	
	public static void updateStats() {
		
		int age = Hero.getAge();
		int curHealth = Hero.getCurrentHealth();
		int maxHealth = Hero.getMaxHealth();
		int attack = Hero.getAttack();
		int style = Hero.getStyle();
		int money = Hero.getMoney();
		int whiskey = Hero.getWhiskey();
		
		String stat = String.format("Age: %2d%nHealth: %3d / %-3d%nAttack: %3d%nStyle: %2d%nMoney: $%5d%nWhiskey: %2d", age, curHealth, maxHealth, attack, style, money, whiskey);
		playerStats.setText(stat);
		
	}
/*	public static void drawGun(Point location){
	
		try {
				bufferedImage = ImageIO.read(new File(imagePath));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			JLabel image = new JLabel(new ImageIcon(bufferedImage));
			//image.setLayout(null);
			frame.add(image);
			
			image.setLocation(location_x, location_y);
			image.setSize(size_x, size_y);
			//image.setBorder(BorderFactory.createLineBorder(Color.black));
		
		drawImage("Gun1.png",location.getX() ,location.getY(),25,25);
	}*/
	
	public static void updateMap(String move) {   // new towns
		
		
		
	}
	
	public static void newGun() {
		
		int fileNum = (int)Math.random()%7;
		String fileName = "Gun" + (fileNum + 1) + ".png";
		// Gun
		drawImage(fileName, 890,380,30,17);
		
	}
	
	public static void newHat() {
		
		int fileNum = (int)Math.random()%7;
		String fileName = "Hat" + (fileNum + 1) + ".png";
		drawImage(fileName, 890,380,30,17);
		
	}
	
	/*public static Point getLocation(){
		int moo_x = Hero.getX();
		int moo_y = Hero.getY();
		
		Point location = new Point(moo_x * 7, moo_y * 7);
		
		
		return location;
	}*/
	

	/*public void keyPressed(KeyEvent e) {
		if(hero) {
			Hero.keyPressed(e);
		} else if (town) {
			Town.keyPressed(e);
		} else if (hideout) {
			Hideout.keyPressed(e);
		}
	}*/
	
	public static void initCharacter(){
		
		 
	}
}

