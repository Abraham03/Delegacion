var table;
var dataEditar;
var id;
var modal;
const inputsIds = ['usuario', 'password', 'passwordConfirmar'];
const editIds = ['usuarioEditar', 'passEditar', 'passEditarConfirmar', 'eliminado'];
var campos = {
  nombre: 'nombre',
  descripcion: 'descripcion',
  prioridad: 'prioridad',
  estatus: 'estatus'
};

$(document).ready(function () {
  initTable();
  login_user();
  $('#table_usuarios').on('click', '.btn-eliminar', function () {
    var row = $(this).closest('tr');
    var data = $('#table_usuarios').DataTable().row(row).data();
    id = data.id;
    enviarDatos(id, "eliminar", "PUT");
  });

  $('#table_usuarios').on('click', '.btn-edit', function () {
    var row = $(this).closest('tr');
    var data = $('#table_usuarios').DataTable().row(row).data();
    id = data.id;
    console.log(data);
    const newData = {
      nombreEditar: data.usuarioEditar, passEditar: data.passEditar, activo: data.activo
    }
    //asignarValores(newData);
  });

  $('#editarTarea').on('click', function () {
    const data = recolectarDatosModificados();
    enviarDatos(data, "editar", "PUT");
  });

  $('#guardarUsuario').on('click', function () {
    const data = recolectarDatos(campos);
    let valido = true;
      if(data === "" || data === undefined) {
          valido = false;
      }
    if(valido){
        enviarDatos(data, "guardar", "POST");
    }
  });



  $('#closeModalBtn').click(function() {
    $('#modalServidor').modal('hide');
});

$('#salirEditar').click(function() {
  $('#modalEditar').modal('hide');
});

$('#salirGuardar').click(function() {
  $('#modalAgregar').modal('hide');
});

$(document).ready(function(){
  $('[data-toggle="tooltip"]').tooltip();   
});


$("#btnMenu").click(function(){
      $("#navbarNav").toggle();
});


});

function login_user() {
  $.ajax({
    url: 'http://localhost:8080/Dextho/usuarios/user_login',
    type: 'GET',
    dataType: 'json',
    success: function(data) {
      console.log(data);
    },
    error: function(xhr, status, error) {
      console.log(xhr.responseJSON.message);
    }
  });
  
}

function enviarDatos(data, accion, httpMetodo) {
  var url;

  switch (accion) {
    case "editar":
      url = "http://localhost:8080/Dextho/tareas/actualizar/" + id;
      modal = "#modalEditar";
      break;
    case "eliminar":
      url = "http://localhost:8080/Dextho/tareas/eliminar/" + id;
      break;
    case "guardar":
      url = "http://localhost:8080/Dextho/tareas/guardar";
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
    dataType: 'json',
    success: function (response) {
      console.log(response);
      table.ajax.reload();
      $(modal).modal('hide');
      switch (accion) {
        case "editar":
          limpiarInputsEdit(editIds);
          $('.alert-danger p').text('Tarea editada correctamente');
          $('#modalServidor').modal('show');
          break;
        case "eliminar":
          $('.alert-danger p').text('Tarea desactivada correctamente');
          $('#modalServidor').modal('show');
          break;
        case "guardar":
          limpiarInputs(inputsIds);
          $('.alert-danger p').text('Tarea guardada correctamente');
          $('#modalServidor').modal('show');
          break;
        default:
          break;
      }
    },
    error: function (error) {
      $('.alert-danger p').text(error);
      $('#modalServidor').modal('show');
    }
  });
}

function asignarValores(data) {
  for (const key in data) {
    if (data.hasOwnProperty(key)) {
      const element = data[key];

      if (key == "activo") {
        $(`input[name="eliminado"][value="${element}"]`).prop('checked', true);
      } else if (document.getElementById(key)) {
        document.getElementById(key).value = element;
      } else if (document.getElementById(key + 'Editar')) {
        document.getElementById(key + 'Editar').value = element;
        $(`#${key}Editar option[value="${element}"]`).attr("selected", true);
      }
    }
  }
}


function recolectarDatosModificados() {
  const nombre = $('#nombreEditar').val();
  const descripcion = $('#descripcionEditar').val();
  const prioridad = $('#prioridadEditar').val();
  const estatus = $('#estatusEditar').val();
  const activo = JSON.parse($("input[name='eliminado']:checked").val());

  return { nombre, descripcion, prioridad, estatus, activo };
}

function recolectarDatos(campos) {
  const data = {};
  for (const key in campos) {
    if (campos.hasOwnProperty(key)) {
      const element = campos[key];
      if (key == "activo") {
        data[key] = $("input[name='eliminado']:checked").val();
      } else {
        const val = document.getElementById(element).value;
        if (val === "") {
          // Mostrar un mensaje de error o alerta indicando que el campo está vacío
          alert("Ingresar datos de la Tarea");
          return;
        }
        data[key] = val;
      }
    }
  }
  return data;
}

