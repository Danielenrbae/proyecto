<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="modelos.Comprador"%>
<%@page import="modelos.Proped"%>
<%@page import="modelos.Pedido"%>
<%@page import="java.util.ArrayList"%>

<!DOCTYPE html>
<html lang="es">


<head>
    <link rel="shortcut icon" href="./Img/dynamic/bigmac.png" type="image/png" >
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="robots" content="index, follow">
    <meta charset="utf-8" />
    <meta name="application-name" content="Mis pedidos" />
    <title>Mis Pedidos</title>

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
    


<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>

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


<%
	Comprador comprador;
	boolean iniciado = false;
	boolean pedidos = false;
	Pedido[] mispedidos;
	ArrayList<Proped> listaProductos; 
	boolean salir;
	int contador;
	
	listaProductos = null;
	mispedidos= null;
	salir = false;
	contador = 0;
	
	if(session.getAttribute("usuario") != null){
		iniciado = true;
		comprador = (Comprador) session.getAttribute("usuario");
	}
	
	if(session.getAttribute("misPedidos") != null){
		pedidos= true;
		mispedidos = (Pedido[]) session.getAttribute("misPedidos");
	}
	
	if(session.getAttribute("misPedidosPro") != null){
		pedidos= true;
		listaProductos = (ArrayList) session.getAttribute("misPedidosPro");
	}
	

	
%>

