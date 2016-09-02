/**
 * Mock del recurso Solicitudes (Mock del servicio REST)
 * @author fa.lopez10
 */
package co.edu.uniandes.rest.mocks;

import co.edu.uniandes.rest.dtos.SolicitudDTO;
import co.edu.uniandes.rest.exceptions.CityLogicException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * SolicitudLogicMock
 * Mock del recurso Solicitudes (Mock del servicio REST)
 * @author fa.lopez10
 */
public class SolicitudLogicMock {
    // objeto para presentar logs de las operaciones
	private final static Logger logger = Logger.getLogger(SolicitudLogicMock.class.getName());
        public final static String SOLICITUD_INEXISTENTE = "No existe solicitud con ese id";
	
	// listado de solicitudes
    private static ArrayList<SolicitudDTO> solicitudes;

    /**
     * Constructor. Crea los datos de ejemplo.
     */
    public SolicitudLogicMock() {

    	if (solicitudes == null) {
            solicitudes = new ArrayList<>();
            solicitudes.add(new SolicitudDTO(1L, new Date(2015, 5, 2, 15, 30), "computador", "rechazada"));
            solicitudes.add(new SolicitudDTO(2L, new Date(2016, 2, 10, 12, 30), "audifonos", "en espera"));
            solicitudes.add(new SolicitudDTO(3L, new Date(2015, 12, 24, 8, 30), "computador", "aprobada"));
        }
        
    	// indica que se muestren todos los mensajes
    	logger.setLevel(Level.INFO);
    	
    	// muestra información 
    	logger.info("Inicializa la lista de solicitudes");
    	logger.info("solicitudes " + solicitudes );
    }    
    
	/**
	 * Obtiene el listado de solicitudes. 
	 * @return lista de solicitudes
	 * @throws CityLogicException cuando no existe la lista en memoria  
	 */    
    public List<SolicitudDTO> getSolicitudes() throws CityLogicException {
    	if (solicitudes == null) {
    		logger.severe("Error interno: lista de solicitudes no existe.");
    		throw new CityLogicException("Error interno: lista de solicitudes no existe.");    		
    	}
    	
    	logger.info("retornando todas las solicitudes");
    	return solicitudes;
    }

 

    /**
     * Agrega una solicitud a la lista.
     * @param newSolicitud solicitud a adicionar
     * @throws CityLogicException cuando ya existe una solicitud con el id suministrado
     * @return solicitud agregada
     */
    public SolicitudDTO createSolicitud(SolicitudDTO newSolicitud) throws CityLogicException {
    	logger.info("recibiendo peticion de agregar solicitud " + newSolicitud);
    	
    	// la nueva solicitud tiene id ?
    	if ( newSolicitud.getId() != null ) {
	    	// busca la solicitud con el id suministrado
	        for (SolicitudDTO solicitud : solicitudes) {
	        	// si existe una solicitud con ese id
	            if (Objects.equals(solicitud.getId(), newSolicitud.getId())){
	            	logger.severe("Ya existe una solicitud con ese id");
	                throw new CityLogicException("Ya existe una solicitud con ese id");
	            }
	        }
	        
	    // la nueva solicitud no tiene id ? 
    	} else {

    		// genera un id para la solicitud
    		logger.info("Generando id paa la nueva solicitud");
    		long newId = 1;
	        for (SolicitudDTO solicitud : solicitudes) {
	            if (newId <= solicitud.getId()){
	                newId =  solicitud.getId() + 1;
	            }
	        }
	        newSolicitud.setId(newId);
    	}
    	
        // agrega la solicitud
    	logger.info("agregando solicitud " + newSolicitud);
        solicitudes.add(newSolicitud);
        return newSolicitud;
    }

    public SolicitudDTO getSolicitud(long id) throws CityLogicException {
        for(SolicitudDTO solicitud : solicitudes)
        {
            if(solicitud.getId()==id)
                return solicitud;
        }
        throw new CityLogicException(SOLICITUD_INEXISTENTE);
    }

    public SolicitudDTO updateSolicitud(Long id, SolicitudDTO newSolicitud) throws CityLogicException {
        for(SolicitudDTO solicitud : solicitudes)
        {
            if(solicitud.getId()==id)
            {
                solicitud.setFecha(newSolicitud.getFecha());
                solicitud.setEstado(newSolicitud.getEstado());
                solicitud.setTipo(newSolicitud.getTipo());
                return solicitud;
            }     
        }
        throw new CityLogicException(SOLICITUD_INEXISTENTE);
    }

    public void deleteSolicitud(Long id) throws CityLogicException {
        for(SolicitudDTO solicitud : solicitudes)
        {
            if(solicitud.getId()==id)
            {
                solicitudes.remove(solicitud);
                return;
            }     
        }
        throw new CityLogicException(SOLICITUD_INEXISTENTE);
    }

}
