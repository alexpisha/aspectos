package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

//import packCodigo.Partida;

public class SGBD {
	Connection connection;
	private Statement sentencia;
	private ResultSet rs;
	private static SGBD miSGBD = null;
	private String usuarioBD="Xamartinez283";
	private String pswBD ="LoohtS5Fcc";

	private SGBD() {
		try {
			Class.forName("org.gjt.mm.mysql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		// Open connection
		try {
			connection = DriverManager.getConnection("jdbc:mysql://galan.ehu.eus/Xamartinez283_Reproductor",
					usuarioBD, pswBD);
			connection.setAutoCommit(true);
			sentencia = connection.createStatement();
			System.out.println("Conexion establecida");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * devuelve el atributo miSGBD
	 * 
	 * @return
	 */
	public static SGBD getSGBD() {
		if (miSGBD == null) {
			miSGBD = new SGBD();
		}
		return miSGBD;
	}

	/**
	 * metodo para realizar una insercion o actualizacion en la base de datos
	 * 
	 * @param strSQL
	 * @return
	 * @throws SQLException
	 */
	public int actualizar(String strSQL) throws SQLException {
		return sentencia.executeUpdate(strSQL);
	}

	/**
	 * M�todo para ejecutar un seleccion en la base de datos
	 * 
	 * @param strSQL
	 * @return
	 * @throws SQLException
	 */
	public ResultSet ejecutarQuery(String strSQL) throws SQLException {
		return this.sentencia.executeQuery(strSQL);
	}

	/**
	 * comprueba si el usuario existe o no en la base de datos
	 * 
	 * @param pNombre
	 * @return
	 */
	
	public String obtenerCorreo(String pUser) {
		String resul = "";
		try{
			rs = sentencia.executeQuery("Select email from Usuario where nombre='" + pUser + "';");
			rs.next();
			resul = rs.getString("email");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resul;
	}
	
	public String obtenerId(String pUser) {
		String resul = "";
		try{
			rs = sentencia.executeQuery("Select id from Usuario where nombre='" + pUser + "';");
			rs.next();
			resul = rs.getString("id");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resul;
	}
	
	public boolean existeUsuario(String pNombre) {
		int contador = 0;
		try {
			rs = sentencia.executeQuery("Select count(*) from Usuario where nombre='" + pNombre + "';");
			rs.next();
			contador = rs.getInt("COUNT(*)");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// Si el contador es igual a 0 no existe el jugador
		return (contador > 0);
	}
	/**
	 * anade un usuario a la base de datos
	 * 
	 * @param pUsuario
	 * @param pContrasena
	 * @return
	 */
	public int ingresarJugador(String pNombre, String pContrasena, String pEmail) {
		int estado = 0;
		try {
			PreparedStatement statement = connection.prepareStatement(
					"insert into Usuario(nombre,contrasena,email) values('" + pNombre + "','" + pContrasena + "','" +pEmail+"');");
			statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			estado = -1;
		}
		// Devuelvo un numero para saber si ha sido correcto o no el ingreso
		return estado;
	}

	/**
	 * Comprueba que los datos de acceso son validos
	 * 
	 * @param pUsuario
	 * @param pContrasena
	 * @return
	 */
	public boolean validarUsuario(String pNombre, String pContrasena) {
		boolean resultado = false;
		try {
			rs = sentencia.executeQuery("Select contrasena from Usuario where nombre='" + pNombre + "';");
			rs.next();
			resultado = rs.getString(1).equals(pContrasena);
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return resultado;
	}

	
	
	
	
	public boolean existenListasReprod(String pUsuario) {
		int contador = 0;
		try {
			rs = sentencia.executeQuery("Select count(*) from ListaReproduccion where idUsuario='" + pUsuario + "';");
			rs.next();
			contador = rs.getInt("COUNT(*)");
		} catch (SQLException e) {

			e.printStackTrace();
		}
		// Si el contador es igual a 0 el jugador no tiene partidas guardadas
		return (contador > 0);
	}
	public boolean existeLista(String pUsuario, String pNombreLista) {
		int id = 0;
		
		try {
			String sentenciaSQL = "SELECT id FROM Usuario WHERE nombre='" + pUsuario + "';";
			rs = sentencia.executeQuery(sentenciaSQL);
			while (rs.next()) {
				id=this.rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();

		}
		int contador = 0;
		try {
			rs = sentencia.executeQuery("Select count(*) from ListaReproduccion where idUsuario='" + id +"' AND tituloLista='"+pNombreLista +"';");
			rs.next();
			contador = rs.getInt("COUNT(*)");
		} catch (SQLException e) {

			e.printStackTrace();
		}
		// Si el contador es igual a 0 el jugador no tiene partidas guardadas
		return (contador > 0);
	}
	
	public ArrayList<ListaReproduccion> getListasReprod(String pUsuario, int id) { //TODO
		ArrayList<ListaReproduccion> aDevolver = new ArrayList<ListaReproduccion>();
		try {
			String sentenciaSQL = "SELECT * FROM ListaReproduccion WHERE idUsuario='" + id + "';";
			rs = sentencia.executeQuery(sentenciaSQL);
		
			while (rs.next()) {
				int idLista=rs.getInt("id"); 
				int idUser= rs.getInt("idUsuario");
				String listaIds= rs.getString("listaIdCanciones");
				String tituloLista= rs.getString("tituloLista");
				System.out.println(sentenciaSQL);
				System.out.println(idLista+" "+idUser+" "+listaIds+" "+tituloLista);
				//aDevolver.add(new ListaReproduccion(idLista, idUser , listaIds, tituloLista));
				ListaReproduccion r =new ListaReproduccion(idLista, idUser, listaIds, tituloLista);
				aDevolver.add(r);
			}
		} catch (SQLException e) {
			e.printStackTrace();	
		}
		ArrayList<Cancion> listaCanciones=new ArrayList<Cancion>();
		for(int i=0; i<aDevolver.size(); i++){
		if(aDevolver.get(i).getListaIdCanciones() !=null){
			String[] canciones =aDevolver.get(i).getListaIdCanciones().split(",");
			for(int j=0; j<canciones.length; j++){
				Cancion c = SGBD.getSGBD().obtCancion(canciones[j].toString());
				listaCanciones.add(c);
				
			}
			}
		aDevolver.get(i).setListaCanciones(listaCanciones);
		}
		System.out.println("size"+aDevolver.size());
			
		return aDevolver;
	}
	public Cancion obtenerCancion(String id) {
	Cancion cancion = null;
		try {
			rs = sentencia.executeQuery("Select * from Cancion where id='" + id + "';");
			while (rs.next()) {
				  cancion = new Cancion(this.rs.getInt(1), this.rs.getString(3), this.rs.getString(2),this.rs.getString(4),this.rs.getString(5));
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return cancion;
	}
	/**
	 * anade un usuario a la base de datos
	 * 
	 * @param pUsuario
	 * @param pContrasena
	 * @return
	 */
	public int insertarListaRepr(String pNombreLista, String pUsuario) {
		int id = Integer.parseInt(obtenerId(pUsuario));
		
		try {
			String sql ="insert into ListaReproduccion (tituloLista, idUsuario) values('" + pNombreLista + "','" + id +"');";
			sentencia.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		
		}
		// Devuelvo un numero para saber si ha sido correcto o no el ingreso
		return id;
	}
	
	public void modificarListaRepr(String pNombreLista, int pIdUsuario, String pListaCanciones, int idLista) {
		try {
			String sql= "Update ListaReproduccion set listaIdCanciones='" + pListaCanciones + "' where idUsuario='" + pIdUsuario +" AND tituloLista = "+pNombreLista+ "';";
			sentencia.executeUpdate(sql);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public String getCancionesListaId(String nombreLista, int id) {
		String canciones ="";
			try {
				rs = sentencia.executeQuery("Select listaIdCanciones from ListaReproduccion where tituloLista='" + nombreLista + "'AND idUsuario='"+id+"';");
				while (rs.next()) {
					 canciones =this.rs.getString(1);
				}
				
			} catch (SQLException e) {

				e.printStackTrace();
			}
			return canciones;
		}

	public String getCancionesLista(String nombreLista) {
		String canciones ="";
			try {
				rs = sentencia.executeQuery("Select listaIdCanciones from ListaReproduccion where tituloLista='" + nombreLista + "';");
				while (rs.next()) {
					 canciones =this.rs.getString(1);
				}
				
			} catch (SQLException e) {

				e.printStackTrace();
			}
			return canciones;
		}
	//obtener cancion por titulo
	public Cancion obtCancion(String id){
		Cancion cancion = new Cancion(0,"","","","");
			try {
				String sql = "Select * from Cancion where id='" + id + "';";
				rs = sentencia.executeQuery(sql);
				while (rs.next()) {
					 cancion = new Cancion(this.rs.getInt(1), this.rs.getString(2), this.rs.getString(3),this.rs.getString(4),this.rs.getString(5));
				}
				
			} catch (SQLException e) {

				e.printStackTrace();
			}
			return cancion;
	}
	
	public int eliminarListaRep(String pNombreLista) {
		int r=-1;
		try {
			String sql ="Delete from ListaReproduccion WHERE tituloLista ='" + pNombreLista + "';";
			r = sentencia.executeUpdate(sql);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return r;
	}
	public void insertarCancionesLista(String listaIdCanciones, String nombreLista, String usuario){
		int id = Integer.parseInt(obtenerId(usuario));
		
		try {
			String sql ="Update ListaReproduccion set listaIdCanciones ='" + listaIdCanciones +"' WHERE tituloLista='"+nombreLista+"' AND idUsuario='"+id+"';";
			
			sentencia.executeUpdate(sql);
			
		} catch (Exception e) {
			e.printStackTrace();
		
		}

	}
	
	
}
