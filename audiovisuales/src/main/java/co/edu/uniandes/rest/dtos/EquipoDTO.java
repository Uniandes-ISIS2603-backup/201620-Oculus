/*
 * EquipoDTO
 * Objeto de transferencia de datos de Equipos.
 * Los DTO especifican los mensajes que se envían entre el cliente y el servidor.
 */
package co.edu.uniandes.rest.dtos;


import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Objeto de transferencia de datos de Equipos.
 * @author German Andrade
 */
//objeto que representa los datos que vamos a enviar o recibir 
//objeto json
    public class EquipoDTO 
{
    private Long id;
    private String tipo;
    private String caracteristicas;
    
    public static final String [] tipos={"Computador portatil", "Audífonos", "Cámara de video", "Apuntador" };
   

    /**
     * Constructor por defecto
     */
    public EquipoDTO() 
    {
    }
    private final static Logger logger = Logger.getLogger(AdministradorDTO.class.getName());

    /**
     * Constructor con parámetros.
     * @param disponibilidad true si está disponible, false de lo contrario
     * @param id identificador del equipo
     * @param tipo tipo del equipo
     * @param descripcion descripción del equipo
     */
    public EquipoDTO(Long id, String tipo,String pCaracteristicas) 
    {
		super();
		this.id = id;
		this.tipo = tipo; 
                this.caracteristicas = pCaracteristicas; 
          

	}

    /**
     * @return the id
     */
    public Long getId() 
    {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) 
    {
        this.id = id;
    }

    /**
     * @return tipo
     */
    public String getTipo() 
    {
        return tipo;
    }

    /**
     * @param tipo el tipo del equipo
     */
    public void setTipo(String tipo) 
    {
        this.tipo = tipo;
    }
    
        /**
     * @return tipo
     */
    public String getCaracteristicas() 
    {
        return caracteristicas;
    }

    /**
     * @param tipo el tipo del equipo
     */
    public void setCaracteristicas(String tipo1) 
    {
        this.caracteristicas = tipo1;
    }
    
    
    /**
     * Convierte el objeto a una cadena
     * @return 
     */
    @Override
    public String toString() 
    {
        String formato="{ id : " + getId() + ", tipo : \"" + getTipo() + "\", descripcion : \""+getCaracteristicas()+" }" ; 
    	logger.setLevel(Level.INFO);
        logger.info("toStrimg:"+formato);
        return  formato;
        
    }
    
}
