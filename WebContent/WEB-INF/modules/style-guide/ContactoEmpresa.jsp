<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="modelos.Empresa"%>
<!DOCTYPE html>
<html lang="es">


<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="robots" content="index, follow">
    <meta charset="utf-8" />
    <meta name="application-name" content="Contacto" />
    <title>Contacto</title>

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
	boolean correcto;

	correcto = false;
	
	if(session.getAttribute("contacto_correcto") != null){
		correcto = true;
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
              <li class="pf-menu__item"><span>¡Hola! <jsp:getProperty property="nombre" name="usuario"/> </span></li>
              <li class="pf-menu__item"><a href="#">Mi Perfil</a></li>
              <li class="pf-menu__item"><a href="cerrarSesion">Cerrar Sesión <img src="./Img/icons/log-out.svg" alt="Icono cerrar sesion"></a></li>
          </ul>
      </div>
      </div>
    </div>
  </div>
</nav>

<div class="pf-nav-bussines__desplegable">
  <ul class="pf-container__menu">
    <li class="pf-menu__item"><a href="resumen">RESUMEN</a></li>
    <li class="pf-menu__item"><a href="mantenimiento">MANTENIMIENTO</a></li>
    <li class="pf-menu__item"><a href="#">SOBRE NOSOTROS</a></li>
    <li class="pf-menu__item"><a href="contacto">CONTACTO</a></li>
  </ul>

  <div class="pf-container__auth">
    <div class="pf-auth__logged">
      <ul class="pf-logged__menu">
              <li class="pf-menu__item"><span>¡Hola! <jsp:getProperty property="nombre" name="usuario"/> </span></li>
        <li class="pf-menu__item"><a href="#">Mi Perfil</a></li>
        <li class="pf-menu__item">
          <a href="cerrarSesion"
            >Cerrar Sesión
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
    <li class="breadcrumb-item" aria-current="page"><a href="contacto">Contacto</a></li>
  
  </ol>
</nav>

<div class="pf-cabecera-contacto"> 
	</div>    

<form class="pf-form-contac" method="post" action="contacto">
    <div class="form-row">
      <div class="form-group col-sm-12 col-lg-6">
        <label for="email">CORREO ELECTRÓNICO</label>
        <input type="email" class="form-control" name="email" aria-describedby="emailHelp" required placeholder="Correo electrónico corporativo">
        
      </div>

      <div class="form-group col-sm-12 col-lg-6">
        <label for="telefono">TELÉFONO</label>
        <input type="tel" class="form-control" name="telefono" aria-describedby="telHelp" placeholder="Teléfono">
        
      </div>
    </div>

    <div class="form-row">
        <div class="form-group col">
            <label for="asunto">ASUNTO</label>
            <input type="text" class="form-control" name="asunto" aria-describedby="asuntoHelp" required placeholder="Asunto">
        </div>
    </div>
    <div class="form-row">
        <div class="form-group col ">
            <textarea class="form-control" name="cuerpo" placeholder="Cuéntanos" required></textarea>
            <span>Máximo 500 caracteres</span>
        </div>
    </div>

    <div class="form-row">
        <div class="form-group col">
            <div class="form-check">
              <input class="form-check-input" type="checkbox" name="acepto" required="required"/>
              <label class="form-check-label" for="acepto">
                He leído, comprendo y acepto el tratamiento de datos personales
              </label>
            </div>
        </div>
    </div>

    <div class="form-row">
        <input class="pf-form-contac__submit" type="submit" value="Contactar">
    </div>
    
      <% if (correcto) %> <h2 style="margin-top:20px;"> Mensaje enviado con éxito.</h2>
    
  </form>

	<% session.setAttribute("contacto_correcto", null); %>

	<footer class="pf-footer-bussiness">
	
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
	        <div>Copyright© is made by Daniel. All Right Reserved. </div>
	        <ul class="pf-policy__list">
	            <li class="pf-list__item"><a href="#">Términos y Condiciones</a></li>
	            <li class="pf-list__item"><a href="#">Política de Privacidad</a></li>
	        </ul>
	
	        <ul class="pf-policy__idioms">
	            <li class="pf-idioms__item"><a href="#">Español</a></li>
	            <li class="pf-idioms__item"><a href="#">Inglés</a></li>
	            <li class="pf-idioms__item"><a href="#">Francés</a></li>
	        </ul>
	    </div>
	
	</footer>

</body>

</html>