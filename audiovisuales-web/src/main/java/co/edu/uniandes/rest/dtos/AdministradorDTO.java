/*
 * AdministradorDTO
 * Objeto de transferencia de datos de Administrador.
 * Los DTO especifican los mensajes que se envían entre el cliente y el servidor.
 */
package co.edu.uniandes.rest.dtos;

import co.edu.uniandes.oculus.audiovisuales.entities.AdministradorEntity;
import co.edu.uniandes.rest.mocks.AdministradorLogicMock;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.annotation.XmlRootElement;


/**
 *Objeto de transferencia de datos de Administrador.
 * @author Sneider Velandia G
 */
@XmlRootElement
public class AdministradorDTO
{
    private String nombre;
    private Long id;
    private String login;
    //esto va en el detail y se borran sus set y get
    //private PuntoDeAtencionDTO puntoDeAtencion;
    
    //constructor 
    public AdministradorDTO()
    {}
    
    //private final static Logger logger = Logger.getLogger(AdministradorLogicMock.class.getName());
    
    //constructor con parametros se hacen modificaciones para que entre un ADMINENTITY 
    // se crea un objeto AdministradorDTO a partir de un objeto Administrador Entity
    public AdministradorDTO(AdministradorEntity adminEntity)
    {
        if(adminEntity != null)
        {
            this.nombre = adminEntity.getName();
            this.id = adminEntity.getId();
            this.login = adminEntity.getLogin();  
        }
    }
    
    //////METODOS .GET\\\\\\
    /**
     * se obtiene el nombre del administrador
     * @return nombre del admin
     */
    public String getNombre()
    {
        return nombre;
    }
    /**
     * se obtiene el id del administrador
     * @return id del admin
     */
    public Long getId()
    {
        return id;
    }
    /**
     * se obtiene el login del administrador
     * @return login del admin
     */
    public String getLogin()
    {
        return login;
    }
    
    //////METODOS .SET\\\\\\
    /**
     * asignarle el nombre al atributo nombre
     * @param nomb nombre a asignar
     */
    public void setNombre(String nomb)
    {
        this.nombre = nomb;
    }
    
    /**
     * asiganarle el id al atributo id
     * @param id id a asignar
     */
    public void setId(Long id)
    {
        this.id = id;
    }
    
    /**
     * asignarle el login al atributo log
     * @param log log a asignar
     */
    public void setLogin(String log)
    {
        this.login = log;
    }
    
    //////Convierte el objeto a una cadena\\\\\\
    @Override
    public String toString() 
    {
        String formato="{ nombre : " + getNombre() + ", id : \"" + getId()+ "\", login : \""+getLogin()+" }" ; 
    	//logger.setLevel(Level.INFO);
        //logger.info("toString:"+formato);
        return  formato;
    }
    
    /**
     * convierte un objeto AdministradorDTO a AdministradorEntity
     * @return retorna el AdministradorENTITY
     */
    public AdministradorEntity AdminDTOtoEntity()
    {
        AdministradorEntity adminEntity = new AdministradorEntity();
        //asigno el nombre a la entidad desde el dto
        adminEntity.setName(this.getNombre());
        //asigno el id a la entidad desde el dto
        adminEntity.setId(this.getId());
        //asigno el login a la entidad desde el dto
        adminEntity.setLogin(this.getLogin());
        
        return adminEntity;
    }

}
