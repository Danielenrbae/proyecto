<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="modelos.Comprador"%>
<%@page import="java.util.ResourceBundle" %>
<%@page import="java.util.Locale" %>
<!DOCTYPE html>
<html lang="es">


<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="robots" content="index, follow">
    <meta charset="utf-8" />        
    <link rel="shortcut icon" href="./Img/dynamic/bigmac.png" type="image/png" >
    
    <meta name="application-name" content="Inicio" />
    
    <%
	Comprador comprador;
	ResourceBundle idioma;
	boolean iniciado = false;
	Locale locale;
	
	locale = null;
			
	if(session.getAttribute("usuario") != null){
		iniciado = true;
		comprador = (Comprador) session.getAttribute("usuario");
	}
	
	if(session.getAttribute("locale") != null){
		locale = (Locale) session.getAttribute("locale");
		
	}else{
		locale = response.getLocale();
	}
	
	idioma = ResourceBundle.getBundle("languages.language" , locale);

%>
    <title><%= idioma.getString("breadcrumb") %></title>

<!--     <link rel="stylesheet" href="/WEB-INF/assets/Script/jquery-ui-1.12.1/jquery-ui.css" media="all"> -->
<!--     <link rel="stylesheet" href="/WEB-INF/assets/Script/jquerymodal/jquerymodal.css" media="all"> -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
    


<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>

<!--     <script src="/WEB-INF/assets/Script/jquery-ui-1.12.1/jquery.js"></script> -->
<!--     <script src="/WEB-INF/assets/Script/jquery-ui-1.12.1/jquery-ui.js"></script> -->
<!--     <script src="/WEB-INF/assets/Script/jquerymodal/jquerymodal.js"></script> -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns" crossorigin="anonymous"></script>


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

    



		

