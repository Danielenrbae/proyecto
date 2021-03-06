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
import utils.Cifrado;
import utils.EnvioCorreo;

/**
 * Servlet implementation class iniciarsesion
 */
@WebServlet("/iniciarsesion")
public class iniciarsesion extends HttpServlet {
	private static final long serialVersionUID = 1L;

	HttpSession session;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		session = request.getSession();

		if (session.isNew()) {
			session.setAttribute("usuario", null);
			session.setAttribute("email-verificacion", null);
			session.setAttribute("codigo-verificacion", null);
			session.setAttribute("tipo_usuario", null);

		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String email;
		String password;
		String usuario;
		PrintWriter out;
		Comprador comprador;
		Empresa empresa;
		String pass_cifrada;
		EnvioCorreo correo;
		String codigo;
		String from;
		String clave;
		String asunto;
		String mensaje;
		String to;

		response.setContentType("text/plain");
		out = response.getWriter();
		session = request.getSession();
		email = request.getParameter("email");
		password = request.getParameter("password");
		usuario = request.getParameter("usuario");

		comprador = new Comprador();
		empresa = new Empresa();

		if (!usuario.equals("Cliente") && !usuario.equals("Empresa")) {
			out.print("{ \"error\" : \"Debes seleccionar un tipo de cliente v??lido : Cliente o Empresa\" }");
		}

		if (usuario.equals("Cliente")) {

			if (comprador.leer("email", email)) {

				pass_cifrada = Cifrado.cifrado(password);

				if (comprador.getPassword().equals(pass_cifrada)) {

					if (comprador.isVerificado().equals("F")) {

						codigo = Cifrado.generarCodigo(); // cambiar cuenta
						
						asunto = "C??digo de verificaci??n";
						mensaje = "El c??digo es: " + codigo;
						to = email;

						correo = new EnvioCorreo();
						if (correo.enviar(to, asunto, mensaje)) {
							System.out.println("Todo correcto");
						}
						//  correo.enviar(from, clave, to, asunto, mensaje);
					//	correo.enviar(from, clave, to, asunto, mensaje);
						session.setAttribute("email-verificacion", email);
						session.setAttribute("codigo-verificacion", codigo);
						session.setAttribute("tipo_usuario", "Cliente");
						
						System.out.println(codigo);
						out.print("{ \"ok\" : 1 }");

					} else {
						session.setAttribute("tipo_usuario", "Cliente");
						session.setAttribute("usuario", comprador);
						out.print("{ \"ok\" : 2 ,\"url\" : \"/a20-denrbae_proyecto_final/inicio\"}");
					}

				} else {
					out.print("{ \"error\" : \"Los datos son err??neos\" }");
				}

			} else {
				out.print("{ \"error\" : \"Los datos son err??neos\" }");
			}

		} else if (usuario.equals("Empresa")) {
			if (empresa.leer("email", email)) {

				pass_cifrada = Cifrado.cifrado(password);

				if (empresa.getPassword().equals(pass_cifrada)) {

					if (empresa.isVerificado().equals("F")) {

						codigo = Cifrado.generarCodigo(); // cambiar cuenta
						
					
						asunto = "C??digo de verificaci??n";
						mensaje = "El c??digo es: " + codigo;
						to = email;

						correo = new EnvioCorreo();

						correo = new EnvioCorreo();
						if (correo.enviar(to, asunto, mensaje)) {
							System.out.println("Todo correcto");
						}
						// correo.enviar(from, clave, to, asunto, mensaje);
						//correo.enviar(from, clave, to, asunto, mensaje);
						session.setAttribute("email-verificacion", email);
						session.setAttribute("codigo-verificacion", codigo);
						session.setAttribute("tipo_usuario", "Empresa");
						System.out.println(codigo);
						out.print("{ \"ok\" : 1 }");

					} else {
						session.setAttribute("tipo_usuario", "Empresa");
						session.setAttribute("usuario", empresa);
						out.print("{ \"ok\" : 2 ,\"url\" : \"/a20-denrbae_proyecto_final/resumen\"  }");
					}

				} else {
					out.print("{ \"error\" : \"Los datos son err??neos\" }");
				}

			} else {
				out.print("{ \"error\" : \"Los datos son err??neos\" }");
			}
		}

	}

}
