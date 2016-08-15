import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class SnakeGame extends JFrame implements KeyListener,Runnable {
	private static Thread SnakeThread = null;
	private boolean isRun = true;
	private boolean isdraw = true;
	private boolean splash = true;
	private int bodyX[] = new int[100];
	private int bodyY[] = new int[100];
	private int foodX;
	private int foodY;
	private int direction;
	private int length = 5;
	private Timer timer;
	private int ScoreBase = 10;
	private boolean shortenLen = false;
	private int powerX;
	private int powerY;
	private int powertime = 200;
	
	static int food = 0;
	static int Score = 0;
	static int level = 5;
	static int FPS = 30;
	
	int i;
	int newX;
	int newY;
	
	public static int moveUp=1;
	public static int moveDown=2;
	public static int moveLeft=3;
	public static int moveRight=4;
	
	//main function
    public static void main(String[] args) {
    	if(args.length > 0){
    		FPS=Integer.parseInt(args[0]);
    		level=Integer.parseInt(args[1]);
    	}
    	new SnakeGame();
    }
    
    public SnakeGame() {
    	setTitle("Snake");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 600);
        setVisible(true);
        setResizable(false);
        addKeyListener(this);
        this.repaint();
		timer = new Timer (1000/FPS, new TimerListerner());
		timer.start();
    }
    
    
    public void run(){
    	for(i=0; i<length; i++){
    		bodyX[i] = 200-i*10;
    		bodyY[i] = 300;
    	}
    	newX = bodyX[0];
    	newY = bodyY[0];
    	direction = moveRight; 
    	while (SnakeThread != null){
    		SnakeMove();
    		isdraw = true;
    		try{
    			Thread.sleep(500/level);
    		}
    		catch(Exception ex){
    		}
    	}
    }
    
    
    public void SnakeMove(){
    	if(isRun == true){
    		Check();
    		if(SnakeThread!=null){
    		
    			//snake ate a food
    			if(foodX == bodyX[0] && foodY == bodyY[0]){
    				// the body array length is 100
    				if(length<100){
    					length = length+1;
    				}
    				Score = Score + ScoreBase * level;
    				food = food + 1;
    				
    				if(food % 10 == 0 && level <=10){
    					level = level + 1;
    					shortenLen = true;
        				powerX=(int)Math.floor(Math.random()*59)*10;
        				powerY=(int)Math.floor(Math.random()*54)*10+30;
    				}
    				
    				Toolkit.getDefaultToolkit().beep();  
    				foodX=(int)Math.floor(Math.random()*59)*10;
    				foodY=(int)Math.floor(Math.random()*54)*10+30;
    			}
    			
    			
    			if(powerX == bodyX[0] && powerY == bodyY[0]){
    				if(length>=5){
    					length = length - 2;
    				}
    				shortenLen = false;
    				Toolkit.getDefaultToolkit().beep();
    			}
    			
    			if(powertime <= 0){
    				shortenLen = false;
    				powertime = 200;
    			}
    			if(shortenLen == true){
    				powertime = powertime - 1;
    			}
    			i=0;
    			for(i=length-1; i>0; i--){
    				bodyX[i] = bodyX[i-1];
    				bodyY[i] = bodyY[i-1];
    			}
    		
    			if(direction == SnakeGame.moveUp){
    				bodyY[0]=bodyY[0]-10;
    			}
    			else if(direction ==SnakeGame.moveDown){
    				bodyY[0]=bodyY[0]+10;
    			}
    			else if(direction == SnakeGame.moveLeft){
    				bodyX[0]=bodyX[0]-10;
    			}
    			else if(direction == SnakeGame.moveRight){
    				bodyX[0]=bodyX[0]+10;
    			}
    		}
    	}
    }
    
    
    public void Check(){
    	if(direction == SnakeGame.moveUp){
    		newY=newY-10;
    	}
    	else if(direction == SnakeGame.moveDown){
    		newY=newY+10;
    	}
    	else if(direction == SnakeGame.moveLeft){
    		newX=newX-10;
    	}
    	else if(direction == SnakeGame.moveRight){
    		newX=newX+10;
    	}
    	
    	// check if snake hit the wall
    	if(newX>590 || newX<0 || newY>590 || newY<20){
    		SnakeThread = null;
    		isRun = false;
    		Toolkit.getDefaultToolkit().beep();  
    		timer.stop();
    		JOptionPane.showMessageDialog(this,"Game Over");
    	}
    	
    	//check if snake hit itself
    	for(i=1; i<length; i++){
    		if(bodyX[i]==newX && bodyY[i]==newY){
    			SnakeThread = null;
    			isRun = false;
    			Toolkit.getDefaultToolkit().beep();  
    			timer.stop();
    			JOptionPane.showMessageDialog(this,"Game Over");
    		}
    	}
    }
    
    
    public void keyPressed(KeyEvent e){
    	if(SnakeThread != null && isdraw == true){
    		if(e.getKeyCode()==KeyEvent.VK_UP){
    			if(direction != SnakeGame.moveDown && direction != SnakeGame.moveUp){
    				direction = SnakeGame.moveUp;
    				isdraw = false;
    			}
    		}
    		else if(e.getKeyCode()==KeyEvent.VK_DOWN){
    			if(direction != SnakeGame.moveUp && direction != SnakeGame.moveDown){
    				direction = SnakeGame.moveDown;
    				isdraw = false;
    			}
    		}
    		else if(e.getKeyCode()==KeyEvent.VK_LEFT){
    			if(direction != SnakeGame.moveRight && direction != SnakeGame.moveLeft){
    				direction = SnakeGame.moveLeft;
    				isdraw = false;
    			}
    		}
    		else if(e.getKeyCode()==KeyEvent.VK_RIGHT){
    			if(direction != SnakeGame.moveLeft && direction != SnakeGame.moveRight){
    				direction = SnakeGame.moveRight;
    				isdraw = false;
    			}
    		}
    		else if(e.getKeyCode()==KeyEvent.VK_SPACE){
    			if(isRun==true){
    				isRun = false;
    			}
    			else{
    				isRun = true;
    			}
    		}
    		else if(e.getKeyCode()==KeyEvent.VK_R){
    			length = 5;
    			Score = 0;
    			food = 0;
    			isRun= true;
    			foodX=(int)Math.floor(Math.random()*59)*10;
    			foodY=(int)Math.floor(Math.random()*54)*10+30;
    			timer.start();
    			SnakeThread=new Thread(this);
    			SnakeThread.start();
    		}
    	}
    	else {
    		if(e.getKeyCode()==KeyEvent.VK_SPACE || e.getKeyCode()==KeyEvent.VK_R){
    			length = 5;
    			Score = 0;
    			food = 0;
    			isRun= true;
    			foodX=(int)Math.floor(Math.random()*59)*10;
    			foodY=(int)Math.floor(Math.random()*54)*10+30;
    			timer.start();
    			SnakeThread=new Thread(this);
    			SnakeThread.start();
    			splash = false;
    		}
    	}
    }
	public void keyReleased(KeyEvent e) 
	{  
		// do nothing
	}
	public void keyTyped(KeyEvent e) 
	{ 
		// do nothing
	}
	
	private class TimerListerner implements ActionListener{
		public void actionPerformed(ActionEvent e){
			repaint();
		}
	}
	
	public void paint(Graphics g){
		if(splash == true){
			super.paint(g);
			g.setColor(Color.white);
			g.fillRect(0,0,800,600);
			
			g.setColor(Color.black);
			g.setFont(new Font("TimesRoman", 3, 20));
			g.drawString("How to play:", 200, 420);
			g.drawString("Arrow key to control", 200, 440);
			g.drawString("Space: Pause/Unpause", 200, 460);
			g.drawString("R: Restart", 200, 480);
			
			g.setColor(Color.red);
			g.setFont(new Font("TimesRoman", 1, 50)); 
			g.drawString("       Snake" , 200, 200);
			
			
			g.setColor(Color.orange);
			g.setFont(new Font("TimesRoman", 3, 30)); 
			g.drawString("CS349 Assignment 1" , 200, 290);
			g.drawString("Student Name: Haojun Luo" , 200, 320);
			g.drawString("Student ID: 20528243" , 200, 350);
		}
		else{
			super.paint(g);
			g.setColor(Color.white);
			g.fillRect(0,0,600,600);
			
			//snake
			g.setColor(Color.blue);
			for(i=0; i<length; i++){
				g.fillRect(bodyX[i], bodyY[i], 10, 10);
			}
			
			//food
			g.setColor(Color.red);
			g.fillRect(foodX, foodY, 10, 10);
			
			//power
			if(shortenLen == true){
				g.setColor(Color.black);
				g.fillRect(powerX, powerY, 10, 10);
			}
			
			//right bar
			g.setColor(Color.darkGray);
			g.fillRect(600, 0, 200, 600);
			g.setColor(Color.green);
			
			//game status
			g.drawString("Level: " + level , 610, 70);
			g.drawString("Score: " + Score , 610, 80);
			g.drawString("food: " + food, 610, 90);
			if(isRun==true){
				g.drawString("Status: Running" , 610, 100);
			}
			else{
				g.drawString("Status: Paused" , 610, 100);
			}
			if(shortenLen == true){
				g.drawString("Power Remain: "+ powertime , 610, 110);
			}
			
			
			
			//how to play
			g.drawString("How to play:", 610, 300);
			g.drawString("Arrow key to control", 610, 320);
			g.drawString("Space: Pause/Unpause", 610, 330);
			g.drawString("R: Restart", 610, 340);
			
			
			//information
			g.setColor(Color.orange);
			g.setFont(new Font("TimesRoman", 3, 13)); 
			g.drawString("CS349 Assignment 1" , 610, 486);
			g.drawString("Student Name: Haojun Luo" , 610, 500);
			g.drawString("Student ID: 20528243" , 610, 514);
		}
	}
}

