package br.edu.ifsul.testes.junit;

import br.edu.ifsul.modelo.Arquivo;
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
public class TesteConsultarQuery {

    EntityManagerFactory emf;
    EntityManager em;

    public TesteConsultarQuery() {
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
        Query consulta = em.createQuery("from Produto where nome like 'M%'");
        List<Produto> lista = consulta.getResultList();
        for (Produto p : lista) {
            System.out.println("ID: " + p.getId() + " Nome: " + p.getNome());
        }
        // consulta que retorna todos os produtos que começam com P 
        Query consulta2 = em.createQuery("from Produto where nome like :pnome");
        consulta2.setParameter("pnome", "M%");
        lista = consulta2.getResultList();
        for (Produto p : lista) {
            System.out.println("ID: " + p.getId() + " Nome: " + p.getNome());
        }
        // consulta ao banco com tudo em maiusculo
        Query consulta3 = em.createQuery("from Produto where upper(nome) like :pnome");
        consulta3.setParameter("pnome", "M%".toUpperCase());
        lista = consulta3.getResultList();
        for (Produto p : lista) {
            System.out.println("ID: " + p.getId() + " Nome: " + p.getNome());
        }        
    }

}
