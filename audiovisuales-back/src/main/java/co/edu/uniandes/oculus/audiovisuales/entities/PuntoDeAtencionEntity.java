/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.oculus.audiovisuales.entities;

import java.io.Serializable;
import java.util.ArrayList;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;

/**
 *
 * @author am.espinosa11
 */
@Entity
public class PuntoDeAtencionEntity extends BaseEntity implements Serializable
{
    @OneToMany(mappedBy = "puntoDeAtencion")
    private ArrayList<AdministradorEntity> administradores = new ArrayList<>() ; 
    
    @OneToMany(mappedBy = "puntoDeAtencion", cascade = CascadeType.ALL, orphanRemoval = true)
    private ArrayList<EquipoEntity> equipos = new ArrayList<>();
    
    
}