<nav class="pf-nav">
    <div class="pf-nav__logo">
    	<a href="inicio">
       	 <img class="img-fluid" src="./Img/common/favicon.png" alt="Logo"/>
        </a>
    </div>

     <div class="pf-nav__container">

        <span class="pf-container__desplegable"> <img src="./Img/icons/icon-menu.svg" alt="Icono del menu desplegable"></span>

        <ul class="pf-container__menu">

            <li class="pf-menu__item"><a href="inicio"><%= idioma.getString("nav.inicio") %></a></li>
            <li class="pf-menu__item"><a href="explorar"><%= idioma.getString("nav.explorar") %></a></li>
            <li class="pf-menu__item"><a href="#"><%= idioma.getString("nav.nosotros") %></a></li>
            <li class="pf-menu__item"><a href="contacto"><%= idioma.getString("nav.contacto") %></a></li>

        </ul>

        
     <div class="pf-container__logged">
        
                <div class="pf-container__auth">

            <%
			
			
				if(!iniciado){
			%>
			           <div class="pf-auth__buttons">
                
                            <span class="pf-auth__login" data-id="login"><%=idioma.getString("nav.iniciarSesion") %></span>
                            
							<div class="pf-button-primary" data-id="registrar">
							    <span class="pf-button-primary__text" ><%=idioma.getString("nav.registrar") %></span>
							</div>                       
 						</div>
			<%
				}else{
			
			%>
				<jsp:useBean id="usuario" class="modelos.Comprador" scope="session"></jsp:useBean>
			
				  <img class="pf-auth__icon" src="./Img/icons/icon-user.svg" alt="Icono usuario">
	
		         <div class="pf-auth__options">
		           <ul class="pf-options__menu">
		               <li class="pf-menu__item"><span><%=idioma.getString("nav.saludo") %> <jsp:getProperty property="nombre" name="usuario"/></span></li>
		               <li class="pf-menu__item"><a href="#"><%=idioma.getString("nav.perfil") %></a></li>
		               <li class="pf-menu__item"><a href="mispedidos"><%=idioma.getString("nav.pedidos") %></a></li>
		                <li class="pf-menu__item"><a href="cerrarSesion"><%=idioma.getString("nav.cerrarSesion") %> <img src="./Img/icons/log-out.svg" alt="Icono cerrar sesion"></a></li>
		           </ul>
		        </div>	
			<%
				}
			%>
                    
                </div>
       
            <div class="pf-container__cart">
                <a href="carrito"><img src="./Img/icons/shopping-bag.svg" alt="Carrito"/></a>
            </div>
     </div>

     </div>
    </nav>




    <div class="pf-nav__desplegable">
        <ul class="pf-container__menu">

            <li class="pf-menu__item"><a href="inicio"><%=idioma.getString("nav.inicio") %></a></li>
            <li class="pf-menu__item"><a href="explorar"><%=idioma.getString("nav.explorar") %></a></li>
            <li class="pf-menu__item"><a href="#"><%=idioma.getString("nav.nosotros") %></a></li>
            <li class="pf-menu__item"><a href="contacto"><%=idioma.getString("nav.contacto") %></a></li>

        </ul>

     
        <div class="pf-container__auth">
        
        <%
        
        if(!iniciado){
			%>
			           <div class="pf-auth__buttons">
			        
			 	  <a class="pf-auth__login" data-id="login"><%=idioma.getString("nav.iniciarSesion") %></a>
			                    
			
					<div class="pf-button-primary " data-id="registrar">
					    <a class="pf-button-primary__text" ><%=idioma.getString("nav.registrar") %></a>
					</div>                
			</div>
			<%
				}else{

			%>
				
		          <div class="pf-auth__logged">
                    <ul class="pf-logged__menu">
                        <li class="pf-menu__item"><span><%=idioma.getString("nav.saludo") %> <jsp:getProperty property="nombre" name="usuario"/></span></li>
                        <li class="pf-menu__item"><a href="#"><%=idioma.getString("nav.perfil") %></a></li>
                        <li class="pf-menu__item"><a href="mispedidos"><%=idioma.getString("nav.pedidos") %></a></li>
                        <li class="pf-menu__item"><a href="cerrarSesion"><%=idioma.getString("nav.cerrarSesion") %> <img src="./Img/icons/log-out.svg" alt="Icono cerrar sesion"></a></li>
                    </ul>
                </div>
			<%
				}
			%>
      
         
                    
        </div>
    </div>
    
        <nav class="pf-breadcrumb" aria-label="breadcrumb">
	  <ol class="breadcrumb">
	    <li class="breadcrumb-item" aria-current="page"><a href="inicio"><%=idioma.getString("breadcrumb") %></a></li>
	  </ol>
	</nav>
    
    
    
    <header class="pf-header">

    <div class="pf-header__info">
        <h1 class="pf-info__title"><%=idioma.getString("header.info.title") %></h1>

        <div class="pf-button-primary" data-id="registrar">
        
        <% if(!iniciado){
        	%>
        	<a class="pf-button-primary__text" ><%=idioma.getString("header.info.button.join") %></a>
        	
        	<%
        	}else{
        		%>
        	<a class="pf-button-primary__text" ><%=idioma.getString("header.info.button.explora") %></a>
        		
        		<%
        	}
        	
        %>
		</div>  
    </div>

    <div class="pf-header__img">
        <img src="./Img/common/portada.png" alt="Imagen cabecera hombre sosteniendo un hamburguesa" />
    </div>

</header>
    
    
<section class="pf-info1">
    <h2 class="pf-info1__title"><%=idioma.getString("info1.title") %></h2>
    <h3 class="pf-info1__subtitle"><%=idioma.getString("info1.subtitle") %></h3>

    <div class="pf-info1__container">
        <div class="pf-container__card">
            <img src="./Img/common/easy-to-use.svg" alt="icono easy to use"/>
            <h3 class="pf-card__text"><%=idioma.getString("info1.option1") %></h3 class="pf-card__text">
        </div>

        <div class="pf-container__card">
            <img src="./Img/common/truck.svg" alt="icono camion"/>
            <h3 class="pf-card__text"><%=idioma.getString("info1.option2") %></h3 class="pf-card__text">
        </div>

        <div class="pf-container__card">
            <img src="./Img/common/medal.svg" alt="icono medalla"/>
            <h3 class="pf-card__text"><%=idioma.getString("info1.option3") %></h3 class="pf-card__text">
        </div>
    </div>
