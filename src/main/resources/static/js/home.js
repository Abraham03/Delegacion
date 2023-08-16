let totalCiudadanos = 0;
$(document).ready(function () {
  grafica();
  $("#btnMenu").click(function () {
    $("#navbarNav").toggle();
  });
});

function grafica() {
  // Hacer una solicitud HTTP para obtener los datos desde el backend
  fetch("http://localhost:8080/Dextho/ciudadano/todos")
    .then((response) => response.json())
    .then((data) => {
      let totalDeudas = 0;
      let totalMonto = 0;

      data.data.forEach((ciudadano) => {
        ciudadano.multas.forEach((multa) => {
          if (!multa.pagado && multa.activo) {
            totalDeudas++;
            totalMonto += multa.monto;
          }
        });
      });

      // Actualizar el elemento HTML con el total de ciudadanos que deben una multa y el monto total
      const totalDeudasElement = document.getElementById("totalDeudas");
      const totalMontoElement = document.getElementById("totalMonto");

      totalDeudasElement.textContent = `Total de Deudores: ${totalDeudas}`;
      totalMontoElement.textContent = `Monto Total: ${totalMonto.toLocaleString("es-MX", { style: "currency", currency: "MXN" })}`;


      totalCiudadanos = data.data.length;

      // Actualizar el contenido del elemento <p> con el id "totalCiudadanos"
      const totalCiudadanosElement = document.getElementById("totalCiudadanos");
      totalCiudadanosElement.textContent = `Total: ${totalCiudadanos}`;

      // Luego de crear el gráfico, actualizamos la lista
      const grupoList = document.getElementById("grupoList");

      // Procesar los datos para contar "vive_pueblo" y "no_vive_pueblo" en cada grupo
      const counts = data.data.reduce((acc, current) => {
        const group = current.grupo;

        if (!acc[group]) {
          acc[group] = {
            vive_pueblo: 0,
            no_vive_pueblo: 0,
          };
        }

        if (current.vive_pueblo === "Si") {
          acc[group].vive_pueblo++;
        } else {
          acc[group].no_vive_pueblo++;
        }

        return acc;
      }, {});

      // Convertir los datos procesados en un formato usable para Chart.js
      const labels = Object.keys(counts);
      const vivePuebloCounts = labels.map((group) => counts[group].vive_pueblo);
      const noVivePuebloCounts = labels.map(
        (group) => counts[group].no_vive_pueblo
      );

      labels.forEach((grupo, index) => {
        const listItem = document.createElement("a");
        listItem.classList.add("list-group-item");
        listItem.textContent = `Grupo ${grupo}: - ${
          vivePuebloCounts[index] + noVivePuebloCounts[index]
        }`;
        grupoList.appendChild(listItem);
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
