var ruta;
const inputs = [
  "documento",
  "descripcion",
  "fecha_emitida",
  "nombre_delegado",
];
$(document).ready(function () {
  initTable();

  //Bloque para Guardar usuarios
  $("#myForm").submit(function (event) {
    event.preventDefault(); // Evitar que la página se recargue
    const data = recolectarDatos();
    let valido = true;
    if (data === "" || data === undefined) {
      valido = false;
    }
    if (valido) {
      enviarDatos(data);
    }
  });

  $("#table_tareas").on("click", ".btn-pdf", function () {
    var row = $(this).closest("tr");
    var data = $("#table_tareas").DataTable().row(row).data();
    obtenerPDF(data);
  });
});

$("#closeModalBtn").click(function () {
  $("#modalServidor").modal("hide");
});

$("#salirGuardar").click(function () {
  $("#modalAgregar").modal("hide");
});

function obtenerPDF(data) {
  $.ajax({
    url: "/Dextho/documentos/pdf",
    type: "GET",
    data: { documentoId: data.id },
    xhrFields: {
      responseType: "blob",
    },
    success: function (response) {
      var file = new Blob([response], { type: "application/pdf" });
      var fileURL = URL.createObjectURL(file);

      // Vaciar el contenido actual del contenedor
      var pdfContainer = document.getElementById("pdf-container");
      pdfContainer.innerHTML = "";

      // Crear un elemento <embed> para mostrar el PDF
      var embedElement = document.createElement("embed");
      embedElement.setAttribute("src", fileURL);
      embedElement.setAttribute("type", "application/pdf");
      embedElement.setAttribute("width", "70%");
      embedElement.setAttribute("height", "600px");

      // Agregar el elemento <embed> al contenedor
      pdfContainer.appendChild(embedElement);
    },
    error: function (xhr, status, error) {
      console.error(error);
    },
  });
}

function enviarDatos(data) {
  var url, contentype;
  url = "/Dextho/documentos/guardar";
  modal = "#modalAgregar";

  // Crear un objeto FormData y agregar los datos y el archivo
  var formData = new FormData();
  formData.append("file", data.file);
  formData.append("data", JSON.stringify(data.data));
  console.log(formData.get("data"));

  $.ajax({
    type: "POST",
    url: url,
    data: formData,
    contentType: false,
    processData: false,
    success: function (response) {
      table.ajax.reload();
      $(modal).modal("hide");
      limpiarInputs(inputs);
      $(".alert-danger p").text("Documento guardada correctamente");
      $("#modalServidor").modal("show");
    },
    error: function (error) {
      $(".alert-danger p").text(error.responseText);
      $("#modalServidor").modal("show");
    },
  });
}