</section>
<section class="pf-info2">

   <div class="pf-info2__title"> <h2><%=idioma.getString("info2.title") %></h2></div>
   <div class="pf-info2__subtitle">
       <h3 class="pf-subtitle__text"><%=idioma.getString("info2.subtitle") %></h3>
       <a href="explorar"> <%=idioma.getString("info2.todos") %> </a>
   </div>

   <div class="pf-info2__container">
       <div class="pf-container__item"><a href="#"><img src="./Img/dynamic/categoria1.jpg" alt="Imagen comida Japonesa"><p class="pf-item__text"><%=idioma.getString("info2.cat1") %></p class="pf-item__text"></a></div>
       <div class="pf-container__item"><a href="#"><img src="./Img/dynamic/categoria2.jpg" alt="Imagen comida Americana"><p class="pf-item__text"><%=idioma.getString("info2.cat2") %></p class="pf-item__text"></a></div>
       <div class="pf-container__item"><a href="#"><img src="./Img/dynamic/categoria3.jpg" alt="Imagen comida Italiana"><p class="pf-item__text"><%=idioma.getString("info2.cat3") %></p class="pf-item__text"></a></div>
       <div class="pf-container__item"><a href="#"><img src="./Img/dynamic/categoria4.jpg" alt="Imagen comida Mexicana"><p class="pf-item__text"><%=idioma.getString("info2.cat4") %></p class="pf-item__text"></a></div>
   </div>

</section><section class="pf-info3">
  <h2 class="pf-info3__title"><%=idioma.getString("info3.title") %></h2>
  <div class="pf-info3__container">
          <div class="pf-container__item img-fluid"><img src="./Img/common/sushiko.svg" alt="Logo empresa sushiko" /></div>
          <div class="pf-container__item img-fluid"><img src="./Img/common/burger-king.svg" alt="Logo empresa burger king" /></div>
          <div class="pf-container__item img-fluid"><img src="./Img/common/pizza-hut.svg" alt="Logo empresa pizza hut" /></div>
          <div class="pf-container__item img-fluid"><img src="./Img/common/coca-cola.svg" alt="Logo empresa coca cola" /></div>
</section>

<section class="pf-info4">
    <h2 class="pf-info4__title"><%=idioma.getString("info4.title")%></h2>
    <h3 class="pf-info4__subtitle"><%=idioma.getString("info4.subtitle")%></h3>

    <div class="pf-info4__container">
        <div class="pf-container__info">
            <div class="pf-info__text">
                <p><%=idioma.getString("info4.option1")%></p>
                <p><%=idioma.getString("info4.option2")%></p>
                <p><%=idioma.getString("info4.option3")%></p>
                <p><%=idioma.getString("info4.option4")%></p>
            </div>

            <div class="pf-info__buttons">
                <h3 class="pf-buttons__title"><%=idioma.getString("info4.button")%></h3>

                



<div class="pf-button-primary  pf-button-primary--fill " data-id="registrar">
    <a class="pf-button-primary__text" ><%=idioma.getString("header.info.button.join")%></a>
</div>
            </div>
        </div>
        <div class="pf-container__img">
            <img src="./Img/common/info4.jpg" alt="Imagen hombre realizando un pedido">
        </div>
    </div>
