package com.ecommerce.project.services;

import com.ecommerce.project.dtos.AddressDTO;
import com.ecommerce.project.entities.AppUser;
import jakarta.validation.Valid;

import java.util.List;

public interface AddressService {
    AddressDTO createAddress(AddressDTO addressDTO, AppUser appUser);

    List<AddressDTO> getAddresses();

    AddressDTO getAddressById(Long addressId);

    List<AddressDTO> getAppUserAddresses(AppUser appUser);

    AddressDTO updateAddress(Long addressId, @Valid AddressDTO addressDTO);

    String deleteAddress(Long addressId);
}
