package controlador;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelos.Comprador;
import modelos.Empresa;

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
			session.setAttribute("tipo_usuario", null);
		}

		email = (String) session.getAttribute("email-verificacion");

		if (email.equals(null)) {
			// redirigir a servicio no disponible
		} else {
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
		String email;
		Comprador comprador;
		Empresa empresa;
		String tipo_usuario;

		response.setContentType("text/plain");
		out = response.getWriter();
		session = request.getSession();

		codigo_verificacion = (String) session.getAttribute("codigo-verificacion");
		email = (String) session.getAttribute("email-verificacion");
		tipo_usuario = (String) session.getAttribute("tipo_usuario");
		codigo = request.getParameter("codigo");

		System.out.println(codigo_verificacion);

		/** MIRAR SI ES COMPRADOR O EMPRESA **/

		if (codigo.equals(codigo_verificacion)) {

			// hacer el update del atributo verificado

			if (tipo_usuario.equals("Cliente")) {
				comprador = new Comprador();
				if (comprador.update("verificado", "T", "email", email)) {
					comprador.leer("email", email);
					session.setAttribute("usuario", comprador);
					out.print("{ \"ok\" : 1 , \"url\" : \"/a20-denrbae_proyecto_final/inicio\" }");
				} else {
					session.setAttribute("usuario", null);
					out.print("{ \"error\" : \"Ha ocurrido un error. Int??ntelo de nuevo\" }");
				}
			} else if (tipo_usuario.equals("Empresa")) {
				empresa = new Empresa();
				if (empresa.update("verificado", "T", "email", email)) {

					empresa.leer("email", email);
					
					session.setAttribute("usuario", empresa);
					
					out.print("{ \"ok\" : 1 , \"url\" : \"/a20-denrbae_proyecto_final/resumen\" }");
				} else {
					session.setAttribute("usuario", null);
					out.print("{ \"error\" : \"Ha ocurrido un error. Int??ntelo de nuevo\" }");

				}
			} else {
				out.print("{ \"error\" : \"Ha ocurrido un error. Int??ntelo de nuevo\" }");

			}

		} else {
			out.print("{ \"error\" : \"C??digo no v??lido\" }");
		}

	}

}
