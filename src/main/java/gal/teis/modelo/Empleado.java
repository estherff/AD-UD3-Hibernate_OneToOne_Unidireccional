/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gal.teis.modelo;

import java.io.Serializable;

import javax.persistence.*;


/**
 *
 * @author Esther Ferreiro

 */
@Entity
@Table (name = "empleados")
public class Empleado implements Serializable {

    @Id //pk
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private int id;
    
    @Column(name = "emNombre")
    private String nombre;
    
    @Column(name = "emApellidos")
    private String apellidos;
    
    @Column(name = "emSueldo")
    private Double sueldo;
    

    /**
     * El constructor sin argumentos es obligatorio ya que Hibernate creará
     * instancias de esta clase usando reflexion cuando recupere las entidades
     * de la BD. Este constructor puede ser privado (si es que no quieren
     * permitir que alguien más lo utilice), pero usualmente el nivel de acceso
     * más restrictivo que usaremos es el de paquete (el default), ya que esto
     * hace más eficiente la creación de los objetos.
     */
    Empleado() {
    }

    public Empleado(String nombre, String apellidos, Double sueldo) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.sueldo = sueldo;
        this.id = id;
    }

    public long getId() {
        return id;
    }

    
    public String getApellidos() {
        return apellidos;
    }

    public Double getSueldo() {
        return sueldo;
    }

    public String getNombre() {
        return nombre;
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

    @Override
    public String toString() {
        return "Empleado{" + "id=" + id + ", nombre=" + nombre + ", apellidos=" + apellidos + ", sueldo=" + sueldo + '}';
    }


    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
        result = prime * result + ((apellidos == null) ? 0 : apellidos.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Empleado other = (Empleado) obj;
        if (nombre == null) {
            if (other.nombre != null) {
                return false;
            }
        } else if (!nombre.equals(other.nombre)) {
            return false;
        }
        if (id != other.id) {
            return false;
        }
        if (apellidos == null) {
            if (other.apellidos != null) {
                return false;
            }
        } else if (!apellidos.equals(other.apellidos)) {
            return false;
        }
        return true;
    }

}
