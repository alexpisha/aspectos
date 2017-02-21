package modelo;

public class Cancion {
	private int id;
	private String titulo;
	private String autor;
	private String nombreRuta;
	
	public Cancion(int pId, String pTitulo, String pAutor, String pNombreRuta) {
		id = pId;
		titulo = pTitulo;
		autor = pAutor;
		nombreRuta = pNombreRuta;
	}
	
	public String getRuta() {
		return nombreRuta;
	}
	
	public int getId() {
		return id;
	}
	
	public String getAutor() {
		return autor;
	}
	
	public String getTitulo() {
		return titulo;
	}

}
