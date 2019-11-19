package br.edu.ifsul.modelo;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

/**
 * @author Prof. Me. Jorge Luis Boeira Bavaresco
 * @email jorge.bavaresco@passofundo.ifsul.edu.br
 * @organization IFSUL - Campus Passo Fundo
 */
@Entity
@Table
@DiscriminatorValue(value = "PF")
@NamedQueries({
    @NamedQuery(name = "PessoaFisica.todosOrderNome", query = "from PessoaFisica order by nome")
    ,
    @NamedQuery(name = "PessoaFisica.buscaPorCPF", query = "from PessoaFisica where cpf like :pcpf order by nome")
})
public class PessoaFisica extends Usuario implements Serializable {

    @NotNull(message = "O CPF não pode ser nulo")
    @NotBlank(message = "O CPF não ser em branco")
    @Length(max = 14, message = "O CPF não pode ter mais de {max} caracteres")
    @CPF(message = "O CPF deve ser válido")
    @Column(name = "cpf", length = 14, nullable = false)
    private String cpf;
    @NotNull(message = "O RG não pode ser nulo")
    @NotBlank(message = "O RG não ser em branco")
    @Length(max = 10, message = "O RG não pode ter mais de {max} caracteres")
    @Column(name = "rg", length = 10, nullable = false)
    private String rg;
    @NotNull(message = "O nascimento não pode ser nulo")
    @Temporal(TemporalType.DATE)
    @Column(name = "nascimento", nullable = false)
    private Calendar nascimento;
    @NotNull(message = "O Endereço não pode ser nulo")
    @NotBlank(message = "O Endereço não ser em branco")
    @Length(max = 50, message = "O Endereço não pode ter mais de {max} caracteres")
    @Column(name = "endereco", length = 50, nullable = false)
    private String endereco;
    @NotNull(message = "O numero não pode ser nulo")
    @NotBlank(message = "O numero não ser em branco")
    @Length(max = 10, message = "O numero não pode ter mais de {max} caracteres")
    @Column(name = "numero", length = 10, nullable = false)
    private String numero;
    @Length(max = 20, message = "O complemento não pode ter mais de {max} caracteres")
    @Column(name = "complemento", length = 20)
    private String complemento;
    @NotNull(message = "O cep não pode ser nulo")
    @NotBlank(message = "O cep não ser em branco")
    @Length(max = 9, message = "O cep não pode ter mais de {max} caracteres")
    @Column(name = "cep", length = 9, nullable = false)
    private String cep;
    @NotNull(message = "O bairro não pode ser nulo")
    @NotBlank(message = "O bairro não ser em branco")
    @Length(max = 40, message = "O bairro não pode ter mais de {max} caracteres")
    @Column(name = "bairro", length = 40, nullable = false)
    private String bairro;
    @Length(max = 30, message = "A referência não pode ter mais de {max} caracteres")
    @Column(name = "referencia", length = 30)
    private String referencia;
    @NotNull(message = "A cidade deve ser informada")
    @ManyToOne
    @JoinColumn(name = "cidade", referencedColumnName = "id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_cidade_id"))
    private Cidade cidade;

    public PessoaFisica() {
        super();
    }

    /**
     * Método para se criar uma pessoa fisica com base em um usuário que já
     * existe
     *
     * @param usuario registro que já existe no banco Antes de salvar a nova
     * pessoa física o usuario que já existia no banco deve ser removido
     */
    public PessoaFisica(Usuario usuario) {
        super.setNomeUsuario(usuario.getNomeUsuario());
        super.setSenha(usuario.getSenha());
        super.setDataCadastro(usuario.getDataCadastro());
        super.setNome(usuario.getNome());
        super.setEmail(usuario.getEmail());
        super.setTelefonePrincipal(usuario.getTelefonePrincipal());
        super.setTelefoneAlternativo(usuario.getTelefoneAlternativo());
        super.setPermissoes(usuario.getPermissoes());

    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public Calendar getNascimento() {
        return nascimento;
    }

    public void setNascimento(Calendar nascimento) {
        this.nascimento = nascimento;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }
}
