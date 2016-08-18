/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.mocks;
import co.edu.uniandes.rest.dtos.ProfesorDTO;
import co.edu.uniandes.rest.exceptions.CityLogicException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ac.fandino10
 */
public class ProfesorLogicMock
{
    
    //Object that represents the logs of the operations
    private final static Logger logger = Logger.getLogger(ProfesorLogicMock.class.getName());
    
    //List of teachers
    private static ArrayList<ProfesorDTO> profesores;
    
    /**
     * Constructor of the logic of profesor mock
     */
    public ProfesorLogicMock()
    {
        if (profesores == null)
        {
            profesores = new ArrayList<>();
            profesores.add(new ProfesorDTO(1L, "Elba Sito", "Contasena"));
            profesores.add(new ProfesorDTO(38L, "Elba Nano", "Clave123"));
            profesores.add(new ProfesorDTO(3L, "Elba Arquito", "AmoAJustinBieber"));
        }
        
    	logger.setLevel(Level.INFO);
    	
    	logger.info("Inicializa la lista de profesores");
    	logger.info("Profesores" + profesores );
    }
    
    /**
     * Metodo que retorna los profesores de la lista
     * @return profesores
     * @throws CityLogicException 
     */
    public List<ProfesorDTO> getProfesores() throws CityLogicException
    {
    	if (profesores == null)
        {
    		logger.severe("Error: no hay profesores.");
    		throw new CityLogicException("Lo sentimos, no hay profesores disponibles");    		
    	}
    	
    	logger.info("Retornando profesores.. sea paciente");
    	return profesores;
    }
    
    /**
     * Metodo que agrega un profesor a la lista
     * @param newTeacher
     * @return
     * @throws CityLogicException 
     */
    public ProfesorDTO createProfesor(ProfesorDTO newTeacher) throws CityLogicException
    {
    	logger.info("proceso: recibiendo solicitud para agregar profe " + newTeacher);
    	
    	if ( newTeacher.getId() != 0 )
        {
            
	        for (ProfesorDTO profesor : profesores)
                {
                    
	            if (Objects.equals(profesor.getId(), newTeacher.getId()))
                    {
	            	logger.severe("Ya existe un profesor con ese id. Intente con otro, gracias");
	                throw new CityLogicException("Ya existe un profesor con ese id. Intente con otro y disculpe los inconvenientes");
	            }
	        }
	        
    	} else {
           
    		logger.info("proceso: generando id para el profesor");
    		long newId = 1;
	        for (ProfesorDTO profesor : profesores)
                {
	            if (newId <= profesor.getId())
                    {
	                newId =  profesor.getId() + 1;
	            }
	        }
	        newTeacher.setId(newId);
    	}
    	
        // agrega la ciudad
    	logger.info("proceso: agregando profe " + newTeacher);
        profesores.add(newTeacher);
        return newTeacher;
    }
    
    
    
    /**
     * Elimina un profesor dado su id
     * @param id
     * @throws CityLogicException 
     */
    public void deleteTeacher (Long id) throws CityLogicException
    {
        
        boolean encontrado = false;
        
        for (int i=0; i<profesores.size(); i++) 
        {
            Long toCompare = ((ProfesorDTO)profesores.get(i)).getId();
            if(Objects.equals(toCompare, id))
            {
                encontrado = true;
                profesores.remove(i);
            }
        }
        if (encontrado == false)
        {
          logger.severe("No worries, ya estaba eliminado el profesor con ese id");
	  throw new CityLogicException("No worries, ya estaba eliminado el profesor con ese id"); 
        }
    }
    
    /**
     * Encuentra el profesor dado su id
     * @param id
     * @return
     * @throws CityLogicException 
     */
    public ProfesorDTO findTeacher (int id) throws CityLogicException
    {
        ProfesorDTO actual = null;
        
        for (int i=0; i<profesores.size(); i++) 
        {
            Long toCompare = ((ProfesorDTO)profesores.get(i)).getId();
            if(Objects.equals(toCompare, id))
            {
                actual=(ProfesorDTO)profesores.get(i);
            }
        }
        if (actual == null)
        {
          logger.severe("Ese profesor ya no trabaja para nosotros. Err Profesor no encontrado");
	  throw new CityLogicException("Ese profesor ya no trabaja para nosotros. Err Profesor no encontrado"); 
        }
        logger.info("proceso: retornando el profesor "+ actual);
        return actual;
    }
    
    /**
     * Actualiza la informacion de un profesor que se encuentra dado su id personal e instransferible
     * @param id
     * @param name
     * @param contrasena
     * @return
     * @throws CityLogicException 
     */

     
    
    public ProfesorDTO updateInfo(Long id, ProfesorDTO profe) throws CityLogicException
    {
        
        boolean encontrado = false;
        ProfesorDTO actual = null;
        
        for (int i=0; i<profesores.size(); i++) 
        {
            Long toCompare = ((ProfesorDTO)profesores.get(i)).getId();
            if(Objects.equals(toCompare, id))
            {
                encontrado = true;
                i=profesores.size();
                actual=(ProfesorDTO)profesores.get(i);
                //if(name!=null) actual.setNombre(name);
                //if(contrasena!=null) actual.setLogin(contrasena);
                logger.info("proceso: retornando el profesor "+ actual);
                return actual;
            }
        }
        
        if(encontrado == false)
        {
          logger.severe("proceso: no existe profesor con tal id");
	  throw new CityLogicException("No existe un profesor con ese id");
        }
        return actual;
    }
    
}

