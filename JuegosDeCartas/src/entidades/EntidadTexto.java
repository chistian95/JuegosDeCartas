package entidades;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import juegos.Juego;

public class EntidadTexto extends Entidad {
	private static final Font fuente = new Font("Arial", Font.BOLD, 14);
	
	private String texto;

	public EntidadTexto(int x, int y, String texto, Juego juego) {
		super(x, y, juego);
		this.texto = texto;
	}

	@Override
	public void pintar(Graphics2D g) {
		g.setColor(Color.WHITE);
		g.setFont(fuente);
		g.drawString(texto, x, y);
	}
	
	public String getTexto() {
		return texto;
	}
	
	public void setTexto(String texto) {
		this.texto = texto;
	}
}
