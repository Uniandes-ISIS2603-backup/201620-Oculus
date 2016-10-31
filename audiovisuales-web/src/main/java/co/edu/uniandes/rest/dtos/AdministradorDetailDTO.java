/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.dtos;

import co.edu.uniandes.oculus.audiovisuales.entities.AdministradorEntity;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * clase usada para la transferencia de datos con el browser
 * @author Sneider Velandia G
 */

@XmlRootElement
public class AdministradorDetailDTO extends AdministradorDTO
{
    //relacion de 1 a 1 con Puntos de Atencion
    private PuntoDeAtencionDTO puntoAtencion;
    
    public AdministradorDetailDTO()
    {
        super();
    }
    
    //  contruye desde un entity ANA DEBE RECIBIR UN ENTITY PUNTOA
    public AdministradorDetailDTO(AdministradorEntity adminEntity)
    {
        super(adminEntity);
        if(adminEntity != null)
            puntoAtencion = new PuntoDeAtencionDTO(adminEntity.getPuntoDeAtencion());
    }
    
    /**
     * Convierte un objeto AdministradorDetailDTO a AdministradorEntity 
     * incluyendo los atributos de CompanyDTO.
     * @return objeto AdministradorEntity.
     */
    @Override
    public AdministradorEntity AdminDTOtoEntity() 
    {
        AdministradorEntity adminEntity = super.AdminDTOtoEntity();
         PuntoDeAtencionDTO puntoDeAtencion = this.getPuntoAtencion();
        return adminEntity;
    }
    
    /**
     * metodo que retorna el punto de atencion
     * @return el puntoAtencion
     */
    public PuntoDeAtencionDTO getPuntoAtencion() 
    {
        return puntoAtencion;
    }

    /**
     * metodo que agrega un punto de atencion
     * @param puntoAtencion el puntoAtencion a agregar
     */
    public void setPuntoAtencion(PuntoDeAtencionDTO puntoAtencion) 
    {
        this.puntoAtencion = puntoAtencion;
    }
}
