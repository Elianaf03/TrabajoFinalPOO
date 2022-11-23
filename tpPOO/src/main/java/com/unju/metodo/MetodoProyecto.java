package com.unju.metodo;

import java.time.LocalDate;
import java.util.List;
import javax.swing.JOptionPane;

import com.unju.implement.EmpleadoDAOImpl;
import com.unju.implement.ProyectoDAOImpl;
import com.unju.interfaces.EmpleadoDAO;
import com.unju.interfaces.ProyectoDAO;
import com.unju.model.Empleado;
import com.unju.model.Proyecto;



public class MetodoProyecto {
	/* INSTANCIAS DE LAS CLASES */
	Proyecto proyecto = new Proyecto();
	Empleado empleado = new Empleado();
	MetodoEmpleado metodo_emp = new MetodoEmpleado();
	
	EmpleadoDAO daoEmpl = new EmpleadoDAOImpl();
	ProyectoDAO daoPro = new ProyectoDAOImpl();
	
	/* AGREGA UN PROYECTO A LA BASE DE DATOS */
	public void insertar_proyecto() {
		try {
			int dia, mes, anio;
			proyecto.setCod_proyecto(Integer.parseInt(JOptionPane.showInputDialog(" Codigo de proyecto: ")));
			proyecto.setNom_proyecto(JOptionPane.showInputDialog(" Nombre de proyecto: "));
			dia = Integer.parseInt(JOptionPane.showInputDialog(" Fecha de incicio  \n\n --- Dia: "));
			mes = Integer.parseInt(JOptionPane.showInputDialog(" --- Mes: "));
			anio = Integer.parseInt(JOptionPane.showInputDialog(" --- Año: "));
			proyecto.setFecha_inicio(LocalDate.of(anio, mes, dia));
			dia = Integer.parseInt(JOptionPane.showInputDialog(" Fecha de finalizacion  \n\n --- Dia: "));
			mes = Integer.parseInt(JOptionPane.showInputDialog(" --- Mes: "));
			anio = Integer.parseInt(JOptionPane.showInputDialog(" --- Año: "));
			proyecto.setFecha_finalizacion(LocalDate.of(anio, mes, dia));
			proyecto.setMonto(Float.parseFloat(JOptionPane.showInputDialog(" Monto de proyecto: ")));

			daoPro.create(proyecto);
			JOptionPane.showMessageDialog(null, "Proyecto registrado", "CONFIRMADO", 1);
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Ingrese valores validos en los campos", "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	/* ELIMINA UN PROYECTO DE LA BASE DE DATOS */
	public void eliminar_proyecto() {
		int eliminar, contador=0;
        try {
            List<Proyecto> listaProyectos = daoPro.findProyectoEntities();
            int id_proyecto = Integer.parseInt(JOptionPane.showInputDialog(" Ingrese codigo de proyecto a eliminar: "));
            
            for (Proyecto p : listaProyectos) {
                if (id_proyecto == p.getCod_proyecto()) {
                    contador++;
                    eliminar = p.getCod_proyecto();
                    daoPro.eliminar(eliminar);
                    JOptionPane.showMessageDialog(null, "Se elimino correctamente", "CONFIRMADO", 1);
                }
            }
            if (contador==0) {
            	JOptionPane.showMessageDialog(null, "Codigo ingresado no se encuentra", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
        	JOptionPane.showMessageDialog(null, "Ingrese datos numericos", "ERROR", JOptionPane.ERROR_MESSAGE);
        } 
    }
	
	/* LISTA TODOS LOS PROYECTOS */
	public void lista_proyecto() {
		try {
			List<Proyecto> listaProyectos = daoPro.findProyectoEntities();
			for (Proyecto p : listaProyectos) {
				JOptionPane.showMessageDialog(null,
						"-------------------------------------------------" + "\n       LISTA DE PROYECTOS" + p.toString()
								+ "\n Total de Proyectos: " + (listaProyectos.size()));
			}
		} catch(Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/* AGREGA A UN EMPLEADO A UN PROYECTO */
	public void agregar_empleado() {
		int con1 = 0, con2=0;
		try {
			List<Empleado> listaEmpleados = daoEmpl.findEmpleadoEntities();
			List<Proyecto> listaProyectos = daoPro.findProyectoEntities();

			int id_proyecto = Integer.parseInt(JOptionPane.showInputDialog(" Ingrese codigo de proyecto: "));
			for (Proyecto p : listaProyectos) {
				if (id_proyecto == p.getCod_proyecto()) {
					con1++;
					int legajo = Integer.parseInt(JOptionPane
							.showInputDialog(" Ingrese el numero de legajo de empleado para asignar a un proyecto: "));

					for (Empleado e : listaEmpleados) {
						if (legajo == e.getN_legajo()) {
							con2++;
							empleado.setN_legajo(legajo);
							empleado.setCod_proyecto(id_proyecto);
							daoEmpl.editar(empleado);
							JOptionPane.showMessageDialog(null, "Empleado se agrego correctamente", "CONFIRMADO", 1);
						}
					}
					if (con2 == 0) {
						JOptionPane.showMessageDialog(null, "Legajo ingresado no existe", "ERROR",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
			if (con1 == 0) {
				JOptionPane.showMessageDialog(null, "Codigo de proyecto no existe", "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
	}
	
	/* ELIMINA A UN EMPLEADO DE UN PROYECTO */
	public void quitar_empleado() {
		int con1=0, con2=0;
        try {
            List<Empleado> listaEmpleados = daoEmpl.findEmpleadoEntities();
            List<Proyecto> listaProyectos = daoPro.findProyectoEntities();
            lista_proyecto();
            int id_proyecto = Integer.parseInt(JOptionPane.showInputDialog(" Ingrese codigo de proyecto para eliminar a un empleado: "));
            for (Proyecto p : listaProyectos) {
                if (id_proyecto == p.getCod_proyecto()) {
                    con1++;
                    int legajo = Integer.parseInt(JOptionPane.showInputDialog(" Ingrese el numero de legajo para eliminar empleado de un proyecto: "));
                    for (Empleado e : listaEmpleados) {
                        if (legajo == e.getN_legajo() && id_proyecto == e.getCod_proyecto()) {
                            con2++;
                            e.setCod_proyecto(0);
                            daoEmpl.editar(e);
                            JOptionPane.showMessageDialog(null, "Empleado fue eliminado de proyecto correctamente", "CONFIRMADO", 1);
                        }
                    }
                }

            }
            if (con2 == 0) {
            	 JOptionPane.showMessageDialog(null, "No se puede eliminar porque el proyecto no tiene ese empleado", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
            if (con1 == 0) {
            	JOptionPane.showMessageDialog(null, "El codigo ingresado no existe", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
        	JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);

        }
    }
	
	/* LISTA TODOS LOS DATOS DE UN PROYECTO Y EMPLEADO VINCULADO */
	public void listar_datosProyecto() {
		int aux1=0, aux2=0;
        try {
            List<Proyecto> listaProyectos = daoPro.findProyectoEntities();
            List<Empleado> listaEmpleados = daoEmpl.findEmpleadoEntities();
            
            lista_proyecto();
            int id_proyceto = Integer.parseInt(JOptionPane.showInputDialog(" Ingrese codigo de proyecto para listar datos: "));
            for (Proyecto p : listaProyectos) {
                if (id_proyceto == p.getCod_proyecto()) {
                    aux1++;
                    for (Empleado e : listaEmpleados) {
                        if (id_proyceto == e.getCod_proyecto()) {
                            aux2++;
                            JOptionPane.showMessageDialog(null,
            						"-------------------------------------------------" + "\n       DATOS DE UN PROYECTO" + p.toString()
            								+ "\n Nro de legajo: "+e.getN_legajo()
            								+ "\n Apellido y Nombre: "+e.getApellido()+" "+e.getNombre()
            								+"\n-------------------------------------------------"
            								+ "\n Total de Proyectos: " + (listaProyectos.size()));
                        }
                    }
                }

            }
            if (aux1 == 0) {
            	JOptionPane.showMessageDialog(null, "No existe codigo ingresado", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
            if (aux2 == 0 && aux1 > 0) {
            	JOptionPane.showMessageDialog(null, "El Proyecto no tiene empleados", "ERROR", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception ex) {
        	JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
	
	/* CALCULA EL MONTO TOTAL DE CADA PROYECTO */
	public void monto_total() {
        List<Proyecto> ListaProyectos = daoPro.findProyectoEntities();
        double total = 0;
        for (Proyecto p : ListaProyectos) {
            total = total + p.getMonto();

        }
        
        lista_proyecto();
        JOptionPane.showMessageDialog(null, "Monto total de prosupuesto a proyectos: $"+total);
    }
	
	/* CALCULO INDIVIDUAL */
	public float monto_individual() {
        float monto_ind = 0;
        int contador = 0;
        try {
            List<Proyecto> listaProyectos = daoPro.findProyectoEntities();
            List<Empleado> listaEmpleados = daoEmpl.findEmpleadoEntities();
            lista_proyecto();
            int id_proyecto = Integer.parseInt(JOptionPane.showInputDialog(" Ingrese codigo de proyecto para listar monto de empleado"));
            
            for (Proyecto p : listaProyectos) {
                if (id_proyecto == p.getCod_proyecto()) {
                    for (Empleado e : listaEmpleados) {
                        if (id_proyecto == e.getCod_proyecto()) {
                            contador++;
                            monto_ind = (float) (p.getMonto() / contador);
                           JOptionPane.showMessageDialog(null, p.toString()+"\n Nro de legajo: "+e.getN_legajo()
                           +"\n Apellido y Nombre: "+e.getApellido()+" "+e.getNombre()
                           +"\n-------------------------------------------------"
                           +"\n Total de empleados en el proyecto: "+contador++);
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("erro: " + e.getMessage());
        }
        return monto_ind;
    }
}
