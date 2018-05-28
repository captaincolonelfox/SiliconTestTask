package com.silicon.test.testtask.Controller;

import com.silicon.test.testtask.Model.Item;
import com.silicon.test.testtask.Model.Category;
import com.silicon.test.testtask.Repo.IItemsRepository;
import com.silicon.test.testtask.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@org.springframework.stereotype.Controller
public class Controller {

    @Autowired
    CategoryService categoryService;
    @Autowired
    IItemsRepository itemRepo;

    @RequestMapping("/viewCategories")
    public String showCategories(Model model) {
        model.addAttribute(categoryService.getAllCategories());
        model.addAttribute("newCat", false);
        return "viewCategories";
    }

    @GetMapping(value = "/viewCategories", params = "newCat")
    public String newCategory(Model model) {
        model.addAttribute("newCat", true);
        model.addAttribute(categoryService.getAllCategories());
        model.addAttribute("category", new Category());
        return "viewCategories";
    }

    @GetMapping(value = "/viewCategories", params = {"edit"})
    public String editCategory(Model model, @RequestParam("edit") String editCategory) {
        model.addAttribute("categoryToEdit", editCategory);
        model.addAttribute(categoryService.getAllCategories());
        model.addAttribute("category", new Category());
        return "viewCategories";
    }

    @PostMapping(value = "/viewCategories")
    public String addCategory(@ModelAttribute Category category, BindingResult bindingResult,
                              @RequestParam(value = "edit", required = false, defaultValue = "") String categoryToEdit) {
        if (bindingResult.hasErrors()) {
            return "viewCategories";
        }
        if (categoryToEdit != null && !categoryToEdit.equals(""))
            category.setName(categoryToEdit);
        this.categoryService.addCategory(category);
        return  "redirect:/viewCategories";
    }

    @RequestMapping("/viewItems")
    public String showItems(@RequestParam(name="category", required = false, defaultValue = "") String catParameter, Model model) {
        List<Item> items = catParameter.equals("") ? itemRepo.findAll() : itemRepo.findByItemIdCategory(catParameter);
        model.addAttribute("items", items);
        model.addAttribute("catParameter", catParameter);
        return "viewItems";
    }

}


