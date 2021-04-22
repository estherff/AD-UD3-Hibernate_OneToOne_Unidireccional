/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gal.teis.modelo;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Esther Ferreiro
 */
public class EmpleadoDireccion {

    private int idDireccion;
    private String direccion;
    private String ciudad;
    
    private int idEmpleado;
    private String nombre;
    private String apellidos;   
    private Double sueldo;

    public EmpleadoDireccion(int idDireccion, String direccion, String ciudad, int idEmpleado, String nombre, String apellidos, Double sueldo) {
        this.idDireccion = idDireccion;
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.idEmpleado = idEmpleado;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.sueldo = sueldo;
    }

    public int getIdDireccion() {
        return idDireccion;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdDireccion(int idDireccion) {
        this.idDireccion = idDireccion;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }
    
  

    

 

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setSueldo(Double sueldo) {
        this.sueldo = sueldo;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }



    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public Double getSueldo() {
        return sueldo;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getCiudad() {
        return ciudad;
    }

    @Override
    public String toString() {
        return "EmpleadoDireccion{" + "idDireccion=" + idDireccion + ", direccion=" + direccion + ", ciudad=" + ciudad + ", idEmpleado=" + idEmpleado + ", nombre=" + nombre + ", apellidos=" + apellidos + ", sueldo=" + sueldo + '}';
    }

   
    
    
}
