package controlador;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelos.Empresa;
import modelos.Pedido;

/**
 * Servlet implementation class resumen
 */
@WebServlet("/resumen")
public class resumen extends HttpServlet {
	private static final long serialVersionUID = 1L;

	HttpSession session;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String tipo;
		Empresa usuario ;
		Pedido pedido , aux_pedido;
		ArrayList<Pedido> listaPedidos;
		
		session = request.getSession();
		pedido = new Pedido();
		tipo = (String) session.getAttribute("tipo_usuario");
		usuario = null;
		listaPedidos= new ArrayList<>();
		
		if (session.getAttribute("usuario") != null) {
			usuario = (Empresa) session.getAttribute("usuario");
		}

		if (tipo != null && !tipo.equals("Cliente")) {
			
			if (pedido.leer("id_empresa", usuario.getID_empresa(), false)) {
				
				aux_pedido = new Pedido();
				
				aux_pedido.setEstado(pedido.getEstado());
				aux_pedido.setFecha(pedido.getFecha());
				aux_pedido.setId_comprador(pedido.getId_comprador());
				aux_pedido.setId_empresa(pedido.getId_empresa());
				aux_pedido.setId_pedido(pedido.getId_pedido());
				
				listaPedidos.add(aux_pedido);
				
				if (pedido.leerSiguiente()) {
					
					aux_pedido = new Pedido();
					
					aux_pedido.setEstado(pedido.getEstado());
					aux_pedido.setFecha(pedido.getFecha());
					aux_pedido.setId_comprador(pedido.getId_comprador());
					aux_pedido.setId_empresa(pedido.getId_empresa());
					aux_pedido.setId_pedido(pedido.getId_pedido());
					
					listaPedidos.add(aux_pedido);
				}
			}
			
			session.setAttribute("pedidos_empresa", listaPedidos);
			request.getRequestDispatcher("/WEB-INF/modules/style-guide/Resumen.jsp").forward(request, response);

		} else {
			response.sendRedirect("inicio");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
