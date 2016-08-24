/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.resources;

import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.DELETE;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;

import co.edu.uniandes.rest.dtos.ProfesorDTO;
import co.edu.uniandes.rest.exceptions.CityLogicException;
import co.edu.uniandes.rest.mocks.ProfesorLogicMock;

import java.util.logging.Logger;
import javax.ws.rs.PathParam;


/**
 *
 * @author ac.fandino10
 */
    
@Path("profesores")
@Produces("application/json")
public class ProfesorResource 
{
  private final static Logger logger = Logger.getLogger(ProfesorLogicMock.class.getName());
    

    ProfesorLogicMock profesorLogic = new ProfesorLogicMock();

    /**
     * Obtiene los profesores
     * @return
     * @throws CityLogicException 
     */
    @GET
    public List<ProfesorDTO> getTeachers() throws CityLogicException {
        return profesorLogic.getProfesores();
    }
    
    /**
     * Retorna un profesor dado su id
     * @param id
     * @return
     * @throws CityLogicException 
     */
    @GET
    @Path("{id: \\d+}")
    public ProfesorDTO getTeacher(@PathParam("id") int id) throws CityLogicException
    {
        return profesorLogic.findTeacher(id);
    }

   
    /**
     * Crea el profesor
     * @param profesor
     * @return
     * @throws CityLogicException 
     */
    @POST
    public ProfesorDTO createTeacher(ProfesorDTO profesor) throws CityLogicException {
        return profesorLogic.createProfesor(profesor);
    }

    /**
     * Actualiza la info de un profe
     * @param id
     * @param nombre
     * @param contrasena
     * @return
     * @throws CityLogicException 
     */
    @PUT
    @Path("{id: \\d+}")
    public ProfesorDTO updateTeacher(@PathParam("id") Long id, ProfesorDTO profe) throws CityLogicException {
        
        logger.info("PUT");
        return profesorLogic.updateInfo(id,profe);
    }
    
    /**
     * Elimina el profesor por id
     * @param id
     * @throws CityLogicException 
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteTeacher(@PathParam("id") int id) throws CityLogicException
    {
          logger.info("proceso: intentando borrar");
            profesorLogic.deleteTeacher(id);
    }
  
    
    
}