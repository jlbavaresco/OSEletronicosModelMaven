package br.edu.ifsul.util.relatorios;

import br.edu.ifsul.modelo.ContaReceber;
import br.edu.ifsul.modelo.ContaReceberID;
import br.edu.ifsul.modelo.Equipamento;
import br.edu.ifsul.modelo.FormaPagamento;
import br.edu.ifsul.modelo.ItemProduto;
import br.edu.ifsul.modelo.ItemServico;
import br.edu.ifsul.modelo.Marca;
import br.edu.ifsul.modelo.OrdemServico;
import br.edu.ifsul.modelo.PessoaFisica;
import br.edu.ifsul.modelo.Produto;
import br.edu.ifsul.modelo.Servico;
import br.edu.ifsul.modelo.Status;
import br.edu.ifsul.modelo.Usuario;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Classe para ser utilizada como fonte de dados no JasperStudio 
 * a fim de ser possivel testar um relatório com fonte de dados JavaBeans
 * @author Prof. Me. Jorge Luis Boeira Bavaresco
 * @email jorge.bavaresco@passofundo.ifsul.edu.br
 * @organization IFSUL - Campus Passo Fundo
 */
public class FabricaObjetos {

    public static List<Produto> carregaProdutos(){
        List<Produto> lista = new ArrayList<>();
        Marca m = new Marca();
        m.setNome("Dell");
        Produto p1 = new Produto();
        p1.setMarca(m);
        p1.setId(1);
        p1.setNome("Mouse Laser");
        p1.setPreco(120.00);
        lista.add(p1);
        Produto p2 = new Produto();
        p2.setMarca(m);
        p2.setId(2);
        p2.setNome("Teclado sem fio");
        p2.setPreco(150.00);
        lista.add(p2);  
        return lista;
    }
    
    public static List<ContaReceber> carregaContas(){
        List<ContaReceber> lista = new ArrayList<>();
        PessoaFisica pf = new PessoaFisica();
        pf.setNome("João da Silva");
        pf.setCpf("999.999.345-87");
        OrdemServico os = new OrdemServico();
        os.setPessoaFisica(pf);
        os.setId(1);
        ContaReceber conta = new ContaReceber();
        ContaReceberID id = new ContaReceberID();
        id.setOrdemServico(os);
        id.setNumeroParcela(1);
        conta.setId(id);
        conta.setValor(500.00);
        conta.setVencimento(Calendar.getInstance());
        lista.add(conta);
        return lista;
    }
    
    public static List<OrdemServico> carregaOrdem(){
        List<OrdemServico> lista = new ArrayList<>();
        OrdemServico os = new OrdemServico();
        os.setDataAbertura(Calendar.getInstance());
        os.setDataFechamento(Calendar.getInstance());
        os.setDescricaoProblema("<b>Notebook não inicia o windows. </b>");
        os.setFormaPagamento(FormaPagamento.APRAZO);
        os.setId(1);
        os.setQuantidadeParcelas(3);
        os.setResolucaoProblema("Reinstalação do windows");
        os.setStatus(Status.ABERTA);
        Equipamento eq = new Equipamento();
        eq.setNumeroSerie("123456");
        eq.setDescricao("Notebook Dell");
        os.setEquipamento(eq);
        PessoaFisica pf = new PessoaFisica();
        pf.setNome("João da Silva");
        pf.setCpf("999.999.345-87");
        os.setPessoaFisica(pf);
        Usuario usuario = new Usuario();
        usuario.setNome("Jorge Bavaresco");
        os.setUsuario(usuario);
        Produto p = new Produto();
        p.setNome("Teclado para notebook");
        ItemProduto ip = new ItemProduto();
        ip.setProduto(p);
        ip.setQuantidade(1);
        ip.setValorUnitario(150.00);
        ip.setValorTotal(150.00);
        os.adicionarProduto(ip);
        Servico s = new Servico();
        s.setNome("Instalação de sistema operacional");
        ItemServico is = new ItemServico();
        is.setServico(s);
        is.setValorUnitario(120.00);
        is.setQuantidade(1);
        is.setValorTotal(120.00);
        os.adicionarServico(is);
        lista.add(os);
        return lista;
    }
}
