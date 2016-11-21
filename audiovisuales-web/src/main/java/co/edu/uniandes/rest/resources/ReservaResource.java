/*
 * ReservaResource.java
 * Clase que representa el recurso "/reservas"
 * Implementa métodos para manipular las reservas desde el cliente
 */
package co.edu.uniandes.rest.resources;

import co.edu.uniandes.oculus.audiovisuales.api.IReservaLogic;
import co.edu.uniandes.oculus.audiovisuales.entities.ReservaEntity;
import co.edu.uniandes.oculus.audiovisuales.exceptions.BusinessLogicException;
import co.edu.uniandes.rest.dtos.ReservaDTO;
import co.edu.uniandes.rest.dtos.ReservaDetailDTO;
import co.edu.uniandes.rest.exceptions.CityLogicException;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
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
    
    @Inject
    private IReservaLogic reservaLogic;

    /**
     * Convierte lista de entidades de reserva a una lista de dtos de reserva
     * @param entityList lista de entidades de reserva
     * @return lista de dtos correspondientes
     */
    private List<ReservaDetailDTO> listEntity2DTOReserva(List<ReservaEntity> entityList) {
        List<ReservaDetailDTO> list = new ArrayList<>();
        for (ReservaEntity entity : entityList) {
            list.add(new ReservaDetailDTO(entity));
        }
        return list;
    }
    
    @GET
    public List<ReservaDetailDTO> getReservas() throws BusinessLogicException {
        return listEntity2DTOReserva(reservaLogic.getReservas());
    }
    
    /**
     * Elimina la reserva
     * 
     * @param id identificador de la reserva a eliminar
     * @throws CityLogicException si no se encuentra con ese id
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteReserva(@PathParam("id") Long id) throws BusinessLogicException {
        reservaLogic.deleteReserva(id);
    }
}
