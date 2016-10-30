/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.oculus.audiovisuales.entities;

import java.io.Serializable;
import java.util.ArrayList;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author gc.andrade10
 */
@Entity
public class EquipoEntity extends BaseEntity implements Serializable
{
    @OneToOne
    @PodamExclude
    private TipoEntity tipo;
   
    private String caracteristicas;
    
    @PodamExclude
    @OneToMany(mappedBy="equipo", cascade=CascadeType.ALL, orphanRemoval=true)
    private ArrayList<ReservaEntity> reservas;
    
    @ManyToOne( cascade=CascadeType.ALL)
    //@PodamExclude
    private PuntoDeAtencionEntity puntoDeAtencion;

    public TipoEntity getTipo() {
        return tipo;
    }

    public void setTipo(TipoEntity tipo) {
        this.tipo = tipo;
    }

    public String getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(String caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    public ArrayList<ReservaEntity> getReservas() {
        return reservas;
    }

    public void setReservas(ArrayList<ReservaEntity> reservas) {
        this.reservas = reservas;
    }

    public PuntoDeAtencionEntity getPuntoDeAtencion() {
        return puntoDeAtencion;
    }

    public void setPuntoDeAtencion(PuntoDeAtencionEntity puntoDeAtencion) {
        this.puntoDeAtencion = puntoDeAtencion;
    }
    
    
}
