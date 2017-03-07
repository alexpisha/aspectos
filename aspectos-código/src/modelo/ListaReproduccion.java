package modelo;

import java.util.ArrayList;

public class ListaReproduccion {
	private int id;
	private String nombreLista;
	private int idUsuario;
	private ArrayList<Cancion> listaCanciones;
	
	public ListaReproduccion(int id, int idUsuario, String listaIdCanciones, String nombreLista) {
		this.id=id;
		this.idUsuario=idUsuario;
		this.nombreLista=nombreLista;
		if(listaIdCanciones!=null){
		String[] canciones =listaIdCanciones.split(",");
		for(int i=0; i<canciones.length; i++){
			Cancion c = SGBD.getSGBD().obtenerCancion(canciones[i].toString());
			this.listaCanciones.add(c);
			
		}
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
	public String getNombreLista() {
		return nombreLista;
	}

	public void setNombreLista(String nombreLista) {
		this.nombreLista = nombreLista;
	}
	
}
