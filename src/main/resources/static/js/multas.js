const campos = [
  "descripcion",
  "fecha_emitida",
  "fecha_limite",
  "monto",
];
const camposSelect = ["grupo", "ciudadano_id"];
const camposEditar = [
  "editar_descripcion",
  "editar_fecha_emitida",
  "editar_fecha_limite",
  "editar_monto",
];
var id;
$(document).ready(function () {
    initTable();

    $("#guardarMulta").click(function () {
      const data = recolectarDatos();
      console.log(data);
   
      let valido = true;
      if (data === "" || data === undefined) {
        valido = false;
      }
      if (valido) {
        enviarDatos(data, "guardar", "POST");
      }
    });
  
    $("#table_tareas").on("click", ".btn-eliminar", function () {
      var row = $(this).closest("tr");
      var data = $("#table_tareas").DataTable().row(row).data();
      id = data.id;
      enviarDatos(id, "eliminar", "PUT");
    });
  
    $("#table_tareas").on("click", ".btn-edit", function () {
      var row = $(this).closest("tr");
      var data = $("#table_tareas").DataTable().row(row).data();
      console.log(data);
      id = data.id;
      llenarCampos(data);
      
    });
  

    $("#editarMulta").click(function () {
      const data = recolectarDatosEditar();
      console.log(data);
      let valido = true;
      if (data === "" || data === undefined) {
        valido = false;
      }
      if (valido) {
        enviarDatos(data, "editar", "PUT");
      }
    });  

  
    $("#closeModalBtn").click(function () {
      $("#modalServidor").modal("hide");
    });
  
    $("#salirEditar").click(function () {
      $("#modalEditar").modal("hide");
    });
  
    $("#salirGuardar").click(function () {
      $("#modalAgregar").modal("hide");
    });
  
    $(document).ready(function () {
      $('[data-toggle="tooltip"]').tooltip();
    });
  
    $("#btnMenu").click(function () {
      $("#navbarNav").toggle();
    });
  });


//Recolecta datos del formulario
function recolectarDatos() {
  const dato = {};

  // Obtener los valores de los campos select
  for (let i = 0; i < camposSelect.length; i++) {
    const campo = camposSelect[i];
    const select = document.getElementById(campo);
    const valor = select.options[select.selectedIndex].value;

    if (valor === "Seleccione") {
      $(".alert-danger p").text(
        "Selecciona una opción válida para todos los campos"
      );
      $("#modalServidor").modal("show");
      return;
    }
    dato[campo] = valor;
  }

  // Obtener el valor del checkbox "pagado"
  const pagadoCheckbox = document.getElementById("pagado");
  const noPagadoCheckbox = document.getElementById("noPagado");
  dato["pagado"] = pagadoCheckbox.checked ? true : (noPagadoCheckbox.checked ? false : null);


  const fecha = document.getElementById("fecha_pagada").value.trim();

   // Marcar automáticamente el checkbox "pagado" o "noPagado" según la existencia de la fecha
   if (fecha) {
    pagadoCheckbox.checked = true;
    noPagadoCheckbox.checked = false;
    dato["pagado"] = true;
  } else {
    pagadoCheckbox.checked = false;
    noPagadoCheckbox.checked = true;
    dato["pagado"] = false;
  }

  campos.forEach(function (campo) {
    const valor = document.getElementById(campo).value;
    dato[campo] = valor;
  });

  console.log(dato);
  // Validar que todos los campos estén completos
  if (Object.values(dato).some((valor) => valor === "")) {
    $(".alert-danger p").text("Llenar todos los campos");
    $("#modalServidor").modal("show");
    return;
  }


  dato["fecha_pagada"] = fecha;
  return dato;
}


//Recolecta datos del formulario
function recolectarDatosEditar() {
  const dato = {};

  // Obtener el valor del checkbox "pagado"
  const pagadoCheckbox = document.getElementById("editar_pagado");
  const noPagadoCheckbox = document.getElementById("editar_noPagado");
  dato["pagado"] = pagadoCheckbox.checked ? true : (noPagadoCheckbox.checked ? false : null);

  const activoCheckbox = document.getElementById("editar_activo");
  const noActivoCheckbox = document.getElementById("editar_noActivo");
  dato["activo"] = activoCheckbox.checked ? true : (noActivoCheckbox.checked ? false : null);


  const fecha = document.getElementById("editar_fecha_pagada").value.trim();

   // Marcar automáticamente el checkbox "pagado" o "noPagado" según la existencia de la fecha
   if (fecha) {
    pagadoCheckbox.checked = true;
    noPagadoCheckbox.checked = false;
    dato["pagado"] = true;
  } else {
    pagadoCheckbox.checked = false;
    noPagadoCheckbox.checked = true;
    dato["pagado"] = false;
  }


  for (let i = 0; i < campos.length; i++) {
    const campo = campos[i];
    const campoEditar = camposEditar[i];
    const valor = document.getElementById(campoEditar).value;
    dato[campo] = valor;
  }

  console.log(dato);
  // Validar que todos los campos estén completos
  if (Object.values(dato).some((valor) => valor === "")) {
    $(".alert-danger p").text("Llenar todos los campos");
    $("#modalServidor").modal("show");
    return;
  }


  dato["fecha_pagada"] = fecha;
  return dato;
}


