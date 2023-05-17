package com.example.petclinic.service;


import java.util.List;

import com.example.petclinic.model.Owner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.petclinic.model.Owner;
import com.example.petclinic.repository.OwnerRepository;

@Service
public class OwnerService {

    @Autowired
    private OwnerRepository ownerRepository;

    public List<Owner> findAll() {
        return ownerRepository.findAll();
    }

    public Owner findById(long id) {
        return ownerRepository.findById(id).orElse(null);
    }

    public void save(Owner owner) {
        ownerRepository.save(owner);
    }

    public void deleteById(long id) {
        ownerRepository.deleteById(id);
    }
}
