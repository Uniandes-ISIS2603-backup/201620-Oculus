/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.oculus.audiovisuales.test.persistence;

import co.edu.uniandes.oculus.audiovisuales.entities.EquipoEntity;
import co.edu.uniandes.oculus.audiovisuales.persistence.EquipoPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author gc.andrade10
 */
@RunWith(Arquillian.class)
public class EquipoPersistenceTest 
{
    
    @Deployment
    public static JavaArchive createDeployment()
    {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(EquipoEntity.class.getPackage())
                .addPackage(EquipoPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml","beans.xml");
    }
    
    @Inject
    private EquipoPersistence equipoPersistence;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    UserTransaction utx;
    
    private List<EquipoEntity> data = new ArrayList<EquipoEntity>();
    
    public EquipoPersistenceTest() 
    {
        
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() 
    {
        try 
        {
            utx.begin();
            em.joinTransaction();
            clearData();
            insertData();
            utx.commit();
              
        } 
        catch (Exception e) 
        {
            try 
            {
                utx.rollback();
            } 
            catch (Exception e1) 
            {
                e1.printStackTrace();
                fail("Configuración en la base de datos falló");
            }
        }
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of find method, of class EquipoPersistence.
     */
    @Test
    public void testFind() throws Exception 
    {
        fail("testFind");
    }

    /**
     * Test of create method, of class EquipoPersistence.
     */
    @Test
    public void testCreate() throws Exception 
    {
        fail("testCreate");
    }

    private void clearData() 
    {
        em.createQuery("DELETE FROM EquipoEntity").executeUpdate();
        em.createQuery("DELETE FROM PuntoDeAtencionEntity").executeUpdate();
        em.createQuery("DELETE FROM ReservaEntity").executeUpdate();
    }

    private void insertData() 
    {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) 
        {
            EquipoEntity entity= factory.manufacturePojo(EquipoEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }
    
}