</section>
<footer class="pf-footer">

    <div class="pf-footer__container">
        <h2 class="pf-container__title"><%=idioma.getString("footer.title")%></h2>
        <h3 class="pf-container__subtitle"><%=idioma.getString("footer.subtitle")%></h3>

        
		<%if(!iniciado){
			%>
		  <div class="pf-button-primary  pf-button-primary--fill " data-id="registrar">
			    <a data-id="registrar" class="pf-button-primary__text" ><%=idioma.getString("header.info.button.join")%></a>
			</div>  
		<%
		}else{				
		%>
		 <div class="pf-button-primary  pf-button-primary--fill " >
			    <a href="explorar" class="pf-button-primary__text" ><%=idioma.getString("header.info.button.explora")%></a>
			</div>  
		<%
		}
		%>


    </div>

    <div class="pf-footer__nav">
        <div class="pf-nav__logo">
            <a href="inicio"><img class="img-fluid" src="./Img/common/favicon-blanco.png" alt="Logo"/></a>
        </div>
    
        <div class="pf-nav__container">
    
            <ul class="pf-container__menu">
    
                 <li class="pf-menu__item"><a href="inicio"><%= idioma.getString("nav.inicio") %></a></li>
            <li class="pf-menu__item"><a href="explorar"><%= idioma.getString("nav.explorar") %></a></li>
            <li class="pf-menu__item"><a href="#"><%= idioma.getString("nav.nosotros") %></a></li>
            <li class="pf-menu__item"><a href="contacto"><%= idioma.getString("nav.contacto") %></a></li>
            </ul>
        </div>

        <div class="pf-container__snetworks">
            <a href="#"><img class="pf-snetworks__item" src="./Img/icons/gorjeo-svg.svg " alt="Logo twitter" /></a>
            <a href="#"><img class="pf-snetworks__item" src="./Img/icons/instagram.svg" alt="Logo instagram" /></a>
            <a href="#"><img class="pf-snetworks__item" src="./Img/icons/facebook.svg" alt="Logo facebook" /></a>
        </div>
    </div>
    
    <div class="pf-footer__policy">
        <div>Copyright© is made by Daniel. All Right Reserved. </div>
        <ul class="pf-policy__list">
            <li class="pf-list__item"><a href="#"><%= idioma.getString("footer.condiciones") %></a></li>
            <li class="pf-list__item"><a href="#"><%= idioma.getString("footer.privacidad") %></a></li>
        </ul>

        <ul class="pf-policy__idioms">
            <li class="pf-idioms__item"><a href="setLocale?language=es_ES"><%= idioma.getString("footer.idioma1") %></a></li>
            <li class="pf-idioms__item"><a href="setLocale?language=en_US"><%= idioma.getString("footer.idioma2") %></a></li>
            <li class="pf-idioms__item"><a href="setLocale?language=fr_FR"><%= idioma.getString("footer.idioma3") %></a></li>
        </ul>
    </div>

