package juegos;

import java.util.List;

import carta.Carta;
import carta.Numero;
import carta.Palo;

public class JuegoBlackJack extends Juego {
	private List<Carta> mazo;

	public JuegoBlackJack() {
		super();
		crearMazo();
		barajar();
	}
	
	private void crearMazo() {
		for(int i=0; i<Palo.PALOS.length; i++) {
			for(int j=0; j<Numero.NUMEROS.length; j++) {
				Carta c = new Carta(i, j);
				mazo.add(c);
			}
		}
	}
	
	private void barajar() {
		for(int i=0; i<1000; i++) {
			int rnd1 = (int) (Math.random()*mazo.size());
			int rnd2 = (int) (Math.random()*mazo.size());
			Carta tmp = mazo.get(rnd2);
			mazo.set(rnd2, mazo.get(rnd1));
			mazo.set(rnd1, tmp);
		}
	}
}
