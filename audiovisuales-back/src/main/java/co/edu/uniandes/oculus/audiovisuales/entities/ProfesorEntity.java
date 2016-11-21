/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.oculus.audiovisuales.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author fa.lopez10
 */
@Entity
public class ProfesorEntity extends BaseEntity implements Serializable{
    private String login;
    private int codigo;
    
    @OneToMany(mappedBy = "profesor", cascade = CascadeType.ALL, orphanRemoval = true) 
    private List<ReservaEntity> reservas;
    
    public String getLogin()
    {
        return login;
    }
    
    public void setLogin(String login)
    {
        this.login=login;
    }
    
    public int getCodigo()
    {
        return codigo;
    }
    
    public void setCodigo(int codigo)
    {
        this.codigo=codigo;
    }
    
    public List<ReservaEntity> getReservas()
    {
        return reservas;
    }
    
    public void setReservas(List<ReservaEntity> reservas)
    {
       this.reservas=reservas;
    }
    
    public List<ReservaEntity> getReservasRangoFechas(Date fecha1, Date fecha2)
    {
        List<ReservaEntity> reservasFech = new ArrayList<ReservaEntity>();
        for (ReservaEntity reserva : reservas) {
            if(reserva.getFecha().after(fecha1) && reserva.getFecha().before(fecha2))
                reservasFech.add(reserva);
        }
        return reservasFech;
    }
}
