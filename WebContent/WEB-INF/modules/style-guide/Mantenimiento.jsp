<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="modelos.Empresa"%>
<%@page import="modelos.Producto"%>
<!DOCTYPE html>
<html lang="es">


<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="robots" content="index, follow">
    <meta charset="utf-8" />
    <meta name="application-name" content="Inicio" />
    <title>Inicio</title>


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

    
<jsp:useBean id="usuario" scope="session" class="modelos.Empresa"></jsp:useBean>

<nav class="pf-nav-bussines">
  <div class="pf-nav-bussines__logo">
    <img
      class="img-fluid"
      src="./Img/common/favicon.png"
      alt="Logo"
    />
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
    <li class="pf-menu__item"><a href="#">PEDIDOS</a></li>
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
    <li class="breadcrumb-item" aria-current="page"><a href="resumen">Inicio</a></li>
    <li class="breadcrumb-item" aria-current="page"><a href="mantenimiento">Mantenimiento</a></li>
    
  </ol>
</nav>
<div class="header-resumen">

</div>



<section class="pf-mantenimiento">
    <h2 class="pf-mantenimiento__title">REALIZA</h2>
    <h3 class="pf-mantenimiento__subtitle">Mantenimiento</h3>

    



<div class="pf-button-primary  pf-button-primary--fill ">
    <a id="addProduct" class="pf-button-primary__text" >Añadir Producto</a>
</div>
    <article class="pf-mantenimiento__table">
             
        <table id="table_id2" class="pf-table">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>PRODUCTO </th>
                    <th>CATEGORIA</th>
                    <th>PRECIO</th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
            
            	<%
          			Producto[] productos;
            	
            		productos = (Producto[]) session.getAttribute("productos");
            		
            		for(int i = 0; i < productos.length; i++){
            			            			
            			%>
            			 <tr>
                    <td><%= productos[i].getId_producto() %></td>
                    <td><%= productos[i].getNombre() %></td>
                    
                    <!--  ENCONTRAR EL NOMBRE DE LA CATEGORIA -->
                    <td><%= productos[i].getId_categoria() %></td>
                    
                    <td><%= productos[i].getPrecio() %></td>
                    <td>
                        <div class="pf-table__buttons">

                            
                            <button data-id="<%= productos[i].getId_producto() %>">
                                Ver producto
                            </button>

                            <div class="pf-buttons__trash">
                            </div>

                        
                        </div>

                    </td>
                </tr>
            			<%
            		}
            	
            	%>
            
                
   
            </tbody>
        </table>


        <section class="pf-mantenimiento__producto">

            <h2 class="pf-producto__title">Datos del Producto</h2>

            <form class="pf-producto-form">

                <div class="form-row">

                    <div class="pf-row__part1">
                        <img src="" alt="Imagen producto">
                        <input type="file" name="imagen">
                    </div>

                    <div class="pf-row__part2">
                            
                        <div class="row">
                            <div class="form-group">
                                <label for="identificador">IDENTIFICADOR</label>
                                <input type="text" name="identificador" class="form-control" disabled >
                            </div>
                        </div>

                        <div class="row">
                            <div class="form-group">
                                <label for="nombre">NOMBRE DEL PRODUCTO</label>
                                <input type="text" class="form-control" name="nombre" >
                            </div>
                        </div>

                        <div class="row">
                            <div class="form-group">
                                <label for="categoria">CATEGORIA</label>
                                <select class="form-control pf-form__select" name="categoria" placeholder="Seleccione una categoria" title="Seleccione una categoria" required>
                                 <option selected>Seleccione una categoria</option>
                             
                          	<%
                          		String[] categorias;
                            	int contador_categorias;
                            	boolean salir_categorias;
                            		
                            	contador_categorias = 0;
                            	salir_categorias= false;
                            	
                            	categorias = (String[]) session.getAttribute("categorias");
                            		
                            	while(!salir_categorias){
                            		
                            		if(categorias[contador_categorias] != null){                            			                            			
                            			%>
                            				<option> <%= categorias[contador_categorias]%> </option>
                            			<%
                            			contador_categorias++;
                            		}else{                            		
                            			salir_categorias= true;
                            		}                            		
                            	}                            		
                          	%>
                          	
                                </select>
                            </div>
                        </div>

                        <div class="row">
                            <div class="form-group">
                                <label for="precio">PRECIO</label>
                                <input type="number" class="form-control" name="precio" >
                            </div>
                        </div>

                        <div class="row">
                            <div class="form-group">
                                <label for="descripcion">DESCRIPCION</label>
                                <textarea name="descripcion"></textarea>
                                <small>Máximo 500 caracteres</small>
                            </div>
                        </div>
                    </div>
                </div>

            </form>


        </section>
        
    </article>
</section>

