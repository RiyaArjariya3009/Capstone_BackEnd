package com.restaurant.userservice.entities;

import com.restaurant.userservice.enums.RoleType;

import javax.persistence.*;

@Table(name = "users")
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(unique = true,nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    private RoleType role;



    public User()
    {
         super();
    }

    public User(int id, String username, String password, String email, RoleType role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
       // this.addressId=addressId;
        this.role = role;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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


    public RoleType getRole() {
        return role;
    }

    public void setRole(RoleType role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                '}';
    }
}