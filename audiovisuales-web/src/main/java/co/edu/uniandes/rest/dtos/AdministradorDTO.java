/*
 * AdministradorDTO
 * Objeto de transferencia de datos de Administrador.
 * Los DTO especifican los mensajes que se envían entre el cliente y el servidor.
 */
package co.edu.uniandes.rest.dtos;

import co.edu.uniandes.rest.mocks.AdministradorLogicMock;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *Objeto de transferencia de datos de Administrador.
 * @author Sneider Velandia G
 */
public class AdministradorDTO
{
    private String nombre;
    private Long id;
    private String login;
    private PuntoDeAtencionDTO puntoDeAtencion;
    
    //constructor 
    public AdministradorDTO(){}
    
    private final static Logger logger = Logger.getLogger(AdministradorLogicMock.class.getName());
    
    //constructor con parametros
    public AdministradorDTO(String nomb, Long id, String log)
    {
        super();
        this.nombre = nomb;
        this.id = id;
        this.login = log;
    }
    
    //METODOS .GET
    public String getNombre()
    {
        return nombre;
    }
    public Long getId()
    {
        return id;
    }
    public String getLogin()
    {
        return login;
    }
    public PuntoDeAtencionDTO getPuntoDeAtencion()
    {
        return puntoDeAtencion;
    }
    
    //METODOS .SET
    public void setNombre(String nomb)
    {
        this.nombre = nomb;
    }
    public void setId(Long id)
    {
        this.id = id;
    }
    public void setLogin(String log)
    {
        this.login = log;
    }
    public void setPuntoDeAtencion(PuntoDeAtencionDTO pda)
    {
        this.puntoDeAtencion = pda;
    }
    //Convierte el objeto a una cadena
    @Override
    public String toString() 
    {
        String formato="{ nombre : " + getNombre() + ", id : \"" + getId()+ "\", login : \""+getLogin()+" }" ; 
    	logger.setLevel(Level.INFO);
        logger.info("toString:"+formato);
        return  formato;
    }

}
