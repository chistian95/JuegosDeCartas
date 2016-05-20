package entidades;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import cartas.Numero;
import cartas.Palo;
import juegos.Juego;

public class EntidadCarta extends Entidad {
	protected static final int ANCHO = 32;
	protected static final int ALTO = 48;
	
	protected int palo;
	protected int numero;
	protected boolean levantada;
	protected Timer t;
	protected boolean animando;
	protected boolean clickable;
	protected String accion;
	
	protected int x1, y1, x2, y2;
	
	public EntidadCarta(int x, int y, int palo, int numero, boolean levantada, Juego juego) {
		super(x, y, juego);
		this.palo = palo;
		this.numero = numero;
		this.levantada = levantada;
		animando = false;
		clickable = false;
	}
	
	public void animacion(int xa1, int ya1, int xa2, int ya2) {	
		animando = true;
		x1 = xa1;
		y1 = ya1;
		x2 = xa2;
		y2 = ya2;
		ActionListener listener = new ActionListener() {
			double deltaX = (x2-x1)/10;
			double deltaY = (y2-y1)/10;
			public void actionPerformed(ActionEvent e) {
				x += deltaX;
				y += deltaY;
				if(x2 < x1 && x <= x2) {
					x = x2;
				} else if(x2 >= x1 && x >= x2) {
					x = x2;
				}
				if(y2 < y1 && y <= y2) {
					y = y2;
				} else if(y2 >= y1 && y >= y2) {
					y = y2;
				}
				if(x == x2 && y == y2) {
					pararTimer();
					animando = false;
				}
			}
		};
		t = new Timer(25, listener);
		t.setRepeats(true);
		t.start();
	}
	
	private void pararTimer() {
		t.stop();		
	}
	
	public int getPalo() {
		return palo;
	}
	
	public int getNumero() {
		return numero;
	}
	
	public boolean isAnimando() {
		return animando;
	}
	
	public void setClickable(boolean b) {
		clickable = b;
	}
	
	public boolean isClickable() {
		return true;
	}
	
	public String getAccion() {
		return x+","+y;
	}
	
	public boolean isLevantada() {
		return levantada;
	}
	
	public void setLevantada(boolean b) {
		levantada = b;
	}

	@Override
	public void pintar(Graphics2D g) {
		if(levantada) {
			g.setColor(Color.WHITE);
			g.fillRect(x, y, ANCHO, ALTO);

			g.setColor(Color.BLACK);
			g.drawString(Numero.NUMEROS[numero], x+12, y+24);
			if(palo >= 2) {
				g.setColor(Color.RED);
			}
			g.drawString(Palo.SIMBOLOS[palo], x+12, y+34);

			g.setColor(Color.BLACK);
			g.drawRect(x, y, ANCHO, ALTO);
		} else {
			g.setColor(new Color(126, 0, 0));
			g.fillRect(x, y, ANCHO, ALTO);
			g.setColor(Color.BLACK);
			g.drawRect(x, y, ANCHO, ALTO);
		}		
	}
}
