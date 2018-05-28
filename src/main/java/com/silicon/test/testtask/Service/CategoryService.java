package com.silicon.test.testtask.Service;

import com.silicon.test.testtask.Model.Category;
import com.silicon.test.testtask.Repo.ICategoryRepository;
import com.silicon.test.testtask.Repo.IItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService implements ICategoryService {

    @Autowired
    private ICategoryRepository catRepo;
    @Autowired
    private IItemsRepository itemsRepo;

    @Override
    public void addCategory(Category c) {
        this.catRepo.save(c);
    }

    @Override
    public void updateCategory(Category c) {
        this.catRepo.save(c);
    }

    @Override
    public List<Category> getAllCategories() {
        List<Category> categories = catRepo.findAll();
        for (Category cat : categories) {
            int count = itemsRepo.findByItemIdCategory(cat.getName()).size();
            cat.setCountOfItems(count);
        }
        return categories;
    }

    @Override
    public Category getCategoryByName(String name) {
        Category cat = catRepo.findByName(name);
        int count = itemsRepo.findByItemIdCategory(cat.getName()).size();
        cat.setCountOfItems(count);
        return cat;
    }

    @Override
    public void removeCategory(Category category) {
            this.catRepo.delete(category);
    }
}
