/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.resources;

import co.edu.uniandes.rest.dtos.PuntoDeAtencionDTO;
import co.edu.uniandes.rest.exceptions.CityLogicException;
import co.edu.uniandes.rest.mocks.PuntoDeAtencionLogicMock;

import java.util.List;
import java.util.logging.Logger;
import javax.ws.rs.DELETE;


import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
/**
 *
 * @author am.espinosa11
 */
/**
 * Clase que implementa el recurso REST correspondiente a "puntosDeAtencion".
 * 
 * Al ejecutar la aplicación, el recurse será accesibe a través de la 
 * ruta "/api/puntosDeAtencion" 
 * 
 * 
 */
@Path("puntosDeAtencion")
@Produces("application/json")
public class PuntoDeAtencionResource {

	
	PuntoDeAtencionLogicMock puntoLogic = new PuntoDeAtencionLogicMock();

	/**
	 * Obtiene el listado de los puntos de atencion. 
	 * @return lista de puntos de atencion
	 * @throws CityLogicException excepción retornada por la lógica  
	 */
    @GET
    public List<PuntoDeAtencionDTO> getPuntosDeAtencion() throws CityLogicException {
        return puntoLogic.getPuntosDeAtencion();
    }

    /**
     * Obtiene un punto de atencion
     * @param id identificador del punto de atencion
     * @return punto encontrado
     * @throws CityLogicException cuando el editorial no existe
     */
    @GET
    @Path("{id: \\d+}")
    public PuntoDeAtencionDTO getPuntoDeAtencion(@PathParam("id") Long id) throws CityLogicException {
        return puntoLogic.getPuntoDeAtencion(id);
    }

    /**
     * Agrega un punto de atencion
     * @param punto punto de atencion a agregar
     * @return datos del punto de atencion a agregar
     * @throws CityLogicException cuando ya existe un punto de atencion con el id suministrado
     */
    @POST
    public PuntoDeAtencionDTO createPuntoDeAtencion(PuntoDeAtencionDTO punto) throws CityLogicException {
        return puntoLogic.createPuntoDeAtencion(punto);
    }

    /**
     * Actualiza los datos de un punto de atencion
     * @param id identificador de el punto de atencion a modificar
     * @param punto punto de atencion a modificar
     * @return datos del punto de atencion modificada 
     * @throws CityLogicException cuando no existe un punto de atencion con el id suministrado
     */
    @PUT
    @Path("{id: \\d+}")
    public PuntoDeAtencionDTO updatePuntoDeAtencion(@PathParam("id") Long id, PuntoDeAtencionDTO punto) throws CityLogicException {
        return puntoLogic.updatePuntoDeAtencion(id, punto);
    }

    /**
     * Elimina los datos de un punto de atencion
     * @param id identificador del punto de atencion a eliminar
     * @throws CityLogicException cuando no existe un punto de atencion con el id suministrado
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deletePuntoDeAtencion(@PathParam("id") Long id) throws CityLogicException {
    	puntoLogic.deletePuntoDeAtencion(id);
    }

}
