package br.org.unisenaipr.comercial.grupo.entity;

import java.io.Serializable;
import java.util.List;

import br.org.unisenaipr.comercial.subgrupo.entity.SubGrupo;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "TB_GRUPO")
@SequenceGenerator(name = "SEQ_GRUPO", sequenceName = "S_GRUPO", allocationSize = 1)
public class Grupo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_GRUPO")
    @SequenceGenerator(name = "SEQ_GRUPO", sequenceName = "S_GRUPO", allocationSize = 1)
    @Column(name = "grupo_id", nullable = false)
    private Long id;

    @Column(name = "grupo_nomegrupo", nullable = false, length = 100)
    private String nomeGrupo;

    @OneToMany(mappedBy = "grupo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SubGrupo> subGrupos;

    public Grupo() {
        super();
    }

    public Grupo(Long id, String nomeGrupo) {
        super();
        this.id = id;
        this.nomeGrupo = nomeGrupo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeGrupo() {
        return nomeGrupo;
    }

    public void setNomeGrupo(String nomeGrupo) {
        this.nomeGrupo = nomeGrupo;
    }

    public List<SubGrupo> getSubGrupos() {
        return subGrupos;
    }

    public void setSubGrupos(List<SubGrupo> subGrupos) {
        this.subGrupos = subGrupos;
    }
}
