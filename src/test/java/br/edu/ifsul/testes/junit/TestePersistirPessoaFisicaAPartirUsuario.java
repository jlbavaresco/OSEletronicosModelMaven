package br.edu.ifsul.testes.junit;

import br.edu.ifsul.modelo.Cidade;
import br.edu.ifsul.modelo.Estado;
import br.edu.ifsul.modelo.PessoaFisica;
import br.edu.ifsul.modelo.Usuario;
import java.util.Calendar;
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
public class TestePersistirPessoaFisicaAPartirUsuario {
    
    EntityManagerFactory emf;
    EntityManager em;
    
    public TestePersistirPessoaFisicaAPartirUsuario() {
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
    public void teste(){
        // carregar o usuário que eu quero usar para gerar a nova pessoa fisica
        Usuario usuario = em.find(Usuario.class, "carlos");
        PessoaFisica obj = new PessoaFisica(usuario);
        obj.setCpf("568.679.120-10");
        obj.setRg("8584934587");
        obj.setNascimento(Calendar.getInstance());
        obj.setEndereco("Av Brasil");
        obj.setNumero("15");
        obj.setReferencia("Perto posto ipiranga");
        obj.setCep("99854-000");
        obj.setBairro("Centro");
        obj.setCidade(em.find(Cidade.class, 1));        
        em.getTransaction().begin();
        // antes de persistir a pessoa fisica deve ser removido o usuário
        em.remove(usuario);
        em.merge(obj);
        em.getTransaction().commit();        
    }
    
}
