package br.edu.ifsul.testes.junit;

import br.edu.ifsul.modelo.PessoaFisica;
import br.edu.ifsul.modelo.Usuario;
import java.io.IOException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Jorge
 */
public class TesteConsultarNamedQuery {

    EntityManagerFactory emf;
    EntityManager em;

    public TesteConsultarNamedQuery() {
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
        List<Usuario> lista = em.createNamedQuery("todosUsuarioOrdemNome").getResultList();
        for (Usuario u : lista){
            System.out.println("Nome: " + u.getNome());
        }
        
        Query q = em.createNamedQuery("PessoaFisica.buscaPorCPF");
        q.setParameter("pcpf", "%56%");
        List<PessoaFisica> listapf = q.getResultList();
        for (PessoaFisica p : listapf){
            System.out.println("Nome: "+p.getNome()+ " CPF: "+p.getCpf());
        }	        
    }
}


