package controlador;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelos.Categoria;
import modelos.Producto;

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
		int page;
		Producto producto , aux_producto;
		Producto[] productos;
		int totalProductos;
		int res = 0;

		
		
		session = request.getSession();
		categoria = new Categoria();
		salir = false;
		contador = 1;
		categorias= new String[MAX_SIZE];
		page = 1;
		producto = new Producto();
		productos = new Producto[MAX_SIZE];
		
		session.setAttribute("info_product",null);
		
		if (session.isNew()) {
			session.setAttribute("usuario", null);
			session.setAttribute("email-verificacion", null);
			session.setAttribute("codigo-verificacion", null);
			session.setAttribute("tipo_usuario", null);
			session.setAttribute("categoriasDisponibles", null);
		}
		
		if(request.getParameter("page") != null){
			page= Integer.parseInt(request.getParameter("page"));
			session.setAttribute("pageSession", page);
		}else {
			session.setAttribute("pageSession", 1);
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
		contador = 1;
		salir = false;
		if (producto.leer("", "", false, true, false,page)) {
			
			aux_producto = new Producto();
			
			aux_producto.setId_producto(producto.getId_producto());
			aux_producto.setNombre(producto.getNombre());										
			aux_producto.setPrecio(producto.getPrecio());
			aux_producto.setFoto(producto.getFoto());
			
			
			productos[0] = aux_producto;
			
			while(!salir) {
				if (producto.leersiguiente()) {
					aux_producto = new Producto();
					
					aux_producto.setId_producto(producto.getId_producto());
					aux_producto.setNombre(producto.getNombre());										
					aux_producto.setPrecio(producto.getPrecio());
					aux_producto.setFoto(producto.getFoto());

					productos[contador] = aux_producto;
					contador++;
				}else {
					salir = true;
				}
				
			}
			
		}
	
		session.setAttribute("explora_productos", productos);
		
	
		
		if(producto.leer("", "", false, false, true, 0)) {
			totalProductos = producto.getNumeroTotal();
			//totalProductos = 53; //prueba para mostrar 3 paginaciones
			res = (totalProductos / 25);
			
			
			if(totalProductos % 25 != 0 ) {
				res = res +1 ;
			}
			session.setAttribute("paginas", res);
		}
	
		if(page > res) {
//			llevar al error 404 
			request.getRequestDispatcher("WEB-INF/modules/style-guide/error404.jsp").forward(request, response);
		}else {
			request.getRequestDispatcher("WEB-INF/modules/style-guide/Explorar.jsp").forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
