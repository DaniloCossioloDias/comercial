package br.org.unisenaipr.comercial.login.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "TB_LOGIN")
@SequenceGenerator(name = "SEQ_LOGIN", sequenceName = "S_LOGIN", allocationSize = 1)
public class Login implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_LOGIN")
    @Column(name = "login_id", nullable = false)
    private Long id;

    @Column(name = "user_nomeuser", nullable = false, length = 100)
    private String nomeUser;  

    @Column(name = "user_emailuser", nullable = false, length = 100)
    private String emailUser;
    
    @Column(name = "user_senhauser", nullable = false, length = 100)
    private String senhaUser;

    public Login() {
        super();

    }

    public Login(Long id, String nomeUser, String emailUser, String senhaUser) {
        super();
        this.id = id;
        this.nomeUser = nomeUser;
        this.emailUser = emailUser;
        this.senhaUser = senhaUser;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeUser() {
        return nomeUser;
    }

    public void setNomeUser(String nomeUser) {
        this.nomeUser = nomeUser;
    }

    public String getEmailUser() {
        return emailUser;
    }

    public void setEmailUser(String emailUser) {
        this.emailUser = emailUser;
    }

    public String getSenhaUser() {
        return senhaUser;
    }

    public void setSenhaUser(String senhaUser) {
        this.senhaUser = senhaUser;
    }
}
