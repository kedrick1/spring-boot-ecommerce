package com.ecommerce.project.services;

import com.ecommerce.project.dtos.AddressDTO;
import com.ecommerce.project.entities.AppUser;

public interface AddressService {
    AddressDTO createAddress(AddressDTO addressDTO, AppUser appUser);
}
