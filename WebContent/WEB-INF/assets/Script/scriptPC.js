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
  
  $(".pf-categorias__list").owlCarousel({
	    loop: true,
	    margin: 0,
	    nav: false,
	    dots: false,
	    autoWidth: true,
	    items: 7,
	    mouseDrag: true
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

  $("#addProduct").on("click", function () {
    $(".modal").css("display", "none");
    $(".pf-modal-producto").css("display", "block");
  });

  $(".pf-modal-forgot__close").on("click", function () {
    $(this).parents(".pf-modal-forgot").css("display", "none");
  });

  $(".pf-modal-producto__close").on("click", function () {
    $(this).parents(".pf-modal-producto").css("display", "none");
  });

  $(".pf-modal-sign-in__close").on("click", function () {
    $(this).parents(".pf-modal-sign-in").css("display", "none");
  });
  $(".pf-modal-sign-up__close").on("click", function () {
    $(this).parents(".pf-modal-sign-up").css("display", "none");
  });

  $(".pf-modal-delete__close").on("click", function () {
    $(this).parents(".pf-modal-delete").css("display", "none");
  });

  $("#cancelarDelete").on("click", function () {
    $(this).parents(".pf-modal-delete").css("display", "none");
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

  // // METODO PARA HACER POST DE REGISTRAR

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

  // METODO PARA HACER POST DE LOGIN

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
            // DISPLAY BLOCK EL MODAL PARA LA VERIFICACION
            $(".pf-modal-verificacion").css("display", "block");
          } else if (json.ok == 2) {
            // saltar al inicio con el usuario iniciado
            document.location.href = json.url;
          } else {
            alert("No se ha podido completar la acción - Intentelo de nuevo");
          }
        }
      }
    );
  });

  // metodo post para la verificacion
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

  // metodo post INSERTAR PRODUCTO

  $("#insertar").on("click", function (e) {
    e.preventDefault();

    let nombre = $(".pf-modal-producto input[name='nombre']").val();
    let categoria = $(".pf-modal-producto select[name='categoria']").val();
    let precio = $(".pf-modal-producto input[name='precio']").val();
    let descripcion = $(
      ".pf-modal-producto textarea[name='descripcion']"
    ).val();
    // let foto = $(".pf-modal-producto input[name='foto']").val();

    let peso = $(".pf-modal-producto input[name='peso']").val();
    let fibra = $(".pf-modal-producto input[name='fibra']").val();
    let venergeticokj = $(
      ".pf-modal-producto input[name='v-energeticokj']"
    ).val();
    let venergeticokcal = $(
      ".pf-modal-producto input[name='v-energeticokc']"
    ).val();
    let grasas = $(".pf-modal-producto input[name='grasas']").val();
    let proteinas = $(".pf-modal-producto input[name='proteinas']").val();
    let acsaturados = $(".pf-modal-producto input[name='ac-saturados']").val();
    let hcarbono = $(".pf-modal-producto input[name='h-carbono']").val();
    let sal = $(".pf-modal-producto input[name='sal']").val();
    let energia = $(".pf-modal-producto input[name='energia']").val();
    let azucares = $(".pf-modal-producto input[name='azucares']").val();

    let formulario = document.getElementById("imagen_Form");

    // comprobar que ninguno esta vacio excepto la foto

    let formimg = new FormData(document.forms.namedItem("fileinfo"));
    $.ajax({
      type: "POST",
      url: "subirFoto",
      data: formimg,
      processData: false,
      contentType: false,
      cache: false,
      success: function (response) {
        alert("correcto");
      },
    });

    $.post(
      "insertarproducto",
      {
        nombre: nombre,
        categoria: categoria,
        descripcion: descripcion,
        precio: precio,
        energia: energia,
        peso: peso,
        valorkj: venergeticokj,
        valorkcal: venergeticokcal,
        fibra: fibra,
        proteinas: proteinas,
        grasas: grasas,
        sal: sal,
        hidratos: hcarbono,
        grasos_saturados: acsaturados,
        azucares: azucares,
      },
      function (data, textStatus, jqXHR) {
        if (data != "") {
          let json = JSON.parse(data);

          if (json.ok == 1) {
            alert("Producto insertado correctamente");

            $(".pf-modal-producto__principal")[0].reset();
            $(".pf-second__form")[0].reset();
          } else {
            alert("No se ha podido completar la acción - Intentelo de nuevo");
          }
        } else {
          alert("No se ha podido completar la acción - Intentelo de nuevo");
        }
      }
    );
  });

  //Metodo para borrar un producto

  $(".pf-buttons__trash").on("click", function () {
    let id_producto;

    id_producto = $(this).siblings(".seeProduct").attr("data-id");

    //coger foto del producto

    $.get(
      "eliminarProducto",
      {
        idproducto: id_producto,
      },
      function (data, textStatus, jqXHR) {

        
        if (data != null) {
          let json = JSON.parse(data);

          if (json.ok == 1) {
            document.location.href = "/mantenimiento?delete=true"
            
            $.get("bajarFoto", {
              idproducto : id_producto,
              param_img : "producto"
            },
              function (data, textStatus, jqXHR) {
                console.log("Todo correcto");
              }
            );
        
            
          }
        }
      }
    );
  });

  //metodo para eliminar un producto

$("#pf-button__confirm-delete").on("click", function () {
 
  $.post("eliminarProducto", {},
    function (data, textStatus, jqXHR) {
       
      if (data != null) {
        let json = JSON.parse(data);

        if (json.error != "") {
            $("#errorDelete").val(json.error);
        }

        if (json.ok == 1) {
          alert("Eliminador correctamente");
          document.location.href = "mantenimiento";
          
        }
      }
    }
  );

});
      

  // config

  const labels = ["January", "February", "March", "April", "May", "June"];
  const data = {
    labels: labels,
    datasets: [
      {
        label: "Pedidos recibidos",
        backgroundColor: "#2E5056",
        borderColor: "#2E5056",
        data: [20, 10, 7, 2, 77, 15, 100],
      },
      {
        label: "Pedidos entregados",
        backgroundColor: "#24BFA3",
        borderColor: "#24BFA3",
        data: [0, 10, 5, 2, 20, 30, 45],
      },
      {
        label: "Pedidos rechazados",
        backgroundColor: "#011F26",
        borderColor: "#011F26",
        data: [4, 2, 5, 8, 1, 5, 4],
      },
    ],
  };

  const config = {
    type: "line",
    data,
    options: {},
  };

  var myChart = new Chart(document.getElementById("chart_resumen"), config);

  $("#table_id").DataTable();
  $("#table_id2").DataTable();
});
