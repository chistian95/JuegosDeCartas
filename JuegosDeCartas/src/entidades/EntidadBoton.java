package entidades;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import juegos.Juego;

public class EntidadBoton extends Entidad {
	private static final Font fuente = new Font("Arial", Font.BOLD, 14);
	
	private int ancho;
	private int alto;
	private String texto;
	private String accion;
        private boolean activado;

	public EntidadBoton(int x, int y, int ancho, int alto, String texto, String accion, Juego juego) {
		super(x, y, juego);
		this.ancho = ancho;
		this.alto = alto;
		this.texto = texto;
		this.accion = accion;
                activado = true; 
	}

	@Override
	public void pintar(Graphics2D g) {
		g.setColor(new Color(0, 0, 126));
		if(!activado) {
			g.setColor(new Color(64, 64, 64));
		}			

		g.fillRect(x, y, ancho, alto);

		g.setColor(Color.BLACK);
		g.drawRect(x, y, ancho, alto);

		g.setColor(Color.WHITE);
		g.setFont(fuente);
                FontMetrics fm = g.getFontMetrics();
                Rectangle2D r = fm.getStringBounds(texto, g);
                int dx = (ancho - (int) r.getWidth())/2;
                int dy = (alto - (int) r.getHeight())/2 + fm.getAscent();
		g.drawString(texto, x+dx, y+dy);
	}
	
	public String getAccion() {
		return accion;
	}
	
	public int getX2() {
		return x+ancho;
	}
	
	public int getY2() {
		return y+alto;
	}

        public void setActivado(boolean b) {
                activado = b;
        }

        public boolean isActivado() {
                return activado; 
        }
}
