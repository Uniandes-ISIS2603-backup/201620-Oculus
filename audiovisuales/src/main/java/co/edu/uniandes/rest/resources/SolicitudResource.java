/*
 * SolicitudResource.java
 * Clase que representa el recurso "/solicitudes"
 * Implementa métodos para manipular las solicitudes desde el cliente
 */
package co.edu.uniandes.rest.resources;

import co.edu.uniandes.rest.dtos.SolicitudDTO;
import co.edu.uniandes.rest.exceptions.CityLogicException;
import co.edu.uniandes.rest.mocks.SolicitudLogicMock;
import java.util.List;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 * Clase que implementa el recurso REST correspondiente a "solicitudes".
 *
 * Note que la aplicación (definida en RestConfig.java) define la ruta "/api" y
 * este recurso tiene la ruta "solicitudes". Al ejecutar la aplicación, el recurse
 * será accesibe a través de la ruta "/api/solicitudes"
 *
 * @author fa.lopez10
 */
@Path("solicitudes")
@Produces("application/json")
public class SolicitudResource {
    SolicitudLogicMock solicitudLogic = new SolicitudLogicMock();

    /**
     * Obtiene el listado de solicitudes.
     *
     * @return lista de solicitudes
     * @throws CityLogicException excepción retornada por la lógica
     */
    @GET
    public List<SolicitudDTO> getSolicitudes() throws CityLogicException {
        return solicitudLogic.getSolicitudes();
    }
    
    /**
     * obtiene una solicitud segun id
     * @param id identificador de la solicitud
     * @return la solicitud deseada
     * @throws CityLogicException 
     */
    @GET
    @Path("{id: \\d+}")
    public SolicitudDTO getSolicitud(@PathParam("id") Long id) throws CityLogicException {
        return solicitudLogic.getSolicitud(id);
    }
        
   
    /**
     * Agrega una solicitud
     *
     * @param solicitud solicitud a agregar
     * @return datos de la solicitud a agregar
     * @throws CityLogicException cuando ya existe una solicitud con el id
     * suministrado
     */
    @POST
    public SolicitudDTO createSolicitud(SolicitudDTO solicitud) throws CityLogicException {
        return solicitudLogic.createSolicitud(solicitud);
    }
    
    /**
     * Actualiza una solicitud
     * 
     * @param id id de la solicitud a modificar
     * @param newSolicitud objeto con los nuevos datos a ingresar
     * @return el objeto una vez es modificado
     * @throws CityLogicException 
     */
    @PUT
    @Path("{id: \\d+}")
    public SolicitudDTO updateSolicitud(@PathParam("id") Long id, SolicitudDTO newSolicitud) throws CityLogicException {
        return solicitudLogic.updateSolicitud(id, newSolicitud);
    }
    
    /**
     * Elimina la solicitud
     * 
     * @param id identificador de la solicitud a eliminar
     * @throws CityLogicException si no se encuentra con ese id
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteSolicitud(@PathParam("id") Long id) throws CityLogicException {
        solicitudLogic.deleteSolicitud(id);
    }
}
