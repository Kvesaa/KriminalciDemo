package com.example.project.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message="Please enter your email address.")
    @Email(message = "Please enter a valid email address.")
    @Column(unique = true, nullable = false, length = 50)
    private String email;

    @NotBlank(message="Please enter your password.")
    @Column(nullable = false)
    private String password;
    @NotBlank(message="Please repeat your password.")
    private String passwordRepeat;

    private boolean passwordsEqual;

    public void setPasswordsEqual(boolean passwordsEqual) {
        this.passwordsEqual = passwordsEqual;
    }

    public String getPasswordRepeat() {
        return passwordRepeat;
    }

    public void setPasswordRepeat(String passwordRepeat) {
        this.passwordRepeat = passwordRepeat;
    }


    @Size(min=2, max=30, message="Your name must be between 2 and 30 characters long.")
    @Column(nullable = false, length = 30)
    private String firstname;


    @Size(min=2, max=30, message="Your last name must be between 2 and 30 characters long.")
    @Column(nullable = false, length = 30)
    private String lastname;

    @Column(nullable = false, columnDefinition = "INT DEFAULT 0")
    private int roleId;

    public User() {}

    public User(Long id, String email, String password, String firstname, String lastname) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    @AssertTrue(message="Passwords must match.")
    public boolean isPasswordsEqual () {
        try {
            return this.password.equals(this.passwordRepeat);
        } catch (NullPointerException e) {
            return false;
        }
    }
}
