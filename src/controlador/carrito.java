package controlador;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelos.Carpro;
import modelos.Carrito;
import modelos.Comprador;

/**
 * Servlet implementation class carrito
 */
@WebServlet("/carrito")
public class carrito extends HttpServlet {
	private static final long serialVersionUID = 1L;

	HttpSession session;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Carrito carrito;
		Comprador usuario;
		Carpro carpro, aux_carpro;
		boolean salir;
		int contador;
		Carpro[] productos;
		final int MAX_SIZE = 25;
		double total_carrito;
		int total_cantidad;

		session = request.getSession();
		carrito = new Carrito();
		salir = false;
		contador = 0;
		productos = new Carpro[MAX_SIZE];
		total_carrito = 0;
		total_cantidad= 0 ;

		if (session.isNew()) {
			session.setAttribute("usuario", null);
			session.setAttribute("email-verificacion", null);
			session.setAttribute("codigo-verificacion", null);
			session.setAttribute("tipo_usuario", null);
			session.setAttribute("categoriasDisponibles", null);
		}

		if (session.getAttribute("usuario") != null) {
			usuario = (Comprador) session.getAttribute("usuario");
			//leo el carrito del usuario y sus productos
			if (carrito.leer("id_comprador", usuario.getEmail(), true)) {
				carpro = new Carpro();

				if (carpro.leer("id_carrito", carrito.getId_carrito(),"",0, false, true)) {
					aux_carpro = new Carpro();

					aux_carpro.setCantidad(carpro.getCantidad());
					aux_carpro.setId_carrito(carpro.getId_carrito());
					aux_carpro.setId_producto(carpro.getId_producto());

					aux_carpro.getProducto().setNombre(carpro.getProducto().getNombre());
					aux_carpro.getProducto().setPrecio(carpro.getProducto().getPrecio());
					aux_carpro.getProducto().setFoto(carpro.getProducto().getFoto());

					total_carrito += (aux_carpro.getProducto().getPrecio() * aux_carpro.getCantidad());
					total_cantidad +=  aux_carpro.getCantidad();
					productos[contador] = aux_carpro;
					contador++;
					while (!salir) {

						if (carpro.leerSiguiente(true)) {
							aux_carpro = new Carpro();

							aux_carpro.setCantidad(carpro.getCantidad());
							aux_carpro.setId_carrito(carpro.getId_carrito());
							aux_carpro.setId_producto(carpro.getId_producto());

							aux_carpro.getProducto().setNombre(carpro.getProducto().getNombre());
							aux_carpro.getProducto().setPrecio(carpro.getProducto().getPrecio());
							aux_carpro.getProducto().setFoto(carpro.getProducto().getFoto());

							
							total_carrito += (aux_carpro.getProducto().getPrecio() * aux_carpro.getCantidad());
							total_cantidad +=  aux_carpro.getCantidad();
							productos[contador] = aux_carpro;

							contador++;

						} else {
							salir = true;
						}

					}

					session.setAttribute("carrito", productos);
					session.setAttribute("id_carrito", carrito.getId_carrito());
					session.setAttribute("total_carrito", total_carrito);
					session.setAttribute("total_cantidad", total_cantidad);

				}else {
					session.setAttribute("carrito", null);
					session.setAttribute("id_carrito", carrito.getId_carrito());
					session.setAttribute("total_carrito", null);
					session.setAttribute("total_cantidad", null);
					
				}

			}

		}

		request.getRequestDispatcher("WEB-INF/modules/style-guide/Carrito.jsp").forward(request, response);
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
