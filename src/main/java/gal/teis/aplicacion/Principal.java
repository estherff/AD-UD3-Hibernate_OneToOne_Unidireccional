/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
https://www.javatutoriales.com/2009/09/hibernate-parte-7-hql-primera-parte.html
 */
package gal.teis.aplicacion;

import gal.teis.libreriadam.ControlData;
import gal.teis.modelo.Empleado;
import gal.teis.modelo.Direccion;
import gal.teis.modelo.EmpleadoDireccion;
import gal.teis.presistencia.EmpresaDAO;
import gal.teis.presistencia.HibernateUtil;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author Esther Ferreiro
 */
public class Principal {

    static Scanner sc = new Scanner(System.in);

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        /**
         * *********GUARDAR*************
         */
        //1º Se crear instancia de Empleado y se guarda en la BD
        Empleado emp1 = new Empleado("Rosa", "Pin", 2000.0);
        Empleado emp2 = new Empleado("Alberto", "Pereira", 2500.0);

        EmpresaDAO.guardaEmpleado(emp1);
        EmpresaDAO.guardaEmpleado(emp2);

        //2º Se crea instancia de dirección, se hace setter a Empleado y se almacena en la BD
        Direccion dire1 = new Direccion("C/Sol nº 5", "Vigo");
        Direccion dire2 = new Direccion("C/Principal nº 30", "Vigo");

        //3º Guardo el empleado en el atributo que hay en Dirección
        dire1.setEmpleado(emp1);
        dire2.setEmpleado(emp2);

        //4º Almaceno cada dirección (cada una lleva a su empleado)
        EmpresaDAO.guardaDireccion(dire1);
        EmpresaDAO.guardaDireccion(dire2);

        /**
         * ********LISTAR LOS ELEMENTOS *************
         */
        //Listar todos los elementos de la tabla empleado con su dirección
        //Devuelve una lista de array de objetos
        //que tendrá tantos elementos como registro haya extraído
        //Cada elemento se corresponde con un array de Object con 2 posiciones,
        //una por cada tabla relacionada que uso en la consulta
        List<Object[]> listaEmpleadosDireccion = EmpresaDAO.obtenerListaEmpDirec();
        if (!Objects.isNull(listaEmpleadosDireccion)) {
            System.out.println("Hay " + listaEmpleadosDireccion.size() + " empleados en la base de datos");
            for (int i = 0; i < listaEmpleadosDireccion.size(); i++) {
                System.out.println("Empleado " + i + ": " + listaEmpleadosDireccion.get(i)[0] + ", Empleado: " + listaEmpleadosDireccion.get(i)[1]);
            }
        }

        /**
         * *********OBTENER UN EMPLEADO POR SU ID*************
         */
        //Buscar un empleado por su id
        //Aquí no obtengo su dirección 
        System.out.println("Introduce el id del elemento a buscar ");
        int id1 = ControlData.lerInt(sc);
        Empleado empleado1 = EmpresaDAO.obtenEmpleado(id1);
        System.out.println(empleado1);

        //Buscar un empleado por su id
        //Aquí obtengo su dirección 
        //Solo va a obtener un elemento que es un array de Object de dos posiciones,
        //una para cada tabla relacionada que uso en la consulta
        List<Object[]> empleadosDireccion = EmpresaDAO.obtenEmplDirec(id1);
        if (!Objects.isNull(empleadosDireccion)) {
            System.out.println("Empleado " + listaEmpleadosDireccion.get(0)[0] + ", Empleado: " + listaEmpleadosDireccion.get(0)[1]);
        }

        /**
         * *********ELIMINAR EMPLEADO*************
         */
        System.out.println("Introduce el id del elemento a eliminar ");
        int id2 = ControlData.lerInt(sc);
        Empleado empleado2 = EmpresaDAO.obtenEmpleado(id2);
        if (!Objects.isNull(empleado2)) {
            System.out.println(empleado2);
            EmpresaDAO.eliminaEmpleado(empleado2);
        } else {
            System.out.println("El empleado no existe");
        }

        /**
         * *********ACTUALIZAR EMPLEADO*************
         */
        System.out.println("Introduce el id del elemento a modificar ");
        int id3 = ControlData.lerInt(sc);
         System.out.println("Introduce el nuevo nombre del empleado");
        String nombre = ControlData.lerString(sc);
        List<Object[]> empleadosDireccion1 = EmpresaDAO.obtenEmplDirec(id3);
        if (!Objects.isNull(empleadosDireccion)) {
            Empleado empleadoActualizar = ((Empleado) (empleadosDireccion1.get(0)[1]));
            empleadoActualizar.setNombre("Lola");
            EmpresaDAO.actualizaEmpleado(empleadoActualizar);
        }

        //Cierra la sesión de Hibernate
        HibernateUtil.shutdown();
        //Cierra el objeto Scanner
        sc.close();
    }

}
