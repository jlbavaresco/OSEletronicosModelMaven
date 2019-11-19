package br.edu.ifsul.testes.junit;

import br.edu.ifsul.modelo.Estado;
import br.edu.ifsul.modelo.Marca;
import br.edu.ifsul.modelo.Produto;
import br.edu.ifsul.modelo.Servico;
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
public class TestePersistirServico {
    
    EntityManagerFactory emf;
    EntityManager em;
    
    public TestePersistirServico() {
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
        Servico obj = new Servico();
        obj.setNome("Instalação de sistema operacional");        
        obj.setValor(120.00);    
        Servico obj2 = new Servico();
        obj2.setNome("Limpeza de componentes eletrônicos");
        obj2.setValor(60.0);
        em.getTransaction().begin();
        em.persist(obj);
        em.persist(obj2);
        em.getTransaction().commit();        
    }
    
}
