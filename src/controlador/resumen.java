package controlador;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class resumen
 */
@WebServlet("/resumen")
public class resumen extends HttpServlet {
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
			
			

			request.getRequestDispatcher("/WEB-INF/modules/style-guide/Resumen.jsp").forward(request, response);
			
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
