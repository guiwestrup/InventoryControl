package br.org.catolicasc.model;

import java.security.Signature;

public class Category implements BaseDaoClass {

    private int id;
    private String name;

    public Category(String name) {
        this.name = name;
    }

    public Category() {

    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
