package com.infotech.batch.model;

import javax.persistence.*;


@Entity
@Table(name = "BLS_members")
public class BLSMember {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private int id;
    private Integer principal_id;
    private Integer member_id;
    private String pin;
    private String relation;
    private String job_group;
    private String country;
    private String status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Integer getPrincipal_id() {
        return principal_id;
    }

    public void setPrincipal_id(Integer principal_id) {
        this.principal_id = principal_id;
    }

    public Integer getMember_id() {
        return member_id;
    }

    public void setMember_id(Integer member_id) {
        this.member_id = member_id;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public String getJob_group() {
        return job_group;
    }

    public void setJob_group(String job_group) {
        this.job_group = job_group;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
