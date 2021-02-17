package com.example.petclinicmicroservice.service;

import com.example.petclinicmicroservice.model.Owner;
import com.example.petclinicmicroservice.model.dto.OwnerCreateRequestDTO;
import com.example.petclinicmicroservice.model.dto.OwnerUpdateDTO;
import com.example.petclinicmicroservice.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class OwnerService {
    @Autowired
    private OwnerRepository ownerRepository;

    public Owner create(OwnerCreateRequestDTO ownerCreateRequestDTO) {
        Owner o = new Owner();
        Long id = ownerCreateRequestDTO.getId();
        if(id == null) {
            id = new Date().getTime();
        }
        o.setId(id);
        o.setFirstName(ownerCreateRequestDTO.getFirstName());
        o.setLastName(ownerCreateRequestDTO.getLastName());
        o.setEmail(ownerCreateRequestDTO.getEmail());
        o = ownerRepository.save(o);
        return o;
    }

    public List<Owner> getOwners() {
        return ownerRepository.findAll();
    }

    public Owner getOwner(Long id) {
        return ownerRepository.findById(id).orElse(null);
    }

    public Owner updateOwner(Owner o, OwnerUpdateDTO ownerUpdateDTO) {
        o.setFirstName(ownerUpdateDTO.getFirstName());
        o.setLastName(ownerUpdateDTO.getLastName());
        o.setEmail(ownerUpdateDTO.getEmail());
        return ownerRepository.save(o);
    }

    public void deleteOwner(Long id) {
        Owner o = this.getOwner(id);
        if(o == null) return;
        ownerRepository.delete(o);
    }
}
