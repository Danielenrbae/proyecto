package controlador;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelos.Categoria;

/**
 * Servlet implementation class mantenimiento
 */
@WebServlet("/mantenimiento")
public class mantenimiento extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 HttpSession session;
 final int MAX_SIZE = 25;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String tipo;
		Categoria categoria;
		String[] categorias;
		int contador;
		boolean salir;
		
		session = request.getSession();
		categorias = new String[MAX_SIZE];
		contador = 1;
		salir = false;
		
		tipo = (String) session.getAttribute("tipo_usuario");
		
		if(tipo != null && !tipo.equals("Cliente")) {
			
			//consultar todas las categorias
			categoria = new Categoria();
			
			if (categoria.leer("", "", false)) {
				
				categorias[0] = categoria.getNombre();
				
				while (!salir && contador < MAX_SIZE) {
					if (categoria.leersiguiente()) {
						categorias[contador] = categoria.getNombre();
						contador++;
					}else {
						salir = true;
					}
				}
								
			}
			
			session.setAttribute("categorias", categorias);
						
			request.getRequestDispatcher("/WEB-INF/modules/style-guide/Mantenimiento.jsp").forward(request, response);
			
		}else {
			response.sendRedirect("inicio");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
