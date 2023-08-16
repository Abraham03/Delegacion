package com.dextho.delegacion.servicesImpl;

import java.io.IOException;
import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.dextho.delegacion.dto.GuardarDocumentosDTO;
import com.dextho.delegacion.model.Ciudadanos;
import com.dextho.delegacion.model.UsuariosDocumentos;
import com.dextho.delegacion.repository.CiudadanosRepository;
import com.dextho.delegacion.repository.UsuariosDocumentosRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@Service
public class UsuariosDocumentosServiceImp {
    private static final Logger LOGGER = Logger.getLogger(UsuariosDocumentosRepository.class.getName());

    @Autowired
    private UsuariosDocumentosRepository usuariosDocumentosRepository;

    @Autowired
    private CiudadanosRepository ciudadanosRepository;

    @Autowired
    private DocumentosService documentosService;

    public Iterable<UsuariosDocumentos> getAllUsuariosDocumentos() {
        return usuariosDocumentosRepository.findAll();
    }

    public Optional<UsuariosDocumentos> findById(Long documentoId) {
        return usuariosDocumentosRepository.findById(documentoId);
    }

    public UsuariosDocumentos saveDocumentos(String jsonData, MultipartFile file) {
        String nombreArchivo = file.getOriginalFilename();
        String rutaCompleta;

        try {

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            JsonNode jsonNode = objectMapper.readTree(jsonData);

            String carpetaTipo = jsonNode.get("tipo_documento").asText();

            // Se obtiene el id del ciudadano y se convierte en Long
            Long ciudadanoId = jsonNode.get("ciudadanos").asLong();

            // Gurada el documento en la carpeta correspondiente
            rutaCompleta = documentosService.guardarDocumentoEnCarpeta(ciudadanoId, carpetaTipo, nombreArchivo, file);

            // Actualizar el valor de "nombre documento y ruta" en el objeto JsonNode
            ((ObjectNode) jsonNode).put("nombre_documento", nombreArchivo);
            ((ObjectNode) jsonNode).put("ruta", rutaCompleta);

            Ciudadanos ciudadanos = ciudadanosRepository.findById(ciudadanoId).orElse(null);

            // Convertir el JsonNode actualizado de nuevo a JSON en formato de cadena
            String jsonDataActualizado = jsonNode.toString();
            LOGGER.info(jsonDataActualizado);
            GuardarDocumentosDTO guardarDocumentosDTO = objectMapper.readValue(jsonDataActualizado,
                    GuardarDocumentosDTO.class);
            LOGGER.warning(guardarDocumentosDTO.toString());
            System.out.println(guardarDocumentosDTO.getFecha_emitida());
            UsuariosDocumentos usuariosDocumentos = new UsuariosDocumentos(guardarDocumentosDTO.getTipo_documento(),
                    guardarDocumentosDTO.getNombre_documento(), guardarDocumentosDTO.getDescripcion(),
                    guardarDocumentosDTO.getFecha_emitida(), guardarDocumentosDTO.getNombre_delegado(),
                    guardarDocumentosDTO.getRuta(), ciudadanos);

            usuariosDocumentosRepository.save(usuariosDocumentos);
            return usuariosDocumentos;

        } catch (IOException e) {
            LOGGER.warning(e.getMessage());
            return null;
        }
    }
}
