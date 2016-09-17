/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.dtos;

/**
 *
 * @author am.espinosa11
 */
public class PuntoDeAtencionDTO {
    private Long id;
    private String ubicacion;

    /**
     * Constructor por defecto
     */
    public PuntoDeAtencionDTO() 
{
	}

    /**
     * Constructor con parámetros.
     * @param id identificador del punto de atención
     * @param ubicacion nombre del punto de atención
     */
    public PuntoDeAtencionDTO(Long id, String ubic) {
		super();
		this.id = id;
		this.ubicacion = ubic;
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
    public String getUbicacion() {
        return ubicacion;
    }

    /**
     * @param ubic the ubicacion to set
     */
    public void setUbicacion(String ubic) {
        this.ubicacion = ubic;
    }
    
    /**
     * Convierte el objeto a una cadena
     */
    @Override
    public String toString() {
    	return "{ id : " + getId() + ", ubicacion : \"" + getUbicacion() + "\" }" ;  
    }
}