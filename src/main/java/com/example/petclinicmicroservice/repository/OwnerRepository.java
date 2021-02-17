package com.example.petclinicmicroservice.repository;

import com.example.petclinicmicroservice.model.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OwnerRepository extends JpaRepository<Owner,Long>  {
    List<Owner> findByFirstNameAndLastName(String firstName,String lastName);
}
