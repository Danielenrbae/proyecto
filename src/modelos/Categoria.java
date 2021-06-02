package modelos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import utils.CConexion;

public class Categoria {

	private int id_categoria;
	private String nombre;
	private String descripcion;

	CConexion con;
	PreparedStatement ps;
	ResultSet rs;

	public Categoria() {
		con = new CConexion();
	}

	/**
	 * @param id_categoria
	 * @param nombre
	 * @param descripcion
	 */
	public Categoria(int cid_categoria, String cnombre, String cdescripcion) {
		id_categoria = cid_categoria;
		nombre = cnombre;
		descripcion = cdescripcion;
		con = new CConexion();
	}

	/**
	 * Este método realiza una consulta en la base de datos en la tabla relacionada
	 * con las categorias - solo devuelve un registro
	 * 
	 * @param columna Valor de la columna donde quiere realizar la consulta
	 * @param valor   Valor de la condicion que se utiliza en la consulta
	 * @param unico   Booleano para especificar que solo se va a realizar una
	 *                consulta
	 *
	 * @return Devuelve true o false dependiendo del resultado de la consulta
	 */

	public boolean leer(String columna, String valor, boolean unico) {
		boolean resultado;
		String sql;
		resultado = false;

		con.iniciarConexion("ns3034756.ip-91-121-81.eu:5432/a20-denrbae", "a20-denrbae", "a20-denrbae");

		if (columna.isEmpty() || valor.isEmpty() ) {
			sql = "SELECT * FROM proyecto.categoria";
		} else {
			sql = "SELECT * FROM proyecto.categoria where " + columna + "= '" + valor + "';";
		}

		try {

			ps = con.getConnection().prepareStatement(sql);

			rs = ps.executeQuery();

			if (rs.next()) {
				
				id_categoria = rs.getInt("id_categoria");
				nombre = rs.getString("nombre");
				descripcion = rs.getString("descripcion");

				resultado = true;

				if (unico) {
					rs.close();
					ps.close();
					con.cerrarConexion();
				}

			} else {
				rs.close();
				ps.close();
				con.cerrarConexion();
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return resultado;
	}

	public boolean leersiguiente() {
		boolean resultado = false;

		try {
			if (rs.next()) {
				id_categoria = rs.getInt("id_categoria");
				nombre = rs.getString("nombre");
				descripcion = rs.getString("descripcion");
				resultado = true;
			} else {
				rs.close();
				ps.close();
				con.cerrarConexion();
			}
		} catch (SQLException e) {
			resultado = false;

		}
		return resultado;
	}

	public int getId_categoria() {
		return id_categoria;
	}

	public void setId_categoria(int id_categoria) {
		this.id_categoria = id_categoria;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	
	
}
