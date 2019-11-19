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
public class TesteConsultasAgregacao {

    EntityManagerFactory emf;
    EntityManager em;

    public TesteConsultasAgregacao() {
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
        // consulta que retorna a quantidade de vendas por produto
        Query query = em.createNativeQuery("select p.nome, count(*) from produto p, item_produto ios  "
                + "where p.id = ios.produto group by p.nome");
        for (Object linha : query.getResultList()) {
            Object[] obj = (Object[]) linha;
            System.out.println("Produto: " + obj[0] + " Quantidade de vendas: " + obj[1]);
        }

        // consulta que retorna o total de produtos cadastrados
        Query query1 = em.createNativeQuery("select count(*) from produto");
        Object resultado = (Object) query1.getSingleResult();
        System.out.println("Total de produtos: " + resultado);

        // Consulta que retorna o maior e o menor preço de produtso
        Query query2 = em.createNativeQuery("select min(preco), max(preco) from produto");
        Object[] result = (Object[]) query2.getSingleResult();
        System.out.println("Menor: " + result[0] + " Maior: " + result[1]);
    }

}
