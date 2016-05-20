package pantalla;

import java.awt.event.MouseEvent;

import entidades.Entidad;
import entidades.EntidadBoton;
import entidades.EntidadCarta;
import juegos.Juego;

public class Raton {
	private Juego juego;
	
	public Raton(Juego juego) {
		this.juego = juego;
	}
	
	public void ratonPulsado(MouseEvent e) {
		for(Entidad entidad : juego.getEntidades()) {
			EntidadCarta c;
			try {
				c = (EntidadCarta) entidad; 
			} catch(Exception ex) {
				continue;
			}			
			if(c.isClickable() && e.getX() >= c.getX() && e.getX() <= c.getX()+32 && e.getY() >= c.getY() && e.getY() <= c.getY()+48) {
				juego.ejecutarAccion(c.getAccion());
				return;
			}
		}
		for(EntidadBoton boton : juego.getBotones()) {
			if(boton.isActivado() && e.getX() >= boton.getX() && e.getX() <= boton.getX2() && e.getY() >= boton.getY() && e.getY() <= boton.getY2()) {
				juego.ejecutarAccion(boton.getAccion());
				return;
			}
		}
	}
}
