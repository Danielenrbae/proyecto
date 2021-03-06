package controlador;

import java.io.IOException;
import java.io.OutputStream;
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
 * Servlet implementation class bajarFoto
 */
@WebServlet("/bajarFoto")
public class bajarFoto extends HttpServlet {
	private static final long serialVersionUID = 1L;

	HttpSession session;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String tipo;
		Producto producto;
		String id_producto;
		byte[] foto;
		OutputStream out;

		session = request.getSession();

		tipo = (String) request.getParameter("param_img");		
		id_producto = request.getParameter("idproducto");
		foto = null;
		
	

			switch (tipo) {
			case "producto":

				producto = new Producto();
				
				if (producto.leer("id_producto", id_producto, true, false, false, 0)) {
					foto = producto.getFoto();
					
					if(foto != null) {							
						session.setAttribute("imagenP", foto);
						response.setContentType("image/png");
						out = response.getOutputStream();
						out.write(foto);
						out.flush();
						out.close();
					}
					
				}
								
				break;

			default:
				break;
			}
			
		


	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.sendRedirect("inicio");

	}

}
