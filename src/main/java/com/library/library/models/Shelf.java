package com.library.library.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Shelf {
    @Id
    @GeneratedValue
    private Long id;

    public Shelf() {

    }
    public Shelf(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;
    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }


}
