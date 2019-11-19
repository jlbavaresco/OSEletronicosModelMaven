package br.edu.ifsul.testes.junit;

import br.edu.ifsul.modelo.Arquivo;
import br.edu.ifsul.modelo.Estado;
import br.edu.ifsul.modelo.Marca;
import br.edu.ifsul.modelo.Produto;
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
public class TestePersistirArquivoProduto {

    EntityManagerFactory emf;
    EntityManager em;

    public TestePersistirArquivoProduto() {
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
        Produto p = em.find(Produto.class, 1);
        Arquivo a = new Arquivo();
        a.setNomeArquivo("dell-wireless-mouse-wm126_setup guide_pt-br.pdf");
        a.setDescricao("Manual do mouse");
        Path path = Paths.get("E:\\Downloads\\dell-wireless-mouse-wm126_setup guide_pt-br.pdf");
        a.setArquivo(Files.readAllBytes(path));
        a.setProduto(p);
        em.getTransaction().begin();
        em.persist(a);
        em.getTransaction().commit();
    }

}
