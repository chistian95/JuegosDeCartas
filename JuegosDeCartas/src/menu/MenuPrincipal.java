package menu;

import java.util.ArrayList;

import entidades.EntidadBoton;
import juegos.Juego;
import juegos.JuegoBlackJack;
import juegos.JuegoDardos;
import juegos.JuegoParejas;
import pantalla.Ventana;

public class MenuPrincipal extends Juego {
	public MenuPrincipal() {
		super();
		crearBotones();
	}
	
	public MenuPrincipal(Ventana ventana) {
		super(ventana);
		crearBotones();
	}
	
	public void run() {}
	
	private void crearBotones() {
		botones = new ArrayList<EntidadBoton>();
		
		EntidadBoton boton1 = new EntidadBoton(ventana.getWidth()/2-50, ventana.getHeight()/2-75, 115, 30, "BlackJack", "blackjack", this);
		EntidadBoton boton2 = new EntidadBoton(ventana.getWidth()/2-50, ventana.getHeight()/2-15, 115, 30, "Dardos", "dardos", this);
		EntidadBoton boton3 = new EntidadBoton(ventana.getWidth()/2-50, ventana.getHeight()/2+45, 115, 30, "Parejas", "parejas", this);
		
		botones.add(boton1);
		botones.add(boton2);
		botones.add(boton3);
	}
	
	@Override
	public void ejecutarAccion(String accion) {
		super.ejecutarAccion(accion);
		if(accion.equals("blackjack")) {
			new Thread(new JuegoBlackJack(ventana)).start();
		} else if(accion.equals("dardos")) {
			new Thread(new JuegoDardos(ventana)).start();
		} else if(accion.equals("parejas")) {
			new Thread(new JuegoParejas(ventana)).start();
		}
	}
}
