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
		Carrito carrito;
		int id_producto;
		Comprador comprador;
		String tipo;
		Carpro carpro;
		
		session = request.getSession();
		carrito = new Carrito();
		carpro = new Carpro();
		
		if(session.getAttribute("tipo_usuario") != null && !request.getParameter("id").isEmpty()) {
			
			tipo= (String) session.getAttribute("tipo_usuario");
			
			System.out.println(tipo);
			id_producto= Integer.parseInt(request.getParameter("id"));
			
			if (tipo.equals("Cliente")) { // TODO EL TIPO ESTA NULO , entra en el if de todas formas
				
				comprador = (Comprador) session.getAttribute("usuario");

				if (carrito.leer("id_comprador", comprador.getEmail(), true)) {
					
					//insertar el producto en la tabla relacion del carrito + producto
					carpro.setId_carrito(carrito.getId_carrito());
					carpro.setId_producto(id_producto);
					//TODO comprobar la cantidad de productos que inserta y hacer bucle
					if (carpro.insertar()) {
						response.sendRedirect("compraProducto?id="+id_producto);
					}else {
						request.getRequestDispatcher("WEB-INF/modules/style-guide/error404.jsp").forward(request, response);
					}
					
					
				}else {
					
					carrito.setId_comprador(comprador.getEmail());
					
					if (carrito.insertar()) {
						
						response.sendRedirect("addcarrito");
					}
				}
				
			}else {
				response.sendRedirect("inicio");
			}
			
		}else {
			System.out.println(request.getParameter("id"));
			System.out.println(session.getAttribute("tipo_usuario"));
			
			response.sendRedirect("inicio");
		}
		
	}

}
