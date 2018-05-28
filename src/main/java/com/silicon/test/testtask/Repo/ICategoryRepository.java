package com.silicon.test.testtask.Repo;

import com.silicon.test.testtask.Model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICategoryRepository extends JpaRepository<Category, String> {
    Category findByName(String name);
    void delete(Category category);
}
