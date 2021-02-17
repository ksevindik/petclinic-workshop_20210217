package com.example.petclinicmicroservice.controller;

import com.example.petclinicmicroservice.model.Owner;
import com.example.petclinicmicroservice.model.dto.OwnerCreateRequestDTO;
import com.example.petclinicmicroservice.model.dto.OwnerResponseDTO;
import com.example.petclinicmicroservice.model.dto.OwnerUpdateDTO;
import com.example.petclinicmicroservice.model.dto.OwnersResponseDTO;
import com.example.petclinicmicroservice.repository.OwnerRepository;
import com.example.petclinicmicroservice.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/rest/owners")
public class OwnerRestController {

    @Autowired
    private OwnerService ownerService;

    @PostMapping
    public ResponseEntity<Void> createOwner(@RequestBody OwnerCreateRequestDTO ownerCreateRequestDTO) throws URISyntaxException {
        Owner o = ownerService.create(ownerCreateRequestDTO);
        String url = MvcUriComponentsBuilder.fromController(OwnerRestController.class).build().toUriString() + "/"+
                o.getId();
        return ResponseEntity.created(new URI(url)).build();
    }

    @GetMapping()
    public List<OwnersResponseDTO> getOwners() {
        List<OwnersResponseDTO> dtoList = new ArrayList<>();
        List<Owner> owners = ownerService.getOwners();
        for (Owner o:owners) {
            OwnersResponseDTO dto = new OwnersResponseDTO();
            dto.setId(o.getId());
            dto.setEmail(o.getEmail());
            dtoList.add(dto);
        }
        return dtoList;
    }

    @GetMapping("/{id}")
    public ResponseEntity<OwnerResponseDTO> getOwner(@PathVariable("id") Long id) {
        Owner o = ownerService.getOwner(id);
        if(o == null) {
            return ResponseEntity.notFound().build();
        }
        OwnerResponseDTO dto = new OwnerResponseDTO();
        dto.setFirstName(o.getFirstName());
        dto.setLastName(o.getLastName());
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateOwner(@PathVariable("id") Long id, @RequestBody OwnerUpdateDTO ownerUpdateDTO) throws URISyntaxException {
        Owner o = ownerService.getOwner(id);
        if(o == null) {
            OwnerCreateRequestDTO dto = new OwnerCreateRequestDTO();
            dto.setId(id);
            dto.setFirstName(ownerUpdateDTO.getFirstName());
            dto.setEmail(ownerUpdateDTO.getEmail());
            dto.setLastName(ownerUpdateDTO.getLastName());
            return this.createOwner(dto);
        } else {
            ownerService.updateOwner(o, ownerUpdateDTO);
            return ResponseEntity.noContent().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOwner(@PathVariable("id") Long id) {
        ownerService.deleteOwner(id);
        return ResponseEntity.noContent().build();
    }

}
