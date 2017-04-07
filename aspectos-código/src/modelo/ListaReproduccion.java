package modelo;

import java.util.ArrayList;

public class ListaReproduccion {
	private int id;
	private String nombreLista;
	private int idUsuario;
	private ArrayList<Cancion> listaCanciones = new ArrayList<Cancion>();
	private String listaIdCanciones="";
	
	public ListaReproduccion(int id, int idUsuario, String listaIdCanciones, String nombreLista) {
		this.id=id;
		this.idUsuario=idUsuario;
		this.nombreLista=nombreLista;
		this.listaIdCanciones=listaIdCanciones;
	}
	
	public void setListaCanciones(ArrayList<Cancion> listaIdCanciones){
		this.listaCanciones=listaIdCanciones;
	}
	public String getListaIdCanciones(){
		return this.listaIdCanciones;
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
