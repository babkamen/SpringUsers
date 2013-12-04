package org.geymer.users.entity;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: babkamen
 * Date: 27.11.13
 */
@Entity
public class Role {

    @Id
    @GeneratedValue
    private int id;
    @Column(name = "name")
    private String name;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId")
    private User user;


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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


}
