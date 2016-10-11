/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.oculus.audiovisuales.test.persistence;


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
import org.junit.runner.RunWith;
import co.edu.uniandes.oculus.audiovisuales.entities.PuntoDeAtencionEntity;
import co.edu.uniandes.oculus.audiovisuales.persistence.PuntoDeAtencionPersistence;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author am.espinosa11
 */

@RunWith(Arquillian.class)
public class PuntoDeAtencionPesistenceTest 
{
     @Deployment
    public static JavaArchive createDeployment() {
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
            PuntoDeAtencionEntity entity= factory.manufacturePojo(PuntoDeAtencionEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }
    
}
