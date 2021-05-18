package modelos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import utils.CConexion;

public class Comprador {

	private String email;
	private String nombre;
	private int telefono;
	private String password;
	private String confirm_password;
	private String localidad;
	private String direccion;
	private byte[] foto;
	private boolean verificado;
	
	CConexion con;
	PreparedStatement ps;
	ResultSet rs;

	public Comprador() {
		con = new CConexion();
	}
	


	public Comprador(String cemail, String cnombre, int ctelefono, String cpassword, String cconfirm_password,
			String clocalidad, String cdireccion, byte[] cfoto, boolean cverificado) {
		email = cemail;
		nombre = cnombre;
		telefono = ctelefono;
		password = cpassword;
		confirm_password = cconfirm_password;
		localidad = clocalidad;
		direccion = cdireccion;
		foto = cfoto;
		verificado = cverificado;
		con = new CConexion();
	}

	
	/*
	 * Este método realiza una consulta en la base de datos en la tabla relacionada con los compradores - solo devuelve un registro
	 * 
	 *	@param columna Valor de la columna donde quiere realizar la consulta
	 *	@param valor Valor de la condicion que se utiliza en la consulta	
	 *
	 *
	 *	@return Devuelve true o false dependiendo del resultado de la consulta
	 * */

	public boolean leer(String columna , String valor) {
		boolean resultado;
		String sql;
		resultado = false;
		
		con.iniciarConexion("ns3034756.ip-91-121-81.eu:5432/a20-denrbae?currentSchema=proyecto", "a20-denrbae", "a20-denrbae");
		
		if(columna.equals("") || valor.equals("") || columna.equals(null) || valor.equals(null)) {
			sql = "SELECT * FROM COMPRADOR";
		}else {
			sql = "SELECT * FROM COMPRADOR where "+columna+"= ?";
		}
		
		
			try {
			
				ps= con.getConnection().prepareStatement(sql);
				
				ps.setString(1, valor);
				
				rs = ps.executeQuery();
				if (rs.next()) {
					email= rs.getString("email");
					nombre= rs.getString("nombre");
					telefono = rs.getInt("telefono");
					password = rs.getString("password");
					localidad = rs.getString("localidad");
					direccion = rs.getString("direccion");
					verificado =rs.getBoolean("verificado");
					//falta coger las fotos
					resultado = true;
					
				}else {
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
	
	public boolean leersiguiente() {
		boolean resultado = false;
		try {
			if (rs.next()) {
				email= rs.getString("email");
				nombre= rs.getString("nombre");
				telefono = rs.getInt("telefono");
				password = rs.getString("password");
				localidad = rs.getString("localidad");
				direccion = rs.getString("direccion");
				verificado =rs.getBoolean("verificado");
				resultado = true;
			}else {
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
		sql = "insert into proyecto.comprador(email, nombre, password, verificado) VALUES(?,?,?,?)";

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
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
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

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}



	public boolean isVerificado() {
		return verificado;
	}



	public void setVerificado(boolean verificado) {
		this.verificado = verificado;
	}
	
	


}
