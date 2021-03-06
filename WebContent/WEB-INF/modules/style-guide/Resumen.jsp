<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="modelos.Empresa"%>
<%@page import="java.util.ArrayList"%>
<%@page import="modelos.Pedido"%>
<!DOCTYPE html>
<html lang="es">


<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="robots" content="index, follow">
    <meta charset="utf-8" />
    <meta name="application-name" content="Inicio" />
    <title>Resumen</title>

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
        <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.24/css/jquery.dataTables.css">
    


<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns" crossorigin="anonymous"></script>
        <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.24/js/jquery.dataTables.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>


</head>

<body>

<style>


	<%@ include file="/WEB-INF/assets/Script/owl-carousel-2.3.4/owl.carousel.min.css"%>
	<%@ include file="/WEB-INF/assets/Script/owl-carousel-2.3.4/owl.theme.default.min.css"%>
	<%@ include file="/WEB-INF/assets/CSS/themes/theme.css"%>
	<%@ include file="/WEB-INF/assets/CSS/stylesPC.css"%>

</style>

<script>

	<%@ include file="/WEB-INF/assets/Script/owl-carousel-2.3.4/owl.carousel.min.js"%>
	<%@ include file="/WEB-INF/assets/Script/scriptPC.js"%>
</script>


<%
	ArrayList<Pedido> listaPedidos;
	boolean b_listaPedidos;
	
	b_listaPedidos = false;
	listaPedidos = null;

	if(session.getAttribute("pedidos_empresa") != null){
		System.out.println("holaa");
		listaPedidos = (ArrayList) session.getAttribute("pedidos_empresa");
		b_listaPedidos= true;
	}

%>
    
<jsp:useBean id="usuario" scope="session" class="modelos.Empresa"></jsp:useBean>

<nav class="pf-nav-bussines">
  <div class="pf-nav-bussines__logo">
  <a href="resumen">
    <img
      class="img-fluid"
      src="./Img/common/favicon.png"
      alt="Logo"
    />
    </a>
  </div>

  <div class="pf-nav-bussines__container">
    <span class="pf-container__desplegable">
      <img
        src="./Img/icons/icon-menu.svg"
        alt="Icono del menu desplegable"
    /></span>

    <ul class="pf-container__menu">
      <li class="pf-menu__item"><a href="resumen">RESUMEN</a></li>
      <li class="pf-menu__item"><a href="#">PEDIDOS</a></li>
      <li class="pf-menu__item"><a href="mantenimiento">MANTENIMIENTO</a></li>
      <li class="pf-menu__item"><a href="#">SOBRE NOSOTROS</a></li>
      <li class="pf-menu__item"><a href="contacto">CONTACTO</a></li>
    </ul>

    <div class="pf-container__logged">
      <div class="pf-container__auth">
        <a href="#"
          ><img
            class="pf-auth__icon"
            src="./Img/icons/icon-user.svg"
            alt="Icono usuario"
        /></a>

        
        <div class="pf-auth__options">
          <ul class="pf-options__menu">
              <li class="pf-menu__item"><span>??Hola! <jsp:getProperty property="nombre" name="usuario"/> </span></li>
              <li class="pf-menu__item"><a href="#">Mi Perfil</a></li>
              <li class="pf-menu__item"><a href="cerrarSesion">Cerrar Sesi??n <img src="./Img/icons/log-out.svg" alt="Icono cerrar sesion"></a></li>
          </ul>
      </div>
      </div>
    </div>
  </div>
</nav>

<div class="pf-nav-bussines__desplegable">
  <ul class="pf-container__menu">
    <li class="pf-menu__item"><a href="resumen">RESUMEN</a></li>
    <li class="pf-menu__item"><a href="#">PEDIDOS</a></li>
    <li class="pf-menu__item"><a href="mantenimiento">MANTENIMIENTO</a></li>
    <li class="pf-menu__item"><a href="#">SOBRE NOSOTROS</a></li>
    <li class="pf-menu__item"><a href="contacto">CONTACTO</a></li>
  </ul>

  <div class="pf-container__auth">
    <div class="pf-auth__logged">
      <ul class="pf-logged__menu">
              <li class="pf-menu__item"><span>??Hola! <jsp:getProperty property="nombre" name="usuario"/> </span></li>
        <li class="pf-menu__item"><a href="#">Mi Perfil</a></li>
        <li class="pf-menu__item">
          <a href="cerrarSesion"
            >Cerrar Sesi??n
            <img
              src="./Img/icons/log-out.svg"
              alt="Icono cerrar sesion"
          /></a>
        </li>
      </ul>
    </div>
  </div>
