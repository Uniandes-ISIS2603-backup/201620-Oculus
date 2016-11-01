/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.dtos;

import co.edu.uniandes.oculus.audiovisuales.entities.ReservaEntity;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author fa.lopez10
 */
@XmlRootElement
public class ReservaDetailDTO extends ReservaDTO{
    
    private EquipoDTO equipo;
    private ProfesorDTO profesor;
    
    public ReservaDetailDTO()
    {
        super();
    }
    
    public ReservaDetailDTO(ReservaEntity entity)
    {
        super(entity);
        if(entity.getEquipo()!=null)
               this.equipo = new EquipoDTO(entity.getEquipo());
        if(entity.getProfesor()!=null)
               this.profesor = new ProfesorDTO(entity.getProfesor());
        
    }
    
    @Override
    public ReservaEntity toEntity()
    {
        ReservaEntity reserva = super.toEntity();
        if(this.getEquipoDTO()!=null)
            reserva.setEquipo(this.getEquipoDTO().toEntity());
        if(this.getProfesorDTO()!=null)
            reserva.setProfesor(this.getProfesorDTO().toEntity());
        return reserva;
    }
    
    public EquipoDTO getEquipoDTO()
    {
        return equipo;
    }
    
    public void setEquipoDTO(EquipoDTO equipo)
    {
        this.equipo = equipo;
    }
    
    public ProfesorDTO getProfesorDTO()
    {
        return profesor;
    }
    
    public void setProfesorDTO(ProfesorDTO profesor)
    {
        this.profesor = profesor;
    }
}
