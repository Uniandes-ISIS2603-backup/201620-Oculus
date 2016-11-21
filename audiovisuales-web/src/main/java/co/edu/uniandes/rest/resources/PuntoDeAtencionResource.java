/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.resources;

import co.edu.uniandes.oculus.audiovisuales.api.IAdministradorLogic;
import co.edu.uniandes.oculus.audiovisuales.api.IPuntoDeAtencionLogic;
import co.edu.uniandes.oculus.audiovisuales.api.IReservaLogic;
import co.edu.uniandes.oculus.audiovisuales.entities.PuntoDeAtencionEntity;
import co.edu.uniandes.oculus.audiovisuales.entities.ReservaEntity;
import co.edu.uniandes.rest.dtos.PuntoDeAtencionDTO;
import co.edu.uniandes.rest.dtos.PuntoDeAtencionDetailDTO;
import co.edu.uniandes.rest.dtos.ReservaDTO;
import co.edu.uniandes.rest.dtos.ReservaDetailDTO;
import co.edu.uniandes.rest.exceptions.CityLogicException;

import java.util.ArrayList;

import java.util.List;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.DELETE;


import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
/**
 *
 * @author am.espinosa11
 */
/**
 * Clase que implementa el recurso REST correspondiente a "puntosDeAtencion".
 * 
 * Al ejecutar la aplicación, el recurse será accesibe a través de la 
 * ruta "/api/puntosDeAtencion" 
 * 
 * 
 */
@Path("puntosDeAtencion")
@Produces("application/json")
public class PuntoDeAtencionResource {

	@Inject 
        private IPuntoDeAtencionLogic puntoDeAtencionLogic;        
	
        @Inject 
        private IReservaLogic reservaLogic;
        
     public List<ReservaDetailDTO> listEntityToDTO(List<ReservaEntity> listEntity)
        {
            List<ReservaDetailDTO> listaADevolver = new ArrayList<>();
            for (ReservaEntity res : listEntity)
            {
              listaADevolver.add(new ReservaDetailDTO(res));
            }
            return listaADevolver;
        }    
    /**
     * Convierte una lista de PuntoDeAtencionEntity a una lista de PuntoDeAtencionDetailDTO.
     * @param listEntity Lista de PuntoDeAtencionEntity a convertir.
     * @return lista de PuntoDeAtencionDetailDTO convertida.
     *
     */
        public List<PuntoDeAtencionDetailDTO> lisEntityToDTO(List<PuntoDeAtencionEntity> listEntity)
        {
            List<PuntoDeAtencionDetailDTO> listaADevolver = new ArrayList<>();
            for (PuntoDeAtencionEntity puntoDeAtencion : listEntity)
            {
              listaADevolver.add(new PuntoDeAtencionDetailDTO(puntoDeAtencion));
            }
            return listaADevolver;
        }
	/**
	 * Obtiene el listado de los puntos de atencion. 
	 * @return lista de puntos de atencion 
	 */
    @GET
    public List<PuntoDeAtencionDetailDTO> getPuntosDeAtencion() 
    {
        return lisEntityToDTO(puntoDeAtencionLogic.getPuntosDeAtencion());
    }

    /**
     * Obtiene un punto de atencion
     * @param id identificador del punto de atencion
     * @return punto encontrado
     */
    @GET
    @Path("{id: \\d+}")
    public PuntoDeAtencionDetailDTO getPuntoDeAtencion(@PathParam("id") Long id) 
    {
        return new PuntoDeAtencionDetailDTO(puntoDeAtencionLogic.getPuntoDeAtencion(id));
    }
    
