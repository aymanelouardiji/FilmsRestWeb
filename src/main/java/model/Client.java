package model;

import jakarta.persistence.*;

import java.io.Serializable;


@Entity
@Table(name = "client")
public class Client implements Serializable {
    private static final long serialVersionUID = 8364239647574512618L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idClient;
    private String name;
    private String mail;
    private String mdp;

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + idClient +
                ", name='" + name + '\'' +
                ", mail='" + mail + '\'' +
                ", mdp='" + mdp + '\'' +
                '}';
    }
}
