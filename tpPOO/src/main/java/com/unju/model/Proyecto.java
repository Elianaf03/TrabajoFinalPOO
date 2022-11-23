package com.unju.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "proyecto")
@NamedQueries({
    @NamedQuery(name = "Proyecto.findAll", query = "SELECT p FROM Proyecto p")
    , @NamedQuery(name = "Proyecto.findByCod_proyecto", query = "SELECT p FROM Proyecto p WHERE p.cod_proyecto = :cod_proyecto")
    , @NamedQuery(name = "Proyecto.findByNom_proyecto", query = "SELECT p FROM Proyecto p WHERE p.nom_proyecto = :nom_proyecto")
    , @NamedQuery(name = "Proyecto.findByFecha_inicio", query = "SELECT p FROM Proyecto p WHERE p.fecha_inicio = :fecha_inicio")
    , @NamedQuery(name = "Proyecto.findByFecha_finalizacion", query = "SELECT p FROM Proyecto p WHERE p.fecha_finalizacion = :fecha_finalizacion")
    , @NamedQuery(name = "Proyecto.findByMonto", query = "SELECT p FROM Proyecto p WHERE p.monto = :monto")})
public class Proyecto implements Serializable {

	private static final long serialVersionUID = 1L;
	// -----ATRIBUTOS
	@Id
	@Column(name = "cod_proyecto", nullable = false, length = 5)
	private int cod_proyecto;
	@Column(name = "nom_proyecto", nullable = false, length = 100)
	private String nom_proyecto;
	@Column(name = "fecha_inicio", nullable = false)
	private LocalDate fecha_inicio;
	@Column(name = "fecha_finalizacion", nullable = false)
	private LocalDate fecha_finalizacion;
	@Column(name = "monto", nullable = false)
	private float monto;

	// -----CONSTRUCTOR
	public Proyecto() {

	}

	public Proyecto(int cod_proyecto, String nom_proyecto, LocalDate fecha_inicio, LocalDate fecha_finalizacion, float monto) {
		this.cod_proyecto = cod_proyecto;
		this.nom_proyecto = nom_proyecto;
		this.fecha_inicio = fecha_inicio;
		this.fecha_finalizacion = fecha_finalizacion;
		this.monto = monto;
	}
	
	// -----GETTER Y SETTER
	public int getCod_proyecto() {
		return cod_proyecto;
	}

	public void setCod_proyecto(int cod_proyecto) {
		this.cod_proyecto = cod_proyecto;
	}

	public String getNom_proyecto() {
		return nom_proyecto;
	}

	public void setNom_proyecto(String nom_proyecto) {
		this.nom_proyecto = nom_proyecto;
	}

	public LocalDate getFecha_inicio() {
		return fecha_inicio;
	}

	public void setFecha_inicio(LocalDate fecha_inicio) {
		this.fecha_inicio = fecha_inicio;
	}

	public LocalDate getFecha_finalizacion() {
		return fecha_finalizacion;
	}

	public void setFecha_finalizacion(LocalDate fecha_finalizacion) {
		this.fecha_finalizacion = fecha_finalizacion;
	}

	public float getMonto() {
		return monto;
	}

	public void setMonto(float monto) {
		this.monto = monto;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cod_proyecto;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Proyecto other = (Proyecto) obj;
		if (cod_proyecto != other.cod_proyecto)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "\n-------------------------------------------------"
				  +"\n Codigo de proyecto: "+cod_proyecto
				  +"\n Nombre de proyecto: "+nom_proyecto 
				  +"\n Fecha de inicio: "+fecha_inicio 
				  +"\n Fecha de Finalizacion: "+fecha_finalizacion
				  +"\n Monto de proyecto: $"+monto
				  +"\n-------------------------------------------------";
	}
}
