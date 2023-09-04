package com.example.project.controller;

import com.example.project.model.Case;
import com.example.project.model.Criminal;
import com.example.project.model.Weapon;
import com.example.project.repositories.CaseRepository;
import com.example.project.repositories.CriminalRepository;
import com.example.project.repositories.WeaponRepository;
import com.example.project.services.UserDetailsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class CaseController {
    @Autowired
    private CaseRepository caseRepository;

    @Autowired
    private WeaponRepository weaponRepository;

    @Autowired
    private CriminalRepository criminalRepository;

    @GetMapping("/cases")
    public String listCases(Model model) {
        List<Case> cases = caseRepository.findAll();
        model.addAttribute("cases", cases);
        UserDetailsService.addUserDetails(model);
        return "cases";
    }

    @GetMapping("/add_case")
    public String showCaseForm(Model model) {
        List<Weapon> weapons = weaponRepository.findAll();
        List<Criminal> criminals = criminalRepository.findAll();
        model.addAttribute("caseObj", new Case());
        model.addAttribute("weapons", weapons);
        model.addAttribute("criminals", criminals);
        return "case_form";
    }

    @PostMapping("/add_case")
    public String addCase(@Valid Case caseObj, BindingResult result, Model model) {
        if (result.hasErrors()) {
            List<Weapon> weapons = weaponRepository.findAll();
            List<Criminal> criminals = criminalRepository.findAll();
            model.addAttribute("weapons", weapons);
            model.addAttribute("criminals", criminals);
            return "case_form";
        }

        caseRepository.save(caseObj);
        return "redirect:/cases";
    }

    @GetMapping("/edit_case/{id}")
    public String showEditCaseForm(@PathVariable Long id, Model model) {
        Optional<Case> optionalCase = caseRepository.findById(id);
        if (optionalCase.isPresent()) {
            List<Weapon> weapons = weaponRepository.findAll();
            List<Criminal> criminals = criminalRepository.findAll();
            model.addAttribute("caseObj", optionalCase.get());
            model.addAttribute("weapons", weapons);
            model.addAttribute("criminals", criminals);
            return "case_form";
        } else {
            return "redirect:/cases";
        }
    }

    @PostMapping("/edit_case/{id}")
    public String editCase(@PathVariable Long id, @Valid Case caseObj, BindingResult result, Model model) {
        if (result.hasErrors()) {
            List<Weapon> weapons = weaponRepository.findAll();
            List<Criminal> criminals = criminalRepository.findAll();
            model.addAttribute("weapons", weapons);
            model.addAttribute("criminals", criminals);
            return "case_form";
        }

        caseRepository.save(caseObj);
        return "redirect:/cases";
    }

    @GetMapping("/delete_case/{id}")
    public String deleteCase(@PathVariable Long id) {
        caseRepository.deleteById(id);
        return "redirect:/cases";
    }
}