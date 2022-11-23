package com.unju;

import javax.swing.JOptionPane;

public class Main {

	public static void main(String[] args) {
		boolean salir = false;
		int opcion;
		Submenu m = new Submenu();

		do {
			try {
				opcion = Integer.parseInt(JOptionPane.showInputDialog("----------------------------------------"
												+ "\n     MENU  PRINCIPAL" 
												+ "\n----------------------------------------" 
												+ "\n  1. Empleado"
												+ "\n  2. Proyecto" 
												+ "\n  3. Salir" 
												+ "\n----------------------------------------"
												+ "\n Elija una opcion:"));

				switch (opcion) {
				case 1:
					m.menuEmpleado();
					break;
				case 2:
					m.menuProyecto();
					break;
				case 3:
					salir = true;
					JOptionPane.showMessageDialog(null, "---------------------------------"
									+"\n INTEGRANTES:"
									+ "\n 1. FLORES, Eliana" 
									+ "\n 2. VENGOLEA, Ofelia"
									+ "\n---------------------------------", "FIN DE PROYECTO", 1);
					break;
				default:
					JOptionPane.showMessageDialog(null, "Opcion incorrecta", "ERROR", JOptionPane.ERROR_MESSAGE);
					break;
				}

			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Ingrese valores numericos enteros", "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 

		} while (salir != true);
	}
}