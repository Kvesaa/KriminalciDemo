package com.example.project.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "weapons")
public class Weapon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Please provide weapon name")
    private String name;

    @Column(name = "serial_number", nullable = false, columnDefinition = "VARCHAR(255) DEFAULT 'default_value'")
    private String serialNumber = "";

    @Column(name = "details", columnDefinition = "VARCHAR(255) DEFAULT 'default_details'")
    private String details = "";

    @Column(name = "type", columnDefinition = "VARCHAR(255) DEFAULT 'default_type'")
    private String type = "";

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

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
