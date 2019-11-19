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
public class TesteConsultarPaginado {

    EntityManagerFactory emf;
    EntityManager em;

    public TesteConsultarPaginado() {
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
	Query consulta = em.createQuery("from Produto order by id");
	// primeiro resultado é zero 0
	consulta.setFirstResult(0);
	consulta.setMaxResults(2);
	//mostrar todos os resultados
	//consulta.setMaxResults(consulta.getResultList().size());                        
	List<Produto> lista = consulta.getResultList();
	for (Produto p : lista){
		System.out.println("ID: "+p.getId()+ " Nome: "+p.getNome());
	}        
    }

}
