package com.example.project.controller;

import com.example.project.model.Criminal;
import com.example.project.repositories.CriminalRepository;
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
public class CriminalController {
    @Autowired
    private CriminalRepository criminalRepository;

    @GetMapping("/criminals")
    public String listCriminals(Model model) {
        List<Criminal> criminals = criminalRepository.findAll();
        model.addAttribute("criminals", criminals);
        UserDetailsService.addUserDetails(model);
        return "criminals";
    }

    @GetMapping("/add_criminal")
    public String showCriminalForm(Model model) {
        model.addAttribute("criminal", new Criminal());
        return "criminal_form";
    }

    @PostMapping("/add_criminal")
    public String addCriminal(@Valid Criminal criminal, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "criminal_form";
        }
        criminalRepository.save(criminal);
        return "redirect:/criminals";
    }

    @GetMapping("/edit_criminal/{id}")
    public String showEditCriminalForm(@PathVariable Long id, Model model) {
        Optional<Criminal> optionalCriminal = criminalRepository.findById(id);
        if (optionalCriminal.isPresent()) {
            model.addAttribute("criminal", optionalCriminal.get());
            return "criminal_form";
        } else {
            return "redirect:/criminals";
        }
    }

    @GetMapping("/delete_criminal/{id}")
    public String deleteCriminal(@PathVariable Long id) {
        criminalRepository.deleteById(id);
        return "redirect:/criminals";
    }
}