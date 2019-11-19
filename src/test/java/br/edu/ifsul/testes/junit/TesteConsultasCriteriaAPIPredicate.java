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
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Jorge
 */
public class TesteConsultasCriteriaAPIPredicate {

    EntityManagerFactory emf;
    EntityManager em;

    public TesteConsultasCriteriaAPIPredicate() {
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
        CriteriaBuilder builder = em.getCriteriaBuilder();
        // criteria query
        CriteriaQuery<Produto> query = builder.createQuery(Produto.class);
        // raiz da consulta
        Root<Produto> raiz = query.from(Produto.class);
        // definindo a raiz da consulta               
        query.select(raiz);        
        // criando um predicado para filtrar por duas duas expressoes        
        Predicate predicate = builder.and( builder.like(raiz.<String>get("nome"), "%Mou%"),
                                           builder.equal(raiz.<Integer>get("marca"), 1) );
        // adicionando o predicado ao where
        query.where(predicate);
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
