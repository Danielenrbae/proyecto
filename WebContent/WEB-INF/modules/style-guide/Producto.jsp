<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="modelos.Comprador"%>
<%@page import="modelos.Producto"%>

<!DOCTYPE html>
<html lang="es">


<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="robots" content="index, follow">
    <meta charset="utf-8" />        
    <link rel="shortcut icon" href="./Img/dynamic/bigmac.png" type="image/png" >
    
    <meta name="application-name" content="Producto" />
    <title>Producto</title>

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
	boolean iniciado;
	
	Producto producto;
	
	boolean binformacion;
	
	iniciado = false;
	
	producto= null;
	
	binformacion= false;
	
	if(session.getAttribute("usuario") != null){
		iniciado = true;
		comprador = (Comprador) session.getAttribute("usuario");
	}
	
	if(session.getAttribute("product") != null){
		producto = (Producto) session.getAttribute("product");
	}
	
	if(session.getAttribute("info_product") == null){
		%>
			<jsp:useBean id="info_product" class="modelos.InformacionNutricional" scope="session"></jsp:useBean>
		<%
	}else{
		binformacion = true;
	}
	

%>

<jsp:useBean id="product" class="modelos.Producto" scope="session"></jsp:useBean>

		

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
	
		         <div class="pf-auth__options">
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
	   	<li class="breadcrumb-item" aria-current="page"><a href="explorar">Explorar</a></li>
	   	
	   	<li class="breadcrumb-item" aria-current="page"><a href="compraProducto?id=<%=producto.getId_producto()%>"> <jsp:getProperty property="nombre" name="product"/> </a></li>
	   	
	    
	  </ol>
	</nav>
	
	
	<section class="pf-product">
    <h2 class="pf-product__title"><jsp:getProperty property="nombre" name="product"/></h2>

    <div class="pf-product__container">
        <div class="pf-container__general">
        
        	<%
        		if(producto.getFoto() != null){
        			
        			%>
        				<img src="bajarFoto?param_img=producto&idproducto=<%= producto.getId_producto()%>" alt=""/>
        			<%
        			
        		}else{
        			
        			%>
        				<img class="card-img-top img-fluid" src="./Img/common/pf-default-image.png"/>         	
        			<%
        		}
        	%>
        
            <div class="pf-general__data">
                <div class="pf-data__description">
                    <span>DESCRIPCI??N</span>
                    <p> <jsp:getProperty property="descripcion" name="product"/> </p>
                </div>

                <div class="pf-data__cantidad">
                   
                        <div class="pf-cantidad__buttons">
                            <button class="pf-buttons__minus"></button>
                                <span class="pf-buttons__input">0</span>
                            <button class="pf-buttons__plus"></button>
                        </div>
                        <div class="pf-cantidad__precio">
                            <span>PRECIO</span>
                            <p><jsp:getProperty property="precio" name="product"/> EUROS</p>
                        </div>
                   

				<div class="pf-button-secondary  pf-button-primary--fill ">
<!-- 					cambiar el controlador del add carrito -->
				    <button <% if (!iniciado) out.print("disabled");%> class="pf-button-primary__text" data-href="compraProducto?id=<%=producto.getId_producto() %>" >A??adir al carrito</button>
				</div>               
				
				 </div>
            </div>

            
        </div>
        
        
        <%
        	if (binformacion){
        		%>
        		
        <div class="pf-container__informacion">
            <h3 class="pf-informacion__title">Informaci??n Nutricional</h3>

            <div class="accordion" id="accordionExample">
                <div class="card">
                  <div class="card-header" id="headingOne">
                    <h2 class="mb-0">
                      <button class="btn btn-link btn-block text-left" type="button" data-toggle="collapse" data-target="#info_nutri" aria-expanded="false" aria-controls="info_nutri">
                        Informaci??n nutricional
                        <span> </span>
                      </button>
                    </h2>
                  </div>
              
                  <div id="info_nutri" class="collapse" aria-labelledby="headingOne" data-parent="#accordionExample">
                    <div class="card-body">

                        <div class="pf-body__energy">
                            <p> <jsp:getProperty property="energia" name="info_product"/> </p>
                            <span>Energ??a</span>
                        </div>
                        <div class="pf-body__datos">
                            <ul>
                                <li>
                                    <div class="form-group">
                                        <label for="peso">Peso(g)</label>
                                        <p><jsp:getProperty property="peso" name="info_product"/>g</p>
                                    </div>
                                </li>
                                <li>   
                                    <div class="form-group">
                                        <label for="fibra">Fibra(g)</label>
                                        <p><jsp:getProperty property="fibra" name="info_product"/>g</p>
                                    </div>
                                </li>
                                <li>
                                    <div class="form-group">
                                        <label for="v-energeticokj">Valor Energ??tico(kJ)</label>
                                        <p><jsp:getProperty property="valorkj" name="info_product"/>kJ</p>
                                    </div>
                                </li>
                                <li>
                                    <div class="form-group">
                                        <label for="azucares">Az??cares(g)</label>
                                        <p><jsp:getProperty property="azucares" name="info_product"/>g</p>
                                    </div>
                                </li>
                                <li>
                                    <div class="form-group">
                                        <label for="v-energeticokc">Valor Energ??tico(kcal)</label>
                                        <p><jsp:getProperty property="valorkcal" name="info_product"/>Kcal</p>
                                    </div>
                                </li>
                                <li>
                                    <div class="form-group">
                                        <label for="grasas">Grasas(g)</label>
                                        <p><jsp:getProperty property="grasas" name="info_product"/>g</p>
                                    </div>
                                </li>
                                <li>
                                    <div class="form-group">
                                        <label for="proteinas">Prote??nas(g)</label>
                                        <p><jsp:getProperty property="proteinas" name="info_product"/>g</p>
                                    </div>
                                </li>
                                <li>
                                    <div class="form-group">
                                        <label for="ac-saturados">??c. Grasos Saturados(g)</label>
                                        <p><jsp:getProperty property="grasos_saturados" name="info_product"/>g</p>
                                    </div>
                                </li>
                                <li>
                                    <div class="form-group">
                                        <label for="h-carbono">Hidratos de Carbono(g)</label>
                                        <p><jsp:getProperty property="hidratos" name="info_product"/>g</p>
                                    </div>
                                </li>
                                <li>
                                    <div class="form-group">
                                        <label for="sal">Sal(g)</label>
                                        <p><jsp:getProperty property="sal" name="info_product"/>g</p>
                                    </div>
                                </li>
                            </ul>
                        </div>
                    </div>
                  </div>
                </div>
             
             
              </div>
        </div>
        		<%
        	}else{
        		
        		%>
        		    <div class="pf-container__informacion">
        			
        				<h4> Este producto no contiene informaci??n nutricional.</h4>
        			
        			</div>
        		<%
        	}
        %>
        

    </div>

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