package controlador;

import java.io.IOException;
import java.io.PrintWriter;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelos.Carrito;
import modelos.Comprador;
import modelos.Empresa;
import utils.Cifrado;

/**
 * Servlet implementation class registrar
 */
@WebServlet("/registrar")
public class registrar extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	
	HttpSession session;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
		session = request.getSession();
		
		if (session.isNew()) {
			session.setAttribute("usuario", null);
			session.setAttribute("email-verificacion", null);
			session.setAttribute("codigo-verificacion", null);
			session.setAttribute("tipo_usuario", null);

		}
		
		response.sendRedirect("inicio");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String nombre;
		String email;
		String password;
		String confirm_password;
		
		Comprador comprador;
		Empresa empresa;
		String user;
		
		Carrito carrito;

		PrintWriter out;
		response.setContentType("text/plain");
		out = response.getWriter();
		session = request.getSession();

		nombre = request.getParameter("nombre");
		email = request.getParameter("email");
		password = request.getParameter("password");
		confirm_password = request.getParameter("confirm_password");
		user = request.getParameter("usuario");
		comprador = new Comprador();
		empresa = new Empresa();

		if (password.equals(confirm_password)) {

			if (!user.equals("Cliente") && !user.equals("Empresa")) {
				out.print("{ \"error\" : \"Debes seleccionar un tipo de cliente válido : Cliente o Empresa\" }");
			}

			if (user.equals("Cliente")) {

				if (comprador.leer("email", email)) {
					
					
					comprador.leersiguiente();
					out.print("{ \"error\" : \"El correo electrónico ya esta en uso\" }");
					
				
				} else {
					Comprador compr;
					String pass_cifrada;
					
					pass_cifrada = Cifrado.cifrado(password);
					compr = new Comprador(email,nombre,0,pass_cifrada,null, null,null,null,"F");
					
					if (!compr.insertar()) {
						carrito = new Carrito();
						carrito.setId_comprador(compr.getEmail());
						
						if (!carrito.insertar()) {
							out.print("{ \"ok\" : 1}");
						}																	
					}
				}

			} else if (user.equals("Empresa")) {
				

				if (empresa.leer("email", email)) {
					empresa.leersiguiente();
					out.print("{ \"error\" : \"El correo electrónico ya esta en uso\" }");
				} else {
					Empresa empr;
					String pass_cifrada;
					
					pass_cifrada = Cifrado.cifrado(password);
					
					empr = new Empresa(nombre, 0, pass_cifrada, null, null, null, null, "F", email);

					if (!empr.insertar()) out.print("{ \"ok\" : 1 }");
						
			
				}

			}
		} else {
			out.print("{ \"error\" : \"Las contraseñas no coinciden.\" }");
		}

	}



}