function limpiarInputs(inputsIds) {
  inputsIds.forEach(function (inputId) {
    document.getElementById(inputId).value = '';
  });
}

function limpiarInputsEdit(inputsIds) {
  inputsIds.forEach(function (inputId) {
    if (document.getElementById(inputId)) {
      document.getElementById(inputId).value = '';
    }
  });
  $("input[name='eliminado'][value='true']").prop('checked', true);
  $("#prioridadEditar option[value='Prioridad...']").prop('selected', true);
  $("#estatusEditar option[value='Estatus...']").prop('selected', true);
}


function initTable() {
  table = $("#table_usuarios").DataTable({
    ajax: {
      url: "http://localhost:8080/Dextho/usuarios/todos",
      dataSrc: "Data",
      error: function(xhr, status, error) {
        $('.alert-danger p').text(xhr.responseJSON.message);
        $('#modalServidor').modal('show');
        table.clear();
        table.row.add(['Sin datos', 'Sin datos', 'Sin datos', 'Sin datos', 'Sin datos','Sin datos','Sin datos']).draw();
      }
    },
    columns: [
      { data: "id" },
      { data: "username" },
      { data: "password" },
      { data: "enabled" },
      {
        "data": "roles",
        "render": function(data, type, row) {
          var roles = "";
          if(row.roles && row.roles.length>0){
              for (var i = 0; i < row.roles.length; i++) {
                  roles += "<li>" + row.roles[i].name + "</li>";
              }
              return "<ul>" + roles + "</ul>";
          }else{
              return "";
          }
      }
    },
      {
        data: null,
        render: function (data, type, row) {
          return '<div class="row"><div class="col"><button type="button" id="addTarea" data-id="' + data + '" class="btn-add btn btn-secondary" data-bs-toggle="modal" data-bs-target="#modalAgregar" data-toggle="tooltip" data-placement="top" title="Agregar nueva Tarea"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-person-fill-add" viewBox="0 0 16 16"><path d="M12.5 16a3.5 3.5 0 1 0 0-7 3.5 3.5 0 0 0 0 7Zm.5-5v1h1a.5.5 0 0 1 0 1h-1v1a.5.5 0 0 1-1 0v-1h-1a.5.5 0 0 1 0-1h1v-1a.5.5 0 0 1 1 0Zm-2-6a3 3 0 1 1-6 0 3 3 0 0 1 6 0Z"></path><path d="M2 13c0 1 1 1 1 1h5.256A4.493 4.493 0 0 1 8 12.5a4.49 4.49 0 0 1 1.544-3.393C9.077 9.038 8.564 9 8 9c-5 0-6 3-6 4Z"></path></svg></button>'
            + '<button type="button" id="editTarea" data-id="' + data + '" class="btn-edit btn btn-secondary" data-bs-toggle="modal" data-bs-target="#modalEditar" data-toggle="tooltip" data-placement="top" title="Editar Tarea"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-pencil-fill" viewBox="0 0 16 16"><path d="M12.854.146a.5.5 0 0 0-.707 0L10.5 1.793 14.207 5.5l1.647-1.646a.5.5 0 0 0 0-.708l-3-3zm.646 6.061L9.793 2.5 3.293 9H3.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.207l6.5-6.5zm-7.468 7.468A.5.5 0 0 1 6 13.5V13h-.5a.5.5 0 0 1-.5-.5V12h-.5a.5.5 0 0 1-.5-.5V11h-.5a.5.5 0 0 1-.5-.5V10h-.5a.499.499 0 0 1-.175-.032l-.179.178a.5.5 0 0 0-.11.168l-2 5a.5.5 0 0 0 .65.65l5-2a.5.5 0 0 0 .168-.11l.178-.178z"></path></svg></button>'
            + '<button type="button" id="eliminarTarea" class="btn-eliminar btn btn-secondary" data-toggle="tooltip" data-placement="top" title="Eliminar Tarea"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-person-dash-fill" viewBox="0 0 16 16"><path fill-rule="evenodd" d="M11 7.5a.5.5 0 0 1 .5-.5h4a.5.5 0 0 1 0 1h-4a.5.5 0 0 1-.5-.5z"></path><path d="M1 14s-1 0-1-1 1-4 6-4 6 3 6 4-1 1-1 1H1zm5-6a3 3 0 1 0 0-6 3 3 0 0 0 0 6z"></path></svg></button></div></div>';
        }
      },
    ],
    buttons: true,
    dom: 'B<"clear">lfrtip',
    buttons: {
      name: 'primary',
      buttons:
        [
          'excel',
          'pdf'
        ]
    },
    select: true,
    autoFill: true,
    "language": {
      "info": "Mostrando pagina _PAGE_ de _PAGES_",
      "search": "Buscar:",
      "paginate": {
        "first": "First",
        "last": "Last",
        "next": "Siguiente",
        "previous": "Anterior"
      },
    },
  });
  table.column(0).visible(false);
  table.column(2).visible(false);
  table.responsive.recalc();
}

