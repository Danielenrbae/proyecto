<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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

    <link rel="stylesheet" href="/WEB-INF/assets/Script/jquery-ui-1.12.1/jquery-ui.css" media="all">
    <link rel="stylesheet" href="/WEB-INF/assets/Script/jquerymodal/jquerymodal.css" media="all">
    <link rel="stylesheet" href="/WEB-INF/assets/CSS/bootstrap-4.1.3/bootstrap.css" media="all">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
    




    <script src="/WEB-INF/assets/Script/jquery-ui-1.12.1/jquery.js"></script>
    <script src="/WEB-INF/assets/Script/jquery-ui-1.12.1/jquery-ui.js"></script>
    <script src="/WEB-INF/assets/Script/jquerymodal/jquerymodal.js"></script>
    <script src="/WEB-INF/assets/Script/owl-carousel-2.3.4/owl.carousel.min.js"></script>  
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns" crossorigin="anonymous"></script>
    <script src="/WEB-INF/assets/Script/scriptPC.js"></script> 

</head>

<body>

<style>

  
	<%@ include file="/WEB-INF/assets/Script/owl-carousel-2.3.4/owl.carousel.min.css"%>
	<%@ include file="/WEB-INF/assets/Script/owl-carousel-2.3.4/owl.theme.default.min.css"%>
	<%@ include file="/WEB-INF/assets/CSS/themes/theme.css"%>
	<%@ include file="/WEB-INF/assets/CSS/stylesPC.css"%>

</style>

    




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
            <li class="pf-menu__item"><a href="#">EXPLORAR</a></li>
            <li class="pf-menu__item"><a href="#">SOBRE NOSOTROS</a></li>
            <li class="pf-menu__item"><a href="#">CONTACTO</a></li>

        </ul>

        
     <div class="pf-container__logged">
        
                <div class="pf-container__auth">

                                            <div class="pf-auth__buttons">
                
                            <a class="pf-auth__login" href="#">INICIAR SESIÓN</a>
                            



<div class="pf-button-primary ">
    <a class="pf-button-primary__text" href="#">REGISTRAR</a>
</div>                        </div>
                    
                </div>
       
            <div class="pf-container__cart">
                <a href="#"><img src="./Img/icons/shopping-bag.svg" alt="Carrito"/></a>
            </div>
     </div>

     </div>
    </nav>




    <div class="pf-nav__desplegable">
        <ul class="pf-container__menu">

            <li class="pf-menu__item"><a href="inicio">INICIO</a></li>
            <li class="pf-menu__item"><a href="#">EXPLORAR</a></li>
            <li class="pf-menu__item"><a href="#">SOBRE NOSOTROS</a></li>
            <li class="pf-menu__item"><a href="#">CONTACTO</a></li>

        </ul>

     
        <div class="pf-container__auth">
      
                            <div class="pf-auth__buttons">
        
                    <a class="pf-auth__login" href="#">INICIAR SESIÓN</a>
                    



<div class="pf-button-primary ">
    <a class="pf-button-primary__text" href="#">REGISTRAR</a>
</div>                </div>
                    
        </div>
    </div>
<section class="pf-info1">
    <h2 class="pf-info1__title">¿QUÉ OFRECEMOS?</h2>
    <h3 class="pf-info1__subtitle">TU SOCIO DE ENTREGA DE COMIDA A DOMICILIO</h3>

    <div class="pf-info1__container">
        <div class="pf-container__card">
            <img src="./Img/common/easy-to-use.svg" alt="icono easy to use"/>
            <h3 class="pf-card__text">Fácil de usar</h3 class="pf-card__text">
        </div>

        <div class="pf-container__card">
            <img src="./Img/common/truck.svg" alt="icono camion"/>
            <h3 class="pf-card__text">Entrega más rápida</h3 class="pf-card__text">
        </div>

        <div class="pf-container__card">
            <img src="./Img/common/medal.svg" alt="icono medalla"/>
            <h3 class="pf-card__text">Mejor calidad</h3 class="pf-card__text">
        </div>
    </div>
</section>
<section class="pf-info2">

   <div class="pf-info2__title"> <h2>EXAMINA LOS PRODUCTOS</h2></div>
   <div class="pf-info2__subtitle">
       <h3 class="pf-subtitle__text">Categorías Destacadas</h3>
       <a href="#"> Todos los productos </a>
   </div>

   <div class="pf-info2__container">
       <div class="pf-container__item"><a href="#"><img src="./Img/dynamic/categoria1.jpg" alt="Imagen comida Japonesa"><p class="pf-item__text">Comida Japonesa</p class="pf-item__text"></a></div>
       <div class="pf-container__item"><a href="#"><img src="./Img/dynamic/categoria2.jpg" alt="Imagen comida Americana"><p class="pf-item__text">Comida Americana</p class="pf-item__text"></a></div>
       <div class="pf-container__item"><a href="#"><img src="./Img/dynamic/categoria3.jpg" alt="Imagen comida Italiana"><p class="pf-item__text">Comida Italiana</p class="pf-item__text"></a></div>
       <div class="pf-container__item"><a href="#"><img src="./Img/dynamic/categoria4.jpg" alt="Imagen comida Mexicana"><p class="pf-item__text">Comida Mexicana</p class="pf-item__text"></a></div>
   </div>

</section><section class="pf-info3">
  <h2 class="pf-info3__title">Nuestros Socios</h2>
  <div class="pf-info3__container">
          <div class="pf-container__item img-fluid"><img src="./Img/common/sushiko.svg" alt="Logo empresa sushiko" /></div>
          <div class="pf-container__item img-fluid"><img src="./Img/common/burger-king.svg" alt="Logo empresa burger king" /></div>
          <div class="pf-container__item img-fluid"><img src="./Img/common/pizza-hut.svg" alt="Logo empresa pizza hut" /></div>
          <div class="pf-container__item img-fluid"><img src="./Img/common/coca-cola.svg" alt="Logo empresa coca cola" /></div>
</section>

<section class="pf-info4">
    <h2 class="pf-info4__title">PARA LAS EMPRESAS</h2>
    <h3 class="pf-info4__subtitle">Empieza tu Camino Aquí</h3>

    <div class="pf-info4__container">
        <div class="pf-container__info">
            <div class="pf-info__text">
                <p>Controla tu pedido desde la preparación hasta la entrega</p>
                <p>Haz que tu marca crezca gracias a nuetra base de datos</p>
                <p>Mejora tus ventas y aumenta tus beneficios</p>
                <p>Entrega tus pedidos de forma rápida y sencilla</p>
            </div>

            <div class="pf-info__buttons">
                <h3 class="pf-buttons__title">¡No esperes más!</h3>

                



<div class="pf-button-primary  pf-button-primary--fill ">
    <a class="pf-button-primary__text" href="#">Únete ya</a>
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
        <h2 class="pf-container__title">PARA UNA MEJOR VIDA</h2>
        <h3 class="pf-container__subtitle">Conviértete en Parte de Nuestro Viaje</h3>

        



<div class="pf-button-primary  pf-button-primary--fill ">
    <a class="pf-button-primary__text" href="#">Únete ya</a>
</div>    </div>

    <div class="pf-footer__nav">
        <div class="pf-nav__logo">
            <a href="inicio"><img class="img-fluid" src="./Img/common/favicon-blanco.png" alt="Logo"/></a>
        </div>
    
        <div class="pf-nav__container">
    
            <ul class="pf-container__menu">
    
                <li class="pf-menu__item"><a href="inicio">INICIO</a></li>
                <li class="pf-menu__item"><a href="#">EXPLORAR</a></li>
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
    
    <div class="pf-footer__policy">
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