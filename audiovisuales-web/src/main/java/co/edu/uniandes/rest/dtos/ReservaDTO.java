/*
* ReservaDTO
* Objeto de transferencia de datos de Reservas.
* Los DTO especifican los mensajes que se envían entre el cliente y el servidor.
*/
package co.edu.uniandes.rest.dtos;

import co.edu.uniandes.oculus.audiovisuales.entities.ReservaEntity;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Objeto de transferencia de datos de Reservas.
 * @author fa.lopez10
 */
@XmlRootElement
public class ReservaDTO {
    private Long id;
    private Date fecha;
    private String estado;
    
    public final static String RESERVA_CANCELADA = "Reserva Cancelada";
    public final static String RESERVA_APROBADA = "Reserva Aprobada";
    public final static String RESERVA_FINALIZADA = "Reserva Finalizada";
    public final static String RESERVA_ACTIVA = "Reserva Activa";
    /**
     * Constructor por defecto
     */
    public ReservaDTO() {
    }
    
    /**
     * Constructor con parámetros.
     * @param entity entidad de la que se toman los parametros del DTO
     */
    public ReservaDTO(ReservaEntity entity) {
        if(entity != null)
        {
            this.id = entity.getId();
            this.fecha = entity.getFecha();
            this.estado = entity.getEstado();
        }
    }
    
    public ReservaEntity toEntity()
    {
        ReservaEntity reservaNueva = new ReservaEntity();
        reservaNueva.setId(id);
        reservaNueva.setFecha(fecha);
        reservaNueva.setEstado(estado);
        return reservaNueva;
    }
    
    /**
     * @return el id
     */
    public Long getId() {
        return id;
    }
    
    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }
    
    /**
     * @return el estado
     */
    public String getEstado() {
        return estado;
    }
    
    /**
     * @param estado el estado a cambiar
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    /**
     * @return la fecha
     */
    public Date getFecha() {
        return fecha;
    }
    
    /**
     * @param fecha la fecha a cambiar
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
    /**
     * Convierte el objeto a una cadena
     * @return atributos del objeto como caracteres
     */
    @Override
    public String toString() {
        return "{ id : " + getId() + ", fecha : \"" + getFecha()+ ", estado : \"" + getEstado()+ "\" }" ;
    }
}
