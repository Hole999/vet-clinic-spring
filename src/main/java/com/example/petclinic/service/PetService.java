package com.example.petclinic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.petclinic.model.Pet;
import com.example.petclinic.repository.PetRepository;

@Service
public class PetService {

    @Autowired
    private PetRepository petRepository;

    public List<Pet> findAll() {
        return petRepository.findAll();
    }

    public Pet findById(long id) {
        return petRepository.findById(id).orElse(null);
    }

    public void save(Pet pet) {
        petRepository.save(pet);
    }

    public void deleteById(long id) {
        petRepository.deleteById(id);
    }
}
