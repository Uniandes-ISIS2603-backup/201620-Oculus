/*
 * EquipoResource.java
 * Clase que representa el recurso "/equipos"
 * Implementa varios métodos para manipular los equipos
 */
package co.edu.uniandes.rest.resources;

import co.edu.uniandes.rest.dtos.EquipoDTO;
import co.edu.uniandes.rest.dtos.TipoDTO;
import co.edu.uniandes.rest.exceptions.EquipoLogicException;
import co.edu.uniandes.rest.mocks.EquipoLogicMock;

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
 * Clase que implementa el recurso REST correspondiente a "equipos".
 *
 * Note que la aplicación (definida en RestConfig.java) define la ruta "/api" y
 * este recurso tiene la ruta "equipos". Al ejecutar la aplicación, el recurse
 * será accesibe a través de la ruta "/api/equipos"
 *
 * @author German Andrade
 */
@Path("equipos") // api/equipos el antepone / solo
@Produces("application/json") // todos retornan Jsons
public class EquipoResource 
{

    private final static Logger logger = Logger.getLogger(EquipoLogicMock.class.getName());

    EquipoLogicMock equipoLogic = new EquipoLogicMock();

    /**
     * Obtiene el listado de equipos.
     *
     * @return lista de equipos
     * @throws EquipoLogicException excepción retornada por la lógica
     */
    @GET // metodo
    public List<EquipoDTO> getEquipos() throws EquipoLogicException 
    {
        return equipoLogic.getEquipos();
    }

    /**
     *Obtiene los atributos de una instancia de Equipo (READ) que tiene identificado id
     * @return Atributos de la instancia de Equipo
     * @throws EquipoLogicException excepción retornada por la lógica
     */
    @GET
    @Path("{id: \\d+}")
    public EquipoDTO getEquipo(@PathParam("id") Long id) throws EquipoLogicException 
    {
            return equipoLogic.getEquipo(id);
    }
    
    /**
     *Obtiene los atributos de una instancia de Equipo (READ) que tiene identificado id
     * @return Atributos de la instancia de Equipo
     * @throws EquipoLogicException excepción retornada por la lógica
     */
    @GET
    @Path("tipos")
    public TipoDTO[] getTipos() throws EquipoLogicException 
    {
            return equipoLogic.getTipos();
    }

   
    /**
     * Agrega un equipo
     *
     * @param equipo ciudad a agregar
     * @return datos del  equipo a agregar
     * @throws EquipoLogicException cuando ya existe un equipo con el id
     * suministrado
     */
    @POST //metodo no importa el nombre del método la anotación es la que utiliza el servidor
    public EquipoDTO createEquipo(EquipoDTO equipo) throws EquipoLogicException 
    {
        //viene por un Json
        //Dto datos que manda el usuario
        //lo agrega a un arreglo
        logger.info("Se trata de agregar "+equipo);
        return equipoLogic.createEquipo(equipo);
    }

     /**
     *Actualiza una instancia de la entidad Equipo (UPDATE)
     * @return Atributos de la instancia de Equipo
     * @throws EquipoLogicException excepciónn retornada por la lógica
     */
    @PUT
    @Path("{id: \\d+}")
    public EquipoDTO updateCity(@PathParam("id") Long id, EquipoDTO equipo) throws EquipoLogicException 
    {
            logger.info("Trata de hacer put");
            return equipoLogic.updateEquipo(id,equipo);
    }
    
         /**
     *Borra el equipo identificado con id
     * @param id id de equipo a eliminar
     * @return Atributos de la instancia de Equipo
     * @throws EquipoLogicException excepciÃ³n retornada por la lógica
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteEquipo(@PathParam("id") Long id) throws EquipoLogicException 
    {
            logger.info("Trata de borrar");
            equipoLogic.deleteEquipo(id);
    }

  
}
