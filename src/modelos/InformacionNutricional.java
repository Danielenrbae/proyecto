package modelos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import utils.CConexion;

public class InformacionNutricional {

	private int energia;
	private int peso;
	private int valorkj;
	private int valorkcal;
	private int proteinas;
	private int hidratos;
	private int fibra;
	private int azucares;
	private int sal;
	private int grasas;
	private int grasos_saturados;

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
	public InformacionNutricional(int cenergia, int cpeso, int cvalorkj, int cvalorkcal, int cproteinas, int chidratos,
			int cfibra, int cazucares, int csal, int cgrasas, int cgrasos_saturados, int cid_producto) {

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

			ps.setInt(1, energia);
			ps.setInt(2, peso);
			ps.setInt(3, valorkj);
			ps.setInt(4, valorkcal);
			ps.setInt(5, proteinas);
			ps.setInt(6, hidratos);
			ps.setInt(7, fibra);
			ps.setInt(8, azucares);
			ps.setInt(9, sal);
			ps.setInt(10, grasas);
			ps.setInt(11, grasos_saturados);
			ps.setInt(12, id_producto);
			
				
			
			if (ps.executeUpdate() == 1) {
				resultado = true;
			}
			
			ps.close();
			con.cerrarConexion();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return resultado;

	}

}
