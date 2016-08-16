/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.mocks;

import co.edu.uniandes.rest.dtos.LocacionDTO;
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
    private static ArrayList<LocacionDTO> locaciones;
    //ArrayList de Locaciones
    
    /**
     * Constructor. Crea los datos de ejemplo.
     */
    public LocacionLogicMock() {

    	if (locaciones == null) {
            locaciones = new ArrayList<>();
            locaciones.add(new LocacionDTO("Mario Laserna"));
            locaciones.add(new LocacionDTO("Julio Mario Santo Domingo"));
            locaciones.add(new LocacionDTO("Aulas"));
            locaciones.add(new LocacionDTO("Henri Yerly"));
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
    public List<LocacionDTO> getLocaciones() throws CityLogicException {
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
    public LocacionDTO createLocacion(LocacionDTO newLocacion) throws CityLogicException 
    {
    	logger.info("recibiendo solicitud de agregar locacion " + newLocacion);
    	
    	// la nueva locacion tiene ubicacion ?
    	if ( newLocacion.getUbicacion() != null ) {
	    	// busca la locacion con la ubicacion suministrada
	        for (LocacionDTO locacion : locaciones) {
	        	// si existe una locacion con esa ubicacion
	            if (Objects.equals(locacion.getUbicacion(), newLocacion.getUbicacion()))
                    {
	            	logger.severe("Ya existe una locacion con esa ubicacion");
	                throw new CityLogicException("Ya existe una locacion con esa ubicacion");
	            }
	        }
	        
	    // la locacion no tiene ubicacion 
    	}
      //  else
        //{
          //  logger.severe("La locación no tiene ubicación, no es posible agregar");
	    //throw new CityLogicException("La locación no tiene ubicación, no es posible agregar");
        //}
    	
        // agrega la locacion
    	logger.info("agregando locacion " + newLocacion);
        locaciones.add(newLocacion);
        return newLocacion;
    }
    
     /**
     *Borra la locacion con la ubicacion dada
     * @param pUbicacion de la locacion a eliminar
     * @throws CityLogicException cuando no existe la lista en memoria 
     */
    public void deleteLocacion(String pUbicacion) throws CityLogicException 
    {
        boolean encontrado = false;
        int i = 0;
        while(i < locaciones.size() && !encontrado)
        {
            if(locaciones.get(i).getUbicacion().equals(pUbicacion))
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
         * @param pUbicacion de la locacion a eliminar
	 * @return Locacion
	 * @throws CityLogicException cuando no existe la lista en memoria  
	 */    
    public LocacionDTO getLocacion(String pUbicacion) throws CityLogicException 
    {
        LocacionDTO retornar = new LocacionDTO("Sin ubicación");
        boolean encontrado = false;
        int i = 0;
        while(i < locaciones.size() && !encontrado)
        {
            if(locaciones.get(i).getUbicacion().equals(pUbicacion))
            {
                encontrado = true;
                retornar = locaciones.get(i);
            }
            i++;
    }
        if(!encontrado)
        {
            logger.severe("No existe una locacion con esa ubicacion");
            throw new CityLogicException("No existe una locacion con esa ubicacion");
        }
        return retornar;
    
    }
    
    /**
     * Actualiza la informaciÃ³n de la locacion con la nueva suministrada por parametro
     * @param pUbicacion ubicacion de la locacion a modificar
     * @param pLoc nuevos datos de la locacion
     * @return locacion modificada
     */
    public LocacionDTO updateLocacion(String pUbicaccion, LocacionDTO pLoc) throws CityLogicException 
    {
        LocacionDTO modificar = new LocacionDTO("Sin ubicación");
        boolean encontrado = false;
        int i = 0;
        while(i < locaciones.size() && !encontrado)
        {
            if(locaciones.get(i).getUbicacion().equals(pUbicaccion))
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
