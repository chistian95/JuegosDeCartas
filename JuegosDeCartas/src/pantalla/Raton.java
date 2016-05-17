package pantalla;

import java.awt.event.MouseEvent;

import entidades.EntidadBoton;
import juegos.Juego;

public class Raton {
	private Juego juego;
	
	public Raton(Juego juego) {
		this.juego = juego;
	}
	
	public void ratonPulsado(MouseEvent e) {
		for(EntidadBoton boton : juego.getBotones()) {
			if(e.getX() >= boton.getX() && e.getX() <= boton.getX2() && e.getY() >= boton.getY() && e.getY() <= boton.getY2()) {
				juego.ejecutarAccion(boton.getAccion());
				return;
			}
		}
	}
}
