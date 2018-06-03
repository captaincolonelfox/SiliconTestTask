package com.silicon.test.testtask.Repo;

import com.silicon.test.testtask.Model.Item;
import com.silicon.test.testtask.Model.ItemId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IItemsRepository extends JpaRepository<Item, ItemId> {
    List<Item> findByItemIdCategory(String category);
    List<Item> findByItemIdName(String name);
    Item findByItemId(ItemId id);
}
