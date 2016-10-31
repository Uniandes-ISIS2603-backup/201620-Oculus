/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.dtos;

import co.edu.uniandes.oculus.audiovisuales.entities.EquipoEntity;
import co.edu.uniandes.oculus.audiovisuales.entities.TipoEntity;

/**
 *
 * @author gc.andrade10
 */
public class TipoDTO 
{
    long id;
    String nombre;
    
    public TipoDTO()
    {
        // No hace nada porque es el constructor vacío.
    }
    
    public TipoDTO(String pNombre,int pId)
    {
        super();
        nombre=pNombre;
        id = pId;
    }
    
    public String getNombre()
    {
        return nombre;
    }
        public void setNombre(String nomb)
    {
        this.nombre = nomb;
    }
        
        public long getId()
    {
        return id;
    }
        public void setId(long pId)
    {
        this.id = pId;
    }
    
    //Convierte el objeto a una cadena
    @Override
    public String toString() 
    {
        String formato="{ nombre : " + getNombre() +" }" ; 

        return  formato;
    }

    TipoEntity toEntity() 
    {
        TipoEntity e = new TipoEntity();
        e.setId(this.getId());
        e.setName(this.getNombre());
        return e;
    }
}
