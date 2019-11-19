package br.edu.ifsul.testes.junit;

import br.edu.ifsul.modelo.Estado;
import br.edu.ifsul.modelo.Marca;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Jorge
 */
public class TestePersistirMarca {
    
    EntityManagerFactory emf;
    EntityManager em;
    
    public TestePersistirMarca() {
    }
    
    @Before
    public void setUp() {
        emf = Persistence.createEntityManagerFactory("OSEletronicosModelPU");
        em = emf.createEntityManager();        
    }
    
    @After
    public void tearDown() {
        em.close();
        emf.close();
    }
    
    @Test
    public void teste(){
        Marca obj1 = new Marca();
        obj1.setNome("Dell");
        Marca obj2 = new Marca();
        obj2.setNome("HP");
        Marca obj3 = new Marca();
        obj3.setNome("Acer");
        Marca obj4 = new Marca();
        obj4.setNome("Microsoft");
        em.getTransaction().begin();
        em.persist(obj1);
        em.persist(obj2);
        em.persist(obj3);
        em.persist(obj4);
        em.getTransaction().commit();        
    }
    
}
