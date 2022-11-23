package com.unju.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "empleado")
@NamedQueries({
    @NamedQuery(name = "Empleado.findAll", query = "SELECT e FROM Empleado e")
    , @NamedQuery(name = "Empleado.findByN_legajo", query = "SELECT e FROM Empleado e WHERE e.n_legajo = :n_legajo")
    , @NamedQuery(name = "Empleado.findByApellido", query = "SELECT e FROM Empleado e WHERE e.apellido = :apellido")
    , @NamedQuery(name = "Empleado.findByNombre", query = "SELECT e FROM Empleado e WHERE e.nombre = :nombre")
    , @NamedQuery(name = "Empleado.findByFecha_nacimiento", query = "SELECT e FROM Empleado e WHERE e.fecha_nacimiento = :fecha_nacimiento")
    , @NamedQuery(name = "Empleado.findByDni", query = "SELECT e FROM Empleado e WHERE e.dni = :dni")
    , @NamedQuery(name = "Empleados.findBySueldo", query = "SELECT e FROM Empleado e WHERE e.sueldo = :sueldo")
    , @NamedQuery(name = "Empleados.findByCod_proyecto", query = "SELECT e FROM Empleado e WHERE e.cod_proyecto = :cod_proyecto")})
public class Empleado implements Serializable, Comparable<Empleado>{

	// -----ATRIBUTOS
	@Id
	@Column(name = "n_legajo", nullable = false)
	private int n_legajo;
	@Column(name = "apellido", nullable = false, length = 50)
	private String apellido;
	@Column(name = "nombre", nullable = false, length = 50)
	private String nombre;
	@Column(name = "dni", nullable = false)
	private int dni;
	@Column(name = "fecha_nacimiento", nullable = false)
	private LocalDate fecha_nacimiento;
	@Column(name = "sueldo", nullable = false)
	private float sueldo;
	@JoinColumn(name = "cod_proyecto")
	private int cod_proyecto;
	
	// -----CONSTRUCTOR
	public Empleado() {

	}
	
	public Empleado(int n_legajo, String apellido, String nombre, int dni, LocalDate fecha_nacimiento, float sueldo,
			int cod_proyecto) {
		this.n_legajo = n_legajo;
		this.apellido = apellido;
		this.nombre = nombre;
		this.dni = dni;
		this.fecha_nacimiento = fecha_nacimiento;
		this.sueldo = sueldo;
		this.cod_proyecto = cod_proyecto;
	}

	// -----GETTER y SETTER
	public int getN_legajo() {
		return n_legajo;
	}

	public void setN_legajo(int n_legajo) {
		this.n_legajo = n_legajo;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getDni() {
		return dni;
	}

	public void setDni(int dni) {
		this.dni = dni;
	}

	public LocalDate getFecha_nacimiento() {
		return fecha_nacimiento;
	}

	public void setFecha_nacimiento(LocalDate fecha_nacimiento) {
		this.fecha_nacimiento = fecha_nacimiento;
	}

	public float getSueldo() {
		return sueldo;
	}

	public void setSueldo(float sueldo) {
		this.sueldo = sueldo;
	}
	
	
	public int getCod_proyecto() {
		return cod_proyecto;
	}

	public void setCod_proyecto(int cod_proyecto) {
		this.cod_proyecto = cod_proyecto;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + n_legajo;
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
		Empleado other = (Empleado) obj;
		if (n_legajo != other.n_legajo)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "\n-------------------------------------------------"
			  +"\n Nro de Legajo: "+n_legajo 
			  +"\n Apellido: "+apellido 
			  +"\n Nombre: "+nombre 
			  +"\n DNI: "+dni
			  +"\n Fecha de nacimiento: "+fecha_nacimiento 
			  +"\n Edad: "+calcularEdad()
			  +"\n Sueldo: $"+sueldo
			  +"\n-------------------------------------------------";
	}
	
	public int calcularEdad() {
		int edad;
		LocalDate actual = LocalDate.now();
		Period periodo = Period.between(this.fecha_nacimiento, actual);
		edad = periodo.getYears();
		return edad;
	}

	public int compareTo(Empleado o) {
		return getApellido().compareTo(o.getApellido());
	}
}