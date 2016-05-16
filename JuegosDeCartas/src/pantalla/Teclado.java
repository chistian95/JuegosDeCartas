package pantalla;

import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

import juegos.Juego;

public class Teclado {
	private Map<Integer, String> teclas;
	private Juego juego;
	
	public Teclado(Juego juego) {
		this.juego = juego;
		teclas = new HashMap<Integer, String>();
	}
	
	public void teclaPulsada(KeyEvent e) {
		for(Map.Entry<Integer, String> entrada : teclas.entrySet()) {
			Integer tecla = entrada.getKey();
			String accion = entrada.getValue();
			if(tecla == e.getKeyCode()) {
				juego.ejecutarAccion(accion);
			}
		}
	}
	
	public void crearListenerTeclado(int tecla, String accion) {
		teclas.put(tecla, accion);
	}
	
	public void limpiarListener() {
		teclas = new HashMap<Integer, String>();
	}

}
