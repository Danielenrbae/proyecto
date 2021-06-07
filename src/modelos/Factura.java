package modelos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import utils.CConexion;

public class Factura {

	private int id_factura;
	private double importe;
	private int id_pedido;
	private String fecha;
	
	CConexion con;
	PreparedStatement ps;
	ResultSet rs;
	
	public Factura() {
		con = new CConexion();
	}

	public Factura(int cid_factura, double cimporte, int cid_pedido, String cfecha) {
		
		id_factura = cid_factura;
		importe = cimporte;
		id_pedido = cid_pedido;
		fecha = cfecha;
		
		con = new CConexion();

	}
	
	/**
	 * Método para la creacion de una factura en la base de datos de la web
	 *  
	 * 
	 * @return boolean 
	 */
	
	
	public boolean insertar() {
		boolean resultado;
		String sql;
		
		sql= "insert into factura (importe , fecha, id_pedido) values(?,?,?)";
		resultado = false;
		con.iniciarConexion("ns3034756.ip-91-121-81.eu:5432/a20-denrbae?currentSchema=proyecto", "a20-denrbae", "a20-denrbae");

		try {
			ps = con.getConnection().prepareStatement(sql);
			
			ps.setDouble(1, importe);
			ps.setString(2, fecha);
			ps.setInt(3, id_pedido);
			
			if (ps.execute()) {
				resultado = true;
			}
			
			ps.close();
			con.cerrarConexion();
			
		} catch (SQLException e) {
			resultado = false;
			e.printStackTrace();
		}
		
		
		
		return resultado;
	}

	public int getId_factura() {
		return id_factura;
	}

	public void setId_factura(int id_factura) {
		this.id_factura = id_factura;
	}

	public double getImporte() {
		return importe;
	}

	public void setImporte(double importe) {
		this.importe = importe;
	}

	public int getId_pedido() {
		return id_pedido;
	}

	public void setId_pedido(int id_pedido) {
		this.id_pedido = id_pedido;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public CConexion getCon() {
		return con;
	}

	public void setCon(CConexion con) {
		this.con = con;
	}
	
	
	
	
}
