package com.silicon.test.testtask.Model;

import java.io.Serializable;

public class ItemId implements Serializable {
    private String category;
    private String name;

    public ItemId() {
    }

    public ItemId(String category, String name) {
        this.category = category;
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
