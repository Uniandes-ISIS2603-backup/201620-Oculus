package co.edu.uniandes.oculus.audiovisuales.entities;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 * @author Sneider Velandia G
 */
@Entity
public class AdministradorEntity extends BaseEntity implements Serializable
{
    //atributo que modela un login
    private String login;
    
    //atributo que representa el punto de atencion que pertenece un administrador
    @PodamExclude
    @ManyToOne
    private PuntoDeAtencionEntity puntoDeAtencion;
    
    /**
     * dar el punto de atencion al que pertenece un administrador
     * @return punto de atencion al que pertenece el administrador 
     */
    public PuntoDeAtencionEntity getPuntoDeAtencion()
    {
        return puntoDeAtencion;
    }
    
    /**
     * asiganar un punto de atencion a un administrador
     * @param pda punto de atencion a asignar
     */
    public void setPuntoDeAtencion(PuntoDeAtencionEntity pda)
    {
        puntoDeAtencion=pda;
    }

    /**
     * retorna el login del administrador
     * @return login del administrador
     */
    public String getLogin() 
    {
        return login;
    }

    /**
     * asigna un login al atributo login
     * @param login login a agregar
     */
    public void setLogin(String login) 
    {
        this.login = login;
    }
}
