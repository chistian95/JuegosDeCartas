package juegos;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import entidades.Entidad;
import entidades.EntidadBoton;
import pantalla.Raton;
import pantalla.Teclado;
import pantalla.Ventana;

public abstract class Juego implements Runnable {
	protected Teclado teclado;;
	protected Raton raton;
	protected Ventana ventana;
	protected List<Entidad> entidades;
	protected List<EntidadBoton> botones;
	protected boolean terminado;
	
	public Juego() {
		teclado = new Teclado(this);
		raton = new Raton(this);
		entidades = new ArrayList<Entidad>();
		botones = new ArrayList<EntidadBoton>();
		ventana = new Ventana(this);
		terminado = false;
		
		teclado.crearListenerTeclado(KeyEvent.VK_ESCAPE, "cerrar");
	}
	
        public Juego(Ventana ventana) {
                 teclado = new Teclado(this);
                 raton = new Raton(this);
                 entidades = new ArrayList<Entidad>();
                 botones = new ArrayList<EntidadBoton>();
                 this.ventana = ventana;
                 ventana.setJuego(this);
                 terminado = false;

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
	
	public Raton getRaton() {
		return raton;
	}
	
	public Ventana getVentana() {
		return ventana;
	}
	
	public List<Entidad> getEntidades() {
		return entidades;
	}
	
	public List<EntidadBoton> getBotones() {
		return botones;
	}
	
	public boolean isTerminado() {
		return terminado;
	}
}
