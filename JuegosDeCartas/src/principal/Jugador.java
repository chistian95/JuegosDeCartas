package principal;

import java.util.ArrayList;
import java.util.List;

import carta.Carta;

public class Jugador {
	private List<Carta> mano;
	private boolean ia;
	
	public Jugador(boolean ia) {
		this.ia = ia;
		mano = new ArrayList<Carta>();
	}
	
	public List<Carta> getMano() {
		return mano;
	}
	
	public boolean isIa() {
		return ia;
	}
}
