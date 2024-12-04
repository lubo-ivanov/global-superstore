package com.ltp.globalsuperstore.controllers;

import com.ltp.globalsuperstore.model.Item;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.ltp.globalsuperstore.Constants.CATEGORIES;

@Controller
public class StoreController {

    private List<Item> items = new ArrayList<>();

    public StoreController() {
    }

    @GetMapping("/")
    public String getForm(Model model, @RequestParam(required = false) String id) {
        model.addAttribute("item", getItem(id));
        model.addAttribute("categories", CATEGORIES);
        return "form";
    }

    private Item getItem(String id) {
        if (id == null) {
            return new Item();
        }
        return items.stream()
                .filter(item -> item.getId().contains(id))
                .findFirst()
                .orElse(new Item());
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
