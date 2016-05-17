package entidades;

import java.awt.Color;
import java.awt.Graphics2D;

import juegos.Juego;

public class EntidadCirculo extends Entidad {
	private int radio;
	private Color color;
	
	public EntidadCirculo(int x, int y, int radio, Juego juego) {
		this(x, y, radio, Color.BLACK, juego);
	}

	public EntidadCirculo(int x, int y, int radio, Color color, Juego juego) {
		super(x, y, juego);
		this.radio = radio;
		this.color = color;
	}

	@Override
	public void pintar(Graphics2D g) {
		g.setColor(color);
		g.fillOval(x, y, radio*2, radio*2);
	}
	
	public int getRadio() {
		return radio;
	}

}
