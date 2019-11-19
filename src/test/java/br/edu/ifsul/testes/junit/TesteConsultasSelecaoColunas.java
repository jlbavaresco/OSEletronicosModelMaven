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
public class TesteConsultasSelecaoColunas {

    EntityManagerFactory emf;
    EntityManager em;

    public TesteConsultasSelecaoColunas() {
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
        //consulta em uma unica tabela
        Query q = em.createQuery("select p.nome, p.cpf from PessoaFisica p");
        for (Object linha : q.getResultList()){
            Object[] obj = (Object[]) linha;
            System.out.println("Nome: "+obj[0]+ " CPF: "+obj[1]);
        }


	// Outra consulta com seleção de colunas
        Query q2 = em.createQuery("select p.nome, m.nome from Produto p, Marca m where p.marca.id = m.id");
        for (Object linha : q2.getResultList()){
            Object[] obj = (Object[]) linha;
            System.out.println("Produto: "+obj[0]+ " Marca: "+obj[1]);
        }  
    }

}
