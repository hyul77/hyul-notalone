package com.webprojectv1.notalone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.webprojectv1.notalone.product.ProductService;

@Controller
public class MainController {

    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public String front(Model model) {
        model.addAttribute("productList", productService.selectProductAll());
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/join")
    public String join() {
        return "join";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin")
    public String admin(Model model) {
        model.addAttribute("productList", productService.selectProductAll());

        return "redirect:http://localhost:8000/admin";
    }

    // @PreAuthorize("isAuthenticated()")
    // @GetMapping("/mypage")
    // public String mypage(Model model, Principal principal) {
    //     SiteUser user = userService.getUser(principal.getName());
    //     model.addAttribute("user", user);
    //     return "mypage";
    // }

}
