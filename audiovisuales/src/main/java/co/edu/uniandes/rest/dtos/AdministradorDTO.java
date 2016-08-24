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
    private int codigo;
    private String login;
    
    //constructor 
    public AdministradorDTO(){}
    
    private final static Logger logger = Logger.getLogger(AdministradorLogicMock.class.getName());
    
    //constructor con parametros
    public AdministradorDTO(String nomb, Integer cod, String log)
    {
        super();
        this.nombre = nomb;
        this.codigo = cod;
        this.login = log;
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
