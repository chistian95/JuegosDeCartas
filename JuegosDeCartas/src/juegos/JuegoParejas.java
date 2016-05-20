package juegos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Timer;

import entidades.EntidadBoton;
import entidades.EntidadCarta;
import menu.MenuPrincipal;
import pantalla.Ventana;

public class JuegoParejas extends Juego {
	private List<EntidadCarta> tablero;
	private EntidadCarta levantada;
	private EntidadCarta levantar;
	private boolean esperando;
	
	public JuegoParejas(Ventana ventana) {
		super(ventana);	
		levantada = null;
		esperando = false;
	}
	
	public void run() {
		crearTablero();
		barajarTablero();
		crearBotones();	
	}
	
	private void levantarCarta(int x, int y) {
		if(esperando) {
			return;
		}
		levantar = null;
		for(EntidadCarta c : tablero) {
			if(c.getX() == x && c.getY() == y) {
				if(c.isLevantada()) {
					return;
				}
				levantar = c;
				break;
			}
		}
		if(levantada == null) {
			levantada = levantar;
			levantada.setLevantada(true);
		} else {
			levantar.setLevantada(true);
			if(levantada.getPalo() == levantar.getPalo() && levantada.getNumero() == levantar.getNumero()) {				
				levantada = null;
			} else {
				esperando = true;
				ActionListener listener = new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						esperando = false;
						levantar.setLevantada(false);
						levantada.setLevantada(false);
						levantada = null;
					}
				};
				Timer t = new Timer(1500, listener);
				t.setRepeats(false);
				t.start();				
			}
		}
	}
	
	private void crearTablero() {
		tablero = new ArrayList<EntidadCarta>();
		for(int p=0; p<2; p++) {
			for(int n=0; n<7; n++) {
				int x = n*40 + ventana.getWidth()/2 - (40*7)/2;
				int y = p*60 + ventana.getHeight()/2 - (60*4)/2;
				int y2 = (p+2)*60 + ventana.getHeight()/2 - (60*4)/2;
				EntidadCarta c1 = new EntidadCarta(x, y, p+1, n, false, this);
				EntidadCarta c2 = new EntidadCarta(x, y2, p+1, n, false, this);
				tablero.add(c1);
				tablero.add(c2);
			}
		}
	}
	
	private void barajarTablero() {
		for(int i=0; i<50; i++) {
			int rnd1 = (int) (Math.random()*tablero.size());
			int rnd2 = (int) (Math.random()*tablero.size());
			EntidadCarta c1 = tablero.get(rnd1);
			EntidadCarta c2 = tablero.get(rnd2);
			if(!c1.isAnimando() && !c2.isAnimando()) {
				int x = c1.getX();
				int y = c1.getY();
				c1.animacion(x, y, c2.getX(), c2.getY());
				c2.animacion(c2.getX(), c2.getY(), x, y);
			}
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void crearBotones() {
		botones = new ArrayList<EntidadBoton>();		
		EntidadBoton boton1 = new EntidadBoton(0, ventana.getHeight()-30, 65, 30, "Salir", "salir", this);		
		botones.add(boton1);
		
		boolean animando = true;
		while(animando) {
			animando = false;
			for(EntidadCarta c : tablero) {
				if(c.isAnimando()) {
					animando = true;
					break;
				}
			}
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		for(EntidadCarta c : tablero) {
			c.setClickable(true);
		}
	}
	
	@Override
	public void ejecutarAccion(String accion) {
		super.ejecutarAccion(accion);
		if(accion.equals("salir")) {
			new Thread(new MenuPrincipal(ventana)).start();
		} else {
			String[] accionArray = accion.split(",");
			int x = Integer.parseInt(accionArray[0]);
			int y = Integer.parseInt(accionArray[1]);
			levantarCarta(x, y);
		}
	}
}
