/*
 * AdministradorResource.java
 * Clase que representa el recurso "/administrador"
 * Implementa varios métodos para manipular los equipos
 */
package co.edu.uniandes.rest.resources;

import co.edu.uniandes.oculus.audiovisuales.api.IAdministradorLogic;
import co.edu.uniandes.oculus.audiovisuales.entities.AdministradorEntity;
import co.edu.uniandes.oculus.audiovisuales.exceptions.BusinessLogicException;

import co.edu.uniandes.rest.dtos.AdministradorDTO;
import co.edu.uniandes.rest.dtos.AdministradorDetailDTO;
import co.edu.uniandes.rest.dtos.EquipoDTO;
import co.edu.uniandes.rest.exceptions.CityLogicException;
import co.edu.uniandes.rest.exceptions.EquipoLogicException;
import co.edu.uniandes.rest.mocks.AdministradorLogicMock;
import co.edu.uniandes.rest.mocks.EquipoLogicMock;
import co.edu.uniandes.rest.mocks.ReservaLogicMock;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Clase que implementa el recurso REST correspondiente a "administrador".
 * 
 * @author Sneider Velandia G
 */
@Path("administradores") // api/administradores
@Produces("application/json") // todos retornan Jsons
@Consumes(MediaType.APPLICATION_JSON)

public class AdministradorResource 
{
    private final static Logger logger = Logger.getLogger(AdministradorLogicMock.class.getName());
    
     //AdministradorLogicMock administradorLogic = new AdministradorLogicMock();
     //EquipoLogicMock equipoLogicMock = new EquipoLogicMock();
     //ReservaLogicMock reservaLogicMock = new ReservaLogicMock();
    
        //inyectamos la interfaz de la logica de admnistrador para omitir los mocks !!!
    @Inject
    private IAdministradorLogic administradorLogic;
    
    
    private List<AdministradorDetailDTO> listAdminEntityToDTO(List<AdministradorEntity> adminEntityList) {
        List<AdministradorDetailDTO> adminList = new ArrayList<>();
        for (AdministradorEntity adminEntity : adminEntityList) {
            adminList.add(new AdministradorDetailDTO(adminEntity));
        }
        return adminList;
    }
    
    /**
     *  NUEVOS PARA CICLO 2 
     */
    
    /**
     *Obtiene los equipos de un administrador dado su id
     * @return lista de equipos
     * @throws EquipoLogicException excepcion retornada por la logica
     */
        @GET
        @Path("{idAdministrador: \\d+}/equipos")
        public List<EquipoDTO> getEquipos(@PathParam("idAdministrador") Long id , @PathParam("idAdministrador") Long ide) throws EquipoLogicException
        {
            return equipoLogicMock.getEquipos();
        }
    
    /**
     *Obtiene los atributos de un equipo de un Administrador, dados el idEquipo y el idAdministrador
     * @return equipo buscado
     * @throws EquipoLogicException excepcion retornada por la logica
     */
    @GET
    @Path("{idAdministrador: \\d+}/equipos/{idEquipo: \\d+}")
    public EquipoDTO getEquipo(@PathParam("idAdministrador") Long idAdmin , @PathParam("idEquipo") Long idEquipo) throws EquipoLogicException
    {
        return equipoLogicMock.getEquipo(idEquipo);
    }
    
    /**
     * Agrega un equipo
     *
     * @param equipo equipos a agregar
     * @return datos del  equipo a agregar
     * @throws EquipoLogicException cuando ya existe un equipo con el id
     * suministrado
     */
    
    @POST
    @Path("{idAdministrador: \\d+}/equipos")
    public EquipoDTO createEquipo(@PathParam("idAdministrador") Long idAdmin, EquipoDTO equipo) throws EquipoLogicException 
    {
        //viene por un Json
        //Dto datos que manda el usuario
        //lo agrega a un arreglo
        logger.info("Se trata de agregar "+equipo+" a "+idAdmin);
        return equipoLogicMock.createEquipo(equipo);
    }
    
    @PUT
    @Path("{idAdministrador: \\d+}/equipos/{idEquipo: \\d+}")
    public EquipoDTO updateEquipo(@PathParam("idEquipo") Long idEquipo, EquipoDTO equipo) throws EquipoLogicException 
    {
            logger.info("Trata de hacer put");
            return equipoLogicMock.updateEquipo(idEquipo,equipo);
    }
    
         /**
     *Borra el equipo identificado con id
     * @param id id de equipo a eliminar
     * @return Atributos de la instancia de Equipo
     * @throws EquipoLogicException excepciÃ³n retornada por la lógica
     */
    @DELETE
    @Path("{idAdministrador: \\d+}/equipos/{idEquipo: \\d+}")
    public void deleteEquipo(@PathParam("idEquipo") Long idEquipo) throws EquipoLogicException 
    {
            logger.info("Trata de borrar");
            equipoLogicMock.deleteEquipo(idEquipo);
    }
    @PUT
    @Path("{idAdministrador: \\d+}/equipos/{idEquipo: \\d+}/devuelto")
    public EquipoDTO devolverEquipo(@PathParam("idEquipo") Long idEquipo, EquipoDTO equipo) throws EquipoLogicException, CityLogicException 
    {
            logger.info("Trata de devolver equipo");
            reservaLogicMock.devolver(idEquipo);
            return null;
    }
    
    
    /***************************\
    ///// PRIMEROS RECURSOS \\\\\
     
     /**
     * Obtiene el listado de administradores asociados.
     * @return lista de administradores
     * @throws CityLogicException excepción retornada por la lógica
     */
    @GET
    public List<AdministradorDetailDTO> getAdministradores() 
    {
        return listAdminEntityToDTO(administradorLogic.getAdministradores());
    }
    
   /**
     *Obtiene los atributos de un administrador dado su id
     * @return adminitrador buscado
     * @throws CityLogicException excepcion retornada por la logica
     */
    @GET
    @Path("{id: \\d+}")
    public AdministradorDetailDTO getAdministrador(@PathParam("id") Long id)
    {
        return new AdministradorDetailDTO(administradorLogic.getAdministrador(id));
    }
    
   /**
     *Actualiza una instancia de la entidad Administrador
     * @return Atributos de la instancia de administrador
     * @throws CityLogicException excepcion retornada por la logica
     */
    @PUT
    @Path("{id: \\d+}")
    public AdministradorDetailDTO updateAdministrador(@PathParam("id") Long id , AdministradorDTO administradorUp) 
    {
        logger.info("trata de hacer PUT");
        AdministradorEntity adminEntity = administradorUp.AdminDTOtoEntity();
        adminEntity.setId(id);
        return new AdministradorDetailDTO(administradorLogic.updateAdministrador(adminEntity));
    }
 
    /**
     * Agrega un administrador
     * @param administrador administrador a agregar
     * @return datos del administrador agregado
     * @throws CityLogicException cuando ya existe un equipo con el id
     * suministrado
     */
    @POST
    public AdministradorDTO creatAdministrador(AdministradorDetailDTO administrador) throws BusinessLogicException
    {
         logger.info("Se trata de agregar "+administrador);
         return new AdministradorDetailDTO(administradorLogic.createAdministrador(administrador.AdminDTOtoEntity()));
    }
    
   /**
     * elimina el administrador con el id dado
     * @param id del administrador a eliminar
     * @return Atributos de la instancia de administrador
     * @throws CityLogicException excepcion retornada por la logica
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteAdminitrador(@PathParam("id") Long id) throws BusinessLogicException
    {
          logger.info("Trata de borrar");
            administradorLogic.deleteAdministrador(id);
    }
}