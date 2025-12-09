package com.dextho.delegacion.servicesImpl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class DocumentosService {

    private final String RUTA_DOCUMENTOS = "C:/Users/DELEGACION DEXTHO/Documents/Aplicacion Dextho/app/Documentos/";
    //private final String RUTA_DOCUMENTOS = "C:/Users/Dell/OneDrive/Documents/Dextho/";

    public String guardarDocumentoEnCarpeta(Long ciudadanoId, String carpetaTipo, String nombreArchivo,
            MultipartFile file) {

        String ruta = RUTA_DOCUMENTOS + ciudadanoId + "/" + carpetaTipo;
        String rutaCompleta = ruta + "/" + nombreArchivo;
        crearCarpetaSiNoExiste(ruta);

        try {
            Files.write(Paths.get(rutaCompleta), file.getBytes());
        } catch (IOException e) {
            // Manejar el error de escritura del archivo
            e.printStackTrace();
        }

        return rutaCompleta;
    }

    private void crearCarpetaSiNoExiste(String carpetaPath) {
        File carpeta = new File(carpetaPath);

        if (!carpeta.exists()) {
            if (carpeta.mkdirs()) {
                System.out.println("Carpeta creada exitosamente: " + carpetaPath);
            } else {
                System.out.println("No se pudo crear la carpeta: " + carpetaPath);
            }
        }
    }

    public Resource obtenerArchivoPDF(String ruta) {
        File file = new File(ruta);
        Resource resource = new FileSystemResource(file);

        if (resource.exists() && resource.isReadable()) {
            return resource;
        } else {
            throw new RuntimeException("El archivo no existe o no es accesible.");
        }
    }

}
