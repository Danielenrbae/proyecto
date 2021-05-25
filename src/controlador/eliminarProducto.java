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
import modelos.Producto;

/**
 * Servlet implementation class eliminarProducto
 */
@WebServlet("/eliminarProducto")
public class eliminarProducto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	HttpSession session;
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id_producto;
		Producto producto;
		String tipo;
		PrintWriter out;
		Categoria categoria;
		
		session = request.getSession();

		response.setContentType("text/plain");
		out = response.getWriter();
		
		producto = new Producto();
		
		tipo = (String) session.getAttribute("tipo_usuario");
		id_producto = Integer.parseInt(request.getParameter("idproducto"));

		
		if(tipo != null && !tipo.equals("Cliente")) {
						
			
			
			if (producto.leer("id_producto", String.valueOf(id_producto), true, false, false)) {
				 categoria  = new Categoria();
				 
				 if (categoria.leer("id_categoria", String.valueOf(producto.getId_categoria()), true)) {
					producto.setCategoria(categoria.getNombre());
					session.setAttribute("delete_producto", producto);
					out.print("{ \"ok\" : 1 }");

				}else {
					out.print("{ \"error\" : \"Intentelo de nuevo\" }");
				}

			}else {
				out.print("{ \"error\" : \"Intentelo de nuevo\" }");
			}
		}else {
			out.print("{ \"error\" : \"Intentelo de redirect\" }");
			
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String tipo;
		PrintWriter out;
		Producto producto;
		
		
		session = request.getSession();
		response.setContentType("text/plain");
		out = response.getWriter();
		
		tipo = (String) session.getAttribute("tipo_usuario");
		producto = (Producto) session.getAttribute("delete_producto"); 
		
		if(tipo != null && !tipo.equals("Cliente")) {
							
			if (producto.delete("id_producto", String.valueOf(producto.getId_producto()), "id_empresa", String.valueOf(producto.getId_empresa()))) {
				out.print("{ \"ok\" : 1 }");
				session.setAttribute("delete_producto", null);
			}else {
				out.print("{ \"error\" : \"No existe el producto que intenta borrar\" }");
			}
			

		}else {
			out.print("{ \"error\" : \"Error! Intentelo de nuevo\" }");
				
		}

	}

}
