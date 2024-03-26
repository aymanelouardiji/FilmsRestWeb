package model;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "comment")
public class Comment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "idClient")
    private Client client;
    private String msgDesc;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getMsgDesc() {
        return msgDesc;
    }

    public void setMsgDesc(String msgDesc) {
        this.msgDesc = msgDesc;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", client=" + client +
                ", msgDesc='" + msgDesc + '\'' +
                '}';
    }
}
