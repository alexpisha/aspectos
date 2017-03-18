package modelo;

import java.util.ArrayList;

public class GestorListasReproduccion {
	private static GestorListasReproduccion miGestorListasReproduccion;
	private static String nombreLista;
	private static ListaReproduccion actual;
	

	private GestorListasReproduccion() {
	}

	public static GestorListasReproduccion getGestorListasReproduccion() {
		if (miGestorListasReproduccion == null) {
			return new GestorListasReproduccion();
		} else {
			return miGestorListasReproduccion;
		}
	}	
	public ListaReproduccion getListaActual() {
		return actual;
	}

	public static void setActual(ListaReproduccion pactual) {
		actual=pactual;
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
		return (SGBD.getSGBD().getListasReprod(pUsuario));
	}
	
	public int eliminarListaRep(String pNombreLista){
		return SGBD.getSGBD().eliminarListaRep(pNombreLista);
	}
	
	
	public ArrayList<Cancion> getCancionesLista(String pNombreLista) {
		ArrayList<Cancion> canciones = new ArrayList<Cancion>();
		String listaids = SGBD.getSGBD().getCancionesLista(pNombreLista);
		if(listaids!=null){
		String[] lista = listaids.split(",");
		for(int i =0; i< lista.length; i++){
			Cancion c = SGBD.getSGBD().obtenerCancion(lista[i]);
			canciones.add(c);
		}
		}
		return 	canciones;
	}
	
	public void crearListaCanciones(ArrayList<Cancion> lista, String usuario, String nombreLista){
		String listaIds="";
		for (int i = 0; i < lista.size(); i++) {
			if(i==lista.size()-1){
			listaIds=listaIds+lista.get(i).getId();
			}else{
			listaIds=listaIds+lista.get(i).getId()+",";
			}
		}
		SGBD.getSGBD().insertarCancionesLista(listaIds, nombreLista, usuario);
		
		
	}
	
}
