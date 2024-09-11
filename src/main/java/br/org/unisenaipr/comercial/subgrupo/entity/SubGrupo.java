package br.org.unisenaipr.comercial.subgrupo.entity;

import java.io.Serializable;
import java.util.List;

import br.org.unisenaipr.comercial.grupo.entity.Grupo;
import br.org.unisenaipr.comercial.produto.entity.Produto;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "TB_SUBGRUPO")
@SequenceGenerator(name = "SEQ_SUBGRUPO", sequenceName = "S_SUBGRUPO", allocationSize = 1)
public class SubGrupo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_SUBGRUPO")
    @Column(name = "subgrupo_id", nullable = false)
    private Long id;

    @Column(name = "subgrupo_nomesubgrupo", nullable = false, length = 150)
    private String nomeSubGrupo;

    @ManyToOne
    @JoinColumn(name = "grupo_id", nullable = false)
    private Grupo grupo;
    
    @OneToMany(mappedBy = "subGrupo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Produto> produtos;

    public SubGrupo() {
        super();
    }

    public SubGrupo(Long id, String nomeSubGrupo) {
        super();
        this.id = id;
        this.nomeSubGrupo = nomeSubGrupo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeSubGrupo() {
        return nomeSubGrupo;
    }

    public void setNomeSubGrupo(String nomeSubGrupo) {
        this.nomeSubGrupo = nomeSubGrupo;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }
}
