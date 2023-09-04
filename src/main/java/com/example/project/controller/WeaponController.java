package com.example.project.controller;

import com.example.project.model.Weapon;
import com.example.project.repositories.WeaponRepository;
import com.example.project.services.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
public class WeaponController {
    @Autowired
    private WeaponRepository weaponRepository;

    @GetMapping("/weapons")
    public String listWeapons(Model model) {
        List<Weapon> weapons = weaponRepository.findAll();
        model.addAttribute("weapons", weapons);
        UserDetailsService.addUserDetails(model);
        return "weapons";
    }

    @GetMapping("/add_weapon")
    public String showWeaponForm(Model model) {
        model.addAttribute("weapon", new Weapon());
        return "weapon_form";
    }

    @PostMapping("/add_weapon")
    public String addWeapon(@Valid Weapon weapon, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "weapon_form";
        }
        weaponRepository.save(weapon);
        return "redirect:/weapons";
    }

    @GetMapping("/edit_weapon/{id}")
    public String showEditWeaponForm(@PathVariable Long id, Model model) {
        Optional<Weapon> optionalWeapon = weaponRepository.findById(id);
        if (optionalWeapon.isPresent()) {
            model.addAttribute("weapon", optionalWeapon.get());
            return "weapon_form";
        } else {
            return "redirect:/weapons";
        }
    }

    @GetMapping("/delete_weapon/{id}")
    public String deleteWeapon(@PathVariable Long id) {
        weaponRepository.deleteById(id);
        return "redirect:/weapons";
    }
}