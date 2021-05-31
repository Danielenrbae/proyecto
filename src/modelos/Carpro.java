package modelos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import utils.CConexion;

public class Carpro {

	private int id_producto;
	private int id_carrito;
	private int cantidad;
	
	
	CConexion con;
	PreparedStatement ps;
	ResultSet rs;
	
	public Carpro() {
		con = new CConexion();
	}
	
	public Carpro(int cid_producto, int cid_carrito , int ccantidad) {
		id_carrito= cid_carrito;
		id_producto = cid_producto;
		cantidad = ccantidad;
		
		con = new CConexion();
	}
	
	/**
	 * 
	 * Método para insertar un producto en la tabla de los productos que se encuentran en el carrito
	 */
	
	public boolean insertar() {
		boolean resultado;
		String sql;
	
		sql = "insert into carpro (id_carrito , id_producto, cantidad) values (?,?,?);";
		resultado = false;
		
		con.iniciarConexion("ns3034756.ip-91-121-81.eu:5432/a20-denrbae?currentSchema=proyecto", "a20-denrbae", "a20-denrbae");

		
		try {
			ps = con.getConnection().prepareStatement(sql);
			
			ps.setInt(1, id_carrito);
			ps.setInt(2, id_producto);
			ps.setInt(3, cantidad);

			
			if (ps.execute()) {
				resultado = true;
				ps.close();
				con.cerrarConexion();
			}else {
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
	
	
}
