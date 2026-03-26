package com.service.visitors.controller;

import com.service.visitors.service.GuardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("role/guard")
public class GuardController {

    private final GuardService guardService;

    @GetMapping("visitors")
    public String getApprovedVisitorsPage(Model model) {
        model.addAttribute("visitors", guardService.findActiveVisitors());
        return "role/guard/visitor_list";
    }

    @PostMapping("visitors/{id}/entry")
    public String markEntry(@PathVariable Integer id, @RequestParam String passportData) {
        guardService.markEntry(id, passportData);
        return "redirect:/role/guard/visitors";
    }

    @PostMapping("visitors/{id}/exit")
    public String markExit(@PathVariable Integer id) {
        guardService.markExit(id);
        return "redirect:/role/guard/visitors";
    }

    @GetMapping("archive")
    public String getArchivePage(Model model) {
        model.addAttribute("archive", guardService.findAllArchive());
        return "role/guard/archive";
    }
}