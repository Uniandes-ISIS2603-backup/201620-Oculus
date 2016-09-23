/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package co.edu.uniandes.rest.resources;

import co.edu.uniandes.rest.dtos.EquipoDTO;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.DELETE;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;

import co.edu.uniandes.rest.dtos.ProfesorDTO;
import co.edu.uniandes.rest.dtos.ReservaDTO;
import co.edu.uniandes.rest.exceptions.CityLogicException;
import co.edu.uniandes.rest.exceptions.EquipoLogicException;
import co.edu.uniandes.rest.mocks.ProfesorLogicMock;
import co.edu.uniandes.rest.mocks.ReservaLogicMock;

import java.util.logging.Logger;
import javax.ws.rs.PathParam;


/**
 *
 * @author ac.fandino10
 * @author fa.lopez10
 */

@Path("profesores")
@Produces("application/json")
public class ProfesorResource
{
    private final static Logger logger = Logger.getLogger(ProfesorLogicMock.class.getName());
    
    
    ProfesorLogicMock profesorLogic = new ProfesorLogicMock();
    ReservaLogicMock reservaLogic = new ReservaLogicMock();
    
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
    public ProfesorDTO updateTeacher(@PathParam("id") int id, ProfesorDTO profe) throws CityLogicException {
        
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
    
    /**
     * R1
     * @param idP id del profesor que tendra la reserva
     * @param reserva reserva a agregar
     * @return datos de la reserva a agregar
     * @throws CityLogicException cuando ya existe una reserva con el id
     * suministrado
     */
    @POST
    @Path("{idP: \\d+}/reservas")
    public ReservaDTO createTeacher(@PathParam("idP") Long idP, ReservaDTO reserva) throws CityLogicException {
        //viene por un Json
        //Dto datos que manda el usuario
        //lo agrega a un arreglo dentro de profesores
        //debe llamar al logic de profesores y ahi si se modifica su arreglo de reservas
        logger.info("Se trata de agregar "+reserva+" a "+idP);
        return reservaLogic.createReserva(reserva);
    }
    
    /**
     * R2: cancelar reserva de equipo
     * Actualiza la info de una reserva de un profesor
     * @param idP id del profesor
     * @param idR id de la reserva
     * @return
     * @throws CityLogicException
     */
    @PUT
    @Path("{idP: \\d+}/cancelarReserva/{idR: \\d+}")
    public ReservaDTO cancelarReserva(@PathParam("idP") Long idP, @PathParam("idR") Long idR) throws CityLogicException
    {
        logger.info("Trata de hacer put");
        return reservaLogic.cancelarReserva(idR);
    }
    
    /**
     * R3
     *Obtiene las reservas de un profesor dado su id en un rango de fechas
     * @param idP id del profesor que posee las reservas
     * @param fechaI  fecha inicial del rango
     * @param fechaF  fecha final del rango
     * @return lista de reservas
     * @throws CityLogicException excepcion retornada por la logica
     */
    @GET
    @Path("{idP: \\d+}/reservasRangoFechas")
    public List<ReservaDTO> getReservasRangoFechas(@PathParam("idP") Long idP /*, Long fechaI, Long fechaF*/) throws CityLogicException
    {
        //Por ahora retorna todas las reservas
        //Debe pedirle al logic de profesores que le de sus reservas y pasarle tambien fechaI y fechaF
        return reservaLogic.getReservas();
    }
    
    /**
     *Obtiene las reservas de un profesor dado su id
     * @param idP
     * @return lista de reservas
     * @throws CityLogicException excepcion retornada por la logica
     */
    @GET
    @Path("{idP: \\d+}/reservas")
    public List<ReservaDTO> getReservas(@PathParam("idP") Long idP ) throws CityLogicException
    {
        return reservaLogic.getReservas();
    }
    
    /**
     *Obtiene los atributos de una reserva de un profesor, dados el idR y el idP
     * @param idP
     * @param idR
     * @return reserva buscado
     * @throws CityLogicException excepcion retornada por la logica
     */
    @GET
    @Path("{idP: \\d+}/reservas/{idR: \\d+}")
    public ReservaDTO getReserva(@PathParam("idP") Long idP , @PathParam("idR") Long idR) throws CityLogicException
    {
        return reservaLogic.getReserva(idR);
    }
    
}