package ru.GalkinAllan.Currency.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.GalkinAllan.Currency.models.Currency;
import ru.GalkinAllan.Currency.services.CurrencyService;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping()
public class CurrencyController {

    private final CurrencyService currencyService;


    @Autowired

    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping("/currencies")
    public String index(Model model) {
        return listByPage(model, 1);
    }

    @GetMapping("/currencies/page/{pageNumber}")
    public String listByPage(Model model, @PathVariable("pageNumber") int currentPage) {
        Page<Currency> page = currencyService.findall(currentPage);
        long totalElements = page.getTotalElements();
        int totalPages = page.getTotalPages();
        List<Currency> list = page.getContent();
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalItems", totalElements);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currency", list);
        return "currency";
    }

    @GetMapping("/currency/{charcode}")
    public String showByCode(@PathVariable("charcode") String charcode, Model model) {
        model.addAttribute("currency", currencyService.findOne(charcode));
        return "/show";
    }


}
