const campos = [
  "nombre",
  "apellido_p",
  "apellido_m",
  "fecha_Nacimiento",
  "fecha_Ingreso",
  "representante",
];
const camposSelect = ["grupo", "vive_pueblo", "id_status"];
const camposEditar = [
  "editarNombre",
  "editarApellido_p",
  "editarApellido_m",
  "editarFecha_Nacimiento",
  "editarFecha_Ingreso",
  "editarRepresentante",
];
const camposSelectEditar = [
  "editarGrupo",
  "editarVive_pueblo",
  "editarId_status",
];
var id;
$(document).ready(function () {
  initTable();
  const dataArray = [];

  // Llamar a la función obtenerEstatus() para obtener los datos
  obtenerEstatus()
    .then(function (data) {
      // Aquí ya tienes los datos disponibles en la variable data (que es el dataArray)
      // Puedes usarlos para lo que necesites, por ejemplo, generar las opciones del select
      const select = $("#id_status");
      const selectEditar = $("#editarId_status");
      data.forEach(function (ciudadano) {
        const option = $("<option>").val(ciudadano.id).text(ciudadano.nombre);
        select.append(option);
        selectEditar.append(option.clone());
      });
      // También puedes utilizar los datos en otras partes del código
      asignarDescripcionOnChange("#id_status", data, "#descripcion_Estatus");
      asignarDescripcionOnChange(
        "#editarId_status",
        data,
        "#editarDescripcion_Estatus"
      );
    })
    .catch(function (error) {
      // En caso de error en la obtención de datos, puedes manejarlo aquí
      console.error(error);
    });

  $("#guardarCiudadano").click(function () {
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

  $("#editarCiudadano").click(function () {
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

  $("#salirGuardar").click(function () {
    $("#modalAgregar").modal("hide");
  });

  $("#salirEditar").click(function () {
    $("#modalEditar").modal("hide");
  });
});

function enviarDatos(data, accion, httpMetodo) {
  var url;
  switch (accion) {
    case "editar":
      url = "/Dextho/ciudadano/actualizar/" + id;
      modal = "#modalEditar";
      break;
    case "eliminar":
      url = "/Dextho/tareas/eliminar/" + id;
      break;
    case "guardar":
      url = "/Dextho/ciudadano/guardar";
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

// Función para obtener los datos y guardarlos en la variable dataArray
function obtenerEstatus() {
  return new Promise(function (resolve, reject) {
    $.getJSON(
      "/Dextho/estatus/todos",
      function (response) {
        if (response.status === 0) {
          // En caso de error o datos vacíos, rechazamos la Promise con un mensaje de error
          reject("Error al obtener los datos");
        } else {
          // Resolvemos la Promise con el array de datos
          dataArray = response.data;
          resolve(dataArray);
        }
      }
    );
  });
}

// Función para asignar la descripción al textarea cuando cambia el select
function asignarDescripcionOnChange(selectId, dataArray, textareaId) {
  $(selectId).change(function () {
    const selectedValue = $(this).val(); // Obtener el valor seleccionado
    if (selectedValue === "") {
      $(textareaId).val(""); // Limpiar el textarea si no hay opción seleccionada
    } else {
      // Buscar el objeto con el estatus seleccionado
      const estatus = dataArray.find(
        (item) => item.id === parseInt(selectedValue)
      );
      // Asignar la descripción al textarea
      $(textareaId).val(estatus ? estatus.descripcion : "");
    }
  });
}


//Recolecta datos del formulario
function recolectarDatos() {
  const dato = {};
  const nombre_delegado = "";

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

  campos.forEach(function (campo) {
    const valor = document.getElementById(campo).value;
    dato[campo] = valor;
  });
  if (dato["representante"] === "") {
    dato["representante"] = "No";
  }


  // Validar que todos los campos estén completos

  const camposAExcluir = ["fecha_Nacimiento", "fecha_Ingreso"];

  // Verifica si existe algun campo vacio que NO sea una de las fechas
  const campoObligatorioVacio = Object.entries(dato).some(([clave, valor]) => {
    // Si el valor esta vacio y la clave no esta en la lista de exclusion (fechas)
    return valor === "" && !camposAExcluir.includes(clave);
  })

  if (campoObligatorioVacio) {
    $(".alert-danger p").text("Llenar todos los campos");
    $("#modalServidor").modal("show");
    return;
  }

  var ordenParametros = [
    "nombre",
    "apellido_p",
    "apellido_m",
    "fecha_Nacimiento",
    "fecha_Ingreso",
    "grupo",
    "vive_pueblo",
    "representante",
    "id_status",
  ];
  var data = reorganizarParametros(dato, ordenParametros);

  return data;
}

//Recolecta datos del formulario editar
function recolectarDatosEditar() {
  const dato = {};
  // Obtener los valores de los campos select

  for (let i = 0; i < camposSelect.length; i++) {
    const campoeditar = camposSelectEditar[i];
    const campo = camposSelect[i];
    const select = document.getElementById(campoeditar);
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

  for (let i = 0; i < campos.length; i++) {
    const campo = campos[i];
    const campoEditar = camposEditar[i];
    const valor = document.getElementById(campoEditar).value;
    dato[campo] = valor;
  }

  if (dato["representante"] === "") {
    dato["representante"] = "No";
  }

  // Validar que todos los campos estén completos

  const camposAExcluir = ["fecha_Nacimiento", "fecha_Ingreso"];

  // Verifica si existe algun campo vacio que NO sea una de las fechas
  const campoObligatorioVacio = Object.entries(dato).some(([clave, valor]) => {
    // Si el valor esta vacio y la clave no esta en la lista de exclusion (fechas)
    return valor === "" && !camposAExcluir.includes(clave);
  })

  if (campoObligatorioVacio) {
    $(".alert-danger p").text("Llenar todos los campos");
    $("#modalServidor").modal("show");
    return;
  }

  return dato;
}

function llenarCampos(data) {
  // Establecer los valores en los inputs
  $("#editarNombre").val(data.nombre);
  $("#editarApellido_p").val(data.apellido_p);
  $("#editarApellido_m").val(data.apellido_m);
  $("#editarFecha_Nacimiento").val(data.fecha_Nacimiento);
  $("#editarFecha_Ingreso").val(data.fecha_Ingreso);
  $("#editarGrupo").val(data.grupo);
  $("#editarVive_pueblo").val(data.vive_pueblo);
  $("#editarRepresentante").val(data.representante);
  $("#editarId_status").val(data.estatusCiudadanos.id);
  $("#editarDescripcion_Estatus").val(data.estatusCiudadanos.descripcion);
}

function limpiarInputs(inputsIds) {
  inputsIds.forEach(function (inputId) {
    if (document.getElementById(inputId)) {
      document.getElementById(inputId).value = "";
    }
  });
  $("#descripcion_Estatus").val("");
  $("#grupo option[value='Seleccione']").prop("selected", true);
  $("#vive_pueblo option[value='Seleccione']").prop("selected", true);
  $("#id_status option[value='Seleccione']").prop("selected", true);
}

function limpiarInputsEditar(inputsIds) {
  inputsIds.forEach(function (inputId) {
    if (document.getElementById(inputId)) {
      document.getElementById(inputId).value = "";
    }
  });
  $("#editarDescripcion_Estatus").val("");
  $("#editarGrupo option[value='Seleccione']").prop("selected", true);
  $("#editarVive_pueblo option[value='Seleccione']").prop("selected", true);
  $("#editarId_status option[value='Seleccione']").prop("selected", true);
}

function reorganizarParametros(objetoOriginal, ordenParametros) {
  var objetoReorganizado = {};

  ordenParametros.forEach(function (parametro) {
    if (objetoOriginal.hasOwnProperty(parametro)) {
      objetoReorganizado[parametro] = objetoOriginal[parametro];
    }
  });

  return objetoReorganizado;
}

function initTable() {
  table = $("#table_tareas").DataTable({
    processing: true,
    ajax: {
      url: "/Dextho/ciudadano/todos",
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
      {
        data: null,
        render: function (data, type, row) {
          return data.nombre + " " + data.apellido_p + " " + data.apellido_m;
        },
      },
      { data: "fecha_Nacimiento" },
      { data: "fecha_Ingreso" },
      { data: "grupo" },
      { data: "vive_pueblo" },
      { data: "representante" },
      {
        data: "estatusCiudadanos.nombre",
        title: "Estatus",
        render: function (data, type, row) {
          return data ? data : "-";
        },
      },
      {
        data: "usuariosDocumentos",
        title: "Documentos",
        render: function (data, type, row) {
          var totalDocumentos = data ? data.length : 0;
          return totalDocumentos;
        },
      },
      {
        data: null,
        title: "Multas",
        render: function (data, type, row) {
          if (row.multas && row.multas.length > 0) {
            var multasNoPagadas = row.multas.filter(function (multa) {
              return multa.pagado === false;
            });
            return multasNoPagadas.length;
          } else {
            return 0;
          }
        },
      },
      {
        data: "cargos",
        title: "Cargos",
        render: function (data, type, row) {
          var totalCargos = data ? data.length : 0;
          return totalCargos;
        },
      },
      {
        data: null,
        render: function (data, type, row) {
          return (
            '<div class="row"><div class="col"><button type="button" id="addTarea" data-id="' +
            data +
            '" class="btn-add btn btn-secondary" data-bs-toggle="modal" data-bs-target="#modalAgregar" data-toggle="tooltip" data-placement="top" title="Agregar nuevo Ciudadano"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-person-fill-add" viewBox="0 0 16 16"><path d="M12.5 16a3.5 3.5 0 1 0 0-7 3.5 3.5 0 0 0 0 7Zm.5-5v1h1a.5.5 0 0 1 0 1h-1v1a.5.5 0 0 1-1 0v-1h-1a.5.5 0 0 1 0-1h1v-1a.5.5 0 0 1 1 0Zm-2-6a3 3 0 1 1-6 0 3 3 0 0 1 6 0Z"></path><path d="M2 13c0 1 1 1 1 1h5.256A4.493 4.493 0 0 1 8 12.5a4.49 4.49 0 0 1 1.544-3.393C9.077 9.038 8.564 9 8 9c-5 0-6 3-6 4Z"></path></svg></button>' +
            '<button type="button" id="editCiudadano" data-id="' +
            data +
            '" class="btn-edit btn btn-secondary" data-bs-toggle="modal" data-bs-target="#modalEditar" data-toggle="tooltip" data-placement="top" title="Editar Ciudadano"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-pencil-fill" viewBox="0 0 16 16"><path d="M12.854.146a.5.5 0 0 0-.707 0L10.5 1.793 14.207 5.5l1.647-1.646a.5.5 0 0 0 0-.708l-3-3zm.646 6.061L9.793 2.5 3.293 9H3.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.207l6.5-6.5zm-7.468 7.468A.5.5 0 0 1 6 13.5V13h-.5a.5.5 0 0 1-.5-.5V12h-.5a.5.5 0 0 1-.5-.5V11h-.5a.5.5 0 0 1-.5-.5V10h-.5a.499.499 0 0 1-.175-.032l-.179.178a.5.5 0 0 0-.11.168l-2 5a.5.5 0 0 0 .65.65l5-2a.5.5 0 0 0 .168-.11l.178-.178z"></path></svg></button>' +
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
            columns: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10],
          },
        },
        {
          extend: "pdf",
          exportOptions: {
            columns: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10],
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
  table.columns([0]).visible(false);
  table.responsive.recalc();
}
