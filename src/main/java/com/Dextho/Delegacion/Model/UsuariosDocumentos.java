package com.Dextho.Delegacion.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "usuariosDocumentos")
@AllArgsConstructor
@NoArgsConstructor
public class UsuariosDocumentos {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "nombre", nullable = false)
	private String nombre;
	
	@Column(name = "descripcion", nullable = false)
	private String descripcion;
	
	@Column(name = "tipo_dato", nullable = false)
	private String tipo_dato;
	
	@Lob
	@Column(name = "dato", nullable = false)
	private byte[] dato;
	
	@Column(name = "id_ciudadano", nullable = false)
	private Long idCiudadano;
	
}
