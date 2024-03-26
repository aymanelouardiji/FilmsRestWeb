package model;

import jakarta.persistence.*;


import java.io.Serializable;

@Entity
@Table(name = "film")
public class Films implements Serializable {
    private static final long serialVersionUID = 8364239647574512618L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String categ;
    private String descrp;
    private String photo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCateg() {
        return categ;
    }

    public void setCateg(String categ) {
        this.categ = categ;
    }

    public String getDescrp() {
        return descrp;
    }

    public void setDescrp(String descrp) {
        this.descrp = descrp;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
    @Override
    public String toString(){return id + "=>" + name + "=>" +categ ; }
}
