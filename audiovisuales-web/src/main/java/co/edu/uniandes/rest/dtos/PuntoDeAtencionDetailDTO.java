/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.dtos;

import co.edu.uniandes.oculus.audiovisuales.entities.AdministradorEntity;
import co.edu.uniandes.oculus.audiovisuales.entities.EquipoEntity;
import co.edu.uniandes.oculus.audiovisuales.entities.PuntoDeAtencionEntity;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author am.espinosa11
 */
@XmlRootElement
public class PuntoDeAtencionDetailDTO extends PuntoDeAtencionDTO
{
    private List<AdministradorDTO> administradores = new ArrayList<>();
    private List<EquipoDTO> equipos = new ArrayList<>();
    
    public PuntoDeAtencionDetailDTO()
    {
        super();
    }
    
    public PuntoDeAtencionDetailDTO(PuntoDeAtencionEntity entity)
    {
        super(entity);
        List<AdministradorEntity> adminList = entity.getAdministradores();
        for (AdministradorEntity administradorEntity : adminList) 
        {
             this.administradores.add(new AdministradorDTO(administradorEntity));
        }
        List<EquipoEntity> equipoList = entity.getEquipos();
        for (EquipoEntity equipoEntity : equipoList) 
        {
            this.equipos.add(new EquipoDTO(equipoEntity));
        }
    }
    
    @Override
    public PuntoDeAtencionEntity toEntity()
    {
        PuntoDeAtencionEntity entity = super.toEntity();
        List<AdministradorDTO> admins = this.getAdministradores();
        for (AdministradorDTO admin : admins) 
        {
          entity.getAdministradores().add(admin.AdminDTOtoEntity());
        }
        
        List<EquipoDTO> equips = this.getEquipos();
        for (EquipoDTO equip : equips) 
        {
          entity.getEquipos().add(equip.toEntity());
        }
        
        return entity;
    }

    public List<AdministradorDTO> getAdministradores() 
    {
        return administradores;
    }

    public List<EquipoDTO> getEquipos() 
    {
        return equipos;
    }

    public void setAdministradores(List<AdministradorDTO> administradores) 
    {
        this.administradores = administradores;
    }

    public void setEquipos(List<EquipoDTO> equipos)
    {
        this.equipos = equipos;
    }
    
}
