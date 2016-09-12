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
            locaciones.add(new LocacionDTO(1L,"Mario Laserna"));
            locaciones.add(new LocacionDTO(2L,"Julio Mario Santo Domingo"));
            locaciones.add(new LocacionDTO(3L,"Aulas"));
            locaciones.add(new LocacionDTO(4L,"Henri Yerly"));
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

        // la nueva editorial tiene id ?
        if (newLocacion.getId() != null) {
            
            for (LocacionDTO locacion : locaciones) {
                
                if (Objects.equals(locacion.getId(), newLocacion.getId())) {
                    logger.severe("Ya existe una locacion con ese id");
                    throw new CityLogicException("Ya existe una locacion con ese id");
                };
                if (Objects.equals(locacion.getUbicacion(), newLocacion.getUbicacion())) {
                    logger.severe("Ya existe una locacion con esa ubicacion");
                    throw new CityLogicException("Ya existe una locacion con esa ubicacion");
                }

            }

           
        } else {
            for (LocacionDTO locacion : locaciones) {
                
                
                if (Objects.equals(locacion.getUbicacion(), newLocacion.getUbicacion())) {
                    logger.severe("Ya existe una locacion con esa ubicacion");
                    throw new CityLogicException("Ya existe una locacion con esa ubicacion");
                }

            }
           
            logger.info("Generando id para la nueva locacion");
            long newId = 1;
            for (LocacionDTO locacion : locaciones) {
                if (newId <= locacion.getId()) {
                    newId = locacion.getId() + 1;
                }
            }
            newLocacion.setId(newId);
        }

        
        logger.info("agregando locacion " + newLocacion);
        locaciones.add(newLocacion);
        return newLocacion;
    
    }
    
    /**
     * Actualiza la informaciÃ³n de la locacion con la nueva suministrada por parametro
     * @param pId id de la locacion a modificar
     * @param pLoc nuevos datos de la locacion
     * @return locacion modificada
     */
    public LocacionDTO updateLocacion(Long pId, LocacionDTO pLoc) throws CityLogicException 
    {
       logger.info("recibiendo solictud de modificar locacion " + pLoc);

        
        for (LocacionDTO locacion : locaciones) {
            if (Objects.equals(locacion.getId(), pId)) {

                
                locacion.setId(pLoc.getId());
                locacion.setUbicacion(pLoc.getUbicacion());

               
                logger.info("Modificando locacion " + locacion);
                return locacion;
            }
        }

       
        logger.severe("No existe una locacion con ese id");
        throw new CityLogicException("No existe una locacion con ese id");
    }
    
    /**
     * Elimina los datos de una Locacion
     *
     * @param id identificador de la locacion a eliminar
     * @throws CityLogicException cuando no existe una editorial con el id
     * suministrado
     */
    public void deleteLocacion(Long id) throws CityLogicException {
        logger.info("recibiendo solictud de eliminar locacion con id " + id);

        
        for (LocacionDTO locacion : locaciones) {
            if (Objects.equals(locacion.getId(), id)) {

                
                logger.info("eliminando locacion " + locacion);
                locaciones.remove(locacion);
                return;
            }
        }

       
        logger.severe("No existe una locacion con ese id");
        throw new CityLogicException("No existe una locacion con ese id");
    }
    
    /**
     * Obtiene una locacion
     *
     * @param id identificador de la locacion
     * @return locacion encontrada
     * @throws CityLogicException cuando el editorial no existe
     */
    public LocacionDTO getLocacion(Long id) throws CityLogicException {
        logger.info("recibiendo solicitud de locacion con id " + id);

        
        for (LocacionDTO locacion : locaciones) {
            if (Objects.equals(locacion.getId(), id)) {
                logger.info("retornando locacion " + locacion);
                return locacion;
            }
        }

        
        logger.severe("No existe locacion con ese id");
        throw new CityLogicException("No existe locacion con ese id");
    }
}
