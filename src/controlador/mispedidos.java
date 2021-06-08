package controlador;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelos.Comprador;

/**
 * Servlet implementation class mispedidos
 */
@WebServlet("/mispedidos")
public class mispedidos extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	HttpSession session;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String tipo;
		Comprador usuario;
		boolean isIniciado;
		
		isIniciado= false;
		usuario = null;
		session = request.getSession();
		
		if (session.getAttribute("tipo_usuario") != null) {
			tipo = (String) session.getAttribute("tipo_usuario");
			
			if (tipo.equals("Cliente")) {
				usuario = (Comprador) session.getAttribute("usuario");
				isIniciado= true;
			} 
		}
		
		
		if (isIniciado) {
			request.getRequestDispatcher("WEB-INF/modules/style-guide/MisPedidos.jsp").forward(request, response);
		}else {
			response.sendRedirect("inicio");
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
