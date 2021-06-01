package modelos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import utils.CConexion;

public class Carpro {

	private int id_producto;
	private int id_carrito;
	private int cantidad;

	private Producto producto;

	CConexion con;
	PreparedStatement ps;
	ResultSet rs;

	public Carpro() {
		con = new CConexion();
		producto = new Producto();
	}

	public Carpro(int cid_producto, int cid_carrito, int ccantidad) {
		id_carrito = cid_carrito;
		id_producto = cid_producto;
		cantidad = ccantidad;

		con = new CConexion();
		producto = new Producto();
	}

	/**
	 * 
	 * Método para insertar un producto en la tabla de los productos que se
	 * encuentran en el carrito
	 */

	public boolean insertar() {
		boolean resultado;
		String sql;

		sql = "insert into carpro (id_carrito , id_producto, cantidad) values (?,?,?);";
		resultado = false;

		con.iniciarConexion("ns3034756.ip-91-121-81.eu:5432/a20-denrbae?currentSchema=proyecto", "a20-denrbae",
				"a20-denrbae");

		try {
			ps = con.getConnection().prepareStatement(sql);

			ps.setInt(1, id_carrito);
			ps.setInt(2, id_producto);
			ps.setInt(3, cantidad);

			if (ps.execute()) {
				resultado = true;
				ps.close();
				con.cerrarConexion();
			} else {
				ps.close();
				con.cerrarConexion();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				ps.close();
				con.cerrarConexion();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

			resultado = false;
		}

		return resultado;
	}

	/**
	 * Metodo para consultar los productos de un carrito
	 * 
	 * 
	 * 
	 * @return resultado boolean
	 */

	public boolean leer(String columna, int valor, String columna2, int valor2, boolean unico, boolean getProducto) {
		boolean resultado;
		String sql;

		sql = "";
		resultado = false;

		if (!columna.isEmpty()) {
			sql = "SELECT * from carpro where " + columna + " = ?";
		}

		if (!columna2.isEmpty()) {
			sql = "SELECT * from carpro where " + columna + " = ? AND " + columna2 + "= ?";

		}

		if (getProducto) {

			sql = "select * from getDatosCarrito(?)";

		}

		con.iniciarConexion("ns3034756.ip-91-121-81.eu:5432/a20-denrbae?currentSchema=proyecto", "a20-denrbae",
				"a20-denrbae");

		try {
			ps = con.getConnection().prepareStatement(sql);

			ps.setInt(1, valor);

			if (!columna2.isEmpty()) {
				ps.setInt(2, valor2);
			}

			rs = ps.executeQuery();

			if (rs.next()) {
				cantidad = rs.getInt("cantidad");
				id_carrito = rs.getInt("id_carrito");
				id_producto = rs.getInt("id_producto");

				if (getProducto) {
					producto.setNombre(rs.getString("nombre"));
					producto.setPrecio(rs.getFloat("precio"));
					producto.setFoto(rs.getBytes("foto"));
				}

				resultado = true;
			} else {
				rs.close();
				ps.close();
				con.cerrarConexion();
			}

			if (unico) {
				rs.close();
				ps.close();
				con.cerrarConexion();
			}

		} catch (SQLException e) {
			resultado = false;
			e.printStackTrace();
		}

		return resultado;
	}

	public boolean leerSiguiente(boolean getProducto) {
		boolean resultado = false;

		try {
			if (rs.next()) {
				cantidad = rs.getInt("cantidad");
				id_carrito = rs.getInt("id_carrito");
				id_producto = rs.getInt("id_producto");
				resultado = true;

				if (getProducto) {
					producto.setNombre(rs.getString("nombre"));
					producto.setPrecio(rs.getFloat("precio"));
					producto.setFoto(rs.getBytes("foto"));
				}
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
	 * Método para insertar un producto del carrito
	 */

	public boolean delete(String columna, int valor, String columna2, int valor2) {
		boolean resultado;
		String sql;

		resultado = false;
		sql = "";

		con.iniciarConexion("ns3034756.ip-91-121-81.eu:5432/a20-denrbae", "a20-denrbae", "a20-denrbae");

		if (!columna.isEmpty()) {
			sql = "delete from proyecto.carpro where " + columna + " = ?";
		}

		if (!columna2.isEmpty()) {
			sql = "delete from proyecto.carpro where " + columna + " = ? and " + columna2 + "= ?";
		}

		try {

			ps = con.getConnection().prepareStatement(sql);

			if (!columna.isEmpty()) {
				ps.setInt(1, valor);
			}

			if (!columna2.isEmpty()) {
				ps.setInt(2, valor2);
			}
			

			if (ps.executeUpdate() == 1) {
				resultado = true;
			}

			ps.close();
			con.cerrarConexion();

		} catch (SQLException e) {
			e.printStackTrace();
			resultado = false;
		}

		return resultado;

	}

	public boolean update(String columna, int valor , String columna2 , int valor2 , String columna3 , int valor3) {
		boolean resultado;
		String sql;
		
		sql = "";
		resultado = false;
		
		con.iniciarConexion("ns3034756.ip-91-121-81.eu:5432/a20-denrbae", "a20-denrbae", "a20-denrbae");

		if (!columna.isEmpty() && !columna2.isEmpty() && !columna3.isEmpty()) {
			sql = "update proyecto.carpro set "+columna+ "= ? where "+columna2+" = ? and "+columna3+"= ?";
		}
		
		try {
			ps = con.getConnection().prepareStatement(sql);
			
			ps.setInt(1, valor);
			ps.setInt(2, valor2);
			ps.setInt(3, valor3);
			
			if (ps.executeUpdate() == 1) {
				resultado = true;
			}
			
		} catch (SQLException e) {
			resultado = false;
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

	public int getId_carrito() {
		return id_carrito;
	}

	public void setId_carrito(int id_carrito) {
		this.id_carrito = id_carrito;
	}

	public CConexion getCon() {
		return con;
	}

	public void setCon(CConexion con) {
		this.con = con;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

}
