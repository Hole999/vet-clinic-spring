package com.example.petclinic.controller;

import com.example.petclinic.model.Owner;
import com.example.petclinic.repository.OwnerRepository;
import com.example.petclinic.service.OwnerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class OwnerController {

    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @Autowired
    private OwnerRepository ownerRepository;

    @GetMapping("/owners")
    public String showOwners(Model model) {
        model.addAttribute("owners", ownerRepository.findAll());
        return "owners";
    }

    @GetMapping("/owners/new")
    public String showAddOwnerForm(Model model) {
        model.addAttribute("owner", new Owner());
        return "add-owner";
    }

    @PostMapping("/owners/new")
    public String addOwner(@Valid Owner owner, BindingResult result) {
        if (result.hasErrors()) {
            return "add-owner";
        } else {
            ownerRepository.save(owner);
            return "redirect:/owners";
        }
    }

    @GetMapping("/owners/edit/{id}")
    public String showEditOwnerForm(@PathVariable("id") Long id, Model model) {
        Owner owner = ownerService.findById(id);
        if (owner == null) {
            return "redirect:/owners";
        }
        model.addAttribute("owner", owner);
        return "edit-owner";
    }

    @PostMapping("/owners/edit/{id}")
    public String editOwner(@PathVariable("id") Long id, @Valid Owner owner, BindingResult result) {
        if (result.hasErrors()) {
            return "edit-owner";
        } else {
            Owner existingOwner = ownerService.findById(id);
            if (existingOwner != null) {
                existingOwner.setFirstName(owner.getFirstName());
                existingOwner.setLastName(owner.getLastName());
                existingOwner.setAddress(owner.getAddress());
                existingOwner.setCity(owner.getCity());
                existingOwner.setTelephone(owner.getTelephone());
                existingOwner.setPets(owner.getPets());
                ownerService.save(existingOwner);
            }
            return "redirect:/owners";
        }
    }

    @GetMapping("/owners/delete/{id}")
    public String deleteOwner(@PathVariable("id") Long id) {
        ownerRepository.deleteById(id);
        return "redirect:/owners";
    }
}
