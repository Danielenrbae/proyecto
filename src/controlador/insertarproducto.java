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
import modelos.Empresa;
import modelos.InformacionNutricional;
import modelos.Producto;

/**
 * Servlet implementation class insertarproducto
 */
@WebServlet("/insertarproducto")
public class insertarproducto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	HttpSession session;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.sendRedirect("resumen");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String nombre;
		String descripcion;
		float precio;
		//foto
		
		int energia;
		int peso;
		int valorkj;
		int valorkcal;
		int proteinas;
		int hidratos;
		int fibra;
		int azucares;
		int sal;
		int grasas;
		int grasos_saturados;
		
		int id_empresa;
		int id_categoria;
		String name_categoria;
		int id_producto;
		
		
		
		Empresa empresa;
		Categoria categoria;
		InformacionNutricional info_nutri;
		Producto producto;
		PrintWriter out;
		
		String tipo;
		
		session = request.getSession();
		response.setContentType("text/plain");
		out = response.getWriter();
		
		tipo = (String) session.getAttribute("tipo_usuario");
		
		if (tipo.equals("Empresa") && tipo != null) {
			
			
			empresa = (Empresa) session.getAttribute("usuario");
			id_empresa = empresa.getID_empresa();
			
			//seleccionar la categoria
			categoria = new Categoria();
			name_categoria = request.getParameter("categoria");
			
			if (categoria.leer("nombre", name_categoria, true)) {
				id_categoria = categoria.getId_categoria();
				//coger parametros del producto
				nombre = (String) request.getParameter("nombre");
				descripcion = (String) request.getParameter("descripcion");
				precio = Float.parseFloat(request.getParameter("precio"));
				//foto
				
				producto = new Producto(nombre,descripcion,precio, null,id_empresa, id_categoria);
				
				if (producto.insertar()) {
					
					if (producto.leer("nombre", nombre, true)) {
						
						//coger la id del producto y coger los parametros de la informacion nutricional
						id_producto = producto.getId_producto();
						
						energia = Integer.parseInt(request.getParameter("energia"));
						peso = Integer.parseInt(request.getParameter("peso"));
						valorkj = Integer.parseInt(request.getParameter("valorkj"));
						valorkcal = Integer.parseInt(request.getParameter("valorkcal"));
						proteinas = Integer.parseInt(request.getParameter("proteinas"));
						hidratos = Integer.parseInt(request.getParameter("hidratos"));
						fibra = Integer.parseInt(request.getParameter("fibra"));
						azucares = Integer.parseInt(request.getParameter("azucares"));
						sal = Integer.parseInt(request.getParameter("sal"));
						grasas = Integer.parseInt(request.getParameter("grasas"));
						grasos_saturados = Integer.parseInt(request.getParameter("grasos_saturados"));
						
						info_nutri = new InformacionNutricional(energia, peso, valorkj, valorkcal, proteinas, hidratos, fibra, azucares, sal, grasas, grasos_saturados, id_producto);
						
						if (info_nutri.insertar()) {
							out.print("{ \"ok\" : 1 }");
							//response.sendRedirect("mantenimiento");
						}else {
							out.print("{ \"error\" : \"Algo ha salido mal, inténtelo de nuevo\" }");

						}
						
					}else {
						out.print("{ \"error\" : \"Algo ha salido mal, inténtelo de nuevo\" }");
					}					
				}else {
					out.print("{ \"error\" : \"Algo ha salido mal, inténtelo de nuevo\" }");
				}				
			}else {
				out.print("{ \"error\" : \"Algo ha salido mal, inténtelo de nuevo\" }");
			}
			
			
				
		}else {
			//response.sendRedirect("inicio");
		}
		
		
		
	}

}
