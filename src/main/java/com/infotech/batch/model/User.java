package com.infotech.batch.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user")
public class User {

    private static final long serialVersionUID = -6402068923614583448L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String password;
    private String firstName;
    private String lastName;
    private String middleName;
    private String gender;
    private Date date_of_birth;
    private String photo;
    private String nationality;
    private String national_id;
    private String passport_no;
    private String mobile_phone_number;
    private String home_phone_number;
    private String residential_county;
    private String residential_address;
    private String postal_address;
    private String email;
    private String marital_status;
    private String occupation;
    private String dialing_code;


    @OneToMany(mappedBy = "user", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Set<BLSMember> bls_member = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Set<RoleUser> role_user = new HashSet<>();


    public User() {
    }

    public User(String password, String firstName, String lastName, String middleName, String gender, Date date_of_birth, String photo, String nationality, String national_id, String passport_no, String mobile_phone_number, String home_phone_number, String residential_county, String residential_address, String postal_address, String email, String marital_status, String occupation, String dialing_code) {
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.gender = gender;
        this.date_of_birth = date_of_birth;
        this.photo = photo;
        this.nationality = nationality;
        this.national_id = national_id;
        this.passport_no = passport_no;
        this.mobile_phone_number = mobile_phone_number;
        this.home_phone_number = home_phone_number;
        this.residential_county = residential_county;
        this.residential_address = residential_address;
        this.postal_address = postal_address;
        this.email = email;
        this.marital_status = marital_status;
        this.occupation = occupation;
        this.dialing_code = dialing_code;
    }

    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(Date date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getNational_id() {
        return national_id;
    }

    public void setNational_id(String national_id) {
        this.national_id = national_id;
    }

    public String getPassport_no() {
        return passport_no;
    }

    public void setPassport_no(String passport_no) {
        this.passport_no = passport_no;
    }

    public String getMobile_phone_number() {
        return mobile_phone_number;
    }

    public void setMobile_phone_number(String mobile_phone_number) {
        this.mobile_phone_number = mobile_phone_number;
    }

    public String getHome_phone_number() {
        return home_phone_number;
    }

    public void setHome_phone_number(String home_phone_number) {
        this.home_phone_number = home_phone_number;
    }

    public String getResidential_county() {
        return residential_county;
    }

    public void setResidential_county(String residential_county) {
        this.residential_county = residential_county;
    }

    public String getResidential_address() {
        return residential_address;
    }

    public void setResidential_address(String residential_address) {
        this.residential_address = residential_address;
    }

    public String getPostal_address() {
        return postal_address;
    }

    public void setPostal_address(String postal_address) {
        this.postal_address = postal_address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMarital_status() {
        return marital_status;
    }

    public void setMarital_status(String marital_status) {
        this.marital_status = marital_status;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getDialing_code() {
        return dialing_code;
    }

    public void setDialing_code(String dialing_code) {
        this.dialing_code = dialing_code;
    }


    @Override
    public String toString() {
        return "User{" +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender=" + gender +
                ", date_of_birth=" + date_of_birth +
                ", photo='" + photo + '\'' +
                ", nationality='" + nationality + '\'' +
                ", national_id='" + national_id + '\'' +
                ", passport_no='" + passport_no + '\'' +
                ", mobile_phone_number='" + mobile_phone_number + '\'' +
                ", home_phone_number='" + home_phone_number + '\'' +
                ", residential_county='" + residential_county + '\'' +
                ", residential_address='" + residential_address + '\'' +
                ", postal_address='" + postal_address + '\'' +
                ", email='" + email + '\'' +
                ", marital_status='" + marital_status + '\'' +
                ", occupation='" + occupation + '\'' +
                ", dialing_code='" + dialing_code + '\'' +
                '}';
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }
}