<footer class="pf-footer-bussiness">

    <div class="pf-footer-bussiness__nav">
        <div class="pf-nav__logo">
            <a href=""><img class="img-fluid" src="./Img/common/favicon-blanco.png" alt="Logo"/></a>
        </div>
    
        <div class="pf-nav__container">
    
            <ul class="pf-container__menu">
    
                <li class="pf-menu__item"><a href="#">RESUMEN</a></li>
                <li class="pf-menu__item"><a href="#">PEDIDOS</a></li>
                <li class="pf-menu__item"><a href="#">MANTENIMIENTO</a></li>
                <li class="pf-menu__item"><a href="#">SOBRE NOSOTROS</a></li>
                <li class="pf-menu__item"><a href="#">CONTACTO</a></li>
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


<!-- Modal -->
<div 
  class="modal pf-modal-producto"
  id="exampleModal"
  tabindex="-1"
  aria-labelledby="exampleModalLabel"
  aria-hidden="true"
 
>
  <div class="modal-dialog modal-dialog-scrollable pf-modal-producto">
    <div class="modal-content">
      <span class="pf-modal-producto__close"></span>
      <div class="modal-body">
        <h2 class="pf-modal-producto__title">Datos del Producto</h2>
        
	      
			<form id="imagen_Form" name="fileinfo">  
	                    <input type="file" name="foto"/>
	         </form>
        <form id="form_multipart" class="pf-modal-producto__principal">

            <div class="pf-principal__principal row">
                <div class="pf-principal__img">
               
               
                    <img src="./Img/common/pf-default-image.png" alt="Icono default">
         
                    
                </div>
                <div class="pf-principal__data">
                    <div class="form-row">
                        <div class="form-group">
                            <label for="nombre">NOMBRE DEL PRODUCTO</label>
                            <input type="text" class="form-control" name="nombre" placeholder="Nombre del producto" required>
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="form-group">
                            <label for="categoria">CATEGORIA</label>
                            <select class="form-control pf-form__select" name="categoria" placeholder="Seleccione un tipo de usuario" title="Seleccione un tipo de usuario" required>
                             <option selected>Seleccione una categoria</option>
                          
                          
                          	<%
                          		                            		
                            	contador_categorias = 0;
                            	salir_categorias= false;
                            	                            
                            		
                            	while(!salir_categorias){
                            		
                            		if(categorias[contador_categorias] != null){                            			                            			
                            			%>
                            				<option> <%= categorias[contador_categorias]%> </option>
                            			<%
                            			contador_categorias++;
                            		}else{                            		
                            			salir_categorias= true;
                            		}                            		
                            	}                            		
                          	%>
                          	                          	                                                  
                            </select>
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="form-group">
                            <label for="precio">PRECIO</label>
                            <input type="number" class="form-control" name="precio" placeholder="Precio" required> 
                        </div>
                    </div>
                </div>
            </div>

            <div class="pf-principal__second row">
                <div class="form-group">
                    <label for="descripcion">DESCRIPCION</label>
                    <textarea name="descripcion"></textarea>
                    <small>Máximo 500 caracteres</small>
                </div>
                
            </div>
        </form>

        <h2 class="pf-modal-producto__title">Información Nutricional</h2>

        <div class="pf-modal-producto__second">

             <form class="pf-second__form">

                <div class="pf-form__energy">
                    <input type="text" name="energia" required>
                    <label for="energia">Energía</label>
                </div>

                <div class="pf-form__datos">
                    <ul>
                        <li>
                            <div class="form-group">
                                <label for="peso">Peso(g)</label>
                                <input type="text" name="peso" required >
                            </div>
                        </li>
                        <li>   
                            <div class="form-group">
                                <label for="fibra">Fibra(g)</label>
                                <input type="text" name="fibra" required>
                            </div>
                        </li>
                        <li>
                            <div class="form-group">
                                <label for="v-energeticokj">Valor Energético(kJ)</label>
                                <input type="text" name="v-energeticokj" required>
                            </div>
                        </li>
                        <li>
                            <div class="form-group">
                                <label for="azucares">Azúcares(g)</label>
                                <input type="text" name="azucares" required>
                            </div>
                        </li>
                        <li>
                            <div class="form-group">
                                <label for="v-energeticokc">Valor Energético(kcal)</label>
                                <input type="text" name="v-energeticokc" required>
                            </div>
                        </li>
                        <li>
                            <div class="form-group">
                                <label for="grasas">Grasas(g)</label>
                                <input type="text" name="grasas" required>
                            </div>
                        </li>
                        <li>
                            <div class="form-group">
                                <label for="proteinas">Proteínas(g)</label>
                                <input type="text" name="proteinas" required>
                            </div>
                        </li>
                        <li>
                            <div class="form-group">
                                <label for="ac-saturados">Ác. Grasos Saturados(g)</label>
                                <input type="text" name="ac-saturados" required>
                            </div>
                        </li>
                        <li>
                            <div class="form-group">
                                <label for="h-carbono">Hidratos de Carbono(g)</label>
                                <input type="text" name="h-carbono" required>
                            </div>
                        </li>
                        <li>
                            <div class="form-group">
                                <label for="sal">Sal(g)</label>
                                <input type="text" name="sal" required>
                            </div>
                        </li>
                    </ul>
                </div>

                           
              
                <div >
                    <input id="insertar" type="submit" value="Insertar producto">
                </div>
             </form>
             
              
             

            
        </div>
      </div>
    </div>
  </div>
</div>






</body>

</html>