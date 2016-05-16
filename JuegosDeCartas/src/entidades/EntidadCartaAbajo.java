package entidades;

import java.awt.Color;
import java.awt.Graphics2D;

public class EntidadCartaAbajo extends Entidad {
	private static final int ANCHO = 32;
	private static final int ALTO = 48;

	public EntidadCartaAbajo(int x, int y) {
		super(x, y);		
	}

	@Override
	public void pintar(Graphics2D g) {
		g.setColor(new Color(126, 0, 0));
		g.fillRect(x, y, ANCHO, ALTO);
		g.setColor(Color.BLACK);
		g.drawRect(x, y, ANCHO, ALTO);
	}
	
}
