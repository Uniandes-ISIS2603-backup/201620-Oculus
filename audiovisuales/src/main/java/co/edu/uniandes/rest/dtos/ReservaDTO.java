/*
 * ReservaDTO
 * Objeto de transferencia de datos de Reservas.
 * Los DTO especifican los mensajes que se envían entre el cliente y el servidor.
 */
package co.edu.uniandes.rest.dtos;

import java.util.Date;

/**
 * Objeto de transferencia de datos de Reservas.
 * @author fa.lopez10
 */
public class ReservaDTO {
    private Long id;
    private Date fecha;
    private String tipo;
    private String estado;

    /**
     * Constructor por defecto
     */
    public ReservaDTO() {
	}

    /**
     * Constructor con parámetros.
     * @param id identificador de la reserva
     * @param estado estado de la reserva
     * @param fecha fecha en que se hace la reserva
     * @param tipo tipo de reserva que se realiza
     */
    public ReservaDTO(Long id, Date fecha, String tipo, String estado) {
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
