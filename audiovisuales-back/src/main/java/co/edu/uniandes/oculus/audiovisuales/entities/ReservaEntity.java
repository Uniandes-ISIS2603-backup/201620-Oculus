/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.oculus.audiovisuales.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 *
 * @author fa.lopez10
 */
@Entity
public class ReservaEntity extends BaseEntity implements Serializable{
    
    public final static String RESERVA_CANCELADA = "Reserva Cancelada";
    
    private Date fecha;
    private String estado;
    @ManyToOne
    private EquipoEntity equipo;
    
    @ManyToOne
    private ProfesorEntity profesor;
    
    public Date getFecha()
    {
        return fecha;
    }
    
    public void setFecha( Date fecha)
    {
        this.fecha = fecha;
    }
    
    public String getEstado()
    {
        return estado;
    }
    
    public void setEstado( String estado)
    {
        this.estado = estado;
    }
    
    public EquipoEntity getEquipo()
    {
        return equipo;
    }
    
    public void setProfesor(ProfesorEntity profesor)
    {
        this.profesor = profesor;
    }
    
    public ProfesorEntity getProfesor()
    {
        return profesor;
    }

    
}
