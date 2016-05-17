package principal;

import java.util.ArrayList;
import java.util.List;

import cartas.Numero;
import entidades.EntidadCarta;

public class Jugador {
	private List<EntidadCarta> mano;
	
	public Jugador() {
		mano = new ArrayList<EntidadCarta>();
	}
	
	public List<EntidadCarta> getMano() {
		return mano;
	}
	
	public int getPuntos() {
		int puntos = 0;
		int ases = 0;
		for(EntidadCarta c : mano) {
			if(c.getNumero() == Numero.C_A) {
				ases++;
			}
			if(c.getNumero() >= Numero.C_J) {
				puntos += 10;
			} else {
				puntos += c.getNumero()+1;
			}			
		}
		for(int i=0; i<ases; i++) {
			if(puntos+10 <= 21) {
				puntos+=10;
			}
		}
		return puntos;
	}
}
