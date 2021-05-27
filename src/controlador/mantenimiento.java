package controlador;

import java.io.IOException;
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
		Producto producto , aux_producto ;
		Empresa empresa;
		int contador;
		boolean salir;
		Producto[] productos;
		int sizeProductos;
		
		
		session = request.getSession();
		categorias = new String[MAX_SIZE];
		contador = 1;
		salir = false;
		productos = null;
		aux_producto = null;
		
		tipo = (String) session.getAttribute("tipo_usuario");
		
		if(tipo != null && !tipo.equals("Cliente")) {
			
			empresa = (Empresa) session.getAttribute("usuario");
			producto = new Producto();
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
			
			
//			encontrar todos los productos de la empresa
			if  (producto.leer("id_empresa", String.valueOf(empresa.getID_empresa()), false, false, true,0)) {
				sizeProductos = producto.getNumeroTotal();
				
				productos = new Producto[sizeProductos];
				
				if (producto.leer("id_empresa", String.valueOf(empresa.getID_empresa()) , false, false, false,0)) {
					contador = 1;
					
					
					aux_producto = new Producto();
					
					aux_producto.setId_producto(producto.getId_producto());
					aux_producto.setNombre(producto.getNombre());	
					
					if (categoria.leer("id_categoria", String.valueOf(producto.getId_categoria()), true)) {
						aux_producto.setCategoria(categoria.getNombre());
					}
					
					aux_producto.setPrecio(producto.getPrecio());
					
					productos[0] = aux_producto;
											
					while(contador < sizeProductos) {
						if (producto.leersiguiente()) {
							
							
							aux_producto = new Producto();
							
							aux_producto.setId_producto(producto.getId_producto());
							aux_producto.setNombre(producto.getNombre());
							
							if (categoria.leer("id_categoria", String.valueOf(producto.getId_categoria()), true)) {
								aux_producto.setCategoria(categoria.getNombre());
							}
							aux_producto.setPrecio(producto.getPrecio());
							
							
							productos[contador] = aux_producto;
							contador++;
						}
						
						 
					}

					producto.getCon().cerrarConexion();
					
						
				}
					
			}
			
			session.setAttribute("productos", productos);
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
