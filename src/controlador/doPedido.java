package controlador;

import java.io.IOException;
import java.util.Calendar;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelos.Carpro;
import modelos.Carrito;
import modelos.Comprador;
import modelos.Factura;
import modelos.Pedido;
import modelos.Producto;
import modelos.Proped;

/**
 * Servlet implementation class doPedido
 */
@WebServlet("/doPedido")
public class doPedido extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	HttpSession session;

	
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String tipo_usuario;
		Comprador usuario;
		int id_carrito;
		Carpro[] productosCarrito;
		Carpro aux_carpro;
		Carrito carrito;
		Carpro carpro;
		int contador;
		boolean salir;
		Set<Integer> conjuntoEmpresas;
		Producto producto;
		Pedido pedido;
		Calendar calendar;
		String fecha;
		Proped proped;
		boolean isInsertado;
	
		Factura factura;
		double importe = 0;
		
		carpro = new Carpro();
		tipo_usuario = null;
		id_carrito = 0;
		aux_carpro = null;
		contador = 0;
		isInsertado= false;
		salir = false;
		carrito = new Carrito();
		conjuntoEmpresas = new TreeSet<Integer>();
		producto = new Producto();

		
		
		productosCarrito = new Carpro[25];
		session = request.getSession();
		
		if (session.getAttribute("tipo_usuario") != null) {
			tipo_usuario = (String) session.getAttribute("tipo_usuario");
		}
		
		if (tipo_usuario.equals("Cliente")) {
			usuario = (Comprador) session.getAttribute("usuario");
			
			//get la id de carrito
			
			
			if (carrito.leer("id_comprador", usuario.getEmail(), true)) {
				
				id_carrito = carrito.getId_carrito();	
			}
			
			//añadir recoger todos los productos del carrito
			if (carpro.leer("id_carrito", id_carrito,"",0, false, true)) {
				aux_carpro = new Carpro();

				aux_carpro.setCantidad(carpro.getCantidad());
				aux_carpro.setId_carrito(carpro.getId_carrito());
				aux_carpro.setId_producto(carpro.getId_producto());
				
				aux_carpro.getProducto().setId_empresa(carpro.getProducto().getId_empresa());
				aux_carpro.getProducto().setPrecio(carpro.getProducto().getPrecio());


				if (producto.leer("id_producto", String.valueOf(aux_carpro.getId_producto()), true, false, false, 0)) {
					conjuntoEmpresas.add(producto.getId_empresa());
				}
				
				
				productosCarrito[contador] = aux_carpro;
				contador++;
				while (!salir) {
				
					if (carpro.leerSiguiente(true)) {
						aux_carpro = new Carpro();

						aux_carpro.setCantidad(carpro.getCantidad());
						aux_carpro.setId_carrito(carpro.getId_carrito());
						aux_carpro.setId_producto(carpro.getId_producto());
						
						aux_carpro.getProducto().setId_empresa(carpro.getProducto().getId_empresa());
						aux_carpro.getProducto().setPrecio(carpro.getProducto().getPrecio());


						
						if (producto.leer("id_producto", String.valueOf(aux_carpro.getId_producto()), true, false, false, 0)) {
							conjuntoEmpresas.add(producto.getId_empresa());
						}

						productosCarrito[contador] = aux_carpro;

						contador++;

					} else {
						salir = true;
					}

				}
			}
			
			//conseguir la id de la empresa del producto
			
			
			//generar el pedido
			calendar = Calendar.getInstance();
			fecha = calendar.get(Calendar.DATE)+"/"+Calendar.MONTH+"/"+Calendar.YEAR;
			double total_carrito;
			total_carrito= 0 ;
			for(int item : conjuntoEmpresas) {

				contador = 0;
				salir = false;
				//generar el pedido
			
				pedido = new Pedido();
				
				pedido.setEstado("P");
				pedido.setId_comprador(usuario.getEmail());
				pedido.setId_empresa(item);
				pedido.setFecha(fecha);
				
				if (!pedido.insertar()) {
					
					//insertar los productos de los pedidos de esa empresa
					
					if (pedido.leer("id_comprador", usuario.getEmail(), "fecha", fecha, "id_empresa", pedido.getId_empresa(), true)) {
					
						
						
						
						while (!salir) {
							proped = new Proped();
	
							if (productosCarrito[contador] != null) {
							

								if (productosCarrito[contador].getProducto().getId_empresa() == item) {
									
									proped.setId_pedido(pedido.getId_pedido());
									proped.setId_producto(productosCarrito[contador].getId_producto());
									proped.setCantidad(productosCarrito[contador].getCantidad());
										
									//System.out.println(productosCarrito[contador].getProducto().getPrecio()  + contador); 
									total_carrito+= (productosCarrito[contador].getProducto().getPrecio() * productosCarrito[contador].getCantidad()) ;
									//System.out.println(total_carrito);
								
									if (!proped.insertar()) {
										isInsertado = true;
										
									}
								}
								contador++;
								
							}else {
								salir = true;
								System.out.println(productosCarrito[contador-1].getProducto().getPrecio());

							
							}
							
							
						}
						
						factura = new Factura();
						
					
						factura.setFecha(fecha);
						factura.setId_pedido(pedido.getId_pedido());
					
						System.out.println("----- "+ item);
					
//						if (factura.insertar()) {
//							System.out.println("todo correcto");
//						}
						
						
					}else {
						System.out.println("no");
					}
				}
				
				
		
				
			}
			
			//borrar datos del carrito al completarse el pedido
//			
//			if (isInsertado) {
//				
//				if (carpro.delete("id_carrito", id_carrito, "", 0)) {
//			
//				}
//				
//			}

	
	
			
		}else {
			request.getRequestDispatcher("WEB-INF/modules/style-guide/error404.jsp").forward(request, response);
		}
		
		
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}