package com.unju.interfaces;

import java.util.List;

import com.unju.exceptions.NonexistentEntityException;
import com.unju.exceptions.PreexistingEntityException;
import com.unju.model.Empleado;


public interface EmpleadoDAO {
	public void agregar(Empleado empleado) throws PreexistingEntityException, Exception;
	public void editar(Empleado empleado) throws NonexistentEntityException, Exception;
	public void eliminar(Integer id) throws NonexistentEntityException, Exception;
	public List<Empleado> findEmpleadoEntities();
	public List<Empleado> findEmpleadoEntities(int maxResults, int firstResult);
	public Empleado findEmpleado(Integer id);
	public int getEmpleadosCount();
	public List<Empleado> buscar_apellido(String apellido);
}
