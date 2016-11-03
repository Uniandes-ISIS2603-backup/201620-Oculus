/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package co.edu.uniandes.oculus.audiovisuales.test.persistence;

import co.edu.uniandes.oculus.audiovisuales.entities.EquipoEntity;
import co.edu.uniandes.oculus.audiovisuales.entities.PuntoDeAtencionEntity;
import co.edu.uniandes.oculus.audiovisuales.persistence.EquipoPersistence;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import org.junit.Assert;
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
    private static final Logger LOGGER = Logger.getLogger(EquipoPersistence.class.getName());
    
    
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
            e.printStackTrace();
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
    @Test
    public void getEquiposTest()
    {
        List<EquipoEntity> list = equipoPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (EquipoEntity equipoList : list)
        {
            boolean found = false;
            for (EquipoEntity equipoData : data)
            {
                if (equipoData.getId().equals(equipoList.getId()))
                {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
    /**
     * Test of create method, of class EquipoPersistence.
     */
    @Test
    public void createEquipoTest() throws Exception
    {
        
        PodamFactory factory = new PodamFactoryImpl();
        EquipoEntity nuevaEntidad = factory.manufacturePojo(EquipoEntity.class);
        EquipoEntity resultado = equipoPersistence.create(nuevaEntidad);
        Assert.assertNotNull(resultado);
        
        EquipoEntity entidad = equipoPersistence.find(resultado.getId());
        Assert.assertEquals(nuevaEntidad.getName(), entidad.getName());
        Assert.assertEquals(nuevaEntidad.getCaracteristicas(), entidad.getCaracteristicas());
        Assert.assertEquals(nuevaEntidad.getPuntoDeAtencion(), entidad.getPuntoDeAtencion());
        
        
    }
    
    @Test
    public void getEquipoTest()
    {
        EquipoEntity entidad = data.get(0);
        EquipoEntity nuevaEntidad = equipoPersistence.find(entidad.getId());
        Assert.assertNotNull(nuevaEntidad);
        Assert.assertEquals(entidad.getCaracteristicas(),nuevaEntidad.getCaracteristicas());
        Assert.assertEquals(entidad.getName(), nuevaEntidad.getName());
        Assert.assertEquals(entidad.getId(), nuevaEntidad.getId());
    }
    
    @Test
    public void getEquipoByNameTest()
    {
        EquipoEntity entidad  = data.get(0);
        EquipoEntity nuevaEntidad = equipoPersistence.findByName(entidad.getName());
        Assert.assertNotNull(nuevaEntidad);
        Assert.assertEquals(entidad.getCaracteristicas(),nuevaEntidad.getCaracteristicas());
        Assert.assertEquals(entidad.getName(), nuevaEntidad.getName());
        Assert.assertEquals(entidad.getId(), nuevaEntidad.getId());
    }
    
    
    public void deleteEquipoTest()
    {
        EquipoEntity entidad = data.get(0);
        equipoPersistence.delete(entidad.getId());
        EquipoEntity deleted = em.find(EquipoEntity.class ,entidad.getId());
        Assert.assertNull(deleted);
    }
    
    @Test
    
    public void updateEquipoTest()
    {
        EquipoEntity entidad = data.get(0);
        PodamFactory f = new PodamFactoryImpl();
        EquipoEntity nuevaEntidad = f.manufacturePojo(EquipoEntity.class);
        nuevaEntidad.setId(entidad.getId());
        equipoPersistence.update(nuevaEntidad);
        EquipoEntity r = em.find(EquipoEntity.class, entidad.getId());
        Assert.assertNotNull(r);
        Assert.assertEquals(r.getCaracteristicas(),nuevaEntidad.getCaracteristicas());
        Assert.assertEquals(r.getName(), nuevaEntidad.getName());
        Assert.assertEquals(r.getId(), nuevaEntidad.getId());
    }
    
    @Test
    
    public void buscarEquiposPuntoDeAtencionTestVacio()
    {
        PodamFactory f = new PodamFactoryImpl();
        EquipoEntity nuevaEntidad = f.manufacturePojo(EquipoEntity.class);
        PuntoDeAtencionEntity p = f.manufacturePojo(PuntoDeAtencionEntity.class);
        nuevaEntidad.setPuntoDeAtencion(p);
        List<EquipoEntity> l =equipoPersistence.buscarEquiposPorIdPuntoDeAtencion(nuevaEntidad.getPuntoDeAtencion().getId());
        Assert.assertNotNull(l);
        Assert.assertEquals(l.size(), 0);
    }
    
    @Test
    
    public void buscarEquiposPuntoDeAtencionTest()
    {
       PuntoDeAtencionEntity nuevaEntidad = setUp2();
        
        List<EquipoEntity> l =equipoPersistence.buscarEquiposPorIdPuntoDeAtencion(nuevaEntidad.getId());
        LOGGER.log(Level.INFO,"tam: "+l.size());
        Assert.assertEquals( data.size(),l.size());
        for (EquipoEntity actual : l)
        {
        boolean encontrado = false;
            for (int i = 0; i < data.size(); i++)
            {
                EquipoEntity a = data.get(i);
                if(actual.getId()==a.getId())
                {
                    encontrado=true;
                }
            }
        Assert.assertEquals(encontrado, true);
        }
        
    }
    @Test
    public void buscarEquipoPorIdPuntoDeAtencion()
    {
        PuntoDeAtencionEntity nuevaEntidad = setUp2();
       EquipoEntity e = equipoPersistence.buscarEquipoPorIdPuntoDeAtencion(nuevaEntidad.getId(), data.get(0).getId());
       Assert.assertNotNull(e);
       Assert.assertEquals( e.getId(),data.get(0).getId());
       Assert.assertEquals( e.getCaracteristicas(),data.get(0).getCaracteristicas());
       Assert.assertEquals( e.getName(),data.get(0).getName());
       
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

    private PuntoDeAtencionEntity setUp2() 
    {
         PodamFactory f = new PodamFactoryImpl();
        PuntoDeAtencionEntity nuevaEntidad = f.manufacturePojo(PuntoDeAtencionEntity.class);
        try
        {
            utx.begin();
            em.joinTransaction();
            em.persist(nuevaEntidad);
            utx.commit();
            
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        for (int i = 0; i < data.size(); i++)
        {
            Assert.assertNull(equipoPersistence.find(data.get(i).getId()).getPuntoDeAtencion());
            
            data.get(i).setPuntoDeAtencion(nuevaEntidad);
            equipoPersistence.update(data.get(i));
            Assert.assertEquals( equipoPersistence.find(data.get(i).getId()).getPuntoDeAtencion().getId() , nuevaEntidad.getId());
            Assert.assertNotNull(equipoPersistence.find(data.get(i).getId()).getPuntoDeAtencion());
            LOGGER.log(Level.INFO, "Actual: "+i+" "+equipoPersistence.find(data.get(i).getId()).getPuntoDeAtencion().getId()+" "+nuevaEntidad.getId());
        }
        return nuevaEntidad;
    }
}
