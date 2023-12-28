# Utiliza una imagen base con Java (puedes ajustar la versión)
FROM openjdk:17

# Establece el directorio de trabajo en el contenedor
WORKDIR /app

# Copia el archivo JAR principal de tu aplicación al contenedor
COPY target/Delegacion-0.0.1-SNAPSHOT.jar app.jar

# Expone el puerto en el que tu aplicación escucha
EXPOSE 8080

# Comando para ejecutar la aplicación cuando el contenedor se inicia
CMD ["java", "-jar", "app.jar"]
