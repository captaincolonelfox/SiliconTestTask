package com.silicon.test.testtask.Repo;

import com.silicon.test.testtask.Model.Item;
import com.silicon.test.testtask.Model.ItemId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IItemsRepository extends JpaRepository<Item, ItemId> {
    List<Item> findByItemIdCategory(String category);
    List<Item> findByItemIdName(String name);
}
