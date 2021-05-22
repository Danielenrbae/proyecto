package controlador;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelos.Empresa;
import modelos.InformacionNutricional;
import modelos.Producto;

/**
 * Servlet implementation class insertarproducto
 */
@WebServlet("/insertarproducto")
public class insertarproducto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	HttpSession session;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.sendRedirect("resumen");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String nombre;
		String descripcion;
		float precio;
		//foto
		
		int energia;
		int peso;
		int valorkj;
		int valorkcal;
		int proteinas;
		int hidratos;
		int fibra;
		int azucares;
		int sal;
		int grasas;
		int grasos_saturados;
		
		int id_empresa;
		int id_categoria;
		
		
		
		Empresa empresa;
		InformacionNutricional info_nutri;
		Producto producto;
		
		String tipo;
		
		session = request.getSession();
		
		tipo = (String) session.getAttribute("tipo_usuario");
		
		if (tipo.equals("Empresa") && tipo != null) {
			
			empresa = (Empresa) session.getAttribute("usuario");
			id_empresa = empresa.getID_empresa();
			
			//seleccionar la categoria
			
				
		}else {
			response.sendRedirect("inicio");
		}
		
		
		
	}

}
