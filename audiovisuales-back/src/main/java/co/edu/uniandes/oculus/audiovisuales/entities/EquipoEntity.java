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

/**
 *
 * @author gc.andrade10
 */
@Entity
public class EquipoEntity extends BaseEntity implements Serializable
{
    @OneToOne
    private TipoEntity tipo;
    
    
    private String caracteristicas;
    
    @OneToMany(mappedBy="equipo", cascade=CascadeType.ALL, orphanRemoval=true)
    private ArrayList<ReservaEntity> reservas;
    
    @ManyToOne
    private PuntoDeAtencionEntity puntoDeAtencion;
    
    
}
