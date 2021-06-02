package controlador;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelos.Categoria;
import modelos.Empresa;
import modelos.Producto;

/**
 * Servlet implementation class actualizarProducto
 */
@WebServlet("/actualizarProducto")
public class actualizarProducto extends HttpServlet {
	private static final long serialVersionUID = 1L;

	HttpSession session;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String tipo;
		int id_producto;
		Empresa empresa;
		Producto producto;
		PrintWriter out;
		Categoria categoria;

		id_producto = 0;
		empresa = null;
		producto = new Producto();
		categoria = new Categoria();
		session = request.getSession();

		if (!request.getParameter("id").isEmpty()) {
			id_producto = Integer.parseInt(request.getParameter("id"));
		}

		if (session.getAttribute("tipo_usuario") != null) {
			tipo = (String) session.getAttribute("tipo_usuario");

			if (tipo.equals("Empresa")) {
				empresa = (Empresa) session.getAttribute("usuario");

				if (producto.leer("id_producto", String.valueOf(id_producto), true, false, false, 0)) {

					if (producto.getId_empresa() == empresa.getID_empresa()) {
					
						if (categoria.leer("id_categoria", String.valueOf(producto.getId_categoria()), true)) {
							producto.setCategoria(categoria.getNombre());
						}
						session.setAttribute("producto_toUpdate", producto);
						response.sendRedirect("mantenimiento");
					}else {
						request.getRequestDispatcher("WEB-INF/modules/style-guide/error404.jsp").forward(request, response);
					}

				} else {
					request.getRequestDispatcher("WEB-INF/modules/style-guide/error404.jsp").forward(request, response);
				}

			}else {
				request.getRequestDispatcher("WEB-INF/modules/style-guide/error404.jsp").forward(request, response);
			}
		}else {
			request.getRequestDispatcher("WEB-INF/modules/style-guide/error404.jsp").forward(request, response);
		}


		

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
