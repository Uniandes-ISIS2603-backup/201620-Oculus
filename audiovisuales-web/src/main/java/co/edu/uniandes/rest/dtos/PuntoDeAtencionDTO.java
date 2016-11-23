/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.dtos;
import co.edu.uniandes.oculus.audiovisuales.entities.PuntoDeAtencionEntity;
import java.util.ArrayList;
import javax.xml.bind.annotation.XmlRootElement;
/**
 *
 * @author am.espinosa11
 */
@XmlRootElement
public class PuntoDeAtencionDTO {
    private Long id;
    private String name;
  

    /**
     * Constructor por defecto
     */
    public PuntoDeAtencionDTO() 
{
	}

    /**
     * Crea un objeto PuntoDeAtencionDTO a partir de un objeto PuntoDeAtencionEntity.
     *
     * @param entity Entidad CompanyEntity desde la cual se va a crear el nuevo
     * objeto.
     * 
     */
    public PuntoDeAtencionDTO(PuntoDeAtencionEntity entity) 
    {
	if(entity != null)
        {
            this.id = entity.getId();
            this.name = entity.getName();
        }
    }
    
     /**
     * Convierte un objeto PuntoDeAtencionDTO a PuntoDeAtencionEntity.
     *
     * @return Nueva objeto PuntoDeAtencionEntity.
     * 
     */
    public PuntoDeAtencionEntity toEntity()
    {
        PuntoDeAtencionEntity newPunto = new PuntoDeAtencionEntity();
        newPunto.setId(id);
        newPunto.setName(name);
        return newPunto;
    }
    
    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the ubicación
     */
    public String getName() {
        return name;
    }

    /**
     * @param ubic the ubicacion to set
     */
    public void setName(String ubic) {
        this.name = ubic;
    }

    
    /**
     * Convierte el objeto a una cadena
     */
    @Override
    public String toString() {
    	return "{ id : " + getId() + ", ubicacion : \"" + getName() + "\" }" ;  
    }
}