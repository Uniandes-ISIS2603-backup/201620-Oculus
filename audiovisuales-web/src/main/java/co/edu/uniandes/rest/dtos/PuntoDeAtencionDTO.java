/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.dtos;
import java.util.ArrayList;
/**
 *
 * @author am.espinosa11
 */
public class PuntoDeAtencionDTO {
    private Long id;
    private String ubicacion;
    private ArrayList<AdministradorDTO> administradores;

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

    public ArrayList<AdministradorDTO> getAdministradores() 
    {
        return administradores;
    }

     /**
     * @param administradores lista de administradores
     */
    public void setAdministradores(ArrayList<AdministradorDTO> administradores)
    {
        this.administradores = administradores;
    }
    
     /**
     * @param admin administrador para agregar
     */
    public void agregarAdministrador(AdministradorDTO admin)
    {
        this.administradores.add(admin);
    }
    
     /**
     * elimina admin que se da por parametro
     */
    public void eliminarAdministrador(AdministradorDTO admin)
    {
        this.administradores.remove(admin);
    }
    
     /**
     * @param pLogin Login del administrador a eliminar
     */
    public void eliminarAdministradorPorLogin(String pLogin)
    {
        for(int i = 0; i < administradores.size(); i++)
        {
            if(administradores.get(i).getLogin().equals(pLogin))
            {
                administradores.remove(i);
            }
        }
    }
    
    
    
    /**
     * Convierte el objeto a una cadena
     */
    @Override
    public String toString() {
    	return "{ id : " + getId() + ", ubicacion : \"" + getUbicacion() + "\" }" ;  
    }
}