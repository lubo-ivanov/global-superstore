package com.ltp.globalsuperstore.controllers;

import com.ltp.globalsuperstore.model.Item;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

import static com.ltp.globalsuperstore.Constants.CATEGORIES;

@Controller
public class StoreController {

    private List<Item> items = new ArrayList<>();

    public StoreController() {
    }

    @GetMapping("/")
    public String getForm(Model model) {
        model.addAttribute("categories", CATEGORIES);
        model.addAttribute("item", new Item());
        return "form";
    }

    @GetMapping("/inventory")
    public String getInventory(Model model) {
        model.addAttribute("items", items);
        return "inventory";
    }

    @PostMapping("/submitItem")
    public String handleSubmit(Item item) {
        items.add(item);
        return "redirect:/inventory";
    }

}
