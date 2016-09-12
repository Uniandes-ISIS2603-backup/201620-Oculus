package co.edu.uniandes.rest.mocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import co.edu.uniandes.rest.dtos.AdministradorDTO;
import co.edu.uniandes.rest.exceptions.CityLogicException;
/**
 *
 * @author Sneider Velandia G
 */
public class AdministradorLogicMock
{
    private final static Logger logger = Logger.getLogger(AdministradorLogicMock.class.getName());
    private static ArrayList<AdministradorDTO> administradores;
    
    //constructor
    public AdministradorLogicMock()
    {
       if (administradores == null)
       {
            administradores = new ArrayList<>();
            administradores.add(new AdministradorDTO("German A", 100001L, "g.andrade10"));
            administradores.add(new AdministradorDTO("Fabio L", 200002L, "f.lopez20"));
            administradores.add(new AdministradorDTO("Ana E", 300003L, "a.espinosa30"));
            administradores.add(new AdministradorDTO("Ana F", 400004L, "a.fandino40"));
            administradores.add(new AdministradorDTO("Sneider V", 500005L, "e.velandia50"));
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
            if (administradores == null)
            {
    		logger.severe("Error interno: lista de Administradores NO existe.");
    		throw new CityLogicException("Error interno: lista de Administradores NO existe.");    		
            }
    	
    	logger.info("retornando todos los equipos");
    	return administradores;
        }
        
        
    /**
     * Agrega un administrador a la lista.
     * @param newAdmi administrador a adicionar
     * @throws CityLogicException cuando ya existe un administrador con el codigo suministrado
     * @return administrador agregado
     */
    public AdministradorDTO createAdministrador(AdministradorDTO newAdmi) throws CityLogicException 
    {
    	logger.info("recibiendo solicitud de agregar administrador " + newAdmi);
    	// el nuevo administrador ya existe ?
    	if ( newAdmi.getCodigo() != null) 
        {
	    	// busca el administrador con el codigo suministrado
	        for (AdministradorDTO administra : administradores) 
                {
	        	// si existe un administrador con ese id
                    if (Objects.equals(administra.getCodigo(), newAdmi.getCodigo()))
                    {
	            	logger.severe("Ya existe un administrador con ese codigo");
	                throw new CityLogicException("Ya existe un Administrador con ese codigo");
	            }
	        }    
        } 
        // el nuevo administrador no existe? 
        // agrega el equipo
            logger.info("agregando administrador " + newAdmi);
            administradores.add(newAdmi);
            return newAdmi;
    }
    
     /**
     * Actualiza la informacion del administrador identificado con codigo con la informacion pasada por parametro
     * @param codigo codigo del administrador a modificar
     * @param administrador nuevos datos del administrador
     * @return administrador modificado
     */
    public AdministradorDTO updateAdministrador (Long codigo, AdministradorDTO administadorUp) throws CityLogicException
    {
        logger.info("recibiendo solictud de modificar administrador " + administadorUp);

        
        for (AdministradorDTO admin : administradores) {
            if (Objects.equals(admin.getCodigo(), codigo)) 
            {
               admin.setCodigo(administadorUp.getCodigo());
               admin.setLogin(administadorUp.getLogin());
               admin.setNombre(administadorUp.getNombre());

               
                logger.info("Modificando administrador " + admin);
                return admin;
            }
        }
        logger.severe("No existe un administrador con ese codigo");
        throw new CityLogicException("No existe un administrador con ese codigo");
    }
     
    /**
     *Borra el administrador identificado con el codigo dado
     * @param codigo codigo del administrador a eliminar
     */
    public void deleteAdministrador (Long codigoDelete) throws CityLogicException
    {
      logger.info("recibiendo solictud de eliminar administrador con codigo " + codigoDelete);

        
        for (AdministradorDTO admin : administradores) 
        {
            if (Objects.equals(admin.getCodigo(), codigoDelete)) 
            {              
                logger.info("eliminando administrador " + admin);
                administradores.remove(admin);
                return;
            }
        }
        logger.severe("No existe un administrador con ese codigo");
        throw new CityLogicException("No existe un administrador con ese codigo");
    }
    
    /**
     * Obtiene los atributos de un administrador dado su codigo                        
     * @return administrador encontrado
     * @throws CityLogicException cuando no existe la lista en memoria  
     */ 
    public AdministradorDTO getAdministradorDTO (Long codigoS) throws CityLogicException
    {
         logger.info("recibiendo solicitud de administrador con codigo " + codigoS);

        // busca el administrador con el codigo suministrado
        for (AdministradorDTO admi : administradores) 
        {
            if (Objects.equals(admi.getCodigo(), codigoS)) {
                logger.info("retornando administrador " + admi);
                return admi;
            }
        }

        // si no encuentra el administrador
        logger.severe("No existe el administrador con ese codigo");
        throw new CityLogicException("No existe administrador con ese codigo");
        }
}