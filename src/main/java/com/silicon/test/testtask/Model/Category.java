package com.silicon.test.testtask.Model;

import javax.persistence.*;

@Entity
@Table(name = "categories")
public class Category {

    @Id
    private String name;

    private String description;

    @Transient
    private int countOfItems;

    public Category() {
    }

    public Category(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public String getShortDescription() {
        if (description != null && description.length() > 150)
            return description.substring(0,150) + "...";
        return description;
    }
    public void setDescription(String description) {this.description = description;}

    @Override
    public String toString() {
        return "Category[" + name + ", " + description + "]";
    }

    public int getCountOfItems() {
        return countOfItems;
    }

    public void setCountOfItems(int countOfItems) {
        this.countOfItems = countOfItems;
    }
}
