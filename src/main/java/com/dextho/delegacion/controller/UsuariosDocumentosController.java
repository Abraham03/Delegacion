package com.dextho.delegacion.controller;

import com.dextho.delegacion.model.UsuariosDocumentos;
import com.dextho.delegacion.servicesImpl.DocumentosService;
import com.dextho.delegacion.servicesImpl.UsuariosDocumentosServiceImp;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/Dextho/documentos")
public class UsuariosDocumentosController {

    @Autowired
    public UsuariosDocumentosServiceImp usuariosDocumentosServiceImp;

    @Autowired
    private DocumentosService documentosService;

    @GetMapping("/todos")
    public ResponseEntity<?> getAllUsuariosDocumentos() {
        Map<String, Object> map = new LinkedHashMap<>();
        Iterable<UsuariosDocumentos> listaDocumentos = usuariosDocumentosServiceImp.getAllUsuariosDocumentos();
        map.put("Status", 1);
        map.put("data", listaDocumentos);
        return new ResponseEntity<>(map, HttpStatus.OK);

    }

    @PostMapping("/guardar")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file,
            @RequestParam("data") String jsonData) {
        Map<String, Object> map = new LinkedHashMap<>();

        UsuariosDocumentos saveUsuariosDocumentos = usuariosDocumentosServiceImp
                .saveDocumentos(jsonData, file);
        if (saveUsuariosDocumentos != null) {
            map.put("status", 1);
            map.put("data", saveUsuariosDocumentos);
            return new ResponseEntity<>(map, HttpStatus.OK);
        } else {
            map.put("status", 0);
            map.put("message", "Documento no guardado");
            return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/pdf")
    public ResponseEntity<Resource> archivoPDF(@RequestParam("documentoId") Long documentoId) {
        Optional<UsuariosDocumentos> usuariosDocumentos = usuariosDocumentosServiceImp.findById(documentoId);
        if (usuariosDocumentos.isPresent()) {
            UsuariosDocumentos doc = usuariosDocumentos.get();
            Resource resource = documentosService.obtenerArchivoPDF(doc.getRuta());

            // 1. Detectar Content-Type dinámicamente
            String nombreArchivo = doc.getNombre_documento().toLowerCase();
            MediaType contentType = MediaType.APPLICATION_OCTET_STREAM; // Genérico por defecto

            if (nombreArchivo.endsWith(".pdf")) {
                contentType = MediaType.APPLICATION_PDF;
            } else if (nombreArchivo.endsWith(".jpg") || nombreArchivo.endsWith(".jpeg")) {
                contentType = MediaType.IMAGE_JPEG;
            } else if (nombreArchivo.endsWith(".png")) {
                contentType = MediaType.IMAGE_PNG;
            } else if (nombreArchivo.endsWith(".doc") || nombreArchivo.endsWith(".docx")) {
                // Tipo MIME para Word
                contentType = MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
            } else if (nombreArchivo.endsWith(".xls") || nombreArchivo.endsWith(".xlsx")) {
                // Tipo MIME para Excel
                contentType = MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            }

            return ResponseEntity.ok()
                    .contentType(contentType)
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + doc.getNombre_documento() + "\"")
                    .body(resource);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

}
