package co.edu.uniandes.rest.mocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import co.edu.uniandes.rest.dtos.AdministradorDTO;
import co.edu.uniandes.rest.dtos.EquipoDTO;
import co.edu.uniandes.rest.dtos.PuntoDeAtencionDTO;
import co.edu.uniandes.rest.exceptions.CityLogicException;
/**
 *
 * @author Sneider Velandia G
 */
public class AdministradorLogicMock
{
    private final static Logger logger = Logger.getLogger(AdministradorLogicMock.class.getName());
    private static AdministradorDTO administrador;
    private Long consecutivoEquipo;
    //constructor
    public AdministradorLogicMock()
    {
        consecutivoEquipo = 1l;
       if (administrador == null)
       {
           PuntoDeAtencionDTO pPunto  = new PuntoDeAtencionDTO(1L, "W 404");
            administrador = new AdministradorDTO("German Andrade", 201511422, "gc.andrade10", pPunto);
            EquipoDTO e = new EquipoDTO(consecutivoEquipo, EquipoDTO.tipos[0], "Dell");
            agregarEquipo(e);
       }
       
       // indica que se muestren todos los mensajes
    	logger.setLevel(Level.INFO);
    	
    	// muestra información 
    	logger.info("Inicializa el administrador");
    }
    
    /**
     * Agrega un equipo a la locación del administrador.
     * 
     */
    public EquipoDTO agregarEquipo(EquipoDTO e)
    {
        logger.info("consecutivo"+this.consecutivoEquipo);
        logger.info("id dado por usuario"+e.getId());
        e.setId(this.consecutivoEquipo);
        logger.info("id dado por el programa"+e.getId());
        this.consecutivoEquipo = this.consecutivoEquipo+1;
        logger.info("consecutivo2"+this.consecutivoEquipo);
        return administrador.getPuntoDeAtencionDTO().agregarEquipo(e);
    }
    
    	/**
	 * Obtiene el listado de equipos de la sede del administrador
	 * @return lista de administradores
	 * @throws CityLogicException cuando no existe la lista en memoria  
	 */    
        public List<EquipoDTO> getEquiposAdministrador() throws CityLogicException 
        {
            ArrayList equipos =administrador.getPuntoDeAtencionDTO().getEquipos();
            if ( equipos== null)
            {
    		logger.severe("Error interno: lista de equipos NO existe.");
    		throw new CityLogicException("Error interno: lista de Administradores NO existe.");    		
            }
    	
    	logger.info("retornando todos los equipos");
    	return equipos;
        }
        
}