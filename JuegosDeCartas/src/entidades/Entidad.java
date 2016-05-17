package entidades;

import java.awt.Graphics2D;

import juegos.Juego;

public abstract class Entidad {
	protected int x;
	protected int y;
	protected Juego juego;
	
	public Entidad(int x, int y, Juego juego) {
		this.x = x;
		this.y = y;
		this.juego = juego;
		juego.getEntidades().add(this);
	}
	
	public abstract void pintar(Graphics2D g);
	
	public void mover(int dx, int dy) {
		x += dx;
		y += dy;
	}
	
	public int getX() {
		return x;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public Juego getJuego() {
		return juego;
	}
}