function initTable() {
  table = $("#table_tareas").DataTable({
    processing: true,
    ajax: {
      url: "/Dextho/documentos/todos",
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
      { data: "tipo_documento" },
      { data: "nombre_documento" },
      { data: "descripcion" },
      { data: "ruta" },
      { data: "fecha_emitida" },
      { data: "nombre_delegado" },
      {
        data: null,
        render: function (data, type, row) {
          return (
            '<div class="row"><div class="col">' +
            '<button type="button" id="addDocumento" data-id="' +
            data +
            '" class="btn-add btn btn-secondary" data-bs-toggle="modal" data-bs-target="#modalAgregar" data-toggle="tooltip" data-placement="top" title="Agregar nuevo documento"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="i class="bi bi-filetype-pdf" viewBox="0 0 16 16"><path d="M12.5 16a3.5 3.5 0 1 0 0-7 3.5 3.5 0 0 0 0 7Zm.5-5v1h1a.5.5 0 0 1 0 1h-1v1a.5.5 0 0 1-1 0v-1h-1a.5.5 0 0 1 0-1h1v-1a.5.5 0 0 1 1 0Zm-2-6a3 3 0 1 1-6 0 3 3 0 0 1 6 0Z"></path><path d="M2 13c0 1 1 1 1 1h5.256A4.493 4.493 0 0 1 8 12.5a4.49 4.49 0 0 1 1.544-3.393C9.077 9.038 8.564 9 8 9c-5 0-6 3-6 4Z"></path></svg></button>' +
            '<button type="button" id="editDocumento" data-id="' +
            data +
            '" class="btn-pdf btn btn-secondary" data-toggle="tooltip" data-placement="top" title="Abrir PDF"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-filetype-pdf" viewBox="0 0 16 16"><path fill-rule="evenodd" d="M14 4.5V14a2 2 0 0 1-2 2h-1v-1h1a1 1 0 0 0 1-1V4.5h-2A1.5 1.5 0 0 1 9.5 3V1H4a1 1 0 0 0-1 1v9H2V2a2 2 0 0 1 2-2h5.5L14 4.5ZM1.6 11.85H0v3.999h.791v-1.342h.803c.287 0 .531-.057.732-.173.203-.117.358-.275.463-.474a1.42 1.42 0 0 0 .161-.677c0-.25-.053-.476-.158-.677a1.176 1.176 0 0 0-.46-.477c-.2-.12-.443-.179-.732-.179Zm.545 1.333a.795.795 0 0 1-.085.38.574.574 0 0 1-.238.241.794.794 0 0 1-.375.082H.788V12.48h.66c.218 0 .389.06.512.181.123.122.185.296.185.522Zm1.217-1.333v3.999h1.46c.401 0 .734-.08.998-.237a1.45 1.45 0 0 0 .595-.689c.13-.3.196-.662.196-1.084 0-.42-.065-.778-.196-1.075a1.426 1.426 0 0 0-.589-.68c-.264-.156-.599-.234-1.005-.234H3.362Zm.791.645h.563c.248 0 .45.05.609.152a.89.89 0 0 1 .354.454c.079.201.118.452.118.753a2.3 2.3 0 0 1-.068.592 1.14 1.14 0 0 1-.196.422.8.8 0 0 1-.334.252 1.298 1.298 0 0 1-.483.082h-.563v-2.707Zm3.743 1.763v1.591h-.79V11.85h2.548v.653H7.896v1.117h1.606v.638H7.896Z"/></svg></button>' +
            "</div></div>"
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
          extend: "pdf",
          exportOptions: {
            columns: [2, 3, 4, 5, 6, 7, 8, 9],
          },
        },
        {
          extend: "excel",
          exportOptions: {
            columns: [2, 3, 4, 5, 6, 7, 8, 9],
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
  table.columns([0, 1, 7]).visible(false);
  table.responsive.recalc();
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
      const select = $("#ciudadanos");

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

//Recolecta datos del formulario
function recolectarDatos() {
  const dato = {};

  var nombreUsuario = document.getElementById("ciudadanos").value;
  dato["nombreUsuario"] = nombreUsuario;

  // Obtener los valores de los campos select
  const camposSelect = ["ciudadanos", "tipo_documento", "grupo"];
  for (let i = 0; i < camposSelect.length; i++) {
    const campo = camposSelect[i];
    const select = document.getElementById(campo);
    const valor = select.options[select.selectedIndex].value;

    if (
      valor === "Seleccione" ||
      valor === "Datos no encontrados"
    ) {
      $(".alert-danger p").text(
        "Selecciona una opción válida para todos los campos"
      );
      $("#modalServidor").modal("show");
      return;
    }
    dato[campo] = valor;
  }

  // Obtener el elemento de entrada de archivo
  const campos = ["descripcion", "fecha_emitida", "nombre_delegado"];
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

  // Validar la selección de archivo
  const inputFile = document.getElementById("documento");
  if (inputFile.files.length === 0) {
    $(".alert-danger p").text("No se ha seleccionado ningún archivo");
    $("#modalServidor").modal("show");
    return;
  }
  const archivo = inputFile.files[0];

  var ordenParametros = [
    "tipo_documento",
    "descripcion",
    "fecha_emitida",
    "nombre_delegado",
    "ciudadanos",
    "nombre_documento",
    "ruta",
  ];
  var data = reorganizarParametros(dato, ordenParametros);

  return {
    data: data,
    file: archivo,
  };
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

function limpiarInputs(inputsIds) {
  inputsIds.forEach(function (inputId) {
    if (document.getElementById(inputId)) {
      document.getElementById(inputId).value = "";
    }
  });
  $("#tipo_documento").val("Seleccione");
  $("#grupo").val("Seleccione");
  const select = $("#ciudadanos");
  select.empty();
}
