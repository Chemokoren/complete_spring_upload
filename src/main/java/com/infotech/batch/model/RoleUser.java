package com.infotech.batch.model;

import javax.persistence.*;

@Entity
@Table(name = "role_user")
public class RoleUser {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private int id;
    private String role_id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public String getRole_id() {
        return role_id;
    }

    public void setRole_id(String role_id) {
        this.role_id = role_id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
