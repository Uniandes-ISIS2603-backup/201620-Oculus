/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.oculus.audiovisuales.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
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
    @OneToMany(mappedBy = "puntoDeAtencion", cascade = CascadeType.ALL)
    private ArrayList<AdministradorEntity> administradores  ; 
    
    //@PodamExclude
    @OneToMany(mappedBy = "puntoDeAtencion", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EquipoEntity> equipos ;

    public void setAdministradores(ArrayList<AdministradorEntity> administradores) 
    {
        this.administradores = administradores;
    }

    public void setEquipos(List<EquipoEntity> equipos)
    {
        this.equipos = equipos;
    }

    public List<AdministradorEntity> getAdministradores() 
    {
        return administradores;
    }

    public List<EquipoEntity> getEquipos() 
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
