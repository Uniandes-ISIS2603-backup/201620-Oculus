/*
 * EquipoDTO
 * Objeto de transferencia de datos de Equipos.
 * Los DTO especifican los mensajes que se envían entre el cliente y el servidor.
 */
package co.edu.uniandes.rest.dtos;


import java.util.ArrayList;
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

    /**
     * @return the tipos
     */
    public static TipoDTO[] getTipos() {
        return tipos;
    }

    /**
     * @param aTipos the tipos to set
     */
    public static void setTipos(TipoDTO[] aTipos) {
        tipos = aTipos;
    }

    /**
     * @return the logger
     */
    public static Logger getLogger() {
        return logger;
    }

    /**
     * @param aLogger the logger to set
     */
    public static void setLogger(Logger aLogger) {
        logger = aLogger;
    }
    private Long id;
    private TipoDTO tipo;
    private String caracteristicas;
    private ArrayList<ReservaDTO> reservas;
    private PuntoDeAtencionDTO puntoDeAtencion; 
    
    protected static TipoDTO [] tipos={new TipoDTO("Computador portatil",1), new TipoDTO("Audífonos",2), new TipoDTO("Cámara de video",3), new TipoDTO("Apuntador",4) };
   

    /**
     * Constructor por defecto
     */
    public EquipoDTO() 
    {
    // No hace nada porque es el constructor vacío.
    }
    private static Logger logger = Logger.getLogger(AdministradorDTO.class.getName());

    /**
     * Constructor con parámetros.
     * @param disponibilidad true si está disponible, false de lo contrario
     * @param id identificador del equipo
     * @param tipo tipo del equipo
     * @param descripcion descripción del equipo
     */
    public EquipoDTO(Long id, TipoDTO tipo,String pCaracteristicas, PuntoDeAtencionDTO puntoDeAtencion ) 
    {
		super();
		this.id = id;
		this.tipo = tipo; 
                this.caracteristicas = pCaracteristicas; 
                this.reservas = new ArrayList();
                this.puntoDeAtencion=puntoDeAtencion;

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
    public TipoDTO getTipo() 
    {
        return tipo;
    }

    /**
     * @param tipo el tipo del equipo
     */
    public void setTipo(TipoDTO tipo) 
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
    	getLogger().setLevel(Level.INFO);
        getLogger().info("toStrimg:"+formato);
        return  formato;
        
    }

    /**
     * @return the reservas
     */
    public ArrayList<ReservaDTO> getReservas() {
        return reservas;
    }

    /**
     * @param reservas the reservas to set
     */
    public void setReservas(ArrayList<ReservaDTO> reservas) {
        this.reservas = reservas;
    }
    
    
    
}