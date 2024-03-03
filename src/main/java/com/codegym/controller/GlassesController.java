package com.codegym.controller;

import com.codegym.model.Glasses;
import com.codegym.model.GlassesForm;
import com.codegym.service.IGlassesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/glasses")
@PropertySource("classpath:upload_file.properties")

public class GlassesController {

    @Value("${upload}")
    private String upload;

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
    public String save(GlassesForm glassesForm) throws IOException {
        //tai file len
        //lay file
        MultipartFile file = glassesForm.getImg();
        //lay ten
        String nameImg = file.getOriginalFilename();
        //copy file vao trong thu muc
        FileCopyUtils.copy(file.getBytes(), new File(upload+nameImg));
        // luu du lieu vao trong db
        Glasses glasses = new Glasses();
        glasses.setCode(glassesForm.getCode());
        glasses.setDescription(glassesForm.getDescription());
        glasses.setPrice(glassesForm.getPrice());
        glasses.setColor(glassesForm.getColor());
        glasses.setImg(nameImg);
        glassesService.save(glasses);
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