package modelo;

public class Cancion {
	private int id;
	private String titulo;
	private String autor;
	private String nombreRuta;
	private String album;
	
	public Cancion(int pId, String pTitulo, String pAutor, String pNombreRuta,String album) {
		this.id = pId;
		this.titulo = pTitulo;
		this.autor = pAutor;
		this.nombreRuta = pNombreRuta;
		this.album=album;
	}
	
	public void setAlbum(String album) {
		this.album = album;
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
	
	public String getAlbum() {
		return album;
	}
}
