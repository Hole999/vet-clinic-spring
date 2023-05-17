package com.example.petclinic.controller;

import com.example.petclinic.model.Pet;
import com.example.petclinic.service.OwnerService;
import com.example.petclinic.service.PetService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
public class PetController {
    private final PetService petService;
    private final OwnerService ownerService;

    public PetController(PetService petService, OwnerService ownerService) {
        this.petService = petService;
        this.ownerService = ownerService;
    }

    @GetMapping("/pets")
    public String getPets(Model model) {
        model.addAttribute("pets", petService.findAll());
        return "pets";
    }

    @GetMapping("/pets/new")
    public String addPet(Model model) {
        model.addAttribute("pet", new Pet());
        model.addAttribute("owners", ownerService.findAll());
        return "add-pet";
    }

    @PostMapping("/pets/new")
    public String savePet(@ModelAttribute("pet") @Validated Pet pet, BindingResult result) {
        if (result.hasErrors()) {
            return "add-pet";
        }
        petService.save(pet);
        return "redirect:/pets";
    }

    @GetMapping("/pets/edit/{id}")
    public String editPet(@PathVariable("id") long id, Model model) {
        Pet pet = petService.findById(id);
        if (pet == null) {
            return "redirect:/pets";
        }
        model.addAttribute("pet", pet);
        model.addAttribute("owners", ownerService.findAll());
        return "edit-pet";
    }

    @PostMapping("/pets/edit/{id}")
    public String updatePet(@PathVariable("id") long id, @ModelAttribute("pet") @Validated Pet pet, BindingResult result) {
        if (result.hasErrors()) {
            return "edit-pet";
        }
        petService.save(pet);
        return "redirect:/pets";
    }

    @GetMapping("/pets/delete/{id}")
    public String deletePet(@PathVariable("id") long id) {
        petService.deleteById(id);
        return "redirect:/pets";
    }
}
