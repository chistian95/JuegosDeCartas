package entidades;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import juegos.Juego;

public class EntidadTexto extends Entidad {
	private static final Font fuente = new Font("Arial", Font.BOLD, 14);
	
	private String texto;
	private Color color;
	
	public EntidadTexto(int x, int y, String texto, Juego juego) {
		this(x, y, texto, Color.WHITE, juego);
	}

	public EntidadTexto(int x, int y, String texto, Color color, Juego juego) {
		super(x, y, juego);
		this.texto = texto;
		this.color = color;
	}

	@Override
	public void pintar(Graphics2D g) {
		g.setColor(color);
		g.setFont(fuente);
		g.drawString(texto, x, y);
	}
	
	public String getTexto() {
		return texto;
	}
	
	public void setTexto(String texto) {
		this.texto = texto;
	}
	
	public Color getColor() {
		return color;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
}
