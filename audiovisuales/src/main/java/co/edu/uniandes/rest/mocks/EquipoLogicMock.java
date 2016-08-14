package co.edu.uniandes.rest.mocks;

/**
 * Mock del recurso Equipos (Mock del servicio REST)
 * @author German Andrade
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

//Preguntas foros sicua


import co.edu.uniandes.rest.dtos.EquipoDTO;
import co.edu.uniandes.rest.exceptions.CityLogicException;

/*
 * EquipoLogicMock
 * Mock del recurso Equipos (Mock del servicio REST)
 */

public class EquipoLogicMock 
{
	
// objeto para presentar logs de las operaciones
	
private final static Logger logger = Logger.getLogger(EquipoLogicMock.class.getName());
	
	// listado de equipos
    private static ArrayList<EquipoDTO> equipos;
    //ArrayList de Equipos

    /**
     * Constructor. Crea los datos de ejemplo.
     */
    public EquipoLogicMock() {

    	if (equipos == null) {
            equipos = new ArrayList<>();
            equipos.add(new EquipoDTO(1L, "computador", true, "toshiba"));
            equipos.add(new EquipoDTO(2L, "audifonos", true, "panasonic"));
            equipos.add(new EquipoDTO(3L, "computador", true, "Dell"));
        }
        
    	// indica que se muestren todos los mensajes
    	logger.setLevel(Level.INFO);
    	
    	// muestra información 
    	logger.info("Inicializa la lista de equipos");
    	logger.info("equipos" + equipos );
    }    
    
	/**
	 * Obtiene el listado de equipos. 
	 * @return lista de equipos
	 * @throws CityLogicException cuando no existe la lista en memoria  
	 */    
    public List<EquipoDTO> getEquipos() throws CityLogicException {
    	if (equipos == null) {
    		logger.severe("Error interno: lista de equipos no existe.");
    		throw new CityLogicException("Error interno: lista de equipos no existe.");    		
    	}
    	
    	logger.info("retornando todos los equipos");
    	return equipos;
    }

 

    /**
     * Agrega un equipo a la lista.
     * @param newEquipo equipo a adicionar
     * @throws CityLogicException cuando ya existe un equipo con el id suministrado
     * @return equipo agregado
     */
    public EquipoDTO createEquipo(EquipoDTO newEquipo) throws CityLogicException {
    	logger.info("recibiendo solicitud de agregar equipo " + newEquipo);
    	
    	// el nuevo equipo tiene id ?
    	if ( newEquipo.getId() != null ) {
	    	// busca la ciudad con el id suministrado
	        for (EquipoDTO equipo : equipos) {
	        	// si existe una ciudad con ese id
	            if (Objects.equals(equipo.getId(), newEquipo.getId())){
	            	logger.severe("Ya existe un equipo con ese id");
	                throw new CityLogicException("Ya existe un equipo con ese id");
	            }
	        }
	        
	    // el nuevo equipo no tiene id ? 
    	} else {

    		// genera un id para el equipo
    		logger.info("Generando id para el nuevo equipo t");
    		long newId = 1;
	        for (EquipoDTO equipo : equipos) {
	            if (newId <= equipo.getId()){
	                newId =  equipo.getId() + 1;
	            }
	        }
	        newEquipo.setId(newId);
    	}
    	
        // agrega el equipo
    	logger.info("agregando equipo " + newEquipo);
        equipos.add(newEquipo);
        return newEquipo;
    }
    //Completar este 
    //y crear el propio
    
     /**
     * Actualiza la informaciÃ³n del equipo identificado con id con la informaciÃ³n pasada por parÃ¡metro en equipo
     * @param id id de la ciudad a modificar
     * @param equipo nuevos datos de la ciudad
     * @return ciudad modificada
     */
    public EquipoDTO updateEquipo(Long id, EquipoDTO equipo) throws CityLogicException 
    {
         boolean encontrado =false;
         EquipoDTO actualEquipo = null;
   for(int i = 0; i<equipos.size() && !encontrado ; i++)
   {
       actualEquipo = ((EquipoDTO)equipos.get(i));
       if(actualEquipo.getId()==id)
       {
           encontrado = true;
           
       }
   }
   if(!encontrado)
   {
       logger.severe("No existe un equipo con ese id");
	                throw new CityLogicException("No existe un equipo con ese id");
   }
    	actualEquipo.setTipo(equipo.getTipo());
        actualEquipo.setId(equipo.getId());
        actualEquipo.setCaracteristicas(equipo.getCaracteristicas());
        actualEquipo.setDisponibilidad(equipo.getDisponibilidad());
    	logger.info("retornando el equipo "+actualEquipo);
    	return actualEquipo;
    }
 
     /**
     *Borra el equipo identificado con el id dado
     * @param id id del equipo a eliminar
     */
    public void deleteEquipo(Long id) throws CityLogicException 
    {
         boolean encontrado = false;
         EquipoDTO actualEquipo = null;
        for(int i = 0; i<equipos.size() && !encontrado ; i++)
   {
       actualEquipo = ((EquipoDTO)equipos.get(i));
       if(actualEquipo.getId()==id)
       {
           equipos.remove(i);
           encontrado=true;
           
       }
   }
        if(!encontrado)
        {
            logger.severe("No existe un equipo con ese id");
	                throw new CityLogicException("No existe un equipo con ese id");
        }

    }
    
    /**
	 * Obtiene los atributos de una instancia de equipo con el id dado
	 * @return Equipo
	 * @throws CityLogicException cuando no existe la lista en memoria  
	 */    
    public EquipoDTO getEquipo( Long id) throws CityLogicException 
    {
        boolean encontrado =false;
         EquipoDTO actualEquipo = null;
   for(int i = 0; i<equipos.size() && !encontrado ; i++)
   {
       actualEquipo = ((EquipoDTO)equipos.get(i));
       if(actualEquipo.getId()==id)
       {
           encontrado = true;
         
       }
   }
   if(!encontrado)
   {
       logger.severe("No existe una ciudad con ese id");
	                throw new CityLogicException("No existe una ciudad con ese id");
   }
    	
    	logger.info("retornando la ciudad "+actualEquipo);
    	return actualEquipo;
    }



   
}
