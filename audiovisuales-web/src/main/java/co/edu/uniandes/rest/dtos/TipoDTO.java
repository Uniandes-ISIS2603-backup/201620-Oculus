/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.dtos;

/**
 *
 * @author gc.andrade10
 */
public class TipoDTO 
{
    int id;
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
        
        public int getId()
    {
        return id;
    }
        public void setId(int pId)
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
}
