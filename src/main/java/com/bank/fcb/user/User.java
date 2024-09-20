package com.bank.fcb.user;
import com.bank.fcb.post.Post;

import java.time.LocalDate;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Size;

@Entity(name = "user_details")
public class User {

    protected User() {
    }

    @Id
    @GeneratedValue
    private Integer id;


    @Size(min = 2, message = "Name should have atleast 2 characters")
    private String name;

    @JsonIgnore
    private String password;

    @JsonProperty("user_email")
    private String email;
    private String phone;
    private LocalDate dob;

    @OneToMany(mappedBy = "user")
    private List<Post> posts;


    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public User(String name, String password, String email, String phone, LocalDate dob) {
        super();
        this.name = name;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.dob = dob;
    }

    public LocalDate getDob() {
        return dob;
    }
    public void setDob(LocalDate dob) {
        this.dob = dob;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User [name=" + name + ", password=" + password + ", email=" + email + ", phone=" + phone + ", dob="
                + dob + "]";
    }
}
