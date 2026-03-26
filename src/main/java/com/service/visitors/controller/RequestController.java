package com.service.visitors.controller;

import com.service.visitors.service.RequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("role/director")
public class RequestController {

    private final RequestService requestService;

    @GetMapping("requests")
    public String getRequestPage(Model model){
        model.addAttribute("requests", this.requestService.findByStatus("WAITING"));
        return "role/director/request_list";
    }

    @PostMapping("requests/{id}/approve")
    public String approveRequest(@PathVariable Integer id) {
        this.requestService.updateRequestStatus(id, "APPROVE");
        return "redirect:/role/director/requests";
    }

    @PostMapping("requests/{id}/reject")
    public String rejectRequest(@PathVariable Integer id) {
        this.requestService.updateRequestStatus(id, "REJECT");
        return "redirect:/role/director/requests";
    }

}
