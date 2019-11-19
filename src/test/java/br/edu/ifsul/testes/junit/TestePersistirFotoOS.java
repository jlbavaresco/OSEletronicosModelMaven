package br.edu.ifsul.testes.junit;

import br.edu.ifsul.modelo.Arquivo;
import br.edu.ifsul.modelo.Estado;
import br.edu.ifsul.modelo.Foto;
import br.edu.ifsul.modelo.Marca;
import br.edu.ifsul.modelo.OrdemServico;
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
public class TestePersistirFotoOS {

    EntityManagerFactory emf;
    EntityManager em;

    public TestePersistirFotoOS() {
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
        Foto foto = new Foto();
        foto.setNomeFoto("placamae.jpg");
        foto.setDescricao("Foto da placa mãe");
        foto.setOrdemServico(os);
        Path path = Paths.get("E:\\Downloads\\placamae.jpg");
        foto.setArquivo(Files.readAllBytes(path));             
        em.getTransaction().begin();
        em.persist(foto);
        em.getTransaction().commit();
    }

}