</footer>

 <div class="modal pf-modal-sign-in" metodo="sign" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true" >
    <div class="modal-dialog">
  
      <div class="modal-content">
        <span class="pf-modal-sign-in__close"></span>
        <div class="modal-body">
          <h2 class="pf-modal-sign-in__title"><%= idioma.getString("inicioSesion.title") %></h2>

          <form class="pf-modal-sign-in__form">

            <div class="form-group">
                <label for="email"><img src="./Img/icons/icon-email-gray.svg" alt="Icono email"></label>
                <input type="email" class="form-control" name="email" aria-describedby="emailHelp" placeholder="E-mail" required>
            </div>

            <div class="form-group">
                <label for="password"><img src="./Img/icons/icon-lock-gray.svg" alt="Icono candado"></label>
                <input type="password" class="form-control" name="password" aria-describedby="passwordhelp" placeholder="<%= idioma.getString("inicioSesion.password") %>" required>
            </div>
            
                <div class="form-group">
                <select class="form-control pf-form__select" name="r_user" required>
                 <option selected><%= idioma.getString("inicioSesion.tipo") %></option>
                  <option value="Cliente"><%= idioma.getString("inicioSesion.option1") %></option>
                  <option value="Empresa"><%= idioma.getString("inicioSesion.option2") %></option>
                </select>
            </div>

            <div class="form-group">
                <span><a data-id="forgot"><%= idioma.getString("inicioSesion.forgot") %></a></span>
            </div>
            
            <span id="error_login"> </span>
            

            <input class="pf-form__submit" type="submit" value="<%= idioma.getString("inicioSesion.submit") %>">        
          </form>

          <div class="pf-modal-sign-in__login">
            <span><%= idioma.getString("inicioSesion.cuenta") %></span>
            <a data-id="registrar"><%= idioma.getString("inicioSesion.registrar") %></a>
          </div>

        </div>
      </div>
    </div>
  </div>
  
  <div class="modal pf-modal-sign-up" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
  
      <div class="modal-content">
        <span class="pf-modal-sign-up__close"></span>
        <div class="modal-body">
          <h2 class="pf-modal-sign-up__title"><%= idioma.getString("registrar.title") %></h2>

          <form class="pf-modal-sign-up__form" >

            <div class="form-group">
                <label for="nombre"><img src="./Img/icons/icon-pf-user-gray.svg" alt="Icono profile"></label>
                <input type="text" class="form-control" name="nombre" aria-describedby="nombrehelp" placeholder="<%= idioma.getString("registrar.placeholder.name") %>" required>
            </div>

            <div class="form-group">
                <label for="email"><img src="./Img/icons/icon-email-gray.svg" alt="Icono email"></label>
                <input type="email" class="form-control" name="email" aria-describedby="emailHelp" placeholder="E-mail" required>
                
            </div>

            <div class="form-group">
                <label for="password"><img src="./Img/icons/icon-lock-gray.svg" alt="Icono candado"></label>
                <input type="password" class="form-control" name="password" aria-describedby="passwordhelp" placeholder="<%= idioma.getString("registrar.placeholder.password") %>" required>
            </div>

            <div class="form-group">
              <label for="confirm_password"><img src="./Img/icons/icon-lock-gray.svg" alt="Icono candado"></label>
                <input type="password" class="form-control" name="confirm_password" placeholder="<%= idioma.getString("registrar.placeholder.confirm.password") %>" aria-describedby="confirm_passwordhelp" required>
            </div>

            <div class="form-group">
                <select class="form-control pf-form__select" name="r_user" required>
                 <option selected><%= idioma.getString("inicioSesion.tipo") %></option>
                  <option value="Cliente"><%= idioma.getString("inicioSesion.option1") %></option>
                  <option value="Empresa"><%= idioma.getString("inicioSesion.option2") %></option>
                </select>
            </div>
            
            <input class="pf-form__submit" type="submit" value="<%= idioma.getString("registrar.submit") %>">        
          </form>
          
          <span id="error_registro"> </span>

          <div class="pf-modal-sign-up__login">
            <span><%= idioma.getString("registrar.cuenta") %></span>
            <a data-id="login"><%= idioma.getString("registrar.iniciar") %></a>

            <p>
              <%= idioma.getString("registrar.terminos1") %><a href="#"> <%= idioma.getString("registrar.terminos.a") %></a> <%= idioma.getString("registrar.terminos2") %><a href="#"> <%= idioma.getString("registrar.terminos.a2") %></a>
            </p>
          </div>

        </div>
      </div>
    </div>
  </div>
  

  
  <!-- Modal -->
  <div class="modal pf-modal-forgot" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true" >
    <div class="modal-dialog">
  
      <div class="modal-content">
        <span class="pf-modal-forgot__close"></span>
        <div class="modal-body">
          <h2 class="pf-modal-forgot__title"> <%= idioma.getString("forgot.title") %></h2>

          <form class="pf-modal-forgot__form" action="#" method="POST">

            <div class="form-group">
                <label for="email"><img src="./Img/icons/icon-email-gray.svg" alt="Icono email"></label>
                <input type="email" class="form-control" name="email" aria-describedby="emailHelp" placeholder="E-mail" required>
            </div>



            <input class="pf-form__submit" type="submit" value="<%= idioma.getString("forgot.submit") %>">        
          </form>

          <div class="pf-modal-forgot__login">
            <a data-id="login"><%= idioma.getString("forgot.volver") %></a>
          </div>

        </div>
      </div>
    </div>
  </div>
  
  
  <!-- Modal -->
  <div class="modal pf-modal-verificacion" id="exampleModal2" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true" >
    <div class="modal-dialog">
  
      <div class="modal-content">
        <span class="pf-modal-verificacion__close"></span>
        <div class="modal-body">
          <h2 class="pf-modal-verificacion__title"><%= idioma.getString("verificar.title") %></h2>

          <form class="pf-modal-verificacion__form">

            <div class="form-group">
                <label for="codigo"><img src="./Img/icons/icon-lock-gray.svg" alt="Icono profile"></label>
                <input type="text" class="form-control" name="codigo" aria-describedby="nombrehelp" placeholder="<%= idioma.getString("verificar.codigo.placeholder") %>" required>
            </div>


            <input class="pf-form__submit" type="submit" value="<%= idioma.getString("verificar.submit") %>">        
          </form>
			<span id="error_verificacion"> </span>
        </div>
      </div>
    </div>
  </div>





</body>

</html>