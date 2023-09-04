package com.example.project.model;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "cases")
public class Case {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Please provide case name")
    private String name;

    @NotBlank(message = "Please provide case description")
    private String description;

    @NotBlank(message = "Please provide case date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String date;

    @Column(name = "weapon_id")
    private Long weaponId;

    @Column(name = "criminal_id")
    private Long criminalId;

    @Column(name = "case_number", nullable = false, columnDefinition = "VARCHAR(255) DEFAULT ''")
    private String caseNumber = ""; // Set the default value here

    public Case() {
        // Set default values in the constructor if needed
        this.caseNumber = "default_value";
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Long getWeaponId() {
        return weaponId;
    }

    public void setWeaponId(Long weaponId) {
        this.weaponId = weaponId;
    }

    public Long getCriminalId() {
        return criminalId;
    }

    public void setCriminalId(Long criminalId) {
        this.criminalId = criminalId;
    }

    public String getCaseNumber() {
        return caseNumber;
    }

    public void setCaseNumber(String caseNumber) {
        this.caseNumber = caseNumber;
    }


}