package modelos;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
	private File file;
	private FileInputStream input;
	private int numeroTotal;
	private String categoria;

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
	public Producto(String cnombre, String cdescripcion, float cprecio, int cid_empresa, int cid_categoria, File foto) {

		nombre = cnombre;
		descripcion = cdescripcion;
		precio = cprecio;
		// foto = cfoto;
		id_empresa = cid_empresa;
		id_categoria = cid_categoria;
		file = foto;

		con = new CConexion();

	}

	/**
	 * Método para insertar un producto con foto en la base de datos
	 */

	public boolean insertar() {
		boolean resultado;

		String sql;
		resultado = false;
		sql = "insert into proyecto.producto(nombre, descripcion, precio,foto, id_empresa , id_categoria) VALUES(?,?,?,?,?,?)";

		con.iniciarConexion("ns3034756.ip-91-121-81.eu:5432/a20-denrbae", "a20-denrbae", "a20-denrbae");

		try {

			input = new FileInputStream(file);

			ps = con.getConnection().prepareStatement(sql);

			ps.setString(1, nombre);
			ps.setString(2, descripcion);
			ps.setFloat(3, precio);
			ps.setBinaryStream(4, input, (int) file.length());
			ps.setInt(5, id_empresa);
			ps.setInt(6, id_categoria);

			if (ps.executeUpdate() == 1) {

				resultado = true;

			}

			ps.close();
			con.cerrarConexion();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
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

	public boolean leer(String columna, String valor, boolean unico, boolean all, boolean count) {
		boolean resultado;
		String sql;
		resultado = false;

		con.iniciarConexion("ns3034756.ip-91-121-81.eu:5432/a20-denrbae", "a20-denrbae", "a20-denrbae");

		if (!columna.equals("") || !valor.equals("") || !columna.equals(null) || !valor.equals(null)) {

			sql = "SELECT * FROM proyecto.producto where " + columna + "= '" + valor+"'";
		} else {
			sql = "SELECT * FROM proyecto.producto";
		}

		if (count) {
			sql = "SELECT count(*) FROM proyecto.producto where " + columna + " = ?";
			unico = true;
		}

		if (all) {
			sql = "SELECT * FROM proyecto.producto";
		}

		try {

			ps = con.getConnection().prepareStatement(sql);

			if (count) {
				ps.setInt(1, Integer.parseInt(valor));
			}

			if (all) {
				ps.setInt(1, Integer.parseInt(valor));
			}

			rs = ps.executeQuery();

			if (rs.next()) {

				if (count) {
					numeroTotal = rs.getInt(1);
				} else {
					id_producto = rs.getInt("id_producto");
					nombre = rs.getString("nombre");
					descripcion = rs.getString("descripcion");
					precio = rs.getFloat("precio");
					foto = rs.getBytes("foto");
					id_empresa = rs.getInt("id_empresa");
					id_categoria = rs.getInt("id_categoria");
				}

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
				id_producto = rs.getInt("id_producto");
				nombre = rs.getString("nombre");
				descripcion = rs.getString("descripcion");
				precio = rs.getFloat("precio");
				foto = rs.getBytes("foto");
				id_empresa = rs.getInt("id_empresa");
				id_categoria = rs.getInt("id_categoria");
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
	
	
	/**
	 * Método para insertar un producto con foto en la base de datos
	 */

	public boolean delete(String columna , String valor , String columna2, String valor2) {
		boolean resultado;

		String sql;
		resultado = false;
		
		
		sql = "delete from proyecto.producto where "+columna+ " = ?";
		
		if (!columna2.equals("") || !columna2.equals(null) || !valor2.equals("") || !valor2.equals(null)) {
			sql = "delete from proyecto.producto where "+columna+ "= ? and "+columna2+" = ?";
				
		}
		

		con.iniciarConexion("ns3034756.ip-91-121-81.eu:5432/a20-denrbae", "a20-denrbae", "a20-denrbae");

		try {

			ps = con.getConnection().prepareStatement(sql);
			
			ps.setInt(1, Integer.parseInt(valor));
			
			if (!columna2.equals("") || !columna2.equals(null) || !valor2.equals("") || !valor2.equals(null)) {
				ps.setInt(2, Integer.parseInt(valor2));
			}


			if (ps.executeUpdate() == 1) {

				resultado = true;

			}

			ps.close();
			con.cerrarConexion();

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

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public int getNumeroTotal() {
		return numeroTotal;
	}

	public void setNumeroTotal(int numeroTotal) {
		this.numeroTotal = numeroTotal;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public CConexion getCon() {
		return con;
	}

	public void setCon(CConexion con) {
		this.con = con;
	}
	
	

}
