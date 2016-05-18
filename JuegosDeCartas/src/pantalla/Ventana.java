package pantalla;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.util.ConcurrentModificationException;
import java.util.ListIterator;

import javax.swing.JFrame;
import javax.swing.Timer;

import entidades.Entidad;
import juegos.Juego;

public class Ventana extends JFrame implements MouseListener, KeyListener {
	private static final long serialVersionUID = 1L;
	private static final int FPS = 60;
	
	private Juego juego;
	private BufferedImage bf;

	public Ventana(Juego juego) {
		this.juego = juego;
		
		setUndecorated(true);
        setSize(720, 480);
        setLocationRelativeTo(null);
        setVisible(true);
        
        bf = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
        
        addMouseListener(this);
        addKeyListener(this);
        
        addWindowListener(new WindowAdapter() {
        	public void windowClosing(WindowEvent e) {
        		dispose();
        		System.exit(0);
        	}
        });
        
        ActionListener listener = new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		repaint();
        	}
        };
        Timer t = new Timer(2000/FPS, listener);
        t.setRepeats(true);
        t.start();
	}
	
	public void paint(Graphics g) {
		Graphics2D bff = (Graphics2D) bf.getGraphics();
		
		bff.setColor(new Color(0, 126, 0));
		bff.fillRect(0, 0, getWidth(), getHeight());
		
		ListIterator<Entidad> it = juego.getEntidades().listIterator();
		while(it.hasNext()) {
			try  {
				Entidad en = it.next();
				en.pintar(bff);
			} catch(ConcurrentModificationException e) {}
			
		}
		
		g.drawImage(bf, 0, 0, null);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		juego.getTeclado().teclaPulsada(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		juego.getRaton().ratonPulsado(e);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}
	
	public void setJuego(Juego juego) {
		this.juego = juego;
	}
}
