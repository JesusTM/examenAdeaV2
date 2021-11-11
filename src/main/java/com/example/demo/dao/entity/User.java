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
	@Column(name = "LOGIN")
	private Long login;
	
	@Column(name = "PASSWORD")
	private String password;

	@Column(name = "NOMBRE")
	private String nombre;
	
	@Column(name = "CLIENTE")
	private String cliente;
	
	@Column(name = "EMAIL")
	private String email;

	@Column(name = "FECHAALTA")
	private LocalDateTime fechaAlta;
	
	@Column(name = "FECHABAJA")
	private LocalDateTime fechaBaja;
	
	@Column(name = "STATUS")
	private char status;
	
	@Column(name = "INTENTOS")
	private int intentos;
	
	@Column(name = "FECHAREVOCADO")
	private LocalDateTime fechaRevocado;
	
	@Column(name = "FECHA_VIGENCIA")
	private LocalDateTime fechaVigencia;
	
	@Column(name = "NO_ACCESO")
	private int noAcceso;
	
	@Column(name = "APELLIDO_PATERNO")
	private String apellidoPaterno;
	
	@Column(name = "APELLIDO_MATERNO")
	private String apellidoMaterno;
	
	@Column(name = "AREA")
	private String area;
	
	@Column(name = "FECHAMODIFICACION")
	private LocalDateTime fechaModificacion;
	
	public boolean isPasswordExpired() {
        if (this.fechaModificacion == null) return false;      
         
        return LocalDateTime.now().isAfter(fechaModificacion);
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