// Manejador de evento para el cambio en el select de grupo
$("#grupo").change(function () {
  // Obtener el valor seleccionado del select de grupo
  var grupoSeleccionado = $(this).val();
  $.getJSON(
    "/Dextho/ciudadano/ByGrupo/" + grupoSeleccionado,
    function (response) {
      // Aquí procesas la respuesta JSON
      // data es un array de objetos JSON, cada objeto representa un ciudadano
      // Se Extrae el array de objetos del objeto response
      const dataArray = response.data;
      // Por ejemplo, asumiendo que tienes un elemento select con el id "ciudadanos_id"
      const select = $("#ciudadano_id");

      // Limpiar las opciones existentes en el select
      select.empty();
      //Verificar si la respuesta esta vacia
      if (response.status === 0) {
        select.append($("<option>").text(response.data));
      } else {
        // Itera sobre los datos para generar las opciones del select
        $.each(dataArray, function (index, ciudadano) {
          const option = $("<option>")
            .val(ciudadano.id) // Asigna el valor del id del ciudadano
            .text(ciudadano.nombre); // Asigna el nombre del ciudadano como texto de la opción
          select.append(option);
        });
      }
    }
  );
});

function enviarDatos(data, accion, httpMetodo) {
  var url;
  switch (accion) {
    case "editar":
      url = "/Dextho/multas/actualizar/" + id;
      modal = "#modalEditar";
      break;
    case "eliminar":
      url = "/Dextho/multas/eliminar/" + id;
      break;
    case "guardar":
      url = "/Dextho/multas/guardar";
      modal = "#modalAgregar";
      break;
    default:
      break;
  }

  $.ajax({
    type: httpMetodo,
    contentType: "application/json",
    url: url,
    data: JSON.stringify(data),
    dataType: "json",
    success: function (response) {
      table.ajax.reload();
      $(modal).modal("hide");
      switch (accion) {
        case "editar":
         // limpiarInputsEditar(camposEditar);
          $(".alert-danger p").text("Ciudadano editado correctamente");
          $("#modalServidor").modal("show");
          break;
        case "eliminar":
          $(".alert-danger p").text("Tarea desactivada correctamente");
          $("#modalServidor").modal("show");
          break;
        case "guardar":
          //limpiarInputs(campos);
          $(".alert-danger p").text("Ciudadano guardado correctamente");
          $("#modalServidor").modal("show");
          break;
        default:
          break;
      }
    },
    error: function (error) {
      $(".alert-danger p").text(error);
      $("#modalServidor").modal("show");
    },
  });
}

// Add a click event handler to each checkbox
$("#editar_activo, #editar_noActivo").click(function() {
  // Check which checkbox was clicked
  const checkboxId = $(this).attr("id");
  
  // Depending on the clicked checkbox, set its value to true and the other checkbox's value to false
  if (checkboxId === "editar_activo") {
    $("#editar_activo").prop("checked", true);
    $("#editar_noActivo").prop("checked", false);
  } else {
    $("#editar_activo").prop("checked", false);
    $("#editar_noActivo").prop("checked", true);
  }
});

$("#editar_pagado, #editar_noPagado").click(function() {
  // Check which checkbox was clicked
  const checkboxId = $(this).attr("id");
  
  // Depending on the clicked checkbox, set its value to true and the other checkbox's value to false
  if (checkboxId === "editar_pagado") {
    $("#editar_pagado").prop("checked", true);
    $("#editar_noPagado").prop("checked", false);
  } else {
    $("#editar_pagado").prop("checked", false);
    $("#editar_noPagado").prop("checked", true);
  }
});

$("#pagado, #noPagado").click(function() {
  // Check which checkbox was clicked
  const checkboxId = $(this).attr("id");
  
  // Depending on the clicked checkbox, set its value to true and the other checkbox's value to false
  if (checkboxId === "pagado") {
    $("#pagado").prop("checked", true);
    $("#noPagado").prop("checked", false);
  } else {
    $("#pagado").prop("checked", false);
    $("#noPagado").prop("checked", true);
  }
});


function llenarCampos(data) {
  // Establecer los valores en los inputs
  $("#editar_fecha_emitida").val(data.fecha_emitida);
  $("#editar_fecha_limite").val(data.fecha_limite);
  $("#editar_fecha_pagada").val(data.fecha_pagada);
  // Setear el valor de los checkboxes "editar_pagado" y "editar_noPagado"
  if (data.pagado === true) {
    $("#editar_pagado").prop("checked", true);
    $("#editar_noPagado").prop("checked", false);
  } else {
    $("#editar_pagado").prop("checked", false);
    $("#editar_noPagado").prop("checked", true);
  }

  if (data.activo === true) {
    $("#editar_activo").prop("checked", true);
    $("#editar_noActivo").prop("checked", false);
  } else {
    $("#editar_activo").prop("checked", false);
    $("#editar_noActivo").prop("checked", true);
  }
  $("#editar_monto").val(data.monto);
  $("#grupo").val(data.grupo);
  $("#esitar_ciudadanos").val(data.ciudadano_id);
  $("#editar_descripcion").val(data.descripcion);

}

