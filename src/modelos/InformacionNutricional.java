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

}
