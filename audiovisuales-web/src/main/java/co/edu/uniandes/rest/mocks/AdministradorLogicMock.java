package co.edu.uniandes.rest.mocks;

/**
 * Mock del recurso Administradores (Mock del servicio REST)
 * @author German Andrade
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

//Preguntas foros sicua


import co.edu.uniandes.rest.dtos.AdministradorDTO;
import co.edu.uniandes.rest.exceptions.CityLogicException;

/*
 * AdministradorLogicMock
 * Mock del recurso Administrador (Mock del servicio REST)
 */

public class AdministradorLogicMock 
{
	
// objeto para presentar logs de las operaciones
	
private final static Logger logger = Logger.getLogger(AdministradorLogicMock.class.getName());
	
	// listado de administradores
    private static ArrayList<AdministradorDTO> administradores;
    //ArrayList de Administradores

    /**
     * Constructor. Crea los datos de ejemplo.
     */
    public AdministradorLogicMock() {

    	if (administradores == null) 
        {
             administradores = new ArrayList<>();
            //administradores.add(new AdministradorDTO("German A", 1L, "g.andrade10"));
            //administradores.add(new AdministradorDTO("Fabio L", 2L, "f.lopez20"));
            //administradores.add(new AdministradorDTO("Ana E", 3L, "a.espinosa30"));
            //administradores.add(new AdministradorDTO("Ana F", 4L, "a.fandino40"));
            //administradores.add(new AdministradorDTO("Sneider V", 5L, "e.velandia50"));
        }
        
    	// indica que se muestren todos los mensajes
    	logger.setLevel(Level.INFO);
    	
    	// muestra información 
    	logger.info("Inicializa la lista de administradores");
    	logger.info("administradores" + administradores );
    }    
    
	/**
	 * Obtiene el listado de administradores. 
	 * @return lista de administradores
	 * @throws CityLogicException cuando no existe la lista en memoria  
	 */    
    public List<AdministradorDTO> getAdministradores() throws CityLogicException
    {
    	if (administradores == null) {
    		logger.severe("Error interno: lista de administradores no existe.");
    		throw new CityLogicException("Error interno: lista de administradores no existe.");    		
    	}
    	
    	logger.info("retornando todos los administradores");
    	return administradores;
    }

 

    /**
     * Agrega un administrador a la lista.
     * @param newAdmin administrador a adicionar
     * @throws CityLogicException cuando ya existe un administrador con el id suministrado
     * @return administrador agregado
     */
    public AdministradorDTO createAdministrador(AdministradorDTO newAdmin) throws CityLogicException 
    {
    	logger.info("recibiendo solicitud de agregar administrador " + newAdmin);
    	
    	// el nuevo administrador tiene id ?
    	if ( newAdmin.getId() != null ) 
        {
	    	// busca el administrador con el id suministrado
	        for (AdministradorDTO administra : administradores) 
                {
	        	// si existe un administrador con ese id
	            if (Objects.equals(administra.getId(), newAdmin.getId()))
                    {
	            	logger.severe("Ya existe un administrador con ese id");
	                throw new CityLogicException("Ya existe un administrador con ese id");
	            }
	        }
	        
	    // el nuevo administrador no tiene id ? 
    	} 
        else {

    		// genera un id para el administardor
    		logger.info("Generando id para el nuevo administrador");
    		long newId = 1;
	        for (AdministradorDTO adminis : administradores) {
	            if (newId <= adminis.getId()){
	                newId =  adminis.getId() + 1;
	            }
	        }
	        newAdmin.setId(newId);
    	}
    	
        // agrega el administrador
    	logger.info("agregando el administrador " + newAdmin);
        administradores.add(newAdmin);
        return newAdmin;
    }
     /**
     * Actualiza la informacion del administrador
     * @param id id del administrador a modificar
     * @param admiUp nuevos datos del administrador
     * @return administrador modificado
     */
    public AdministradorDTO updateAdministrador(Long id, AdministradorDTO adminUp) throws CityLogicException 
    {
         boolean encontrado =false;
         AdministradorDTO actualAdministrador = null;
           
         for(int i = 0; i<administradores.size() && !encontrado ; i++)
         {
            actualAdministrador = ((AdministradorDTO)administradores.get(i));
            if(actualAdministrador.getId()==id)
               {
                encontrado = true;  
                }
         }
        
         if(!encontrado)
         {
            logger.severe("No existe un administrador con ese id");
	                throw new CityLogicException("No existe un administrador con ese id");
         }
    	
        actualAdministrador.setId(adminUp.getId());
        actualAdministrador.setLogin(adminUp.getLogin());
        actualAdministrador.setNombre(adminUp.getNombre());

        logger.info("retornando el administrador "+actualAdministrador);
    	return actualAdministrador;
    }
 
     /**
     *Borra el administrador identificado con el id dado
     * @param id id del administrador a eliminar
     */
    public void deleteAdministrador(Long id) throws CityLogicException 
    {
         boolean encontrado = false;
         AdministradorDTO actualAdmin = null;
        for(int i = 0; i<administradores.size() && !encontrado ; i++)
        {
            actualAdmin = ((AdministradorDTO)administradores.get(i));
            
            if(actualAdmin.getId()==id)
            {
                administradores.remove(i);
                encontrado=true;     
            }
        }
        
        if(!encontrado)
        {
            logger.severe("No existe un administrador con ese id");
	                throw new CityLogicException("No existe un administrador con ese id");
        }
    }
    
    /**
	 * Obtiene los atributos de una instancia de administrador con el id dado
	 * @return Administrador
         * @param id id del administrador a eliminar
	 * @throws CityLogicException cuando no existe la lista en memoria  
	 */    
    public AdministradorDTO getAdministrador( Long id) throws CityLogicException 
    {
        boolean encontrado =false;
         AdministradorDTO actualAdmin = null;
        
         for(int i = 0; i<administradores.size() && !encontrado ; i++)
         {
            actualAdmin = ((AdministradorDTO)administradores.get(i));
            if(actualAdmin.getId()==id)
            {
                encontrado = true;
            }
         }
         
         if(!encontrado)
         {
            logger.severe("No existe un administrador con ese id");
	                throw new CityLogicException("No existe un administrador con ese id");
        }
    	
    	logger.info("retornando el administrador "+actualAdmin);
    	return actualAdmin;
    }  
}
