package controlador;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class setLocale
 */
@WebServlet("/setLocale")
public class setLocale extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	HttpSession session;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Locale locale;
		String language;
		
		String[] lang;
		
		
		
		session= request.getSession();
		lang = new String[2];
		
		if (request.getParameter("language") != null) {
			language = request.getParameter("language");
			
			lang = language.split("_");
			
			locale = new Locale(lang[0] , lang[1]);
			session.setAttribute("locale", locale);
			
			response.setLocale(locale);
		}

		response.sendRedirect(request.getHeader("Referer"));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request , response);
	}

}
