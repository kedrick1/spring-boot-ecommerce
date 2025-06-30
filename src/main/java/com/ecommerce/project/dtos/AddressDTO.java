package com.ecommerce.project.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO {


    private Long addressId;

    private String street;

    private String buildingName;

    private String city;

    private String province;

    private String country;

    private String postalCode;
}
