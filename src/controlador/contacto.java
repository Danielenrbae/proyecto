package controlador;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import utils.EnvioCorreo;

/**
 * Servlet implementation class contacto
 */
@WebServlet("/contacto")
public class contacto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    HttpSession session;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String tipo;
		
		tipo=null;
		
		session = request.getSession();
		
		if (session.isNew()) {
			session.setAttribute("usuario", null);
			session.setAttribute("email-verificacion", null);
			session.setAttribute("codigo-verificacion", null);
			session.setAttribute("tipo_usuario", null);
			session.setAttribute("categoriasDisponibles", null);

		}
		
		if (session.getAttribute("tipo_usuario") != null) {
			tipo = (String) session.getAttribute("tipo_usuario");
			
			if (tipo.equals("Empresa")) {
				request.getRequestDispatcher("/WEB-INF/modules/style-guide/ContactoEmpresa.jsp").forward(request, response);

			}else {
				request.getRequestDispatcher("/WEB-INF/modules/style-guide/Contacto.jsp").forward(request, response);

			}
		}else {
			request.getRequestDispatcher("/WEB-INF/modules/style-guide/Contacto.jsp").forward(request, response);

		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		EnvioCorreo envio ;
		String email;
		String telefono;
		String asunto;
		String mensaje;
		
		
		session = request.getSession();
		envio = new EnvioCorreo();
		
		
	}

}
