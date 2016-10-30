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
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author am.espinosa11
 */
@Entity
public class PuntoDeAtencionEntity extends BaseEntity implements Serializable
{
    private String ubicacion;
    @PodamExclude
    @OneToMany(mappedBy = "puntoDeAtencion")
    private ArrayList<AdministradorEntity> administradores  ; 
    
    @PodamExclude
    @OneToMany(mappedBy = "puntoDeAtencion", cascade = CascadeType.ALL, orphanRemoval = true)
    private ArrayList<EquipoEntity> equipos ;

    public void setAdministradores(ArrayList<AdministradorEntity> administradores) 
    {
        this.administradores = administradores;
    }

    public void setEquipos(ArrayList<EquipoEntity> equipos)
    {
        this.equipos = equipos;
    }

    public ArrayList<AdministradorEntity> getAdministradores() 
    {
        return administradores;
    }

    public ArrayList<EquipoEntity> getEquipos() 
    {
        return equipos;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getUbicacion() {
        return ubicacion;
    }
    
    
     
}
