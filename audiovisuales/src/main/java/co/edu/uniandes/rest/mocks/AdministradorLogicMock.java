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
    private final static Logger logger = Logger.getLogger(EquipoLogicMock.class.getName());
    private static ArrayList<AdministradorDTO> administradores;
    
    //constructor
    public AdministradorLogicMock()
    {
       if (administradores == null)
       {
            administradores = new ArrayList<>();
            administradores.add(new AdministradorDTO("German A", 100001, "g.andrade10"));
            administradores.add(new AdministradorDTO("Fabio L", 200002, "f.lopez20"));
            administradores.add(new AdministradorDTO("Ana E", 300003, "a.espinosa30"));
            administradores.add(new AdministradorDTO("Ana F", 400004, "a.fandino40"));
            administradores.add(new AdministradorDTO("Sneider V", 500005, "e.velandia50"));
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
    	if ( newAdmi.getCodigo() != null ) 
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
    public AdministradorDTO updateAdministrador (int codigo, AdministradorDTO administadorUp) throws CityLogicException
    {
        boolean encontro = false;
        AdministradorDTO actualAdmi = null;
        
        for (int i=0; i<administradores.size(); i++) 
        {
            actualAdmi = (AdministradorDTO)administradores.get(i);
            if(actualAdmi.getCodigo() == codigo)
            {
                encontro = true;
            }
        }
        //si no encuentra el administrador lanzamos mensaje de error
        if(encontro == false)
        {
          logger.severe("No existe un administrador con ese codigo");
	  throw new CityLogicException("No existe un administrador con ese codigo");
        }
        //si encuentra el administrador hacemos el cambio 
        actualAdmi.setCodigo(administadorUp.getCodigo());
        actualAdmi.setNombre(administadorUp.getNombre());
        actualAdmi.setLogin(administadorUp.getLogin());
        
        logger.info("retornando el administrador "+ actualAdmi);
    	return actualAdmi;
    }
     
    /**
     *Borra el administrador identificado con el codigo dado
     * @param codigo codigo del administrador a eliminar
     */
    public void deleteAdministrador (int codigoDelete) throws CityLogicException
    {
       boolean encontro = false;
       AdministradorDTO administradorActual = null;
       //buscamos el administrador y lo eliminamos
        for (int i=0; i<administradores.size(); i++) 
        {
            administradorActual = (AdministradorDTO)administradores.get(i);
            if(administradorActual.getCodigo() == codigoDelete)
            {
                administradores.remove(i);
                encontro = true;
            }
        }
        //no se encuentra el administrador
        if (encontro == false)
        {
          logger.severe("No existe un Administrador con ese codigo");
	  throw new CityLogicException("No existe un Administrador con ese codigo"); 
        }
    }
    
    /**
     * Obtiene los atributos de un administrador dado su codigo                        
     * @return administrador encontrado
     * @throws CityLogicException cuando no existe la lista en memoria  
     */ 
    public AdministradorDTO getAdministradorDTO (int codigoS) throws CityLogicException
    {
        boolean encontro = false;
        AdministradorDTO admiActual = null;
        for (int i=0; i<administradores.size(); i++) 
        {
            admiActual = (AdministradorDTO)administradores.get(i);
            if(admiActual.getCodigo() == codigoS)
            {
                encontro = true;
            }
        }
        //no existe el administrador
        if( encontro == false)
        {
            logger.severe("No existe un administrador con ese codigo");
	    throw new CityLogicException("No existe un administrador con ese codigo");
        }
        logger.info("retornando el administrador "+admiActual);
        return admiActual;
    }
}