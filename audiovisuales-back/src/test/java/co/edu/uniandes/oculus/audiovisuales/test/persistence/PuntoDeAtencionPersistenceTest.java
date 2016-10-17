/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.oculus.audiovisuales.test.persistence;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import co.edu.uniandes.oculus.audiovisuales.entities.PuntoDeAtencionEntity;
import co.edu.uniandes.oculus.audiovisuales.persistence.PuntoDeAtencionPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;
/**
 *
 * @author am.espinosa11
 */
@RunWith(Arquillian.class)
public class PuntoDeAtencionPersistenceTest 
{
    @Deployment
    public static JavaArchive createDeployment()
    {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(PuntoDeAtencionEntity.class.getPackage())
                .addPackage(PuntoDeAtencionPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    @Inject
    private PuntoDeAtencionPersistence puntoDeAtencionPersistence;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    UserTransaction utx;
    
    private List<PuntoDeAtencionEntity> data = new ArrayList<PuntoDeAtencionEntity>();
    
    public PuntoDeAtencionPersistenceTest() 
    {
        
    }
    
    @BeforeClass
    public static void setUpClass() 
    {
        
    }
    
    @AfterClass
    public static void tearDownClass() 
    {
        
    }
   
    @Before
    public void setUp() 
    {
        try {
            utx.begin();
            em.joinTransaction();
            clearData();
            insertData();
            utx.commit();
            
        } catch (Exception e) 
        {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (Exception ea)
            {
             ea.printStackTrace();
             //fail("Falla de configuración");
            }
        } 
    }
    
     private void clearData() 
     {
        em.createQuery("DELETE FROM EquipoEntity").executeUpdate();
        em.createQuery("DELETE FROM PuntoDeAtencionEntity").executeUpdate();
        em.createQuery("DELETE FROM AdministradorEntity").executeUpdate();
    }
     
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            PuntoDeAtencionEntity entity = factory.manufacturePojo(PuntoDeAtencionEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }
 
    @After
    public void tearDown() 
    {
        
    }

    /**
     * Test of find method, of class PuntoDeAtencionPersistence.
     */
    @Test
    public void testFind() throws Exception
    {
        PuntoDeAtencionEntity entidad = data.get(0);
        PuntoDeAtencionEntity nuevaEntidad = puntoDeAtencionPersistence.find(entidad.getId());
        Assert.assertNotNull(nuevaEntidad);
       
        Assert.assertEquals(entidad.getId(), nuevaEntidad.getId());
        Assert.assertEquals(entidad.getUbicacion(), nuevaEntidad.getUbicacion());
        //fail("testFind");
    }

    /**
     * Test of findAll method, of class PuntoDeAtencionPersistence.
     */
    @Test
    public void testFindAll() throws Exception 
    {
         List<PuntoDeAtencionEntity> list = puntoDeAtencionPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (PuntoDeAtencionEntity ent : list) {
            boolean found = false;
            for (PuntoDeAtencionEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }

      //  fail("testFindAll");
    }

    /**
     * Test of create method, of class PuntoDeAtencionPersistence.
     */
    @Test
    public void testCreate() throws Exception 
    {
        PodamFactory factory = new PodamFactoryImpl();
        PuntoDeAtencionEntity newEntity = factory.manufacturePojo(PuntoDeAtencionEntity.class);

        PuntoDeAtencionEntity result = puntoDeAtencionPersistence.create(newEntity);

        Assert.assertNotNull(result);
        PuntoDeAtencionEntity entity = em.find(PuntoDeAtencionEntity.class, result.getId());
        Assert.assertNotNull(entity);
        Assert.assertEquals(newEntity.getName(), entity.getName());
        Assert.assertEquals(entity.getUbicacion(), newEntity.getUbicacion());
        //fail("testCreate");
    }

    /**
     * Test of delete method, of class PuntoDeAtencionPersistence.
     */
    @Test
    public void testDelete() throws Exception 
    {
        PuntoDeAtencionEntity entity = data.get(0);
        puntoDeAtencionPersistence.delete(entity.getId());
        PuntoDeAtencionEntity deleted = em.find(PuntoDeAtencionEntity.class, entity.getId());
        Assert.assertNull(deleted);
        //fail("testDelete");
    }

    /**
     * Test of update method, of class PuntoDeAtencionPersistence.
     */
    @Test
    public void testUpdate() throws Exception 
    {
        PuntoDeAtencionEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        PuntoDeAtencionEntity newEntity = factory.manufacturePojo(PuntoDeAtencionEntity.class);

        newEntity.setId(entity.getId());

        puntoDeAtencionPersistence.update(newEntity);

        PuntoDeAtencionEntity resp = em.find(PuntoDeAtencionEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getName(), resp.getName());
        Assert.assertEquals(newEntity.getUbicacion(), resp.getUbicacion());
        //fail("testUpdate");
    }
    
}
