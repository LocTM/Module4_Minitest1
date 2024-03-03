package com.codegym.controller;

import com.codegym.model.Glasses;
import com.codegym.model.GlassesForm;
import com.codegym.service.IGlassesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/glasses")
public class GlassesController {
    @Autowired
    private IGlassesService glassesService;

    @GetMapping("")
    public String index(Model model) {
        List<Glasses> glassesList = glassesService.findAll();
        model.addAttribute("glasses", glassesList);
        return "/index";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("glasses", new GlassesForm());
        return "/create";
    }

    @PostMapping("/save")
    public String save(GlassesForm glasses) {
        GlassesForm glasses1 = glasses;
//        glassesService.save(glasses);
        return "redirect:/glasses";
    }

    @GetMapping("/{id}/edit")
    public String update(@PathVariable int id, Model model) {
        model.addAttribute("glasses", glassesService.findById(id));
        return "/update";
    }

    @PostMapping("/update")
    public String update(Glasses glasses) {
        glassesService.save(glasses);
        return "redirect:/glasses";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable int id, Model model) {
        model.addAttribute("glasses", glassesService.findById(id));
        return "/delete";
    }

    @PostMapping("/delete")
    public String delete(Glasses glasses, RedirectAttributes redirect) {
        glassesService.remove(glasses.getId());
        redirect.addFlashAttribute("success", "Removed customer successfully!");
        return "redirect:/glasses";
    }

    @GetMapping("/{id}/view")
    public String view(@PathVariable int id, Model model) {
        model.addAttribute("glasses", glassesService.findById(id));
        return "/view";
    }
}