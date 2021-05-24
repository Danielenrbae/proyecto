package controlador;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelos.Producto;

/**
 * Servlet implementation class bajarFoto
 */
@WebServlet("/bajarFoto")
public class bajarFoto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   HttpSession session;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("inicio");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String tipo;
		Producto producto;
		
		session = request.getSession();
		
		tipo = (String) request.getParameter("param_img");
		
		switch (tipo) {
		case "producto":
			
			
			
			
			break;

		default:
			break;
		}
		
		
	}

}
