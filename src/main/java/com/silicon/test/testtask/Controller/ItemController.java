package com.silicon.test.testtask.Controller;

import com.silicon.test.testtask.Model.Item;
import com.silicon.test.testtask.Model.ItemId;
import com.silicon.test.testtask.Model.User;
import com.silicon.test.testtask.Repo.IItemsRepository;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Controller
@PropertySource("classpath:application.properties")
public class ItemController {

    @Autowired
    IItemsRepository itemRepo;

    @Value("${image.pathTo}")
    private String webRootPath;

    @RequestMapping("/viewItems")
    public String showItems(@RequestParam(name="category", required = false, defaultValue = "") String catParameter,
                            @RequestParam(name="sort", required = false) String currentSort,
                            @PageableDefault Pageable pageable,
                            Model model){
        Page<Item> items = catParameter.equals("") ? itemRepo.findAll(pageable) : itemRepo.findByItemIdCategory(catParameter, pageable);
        model.addAttribute("items", items);
        model.addAttribute("imagePrefix", webRootPath);
        model.addAttribute("catParameter", catParameter);
        model.addAttribute("currentPage", pageable.getPageNumber());
        model.addAttribute("pageSize", items.getSize());
        model.addAttribute("currentSort", currentSort);
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
        else if (!catName.equals("") && itemName.equals("")) {
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
    public String updateItems(@ModelAttribute Item item,
                              BindingResult bindingResult,
                              @RequestParam(value="image", required=false) MultipartFile image) throws UnsupportedEncodingException {
        if(bindingResult.hasErrors()) {
            return "/editItems";
        }

        try {
            if(!image.isEmpty()) {
                validateImage(image);
                saveImage(item.getCategory() + item.getName() + ".jpg", image);
                item.setImg(item.getCategory() + item.getName());
            }
        } catch (RuntimeException e) {
            bindingResult.reject(e.getMessage());
            return "/editItems";
        }

        itemRepo.save(item);

        return "redirect:/viewItems?category=" + URLEncoder.encode(item.getCategory(), "UTF-8");
    }

    private void validateImage(MultipartFile image) {
        if(!image.getContentType().equals("image/jpeg")) {
            throw new RuntimeException("Only JPG images accepted");
        }
    }

    private void saveImage(String filename, MultipartFile image) throws RuntimeException {
        try {
            File file = new File(webRootPath + filename);
            FileUtils.writeByteArrayToFile(file, image.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Unable to save image", e);
        }
    }

    @Secured("ROLE_ADMIN")
    @GetMapping(value = "/editItems", params = {"delete", "cat", "name"})
    public String deleteItems(@RequestParam("cat") String catName,
                              @RequestParam("name") String itemName) throws UnsupportedEncodingException {
        itemRepo.delete(itemRepo.findByItemId(new ItemId(catName, itemName)));
        return "redirect:/viewItems?category=" + URLEncoder.encode(catName, "UTF-8");
    }
}