</div>
<nav class="pf-breadcrumb" aria-label="breadcrumb">
  <ol class="breadcrumb">
    <li class="breadcrumb-item" aria-current="page"><a href="inicio">Inicio</a></li>
    <li class="breadcrumb-item" aria-current="page"><a href="resumen">Resumen</a></li>
  
  </ol>
</nav>
<div class="header-resumen">

</div>


<section class="pf-resumen">

    <h2 class="pf-resumen__title">EXAMINA</h2>
    <h3 class="pf-resumen__subtitle">Resumen</h3>


    <section class="pf-resumen__grafico">
        <canvas id="chart_resumen"></canvas>
    </section>

    <h2 class="pf-resumen__title">INF??RMATE</h2>
    <h3 class="pf-resumen__subtitle">Historial de Pedidos</h3>

	<%
		if (b_listaPedidos){
			%>
			 <section class="pf-resumen__table">
		        <table id="table_id" class="pf-table">
		            <thead>
		                <tr>
		                    <th>ID</th>
		                    <th>CLIENTE </th>
		                    <th>FECHA</th>
		                    <th>IMPORTE</th>
		                    <th>ESTADO</th>
		                </tr>
		            </thead>
		            <tbody>
		            
		            <%
					for(int i = 0; i < listaPedidos.size(); i++){
						Pedido pedido = listaPedidos.get(i);
						
						%>
						 <tr>
		                    <td><%= pedido.getId_pedido() %></td>
		                    <td><%= pedido.getId_comprador() %></td>
		                    <td><%= pedido.getFecha() %></td>
		                    <td><%= pedido.getImporte() %> EUR</td>
		                    <td>
		                    
		                    <% if(pedido.getEstado().equals("E")) %> <img src="./Img/icons/check-circle.svg" alt="Icon check"/>
		                   	<% if(pedido.getEstado().equals("P")) %> <img src="./Img/icons/coffee.svg" alt="Icon in progress"/>
		                   	<% if(pedido.getEstado().equals("D")) %> <img src="./Img/icons/x-circle.svg" alt="Icon denegado"/>
		                   	
		                    
		                    </td>
		               	 </tr>
		                
						<%
					}
		            
		            %>
		               
		                
		            </tbody>
		        </table>
		    </section>
			<%
		}else{
			%>
			<h2> Todav??a no se ha realizado ning??n pedido.</h2>
			<%
		}
	%>

   

</section>
<footer class="pf-footer-bussiness" style="margin-top:120px;">

    <div class="pf-footer-bussiness__nav">
        <div class="pf-nav__logo">
            <a href=""><img class="img-fluid" src="./Img/common/favicon-blanco.png" alt="Logo"/></a>
        </div>
    
        <div class="pf-nav__container">
    
            <ul class="pf-container__menu">
    
                <li class="pf-menu__item"><a href="resumen">RESUMEN</a></li>
                <li class="pf-menu__item"><a href="#">PEDIDOS</a></li>
                <li class="pf-menu__item"><a href="mantenimiento">MANTENIMIENTO</a></li>
                <li class="pf-menu__item"><a href="#">SOBRE NOSOTROS</a></li>
                <li class="pf-menu__item"><a href="contacto">CONTACTO</a></li>
            </ul>
        </div>

        <div class="pf-container__snetworks">
            <a href="#"><img class="pf-snetworks__item" src="./Img/icons/gorjeo-svg.svg " alt="Logo twitter" /></a>
            <a href="#"><img class="pf-snetworks__item" src="./Img/icons/instagram.svg" alt="Logo instagram" /></a>
            <a href="#"><img class="pf-snetworks__item" src="./Img/icons/facebook.svg" alt="Logo facebook" /></a>
        </div>
    </div>
    
    <div class="pf-footer-bussiness__policy">
        <div>Copyright?? is made by Daniel. All Right Reserved. </div>
        <ul class="pf-policy__list">
            <li class="pf-list__item"><a href="#">T??rminos y Condiciones</a></li>
            <li class="pf-list__item"><a href="#">Pol??tica de Privacidad</a></li>
        </ul>

        <ul class="pf-policy__idioms">
            <li class="pf-idioms__item"><a href="#">Espa??ol</a></li>
            <li class="pf-idioms__item"><a href="#">Ingl??s</a></li>
            <li class="pf-idioms__item"><a href="#">Franc??s</a></li>
        </ul>
    </div>

</footer>


    






</body>

</html>