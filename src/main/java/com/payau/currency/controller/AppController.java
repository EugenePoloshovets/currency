package com.payau.currency.controller;

import com.payau.currency.service.USDRate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;

@Controller
public class AppController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/currency")
    public String getUSDRate(Model model) throws IOException {
        USDRate usdrate = new USDRate();
        model.addAttribute("usdRate", usdrate.getDollarCourse());
        return "currency";
    }
}
