package juegos;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import entidades.EntidadCirculo;
import entidades.EntidadDardo;
import entidades.EntidadTexto;
import principal.Jugador;

public class JuegoDardos extends Juego {
	private static final int LIMITE_PUNTOS = 900;
	
	private int nJugadores;
	private List<Jugador> jugadores;
	private List<EntidadTexto> textoJugadores;
	private List<EntidadCirculo> diana;
	private EntidadDardo[] dardo;
	private EntidadDardo dardoActivo;
	private EntidadTexto textoPuntos;
	private boolean terminado;
	
	public JuegoDardos() {
		super();
		nJugadores = Integer.parseInt(JOptionPane.showInputDialog("Introduce el Nº de jugadores"));
		crearJugadores();
		crearDiana();
		terminado = false;
		teclado.crearListenerTeclado(KeyEvent.VK_SPACE, "disparar");
		textoPuntos = new EntidadTexto(50, ventana.getHeight()-100, "", this);
		
		while(true) {
			for(int j=0; j<nJugadores; j++) {	
				textoJugadores.get(j).setColor(Color.RED);
				dardo = new EntidadDardo[3];
				for(int d=3; d>0; d--) {
					dardo[d-1] = new EntidadDardo(ventana.getWidth()/2, ventana.getHeight()-40, this);
					dardoActivo = dardo[d-1];
					dardo[d-1].animarX();
					while(!dardo[d-1].isDisparado()) {
						try {
							Thread.sleep(100);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					textoPuntos.setTexto(dardo[d-1].getPuntos()+" puntos");
				}
				int suma = 0;
				for(int i=0; i<3; i++) {
					entidades.remove(dardo[i]);
					suma += dardo[i].getPuntos();
				}
				jugadores.get(j).setPuntos(jugadores.get(j).getPuntos()+suma);
				textoJugadores.get(j).setColor(Color.WHITE);
				textoJugadores.get(j).setTexto(jugadores.get(j).getNombre()+" ("+jugadores.get(j).getPuntos()+")");
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if(jugadores.get(j).getPuntos() >= LIMITE_PUNTOS) {
					terminado = true;
					new EntidadTexto(ventana.getWidth()/2-200, ventana.getHeight()/2, jugadores.get(j).getNombre()+" ha ganado!", this);
					break;
				}
			}
			if(terminado) {
				break;
			}
		}		
	}
	
	private void crearJugadores() {
		textoJugadores = new ArrayList<EntidadTexto>();
		jugadores = new ArrayList<Jugador>();
		for(int i=0; i<nJugadores; i++) {
			String nombre = JOptionPane.showInputDialog("Introduce el nombre del jugador "+(i+1));
			Jugador jg = new Jugador(nombre);
			jugadores.add(jg);
			EntidadTexto txt = new EntidadTexto(ventana.getWidth()-125, 50+(16*i), jg.getNombre(), this);
			textoJugadores.add(txt);
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
			dardoActivo.disparar();
		}
	}
	
	public List<EntidadCirculo> getDiana() {
		return diana;
	}
	
}
