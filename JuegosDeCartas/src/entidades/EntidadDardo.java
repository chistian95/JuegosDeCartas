package entidades;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Timer;

import juegos.Juego;
import juegos.JuegoDardos;

public class EntidadDardo extends Entidad {
	private static final int VELOCIDAD = 2;
	
	private Timer t;
	private int potencia;
	private int sentido;
	private boolean ejeX;
	private BufferedImage img;
	private EntidadCaja barra;
	private int puntos;

	public EntidadDardo(int x, int y, Juego juego) {
		super(x, y, juego);
		potencia = 100;
		try {
			img = ImageIO.read(ClassLoader.getSystemResourceAsStream("res/dardo.png"));
		} catch (IOException e) {
			System.out.println("Error al cargar el dardo");
			e.printStackTrace();
		}
		
		new EntidadCaja(50, 50, 25, 100, Color.WHITE, juego);
		barra = new EntidadCaja(50, 50, 25, 5, Color.RED, juego);
	}
	
	public void animarX() {
		sentido = 0;
		ejeX = false;
		ActionListener listener = new ActionListener() {			
			public void actionPerformed(ActionEvent e) {
				if(sentido == 0) {
					x--;
					if(x < juego.getVentana().getWidth()/2-100) {
						sentido = 1;
					}
				} else {
					x++;
					if(x > juego.getVentana().getWidth()/2+100) {
						sentido = 0;
					}
				}
			}
		};
		t = new Timer(VELOCIDAD, listener);
		t.setRepeats(true);
		t.start();
	}
	
	public void disparar() {
		t.stop();
		if(!ejeX) {
			ejeX = true;
			sentido = 0;
			ActionListener listener = new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(sentido == 0) {
						potencia--;
						if(potencia <= 0) {
							sentido = 1;
						}
					} else {
						potencia++;
						if(potencia >= 100) {
							sentido = 0;
						}
					}
					barra.setY(150-potencia);
				}
			};
			t = new Timer(VELOCIDAD, listener);
			t.setRepeats(true);
			t.start();
		} else {
			ActionListener listener = new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int ptFin = 220-potencia*2;
					y -= 1;
					if(y <= ptFin) {
						pararTimer();
						comprobarColision();
					}
				}
			};
			t = new Timer(VELOCIDAD, listener);
			t.setRepeats(true);
			t.start();
		}		
	}
	
	private void pararTimer() {
		t.stop();
	}
	
	private void mostrarPuntos() {
		new EntidadTexto(50, juego.getVentana().getHeight()-100, puntos+" puntos", juego);
	}

	private void comprobarColision() {
		JuegoDardos jg = (JuegoDardos) juego;
		puntos = 0;
		for(int i=jg.getDiana().size()-1; i>=0; i--) {
			EntidadCirculo dc = jg.getDiana().get(i);
			int radio = dc.getRadio();
			int xc = dc.getX()+radio;
			int yc = dc.getY()+radio;
			int x1 = (int) Math.pow(Math.abs(x-xc), 2);
			int y1 = (int) Math.pow(Math.abs(y-yc), 2);
			int res = (int) Math.sqrt(x1+y1);
			if(res <= radio) {
				puntos = i*30;				
				break;
			}
			
		}
		mostrarPuntos();
	}

	@Override
	public void pintar(Graphics2D g) {
		g.drawImage(img, x-4, y, 8, 16, juego.getVentana());
	}
	
	public void setPotencia(int p) {
		int potencia = 175-p;
		this.potencia = potencia;
	}
	
	public int getPuntos() {
		return puntos;
	}

}
