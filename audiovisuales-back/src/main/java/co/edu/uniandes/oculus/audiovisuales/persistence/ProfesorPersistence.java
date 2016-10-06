/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.oculus.audiovisuales.persistence;

import javax.ejb.Stateless;
import javax.persistence.PersistenceContext;

/**
 *
 * @author fa.lopez10
 */
@Stateless
public class ProfesorPersistence {
    
    
    @PersistenceContext(unitName = "OculusPU")
    protected EntityManager em;
    
    public ProfesorEntity find(Long id) {
        LOGGER.log(Level.INFO)
    }
    
}
