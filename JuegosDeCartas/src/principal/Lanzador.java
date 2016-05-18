package principal;

import menu.MenuPrincipal;

public class Lanzador {
	public static void main(String[] args) {
		new Thread(new MenuPrincipal()).start();
	}
}
 