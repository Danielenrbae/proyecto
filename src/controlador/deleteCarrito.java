package controlador;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelos.Carpro;
import modelos.Comprador;

/**
 * Servlet implementation class deleteCarrito
 */
@WebServlet("/deleteCarrito")
public class deleteCarrito extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	HttpSession session;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id_carrito;
		int id_producto;
		Comprador usuario;
		Carpro carpro;
		String tipo;
		int cantidad;
		
		tipo = null;
		cantidad = 0;
		id_carrito= 0;
		id_producto= 0;

		session = request.getSession();
		
		if (request.getParameter("producto") != null) {
			id_producto = Integer.parseInt(request.getParameter("producto"));
		}
		
		if (session.getAttribute("id_carrito") != null) {
			id_carrito = (int) session.getAttribute("id_carrito");
		}
		
		if (session.getAttribute("tipo_usuario") != null) {
			tipo = (String) session.getAttribute("tipo_usuario") ;
		}
		
		
		if (tipo == null || tipo.equals("Empresa")) {
			request.getRequestDispatcher("WEB-INF/modules/style-guide/error404.jsp").forward(request, response);
		}else {
			
			carpro = new Carpro();
			
			if (carpro.delete("id_carrito", id_carrito, "id_producto", id_producto)) {
				response.sendRedirect("carrito");
			}else {
				request.getRequestDispatcher("WEB-INF/modules/style-guide/error404.jsp").forward(request, response);
			}
		}
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
