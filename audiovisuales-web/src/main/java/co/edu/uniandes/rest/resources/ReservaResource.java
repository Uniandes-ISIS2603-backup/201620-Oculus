/*
 * ReservaResource.java
 * Clase que representa el recurso "/reservas"
 * Implementa métodos para manipular las reservas desde el cliente
 */
package co.edu.uniandes.rest.resources;

import co.edu.uniandes.rest.dtos.ReservaDTO;
import co.edu.uniandes.rest.exceptions.CityLogicException;
import java.util.List;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 * Clase que implementa el recurso REST correspondiente a "reservas".
 *
 * Note que la aplicación (definida en RestConfig.java) define la ruta "/api" y
 * este recurso tiene la ruta "reservas". Al ejecutar la aplicación, el recurse
 * será accesibe a través de la ruta "/api/reservas"
 *
 * @author fa.lopez10
 */
@Path("reservas")
@Produces("application/json")
public class ReservaResource {
    
    ReservaLogicMock reservaLogic = new ReservaLogicMock();

    /**
     * Obtiene el listado de reservas.
     *
     * @return lista de reservas
     * @throws CityLogicException excepción retornada por la lógica
     */
    @GET
    public List<ReservaDTO> getReservas() throws CityLogicException {
        return reservaLogic.getReservas();
    }
    
    /**
     * obtiene una reserva segun id
     * @param id identificador de la reserva
     * @return la reserva deseada
     * @throws CityLogicException 
     */
    @GET
    @Path("{id: \\d+}")
    public ReservaDTO getReserva(@PathParam("id") Long id) throws CityLogicException {
        return reservaLogic.getReserva(id);
    }
        
   
    /**
     * Agrega una reserva
     *
     * @param reserva reserva a agregar
     * @return datos de la reserva a agregar
     * @throws CityLogicException cuando ya existe una reserva con el id
     * suministrado
     */
    @POST
    public ReservaDTO createReserva(ReservaDTO reserva) throws CityLogicException {
        return reservaLogic.createReserva(reserva);
    }
    
    /**
     * Actualiza una reserva
     * 
     * @param id id de la reserva a modificar
     * @param newReserva objeto con los nuevos datos a ingresar
     * @return el objeto una vez es modificado
     * @throws CityLogicException 
     */
    @PUT
    @Path("{id: \\d+}")
    public ReservaDTO updateReserva(@PathParam("id") Long id, ReservaDTO newReserva) throws CityLogicException {
        return reservaLogic.updateReserva(id, newReserva);
    }
    
    /**
     * Elimina la reserva
     * 
     * @param id identificador de la reserva a eliminar
     * @throws CityLogicException si no se encuentra con ese id
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteReserva(@PathParam("id") Long id) throws CityLogicException {
        reservaLogic.deleteReserva(id);
    }
}
