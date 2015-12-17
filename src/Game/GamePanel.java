package Game;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

import GameState.GameStateManager;
import Input.InputHandler;
import Input.Joystick;
import Input.Keyboard;

@SuppressWarnings("serial")
public class GamePanel extends JPanel implements Runnable{
	private static final int WINDOW_SIZE = Config.TILE_SIZE * Config.VIEW_SIZE;
	
	private GameStateManager gsm;
	private Keyboard kb;
	private Joystick js;
	private Thread thread;
	private BufferedImage img;
	private Graphics2D buffer;
	
	private long frameTime;			//Milliseconds per frame
	
	private boolean running;
	
	private void render() {
		Graphics2D screen = (Graphics2D)this.getGraphics();
		screen.drawImage(img, 0, 0,  null);
		screen.dispose();
	}
	
	public GamePanel() {
		this.setPreferredSize(new Dimension(WINDOW_SIZE, WINDOW_SIZE));
	}
	
	@Override
	public void addNotify() {	
		super.addNotify();
		
		if(thread == null) {
			thread = new Thread(this);
			thread.start();
		}
		
		this.requestFocusInWindow();
	}

	@Override
	public void run() {
		gsm = new GameStateManager();
		kb = new Keyboard();
		js = new Joystick();
		InputHandler.setStateManager(gsm);
		
		img = new BufferedImage(WINDOW_SIZE, WINDOW_SIZE, BufferedImage.TYPE_INT_RGB);
		buffer = (Graphics2D)img.getGraphics();
		
		addKeyListener(kb);
		
		frameTime = 1000 / Config.FPS_CAP;
		
		long start;
		long elapsed;
		
		running = true;
		
		while(running) {
			start = System.nanoTime();
			
			update();
			draw();
			render();
			
			elapsed = (System.nanoTime() - start) / 1000000;
			
			if(elapsed < frameTime) {
				try {
					Thread.sleep(frameTime - elapsed);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private void update() { gsm.update(); }
	private void draw() { gsm.draw(buffer); }
}
