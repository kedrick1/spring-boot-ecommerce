package com.ecommerce.project.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="address")
@Data
@NoArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="address_id")
    private Long addressId;

    @NotBlank
    @Size(min = 5, message = "Street name must be at least 5 characters")
    @Column(name="street")
    private String street;

    @NotBlank
    @Size(min=5, message = "Building name must be at least 5 characters")
    @Column(name = "buidling_name")
    private String buildingName;

    @NotBlank
    @Size(min=4, message = "City name must be at least 4 characters")
    @Column(name = "city")
    private String city;

    @NotBlank
    @Size(min=2, message = "Province must be at least 2 characters")
    @Column(name = "province")
    private String province;

    @NotBlank
    @Size(min=2, message = "Country must be at least 2 characters")
    @Column(name = "country")
    private String country;

    @NotBlank
    @Size(min=6, max = 6, message = "Postal code must be 6 characters long")
    @Column(name = "postal_code")
    private String postalCode;

    @ToString.Exclude
    @ManyToMany(mappedBy = "addresses")
    private List<AppUser> appUsers = new ArrayList<>();

    public Address(String street, String buildingName, String city, String province, String country, String postalCode) {
        this.street = street;
        this.buildingName = buildingName;
        this.city = city;
        this.province = province;
        this.country = country;
        this.postalCode = postalCode;
    }
}
