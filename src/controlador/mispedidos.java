package controlador;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelos.Comprador;
import modelos.Pedido;
import modelos.Proped;

/**
 * Servlet implementation class mispedidos
 */
@WebServlet("/mispedidos")
public class mispedidos extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	HttpSession session;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String tipo;
		Comprador usuario;
		boolean isIniciado;
		Pedido[] pedidos ;
		Pedido pedido, aux_pedido; 
		int contador;
		boolean salir;
		Proped producto , aux_producto;
		ArrayList< Proped> listaProductos;
		
			
		pedido = null;
		isIniciado= false;
		aux_pedido= null;
		usuario = null;
		session = request.getSession();
		pedidos = new Pedido[25];
		contador = 0;
		salir = false;
		producto= null;
		aux_producto = null;
		
		
		listaProductos = new ArrayList<Proped>();
		
		
		if (session.getAttribute("tipo_usuario") != null) {
			tipo = (String) session.getAttribute("tipo_usuario");
			
			if (tipo.equals("Cliente")) {
				usuario = (Comprador) session.getAttribute("usuario");
				isIniciado= true;
			} 
		}
		
		
		if (isIniciado) {
			pedido = new Pedido();
			
			if (pedido.leer( usuario.getEmail(), false)) {
				aux_pedido = new Pedido();

				aux_pedido.setId_pedido(pedido.getId_pedido());
				aux_pedido.setEstado(pedido.getEstado());
				aux_pedido.setFecha(pedido.getFecha());
				aux_pedido.setId_comprador(pedido.getId_comprador());
				aux_pedido.setImporte(pedido.getImporte());
				aux_pedido.setId_empresa(pedido.getId_empresa());
				aux_pedido.setNombreEmpresa(pedido.getNombreEmpresa());
				
				
				pedidos[contador]  = aux_pedido;
				contador++;
				
				while(!salir) {
					
					if (pedido.leerSiguiente(true) && contador < 25) {
						
						aux_pedido = new Pedido();

						aux_pedido.setId_pedido(pedido.getId_pedido());
						aux_pedido.setEstado(pedido.getEstado());
						aux_pedido.setFecha(pedido.getFecha());
						aux_pedido.setId_comprador(pedido.getId_comprador());
						aux_pedido.setImporte(pedido.getImporte());
						aux_pedido.setId_empresa(pedido.getId_empresa());
						aux_pedido.setNombreEmpresa(pedido.getNombreEmpresa());
						
						
						pedidos[contador]  = aux_pedido;
						contador++;
					}else {
						salir = true;
					}
					
				}
				
			}
			
			contador = 0;
			salir = false;
			
			while(!salir) {
				
				if (pedidos[contador] != null ) {
					
					int id_pedido;
					
					id_pedido = pedidos[contador].getId_pedido();
					producto = new Proped();
					if (producto.leer(id_pedido, usuario.getEmail())) {
					
						aux_producto = new Proped();
						
						aux_producto.setCantidad(producto.getCantidad());
						aux_producto.setId_comprador(producto.getId_comprador());
						aux_producto.setId_pedido(producto.getId_pedido());
						aux_producto.setNombre(producto.getNombre());
						aux_producto.setPrecioUnidad(producto.getPrecioUnidad());
						aux_producto.setId_producto(producto.getId_producto());
						
						listaProductos.add(aux_producto);
						
						while (producto.leerSiguiente()) {
							
							aux_producto = new Proped();
							
							aux_producto.setCantidad(producto.getCantidad());
							aux_producto.setId_comprador(producto.getId_comprador());
							aux_producto.setId_pedido(producto.getId_pedido());
							aux_producto.setNombre(producto.getNombre());
							aux_producto.setPrecioUnidad(producto.getPrecioUnidad());
							aux_producto.setId_producto(producto.getId_producto());
							
							listaProductos.add( aux_producto);
						}
						
					}
					contador++;
	
				}else
				{
					salir = true;
				}
				
			}
		
			session.setAttribute("misPedidos", pedidos);
			session.setAttribute("misPedidosPro", listaProductos);
		
			
			request.getRequestDispatcher("WEB-INF/modules/style-guide/MisPedidos.jsp").forward(request, response);
		}else {
			response.sendRedirect("inicio");
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
