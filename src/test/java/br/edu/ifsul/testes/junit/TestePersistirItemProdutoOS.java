package br.edu.ifsul.testes.junit;

import br.edu.ifsul.modelo.Arquivo;
import br.edu.ifsul.modelo.Estado;
import br.edu.ifsul.modelo.Foto;
import br.edu.ifsul.modelo.ItemProduto;
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
public class TestePersistirItemProdutoOS {

    EntityManagerFactory emf;
    EntityManager em;

    public TestePersistirItemProdutoOS() {
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
        ItemProduto ip = new ItemProduto();
        ip.setProduto(em.find(Produto.class, 1));
        ip.setQuantidade(2);
        ip.setValorUnitario(ip.getProduto().getPreco());
        ip.setValorTotal(ip.getValorUnitario() * ip.getValorUnitario());
        os.adicionarProduto(ip);
        em.getTransaction().begin();
        em.persist(os);
        em.getTransaction().commit();
    }

}
