package entidades;

import java.awt.Color;
import java.awt.Graphics2D;

import juegos.Juego;

public class EntidadCaja extends Entidad {
	private int ancho;
	private int alto;
	private Color color;
	
	public EntidadCaja(int x, int y, int ancho, int alto, Color color, Juego juego) {
		super(x, y, juego);
		this.ancho = ancho;
		this.alto = alto;
		this.color = color;
		
	}

	@Override
	public void pintar(Graphics2D g) {	
			g.setColor(color);
			g.fillRect(x, y, ancho, alto);
			g.setColor(Color.BLACK);
			g.drawRect(x, y, ancho, alto);
	}
	
	public int getAncho() {
		return ancho;
	}
	
	public int getAlto() {
		return alto;
	}

}
