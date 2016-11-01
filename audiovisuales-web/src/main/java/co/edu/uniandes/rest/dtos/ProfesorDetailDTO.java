/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.dtos;

import co.edu.uniandes.oculus.audiovisuales.entities.ProfesorEntity;
import co.edu.uniandes.oculus.audiovisuales.entities.ReservaEntity;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author fa.lopez10
 */
@XmlRootElement
public class ProfesorDetailDTO extends ProfesorDTO 
{
    private List<ReservaDTO> reservas = new ArrayList<>();
    
    public ProfesorDetailDTO()
    {
        super();
    }
    
    public ProfesorDetailDTO(ProfesorEntity entity)
    {
        super(entity);
        List<ReservaEntity> reserList = entity.getReservas();
        for(ReservaEntity reservaEntity : reserList)
        {
            this.reservas.add(new ReservaDTO(reservaEntity));
        }  
    }
    
    public ProfesorEntity toEntity()
    {
        ProfesorEntity entity = super.toEntity();
        List<ReservaDTO> resers = this.getReservas();
        for(ReservaDTO reser : resers)
        {
            entity.getReservas().add(reser.toEntity());
        }
        return entity;
    }
    
    public List<ReservaDTO> getReservas()
    {
        return reservas;
    }
    
    public void setReservas(List<ReservaDTO> reservas)
    {
        this.reservas = reservas;
    }
}
