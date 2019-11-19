package br.edu.ifsul.testes.junit;

import br.edu.ifsul.modelo.Equipamento;
import br.edu.ifsul.modelo.Estado;
import br.edu.ifsul.modelo.FormaPagamento;
import br.edu.ifsul.modelo.Marca;
import br.edu.ifsul.modelo.OrdemServico;
import br.edu.ifsul.modelo.PessoaFisica;
import br.edu.ifsul.modelo.Produto;
import br.edu.ifsul.modelo.Servico;
import br.edu.ifsul.modelo.Status;
import br.edu.ifsul.modelo.Usuario;
import java.util.Calendar;
import java.util.Locale;
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
public class TesteAlterarOrdemServico {
    
    EntityManagerFactory emf;
    EntityManager em;
    
    public TesteAlterarOrdemServico() {
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
        OrdemServico obj = em.find(OrdemServico.class, 1);
        obj.setStatus(Status.CANCELADA);
        em.getTransaction().begin();
        em.merge(obj);
        em.getTransaction().commit();        
    }
    
}
