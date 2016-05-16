package juegos;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import entidades.Entidad;
import pantalla.Teclado;
import pantalla.Ventana;

public abstract class Juego {
	protected Teclado teclado;;
	protected Ventana ventana;
	protected List<Entidad> entidades;
	
	public Juego() {
		teclado = new Teclado(this);
		entidades = new ArrayList<Entidad>();
		ventana = new Ventana(this);
		
		teclado.crearListenerTeclado(KeyEvent.VK_ESCAPE, "cerrar");
	}
	
	public void ejecutarAccion(String accion) {
		if(accion.equals("cerrar")) {
			System.exit(0);
		}
	}
	
	public Teclado getTeclado() {
		return teclado;
	}
	
	public Ventana getVentana() {
		return ventana;
	}
	
	public List<Entidad> getEntidades() {
		return entidades;
	}
}
