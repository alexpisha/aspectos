package modelo;

import java.util.ArrayList;

public class ListaReproduccion {
	private int id;
	private int idUsuario;
	private ArrayList<Cancion> listaCanciones;
	
	public ListaReproduccion(int id, int idUsuario, String listaIdCanciones) {
		this.id=id;
		this.idUsuario=idUsuario;
		String[] canciones =listaIdCanciones.split(",");
		for(int i=0; i<canciones.length; i++){
			Cancion c = SGBD.getSGBD().obtenerCancion(canciones[i].toString());
			this.listaCanciones.add(c);
			
		}
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
