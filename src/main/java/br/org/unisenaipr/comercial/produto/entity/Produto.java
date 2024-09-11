package br.org.unisenaipr.comercial.produto.entity;

import java.io.Serializable;
import java.util.List;

import br.org.unisenaipr.comercial.fabricante.entity.Fabricante;
import br.org.unisenaipr.comercial.subgrupo.entity.SubGrupo;
import br.org.unisenaipr.comercial.venda.entity.Venda;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "TB_PRODUTO")
@SequenceGenerator(name = "SEQ_PRODUTO", sequenceName = "S_PRODUTO", allocationSize = 1)
public class Produto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PRODUTO")
    @Column(name = "produto_id", nullable = false)
    private Long id;

    @Column(name = "produto_nomesubgrupo", nullable = false, length = 150)
    private String nomeProduto;

    @Column(name = "produto_precovenda", nullable = false)
    private double precoVenda;

    @Transient
    private int qtdVenda;

    @Transient
    private double vlrVenda;

    @Transient
    private List<Produto> vendasdeprodutos;

    @ManyToOne
    @JoinColumn(name = "subgrupo_id")
    private SubGrupo subGrupo;

    @ManyToOne
    @JoinColumn(name = "fabricante_id")
    private Fabricante fabricante;

    @ManyToMany(mappedBy = "produtos", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<Venda> vendas;

    // Construtores
    public Produto() {
        super();
    }

    public Produto(Long id, String nomeProduto, double precoVenda, SubGrupo subGrupo, Fabricante fabricante) {
        this.id = id;
        this.nomeProduto = nomeProduto;
        this.precoVenda = precoVenda;
        this.subGrupo = subGrupo;
        this.fabricante = fabricante;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public double getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(double precoVenda) {
        this.precoVenda = precoVenda;
    }

    public int getQtdVenda() {
        return qtdVenda;
    }

    public void setQtdVenda(int qtdVenda) {
        this.qtdVenda = qtdVenda;
    }

    public double getVlrVenda() {
        return vlrVenda;
    }

    public void setVlrVenda(double vlrVenda) {
        this.vlrVenda = vlrVenda;
    }

    public SubGrupo getSubGrupo() {
        return subGrupo;
    }

    public void setSubGrupo(SubGrupo subGrupo) {
        this.subGrupo = subGrupo;
    }

    public Fabricante getFabricante() {
        return fabricante;
    }

    public void setFabricante(Fabricante fabricante) {
        this.fabricante = fabricante;
    }

    public List<Venda> getVendas() {
        return vendas;
    }

    public void setVendas(List<Venda> vendas) {
        this.vendas = vendas;
    }

    public List<Produto> getVendasdeprodutos() {
        return vendasdeprodutos;
    }

    public void setVendasdeprodutos(List<Produto> vendasdeprodutos) {
        this.vendasdeprodutos = vendasdeprodutos;
    }
}