function initTable() {
    table = $("#table_tareas").DataTable({
      processing:true,
      ajax: {
        url: "/Dextho/multas/todos",
        error: function (xhr, status, error) {
          $(".alert-danger p").text(xhr.responseJSON.message);
          $("#modalServidor").modal("show");
          table.clear();
          table.row
            .add([
              "Sin datos",
              "Sin datos",
              "Sin datos",
              "Sin datos",
              "Sin datos",
              "Sin datos",
              "Sin datos",
            ])
            .draw();
        },
      },
      columns: [
        { data: "id" },
        { data: "ciudadano_id" },
        { data: "nombreUsuario" },
        { data: "grupo" },
        { data: "descripcion" },
        { data: "fecha_emitida" },
        { data: "fecha_limite" },
        { data: "fecha_pagada",
        "render": function(data, type, row) {
          // Si la fecha_pagada es null, mostrar "Pendiente"; de lo contrario, mostrar la fecha
          return data === null ? "Pendiente" : data;
        } 
      },
        { data: "monto",
          render: function (data, type, row){
            return "$" + parseFloat(data).toFixed(2).replace(/\d(?=(\d{3})+\.)/g, '$&,');
          } 
      },
        { data: "pagado", 
          render: function (data, type, row){
            return data ? "Si" : "No";
          }
        },
        { data: "activo",
          render: function (data, type, row){
            return data ? "Activo" : "Inactivo";
          }
       },
        {
          data: null,
          render: function (data, type, row) {
            return (
              '<div class="row"><div class="col"><button type="button" id="addTarea" data-id="' +
              data +
              '" class="btn-add btn btn-secondary" data-bs-toggle="modal" data-bs-target="#modalAgregar" data-toggle="tooltip" data-placement="top" title="Agregar nueva Tarea"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-person-fill-add" viewBox="0 0 16 16"><path d="M12.5 16a3.5 3.5 0 1 0 0-7 3.5 3.5 0 0 0 0 7Zm.5-5v1h1a.5.5 0 0 1 0 1h-1v1a.5.5 0 0 1-1 0v-1h-1a.5.5 0 0 1 0-1h1v-1a.5.5 0 0 1 1 0Zm-2-6a3 3 0 1 1-6 0 3 3 0 0 1 6 0Z"></path><path d="M2 13c0 1 1 1 1 1h5.256A4.493 4.493 0 0 1 8 12.5a4.49 4.49 0 0 1 1.544-3.393C9.077 9.038 8.564 9 8 9c-5 0-6 3-6 4Z"></path></svg></button>' +
              '<button type="button" id="editTarea" data-id="' +
              data +
              '" class="btn-edit btn btn-secondary" data-bs-toggle="modal" data-bs-target="#modalEditar" data-toggle="tooltip" data-placement="top" title="Editar Tarea"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-pencil-fill" viewBox="0 0 16 16"><path d="M12.854.146a.5.5 0 0 0-.707 0L10.5 1.793 14.207 5.5l1.647-1.646a.5.5 0 0 0 0-.708l-3-3zm.646 6.061L9.793 2.5 3.293 9H3.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.207l6.5-6.5zm-7.468 7.468A.5.5 0 0 1 6 13.5V13h-.5a.5.5 0 0 1-.5-.5V12h-.5a.5.5 0 0 1-.5-.5V11h-.5a.5.5 0 0 1-.5-.5V10h-.5a.499.499 0 0 1-.175-.032l-.179.178a.5.5 0 0 0-.11.168l-2 5a.5.5 0 0 0 .65.65l5-2a.5.5 0 0 0 .168-.11l.178-.178z"></path></svg></button>' +
              '</div></div>'
            );
          },
        },
      ],
      autoWidth: true,
      buttons: true,
      dom: 'B<"clear">lfrtip',
      buttons: {
        name: "primary",
        buttons: [
          {
            extend: "excel",
            exportOptions: {
              columns: [2, 3, 4, 5, 6,7,8,9,10],
            },
          },
          {
            extend: "pdf",
            exportOptions: {
              columns: [2, 3, 4, 5, 6,7,8,9,10],
            },
          },
        ],
      },
      select: true,
      autoFill: true,
      language: {
        info: "Mostrando pagina _PAGE_ de _PAGES_",
        search: "Buscar:",
        paginate: {
          first: "First",
          last: "Last",
          next: "Siguiente",
          previous: "Anterior",
        },
      },
    });
    table.columns([0,1]).visible(false);
    table.responsive.recalc();
  }
  