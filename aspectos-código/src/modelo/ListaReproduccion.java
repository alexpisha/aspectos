package modelo;

import java.util.ArrayList;

public class ListaReproduccion {
	private int id;
	private int idUsuario;
	private ArrayList<Cancion> listaCanciones;
	
	public ListaReproduccion() {
		
	}
	
	public ArrayList<Cancion> getLista() {
		return listaCanciones;
	}
	
	public int getId() {
		return id;
	}
	
	public int getIdUsuario() {
		return idUsuario;
	}
}
