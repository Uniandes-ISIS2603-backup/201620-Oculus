/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.dtos;


import java.util.logging.Logger;
import co.edu.uniandes.rest.mocks.LocacionLogicMock;
import java.util.logging.Level;


/**
 *
 * @author am.espinosa11
 */
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
public class LocacionDTO 
{
    private String ubicacion;
    
    /**
     * Constructor por defecto
     */
    public LocacionDTO()
    {
        
    }
    /**
     * Constructor 
     * @param pUbicacion edificio de ubicacion
     */
    public LocacionDTO(String pUbicacion) 
    {
      super();
      this.ubicacion = pUbicacion;
    }
    
     private final static Logger logger = Logger.getLogger(LocacionLogicMock.class.getName());
     
     
     /**
     * @return ubicacion
     */
     public String getUbicacion()
     {
         return ubicacion;
     }
     
     /**
     * @param ubicacion en la cual se encuentra
     */
     public void setUbicacion(String pUbicacion)
     {
         this.ubicacion = pUbicacion;
     }
     
     /**
     * Convierte el objeto a una cadena
     * @return 
     */
    @Override
    public String toString() 
    {
        String formato="{ ubicacion : " + getUbicacion() +"}" ; 
    	logger.setLevel(Level.INFO);
        logger.info("toStrimg:"+ formato);
        return  formato;
        
    }
}
