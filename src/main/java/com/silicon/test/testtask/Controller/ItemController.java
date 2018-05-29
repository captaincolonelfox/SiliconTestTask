package com.silicon.test.testtask.Controller;

import com.silicon.test.testtask.Model.Item;
import com.silicon.test.testtask.Model.ItemId;
import com.silicon.test.testtask.Repo.IItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(value = "/viewItems", params = {"edit", "cat", "name"})
    public String editItems(@RequestParam("cat") String catName,
                            @RequestParam("name") String itemName,
                            Model model) {
        Item i = itemRepo.findByItemId(new ItemId(catName, itemName));
        System.out.println(i);
        model.addAttribute("item", i);
        model.addAttribute("newItem", new Item());
        return "editItems";
    }

    @PostMapping(value = "/editItems")
    public String updateItems(@ModelAttribute Item item) {
        itemRepo.save(item);
        return "redirect:/viewItems?category=" + item.getCategory();
    }

    @GetMapping(value = "/viewItems", params = {"delete", "cat", "name"})
    public String deleteItems(@RequestParam("cat") String catName,
                              @RequestParam("name") String itemName) {
        itemRepo.delete(itemRepo.findByItemId(new ItemId(catName, itemName)));
        return "redirect:/viewItems?category=" + catName;
    }

}
