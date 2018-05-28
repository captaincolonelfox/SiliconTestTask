package com.silicon.test.testtask.Model;

import javax.persistence.*;

@Entity
@Table(name = "items")
public class Item {
    @EmbeddedId
    private ItemId itemId;
    private String img;
    private String description;
    private float cost;
    private int amount;

    protected Item() {

    }

    public Item(ItemId itemid, String img, String description, float cost, int amount) {
        this.itemId = itemid;
        this.img = img;
        this.description = description;
        this.cost = cost;
        this.amount = amount;
    }


    public ItemId getId() {
        return itemId;
    }

    public void setId(ItemId itemid) {
        this.itemId = itemid;
    }

    public String getCategory() {
        return itemId.getCategory();
    }

    public void setCategory(String category) {
        this.itemId.setCategory(category);
    }

    public String getName() {
        return itemId.getName();
    }

    public void setName(String name) {
        this.itemId.setName(name);
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (description == null) description = "";
        this.description = description;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Item[name:" + itemId.getName() + ";img:" + img + ";]";
    }
}