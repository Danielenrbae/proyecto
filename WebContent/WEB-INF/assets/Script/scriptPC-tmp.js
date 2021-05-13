
$(".pf-carousel__owl").owlCarousel({
  loop: true,
  margin: 50,
  nav: true,
  dots: false,
  autoWidth: false,
  items: 4,
  mouseDrag: true,
  responsive: {
    0: {
      items: 1,
      dots: true,
    },
    600: {
      items: 2,
    },
    1000: {
      items: 3,
    },
  },
});













$(".pf-modal-producto__close").on("click", function () {
        $(this).parents().css("display" , "none");
}); 


$(".pf-modal-sign-in__close").on("click", function () {
    // $(".pf-modal-sign-in").css("display", "none");
    $(this).parents().css("display" , "none");
});
$(".pf-modal-sign-up__close").on("click", function () {
    $(this).parents().css("display" , "none");
});
$(".pf-container__desplegable").on("click", function () {
  $(".pf-nav__desplegable").toggleClass("show");
});

$(".pf-auth__icon").on("click", function () {
  $(".pf-auth__options").toggle();
});


$(".pf-container__desplegable").on("click", function () {
    $(".pf-nav-bussiness__desplegable").toggleClass("show");
  });
  $(".pf-container__desplegable").on("click", function () {
    $(".pf-nav-bussines__desplegable").toggleClass("show");
  });
  

$(".pf-buttons__minus").on("click", function () {
  const old_val = $(".pf-buttons__input").text();

  if (comprobarCantidad(old_val))
    $(".pf-buttons__input").text(parseInt(old_val) - 1);
});

$(".pf-buttons__plus").on("click", function () {
  const old_val = $(".pf-buttons__input").text();

  if (comprobarCantidad(old_val) || parseInt(old_val) == 0)
    $(".pf-buttons__input").text(parseInt(old_val) + 1);
});

function comprobarCantidad(num) {
  let result;
  result = false;
  if (num > 0 && num < 99) result = true;
  return result;
}


