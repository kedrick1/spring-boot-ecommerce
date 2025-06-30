package com.ecommerce.project.services;

import com.ecommerce.project.dtos.AddressDTO;
import com.ecommerce.project.entities.Address;
import com.ecommerce.project.entities.AppUser;
import com.ecommerce.project.repositories.AddressRepository;
import com.ecommerce.project.util.AuthUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    AuthUtil authUtil;



    @Override
    public AddressDTO createAddress(AddressDTO addressDTO, AppUser appUser) {

        Address address = modelMapper.map(addressDTO, Address.class);
        List<Address> addressList = appUser.getAddresses();
        addressList.add(address);
        appUser.setAddresses(addressList);

        address.setAppUser(appUser);
        Address savedAddress = addressRepository.save(address);
        return modelMapper.map(savedAddress, AddressDTO.class);

    }
}
