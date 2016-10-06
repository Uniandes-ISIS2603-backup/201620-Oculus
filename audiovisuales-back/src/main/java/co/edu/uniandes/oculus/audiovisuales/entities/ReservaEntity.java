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
    
    private Date fecha;
    private String estado;
    @ManyToOne
    EquipoEntity equipo;
    
    @ManyToOne
    ProfesorEntity profesor;
    
}
