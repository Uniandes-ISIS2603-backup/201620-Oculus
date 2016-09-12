/*
 * AdministradorResource.java
 * Clase que representa el recurso "/administrador"
 * Implementa varios métodos para manipular los equipos
 */
package co.edu.uniandes.rest.resources;

import co.edu.uniandes.rest.dtos.AdministradorDTO;
import co.edu.uniandes.rest.dtos.EquipoDTO;
import co.edu.uniandes.rest.exceptions.CityLogicException;
import co.edu.uniandes.rest.exceptions.EquipoLogicException;
import co.edu.uniandes.rest.mocks.AdministradorLogicMock;
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
 * Clase que implementa el recurso REST correspondiente a "administrador".
 * 
 * @author Sneider Velandia G
 */
@Path("administrador") // api/administrador
@Produces("application/json") // todos retornan Jsons

public class AdministradorResource 
{
    private final static Logger logger = Logger.getLogger(AdministradorLogicMock.class.getName());
    AdministradorLogicMock administradorLogic = new AdministradorLogicMock();

    /**
     * Obtiene el listado de equipos asociados al punto de atención que administra.
     * @return lista de equipos
     * @throws CityLogicException excepción retornada por la lógica
     */
    @GET
    public List<EquipoDTO> getAdministradores() throws CityLogicException
    {
        return administradorLogic.getEquiposAdministrador();
    }
    @POST //metodo no importa el nombre del método la anotación es la que utiliza el servidor
    public EquipoDTO createEquipo(EquipoDTO equipo) throws EquipoLogicException 
    {
        //viene por un Json
        //Dto datos que manda el usuario
        //lo agrega a un arreglo
        logger.info("Se trata de agregar "+equipo);
        return administradorLogic.agregarEquipo(equipo);
    }    
}