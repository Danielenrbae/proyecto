package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

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

		//comprobar tipo usuario
		if (session.getAttribute("tipo_usuario") != null) {
			tipo = (String) session.getAttribute("tipo_usuario");

			if (tipo.equals("Empresa")) {
				empresa = (Empresa) session.getAttribute("usuario");

				//leer si existe el producto
				if (producto.leer("id_producto", String.valueOf(id_producto), true, false, false, 0)) {
					//comprobar si es la misma empresa
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

		
		String id_producto;
		String nombre;
		double precio;
		String descripcion;
		String categoria;
		Empresa empresa;
		Producto producto;
		
		
		
		session = request.getSession();
		producto = null;
		nombre = "";
		descripcion ="";
		precio = 0;
		
		if (session.getAttribute("usuario") != null) {
			empresa = (Empresa) session.getAttribute("usuario");
		}
		
		Map<String, String[]> params = request.getParameterMap();
		
		for (String key : params.keySet()) {
		   if (!params.get(key)[0].isEmpty()) {
			   
			   if (key.equals("id_producto")) id_producto= params.get(key)[0] ;
			   if (key.equals("nombre")) nombre= params.get(key)[0] ;
			   if (key.equals("categoria")) categoria= params.get(key)[0] ;
			   if (key.equals("precio")) precio= Double.parseDouble(params.get(key)[0] );
			   if (key.equals("descripcion")) descripcion= params.get(key)[0] ;
			   			   
				producto = (Producto) session.getAttribute("producto_toUpdate");

			   
			}else {
				session.setAttribute("error_Update", "Hay campos vacios en el formulario");
				response.sendRedirect("mantenimiento#formUpdate");
			}
		}
		
		if (producto.update("id_producto", producto.getId_producto(), nombre, descripcion, precio)) {
			
			if (producto.leer("id_producto", String.valueOf(producto.getId_producto()), true, false, false, 0)) {
				session.setAttribute("producto_toUpdate", producto);
			}
			
			session.setAttribute("error_Update", "");
			response.sendRedirect("mantenimiento#formUpdate");
		}else {
			session.setAttribute("error_Update", "No se ha podido guardar los cambios. Inténtelo de nuevo");
			response.sendRedirect("mantenimiento#formUpdate");

		}
		
	}

}
