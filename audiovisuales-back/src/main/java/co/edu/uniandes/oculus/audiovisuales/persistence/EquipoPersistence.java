/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.oculus.audiovisuales.persistence;

import co.edu.uniandes.oculus.audiovisuales.entities.ReservaEntity;
import co.edu.uniandes.oculus.audiovisuales.entities.TipoEntity;
import java.util.ArrayList;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author gc.andrade10
 */
@Stateless
public class EquipoPersistence 
{
 
    @PersistenceContext(unitName = "OculusPU")
    protected EntityManager em;
    
    
}
