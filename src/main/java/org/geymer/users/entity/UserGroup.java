package org.geymer.users.entity;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonManagedReference;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: babkamen
 * Date: 14.11.13
 * Time: 13:45
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "usergroup")
public class UserGroup {
    @javax.persistence.Column(name = "id")
    @Id
    @GeneratedValue
    private int id;
    @NotEmpty(message = "This field is required")
    @javax.persistence.Column(name = "title")
    private String title;

    @Lob
    @javax.persistence.Column(name = "logo")
    private byte[] logo;
    @NotEmpty(message = "This field is required")
    @javax.persistence.Column(name = "about")
    private String about;
    @JsonManagedReference
    @OneToMany(mappedBy = "userGroup", fetch = FetchType.EAGER)
    private List<User> users;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }


    public List<User> getUsers() {
        return users;
    }


    public void setUsers(List<User> users) {
        this.users = users;
    }

    @JsonIgnore
    public byte[] getLogo() {
        return logo;
    }

    @JsonIgnore
    public void setLogo(byte[] logo) {
        this.logo = logo;
    }
}
