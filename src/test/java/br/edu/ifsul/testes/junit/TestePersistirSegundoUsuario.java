package br.edu.ifsul.testes.junit;

import br.edu.ifsul.modelo.Cidade;
import br.edu.ifsul.modelo.Estado;
import br.edu.ifsul.modelo.Permissao;
import br.edu.ifsul.modelo.Usuario;
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
public class TestePersistirSegundoUsuario {

    EntityManagerFactory emf;
    EntityManager em;

    public TestePersistirSegundoUsuario() {
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
    public void teste() {
        Usuario obj = new Usuario();
        obj.setEmail("carlos@gmail.com");
        obj.setNome("carlos");
        obj.setNomeUsuario("carlos");
        obj.setSenha("123456");
        obj.setTelefoneAlternativo("(54)99987-4584");
        obj.setTelefonePrincipal("(54)99876-4956");
        obj.getPermissoes().add(em.find(Permissao.class, "ADMINISTRADOR"));
        obj.getPermissoes().add(em.find(Permissao.class, "USUARIO"));
        em.getTransaction().begin();
        em.persist(obj);
        em.getTransaction().commit();
    }

}
