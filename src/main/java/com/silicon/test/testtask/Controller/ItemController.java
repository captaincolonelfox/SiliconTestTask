package com.silicon.test.testtask.Controller;

import com.silicon.test.testtask.Model.Item;
import com.silicon.test.testtask.Repo.IItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ItemController {

    @Autowired
    IItemsRepository itemRepo;

    @RequestMapping("/viewItems")
    public String showItems(@RequestParam(name="category", required = false, defaultValue = "") String catParameter, Model model) {
        List<Item> items = catParameter.equals("") ? itemRepo.findAll() : itemRepo.findByItemIdCategory(catParameter);
        model.addAttribute("items", items);
        model.addAttribute("catParameter", catParameter);
        return "viewItems";
    }

}
