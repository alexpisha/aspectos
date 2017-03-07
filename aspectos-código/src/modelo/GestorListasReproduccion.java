package modelo;

public class GestorListasReproduccion {
	private static GestorListasReproduccion miGestorListasReproduccion;
	private GestorListasReproduccion() {
	}

	public static GestorListasReproduccion getGestorListasReproduccion() {
		if (miGestorListasReproduccion == null) {
			return new GestorListasReproduccion();
		} else {
			return miGestorListasReproduccion;
		}
	}	
	
	
	public boolean existeLista(String pNombreUsuario, String pNombreLista){
		return (SGBD.getSGBD().existeLista(pNombreUsuario, pNombreLista));
	}
	public int insertarListaRepr(String pNombreLista, String pIdUsuario) {
	 return (SGBD.getSGBD().insertarListaRepr(pNombreLista, pIdUsuario));
	}
	
}
