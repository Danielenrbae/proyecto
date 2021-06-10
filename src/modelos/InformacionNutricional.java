package modelos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import utils.CConexion;

public class InformacionNutricional {

	private double energia;
	private double peso;
	private double valorkj;
	private double valorkcal;
	private double proteinas;
	private double hidratos;
	private double fibra;
	private double azucares;
	private double sal;
	private double grasas;
	private double grasos_saturados;

	private int id_producto;
	private int id_informacion;

	CConexion con;
	PreparedStatement ps;
	ResultSet rs;

	public InformacionNutricional() {
		con = new CConexion();
	}

	/**
	 * @param energia
	 * @param peso
	 * @param valorkj
	 * @param valorkcal
	 * @param proteinas
	 * @param hidratos
	 * @param fibra
	 * @param azucares
	 * @param sal
	 * @param grasas
	 * @param grasos_saturados
	 * @param id_producto
	 */
	public InformacionNutricional(double cenergia, double cpeso, double cvalorkj, double cvalorkcal, double cproteinas, double chidratos,
			double cfibra, double cazucares, double csal, double cgrasas, double cgrasos_saturados, int cid_producto) {

		energia = cenergia;
		peso = cpeso;
		valorkj = cvalorkj;
		valorkcal = cvalorkcal;
		proteinas = cproteinas;
		hidratos = chidratos;
		fibra = cfibra;
		azucares = cazucares;
		sal = csal;
		grasas = cgrasas;
		grasos_saturados = cgrasos_saturados;
		id_producto = cid_producto;
		con = new CConexion();
	}
	
	
	/**
	 * 
	 * 	Metodo para consultar la informacion nutricional de un producto
	 * 
	 * @param columna String
	 * @param valor String 
	 * @param unico boolean
	 * 
	 * @return boolean
	 * */
	
	public boolean leer( String columna, int valor , boolean unico) {
		boolean resultado;
		String sql;
		
		resultado = false;
		sql = "";
		
		con.iniciarConexion("ns3034756.ip-91-121-81.eu:5432/a20-denrbae?currentSchema=proyecto", "a20-denrbae", "a20-denrbae");

		
		if (!columna.isEmpty() || valor != 0) {
			sql = "SELECT * FROM informacion_nutricional where "+columna+" = ? ;";
		}
		
		
			try {
				ps= con.getConnection().prepareStatement(sql);
			

			ps.setInt(1, valor);
		
			rs = ps.executeQuery();
			
			
			if (rs.next()) {
				
				energia = rs.getDouble("energia");
				peso = rs.getDouble("peso");
				valorkj = rs.getDouble("valorkj");
				valorkcal = rs.getDouble("valorkcal");
				proteinas = rs.getDouble("proteinas");
				hidratos = rs.getDouble("hidratos");
				fibra = rs.getDouble("fibra");
				azucares = rs.getDouble("azucares");
				sal = rs.getDouble("sal");
				grasas = rs.getDouble("grasas");
				grasos_saturados = rs.getDouble("grasos_saturados");
				id_producto = rs.getInt("id_producto");
				id_informacion = rs.getInt("id_informacion");
				
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
				resultado =false;
			}
		
		
		
		return resultado;
	}
	
	/**
	 * Método para insertar un producto con foto en la base de datos
	 * */

	public boolean insertar() {
		boolean resultado;

		String sql;
		resultado = false;
		sql = "insert into proyecto.informacion_nutricional(energia, peso, valorkj, valorkcal, proteinas, hidratos, fibra,azucares, sal, grasas, grasos_saturados, id_producto) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";

		con.iniciarConexion("ns3034756.ip-91-121-81.eu:5432/a20-denrbae", "a20-denrbae", "a20-denrbae");

		
		try {

			ps = con.getConnection().prepareStatement(sql);

			ps.setDouble(1, energia);
			ps.setDouble(2, peso);
			ps.setDouble(3, valorkj);
			ps.setDouble(4, valorkcal);
			ps.setDouble(5, proteinas);
			ps.setDouble(6, hidratos);
			ps.setDouble(7, fibra);
			ps.setDouble(8, azucares);
			ps.setDouble(9, sal);
			ps.setDouble(10, grasas);
			ps.setDouble(11, grasos_saturados);
			ps.setInt(12, id_producto);
			
				
		

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

	public double getEnergia() {
		return energia;
	}

	public void setEnergia(double energia) {
		this.energia = energia;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	public double getValorkj() {
		return valorkj;
	}

	public void setValorkj(double valorkj) {
		this.valorkj = valorkj;
	}

	public double getValorkcal() {
		return valorkcal;
	}

	public void setValorkcal(double valorkcal) {
		this.valorkcal = valorkcal;
	}

	public double getProteinas() {
		return proteinas;
	}

	public void setProteinas(double proteinas) {
		this.proteinas = proteinas;
	}

	public double getHidratos() {
		return hidratos;
	}

	public void setHidratos(double hidratos) {
		this.hidratos = hidratos;
	}

	public double getFibra() {
		return fibra;
	}

	public void setFibra(double fibra) {
		this.fibra = fibra;
	}

	public double getAzucares() {
		return azucares;
	}

	public void setAzucares(double azucares) {
		this.azucares = azucares;
	}

	public double getSal() {
		return sal;
	}

	public void setSal(double sal) {
		this.sal = sal;
	}

	public double getGrasas() {
		return grasas;
	}

	public void setGrasas(double grasas) {
		this.grasas = grasas;
	}

	public double getGrasos_saturados() {
		return grasos_saturados;
	}

	public void setGrasos_saturados(double grasos_saturados) {
		this.grasos_saturados = grasos_saturados;
	}

	public int getId_producto() {
		return id_producto;
	}

	public void setId_producto(int id_producto) {
		this.id_producto = id_producto;
	}

	public int getId_informacion() {
		return id_informacion;
	}

	public void setId_informacion(int id_informacion) {
		this.id_informacion = id_informacion;
	}

	public CConexion getCon() {
		return con;
	}

	public void setCon(CConexion con) {
		this.con = con;
	}
	
	

}
