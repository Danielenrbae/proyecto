package modelos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import utils.CConexion;

public class Producto {

	private int id_producto;
	private String nombre;
	private String descripcion;
	private float precio;
	private byte[] foto;
	private int id_empresa;
	private int id_categoria;
	
	CConexion con;
	PreparedStatement ps;
	ResultSet rs;

	public Producto() {
		con = new CConexion();
	}

	/**
	 * @param id_producto
	 * @param nombre
	 * @param descripcion
	 * @param precio
	 * @param foto
	 * @param id_empresa
	 * @param id_categoria
	 */
	public Producto(String cnombre, String cdescripcion, float cprecio, byte[] cfoto, int cid_empresa,
			int cid_categoria) {

	
		nombre = cnombre;
		descripcion = cdescripcion;
		precio = cprecio;
		foto = cfoto;
		id_empresa = cid_empresa;
		id_categoria = cid_categoria;
		con = new CConexion();

	}
	
	
	/**
	 * Método para insertar un producto con foto en la base de datos
	 * */

	public boolean insertar() {
		boolean resultado;

		String sql;
		resultado = false;
		sql = "insert into proyecto.producto(nombre, descripcion, precio , foto , id_empresa , id_categoria) VALUES(?,?,?,?,?,?)";

		con.iniciarConexion("ns3034756.ip-91-121-81.eu:5432/a20-denrbae", "a20-denrbae", "a20-denrbae");

		
		try {

			ps = con.getConnection().prepareStatement(sql);

			ps.setString(1, nombre);
			ps.setString(2, descripcion);
			ps.setFloat(3, precio);
			ps.setBinaryStream(4, null);
			ps.setInt(5, id_empresa);
			ps.setInt(6, id_categoria);
			
			if (ps.execute()) {
				resultado = true;
			}
			
			ps.close();
			con.cerrarConexion();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return resultado;

	}
	
	/**
	 * Este método realiza una consulta en la base de datos en la tabla relacionada
	 * con los productos - solo devuelve un registro
	 * 
	 * @param columna Valor de la columna donde quiere realizar la consulta
	 * @param valor   Valor de la condicion que se utiliza en la consulta
	 *
	 *
	 * @return Devuelve true o false dependiendo del resultado de la consulta
	 */

	public boolean leer(String columna, String valor , boolean unico) {
		boolean resultado;
		String sql;
		resultado = false;

		con.iniciarConexion("ns3034756.ip-91-121-81.eu:5432/a20-denrbae", "a20-denrbae", "a20-denrbae");

		if (columna.equals("") || valor.equals("") || columna.equals(null) || valor.equals(null)) {
			sql = "SELECT * FROM proyecto.empresa";
		} else {
			sql = "SELECT * FROM proyecto.empresa where " + columna + "= '" + valor + "';";
		}
		

		try {

			ps = con.getConnection().prepareStatement(sql);

		
			rs = ps.executeQuery();

			if (rs.next()) {
				
				id_producto = rs.getInt("id_producto");
				nombre = rs.getString("nombre");
				descripcion= rs.getString("descripcion");
				precio = rs.getFloat("precio");
				foto = rs.getBytes("foto");
				id_empresa = rs.getInt("id_empresa");
				id_categoria = rs.getInt("id_categoria");
				
				
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

	public int getId_producto() {
		return id_producto;
	}

	public void setId_producto(int id_producto) {
		this.id_producto = id_producto;
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

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

	public int getId_empresa() {
		return id_empresa;
	}

	public void setId_empresa(int id_empresa) {
		this.id_empresa = id_empresa;
	}

	public int getId_categoria() {
		return id_categoria;
	}

	public void setId_categoria(int id_categoria) {
		this.id_categoria = id_categoria;
	}
	
	
	

}
