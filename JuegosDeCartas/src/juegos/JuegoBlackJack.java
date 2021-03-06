package juegos;

import java.util.ArrayList;
import java.util.List;

import cartas.Numero;
import cartas.Palo;
import entidades.Entidad;
import entidades.EntidadBoton;
import entidades.EntidadCarta;
import entidades.EntidadTexto;
import menu.MenuPrincipal;
import pantalla.Ventana;
import principal.Jugador;

public class JuegoBlackJack extends Juego {
	private List<EntidadCarta> mazo;
	private Jugador jugador;
	private Jugador casa;
	private EntidadTexto puntosJugador;

	public JuegoBlackJack(Ventana ventana) {
		super(ventana);
		crearMazo();
		crearBotones();
		jugador = new Jugador();
		casa = new Jugador();
		
		puntosJugador = new EntidadTexto(ventana.getWidth()/2-150, ventana.getHeight()/2+64, jugador.calcPuntos()+"", this);
		entidades.add(puntosJugador);
	}
	
	public void run() {}
	
	private void cogerCarta() {
		int ultCarta = ultimaCarta();
		EntidadCarta carta = mazo.get(ultCarta);
		mazo.remove(ultCarta);
		jugador.getMano().add(carta);
		int x = ventana.getWidth()/2-128 + 34*jugador.getMano().size();
		int y = ventana.getHeight()/2 + 64;
		carta.animacion(carta.getX(), carta.getY(), x, y);
		carta.setLevantada(true);
		puntosJugador.setTexto(jugador.calcPuntos()+"");
		
		if(jugador.calcPuntos() >= 21) {
			terminar();	
		} else {
			turnoCasa();
		}
	}
	
	private boolean turnoCasa() {
		if(casa.calcPuntos() < 16 && casa.calcPuntos() < jugador.calcPuntos()) {
			int ultCarta = ultimaCarta();
			EntidadCarta carta = mazo.get(ultCarta);
			mazo.remove(ultCarta);
			casa.getMano().add(carta);
			int x = ventana.getWidth()/2-128 + 34*casa.getMano().size();
			int y = ventana.getHeight()/2-200 + 64;
			carta.animacion(carta.getX(), carta.getY(), x, y);
			return true;
		}
		return false;
	}
	
	private int ultimaCarta() {
		int ultCarta = 0;
		int ultCartaY = 720;
		int cont = 0;
		for(EntidadCarta c : mazo) {
			if(c.getY() < ultCartaY) {
				ultCartaY = c.getY();
				ultCarta = cont;
			}
			cont++;
		}
		return ultCarta;
	}
	
	private void terminar() {
		if(jugador.calcPuntos() <= 21) {
			while(turnoCasa()) {}
		}
		for(EntidadCarta c : casa.getMano()) {
			c.setLevantada(true);
		}
		new EntidadTexto(ventana.getWidth()/2-150, ventana.getHeight()/2-200+64, casa.calcPuntos()+"", this);
		
		String mensaje = "Has perdido!";
		if(jugador.calcPuntos() > 21) {
			mensaje = "Has perdido!";
		} else if(casa.calcPuntos() > 21) {
			mensaje = "Has ganado!";
		} else if(jugador.calcPuntos() > casa.calcPuntos()) {
			mensaje = "Has ganado!";
		}
		new EntidadTexto(ventana.getWidth()/2-50, ventana.getHeight()/2-150, mensaje, this);
		
		terminado = true;

		for(EntidadBoton boton : botones) {
			if(!boton.getAccion().equals("salir")) {
				boton.setActivado(false);
			}
		}
		EntidadBoton reiniciar = new EntidadBoton(ventana.getWidth()/2+200, ventana.getHeight()/2+60, 110, 30, "Reiniciar", "reiniciar", this);
		botones.add(reiniciar);
	}
	
	private void crearMazo() {
		mazo = new ArrayList<EntidadCarta>();
		for(int i=0; i<Palo.PALOS.length; i++) {
			for(int j=0; j<Numero.NUMEROS.length; j++) {			
				EntidadCarta carta = new EntidadCarta(0, 0, i, j, false, this);
				mazo.add(carta);
			}
		}
		int cont = 0;
		for(EntidadCarta carta : mazo) {
			carta.setX(ventana.getWidth()/2+48);
			carta.setY(ventana.getHeight()/2-(2*cont));
			cont++;
		}
		barajar();		
	}
	
	private void barajar() {
		for(int i=0; i<1000; i++) {
			int rnd1 = (int) (Math.random()*mazo.size());
			int rnd2 = (int) (Math.random()*mazo.size());	
			int tmp = mazo.get(rnd2).getY();
			mazo.get(rnd2).setY(mazo.get(rnd1).getY());
			mazo.get(rnd1).setY(tmp);
		}
		int tam = entidades.size()-1;
		int puntero;
		for(int m = tam; m >= 0; m--) {
			for(int n = 0; n < tam; n++) {
				puntero = n + 1;
				if(entidades.get(n).getY() < entidades.get(puntero).getY()) {
					Entidad tmp = entidades.get(n);
					entidades.set(n, entidades.get(puntero));
					entidades.set(puntero, tmp);
				}
			}
		}
	}
	
	private void reiniciar() {
		entidades = new ArrayList<Entidad>();
		terminado = false;
		crearMazo();
		crearBotones();
		jugador = new Jugador();
		casa = new Jugador();
		
		puntosJugador = new EntidadTexto(ventana.getWidth()/2-150, ventana.getHeight()/2+64, jugador.calcPuntos()+"", this);
		entidades.add(puntosJugador);
	}
	
	private void crearBotones() {
		botones = new ArrayList<EntidadBoton>();
		
		EntidadBoton boton1 = new EntidadBoton(ventana.getWidth()/2+200, ventana.getHeight()/2-60, 110, 30, "Repartir", "repartir", this);
		EntidadBoton boton2 = new EntidadBoton(ventana.getWidth()/2+200, ventana.getHeight()/2, 110, 30, "Plantarse", "plantarse", this);
		EntidadBoton boton3 = new EntidadBoton(0, ventana.getHeight()-30, 65, 30, "Salir", "salir", this);
		
		botones.add(boton1);
		botones.add(boton2);
		botones.add(boton3);
	}
	
	@Override
	public void ejecutarAccion(String accion) {
		super.ejecutarAccion(accion);
		if(accion.equals("repartir")) {
			cogerCarta();
		} else if(accion.equals("plantarse")) {
			terminar();
		} else if(accion.equals("reiniciar")) {
			reiniciar();
		} else if(accion.equals("salir")) {
			new Thread(new MenuPrincipal(ventana)).start();
		}
	}
}
