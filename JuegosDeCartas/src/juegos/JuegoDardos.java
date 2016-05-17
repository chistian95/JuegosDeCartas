package juegos;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import entidades.EntidadCirculo;
import entidades.EntidadDardo;
import entidades.EntidadTexto;
import principal.Jugador;

public class JuegoDardos extends Juego {
	private static final int nJugadores = 4;
	
	private List<Jugador> jugadores;
	private List<EntidadCirculo> diana;
	private EntidadDardo dardo;
	
	public JuegoDardos() {
		super();
		crearJugadores();
		crearDiana();
		
		dardo = new EntidadDardo(ventana.getWidth()/2, ventana.getHeight()-40, this);
		dardo.animarX();
		teclado.crearListenerTeclado(KeyEvent.VK_SPACE, "disparar");
	}
	
	private void crearJugadores() {
		jugadores = new ArrayList<Jugador>();
		for(int i=0; i<nJugadores; i++) {
			Jugador jg = new Jugador("Jugador "+(i+1));
			jugadores.add(jg);
			new EntidadTexto(ventana.getWidth()-125, 50+(16*i), jg.getNombre(), this);
		}
	}
	
	private void crearDiana() {
		diana = new ArrayList<EntidadCirculo>();
		EntidadCirculo d;
		int radio = 50;
		d = new EntidadCirculo(ventana.getWidth()/2-radio, 120-radio, radio, Color.BLACK, this);
		diana.add(d);
		
		radio = 40;
		d = new EntidadCirculo(ventana.getWidth()/2-radio, 120-radio, radio, Color.WHITE, this);
		diana.add(d);
		
		radio = 30;
		d = new EntidadCirculo(ventana.getWidth()/2-radio, 120-radio, radio, Color.YELLOW, this);
		diana.add(d);
		
		radio = 10;
		d = new EntidadCirculo(ventana.getWidth()/2-radio, 120-radio, radio, Color.RED, this);
		diana.add(d);
	}
	
	@Override
	public void ejecutarAccion(String accion) {
		super.ejecutarAccion(accion);
		if(accion.equals("disparar")) {
			dardo.disparar();
		}
	}
	
	public List<EntidadCirculo> getDiana() {
		return diana;
	}
	
}
