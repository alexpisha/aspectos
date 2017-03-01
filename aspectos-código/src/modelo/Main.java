package modelo;

import java.io.IOException;

import interfaces.VPrincipal;

public class Main {

	public static void main(String[] args) {
		try {
			GestorCanciones.getGestorCanciones().cargarCanciones();
		} catch (IOException e) {
			e.printStackTrace();
		}
		VPrincipal v = new VPrincipal();
		v.setVisible(true);
	}

}