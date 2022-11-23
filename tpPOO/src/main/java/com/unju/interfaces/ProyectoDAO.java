package com.unju.interfaces;

import java.util.List;

import com.unju.model.Proyecto;

public interface ProyectoDAO {
	public void create(Proyecto proyecto) throws Exception;
	public void eliminar(Integer id) throws Exception;
	public void editar(Proyecto proyecto) throws Exception;
	public List<Proyecto> findProyectoEntities();
	public List<Proyecto> findProyectoEntities(int maxResults, int firstResult);
	public Proyecto findProyecto(Integer id);
	public int getProyectoCount();
}
