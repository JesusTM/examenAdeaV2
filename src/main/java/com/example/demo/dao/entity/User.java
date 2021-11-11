package com.example.demo.dao.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long login;
	
	@Column
	private String password;

	@Column
	private String nombre;
	
	@Column
	private String cliente;
	
	@Column
	private String email;

	@Column
	private LocalDateTime fechaAlta;
	
	@Column
	private LocalDateTime fechaBaja;
	
	@Column
	private char status;
	
	@Column
	private int intentos;
	
	@Column
	private LocalDateTime fechaRevocado;
	
	@Column
	private LocalDateTime fechaVigencia;
	
	@Column
	private int noAcceso;
	
	@Column
	private String apellidoPaterno;
	
	@Column
	private String apellidoMaterno;
	
	@Column
	private String area;
	
	@Column
	private LocalDateTime fechaModificacion;
	
	public boolean isPasswordExpired() {
        if (this.fechaVigencia == null) return false;      
         
        return LocalDateTime.now().isAfter(fechaVigencia);
    }

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public LocalDateTime getFechaVigencia() {
		return fechaVigencia;
	}

	public void setFechaVigencia(LocalDateTime fechaVigencia) {
		this.fechaVigencia = fechaVigencia;
	}

	public LocalDateTime getFechaModificacion() {
		return fechaModificacion;
	}

	public void setFechaModificacion(LocalDateTime fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}
}
