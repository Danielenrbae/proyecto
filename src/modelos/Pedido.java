package modelos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import utils.CConexion;

public class Pedido {
	
	private int id_pedido;
	private String estado;
	private String fecha;
	private String id_comprador;
	private int id_empresa;
	private int id_repartidor;
	
	CConexion con;
	ResultSet rs;
	PreparedStatement ps;
	
	
	public Pedido() {
		con = new CConexion();
	}

	

	public Pedido(int cid_pedido, String cestado, String cfecha, String cid_comprador, int cid_empresa, int cid_repartidor) {
	
		id_pedido = cid_pedido;
		estado = cestado;
		fecha = cfecha;
		id_comprador = cid_comprador;
		id_empresa = cid_empresa;
		id_repartidor = cid_repartidor;
		
		con = new CConexion();
	}
	
	/**
	 * Metodo para insertar un pedido en la base de datos
	 * 
	 * @return boolean
	 */
	
	public boolean insertar() {
		boolean resultado;
		String sql;
		
		con.iniciarConexion("ns3034756.ip-91-121-81.eu:5432/a20-denrbae?currentSchema=proyecto", "a20-denrbae", "a20-denrbae");
		resultado = false;
		sql="insert into proyecto.pedido (estado, fecha , id_comprador, id_empresa) values (?,?,?,?)";
		
		try {
			ps = con.getConnection().prepareStatement(sql);
			
			ps.setString(1, estado);
			ps.setString(2, fecha);
			ps.setString(3, id_comprador);
			ps.setInt(4, id_empresa);
			
			if(ps.execute()) {
				resultado = true;
			}
			
			ps.close();
			con.cerrarConexion();
			
		} catch (SQLException e) {
			resultado= false;
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
	 * Método para leer el contenido de un producto
	 * 
	 * @return boolean
	 */
	
	public boolean leerPrimero(String columna , String valor) {
		boolean resultado;
		resultado= false;
		
		
		return resultado;
	}



	public int getId_pedido() {
		return id_pedido;
	}


	public void setId_pedido(int id_pedido) {
		this.id_pedido = id_pedido;
	}


	public String getEstado() {
		return estado;
	}


	public void setEstado(String estado) {
		this.estado = estado;
	}


	public String getFecha() {
		return fecha;
	}


	public void setFecha(String fecha) {
		this.fecha = fecha;
	}


	public String getId_comprador() {
		return id_comprador;
	}


	public void setId_comprador(String id_comprador) {
		this.id_comprador = id_comprador;
	}


	public int getId_empresa() {
		return id_empresa;
	}


	public void setId_empresa(int id_empresa) {
		this.id_empresa = id_empresa;
	}


	public int getId_repartidor() {
		return id_repartidor;
	}


	public void setId_repartidor(int id_repartidor) {
		this.id_repartidor = id_repartidor;
	}


	public CConexion getCon() {
		return con;
	}


	public void setCon(CConexion con) {
		this.con = con;
	}

	
	
}
