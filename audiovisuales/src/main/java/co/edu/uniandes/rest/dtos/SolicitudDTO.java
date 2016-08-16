/*
 * SolicitudDTO
 * Objeto de transferencia de datos de Solicitudes.
 * Los DTO especifican los mensajes que se envían entre el cliente y el servidor.
 */
package co.edu.uniandes.rest.dtos;

import java.util.Date;

/**
 * Objeto de transferencia de datos de Solicitudes.
 * @author fa.lopez10
 */
public class SolicitudDTO {
    private Long id;
    private Date fecha;
    private String tipo;
    private String estado;

    /**
     * Constructor por defecto
     */
    public SolicitudDTO() {
	}

    /**
     * Constructor con parámetros.
     * @param id identificador de la solicitud
     * @param estado estado de la solicitud
     * @param fecha fecha en que se hace la solicitud
     * @param tipo tipo de solicitud que se realiza
     */
    public SolicitudDTO(Long id, Date fecha, String tipo, String estado) {
		super();
		this.id = id;
		this.fecha = fecha;
                this.tipo = tipo;
                this.estado = estado;
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
     * @return el tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @param tipo el tipo a cambiar
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    /**
     * @return el fecha
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
    	return "{ id : " + getId() + ", fecha : \"" + getFecha()+ ", tipo : \"" + getTipo()+ ", estado : \"" + getEstado()+ "\" }" ;  
    }
}
