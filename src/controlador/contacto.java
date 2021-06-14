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
		boolean correcto;
		
		tipo=null;
		correcto =false;
		
		session = request.getSession();
		if (request.getParameter("correcto") != null) {
			correcto = Boolean.parseBoolean(request.getParameter("correcto"));
			session.setAttribute("contacto_correcto", correcto);
		}
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
		String FROM ;
		String TO;
		String cuerpo;
		String CLAVE;
		String acepto;
		
		session = request.getSession();
		envio = new EnvioCorreo();
		TO = "danielenrbae@gmail.com";
		
		
		asunto = request.getParameter("asunto");
		email = request.getParameter("email");
		telefono = request.getParameter("telefono");
		cuerpo = request.getParameter("mensaje");
		acepto = request.getParameter("acepto");
		
		mensaje = "Mensaje recibido de "+email+ " con teléfono "+telefono+"\n"
				+ "Asunto del mensaje: "+asunto+"\n"
				+ "Cuerpo del mensaje: "+cuerpo+"\n";
		
		System.out.println(mensaje);
		
		if (acepto.equals("on")) {
			
			//envio.correoContacto("brian94mj@gmail.com", "brian94mj@gmail.com", asunto, mensaje);
			
			if((envio.enviar( TO, asunto, mensaje))) {
				session.setAttribute("messageErrorContacto", "Mensaje enviado correctamente");

				response.sendRedirect("contacto?correcto=true");
				

			}else {

				session.setAttribute("messageErrorContacto", "Error al enviar el mensaje - Inténtelo de nuevo");
				response.sendRedirect("contacto?correcto=true");

			}

		}else  {
			response.sendRedirect("contacto");
		}
		
		
		
	}

}
