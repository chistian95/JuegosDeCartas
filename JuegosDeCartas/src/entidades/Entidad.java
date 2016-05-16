package entidades;

import java.awt.Graphics2D;

public abstract class Entidad {
	protected int x;
	protected int y;
	
	public Entidad(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public abstract void pintar(Graphics2D g);
	
	public void mover(int dx, int dy) {
		x += dx;
		y += dy;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
}
