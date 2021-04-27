/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gal.teis.presistencia;

import gal.teis.modelo.Direccion;
import gal.teis.modelo.Empleado;
import gal.teis.modelo.EmpleadoDireccion;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Contiene los métodos para realizar las siguientes operaciones con Hibernate:
 * abrir sesión, crear transacción, agregar, eliminar, actualizar, obtener y
 * listaR elementos de la BB
 *
 * @author Esther Ferreiro
 */
public class EmpresaDAO {

    /**
     * Objeto Session y Transaction para porder realizar operaciones sobre la BD
     */
    private static Session sesion;
    private static Transaction transa;

    /**
     * obtenemos una referencia a "SessionFactory" usando nuestra clase de
     * utilidad "HibernateUtil". Una vez que tenemos la "SessionFactory" creamos
     * una conexión a la base de datos e iniciamos una nueva sesión con el
     * método "openSession()". Una vez teniendo la sesión iniciamos una nueva
     * transacción y obtenemos una referencia a ella con "beginTransaction()"
     *
     */
    private static void iniciaOperacion() throws HibernateException {
        sesion = HibernateUtil.getSessionFactory().openSession();
        transa = sesion.beginTransaction();
    }

    /**
     * Si se produce una excepción queremos que la transacción que se está
     * ejecutando se deshaga y se relance la excepción (podríamos lanzar una
     * excepción propia)
     */
    private static void manejaExcepcion(HibernateException he) throws HibernateException {
        transa.rollback();
        throw new HibernateException("Ha sucedido un error en la capa de acceso a datos", he);
    }

    /*
    Ahora crearemos los métodos que nos permitirán realizar las tareas de persistencia 
    de una entidad "Contacto", conocidas en lenguaje de base de datos como CRUD: guardarla, 
    actualizarla, eliminarla, buscar un entidad "Contacto" y obtener todas los contactos 
    que existen en la base de datos
     */
    /**
     * Permite guardar un empleado en la BD
     *
     * @param empleado Contacto, elemento a insertar en la BD
     * @return
     */
    public static int guardaEmpleado(Empleado empleado)  throws HibernateException{
        int id = 0;

        try {
            iniciaOperacion();
            //guarda el empleado en la base de datos y devuelve el id generado
            id = (int) sesion.save(empleado);
            transa.commit();
        } catch (HibernateException he) {
            manejaExcepcion(he);
        } finally {
            sesion.close();
        }
        return id;
    }

    public static long guardaDireccion(Direccion dir)  throws HibernateException{
        int id = 0;

        try {
            iniciaOperacion();
            //guarda el empleado en la base de datos y devuelve el id generado
            id = (int) sesion.save(dir);
            transa.commit();
        } catch (HibernateException he) {
            manejaExcepcion(he);
         } finally {
            sesion.close();
        }
        return id;
    }


    public static void actualizaEmpleado(Empleado empleado) throws HibernateException {
        try {
            iniciaOperacion();
            sesion.update(empleado);
            transa.commit();
        } catch (HibernateException he) {
            manejaExcepcion(he);
        } finally {
            sesion.close();
        }
    }

    public static Empleado obtenEmpleado(int id)  throws HibernateException{
        Empleado empleado = null;
        boolean obtenido = false;

        try {
            //abre la sesión e inicia la transición
            iniciaOperacion();

            empleado = sesion.get(Empleado.class, id);
            transa.commit();
        } catch (HibernateException he) {
            manejaExcepcion(he);
        } finally {
            sesion.close();
        }
        return empleado;
    }

    public static List<Object[]> obtenEmplDirec(int id)  throws HibernateException{
        List<Object[]>  empleadoDireccion = null;
        boolean obtenido = false;

        try {
            //abre la sesión e inicia la transición
            iniciaOperacion();
            empleadoDireccion = sesion.createQuery("from Direccion as dire "
                    + "inner join dire.empleado as emp where emp.id = :id").
                    setParameter("id", id).list();
            //query.uniqueResult();
            transa.commit();
        } catch (HibernateException he) {
            manejaExcepcion(he);
        } finally {
            sesion.close();
        }
        return empleadoDireccion;
    }

    public static void eliminaEmpleado(Empleado empleado) throws HibernateException {
        try {
            iniciaOperacion();
            sesion.delete(empleado);
            transa.commit();
        } catch (HibernateException he) {
            manejaExcepcion(he);
        } finally {
            sesion.close();
        }
    }

    public static List<Empleado> obtenListaEmpleados() throws HibernateException {
        List<Empleado> listaContactos = null;

        try {
            iniciaOperacion();
            listaContactos = sesion.createQuery("from Empleado").list();
        } finally {
            sesion.close();
        }

        return listaContactos;
    }

    public static List<Object[]> obtenerListaEmpDirec() throws HibernateException {
        List<Object[]> empleadoDireccion = null;

        try {
            iniciaOperacion();
            //En este caso, recuperamos una lista de arrays de objetos pues 
            //la consulta incluye dos tablas.
            /*Instrucción HQL para acceder a dos tablas relacionadas 1 a 1 de forma unidireccional.
            “ver elementos mapeados con la clase Direccion (alias dire) enlazada 
            con dire.empleado (alias emp)” */
            empleadoDireccion = sesion.createQuery("from Direccion as dire "
                    + "inner join dire.empleado as emp").list();
        } finally {
            sesion.close();
        }

        return empleadoDireccion;
    }
}
