package modelos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import utils.CConexion;

public class Empresa {

	private int ID_empresa;
	private String nombre;
	private int telefono;
	private String password;
	private String confirm_password;
	private String descripcion;
	private String direccion;
	private byte[] foto;
	private String email;
	private boolean verificado;

	CConexion con;
	PreparedStatement ps;
	ResultSet rs;

	public Empresa() {
		con = new CConexion();
	}

	/**
	 * @param iD_empresa
	 * @param nombre
	 * @param telefono
	 * @param password
	 * @param confirm_password
	 * @param descripcion
	 * @param direccion
	 * @param foto
	 */
	public Empresa(String cnombre, int ctelefono, String cpassword, String cconfirm_password,
			String cdescripcion, String cdireccion, byte[] cfoto, boolean cverificado, String cemail) {
		nombre = cnombre;
		telefono = ctelefono;
		password = cpassword;
		confirm_password = cconfirm_password;
		descripcion = cdescripcion;
		direccion = cdireccion;
		foto = cfoto;
		verificado = cverificado;
		email = cemail;
		
		con = new CConexion();
	}

	/**
	 * Este método realiza una consulta en la base de datos en la tabla relacionada
	 * con las empresas - solo devuelve un registro
	 * 
	 * @param columna Valor de la columna donde quiere realizar la consulta
	 * @param valor   Valor de la condicion que se utiliza en la consulta
	 *
	 *
	 * @return Devuelve true o false dependiendo del resultado de la consulta
	 */

	public boolean leer(String columna, String valor) {
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

			// ps.setString(1, valor);
			rs = ps.executeQuery();

			if (rs.next()) {
				ID_empresa = rs.getInt("ID_empresa");
				nombre = rs.getString("nombre");
				telefono = rs.getInt("telefono");
				password = rs.getString("password");
				direccion = rs.getString("direccion");
				descripcion = rs.getString("descripcion");
				verificado = rs.getBoolean("verificado");
				email = rs.getString("email");

				// falta coger las fotos
				resultado = true;

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
				ID_empresa = rs.getInt("ID_empresa");
				nombre = rs.getString("nombre");
				telefono = rs.getInt("telefono");
				password = rs.getString("password");
				direccion = rs.getString("direccion");
				descripcion = rs.getString("descripcion");
				verificado = rs.getBoolean("verificado");
				email = rs.getString("email");
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

	public boolean insertar() {
		boolean resultado;

		String sql;
		resultado = false;
		sql = "insert into proyecto.empresa(email, nombre, password, verificado) VALUES(?,?,?,?)";

		con.iniciarConexion("ns3034756.ip-91-121-81.eu:5432/a20-denrbae", "a20-denrbae", "a20-denrbae");

		
		try {

			ps = con.getConnection().prepareStatement(sql);

			ps.setString(1, email);
			ps.setString(2, nombre);
			ps.setString(3, password);
			ps.setBoolean(4, verificado);
			
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

	public int getID_empresa() {
		return ID_empresa;
	}

	public void setID_empresa(int iD_empresa) {
		ID_empresa = iD_empresa;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirm_password() {
		return confirm_password;
	}

	public void setConfirm_password(String confirm_password) {
		this.confirm_password = confirm_password;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public byte[] getFoto() {
		return foto;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isVerificado() {
		return verificado;
	}

	public void setVerificado(boolean verificado) {
		this.verificado = verificado;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

}
