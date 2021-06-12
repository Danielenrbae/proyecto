package tags;

import java.io.IOException;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import modelos.Categoria;
import modelos.Producto;

public class tag_productos extends SimpleTagSupport {

	private int page;
	private String cat;





	public String getCat() {
		return cat;
	}

	public void setCat(String cat) {
		this.cat = cat;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public void doTag() {
		JspWriter out;

		boolean salir;
		Producto producto;
		Categoria categoria;
		String columna;
		String valor;
		boolean all;

		out = getJspContext().getOut();
		categoria = new Categoria();
		producto = new Producto();
		salir = false;
		columna = "";
		valor = "";
		all = true;

		
		if (!cat.isEmpty()) {

			if (categoria.leer("nombre", cat, true)) {
				columna = "id_categoria";
				valor = String.valueOf(categoria.getId_categoria());
				all = false;
			}

		}

		if (producto.leer(columna, valor, false, all, false, page)) {

			if (producto.getFoto() != null) {
				try {
					out.print("<div class='card pf-productos__item' data-id='" + producto.getId_producto() + "'>"
							+ "<img class='content card-img-top img-fluid' src='bajarFoto?param_img=producto&idproducto="
							+ producto.getId_producto() + "'/> \n"
							+ "									  <div class='card-body'>\n"
							+ "									    <p class='card-text' >" + producto.getNombre()
							+ "</p>\n" + "								\n"
							+ "										<div class='button-group'>"
							+ "											    <p class='card-text'>"
							+ producto.getPrecio() + "€</p>"
							+ "									        <a href='compraProducto?id="
							+ producto.getId_producto() + "' class='btn btn-primary'>Añadir al carrito</a>\n"
							+ "										</div>\n" + "									\n"
							+ "									  </div>\n"
							+ "									</div>");
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				try {
					out.print("<div class='card pf-productos__item' data-id='" + producto.getId_producto() + "'>"
							+ "<img class='card-img-top img-fluid' src='./Img/common/pf-default-image.png'/>"
							+ "									  <div class='card-body'>\n"
							+ "									    <p class='card-text' >" + producto.getNombre()
							+ "</p>\n" + "								\n"
							+ "										<div class='button-group'>"
							+ "											    <p class='card-text'>"
							+ producto.getPrecio() + "€</p>"
							+ "									        <a href='compraProducto?id="
							+ producto.getId_producto() + "' class='btn btn-primary'>Añadir al carrito</a>\n"
							+ "										</div>\n" + "									\n"
							+ "									  </div>\n"
							+ "									</div>");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			while (!salir) {

				if (producto.leersiguiente()) {

					if (producto.getFoto() != null) {
						try {
							out.print("<div class='card pf-productos__item' data-id='" + producto.getId_producto()
									+ "'>"
									+ "<img class='content card-img-top img-fluid' src='bajarFoto?param_img=producto&idproducto="
									+ producto.getId_producto() + "'/> \n"
									+ "									  <div class='card-body'>\n"
									+ "									    <p class='card-text' >"
									+ producto.getNombre() + "</p>\n" + "								\n"
									+ "										<div class='button-group'>"
									+ "											    <p class='card-text'>"
									+ producto.getPrecio() + "€</p>"
									+ "									        <a href='compraProducto?id="
									+ producto.getId_producto() + "' class='btn btn-primary'>Añadir al carrito</a>\n"
									+ "										</div>\n"
									+ "									\n"
									+ "									  </div>\n"
									+ "									</div>");
						} catch (IOException e) {
							e.printStackTrace();
						}
					} else {
						try {
							out.print("<div class='card pf-productos__item' data-id='" + producto.getId_producto()
									+ "'>"
									+ "<img class='card-img-top img-fluid' src='./Img/common/pf-default-image.png'/>"
									+ "									  <div class='card-body'>\n"
									+ "									    <p class='card-text' >"
									+ producto.getNombre() + "</p>\n" + "								\n"
									+ "										<div class='button-group'>"
									+ "											    <p class='card-text'>"
									+ producto.getPrecio() + "€</p>"
									+ "									        <a href='compraProducto?id="
									+ producto.getId_producto() + "' class='btn btn-primary'>Añadir al carrito</a>\n"
									+ "										</div>\n"
									+ "									\n"
									+ "									  </div>\n"
									+ "									</div>");
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				} else {
					salir = true;
				}

			}

		}else {
			try {
				out.print("<h2> No hay productos existentes</h2>");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
