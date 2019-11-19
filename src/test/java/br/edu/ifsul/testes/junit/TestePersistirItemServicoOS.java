package br.edu.ifsul.testes.junit;

import br.edu.ifsul.modelo.Arquivo;
import br.edu.ifsul.modelo.Estado;
import br.edu.ifsul.modelo.Foto;
import br.edu.ifsul.modelo.ItemServico;
import br.edu.ifsul.modelo.Marca;
import br.edu.ifsul.modelo.OrdemServico;
import br.edu.ifsul.modelo.Produto;
import br.edu.ifsul.modelo.Servico;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
public class TestePersistirItemServicoOS {

    EntityManagerFactory emf;
    EntityManager em;

    public TestePersistirItemServicoOS() {
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
    public void teste() throws IOException {
        OrdemServico os = em.find(OrdemServico.class, 1);
        ItemServico is = new ItemServico();
        is.setServico(em.find(Servico.class, 3));
        is.setQuantidade(1);
        is.setValorUnitario(is.getServico().getValor());
        is.setValorTotal(is.getValorUnitario() * is.getQuantidade());
        os.adicionarServico(is);            
        em.getTransaction().begin();
        em.persist(is);
        em.getTransaction().commit();
    }

}
