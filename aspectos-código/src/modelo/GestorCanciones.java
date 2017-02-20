package modelo;

import java.util.ArrayList;
import java.util.Calendar;

public class GestorCanciones {
	private static GestorCanciones miGestorCanciones;
	private static ArrayList<Cancion> listaCanciones;

	private GestorCanciones() {
	}

	public static GestorCanciones getGestorCanciones() {

		if (miGestorCanciones == null) {
			return new GestorCanciones();
		} else {
			return miGestorCanciones;
		}
	}

	

	

	

	

	
	
	
}
