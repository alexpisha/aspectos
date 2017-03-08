package modelo;

import java.util.ArrayList;

public class GestorListasReproduccion {
	private static GestorListasReproduccion miGestorListasReproduccion;
	private static String nombreLista;
	

	private GestorListasReproduccion() {
	}

	public static GestorListasReproduccion getGestorListasReproduccion() {
		if (miGestorListasReproduccion == null) {
			return new GestorListasReproduccion();
		} else {
			return miGestorListasReproduccion;
		}
	}	
	public String getNombreListaSeleccionada() {
		return nombreLista;
	}

	public static void setNombreListaSeleccionada(String nombreListaSeleccionada) {
		nombreLista=nombreListaSeleccionada;
	}
	
	public boolean existeLista(String pNombreUsuario, String pNombreLista){
		return (SGBD.getSGBD().existeLista(pNombreUsuario, pNombreLista));
	}
	public int insertarListaRepr(String pNombreLista, String pIdUsuario) {
	 return (SGBD.getSGBD().insertarListaRepr(pNombreLista, pIdUsuario));
	}
	public ArrayList<ListaReproduccion> getListasReprod(String pUsuario) {
		return (SGBD.getSGBD().getListasReprod(pUsuario));}
}
