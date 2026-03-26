package com.service.visitors.controller;

import com.service.visitors.controller.payload.NewVisitorPayload;
import com.service.visitors.entity.Visitor;
import com.service.visitors.service.RequestService;
import com.service.visitors.service.VisitorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("role/worker")
public class VisitorController {

    private final VisitorService visitorService;
    private final RequestService requestService;

    @GetMapping(value = "list")
    public String getVisitorList(Model model){
        model.addAttribute("visitors", this.visitorService.findAllVisitors());
        return "role/worker/list";
    }

    @GetMapping("create")
    public String getNewVisitorPage(){
        return "role/worker/new_visitor";
    }

    @PostMapping("create")
    public String addVisitor(NewVisitorPayload payload){
        Visitor visitor = this.visitorService.addVisitor(payload.fullName(), payload.organization(),
                payload.department(), payload.date());
        return "redirect:/role/worker/list";
    }

    @PostMapping("submit")
    public String submitRequest(){
        List<Visitor> currentVisitors = this.visitorService.findAllVisitors();
        this.requestService.create(currentVisitors, "WAITING");
        this.visitorService.deleteAllVisitors();
        return "role/worker/new_visitor";
    }

}
