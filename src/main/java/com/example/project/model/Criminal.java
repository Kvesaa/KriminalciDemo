package com.example.project.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "criminals")
public class Criminal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Please provide criminal Firstname")
    private String name;

    @NotBlank(message = "Please provide criminal Lastname")
    private String lastName;

    @NotBlank(message = "State the age of the criminal")
    @Pattern(regexp = "\\d+", message = "Please enter a valid number for years")
    private String year;

    @Column(name = "details", columnDefinition = "VARCHAR(255) DEFAULT 'default_details'")
    private String details = "";


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}