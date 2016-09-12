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
    private int codigo;
    private String nombre;
    private String login;
    private PuntoDeAtencionDTO puntoDeAtencion;
    //constructor 
    public AdministradorDTO(){}
    
    private final static Logger logger = Logger.getLogger(AdministradorLogicMock.class.getName());
    
    //constructor con parametros
    public AdministradorDTO(String nomb, Integer cod, String log, PuntoDeAtencionDTO pPunto)
    {
        super();
        this.nombre = nomb;
        this.codigo = cod;
        this.login = log;
        puntoDeAtencion = pPunto;
    }
    
    
    //METODOS .GET
    public String getNombre()
    {
        return nombre;
    }
    public int getCodigo()
    {
        return codigo;
    }
    public String getLogin()
    {
        return login;
    }
        public PuntoDeAtencionDTO getPuntoDeAtencionDTO()
    {
        return puntoDeAtencion;
    }
    
    //METODOS .SET
    public void setNombre(String nomb)
    {
        this.nombre = nomb;
    }
    public void setCodigo(int cod)
    {
        this.codigo = cod;
    }
    public void setLogin(String log)
    {
        this.login = log;
    }
    public void setPuntoDeAtencionDTO(PuntoDeAtencionDTO pPunto)
    {
        this.puntoDeAtencion = pPunto;
    }
    
    //Convierte el objeto a una cadena
    @Override
    public String toString() 
    {
        String formato="{ nombre : " + getNombre() + ", codigo : \"" + getCodigo() + "\", login : \""+getLogin()+" }" ; 
    	logger.setLevel(Level.INFO);
        logger.info("toStrimg:"+formato);
        return  formato;
    }

}
