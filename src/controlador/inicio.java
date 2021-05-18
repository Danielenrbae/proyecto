package controlador;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class inicio
 */
@WebServlet("/inicio")
public class inicio extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	HttpSession session;
  

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		session = request.getSession();
		
		if (session.isNew()) {
			session.setAttribute("usuario", null);
			session.setAttribute("email-verificacion", null);
			session.setAttribute("codigo-verificacion", null);
		}
		
		request.getRequestDispatcher("/WEB-INF/modules/style-guide/Inicio.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		session = request.getSession();
		doGet(request, response);
	}

}
