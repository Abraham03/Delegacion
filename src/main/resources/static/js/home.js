let totalCiudadanos = 0;
$(document).ready(function () {
  grafica();
  $("#btnMenu").click(function () {
    $("#navbarNav").toggle();
  });
});

function grafica() {
  // Hacer una solicitud HTTP para obtener los datos desde el backend
  fetch("/Dextho/ciudadano/todos")
    .then((response) => response.json())
    .then((data) => {
      console.log(data);
      let totalDeudas = 0;
      let totalMonto = 0;

      let countActivos = 0;
      let countInactivos = 0;
      let countJubilados = 0;

      data.data.forEach((ciudadano) => {
        ciudadano.multas.forEach((multa) => {
          if (!multa.pagado && multa.activo) {
            totalDeudas++;
            totalMonto += multa.monto;
          }
        });

        if (ciudadano.estatusCiudadanos){
          const estatusNombre = ciudadano.estatusCiudadanos.nombre;

          if(estatusNombre === "Activo") countActivos++;
          else if(estatusNombre === "Inactivo") countInactivos++;
          else if(estatusNombre === "Jubilado") countJubilados++;
        
        }
      });



      // Actualizar el elemento HTML con el total de ciudadanos que deben una multa y el monto total
      const totalDeudasElement = document.getElementById("totalDeudas").textContent = `Total de Deudores: ${totalDeudas}`;
      const totalMontoElement = document.getElementById("totalMonto").textContent = `Monto Total: ${totalMonto.toLocaleString("es-MX", { style: "currency", currency: "MXN" })}`;


      // ACTUALIZAR NUEVOS CONTADORES EN DOM
      // Asegúrate de haber agregado los IDs correspondientes en el HTML
      if(document.getElementById("totalActivos")) 
          document.getElementById("totalActivos").textContent = `Total: ${countActivos}`;
      if(document.getElementById("totalJubilados")) 
          document.getElementById("totalJubilados").textContent = `Total: ${countJubilados}`;
      if(document.getElementById("totalInactivos")) 
          document.getElementById("totalInactivos").textContent = `Total: ${countInactivos}`;

      //totalCiudadanos = data.data.length;

      // Actualizar el contenido del elemento <p> con el id "totalCiudadanos"
      //const totalCiudadanosElement = document.getElementById("totalCiudadanos");
      //totalCiudadanosElement.textContent = `Total: ${totalCiudadanos}`;

      // Luego de crear el gráfico, actualizamos la lista
      const grupoList = document.getElementById("grupoList");

      // Procesar los datos para contar "vive_pueblo" y "no_vive_pueblo" en cada grupo
      const counts = data.data.reduce((acc, current) => {
        const group = current.grupo;

        if (!acc[group]) {
          acc[group] = {
            vive_pueblo: 0,
            no_vive_pueblo: 0,
            activos: 0,
            inactivos: 0,
            jubilados: 0,
            otros: 0
          };
        }

        if (current.vive_pueblo === "Si") {
          acc[group].vive_pueblo++;
        } else {
          acc[group].no_vive_pueblo++;
        }

        // Conteo detallado por Estatus
        if (current.estatusCiudadanos) {
             const nombreEstatus = current.estatusCiudadanos.nombre;
             if (nombreEstatus === "Activo") acc[group].activos++;
             else if (nombreEstatus === "Inactivo") acc[group].inactivos++;
             else if (nombreEstatus === "Jubilado") acc[group].jubilados++;
             else acc[group].otros++;
        }

        return acc;
      }, {});

      // Convertir los datos procesados en un formato usable para Chart.js
      const labels = Object.keys(counts);
      const vivePuebloCounts = labels.map((group) => counts[group].vive_pueblo);
      const noVivePuebloCounts = labels.map(
        (group) => counts[group].no_vive_pueblo
      );

// Limpiamos la lista antes de agregar (buena práctica)
      grupoList.innerHTML = ""; 

      labels.forEach((grupo) => {
        const datosGrupo = counts[grupo];
        const cantidadActivos = datosGrupo.activos;
        
        // Creamos el contenido HTML que irá DENTRO de la burbuja (Popover)
        // Usamos clases utilitarias para colores: Success(Verde), Secondary(Gris), Warning(Amarillo)
        const popoverContent = `
            <div class="small">
                <div class="d-flex justify-content-between mb-1">
                    <span class="text-success fw-bold">Activos:</span>
                    <span>${datosGrupo.activos}</span>
                </div>
                <div class="d-flex justify-content-between mb-1">
                    <span class="text-secondary fw-bold">Inactivos:</span>
                    <span>${datosGrupo.inactivos}</span>
                </div>
                <div class="d-flex justify-content-between mb-1">
                    <span class="text-warning fw-bold">Jubilados:</span>
                    <span>${datosGrupo.jubilados}</span>
                </div>
                ${datosGrupo.otros > 0 ? `
                <div class="d-flex justify-content-between border-top pt-1 mt-1">
                    <span class="text-muted">Otros:</span>
                    <span>${datosGrupo.otros}</span>
                </div>` : ''}
            </div>
        `;

        const listItem = document.createElement("li");
        
        // Configuración visual de la lista
        listItem.classList.add("list-group-item", "list-group-item-action", "d-flex", "justify-content-between", "align-items-center", "py-3");
        
        // --- AQUÍ LA MAGIA DEL POPOVER ---
        listItem.setAttribute("data-bs-toggle", "popover");
        listItem.setAttribute("data-bs-trigger", "hover focus"); // Se activa con hover
        listItem.setAttribute("data-bs-html", "true"); // Permite HTML dentro
        listItem.setAttribute("title", `Desglose Grupo ${grupo}`); // Título de la burbuja
        listItem.setAttribute("data-bs-content", popoverContent); // El contenido detallado
        // ---------------------------------

        const badgeColor = cantidadActivos > 0 ? "bg-success" : "bg-secondary";

        listItem.innerHTML = `
            <div class="d-flex align-items-center">
                <div>
                    <h6 class="mb-0 text-dark fw-bold">Grupo ${grupo}</h6>
                    <small class="text-muted" style="font-size: 0.75rem;">Pasa el mouse para detalles</small>
                </div>
            </div>
            <span class="badge ${badgeColor} rounded-pill px-3 py-2">
                ${cantidadActivos} Activos
            </span>
        `;
        
        grupoList.appendChild(listItem);
      });

      // IMPORTANTE: Inicializar los Popovers después de agregarlos al DOM
      const popoverTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="popover"]'));
      const popoverList = popoverTriggerList.map(function (popoverTriggerEl) {
        return new bootstrap.Popover(popoverTriggerEl);
      });

      // Crear el gráfico de barras
      const ctx = document.getElementById("myChart");

      new Chart(ctx, {
        type: "line",
        data: {
          labels: labels,
          datasets: [
            {
              label: "Vive en el Pueblo",
              data: vivePuebloCounts,
              backgroundColor: "rgba(0, 69, 255)",
              borderColor: "rgba(75, 192, 192, 1)",
              borderWidth: 2,
            },
            {
              label: "No Vive en el Pueblo",
              data: noVivePuebloCounts,
              backgroundColor: "rgba(221, 99, 78)",
              borderColor: "rgba(255, 99, 132, 1)",
              borderWidth: 2,
            },
          ],
        },
        options: {
          responsive: true,
          plugins: {
            title: {
              display: true,
              text: "Distribución de Ciudadanos por Grupo",
              fontSize: 16,
            },
            legend: {
              position: "bottom", // Puedes ajustar la posición: "top", "left", "right"
            },
          },
          scales: {
            x: {
              title: {
                display: true,
                text: "Grupo",
                fontSize: 14,
              },
              grid: {
                display: false,
              },
            },
            y: {
              beginAtZero: true,
              title: {
                display: true,
                text: "Cantidad de Ciudadanos",
                fontSize: 14,
              },
              ticks: {
                precision: 0, // Mostrar valores enteros en el eje Y
              },
            },
          },
        },
      });
    })
    .catch((error) => {
      console.error("Error al obtener los datos desde el backend:", error);
    });
}
