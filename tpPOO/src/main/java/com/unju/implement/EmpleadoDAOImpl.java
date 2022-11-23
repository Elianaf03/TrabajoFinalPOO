package com.unju.implement;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.swing.JOptionPane;

import com.unju.exceptions.NonexistentEntityException;
import com.unju.exceptions.PreexistingEntityException;
import com.unju.interfaces.EmpleadoDAO;
import com.unju.model.Empleado;

public class EmpleadoDAOImpl implements EmpleadoDAO {
	
	public EmpleadoDAOImpl() {
        this.emf = Persistence.createEntityManagerFactory("persistencia");
    }
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

	List<Empleado> listaEmpleados = new ArrayList<Empleado>();
	Empleado empleado = new Empleado();

	/* AGREGA A EMPLEADOS */
	public void agregar(Empleado empleado) throws PreexistingEntityException, Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			em.persist(empleado);
			em.getTransaction().commit();
		} catch (Exception ex) {
			if (findEmpleado(empleado.getN_legajo()) != null) {
				throw new PreexistingEntityException("Empleados " + empleado + " already exists.", ex);
			}
			throw ex;
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	/* MODIFICA EMPLEADOS */
	public void editar(Empleado empleado) throws NonexistentEntityException, Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			empleado = em.merge(empleado);
			em.getTransaction().commit();
		} catch (Exception ex) {
			String msg = ex.getLocalizedMessage();
			if (msg == null || msg.length() == 0) {
				int id = empleado.getN_legajo();
				if (findEmpleado(id) == null) {
					throw new NonexistentEntityException("The empleados with id " + id + " no longer exists.");
				}
			}
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	/* ELIMINA EMPLEADOS */
	public void eliminar(Integer id) throws NonexistentEntityException, Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Empleado empleado;
			try {
				empleado = em.getReference(Empleado.class, id);
				empleado.getN_legajo();
			} catch (EntityNotFoundException enfe) {
				throw new NonexistentEntityException("The empleados with id " + id + " no longer exists.", enfe);
			}
			em.remove(empleado);
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	public List<Empleado> findEmpleadoEntities() {
		return findEmpleadoEntities(true, -1, -1);
	}

	public List<Empleado> findEmpleadoEntities(int maxResults, int firstResult) {
		return findEmpleadoEntities(false, maxResults, firstResult);
	}

	private List<Empleado> findEmpleadoEntities(boolean all, int maxResults, int firstResult) {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			cq.select(cq.from(Empleado.class));
			Query q = em.createQuery(cq);
			if (!all) {
				q.setMaxResults(maxResults);
				q.setFirstResult(firstResult);
			}
			return q.getResultList();
		} finally {
			em.close();
		}
	}

	/* BUSCA EMPLEADO POR ID */
	public Empleado findEmpleado(Integer id) {
		EntityManager em = getEntityManager();
		try {
			return em.find(Empleado.class, id);
		} finally {
			em.close();
		}
	}

	public int getEmpleadosCount() {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			Root<Empleado> rt = cq.from(Empleado.class);
			cq.select(em.getCriteriaBuilder().count(rt));
			Query q = em.createQuery(cq);
			return ((Long) q.getSingleResult()).intValue();
		} finally {
			em.close();
		}
	}
	
	/* BUSCA POR APELLIDO */
	@SuppressWarnings("unchecked")
	public List<Empleado> buscar_apellido(String apellido) {
		EntityManager em = getEntityManager();
		listaEmpleados = em.createQuery("SELECT e FROM Empleado e WHERE e.apellido = :apellido").setParameter("apellido", apellido).getResultList();
		for(Empleado e : listaEmpleados) {
			JOptionPane.showMessageDialog(null, "\n  EMPLEADO ENCONTRADO"+e.toString());
		}
		return listaEmpleados;
	}
}
