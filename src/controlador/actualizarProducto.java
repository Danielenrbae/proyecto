package controlador;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelos.Categoria;

/**
 * Servlet implementation class actualizarProducto
 */
@WebServlet("/actualizarProducto")
public class actualizarProducto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	HttpSession session;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String tipo;
		
		session = request.getSession();

		tipo = (String) session.getAttribute("tipo_usuario");
		
		if(tipo != null && !tipo.equals("Cliente")) {
						
			
			
		}else {
			response.sendRedirect("inicio");
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

}
