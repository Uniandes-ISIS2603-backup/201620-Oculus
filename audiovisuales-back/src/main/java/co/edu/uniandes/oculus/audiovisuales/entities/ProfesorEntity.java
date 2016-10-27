/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.oculus.audiovisuales.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

/**
 *
 * @author fa.lopez10
 */
@Entity
public class ProfesorEntity extends BaseEntity implements Serializable{
    private String login;
    private int codigo;
    @OneToMany(mappedBy = "profesor", cascade = CascadeType.ALL, orphanRemoval = true)
    private ArrayList<ReservaEntity> reservas;
    
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
    
    public ArrayList<ReservaEntity> getReservas()
    {
        return reservas;
    }
    
    public void setReservas(ArrayList<ReservaEntity> reservas)
    {
       this.reservas=reservas;
    }
    
    public ArrayList<ReservaEntity> getReservasRangoFechas(Date fecha1, Date fecha2)
    {
        ArrayList<ReservaEntity> reservasFech = new ArrayList<ReservaEntity>();
        for (ReservaEntity reserva : reservas) {
            if(reserva.getFecha().after(fecha1) && reserva.getFecha().before(fecha2))
                reservasFech.add(reserva);
        }
        return reservasFech;
    }
}
