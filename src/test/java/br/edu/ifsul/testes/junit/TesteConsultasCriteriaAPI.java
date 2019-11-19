package br.edu.ifsul.testes.junit;

import br.edu.ifsul.modelo.Produto;
import java.io.IOException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Jorge
 */
public class TesteConsultasCriteriaAPI {

    EntityManagerFactory emf;
    EntityManager em;

    public TesteConsultasCriteriaAPI() {
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
	// consulta que retorna todos os produtos
        //objeto criador de criterias
        CriteriaBuilder builder = em.getCriteriaBuilder();
        // criteria query
        CriteriaQuery<Produto> query = builder.createQuery(Produto.class);
        // raiz da consulta
        Root<Produto> raiz = query.from(Produto.class);
        // definindo a raiz da consulta
        query.select(raiz);
        // filtrando com equal que é similar ao =
        //query.where(builder.equal(raiz.get("nome"), "Mouse Laser Dell"));	        
        // filtrando com like
        query.where(builder.like(raiz.<String>get("nome"), "%Mou%"));        
        // ordenando
        query.orderBy(builder.asc(raiz.get("nome")));
        // lancando uma consulta em uma coleção tipada
        TypedQuery<Produto> q = em.createQuery(query);
        List<Produto> lista = q.getResultList();
	for (Produto p : lista){
		System.out.println("ID: "+p.getId()+ " Nome: "+p.getNome());
	}          
    }

}
