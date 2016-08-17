/*
 * AdministradorResource.java
 * Clase que representa el recurso "/administrador"
 * Implementa varios métodos para manipular los equipos
 */
package co.edu.uniandes.rest.resources;

import co.edu.uniandes.rest.dtos.AdministradorDTO;
import co.edu.uniandes.rest.exceptions.CityLogicException;
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
     * Obtiene el listado de administradores asociados.
     * @return lista de administradores
     * @throws CityLogicException excepción retornada por la lógica
     */
    @GET
    public List<AdministradorDTO> getAdministradores() throws CityLogicException
    {
        return administradorLogic.getAdministradores();
    }
    
   /**
     *Obtiene los atributos de un administrador dado su codigo
     * @return adminitrador buscado
     * @throws CityLogicException excepcion retornada por la logica
     */
    @GET
    @Path("{codigo: \\d+}")
    public AdministradorDTO getAdministrador(@PathParam("codigo") int cod) throws CityLogicException
    {
        return administradorLogic.getAdministradorDTO(cod);
    }
    
   /**
     *Actualiza una instancia de la entidad Administrador
     * @return Atributos de la instancia de administrador
     * @throws CityLogicException excepcion retornada por la logica
     */
    @PUT
    @Path("{codigo: \\d+}")
    public AdministradorDTO updateAdministrador(@PathParam("codigo") int cod , AdministradorDTO administradorUp) throws CityLogicException
    {
        logger.info("PUT");
        return administradorLogic.updateAdministrador(cod, administradorUp);
    }
 
    /**
     * Agrega un administrador
     * @param administrador administrador a agregar
     * @return datos del administrador agregado
     * @throws CityLogicException cuando ya existe un equipo con el id
     * suministrado
     */
    @POST
    public AdministradorDTO creatAdministrador(AdministradorDTO administrador) throws CityLogicException
    {
         logger.info("Se trata de agregar "+administrador);
        return administradorLogic.createAdministrador(administrador);
    }
    
   /**
     * elimina el administrador con el codigo dado
     * @param cod codigo del administrador a eliminar
     * @return Atributos de la instancia de administrador
     * @throws CityLogicException excepcion retornada por la logica
     */
    @DELETE
    @Path("{codigo: \\d+}")
    public void deleteAdminitrador(@PathParam("codigo") int cod) throws CityLogicException
    {
          logger.info("Trata de borrar");
            administradorLogic.deleteAdministrador(cod);
    }
}