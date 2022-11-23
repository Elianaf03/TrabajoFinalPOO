package com.unju;

import javax.swing.JOptionPane;

import com.unju.implement.EmpleadoDAOImpl;
import com.unju.interfaces.EmpleadoDAO;
import com.unju.metodo.MetodoEmpleado;
import com.unju.metodo.MetodoProyecto;
import com.unju.model.Empleado;
import com.unju.model.Proyecto;

public class Submenu {
	boolean salir = false;
	int opcion;

	/* INSTANCIAS DE LAS CLASES */
	Empleado empleado = new Empleado();
	Proyecto proyecto = new Proyecto();
	EmpleadoDAO daoEmpl = new EmpleadoDAOImpl();
	
	MetodoEmpleado metodo_emp = new MetodoEmpleado();
	MetodoProyecto metodo_pro = new MetodoProyecto();

	/* MENU DE EMPLEADO */
	public void menuEmpleado() {
		do {
			try {
				opcion = Integer.parseInt(JOptionPane.showInputDialog("------------------------------------------------"
												+ "\n            MENU EMPLEADO" 
												+ "\n------------------------------------------------"
												+ "\n  1. Agregar empleado" 
												+ "\n  2. Eliminar empleado" 
												+ "\n  3. Modificar empleado"
												+ "\n  4. Listar empleados" 
												+ "\n  5. Empleado que supere sueldo basico" 
												+ "\n  6. Buscar empleado" 
												+ "\n  7. Volver"
												+ "\n------------------------------------------------" 
												+ "\n Elija una opcion:"));

				switch (opcion) {
				case 1:
					metodo_emp.insertar_empleado();
					break;
				case 2:
					metodo_emp.eliminar_empleado();
					break;
				case 3:
					metodo_emp.modificar_empleado();
					break;
				case 4:
					metodo_emp.lista_empleados();
					break;
				case 5:
					metodo_emp.listaSB();
					break;
				case 6:
					try {
						do {
							opcion = Integer.parseInt(JOptionPane.showInputDialog("------------------------------------"
															+ "\n    BUSCAR EMPLEADO" 
															+ "\n------------------------------------"
															+ "\n  1. Por DNI" 
															+ "\n  2. Por Apellido" 
															+ "\n  3. Volver"
															+ "\n------------------------------------" 
															+ "\n Elija una opcion:"));
							switch (opcion) {
							case 1:
								metodo_emp.buscar_empleado();
								break;
							case 2:
								String apellido = JOptionPane.showInputDialog(" Ingrese apellido de empleado buscar: ");
								daoEmpl.buscar_apellido(apellido);
								break;
							case 3:
								break;
							default:
								JOptionPane.showMessageDialog(null, "Opcion incorrecta", "ERROR", JOptionPane.ERROR_MESSAGE);
								break;
							}

						} while (opcion != 3);
					} catch (NumberFormatException e) {
						JOptionPane.showMessageDialog(null, "Ingrese valores numericos enteros", "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
					}
						break;
				case 8:
					salir = true;
					break;
				default:
					JOptionPane.showMessageDialog(null, "Opcion incorrecta", "ERROR", JOptionPane.ERROR_MESSAGE);
					break;
				}
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Ingrese valores numericos enteros", "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
			} 
		} while (salir!=true);
	}

	/* MENU DE PROYECTO */
	public void menuProyecto() {
		do {
			try {
				opcion = Integer.parseInt(JOptionPane.showInputDialog("----------------------------------------------------" 
												+ "\n            MENU DE PROYECTO"
												+ "\n----------------------------------------------------" 
												+ "\n  1. Agregar proyecto"
												+ "\n  2. Eliminar proyecto" 
												+ "\n  3. Listar proyecto"
												+ "\n  4. Agregar empleado a un proyecto" 
												+ "\n  5. Quitar empleado de un proyecto"
												+ "\n  6. Listar proyecto con empleados" 
												+ "\n  7. Total de monto destinado a un proyecto"
												+ "\n  8. Monto que recibe que recibe cada particpante" 
												+ "\n  de un proyecto"
												+ "\n  9. Volver"
												+ "\n----------------------------------------------------" 
												+ "\n Elija una opcion:"));

				switch (opcion) {
				case 1:
					metodo_pro.insertar_proyecto();
					menuProyecto();
					break;
				case 2:
					metodo_pro.eliminar_proyecto();
					menuProyecto();
					break;
				case 3:
					metodo_pro.lista_proyecto();
					menuProyecto();
					break;
				case 4:
					metodo_pro.agregar_empleado();
					menuProyecto();
					break;
				case 5:
					metodo_pro.quitar_empleado();
					menuProyecto();
					break;
				case 6:
					metodo_pro.listar_datosProyecto();
					menuProyecto();
					break;
				case 7:
					metodo_pro.monto_total();
					menuProyecto();
					break;
				case 8:
					metodo_pro.monto_individual();
					menuProyecto();
					break;
				case 9:
					salir = true;
					break;
				default:
					JOptionPane.showMessageDialog(null, "Opcion incorrecta", "ERROR", JOptionPane.ERROR_MESSAGE);
					break;
				}
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Ingrese valores numericos enteros", "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
			} 
		} while (salir != true);
	}
}
