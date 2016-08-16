/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.resources;

import co.edu.uniandes.rest.dtos.LocacionDTO;
import co.edu.uniandes.rest.exceptions.CityLogicException;
import co.edu.uniandes.rest.mocks.LocacionLogicMock;
import java.util.ArrayList;
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
@Path("locaciones") // api/locaciones
@Produces("application/json") // todos retornan Jsons

public class LocacionResource 
{
     private final static Logger logger = Logger.getLogger(LocacionLogicMock.class.getName());

    LocacionLogicMock locacionLogic = new LocacionLogicMock();
    
    /**
     * Obtiene el listado de locaciones.
     * @return lista de locaciones
     * @throws CityLogicException excepción retornada por la lógica
     */
    @GET 
    public List<LocacionDTO> getLocaciones() throws CityLogicException 
    {
        return locacionLogic.getLocaciones();
    }

    /**
     *Obtiene los atributos de una instancia de Locacion
     * @return Atributos de la instancia de Locacion
     * @throws CityLogicException excepciÃ³n retornada por la lÃ³gica
     */
    @GET
    
    @Path("{ubicacion: \\d+}")
    public LocacionDTO getLocacion(@PathParam("ubicacion") String pUbic) throws CityLogicException 
    {
            return locacionLogic.getLocacion(pUbic);
    }

   
    /**
     * Agrega una locacion
     *
     * @param pLocacion locacion a agregar
     * @return datos de la locacion a agregar
     * @throws CityLogicException cuando ya existe una locacion con esa ubicacion
     * suministrado
     */
    @POST //metodo no importa el nombre del método la anotación es la que utiliza el servidor
    public LocacionDTO createLocacion(LocacionDTO pLocacion) throws CityLogicException 
    {
        logger.info("Se trata de agregar "+ pLocacion);
        return locacionLogic.createLocacion(pLocacion);
    }

     /**
     *Actualiza una instancia de la entidad Locacion (UPDATE)
     * @return Atributos de la instancia de Locacion
     * @throws CityLogicException excepciÃ³n retornada por la lÃ³gica
     */
    @PUT
    @Path("{ubicacion: \\d+}")
    public LocacionDTO updateCity(@PathParam("ubicacion") String pUbic, LocacionDTO pLoc) throws CityLogicException 
    {
            logger.info("PUT");
            return locacionLogic.updateLocacion(pUbic,pLoc);
    }
    
   /**
     *Borra la locacion identificada con la ubicacion
     * @param pUbic ubicacion de la locacion a eliminar
     * @return Atributos de la instancia de Locacion
     * @throws CityLogicException excepciÃ³n retornada por la lÃ³gica
     */
    @DELETE
    @Path("{ubicacion: \\d+}")
    public void deleteEquipo(@PathParam("ubicacion") String pUbic) throws CityLogicException 
    {
            logger.info("Trata de borrar");
            locacionLogic.deleteLocacion(pUbic);
    }
    
}
