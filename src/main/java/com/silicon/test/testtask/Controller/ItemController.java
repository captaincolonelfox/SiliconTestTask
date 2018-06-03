package com.silicon.test.testtask.Controller;

import com.silicon.test.testtask.Model.Item;
import com.silicon.test.testtask.Model.ItemId;
import com.silicon.test.testtask.Model.User;
import com.silicon.test.testtask.Repo.IItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Controller
public class ItemController {

    @Autowired
    IItemsRepository itemRepo;

    @RequestMapping("/viewItems")
    public String showItems(@RequestParam(name="category", required = false, defaultValue = "") String catParameter,
                            @PageableDefault Pageable pageable,
                            Model model) {
        Page<Item> items = catParameter.equals("") ? itemRepo.findAll(pageable) : itemRepo.findByItemIdCategory(catParameter, pageable);
        model.addAttribute("items", items);
        model.addAttribute("catParameter", catParameter);
        if (((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getRole().equals("ROLE_USER"))
            model.addAttribute("regularUser", true);
        return "viewItems";
    }
    @Secured("ROLE_ADMIN")
    @GetMapping(value = "/editItems", params = {"edit"})
    public String editItems(@RequestParam(value = "cat", required = false, defaultValue = "") String catName,
                            @RequestParam(value = "name", required = false, defaultValue = "") String itemName,
                            Model model) {
        if (catName.equals("") && itemName.equals(""))
            model.addAttribute("item", new Item());
        else if (!catName.equals("")) {
            Item item = new Item();
            item.setCategory(catName);
            model.addAttribute("item", item);
        }
        else
            model.addAttribute("item", itemRepo.findByItemId(new ItemId(catName, itemName)));
        return "editItems";
    }
    @Secured("ROLE_ADMIN")
    @PostMapping(value = "/editItems")
    public String updateItems(@ModelAttribute Item item) throws UnsupportedEncodingException {
        itemRepo.save(item);
        return "redirect:/viewItems?category=" + URLEncoder.encode(item.getCategory(), "UTF-8");
    }
    @Secured("ROLE_ADMIN")
    @GetMapping(value = "/editItems", params = {"delete", "cat", "name"})
    public String deleteItems(@RequestParam("cat") String catName,
                              @RequestParam("name") String itemName) throws UnsupportedEncodingException {
        itemRepo.delete(itemRepo.findByItemId(new ItemId(catName, itemName)));
        return "redirect:/viewItems?category=" + URLEncoder.encode(catName, "UTF-8");
    }
}
