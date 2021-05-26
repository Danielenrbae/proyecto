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
 * Servlet implementation class explorar
 */
@WebServlet("/explorar")
public class explorar extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
		HttpSession session;
		 final int MAX_SIZE = 25;


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Categoria categoria;
		String[] categorias;
		boolean salir;
		int contador;
		
		session = request.getSession();
		categoria = new Categoria();
		salir = false;
		contador = 1;
		categorias= new String[MAX_SIZE];
		
		if (session.isNew()) {
			session.setAttribute("usuario", null);
			session.setAttribute("email-verificacion", null);
			session.setAttribute("codigo-verificacion", null);
			session.setAttribute("tipo_usuario", null);
			session.setAttribute("categoriasDisponibles", null);
		}
		//GET CATEGORIAS
		
		
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
			session.setAttribute("categoriasDisponibles", categorias);
		}
		
		//GET PRODUCTOS CON PAGINACION
		
		
		request.getRequestDispatcher("WEB-INF/modules/style-guide/Explorar.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
