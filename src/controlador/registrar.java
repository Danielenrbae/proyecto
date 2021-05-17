package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelos.Comprador;
import modelos.Empresa;

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
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.sendRedirect("hello");
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
		String pass_cifrada;
		Comprador comprador;
		Empresa empresa;
		String user;

		PrintWriter out;
		response.setContentType("text/plain");
		out = response.getWriter();

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
					out.print("{ \"error\" : \"El correo electrónico ya esta en uso\" }");
				} else {
						
				}

			} else if (user.equals("Empresa")) {
				System.out.println(empresa.leer("email", email));
				
				if (empresa.leer("email", email)) {
					out.print("{ \"error\" : \"El correo electrónico ya esta en uso\" }");
				} else {
					
					Empresa empr;
					empr = new Empresa(0,nombre,0,password,null,null,null,null,false,email);
					
					//if(empr.insertar()) System.out.println("adios");
					
					
				}

			}
		}else {
			out.print("{ \"error\" : \"Las contraseñas no coinciden.\" }");
		}

	}

	public String cifrado(String password) {
		String password_cifrada;
		MessageDigest md = null;
		byte[] digest = null;

		try {
			md = MessageDigest.getInstance("SHA-256");
			md.update(password.getBytes());
			digest = md.digest();

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		StringBuffer sb;
		sb = new StringBuffer();

		for (byte b : digest) {
			sb.append(String.format("%02x", b));
		}

		password_cifrada = sb.toString();

		return password_cifrada;
	}

}
