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
	private double importe;

	CConexion con;
	ResultSet rs;
	PreparedStatement ps;

	public Pedido() {
		con = new CConexion();
	}

	public Pedido(int cid_pedido, String cestado, String cfecha, String cid_comprador, int cid_empresa,
			int cid_repartidor) {

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

		con.iniciarConexion("ns3034756.ip-91-121-81.eu:5432/a20-denrbae?currentSchema=proyecto", "a20-denrbae",
				"a20-denrbae");
		resultado = false;
		sql = "select * from proyecto.getLastIDPedido(?,?,?,?)";

		try {
			ps = con.getConnection().prepareStatement(sql);

			ps.setString(1, estado);
			ps.setString(2, fecha);
			ps.setString(3, id_comprador);
			ps.setInt(4, id_empresa);

			// ejecute query
			rs = ps.executeQuery();

			if (rs.next()) {
				id_pedido = rs.getInt(1);
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
	 * Método para leer el contenido de un producto
	 * 
	 * @return boolean
	 */

	public boolean leer(String columna, String valor, String columna2, String valor2, String columna3, int valor3,
			boolean unico) {
		boolean resultado;
		String sql;

		sql = "";
		resultado = false;

		if (!columna.isEmpty() && columna2.isEmpty() && columna3.isEmpty()) {
			sql = "select * from pedido where " + columna + "= ?";
		}

		if (!columna.isEmpty() && !columna2.isEmpty() && !columna3.isEmpty()) {
			sql = "select * from pedido where " + columna + "= ? and " + columna2 + "= ? and " + columna3 + "= ?";

		}

		con.iniciarConexion("ns3034756.ip-91-121-81.eu:5432/a20-denrbae?currentSchema=proyecto", "a20-denrbae",
				"a20-denrbae");

		try {
			ps = con.getConnection().prepareStatement(sql);

			if (!columna.isEmpty() && columna2.isEmpty() && columna3.isEmpty()) {
				ps.setString(1, valor);
			}

			if (!columna.isEmpty() && !columna2.isEmpty() && !columna3.isEmpty()) {
				ps.setString(1, valor);
				ps.setString(2, valor2);
				ps.setInt(3, valor3);
			}
			System.out.println(ps);
			rs = ps.executeQuery();

			if (rs.next()) {
				id_comprador = rs.getString("id_comprador");
				id_empresa = rs.getInt("id_empresa");
				estado = rs.getString("estado");
				fecha = rs.getString("fecha");
				id_pedido = rs.getInt("id_pedido");

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

			try {
				ps.close();
				rs.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			con.cerrarConexion();
			e.printStackTrace();
		}

		return resultado;
	}

	public boolean leer(String columna, int valor, boolean unico) {
		boolean resultado;
		String sql;

		sql = "";
		resultado = false;

		if (!columna.isEmpty()) {
			sql = "select * from pedido where " + columna + "= ?";
		}

		con.iniciarConexion("ns3034756.ip-91-121-81.eu:5432/a20-denrbae?currentSchema=proyecto", "a20-denrbae",
				"a20-denrbae");

		try {
			ps = con.getConnection().prepareStatement(sql);

			if (!columna.isEmpty()) {
				ps.setInt(1, valor);
			}

			rs = ps.executeQuery();

			if (rs.next()) {
				id_comprador = rs.getString("id_comprador");
				id_empresa = rs.getInt("id_empresa");
				estado = rs.getString("estado");
				fecha = rs.getString("fecha");
				id_pedido = rs.getInt("id_pedido");

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

			try {
				ps.close();
				rs.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			con.cerrarConexion();
			e.printStackTrace();
		}

		return resultado;
	}

	public boolean leer(int valor, boolean unico) {
		boolean resultado;
		String sql;

		sql = "";
		resultado = false;

		sql = "select * from proyecto.getpedido_importe(?)";

		con.iniciarConexion("ns3034756.ip-91-121-81.eu:5432/a20-denrbae?currentSchema=proyecto", "a20-denrbae",
				"a20-denrbae");

		try {
			ps = con.getConnection().prepareStatement(sql);

			ps.setInt(1, valor);

			rs = ps.executeQuery();

			if (rs.next()) {
				id_comprador = rs.getString("id_comprador");
				id_empresa = rs.getInt("id_empresa");
				estado = rs.getString("estado");
				fecha = rs.getString("fecha");
				id_pedido = rs.getInt("id_pedido");
				importe = rs.getDouble("importe");

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

			try {
				ps.close();
				rs.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			con.cerrarConexion();
			e.printStackTrace();
		}

		return resultado;
	}

	public boolean leerSiguiente() {
		boolean resultado;
		resultado = false;

		try {
			if (rs.next()) {
				id_comprador = rs.getString("id_comprador");
				id_empresa = rs.getInt("id_empresa");
				estado = rs.getString("estado");
				fecha = rs.getString("fecha");
				id_pedido = rs.getInt("id_pedido");

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

	public double getImporte() {
		return importe;
	}

	public void setImporte(double importe) {
		this.importe = importe;
	}

}
