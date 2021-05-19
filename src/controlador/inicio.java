package controlador;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelos.Comprador;
import modelos.Empresa;

/**
 * Servlet implementation class inicio
 */
@WebServlet("/inicio")
public class inicio extends HttpServlet {
	private static final long serialVersionUID = 1L;

	HttpSession session;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Empresa empresa;
		Comprador comprador;
		String tipo;
		String email;

		session = request.getSession();

		if (session.isNew()) {
			session.setAttribute("usuario", null);
			session.setAttribute("email-verificacion", null);
			session.setAttribute("codigo-verificacion", null);
			session.setAttribute("tipo_usuario", null);
		}

		tipo = (String) session.getAttribute("tipo_usuario");

		
		if (tipo != null) {
			email = (String) session.getAttribute("usuario");
			if (tipo.equals("Empresa")) {

				empresa = new Empresa();

				if (empresa.leer("email", email)) {

					if (!empresa.leersiguiente()) {
						session.setAttribute("usuario", empresa);
						request.getRequestDispatcher("/WEB-INF/modules/style-guide/Resumen.jsp").forward(request,
								response);
					}

				}

			} else if (tipo.equals("Cliente")) {
				comprador = new Comprador();

				if (comprador.leer("email", email)) {
					if (!comprador.leersiguiente()) {
						session.setAttribute("usuario", comprador);

					}
				}
			}

		}

		request.getRequestDispatcher("/WEB-INF/modules/style-guide/Inicio.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		session = request.getSession();
		doGet(request, response);
	}

}
