package com.dextho.delegacion.controller;

import com.dextho.delegacion.model.UsuariosDocumentos;
import com.dextho.delegacion.servicesImpl.DocumentosService;
import com.dextho.delegacion.servicesImpl.UsuariosDocumentosServiceImp;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
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
        listaDocumentos.iterator();
        if (listaDocumentos.iterator().hasNext()) {
            map.put("Status", 1);
            map.put("data", listaDocumentos);
            return new ResponseEntity<>(map, HttpStatus.OK);
        } else {
            map.clear();
            map.put("Status", 0);
            map.put("message", "Datos no encontrados");
            return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
        }
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
        Map<String, Object> map = new LinkedHashMap<>();
        if (usuariosDocumentos.isPresent()) {
            UsuariosDocumentos usuariosDocumentosDatos = usuariosDocumentos.get();
            String ruta = usuariosDocumentosDatos.getRuta();
            Resource resource = documentosService.obtenerArchivoPDF(ruta);

            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(resource);
        } else {
            map.put("status", 0);
            map.put("error", "Error");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }

    }

}
