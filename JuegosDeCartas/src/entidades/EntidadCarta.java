package entidades;

import java.awt.Color;
import java.awt.Graphics2D;

import cartas.Numero;
import cartas.Palo;
import juegos.Juego;

public class EntidadCarta extends Entidad {
	protected static final int ANCHO = 32;
	protected static final int ALTO = 48;
	
	private int palo;
	private int numero;
	private boolean levantada;
	
	public EntidadCarta(int x, int y, int palo, int numero, boolean levantada, Juego juego) {
		super(x, y, juego);
		this.palo = palo;
		this.numero = numero;
		this.levantada = levantada;
	}
	
	public int getPalo() {
		return palo;
	}
	
	public int getNumero() {
		return numero;
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