    /**
     * Obtiene un punto de atencion con la ubicación dada por parametro
     * @param ubic ubicación del punto de atencion
     * @return punto encontrado
     */
    @GET
    @Path("ubicacion")
    public PuntoDeAtencionDetailDTO getPuntoDeAtencionByUbicacion(@QueryParam("ubicacion") String ubic)
    {
        PuntoDeAtencionEntity puntoEntity = puntoDeAtencionLogic.getByUbicacion(ubic);
        if(puntoEntity == null)
        {
            throw new WebApplicationException("No existe un punto de atención con esa especificación", 404);
            
        }
        else
        {
            return new PuntoDeAtencionDetailDTO(puntoEntity);
        }
    }

    /**
     * Agrega un punto de atencion
     * @param punto punto de atencion a agregar
     * @return datos del punto de atencion a agregar
     * @throws CityLogicException cuando ya existe un punto de atencion con el id suministrado
     */
    @POST
    public PuntoDeAtencionDetailDTO createPuntoDeAtencion(PuntoDeAtencionDetailDTO punto) throws Exception 
    {
        return new PuntoDeAtencionDetailDTO(puntoDeAtencionLogic.createPuntoDeAtencion(punto.toEntity()));
    }

    /**
     * Actualiza los datos de un punto de atencion
     * @param id identificador de el punto de atencion a modificar
     * @param punto punto de atencion a modificar
     * @return datos del punto de atencion modificada 
     */
    @PUT
    @Path("{id: \\d+}")
    public PuntoDeAtencionDetailDTO updatePuntoDeAtencion(@PathParam("id") Long id, PuntoDeAtencionDetailDTO punto) 
    {
        PuntoDeAtencionEntity puntoEntity = punto.toEntity();
        puntoEntity.setId(id);
        return new PuntoDeAtencionDetailDTO(puntoDeAtencionLogic.updatePuntoDeAtencion(puntoEntity));
    }

    /**
     * Elimina los datos de un punto de atencion
     * @param id identificador del punto de atencion a eliminar
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deletePuntoDeAtencion(@PathParam("id") Long id) 
    {
    	puntoDeAtencionLogic.deletePuntoDeAtencion(id);
    }
    
    
    
    
    
    //Recursos de Administrador\\
    
    
   /**
     * Lista las reservas PENDIENTES de un Punto de atencion en una fecha dada.
     * @param idPA identificador del punto de atencion.
     * @return lista con las reservas pendientes en la locacion en la fecha dada.
     * @throws CityLogicException 
     */
    @GET
    @Path("{idPuntoAtencion: \\d+}/ReservasPendientes")  //faltan los parametros de las fechas
    public List<ReservaDetailDTO> getReservasPendientesPuntoA(@PathParam("idPuntoAtencion")Long idPA) throws CityLogicException
    {
        //return reservaLogic.getReservas();
        //return listAdminEntityToDTO(administradorLogic.getAdministradores());
        return listEntityToDTO(reservaLogic.getReservasPendientes(idPA));
    }    
    
    /**
     * Lista las reservas CANCELADAS de un Punto de atencion en una fecha dada.
     * @param idPA identificador del punto de atencion 
     * @return lista con las reservas canceladas en la locacion en la fecha dada.
     * @throws CityLogicException 
     */
    @GET
    @Path("{idPuntoAtencion: \\d+}/ReservasCanceladas")  //faltan los parametros de las fechas
    public List<ReservaDetailDTO> getReservasCanceladasPuntoA(@PathParam("idPuntoAtencion")Long idPA) throws CityLogicException
    {
        return listEntityToDTO(reservaLogic.getReservasCanceladas(idPA));
    }
    
    /**
     * Lista TODAS las reservas de un Punto de atencion.
     * @param idPA identificador del punto de atencion. 
     * @return lista con las reservas la locacion 
     * @throws CityLogicException 
     */
    @GET
    @Path("{idPuntoAtencion: \\d+}/TodasLasReservasDeUnPuntoA") 
    public List<ReservaDetailDTO> getReservasPuntoA(@PathParam("idPuntoAtencion")Long idPA) 
    {
        return listEntityToDTO(reservaLogic.getReservasByIdProfesor(idPA));
    }
   
}
