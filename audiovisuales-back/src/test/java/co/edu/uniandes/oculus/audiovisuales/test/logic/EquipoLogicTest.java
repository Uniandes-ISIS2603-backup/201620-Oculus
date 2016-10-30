/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package co.edu.uniandes.oculus.audiovisuales.test.logic;

import co.edu.uniandes.oculus.audiovisuales.api.IEquipoLogic;
import co.edu.uniandes.oculus.audiovisuales.ejbs.EquipoLogic;
import co.edu.uniandes.oculus.audiovisuales.entities.EquipoEntity;
import co.edu.uniandes.oculus.audiovisuales.entities.PuntoDeAtencionEntity;
import co.edu.uniandes.oculus.audiovisuales.entities.ReservaEntity;
import co.edu.uniandes.oculus.audiovisuales.persistence.EquipoPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
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
 * @author gc.andrade10
 */
@RunWith(Arquillian.class)
public class EquipoLogicTest
{
    private PodamFactory factory = new PodamFactoryImpl();
    
    @Inject
    private IEquipoLogic equipoLogic;
    
    @PersistenceContext
    private EntityManager  em;
    
    @Inject
    private UserTransaction utx;
    
    private List<EquipoEntity> data = new ArrayList<EquipoEntity>();
    
    private List<ArrayList<ReservaEntity>> reservasData = new  ArrayList<ArrayList<ReservaEntity>>();
    
    private List<PuntoDeAtencionEntity> puntoDeAtencionData = new ArrayList<PuntoDeAtencionEntity>();
    
    @Deployment
    public static JavaArchive createDeployment()
    {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(EquipoEntity.class.getPackage())
                .addPackage(EquipoLogic.class.getPackage())
                .addPackage(IEquipoLogic.class.getPackage())
                .addPackage(EquipoPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml","persistence.xml")
                .addAsManifestResource("META-INF/beans.xml","beans.xml");
    }
    
    @Before
    public void setUp()
    {
        try
        {
            utx.begin();
            clearData();
            insertData();
            utx.commit();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            try{utx.rollback();}
            catch(Exception e1){e1.printStackTrace();}
        }
    }
    
    /**
     * Limpia las tablas que están implicadas en la prueba.
     */
    private void clearData()
    {
        em.createQuery("delete from EquipoEntity").executeUpdate();
        em.createQuery("delete from PuntoDeAtencionEntity").executeUpdate();
        em.createQuery("delete from ReservaEntity").executeUpdate();
    }
    
    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las pruebas.
     */
    private void insertData()
    {
        for (int i = 0; i < 3; i++)
        {ArrayList<ReservaEntity> reservas = new ArrayList<>();
        for (int j = 0; j < 3; j++)
        {
            ReservaEntity reserva = factory.manufacturePojo(ReservaEntity.class);
            em.persist(reserva);
            reservas.add(reserva);
        }
        reservasData.add(reservas);
        }
        for (int i = 0; i < 3; i++)
        {
            PuntoDeAtencionEntity punto = factory.manufacturePojo(PuntoDeAtencionEntity.class);
            em.persist(punto);
            puntoDeAtencionData.add(punto);
        }
        for (int i = 0; i < 3; i++)
        {
            EquipoEntity entity = factory.manufacturePojo(EquipoEntity.class);
            entity.setPuntoDeAtencion(puntoDeAtencionData.get(i));
            entity.setReservas(reservasData.get(0));
            em.persist(entity);
            data.add(entity);
        }
    }
    
    @Test
    public void createEquipoTest()
    {
        EquipoEntity nuevaEntidad = factory.manufacturePojo(EquipoEntity.class);
        EquipoEntity resultado = equipoLogic.createEquipo(nuevaEntidad);
        Assert.assertNotNull(resultado);
        EquipoEntity entidad = em.find(EquipoEntity.class, resultado.getId());
        Assert.assertEquals(nuevaEntidad.getName(),entidad.getName());
        Assert.assertEquals(nuevaEntidad.getCaracteristicas(),entidad.getCaracteristicas());
        Assert.assertEquals(nuevaEntidad.getId(),entidad.getId());
    }
    
    @Test
    public void getEquiposTest()
    {
        List <EquipoEntity> list = equipoLogic.getEquipos();
        Assert.assertEquals(data.size(), list.size());
        for (EquipoEntity  entidad :list)
        {
            boolean encontrado = false;
            for (EquipoEntity entidadAlmacenada : data)
            {
                if(entidad.getId().equals(entidadAlmacenada.getId()))
                {
                    encontrado=true;
                }
            }
            Assert.assertTrue(encontrado);
        }
    }
    
    @Test
    public void getEquipoTest()
    {
        EquipoEntity entidad = data.get(0);
        EquipoEntity resultado = equipoLogic.getEquipo(entidad.getId());
        Assert.assertNotNull(resultado);
        Assert.assertEquals(entidad.getCaracteristicas(), resultado.getCaracteristicas());
        Assert.assertEquals(entidad.getName(), resultado.getName());
        Assert.assertEquals(entidad.getId(), resultado.getId());
    }
    
    @Test
    public void deleteEquipoTest()
    {
        EquipoEntity entidad = data.get(1);
        equipoLogic.deleteEquipo(entidad.getId());
        EquipoEntity deleted = em.find(EquipoEntity.class, entidad.getId());
        Assert.assertNull(deleted);
    }
    
    @Test 
    public void updateEquipoTest()
    {
        EquipoEntity entidad = data.get(0);
        EquipoEntity entidadGenerada = factory.manufacturePojo(EquipoEntity.class);
        entidadGenerada.setId(entidad.getId());
        equipoLogic.updateEquipo(entidadGenerada);
        EquipoEntity r = em.find(EquipoEntity.class, entidad.getId());
        Assert.assertEquals(entidadGenerada.getCaracteristicas(), r.getCaracteristicas());
        Assert.assertEquals(entidadGenerada.getId(), r.getId());
        Assert.assertEquals(entidadGenerada.getName(), r.getName());
    }
    
    @Test
    public void getEquipoByNameTest()
    {
        EquipoEntity entidad = data.get(0);
        EquipoEntity r = equipoLogic.getEquipoByName(entidad.getName());
        Assert.assertEquals(entidad.getCaracteristicas(), r.getCaracteristicas());
        Assert.assertEquals(entidad.getId(), r.getId());
        Assert.assertEquals(entidad.getName(), r.getName());
    }
    
    @Test
    public void getEquiposByIdPuntoDeAtencionTest()
    {
        long idPA = puntoDeAtencionData.get(0).getId();
        List <EquipoEntity> list = equipoLogic.getEquiposByIdPuntoDeAtencion(idPA);
        TypedQuery<EquipoEntity> q = em.createQuery("SELECT u FROM EquipoEntity u WHERE  u.puntoDeAtencion.id = :id",EquipoEntity.class);
        q = q.setParameter("id", idPA);
        List<EquipoEntity> eqps = q.getResultList();
        Assert.assertEquals(eqps.size(), list.size());
        for (EquipoEntity  entidad :list)
        {
            boolean encontrado = false;
            for (EquipoEntity entidadAlmacenada : eqps)
            {
                if(entidad.getId().equals(entidadAlmacenada.getId()))
                {
                    encontrado=true;
                }
            }
            Assert.assertTrue(encontrado);
        }
    }
    
    
    
    
    
    
    
}
