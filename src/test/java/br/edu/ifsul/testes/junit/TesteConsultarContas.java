package br.edu.ifsul.testes.junit;

import br.edu.ifsul.modelo.Arquivo;
import br.edu.ifsul.modelo.ContaReceber;
import br.edu.ifsul.modelo.Estado;
import br.edu.ifsul.modelo.Marca;
import br.edu.ifsul.modelo.Produto;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Jorge
 */
public class TesteConsultarContas {

    EntityManagerFactory emf;
    EntityManager em;

    public TesteConsultarContas() {
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
        // mostrar a consulta sem o parametro
        Query consulta = em.createQuery("select c from br.edu.ifsul.modelo.ContaReceber c join fetch c.id.ordemServico  order by c.id.ordemServico.id");
        List<ContaReceber> lista = consulta.getResultList();
        for (ContaReceber c : lista){
            System.out.println("OS: " + c.getId().getOrdemServico().getId());
        }
       
    }

}