<nav class="pf-nav">
    <div class="pf-nav__logo">
    	<a href="inicio">
       	 <img class="img-fluid" src="./Img/common/favicon.png" alt="Logo"/>
        </a>
    </div>

     <div class="pf-nav__container">

        <span class="pf-container__desplegable"> <img src="./Img/icons/icon-menu.svg" alt="Icono del menu desplegable"></span>

        <ul class="pf-container__menu">

            <li class="pf-menu__item"><a href="inicio">INICIO</a></li>
            <li class="pf-menu__item"><a href="explorar">EXPLORAR</a></li>
            <li class="pf-menu__item"><a href="#">SOBRE NOSOTROS</a></li>
            <li class="pf-menu__item"><a href="contacto">CONTACTO</a></li>

        </ul>

        
     <div class="pf-container__logged">
        
                <div class="pf-container__auth">

            <%
				
			
				if(!iniciado){
			%>
			           <div class="pf-auth__buttons">
                
                            <span class="pf-auth__login" data-id="login">INICIAR SESI??N</span>
                            
							<div class="pf-button-primary" data-id="registrar">
							    <span class="pf-button-primary__text" >REGISTRAR</span>
							</div>                       
 						</div>
			<%
				}else{													
			%>
				<jsp:useBean id="usuario" class="modelos.Comprador" scope="session"></jsp:useBean>
			
				  <img class="pf-auth__icon" src="./Img/icons/icon-user.svg" alt="Icono usuario">
	
		         <div class="pf-auth__options" style="z-index: 9999">
		           <ul class="pf-options__menu">
		               <li class="pf-menu__item"><span>??Hola! <jsp:getProperty property="nombre" name="usuario"/></span></li>
		               <li class="pf-menu__item"><a href="#">Mi Perfil</a></li>
		               <li class="pf-menu__item"><a href="mispedidos">Mis Pedidos</a></li>
		                <li class="pf-menu__item"><a href="cerrarSesion">Cerrar Sesi??n <img src="./Img/icons/log-out.svg" alt="Icono cerrar sesion"></a></li>
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

            <li class="pf-menu__item"><a href="inicio">INICIO</a></li>
            <li class="pf-menu__item"><a href="explorar">EXPLORAR</a></li>
            <li class="pf-menu__item"><a href="#">SOBRE NOSOTROS</a></li>
            <li class="pf-menu__item"><a href="contacto">CONTACTO</a></li>

        </ul>

     
        <div class="pf-container__auth">
        
        <%
        
        if(!iniciado){
			%>
			           <div class="pf-auth__buttons">
			        
			 	  <a class="pf-auth__login" data-id="login">INICIAR SESI??N</a>
			                    
			
					<div class="pf-button-primary " data-id="registrar">
					    <a class="pf-button-primary__text" >REGISTRAR</a>
					</div>                
			</div>
			<%
				}else{
		
			%>
			
		          <div class="pf-auth__logged">
                    <ul class="pf-logged__menu">
                        <li class="pf-menu__item"><span>??Hola! <jsp:getProperty property="nombre" name="usuario"/></span></li>
                        <li class="pf-menu__item"><a href="#">Mi Perfil</a></li>
                        <li class="pf-menu__item"><a href="mispedidos">Mis Pedidos</a></li>
                        <li class="pf-menu__item"><a href="cerrarSesion">Cerrar Sesi??n <img src="./Img/icons/log-out.svg" alt="Icono cerrar sesion"></a></li>
                    </ul>
                </div>
			<%
				}
			%>
      
         
                    
        </div>
    </div>
    
        <nav class="pf-breadcrumb" aria-label="breadcrumb">
	  <ol class="breadcrumb">
	    <li class="breadcrumb-item" aria-current="page"><a href="inicio">Inicio</a></li>
	     <li class="breadcrumb-item" aria-current="page"><a href="inicio">Mis Pedidos</a></li>
	  </ol>
	</nav>
    
    
    
    
	<section class="pf-carrito">
	
		<%
			if(!iniciado){
				%>
			<h2 class="pf-carrito__title">Mis Pedidos</h2>

            <div class="pf-carrito__container--nocontent">
	            <p>Primero debes iniciar sesi??n para poder comprobar los pedidos realizados</p>
	            <a href="inicio">Volver al inicio</a>
	     	</div>
				<%
			}else{
				
				if(pedidos){
					
					%>
				
		<section class="pf-carrito">
		
		
		    <h2 class="pf-carrito__title">Mis Pedidos</h2>
		
		              <div class="pf-carrito__container">
		              
		              	<div class="pf-container__productos"> 		           
		              	
		              	<%
		              	
		              		while(!salir){
		              			if(mispedidos[contador] != null){
		              				 
		              				%>
		              					<div class="pf-productos__item" style="flex-direction: column;">
		              					<div style="width: 100%;">
		              						<h1>Pedido N?? <strong><%= mispedidos[contador].getId_pedido() %></strong></h2>
		              						
		              					
		              						<h3>El pedido fue realizado el <strong><%=mispedidos[contador].getFecha()%> </strong></h4>
		              						
		              							<h2 style ="display: flex; justify-content: flex-start; align-items: center; gap:20px; margin-top: 20px;">Estado: 
		              						
		              						         <% if(mispedidos[contador].getEstado().equals("E")) %> <img style="width: 30px;" src="./Img/icons/check-circle.svg" alt="Icon check"/>
									                 <% if(mispedidos[contador].getEstado().equals("P")) %> <img style="width: 30px;" src="./Img/icons/coffee.svg" alt="Icon in progress"/>
									                 <% if(mispedidos[contador].getEstado().equals("D")) %> <img style="width: 30px;" src="./Img/icons/x-circle.svg" alt="Icon denegado"/>
		              							
		              						</h2>
		              					</div>
		              					
		              					<div style="width: 100%">
		              						<h1>Productos:</h1>
		              						<ul style="width: 100%; margin-top:30px;">
		              							<%
		              							for(Proped item : listaProductos){
		              								if(item.getId_pedido() == mispedidos[contador].getId_pedido()){
		              									%>
		              									<li style="font-size: 2rem;  display: flex; justify-content:flex-start; gap: 70px; width: 100%; margin-left: 100px; align-items: center;">
		              										
		              										<img class="img-fluid" style="width: 80px;" src="bajarFoto?param_img=producto&idproducto=<%= item.getId_producto()%>"/> 

		              									
		              										<p style="max-width: 320px; min-width: 320px;"> <stong><a style="color: black" href="compraProducto?id=<%= item.getId_producto()%>"><%= item.getNombre() %></a> </stong></p>
		              										<div style="display: flex; justify-content: space-between; align-items:center; gap:100px;">
		              								
		              										<p style="min-width: 170px"> Precio: <strong><%= item.getPrecioUnidad() %> EUR </strong> </p>
		              										<p style="min-width: 170px"> Cantidad:  <%= item.getCantidad() %></p>
		              										
		              										</div>
		              									
		              									</li>
		              									
		              									<hr>
		              									<%
		              								}
		              							}
		              							
		              							%>
		              						</ul>
		              					</div>
		              						<h2 style="align-self: flex-end;">Importe Total: <strong style="font-size: 2.5rem;"><%=mispedidos[contador].getImporte() %> EUR</strong></h4>
		              						
		              						
		              					</div>
		              				<%
		              				contador++;
		              			}else{
		              				salir = true;
		              			}
		              		}
		              	
		              	%>
		              	
		              	</div>
		              	
		              </div>
		            
			</section> 


					<%					
				}else{
		
				%>
				
				
				 <h2 class="pf-carrito__title">Mis Pedidos</h2>

            
		          <div class="pf-carrito__container--nocontent">
		            <p>Actualmente no has realizado ning??n pedido.</p>
		            <a href="explorar">??Realizalo ya!</a>
		          </div>
				<%
				}
			}
		%>
	
	    
	</section> 
    

	<footer class="pf-footer">

    <div class="pf-footer__container">
        <h2 class="pf-container__title">PARA UNA MEJOR VIDA</h2>
        <h3 class="pf-container__subtitle">Convi??rtete en Parte de Nuestro Viaje</h3>

        
		<%if(!iniciado){
			%>
		  <div class="pf-button-primary  pf-button-primary--fill " data-id="registrar">
			    <a data-id="registrar" class="pf-button-primary__text" >??nete ya</a>
			</div>  
		<%
		}else{
							
		%>
		 <div class="pf-button-primary  pf-button-primary--fill " >
			    <a href="explorar" class="pf-button-primary__text" >Explora</a>
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
    
                <li class="pf-menu__item"><a href="inicio">INICIO</a></li>
                <li class="pf-menu__item"><a href="explorar">EXPLORAR</a></li>
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
    
    <div class="pf-footer__policy">
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

 <div class="modal pf-modal-sign-in" metodo="sign" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true" >
    <div class="modal-dialog">
  
      <div class="modal-content">
        <span class="pf-modal-sign-in__close"></span>
        <div class="modal-body">
          <h2 class="pf-modal-sign-in__title">Iniciar sesi??n en *****</h2>

          <form class="pf-modal-sign-in__form">

            <div class="form-group">
                <label for="email"><img src="./Img/icons/icon-email-gray.svg" alt="Icono email"></label>
                <input type="email" class="form-control" name="email" aria-describedby="emailHelp" placeholder="E-mail" required>
            </div>

            <div class="form-group">
                <label for="password"><img src="./Img/icons/icon-lock-gray.svg" alt="Icono candado"></label>
                <input type="password" class="form-control" name="password" aria-describedby="passwordhelp" placeholder="Contrase??a" required>
            </div>
            
                <div class="form-group">
                <select class="form-control pf-form__select" name="r_user" required>
                 <option selected>Seleccione un tipo de usuario</option>
                  <option value="Cliente">Cliente</option>
                  <option value="Empresa">Empresa</option>
                </select>
            </div>

            <div class="form-group">
                <span><a data-id="forgot">??Has olvidado tu contrase??a?</a></span>
            </div>
            
            <span id="error_login"> </span>
            

            <input class="pf-form__submit" type="submit" value="Inicia sesi??n con e-mail">        
          </form>

          <div class="pf-modal-sign-in__login">
            <span>??No tienes cuenta?</span>
            <a data-id="registrar">Registrar</a>
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
          <h2 class="pf-modal-sign-up__title">Reg??strate en *****</h2>

          <form class="pf-modal-sign-up__form" >

            <div class="form-group">
                <label for="nombre"><img src="./Img/icons/icon-pf-user-gray.svg" alt="Icono profile"></label>
                <input type="text" class="form-control" name="nombre" aria-describedby="nombrehelp" placeholder="Nombre" required>
            </div>

            <div class="form-group">
                <label for="email"><img src="./Img/icons/icon-email-gray.svg" alt="Icono email"></label>
                <input type="email" class="form-control" name="email" aria-describedby="emailHelp" placeholder="E-mail" required>
                
            </div>

            <div class="form-group">
                <label for="password"><img src="./Img/icons/icon-lock-gray.svg" alt="Icono candado"></label>
                <input type="password" class="form-control" name="password" aria-describedby="passwordhelp" placeholder="Contrase??a" required>
            </div>

            <div class="form-group">
              <label for="confirm_password"><img src="./Img/icons/icon-lock-gray.svg" alt="Icono candado"></label>
                <input type="password" class="form-control" name="confirm_password" placeholder="Vuelve a introducir la contrase??a" aria-describedby="confirm_passwordhelp" required>
            </div>

            <div class="form-group">
                <select class="form-control pf-form__select" name="r_user" required>
                 <option selected>Seleccione un tipo de usuario</option>
                  <option value="Cliente">Cliente</option>
                  <option value="Empresa">Empresa</option>
                </select>
            </div>
            
            <input class="pf-form__submit" type="submit" value="Reg??strate con tu e-mail">        
          </form>
          
          <span id="error_registro"> </span>

          <div class="pf-modal-sign-up__login">
            <span>??Ya tienes una cuenta de usuario?</span>
            <a data-id="login">Iniciar sesi??n</a>

            <p>
                Al registrarse con nosotros, aceptas nuestros <a href="#">T??rminos de Servicio</a>y <a href="#">Pol??tica de Privacidad</a>
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
          <h2 class="pf-modal-forgot__title">??Has olvidado tu contrase??a?</h2>

          <form class="pf-modal-forgot__form" action="#" method="POST">

            <div class="form-group">
                <label for="email"><img src="./Img/icons/icon-email-gray.svg" alt="Icono email"></label>
                <input type="email" class="form-control" name="email" aria-describedby="emailHelp" placeholder="E-mail" required>
            </div>



            <input class="pf-form__submit" type="submit" value="Restaurar tu contrase??a">        
          </form>

          <div class="pf-modal-forgot__login">
            <a data-id="login">Volver a iniciar sesi??n</a>
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
          <h2 class="pf-modal-verificacion__title">Verificar</h2>

          <form class="pf-modal-verificacion__form">

            <div class="form-group">
                <label for="codigo"><img src="./Img/icons/icon-lock-gray.svg" alt="Icono profile"></label>
                <input type="text" class="form-control" name="codigo" aria-describedby="nombrehelp" placeholder="C??digo" required>
            </div>


            <input class="pf-form__submit" type="submit" value="Verificar">        
          </form>
			<span id="error_verificacion"> </span>
        </div>
      </div>
    </div>
  </div>





</body>

</html>