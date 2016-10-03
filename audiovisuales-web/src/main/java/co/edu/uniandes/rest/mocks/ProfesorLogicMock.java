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
            profesores.add(new ProfesorDTO(1, "Juan Pablo Arjona", "jpArjona10",24));
            profesores.add(new ProfesorDTO(2, "Fernanda Velasquez", "fvelasquez11",25));
            profesores.add(new ProfesorDTO(3, "Rogelio Diaz", "rdiaz20",26));
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
        
    	//ProfesorDTO newTeacher = new ProfesorDTO(num, login, nom);
    	logger.info("proceso: recibiendo solicitud para agregar profe " + newTeacher.getNombre());
    	if ( newTeacher.getId() >= 0 )
        {
            
	        for (ProfesorDTO profesor : profesores)
                {
                    
	            if (profesor.getId()==newTeacher.getId())
                    {
	            	logger.severe("Ya existe un profesor con ese id. Intente con otro, gracias");
	                throw new CityLogicException("Ya existe un profesor con ese id. Intente con otro y disculpe los inconvenientes");
	            }
	        }
	        
    	}
    	
        
    	logger.info("proceso: generando id para el profesor");
	newTeacher.setId(profesores.size()+1);
    	logger.info("proceso: agregando profe " + newTeacher);
        profesores.add(newTeacher);
        return newTeacher;
    }
    
    
    
    /**
     * Elimina un profesor dado su id
     * @param id
     * @throws CityLogicException 
     */
    public void deleteTeacher (int id) throws CityLogicException
    {
        
        boolean encontrado = false;
        
        for (int i=0; i<profesores.size(); i++) 
        {
            int toCompare = ((ProfesorDTO)profesores.get(i)).getId();
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
            int toCompare = ((ProfesorDTO)profesores.get(i)).getId();
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

     
    
    public ProfesorDTO updateInfo(int id, ProfesorDTO profe) throws CityLogicException
    {
        ProfesorDTO toReturn = findTeacher(id);
        
        if(toReturn==null) 
        {
            logger.severe("proceso: no existe profesor con tal id");
            throw new CityLogicException("No existe un profesor con ese id");
            
        }else{
            profe.setId(id);
            profesores.set(id-1, profe);
            logger.info("proceso: retornando el profesor "+ ((ProfesorDTO)profesores.get(id-1)).getNombre());
        }
        
       
            return findTeacher(id);
    }
    
}

