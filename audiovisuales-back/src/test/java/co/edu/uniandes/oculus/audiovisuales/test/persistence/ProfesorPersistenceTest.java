/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.oculus.audiovisuales.test.persistence;

import co.edu.uniandes.oculus.audiovisuales.entities.ProfesorEntity;
import co.edu.uniandes.oculus.audiovisuales.persistence.ProfesorPersistence;
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
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author fa.lopez10
 */
@RunWith(Arquillian.class)
public class ProfesorPersistenceTest {
    @Deployment
    public static JavaArchive createDeployment()
    {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ProfesorEntity.class.getPackage())
                .addPackage(ProfesorPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml","beans.xml");
    }
    
    @Inject
    private ProfesorPersistence profesorPersistence;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    UserTransaction utx;
    
    private List<ProfesorEntity> data = new ArrayList<ProfesorEntity>();
    
    /**
     * Configuración inicial de la prueba.
     */
    @Before
    public void setUp() {
        try {
            utx.begin();
            em.joinTransaction();
            clearData();
            insertData();
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }
    
    /**
     * Limpia las tablas que están implicadas en la prueba.
     */
    private void clearData() {
        em.createQuery("delete from ReservaEntity").executeUpdate();
        em.createQuery("delete from ProfesorEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     *
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            ProfesorEntity entity = factory.manufacturePojo(ProfesorEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }
    
    /**
     * Prueba para crear un Profesor.
     */
    @Test
    public void createProfesorTest() {
        PodamFactory factory = new PodamFactoryImpl();
        ProfesorEntity newEntity = factory.manufacturePojo(ProfesorEntity.class);

        ProfesorEntity result = profesorPersistence.create(newEntity);

        Assert.assertNotNull(result);
        ProfesorEntity entity = em.find(ProfesorEntity.class, result.getId());
        Assert.assertNotNull(entity);
        Assert.assertEquals(newEntity.getName(), entity.getName());
    }
    
    /**
     * Prueba para consultar la lista de Profesores.
     *
     *
     */
    @Test
    public void getProfesoresTest() {
        List<ProfesorEntity> list = profesorPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (ProfesorEntity ent : list) {
            boolean found = false;
            for (ProfesorEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar un Profesor.
     */
    @Test
    public void getProfesorTest() {
        ProfesorEntity entity = data.get(0);
        ProfesorEntity newEntity = profesorPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getName(), newEntity.getName());
    }
    
    /**
     * Prueba para consultar un profesor segun su nombre
     */
    @Test
    public void getProfesorByNameTest1() {
        ProfesorEntity entity = data.get(0);
        ProfesorEntity newEntity = profesorPersistence.findByName(entity.getName());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getName(), newEntity.getName());
    }
    
    /**
     * Prueba para eliminar un Profesor.
     */
    @Test
    public void deleteProfesorTest() {
        ProfesorEntity entity = data.get(0);
        profesorPersistence.delete(entity.getId());
        ProfesorEntity deleted = em.find(ProfesorEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar un Profesor.
     */
    @Test
    public void updateProfesorTest() {
        ProfesorEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        ProfesorEntity newEntity = factory.manufacturePojo(ProfesorEntity.class);

        newEntity.setId(entity.getId());

        profesorPersistence.update(newEntity);

        ProfesorEntity resp = em.find(ProfesorEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getName(), resp.getName());
    }
    
}
