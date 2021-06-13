package controlador;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelos.Carpro;
import modelos.Carrito;
import modelos.Comprador;
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

			if (info_nutri.leer("id_producto", id_producto, true)) {

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
		Carrito carrito;
		int id_producto;
		Comprador comprador;
		String tipo;
		Carpro carpro;
		PrintWriter out;
		int cantidad;

		session = request.getSession();
		carrito = new Carrito();
		carpro = new Carpro();
		response.setContentType("text/plain");
		out = response.getWriter();
		id_producto = 0;
		cantidad = 0;

		

		// SI EL USUARIO ES NULO QUE SALGA MENSAJE QUE DEBE INICIAR SESION
		if (session.getAttribute("tipo_usuario") != null) {
			tipo = (String) session.getAttribute("tipo_usuario");

			if (!request.getParameter("id").isEmpty()) {
				id_producto = Integer.parseInt(request.getParameter("id"));

				if (tipo.equals("Cliente")) {

					comprador = (Comprador) session.getAttribute("usuario");

					cantidad = Integer.parseInt(request.getParameter("cantidad"));

					if (cantidad != 0) {

						if (carrito.leer("id_comprador", comprador.getEmail(), true)) {

							if (carpro.leer("id_carrito", carrito.getId_carrito(), "id_producto" , id_producto, true, false)) {
								int cantidad_old;
								cantidad_old = carpro.getCantidad();
								
								int cantidad_nueva = cantidad_old + cantidad;
								if (carpro.update("cantidad" , cantidad_nueva , "id_producto" , id_producto , "id_carrito" , carrito.getId_carrito())) {
									out.print("{ \"ok\" : 1 , \"url\": \"/a20-denrbae_proyecto_final/explorar\"}");
								}else {
									out.print("{ \"error\" : \"Algo ha salido mal. Inténtelo de nuevo\"}");
								}
								
							}else {
								// insertar el producto en la tabla relacion del carrito + producto
								carpro.setId_carrito(carrito.getId_carrito());
								carpro.setId_producto(id_producto);
								carpro.setCantidad(cantidad);
								
								if (!carpro.insertar()) {
									out.print("{ \"ok\" : 1 , \"url\": \"/a20-denrbae_proyecto_final/explorar\"}");

								} else {

									out.print("{ \"error\" : \"Algo ha salido mal. Inténtelo de nuevo\"}");
								}
							}

						}

					}else {
						out.print("{ \"error\" : \"La cantidad no debe ser 0\"}");

					}

				} else {
					out.print(
							"{ \"error\" : \"Debes iniciar sesión como cliente para poder añadir un producto al carrito\" }");
				}

			} else {
				out.print("{ \"error\" : \"Algo ha salido mal. Inténtelo de nuevo\"}");
			}

		} else {
			out.print("{ \"error\" : \"Debes iniciar sesión para poder añadir un producto al carrito\" }");
		}

	}

}
