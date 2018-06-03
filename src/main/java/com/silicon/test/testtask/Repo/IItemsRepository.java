package com.silicon.test.testtask.Repo;

import com.silicon.test.testtask.Model.Item;
import com.silicon.test.testtask.Model.ItemId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IItemsRepository extends JpaRepository<Item, ItemId> {
    Page<Item> findByItemIdCategory(String category, Pageable pageable);
    List<Item> findByItemIdCategory(String categoryOnly);
    Item findByItemId(ItemId id);
}
