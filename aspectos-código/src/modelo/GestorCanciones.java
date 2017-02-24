package modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.sql.Statement;


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
		
	public ArrayList<Cancion> getTodasLasCanciones(){
		ArrayList<Cancion> todas = new ArrayList<Cancion>();
		return todas;
	}
		

	

	
	
	
}
