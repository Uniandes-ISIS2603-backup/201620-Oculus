/**
 * Mock del recurso Reservas (Mock del servicio REST)
 * @author fa.lopez10
 */
package co.edu.uniandes.rest.mocks;

import co.edu.uniandes.rest.dtos.ReservaDTO;
import co.edu.uniandes.rest.exceptions.CityLogicException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * ReservaLogicMock
 * Mock del recurso Reservas (Mock del servicio REST)
 * @author fa.lopez10
 */
public class ReservaLogicMock {
    // objeto para presentar logs de las operaciones
	private final static Logger logger = Logger.getLogger(ReservaLogicMock.class.getName());
        public final static String RESERVA_INEXISTENTE = "No existe reserva con ese id";
        
	
	// listado de reservas
    private static ArrayList<ReservaDTO> reservas;

    /**
     * Constructor. Crea los datos de ejemplo.
     */
    public ReservaLogicMock() {
        Date date = new Date();

    	if (reservas == null) {
            reservas = new ArrayList<>();
            reservas.add(new ReservaDTO(1L, date, ReservaDTO.RESERVA_CANCELADA));
            reservas.add(new ReservaDTO(2L, date, ReservaDTO.RESERVA_APROBADA));
            reservas.add(new ReservaDTO(3L, date, ReservaDTO.RESERVA_APROBADA));
        }
        
    	// indica que se muestren todos los mensajes
    	logger.setLevel(Level.INFO);
    	
    	// muestra información 
    	logger.info("Inicializa la lista de reservas");
    	logger.info("reservas " + reservas );
    }    
    
	/**
	 * Obtiene el listado de reservas. 
	 * @return lista de reservas
	 * @throws CityLogicException cuando no existe la lista en memoria  
	 */    
    public List<ReservaDTO> getReservas() throws CityLogicException {
    	if (reservas == null) {
    		logger.severe("Error interno: lista de reservas no existe.");
    		throw new CityLogicException("Error interno: lista de reservas no existe.");    		
    	}
    	
    	logger.info("retornando todas las reservas");
    	return reservas;
    }

 

    /**
     * Agrega una reserva a la lista.
     * @param newReserva reserva a adicionar
     * @throws CityLogicException cuando ya existe una reserva con el id suministrado
     * @return reserva agregada
     */
    public ReservaDTO createReserva(ReservaDTO newReserva) throws CityLogicException {
    	logger.info("recibiendo peticion de agregar reserva " + newReserva);
    	
    	// la nueva reserva tiene id ?
    	if ( newReserva.getId() != null ) {
	    	// busca la reserva con el id suministrado
	        for (ReservaDTO reserva : reservas) {
	        	// si existe una reserva con ese id
	            if (Objects.equals(reserva.getId(), newReserva.getId())){
	            	logger.severe("Ya existe una reserva con ese id");
	                throw new CityLogicException("Ya existe una reserva con ese id");
	            }
	        }
	        
	    // la nueva reserva no tiene id ? 
    	} else {

    		// genera un id para la reserva
    		logger.info("Generando id paa la nueva reserva");
    		long newId = 1;
	        for (ReservaDTO reserva : reservas) {
	            if (newId <= reserva.getId()){
	                newId =  reserva.getId() + 1;
	            }
	        }
	        newReserva.setId(newId);
    	}
    	
        // agrega la reserva
    	logger.info("agregando reserva " + newReserva);
        reservas.add(newReserva);
        return newReserva;
    }

    public ReservaDTO getReserva(long id) throws CityLogicException {
        for(ReservaDTO reserva : reservas)
        {
            if(reserva.getId()==id)
                return reserva;
        }
        throw new CityLogicException(RESERVA_INEXISTENTE);
    }

    public ReservaDTO updateReserva(Long id, ReservaDTO newReserva) throws CityLogicException {
        for(ReservaDTO reserva : reservas)
        {
            if(Objects.equals(reserva.getId(), id))
            {
                reserva.setFecha(newReserva.getFecha());
                reserva.setEstado(newReserva.getEstado());
                return reserva;
            }     
        }
        throw new CityLogicException(RESERVA_INEXISTENTE);
    }

    public void deleteReserva(Long id) throws CityLogicException {
        for(ReservaDTO reserva : reservas)
        {
            if(reserva.getId()==id)
            {
                reservas.remove(reserva);
                return;
            }     
        }
        throw new CityLogicException(RESERVA_INEXISTENTE);
    }

       
    public ReservaDTO cancelarReserva(Long id) throws CityLogicException {
        for(ReservaDTO reserva : reservas)
        {
            if(reserva.getId()==id)
            {
                reserva.setEstado(ReservaDTO.RESERVA_CANCELADA);
                return reserva;
            }     
        }
        throw new CityLogicException(RESERVA_INEXISTENTE);
    }

}

