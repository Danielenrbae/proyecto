package controlador;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class verificacion
 */
@WebServlet("/verificacion")
public class verificacion extends HttpServlet {
	private static final long serialVersionUID = 1L;

	HttpSession session;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String email;
		session = request.getSession();
		
		if (session.isNew()) {
			session.setAttribute("usuario", null);
			session.setAttribute("email-verificacion", null);
			session.setAttribute("codigo-verificacion", null);

		}
		
		
		email = (String) session.getAttribute("email-verificacion");
		
		if(email.equals(null)) {
			//redirigir a servicio no disponible
		}else {
			doPost(request, response);
		}

	
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
		String codigo_verificacion;
		String codigo;
		PrintWriter out;
		
		response.setContentType("text/plain");
		out = response.getWriter();
		session = request.getSession();
		
		System.out.println("hola");
		codigo_verificacion = (String) session.getAttribute("codigo-verificacion");
		codigo = request.getParameter("codigo");
		
		
		System.out.println(codigo_verificacion);
		
		if (codigo.equals(codigo_verificacion)) {
		
			
			//hacer el update del atributo verificado
			
			out.print("{ \"ok\" : 1 }");
		}else {
			out.print("{ \"error\" : \"Código no válido\" }");
		}
		
	}

}
