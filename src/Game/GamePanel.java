package Game;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

import GameState.GameStateManager;

@SuppressWarnings("serial")
public class GamePanel extends JPanel implements Runnable,
												MouseListener,
												MouseMotionListener,
												KeyListener {
	private static final int WINDOW_SIZE = Config.TILE_SIZE * Config.VIEW_SIZE;
	
	private GameStateManager gsm;
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
		
		img = new BufferedImage(WINDOW_SIZE, WINDOW_SIZE, BufferedImage.TYPE_INT_RGB);
		buffer = (Graphics2D)img.getGraphics();
		
		addMouseListener(this);
		addMouseMotionListener(this);
		addKeyListener(this);
		
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
	@Override
	public void keyPressed(KeyEvent e) { gsm.keyPressed(e.getKeyCode()); }
	@Override
	public void keyReleased(KeyEvent e) { gsm.keyReleased(e.getKeyCode()); }
	@Override
	public void mouseDragged(MouseEvent e) { gsm.mouseDragged(e); }
	@Override
	public void mousePressed(MouseEvent e) { gsm.mousePressed(e); }
	@Override
	public void mouseReleased(MouseEvent e) { gsm.mouseReleased(e); }
	@Override
	public void keyTyped(KeyEvent arg0) {}
	@Override
	public void mouseMoved(MouseEvent e) {}
	@Override
	public void mouseClicked(MouseEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
}
