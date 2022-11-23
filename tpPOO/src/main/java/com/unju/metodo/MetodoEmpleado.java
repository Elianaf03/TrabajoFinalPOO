package com.unju.metodo;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JOptionPane;

import com.unju.implement.EmpleadoDAOImpl;
import com.unju.interfaces.EmpleadoDAO;
import com.unju.model.Empleado;

public class MetodoEmpleado {
	
	/* INSTANCIAS DE LAS CLASES */
	Empleado empleado = new Empleado();
	EmpleadoDAO daoEmpl = new EmpleadoDAOImpl();
	
	List<Empleado> listaEmpleados = daoEmpl.findEmpleadoEntities();
	
	/* AGREGA UN EMPLEADO A LA BASE DE DATOS */
	public void insertar_empleado() {
		try {
			empleado.setN_legajo(Integer.parseInt(JOptionPane.showInputDialog(" Nro de Legajo: ")));
			empleado.setApellido(JOptionPane.showInputDialog(" Apellido: "));
			empleado.setNombre(JOptionPane.showInputDialog(" Nombre: "));
			empleado.setDni(Integer.parseInt(JOptionPane.showInputDialog(" DNI: ")));
			int dia = Integer.parseInt(JOptionPane.showInputDialog(" Fecha de nacimiento \n\n --- Dia: "));
			int mes = Integer.parseInt(JOptionPane.showInputDialog(" --- Mes: "));
			int anio = Integer.parseInt(JOptionPane.showInputDialog(" --- Año: "));
			empleado.setFecha_nacimiento(LocalDate.of(anio, mes, dia));
			empleado.setSueldo(Float.parseFloat(JOptionPane.showInputDialog(" Sueldo $: ")));
			empleado.setCod_proyecto(0);
			
			daoEmpl.agregar(empleado);
			JOptionPane.showMessageDialog(null, "Empleado registrado", "CONFIRMADO", 1);
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Ingrese valores validos en los campos", "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	/* ELIMINA UN EMPLEADO DE LA BASE DE DATOS */
	public void eliminar_empleado() {
		int eliminar, contador=0;
        try {
        	List<Empleado> listaEmpleados = daoEmpl.findEmpleadoEntities();
            int dni = Integer.parseInt(JOptionPane.showInputDialog(" Ingrese DNI de empleado a eliminar: "));
            for (Empleado e : listaEmpleados) {
                if(dni == e.getDni()) {
                    contador++;
                    eliminar = e.getN_legajo();
                    daoEmpl.eliminar(eliminar);
                    JOptionPane.showMessageDialog(null, "Se elimino correctamente", "CONFIRMADO", 1);
                }
            }
            if (contador==0) {
            	JOptionPane.showMessageDialog(null, "DNI ingresado no se encuentra", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
        	JOptionPane.showMessageDialog(null, "Ingrese datos numericos", "ERROR", JOptionPane.ERROR_MESSAGE);
        } 
    }
	
	/* MODIFICA UN EMPLEADO DE LA BASE DE DATOS */
	public void modificar_empleado() throws Exception {
		boolean salir = false;
		int opcion, contador=0;
		List<Empleado> listaEmpleados = daoEmpl.findEmpleadoEntities();
		int dni = Integer.parseInt(JOptionPane.showInputDialog(" Ingrese DNI de empleado a modificar: "));
		for(Empleado e : listaEmpleados) {
			if(dni == e.getDni()) {
				contador++;
				do { 
					try {
						opcion = Integer.parseInt(JOptionPane.showInputDialog("------------------------------------"
								+ "\n  MODIFICAR EMPLEADO" 
								+ "\n------------------------------------"
								+ "\n  1. Apellido" 
								+ "\n  2. Nombre"
								+ "\n  3. DNI"
								+ "\n  4. Fecha de nacimiento"
								+ "\n  5. Sueldo basico"
								+ "\n  6. Volver"
								+ "\n------------------------------------" 
								+ "\n Elija una opcion:"));
						switch(opcion) {
						case 1:
							empleado.setApellido(JOptionPane.showInputDialog(" Nuevo nombre de empleado: "));
							daoEmpl.editar(empleado);
							JOptionPane.showMessageDialog(null, "\n Dato actualizado");
							break;
						case 2:
							empleado.setApellido(JOptionPane.showInputDialog(" Nuevo apellido de empleado: "));
							daoEmpl.editar(empleado);
							JOptionPane.showMessageDialog(null, "\n Dato actualizado");
							break;
						case 3:
							empleado.setDni(Integer.parseInt(JOptionPane.showInputDialog(" Nuevo DNI de empleado: ")));
							daoEmpl.editar(empleado);
							JOptionPane.showMessageDialog(null, "\n Dato actualizado");
							break;
						case 4:
							int dia = Integer.parseInt(JOptionPane.showInputDialog(" Nueva fecha de nacimiento de empleado \n\n --- Dia: "));
							int mes = Integer.parseInt(JOptionPane.showInputDialog(" --- Mes: "));
							int anio = Integer.parseInt(JOptionPane.showInputDialog(" --- Año: "));
							empleado.setFecha_nacimiento(LocalDate.of(anio, mes, dia)); 
							daoEmpl.editar(empleado);
							JOptionPane.showMessageDialog(null, "\n Dato actualizado");
							break;
						case 5:
							empleado.setSueldo(Integer.parseInt(JOptionPane.showInputDialog(" Nuevo sueldo basico: ")));
							daoEmpl.editar(empleado);
							JOptionPane.showMessageDialog(null, "\n Dato actualizado");
							break;
						case 6:
							salir=true;
							break;	
						default:
							JOptionPane.showMessageDialog(null, "Opcion incorrecta", "ERROR", JOptionPane.ERROR_MESSAGE);
							break;
						}
					} catch(NumberFormatException ex) {
						JOptionPane.showMessageDialog(null, "Ingrese valores numericos enteros", "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
					}
					
				} while(salir!=true);
			}
		}
		if(contador==0) {
			JOptionPane.showMessageDialog(null, "Empleado no encontrado", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/* MUESTRA LA LISTA DE LOS EMPLEADOS REGISTRADOS */
	public void lista_empleados() {
		try {
			List<Empleado> listaEmpleados = daoEmpl.findEmpleadoEntities();
			for (Empleado e : listaEmpleados) {
				JOptionPane.showMessageDialog(null,
						"-------------------------------------------------" + "\n       LISTA DE EMPLEADOS" + e.toString()
								+ "\n Total de Empleados: " + (listaEmpleados.size()));
			}
		} catch(Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/* LISTA TODOS EMPLEADOS QUE SUPEREN A UN SUELDO BASICO */
	public void listaSB() {
		int contador=0;
		try {
			List<Empleado> listaEmpleados = daoEmpl.findEmpleadoEntities();
			float sb = Float.parseFloat(JOptionPane.showInputDialog(" Ingrese sueldo basico: "));
			for (Empleado e : listaEmpleados) {
				if(sb < e.getSueldo()) {
					contador++;
					JOptionPane.showMessageDialog(null, "\n  LISTA DE EMPLEADOS"+e.toString());
				}
			}
			if(contador==0) {
				JOptionPane.showMessageDialog(null, "No hay empleados que superen el salario ingresado", "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		} catch(Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/* BUSCA UN EMPLEADO A TRAVES DE SU DNI */
	public void buscar_empleado() {
		int contador=0;
		try {
			List<Empleado> listaEmpleados = daoEmpl.findEmpleadoEntities();
			int dni = Integer.parseInt(JOptionPane.showInputDialog(" Ingrese DNI de empleado a buscar: "));
			for (Empleado e : listaEmpleados) {
				if(dni == e.getDni()) {
					contador++;
					JOptionPane.showMessageDialog(null, "\n  EMPLEADOS ENCONTRADO"+e.toString());
				}
			}
			if(contador==0) {
				JOptionPane.showMessageDialog(null, "No hay empleados que superen el salario ingresado", "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		} catch(Exception ex ) {
			JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
		}
	}
}
