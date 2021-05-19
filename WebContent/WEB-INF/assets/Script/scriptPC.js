$(document).ready(function () {
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

  $("*[data-id='login']").on("click", function () {
    $(".modal").css("display", "none");
    $(".pf-modal-sign-in").css("display", "block");
  });

  $("*[data-id='registrar']").on("click", function () {
    $(".modal").css("display", "none");
    $(".pf-modal-sign-up").css("display", "block");
  });

  $("*[data-id='forgot']").on("click", function () {
    $(".modal").css("display", "none");
    $(".pf-modal-forgot").css("display", "block");
  });

  $(".pf-modal-forgot__close").on("click", function () {
    $(this).parents(".pf-modal-forgot").css("display", "none");
  });

  $(".pf-modal-sign-in__close").on("click", function () {
    $(this).parents(".pf-modal-sign-in").css("display", "none");
  });
  $(".pf-modal-sign-up__close").on("click", function () {
    $(this).parents(".pf-modal-sign-up").css("display", "none");
  });

  $(".pf-modal-verificacion__close").on("click", function () {
    $(this).parents(".pf-modal-verificacion").css("display", "none");
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

  //// METODO PARA HACER POST DE REGISTRAR

  $(".pf-modal-sign-up__form").submit(function (e) {
    e.preventDefault();

    let nombre = $(".pf-modal-sign-up__form input[name='nombre']").val();
    let email = $(".pf-modal-sign-up__form input[name='email']").val();
    let password = $(".pf-modal-sign-up__form input[name='password']").val();
    let password_confirm = $(
      ".pf-modal-sign-up__form input[name='confirm_password']"
    ).val();
    let usuario = $(".pf-modal-sign-up__form select[name='r_user']").val();

    if (password === password_confirm) {
      $.post(
        "registrar",
        {
          nombre: nombre,
          email: email,
          password: password,
          confirm_password: password_confirm,
          usuario: usuario,
        },
        function (data, textStatus, jqXHR) {
          if (data != "") {
            let json = JSON.parse(data);

            if (json.error != "") {
              $("#error_registro").text(json.error);
            }

            if (json.ok == 1) {
              alert("Registro completo");

              document.location.href = "/inicio";
            } else {
              alert("No se ha podido completar la acción - Intentelo de nuevo");
            }
          }
        }
      );
    } else {
      $("#error_registro").text("Las contraseñas no coinciden");
    }
  });

  //METODO PARA HACER POST DE LOGIN

  $(".pf-modal-sign-in__form").submit(function (e) {
    e.preventDefault();

    let email = $(".pf-modal-sign-in__form input[name='email']").val();
    let password = $(".pf-modal-sign-in__form input[name='password']").val();
    let usuario = $(".pf-modal-sign-in__form select[name='r_user']").val();

    $.post(
      "iniciarsesion",
      {
        email: email,
        password: password,
        usuario: usuario,
      },
      function (data, textStatus, jqXHR) {
        if (data != "") {
          let json = JSON.parse(data);

          if (json.error != "") {
            $("#error_login").text(json.error);
          }

          if (json.ok == 1) {
            alert("Contraseña correcta - no verificado ");
            //DISPLAY BLOCK EL MODAL PARA LA VERIFICACION
            $(".pf-modal-verificacion").css("display", "block");
          } else if (json.ok == 2) {
            //saltar al inicio con el usuario iniciado
            document.location.href = "/inicio";
          } else {
            alert("No se ha podido completar la acción - Intentelo de nuevo");
          }
        }
      }
    );
  });

  //metodo post para la verificacion
  $(".pf-modal-verificacion__form").submit(function (e) {
    e.preventDefault();



    let codigo = $(".pf-modal-verificacion__form input[name='codigo']").val();

    $.post(
      "verificacion",
      {
        codigo: codigo,
      },
      function (data, textStatus, jqXHR) {
        if (data != "") {
          let json = JSON.parse(data);
          if (json.error != "") {
            $("#error_verificacion").text(json.error);
          }

          if (json.ok == 1) {
            alert("Verificacion exitosa");
            document.location.href = json.url;
          }
        }
      }
    );
  });
});
