/*
* EquipoDTO
* Objeto de transferencia de datos de Equipos.
* Los DTO especifican los mensajes que se envían entre el cliente y el servidor.
*/
package co.edu.uniandes.rest.dtos;


import co.edu.uniandes.oculus.audiovisuales.entities.EquipoEntity;
import java.util.logging.Logger;

/**
 * Objeto de transferencia de datos de Equipos.
 * @author German Andrade
 */
//objeto que representa los datos que vamos a enviar o recibir
//objeto json
public class EquipoDTO
{
    private String name;
    private Long id;
    private String caracteristicas;
    protected static TipoDTO [] tipos={new TipoDTO("Computador portatil",1), new TipoDTO("Audífonos",2), new TipoDTO("Cámara de video",3), new TipoDTO("Apuntador",4) };
    
    /**
     * Constructor por defecto
     */
    public EquipoDTO()
    {
        // No hace nada porque es el constructor vacío.
    }
    
    public EquipoDTO(EquipoEntity entidad)
    {
        if(entidad!=null)
        {
            this.name = entidad.getName();
            this.id=entidad.getId();
            this.caracteristicas=entidad.getCaracteristicas();
        }
    }
    
    public EquipoEntity toEntity()
    {
        EquipoEntity entidad = new EquipoEntity();
        entidad.setCaracteristicas(this.getCaracteristicas());
        entidad.setId(this.getId());
        entidad.setName(this.getName());
        return entidad;
    }
    
    
    /**
     * @return the tipos
     */
    public static TipoDTO[] getTipos()
    {
        return tipos;
    }
    
    /**
     * @param aTipos the tipos to set
     */
    public static void setTipos(TipoDTO[] aTipos)
    {
        tipos = aTipos;
    }
    
    /**
     * @return the logger
     */
    public static Logger getLogger()
    {
        return logger;
    }
    
    /**
     * @param aLogger the logger to set
     */
    public static void setLogger(Logger aLogger) {
        logger = aLogger;
    }
    
    
    private static Logger logger = Logger.getLogger(AdministradorDTO.class.getName());
    
    
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
     * @return the name
     */
    public String getName() {
        return name;
    }
    
    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() 
    {
        return "Name: "+name +" id: "+id;
    }
    
    
}