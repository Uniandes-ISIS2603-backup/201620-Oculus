/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.mocks;

import co.edu.uniandes.rest.dtos.PuntoDeAtencionDTO;
import co.edu.uniandes.rest.exceptions.CityLogicException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author am.espinosa11
 */
public class LocacionLogicMock 
{
    private final static Logger logger = Logger.getLogger(LocacionLogicMock.class.getName());
	
	// listado de locaciones
    private static ArrayList<PuntoDeAtencionDTO> locaciones;
    //ArrayList de Locaciones
    
    /**
     * Constructor. Crea los datos de ejemplo.
     */
    public LocacionLogicMock() {

    	if (locaciones == null) {
            locaciones = new ArrayList<>();
            locaciones.add(new PuntoDeAtencionDTO(1L,"Mario Laserna"));
            locaciones.add(new PuntoDeAtencionDTO(2L,"Julio Mario Santo Domingo"));
            locaciones.add(new PuntoDeAtencionDTO(3L,"Aulas"));
            locaciones.add(new PuntoDeAtencionDTO(4L,"Henri Yerly"));
        }
        
    	// indica que se muestren todos los mensajes
    	logger.setLevel(Level.INFO);
    	
    	// muestra información 
    	logger.info("Inicializa la lista de locaciones");
    	logger.info("Locaciones" + locaciones );
    }   
    
     /**
	 * Obtiene el listado de locaciones. 
	 * @return lista de locaciones
	 * @throws CityLogicException cuando no existe la lista en memoria  
	 */    
    public List<PuntoDeAtencionDTO> getLocaciones() throws CityLogicException {
    	if (locaciones == null) {
    		logger.severe("Error interno: lista de locaciones no existe.");
    		throw new CityLogicException("Error interno: lista de locaciones no existe.");    		
    	}
    	
    	logger.info("retornando todas las locaciones");
    	return locaciones;
    }
    
   
   /**
     * Agrega una locacion a la lista.
     * @param newLocacion locacion a adicionar
     * @throws CityLogicException cuando ya existe un equipo con el id suministrado
     * @return locacion agregada
     */
    public PuntoDeAtencionDTO createLocacion(PuntoDeAtencionDTO newLocacion) throws CityLogicException 
    {
    	logger.info("recibiendo solicitud de agregar locacion " + newLocacion);
    	
    	// la nueva locación tiene id ?
    	if ( newLocacion.getId() != null ) {
	    	// busca la locación con el id suministrado
	        for (PuntoDeAtencionDTO locacion : locaciones) {
	        	// si existe una locación con ese id
	            if (Objects.equals(locacion.getId(), newLocacion.getId())){
	            	logger.severe("Ya existe una locación con ese id");
	                throw new CityLogicException("Ya existe una locación con ese id");
	            }
	        }
	        
	    // la nueva locación no tiene id ? 
    	} else {

    		// genera un id para la locación
    		logger.info("Generando id para la nueva locación");
    		long newId = 1;
	        for (PuntoDeAtencionDTO locacion : locaciones) {
	            if (newId <= locacion.getId()){
	                newId =  locacion.getId() + 1;
	            }
	        }
	        newLocacion.setId(newId);
    	}
    	
        // agrega la locación
    	logger.info("agregando locación " + newLocacion);
        locaciones.add(newLocacion);
        return newLocacion;
    }
    
     /**
     *Borra la locacion con la ubicacion dada
     * @param pId de la locacion a eliminar
     * @throws CityLogicException cuando no existe la lista en memoria 
     */
    public void deleteLocacion(Long pId) throws CityLogicException 
    {
        boolean encontrado = false;
        int i = 0;
        while(i < locaciones.size() && !encontrado)
        {
            if(locaciones.get(i).getId() == pId)
            {
                encontrado = true;
                locaciones.remove(i);
            }
            i++;
        }
        if(!encontrado)
        {
             logger.severe("No existe una locacion con esa ubicacion");
             throw new CityLogicException("No existe una locacion con esa ubicacion");
            
        }
    }
    
    /**
	 * Obtiene los atributos de una instancia de locacion con la ubicacion dada
         * @param pId de la locacion a eliminar
	 * @return Locacion
	 * @throws CityLogicException cuando no existe la lista en memoria  
	 */    
    public PuntoDeAtencionDTO getLocacion(Long pId) throws CityLogicException 
    {
        PuntoDeAtencionDTO retornar = new PuntoDeAtencionDTO(0L,"Sin ubicación");
        boolean encontrado = false;
        int i = 0;
        while(i < locaciones.size() && !encontrado)
        {
            if(locaciones.get(i).getId() == pId)
            {
                encontrado = true;
                retornar = locaciones.get(i);
            }
            i++;
    }
        if(!encontrado)
        {
            logger.severe("No existe una locación con esa ubicación");
            throw new CityLogicException("No existe una locación con esa ubicación");
        }
        return retornar;
    
    }
    
    /**
     * Actualiza la informaciÃ³n de la locacion con la nueva suministrada por parametro
     * @param pId id de la locacion a modificar
     * @param pLoc nuevos datos de la locacion
     * @return locacion modificada
     */
    public PuntoDeAtencionDTO updateLocacion(Long pId, PuntoDeAtencionDTO pLoc) throws CityLogicException 
    {
        PuntoDeAtencionDTO modificar = new PuntoDeAtencionDTO(0L,"Sin ubicación");
        boolean encontrado = false;
        int i = 0;
        while(i < locaciones.size() && !encontrado)
        {
            if(locaciones.get(i).getId() == pId)
            {
                encontrado = true;
                modificar = locaciones.get(i);
                modificar.setUbicacion(pLoc.getUbicacion());
              
            }
            i++;
        }
        if(!encontrado)
        {
            logger.severe("No existe una locacion con esa ubicacion");
            throw new CityLogicException("No existe una locacion con esa ubicacion");
        }
        return modificar;
    }
    
}
