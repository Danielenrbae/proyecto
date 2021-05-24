package controlador;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;

/**
 * Servlet implementation class insertarFotoproducto
 */
@WebServlet("/insertarFotoproducto")
public class subirFoto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   HttpSession session;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("inicio");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		File file = null;
		int maxFileSize = 5000 * 1024;
		int maxMemSize = 5000 * 1024;
		ServletContext servletContext;
		String filePath;
		String contentType;
		PrintWriter out;
		FileItem fi;
		List<FileItem> fileItems;
		DiskFileItemFactory factory;
		String fileName = null;
		boolean isInMemory;
		String fieldName;
		long sizeInBytes;
		
		
		session = request.getSession();
		
		
	}

}
