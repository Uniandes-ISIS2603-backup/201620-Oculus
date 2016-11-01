/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package co.edu.uniandes.rest.resources;

import co.edu.uniandes.oculus.audiovisuales.api.IProfesorLogic;
import co.edu.uniandes.oculus.audiovisuales.api.IReservaLogic;
import co.edu.uniandes.oculus.audiovisuales.entities.ProfesorEntity;
import co.edu.uniandes.oculus.audiovisuales.entities.ReservaEntity;
import co.edu.uniandes.oculus.audiovisuales.exceptions.BusinessLogicException;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.DELETE;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;

import co.edu.uniandes.rest.dtos.ProfesorDTO;
import co.edu.uniandes.rest.dtos.ProfesorDetailDTO;
import co.edu.uniandes.rest.dtos.ReservaDTO;
import co.edu.uniandes.rest.dtos.ReservaDetailDTO;
import co.edu.uniandes.rest.exceptions.CityLogicException;
import java.util.ArrayList;
import java.util.Date;

import javax.inject.Inject;
import javax.ws.rs.PathParam;


/**
 *
 * @author fa.lopez10
 */

@Path("profesores")
@Produces("application/json")
public class ProfesorResource
{
    @Inject
    private IProfesorLogic profesorLogic;
    
    @Inject
    private IReservaLogic reservaLogic;
    
    /**
     * Convierte lista de entidades de profesor a una lista de dtos de profesor
     * @param entityList lista de entidades de profesor
     * @return lista de dtos correspondientes
     */
    private List<ProfesorDetailDTO> listEntity2DTOProfesor(List<ProfesorEntity> entityList) {
        List<ProfesorDetailDTO> list = new ArrayList<>();
        for (ProfesorEntity entity : entityList) {
            list.add(new ProfesorDetailDTO(entity));
        }
        return list;
    }
    
    /**
     * Convierte lista de entidades de profesor a una lista de dtos de profesor
     * @param entityList lista de entidades de profesor
     * @return lista de dtos correspondientes
     */
    private List<ReservaDetailDTO> listEntity2DTOReserva(List<ReservaEntity> entityList) {
        List<ReservaDetailDTO> list = new ArrayList<>();
        for (ReservaEntity entity : entityList) {
            list.add(new ReservaDetailDTO(entity));
        }
        return list;
    }
    
    /**
     * Obtiene los profesores
     * @return
     * @throws CityLogicException
     */
    @GET
    public List<ProfesorDetailDTO> getProfesores() throws BusinessLogicException {
        return listEntity2DTOProfesor(profesorLogic.getProfesores());
    }
    
    /**
     * Retorna un profesor dado su id
     * @param id
     * @return
     * @throws CityLogicException
     */
    @GET
    @Path("{id: \\d+}")
    public ProfesorDetailDTO getProfesor(@PathParam("id") Long id) throws BusinessLogicException
    {
        return new ProfesorDetailDTO(profesorLogic.getProfesor(id));
    }
    
    
    /**
     * Crea el profesor
     * @param profesor
     * @return
     * @throws CityLogicException
     */
    @POST
    public ProfesorDetailDTO createProfesor(ProfesorDTO profesor) throws BusinessLogicException {
        return new ProfesorDetailDTO(profesorLogic.createProfesor(profesor.toEntity()));
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
    public ProfesorDetailDTO updateProfesor(@PathParam("id") Long id, ProfesorDTO profe) throws BusinessLogicException {
        
        ProfesorEntity entity = profe.toEntity();
        entity.setId(id);
        return new ProfesorDetailDTO(profesorLogic.updateProfesor(entity));
    }
    
    /**
     * Elimina el profesor por id
     * @param id
     * @throws CityLogicException
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteProfesor(@PathParam("id") Long id) throws BusinessLogicException
    {
        profesorLogic.deleteProfesor(id);
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
    public ReservaDetailDTO createReserva(@PathParam("idP") Long idP, ReservaDTO reserva) throws BusinessLogicException {
        //viene por un Json
        //Dto datos que manda el usuario
        //lo agrega a un arreglo dentro de profesores
        //debe llamar al logic de profesores y ahi si se modifica su arreglo de reservas
        
        
        return new ReservaDetailDTO(reservaLogic.createReserva(idP, reserva.toEntity()));
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
    public ReservaDetailDTO cancelarReserva(@PathParam("idP") Long idP, @PathParam("idR") Long idR) throws BusinessLogicException
    {
        return new ReservaDetailDTO(reservaLogic.cancelarReserva(idR));
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
    public List<ReservaDetailDTO> getReservasRangoFechas(@PathParam("idP") Long idP /*, Long fechaI, Long fechaF*/) throws BusinessLogicException
    {
        //Debe pedirle al logic de profesores que le de sus reservas y pasarle tambien fechaI y fechaF
//        return listEntity2DTOReserva(profesorLogic.listReservasRangoFechas(idP, new Date(fechaI), new Date(fechaF)));
        return listEntity2DTOReserva(reservaLogic.getReservas(idP));
    }
    
    /**
     *Obtiene las reservas de un profesor dado su id
     * @param idP
     * @return lista de reservas
     * @throws CityLogicException excepcion retornada por la logica
     */
    @GET
    @Path("{idP: \\d+}/reservas")
    public List<ReservaDetailDTO> getReservas(@PathParam("idP") Long idP ) throws BusinessLogicException
    {
        return listEntity2DTOReserva(reservaLogic.getReservas(idP));
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
    public ReservaDetailDTO getReserva(@PathParam("idP") Long idP , @PathParam("idR") Long idR) throws BusinessLogicException
    {
        return new ReservaDetailDTO(reservaLogic.getReserva(idR));
    }
    
}