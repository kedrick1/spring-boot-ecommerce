package com.ecommerce.project.services;

import com.ecommerce.project.dtos.AddressDTO;
import com.ecommerce.project.entities.Address;
import com.ecommerce.project.entities.AppUser;
import com.ecommerce.project.exceptions.ResourceNotFoundException;
import com.ecommerce.project.repositories.AddressRepository;
import com.ecommerce.project.repositories.UserRepository;
import com.ecommerce.project.util.AuthUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    AuthUtil authUtil;

    @Autowired
    UserRepository userRepository;



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

    @Override
    public List<AddressDTO> getAddresses() {

        List<Address> addresses = addressRepository.findAll();
        return addresses.stream()
                .map(address -> modelMapper.map(address, AddressDTO.class))
                .toList();
    }

    @Override
    public AddressDTO getAddressById(Long addressId) {
        Address address = addressRepository.findById(addressId)
                .orElseThrow(() -> new ResourceNotFoundException("Address", "id", addressId));

        return modelMapper.map(address, AddressDTO.class);
    }

    @Override
    public List<AddressDTO> getAppUserAddresses(AppUser appUser) {

        List<Address> addresses = appUser.getAddresses();
        return addresses.stream()
                .map(address -> modelMapper.map(address, AddressDTO.class))
                .toList();

    }

    @Override
    public AddressDTO updateAddress(Long addressId, AddressDTO addressDTO) {

        Address addressFromDatabase = addressRepository.findById(addressId)
                .orElseThrow(() -> new ResourceNotFoundException("Address", "id", addressId));

        addressFromDatabase.setCity(addressDTO.getCity());
        addressFromDatabase.setCountry(addressDTO.getCountry());
        addressFromDatabase.setStreet(addressDTO.getStreet());
        addressFromDatabase.setBuildingName(addressDTO.getBuildingName());
        addressFromDatabase.setPostalCode(addressDTO.getPostalCode());
        addressFromDatabase.setBuildingName(addressDTO.getBuildingName());

        Address updatedAddress = addressRepository.save(addressFromDatabase);

        AppUser appUser = addressFromDatabase.getAppUser();
        appUser.getAddresses().removeIf(address -> address.getAddressId().equals(addressId));
        appUser.getAddresses().add(updatedAddress);
        userRepository.save(appUser);

        return modelMapper.map(updatedAddress, AddressDTO.class);
    }

    @Override
    public String deleteAddress(Long addressId) {

        Address addressFromDatabase = addressRepository.findById(addressId)
                .orElseThrow(() -> new ResourceNotFoundException("Address", "id", addressId));

        AppUser appUser = addressFromDatabase.getAppUser();
        appUser.getAddresses().removeIf(address -> address.getAddressId().equals(addressId));
        userRepository.save(appUser);

        addressRepository.delete(addressFromDatabase);

        return "Address successfully deleted with addressId: " + addressId;
    }


}
