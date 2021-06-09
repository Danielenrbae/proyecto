package modelos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import utils.CConexion;

public class Proped {

	private int id_pedido;
	private int id_producto;
	private int cantidad;

	private String nombre;
	private double precioUnidad;
	private String id_comprador;

	CConexion con;
	PreparedStatement ps;
	ResultSet rs;

	public Proped() {
		con = new CConexion();
	}

	public Proped(int cid_pedido, int cid_producto, int ccantidad) {

		id_pedido = cid_pedido;
		id_producto = cid_producto;
		cantidad = ccantidad;
		con = new CConexion();

	}

	/**
	 * Metodo para insertar un producto de un pedido
	 * 
	 * 
	 * @return boolean
	 */

	public boolean insertar() {
		boolean resultado;
		String sql;

		resultado = false;
		sql = "insert into proyecto.proped (id_pedido, id_producto , cantidad) values(?,?,?);";
		con.iniciarConexion("ns3034756.ip-91-121-81.eu:5432/a20-denrbae?currentSchema=proyecto", "a20-denrbae",
				"a20-denrbae");

		try {
			ps = con.getConnection().prepareStatement(sql);

			ps.setInt(1, id_pedido);
			ps.setInt(2, id_producto);
			ps.setInt(3, cantidad);

			if (ps.execute()) {
				resultado = true;
			}
			ps.close();
			con.cerrarConexion();

		} catch (SQLException e) {
			resultado = false;
			try {
				ps.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			con.cerrarConexion();
			e.printStackTrace();
		}

		return resultado;
	}

	/**
	 * Metodo para consultar los datos de los productos de un pedido
	 * 
	 * @return boolean
	 */

	public boolean leer(int pedido, String comprador) {
		boolean resultado;
		String sql;

		sql = "";
		resultado = false;
		con.iniciarConexion("ns3034756.ip-91-121-81.eu:5432/a20-denrbae?currentSchema=proyecto", "a20-denrbae",
				"a20-denrbae");

		if (pedido != 0 && !comprador.isEmpty()) {
			sql = "select * from proyecto.getpedido_proped_producto(?,?)";
		}

		try {
			ps = con.getConnection().prepareStatement(sql);

			ps.setInt(1, pedido);
			ps.setString(2, comprador);

			rs = ps.executeQuery();

			if (rs.next()) {

				id_pedido = rs.getInt("id_pedido");
				id_producto = rs.getInt("id_producto");
				cantidad = rs.getInt("cantidad");

				nombre = rs.getString("nombre");
				precioUnidad = rs.getDouble("precio");
				id_comprador = rs.getString("id_comprador");

				resultado = true;

			} else {
				rs.close();
				ps.close();
				con.cerrarConexion();
			}

		} catch (SQLException e) {
			e.printStackTrace();
			resultado = false;
		}

		return resultado;
	}

	/**
	 * Método para leer el siguiente registro de la consulta
	 * 
	 * @return boolean
	 */

	public boolean leerSiguiente() {
		boolean resultado;
		resultado = false;

		try {
			if (rs.next()) {

				id_pedido = rs.getInt("id_pedido");
				id_producto = rs.getInt("id_producto");
				cantidad = rs.getInt("cantidad");

				nombre = rs.getString("nombre");
				precioUnidad = rs.getDouble("precio");
				id_comprador = rs.getString("id_comprador");

				resultado = true;

			} else {
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

	public int getId_pedido() {
		return id_pedido;
	}

	public void setId_pedido(int id_pedido) {
		this.id_pedido = id_pedido;
	}

	public int getId_producto() {
		return id_producto;
	}

	public void setId_producto(int id_producto) {
		this.id_producto = id_producto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public CConexion getCon() {
		return con;
	}

	public void setCon(CConexion con) {
		this.con = con;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getPrecioUnidad() {
		return precioUnidad;
	}

	public void setPrecioUnidad(double precioUnidad) {
		this.precioUnidad = precioUnidad;
	}

	public String getId_comprador() {
		return id_comprador;
	}

	public void setId_comprador(String id_comprador) {
		this.id_comprador = id_comprador;
	}

}
