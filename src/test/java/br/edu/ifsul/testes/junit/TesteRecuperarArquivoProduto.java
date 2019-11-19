package br.edu.ifsul.testes.junit;

import br.edu.ifsul.modelo.Arquivo;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Jorge
 */
public class TesteRecuperarArquivoProduto {

    EntityManagerFactory emf;
    EntityManager em;

    public TesteRecuperarArquivoProduto() {
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
        Arquivo a = em.find(Arquivo.class, 1);
        File file = new File("d:\\" + a.getNomeArquivo());
        FileOutputStream out = new FileOutputStream(file);
        out.write(a.getArquivo());
        out.close();
    }

}
