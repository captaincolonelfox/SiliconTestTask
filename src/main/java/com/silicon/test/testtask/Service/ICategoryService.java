package com.silicon.test.testtask.Service;

import com.silicon.test.testtask.Model.Category;

import java.util.List;

public interface ICategoryService {

    public void addCategory(Category c);
    public void updateCategory(Category c);
    public List<Category> getAllCategories();
    public Category getCategoryByName(String name);
    public void removeCategory(Category c);
}
