package com.reswizard.Controllers;

import com.reswizard.Security.PersonDetail;
import com.reswizard.Services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

    private final AdminService adminService;

    @Autowired
    public MainController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/hello")
    public String sayHello(){
        return "MainPage";
    }

    @GetMapping("/showUserInfo")
    @ResponseBody
    public String showUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetail personDetails = (PersonDetail) authentication.getPrincipal();

        return personDetails.getUsername();
    }

    @GetMapping("/logout")
    public String logout(){
        return "redirect:/logout";
    }

    @GetMapping("/admin")
    public String adminPage(){
        adminService.doAdmin();
        return "Admin";
    }
}
