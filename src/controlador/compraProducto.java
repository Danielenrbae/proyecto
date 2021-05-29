package controlador;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelos.InformacionNutricional;
import modelos.Producto;

/**
 * Servlet implementation class compraProducto
 */
@WebServlet("/compraProducto")
public class compraProducto extends HttpServlet {
	private static final long serialVersionUID = 1L;

	HttpSession session;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Producto producto;
		int id_producto;
		InformacionNutricional info_nutri;

		session = request.getSession();
		producto = new Producto();
		id_producto = 0;

		if (request.getParameter("id") == null) {
			request.getRequestDispatcher("WEB-INF/modules/style-guide/error404.jsp").forward(request, response);
		} else {
			id_producto = Integer.parseInt(request.getParameter("id"));
		}

		if (producto.leer("id_producto", String.valueOf(id_producto), true, false, false, 0)) {
			info_nutri = new InformacionNutricional();

			if(info_nutri.leer("id_producto", id_producto, true)) {
				
				session.setAttribute("info_product", info_nutri);
							
			}
			
			session.setAttribute("product", producto);
			request.getRequestDispatcher("WEB-INF/modules/style-guide/Producto.jsp").forward(request, response);	

		} else {
			request.getRequestDispatcher("WEB-INF/modules/style-guide/error404.jsp").forward(request, response);

		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
