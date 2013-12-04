package org.geymer.users.entity;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: babkamen
 * Date: 14.11.13
 * Time: 13:45
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "user")
public class User {
    @javax.persistence.Column(name = "id")
    @GeneratedValue
    @Id
    private int id;
    @NotEmpty(message = "This field is required")
    @javax.persistence.Column(name = "name")
    private String name;
    @NotEmpty(message = "This field is required")
    @Email
    @javax.persistence.Column(name = "email")
    private String email;
    @NotEmpty(message = "This field is required")
    @javax.persistence.Column(name = "password")
    private String password;
    @NotEmpty(message = "This field is required")
    @javax.persistence.Column(name = "about")
    private String about;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userGroupId")
    private UserGroup userGroup;


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Role> roles;

    @Column(name = "userGroupId", insertable = false, updatable = false, nullable = true)
    Integer userGroupId;

    public Integer getUserGroupId() {
        return userGroupId;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", about='" + about + '\'' +
                ", userGroup=" + userGroup +
                ", roles=" + roles +
                ", userGroupId=" + userGroupId +
                '}';
    }

    public void setUserGroupId(Integer userGroupId) {
        this.userGroupId = userGroupId;
    }


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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }


    public UserGroup getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(UserGroup usergroup) {
        this.userGroup = usergroup;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
