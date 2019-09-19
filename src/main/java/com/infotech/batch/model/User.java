package com.infotech.batch.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "users")
@SecondaryTables({
        @SecondaryTable(name = "role_user",
                pkJoinColumns = @PrimaryKeyJoinColumn(name = "user_id")),
        @SecondaryTable(name = "BLS_members",
                pkJoinColumns = @PrimaryKeyJoinColumn(name = "user_id")),
        @SecondaryTable(name = "BLS_categories",
                pkJoinColumns = @PrimaryKeyJoinColumn(name = "user_id")),
        @SecondaryTable(name = "BLS_user_category",
                pkJoinColumns = @PrimaryKeyJoinColumn(name = "user_id")),
})
public class User implements Serializable {

    private static final long serialVersionUID = -6402068923614583448L;

    @Id
    @Column(name="user_id")
    private Long id;

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
    private String t_one;
    private String t_two;

    public String getT_one() {
        return t_one;
    }

    public void setT_one(String t_one) {
        this.t_one = t_one;
    }

    public String getT_two() {
        return t_two;
    }

    public void setT_two(String t_two) {
        this.t_two = t_two;
    }


    @Column(table = "role_user")
    private String role_id;


    @Column(table = "BLS_members")
    private Integer principal_id;
    @Column(table = "BLS_members")
    private Integer member_id;
    @Column(table = "BLS_members")
    private String pin;
    @Column(table = "BLS_members")
    private String relation;
    @Column(table = "BLS_members")
    private String job_group;
    @Column(table = "BLS_members")
    private String country;
    @Column(table = "BLS_members")
    private String status;


    @Column(table = "BLS_categories")
    private Integer scheme_id;
    @Column(table = "BLS_categories")
    private Integer category_name;


    @Column(table = "BLS_user_category")
    private Integer category_id;

    public User() {
    }

    public User(String password, String firstName, String lastName, String middleName, String gender, Date date_of_birth, String national_id, String mobile_phone_number) {
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.gender = gender;
        this.date_of_birth = date_of_birth;
        this.national_id = national_id;
        this.mobile_phone_number = mobile_phone_number;

    }

    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Integer getPrincipal_id() {
        return principal_id;
    }

    public void setPrincipal_id(Integer principal_id) {
        this.principal_id = principal_id;
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

    public Long getUser_id() {
        return id;
    }

    public void setUser_id(Long user_id) {
        this.id = user_id;
    }

    public String getRole_id() {
        return role_id;
    }

    public void setRole_id(String role_id) {
        this.role_id = role_id;
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

    public Integer getScheme_id() {
        return scheme_id;
    }

    public void setScheme_id(Integer scheme_id) {
        this.scheme_id = scheme_id;
    }

    public Integer getCategory_name() {
        return category_name;
    }

    public void setCategory_name(Integer category_name) {
        this.category_name = category_name;
    }

    public Integer getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Integer category_id) {
        this.category_id = category_id;
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
                ", user_id='" + id + '\'' +
                ", role_id='" + role_id + '\'' +
                ", member_id='" + member_id + '\'' +
                ", pin='" + pin + '\'' +
                ", relation='" + relation + '\'' +
                ", job_group='" + job_group + '\'' +
                ", country='" + country + '\'' +
                ", status='" + status + '\'' +
                ", scheme_id=" + scheme_id +
                ", category_name=" + category_name +
                ", category_id=" + category_id +
                '}';
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }
}
