package controlador;

import java.io.File;
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
		
		File file;
		
		String nombre;
		String descripcion;
		float precio;
		//foto
		
		double energia;
		double peso;
		double valorkj;
		double valorkcal;
		double proteinas;
		double hidratos;
		double fibra;
		double azucares;
		double sal;
		double grasas;
		double grasos_saturados;
		
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
				file = (File) session.getAttribute("file");
				
				producto = new Producto(nombre,descripcion,precio,id_empresa, id_categoria ,file );
				if (producto.insertar()) {
					
					if (producto.leer("nombre", producto.getNombre(), true, false, false,0)) {

						//coger la id del producto y coger los parametros de la informacion nutricional
						id_producto = producto.getId_producto();
						
						energia =  Double.parseDouble(request.getParameter("energia"));						
						peso = Double.parseDouble(request.getParameter("peso"));
						valorkj = Double.parseDouble(request.getParameter("valorkj"));
						valorkcal = Double.parseDouble(request.getParameter("valorkcal"));
						proteinas = Double.parseDouble(request.getParameter("proteinas"));
						hidratos = Double.parseDouble(request.getParameter("hidratos"));
						fibra = Double.parseDouble(request.getParameter("fibra"));
						azucares = Double.parseDouble(request.getParameter("azucares"));
						sal = Double.parseDouble(request.getParameter("sal"));
						grasas = Double.parseDouble(request.getParameter("grasas"));
						grasos_saturados = Double.parseDouble(request.getParameter("grasos_saturados"));
						
						info_nutri = new InformacionNutricional(energia, peso, valorkj, valorkcal, proteinas, hidratos, fibra, azucares, sal, grasas, grasos_saturados, id_producto);
						
						if (info_nutri.insertar()) {
														
					
								out.print("{ \"ok\" : 1 }");
							
							//response.sendRedirect("mantenimiento");
						}else {
							out.print("{ \"error\" : \"Algo ha salido mal, inténtelo de nuevo 1\" }");

						}
						
					}else {
						out.print("{ \"error\" : \"Algo ha salido mal, inténtelo de nuevo 2\" }");
					}					
				}else {
					out.print("{ \"error\" : \"Algo ha salido mal, inténtelo de nuevo 3\" }");
				}				
			}else {
				out.print("{ \"error\" : \"Algo ha salido mal, inténtelo de nuevo 4\" }");
			}
			
			
				
		}else {
			//response.sendRedirect("inicio");
		}
		
		
		
	}

}
