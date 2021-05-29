package modelos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import utils.CConexion;

public class Carrito {

	private int id_carrito;
	private int id_comprador;
	
	CConexion con ;
	ResultSet rs;
	PreparedStatement ps;
	
	public Carrito() {
		con = new CConexion();
	}
	
	public Carrito(int cid_comprador) {
		id_comprador = cid_comprador;
		con = new CConexion();
	}
	
	
	/**
	 * Método para insertar el carrito de un comprador
	 * 
	 * @return resultado boolean
	 */
	
	public boolean insertar() {
		String sql;
		boolean resultado;
		
		resultado = false;
		con.iniciarConexion("ns3034756.ip-91-121-81.eu:5432/a20-denrbae?currentSchema=proyecto", "a20-denrbae", "a20-denrbae");

		sql = "insert into carrito (id_comprador) values (?)";
		
		try {
			
			ps = con.getConnection().prepareStatement(sql);
			ps.setInt(1, id_comprador);
			
			if(ps.execute()) {
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
	
	/**
	 * Método para consultar un carrito 
	 * 
	 * @param columna string
	 * @param valor integer
	 * @param unico boolean
	 * 
	 * @return boolean
	 */
	
	
	public boolean leer(String columna, String valor , boolean unico) {
		boolean resultado;
		String sql;
		
		resultado =false;
		sql = "select * from carrito";
		
		con.iniciarConexion("ns3034756.ip-91-121-81.eu:5432/a20-denrbae?currentSchema=proyecto", "a20-denrbae", "a20-denrbae");

		
		if (!columna.isEmpty() || !valor.isEmpty()) {
			sql = "select * from carrito where "+columna + "= ?";
		}
		
		try {
			ps= con.getConnection().prepareStatement(sql);
			
			ps.setInt(1, Integer.parseInt(valor));
			
			rs = ps.executeQuery();
			
			if (rs.next()) {
				
				id_carrito = rs.getInt("id_carrito");
				id_comprador = rs.getInt("id_comprador");
				resultado = true;
			}else {
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
			e.printStackTrace();
			resultado = false;
		}
	
		return resultado;
	}
	
	
}