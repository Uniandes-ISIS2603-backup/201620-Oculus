/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package co.edu.uniandes.rest.dtos;

import co.edu.uniandes.oculus.audiovisuales.entities.EquipoEntity;
import co.edu.uniandes.oculus.audiovisuales.entities.ReservaEntity;
import java.util.ArrayList;

/**
 *
 * @author gc.andrade10
 */
public class EquipoDetailDTO extends EquipoDTO
{
    private TipoDTO tipo;
    private ArrayList<ReservaDTO> reservas;
    private PuntoDeAtencionDTO puntoDeAtencion;
    public EquipoDetailDTO()
    {
        super();
    }
    public EquipoDetailDTO(EquipoEntity e)
    {
        super(e);
        if(e.getPuntoDeAtencion()!=null)
        {
        //this.puntoDeAtencion=PuntoDeAtencionDTO(e.getPuntoDeAtencion());
        }
        if(e.getReservas()!=null)
        {
            this.reservas=new  ArrayList<>();
            for (ReservaEntity r : e.getReservas()) 
            {
                 //this.reservas.add(ReservaDTO(r));            
            }
        }
        if(e.getTipo()!=null)
        {
        //this.tipo=TipoDTO(e.getTipo());
        }
        
    }
    
    @Override
    public EquipoEntity toEntity()
    {
    EquipoEntity entidad = super.toEntity();
    if(this.getPuntoDeAtencionDTO()!=null)
        {
            //entidad.setPuntoDeAtencion(this.getPuntoDeAtencionDTO()toEntity());
        }
        if(this.getReservas()!=null)
        {
          ArrayList<ReservaEntity> re = new ArrayList<ReservaEntity>();
            for (ReservaDTO r : this.getReservas()) 
            {
                //re.add(r.toEntity());
            }
                entidad.setReservas(re);
        }
        if(this.getTipo()!=null)
        {
        //this.tipo=TipoDTO(e.getTipo());
            entidad.setTipo(this.getTipo().toEntity());
        }
    return entidad;
    }
    
     /**
     * @return the reservas
     */
    public ArrayList<ReservaDTO> getReservas() {
        return reservas;
    }
    
    /**
     * @param reservas the reservas to set
     */
    public void setReservas(ArrayList<ReservaDTO> reservas)
    {
        this.reservas = reservas;
    }
    
     /**
     * @return tipo
     */
    public TipoDTO getTipo()
    {
        return tipo;
    }
    
    /**
     * @param tipo el tipo del equipo
     */
    public void setTipo(TipoDTO tipo)
    {
        this.tipo = tipo;
    }
    
     /**
     * @return tipo
     */
    public PuntoDeAtencionDTO getPuntoDeAtencionDTO()
    {
        return puntoDeAtencion;
    }
    
    /**
     * @param tipo el tipo del equipo
     */
    public void setTipo(PuntoDeAtencionDTO puntoDeAtencion)
    {
        this.puntoDeAtencion = puntoDeAtencion;
    }
    
}
