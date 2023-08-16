const campos = [
  "nombre",
  "descripcion",
  "fecha",
];

const camposEditar = [
  "editarNombre",
  "editarDescripcion",
  "editarFecha",
];
const camposSelect = ["grupo", "ciudadano_id"];
var id;
$(document).ready(function () {
    initTable();

    
    $("#guardarCargo").click(function () {
      const data = recolectarDatos();
    
      let valido = true;
      if (data === "" || data === undefined) {
        valido = false;
      }
      if (valido) {
        enviarDatos(data, "guardar", "POST");
      }
    });
  
  
    $("#table_tareas").on("click", ".btn-edit", function () {
      var row = $(this).closest("tr");
      var data = $("#table_tareas").DataTable().row(row).data();
      id = data.id;
      llenarCampos(data);
    });

    $("#editarCargo").click(function () {
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

    if (valor === "Seleccione" || valor === "Datos no encontrados") {
      $(".alert-danger p").text(
        "Selecciona una opción válida para todos los campos"
      );
      $("#modalServidor").modal("show");
      return;
    }
    dato[campo] = valor;
  }


  campos.forEach(function (campo) {
    const valor = document.getElementById(campo).value;
    dato[campo] = valor;
  });

  // Validar que todos los campos estén completos
  if (Object.values(dato).some((valor) => valor === "")) {
    $(".alert-danger p").text("Llenar todos los campos");
    $("#modalServidor").modal("show");
    return;
  }

  return dato;
}

//Recolecta datos del formulario
function recolectarDatosEditar() {
  const dato = {};

  for (let i = 0; i < campos.length; i++) {
    const campo = campos[i];
    const campoEditar = camposEditar[i];
    const valor = document.getElementById(campoEditar).value;
    dato[campo] = valor;
  }

  // Validar que todos los campos estén completos
  if (Object.values(dato).some((valor) => valor === "")) {
    $(".alert-danger p").text("Llenar todos los campos");
    $("#modalServidor").modal("show");
    return;
  }
  return dato;
}

function limpiarInputs(inputsIds) {
  inputsIds.forEach(function (inputId) {
    if (document.getElementById(inputId)) {
      document.getElementById(inputId).value = "";
    }
  });
  $("#ciudadano_id").val("Seleccione");
  $("#grupo").val("Seleccione");
}

function enviarDatos(data, accion, httpMetodo) {
  var url;
  switch (accion) {
    case "editar":
      url = "http://localhost:8080/Dextho/cargos/actualizar/" + id;
      modal = "#modalEditar";
      break;
    case "eliminar":
      url = "http://localhost:8080/Dextho/cargos/eliminar/" + id;
      break;
    case "guardar":
      url = "http://localhost:8080/Dextho/cargos/guardar";
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
         limpiarInputsEditar(camposEditar);
          $(".alert-danger p").text("Ciudadano editado correctamente");
          $("#modalServidor").modal("show");
          break;
        case "eliminar":
          $(".alert-danger p").text("Tarea desactivada correctamente");
          $("#modalServidor").modal("show");
          break;
        case "guardar":
          limpiarInputs(campos);
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

function llenarCampos(data) {
  // Establecer los valores en los inputs
  $("#editarNombre").val(data.nombre);
  $("#editarDescripcion").val(data.descripcion);
  $("#editarFecha").val(data.fecha);

}


// Manejador de evento para el cambio en el select de grupo
$("#grupo").change(function () {
  // Obtener el valor seleccionado del select de grupo
  var grupoSeleccionado = $(this).val();
  $.getJSON(
    "http://localhost:8080/Dextho/ciudadano/ByGrupo/" + grupoSeleccionado,
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

function initTable() {
    table = $("#table_tareas").DataTable({
      processing:true,
      ajax: {
        url: "http://localhost:8080/Dextho/cargos/todos",
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
        { data: "nombre" },
        { data: "descripcion" },
        { data: "fecha" },
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
              columns: [1, 2, 3,],
            },
          },
          {
            extend: "pdf",
            exportOptions: {
              columns: [1, 2, 3],
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
    table.columns([0,1,]).visible(false);
    table.responsive.recalc();
  }
  