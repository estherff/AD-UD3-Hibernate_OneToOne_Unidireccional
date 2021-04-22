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
 * 
 */
@Entity
@Table (name = "direcciones")
public class Direccion implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private int id;
     
    @Column(name = "direccion")
    private String direccion;
    
    @Column(name = "ciudad")
    private String ciudad;
   
   
    /*Tenemos que asociar el objeto empleado con la dirección, para ello creamos 
    un atributo con el objeto dirección*/
    @OneToOne 
    @JoinColumn(name = "idEmpleado")
    private Empleado empleado;
    
     
         
    /**
     * El constructor sin argumentos es obligatorio ya que Hibernate creará
     * instancias de esta clase usando reflexion cuando recupere las entidades
     * de la BD. Este constructor puede ser privado (si es que no quieren
     * permitir que alguien más lo utilice), pero usualmente el nivel de acceso
     * más restrictivo que usaremos es el de paquete (el default), ya que esto
     * hace más eficiente la creación de los objetos.
     */
    Direccion() {
    }

    public Direccion(String direccion, String ciudad) {
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.id = id;
        this.empleado = empleado;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    
    
    public String getDireccion() {
        return direccion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

   
    public int getId() {
        return id;
    }
    

    @Override
    public String toString() {
        return "Dirección: " + direccion + ", ciudad: " + ciudad;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((direccion == null) ? 0 : direccion.hashCode());
        result = prime * result + id;
        result = prime * result + ((ciudad == null) ? 0 : ciudad.hashCode());
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
        Direccion other = (Direccion) obj;
        if (direccion == null) {
            if (other.direccion != null) {
                return false;
            }
        } else if (!direccion.equals(other.direccion)) {
            return false;
        }
        if (id != other.id) {
            return false;
        }
        if (ciudad == null) {
            if (other.ciudad != null) {
                return false;
            }
        } else if (!ciudad.equals(other.ciudad)) {
            return false;
        }
        return true;
    }

}
