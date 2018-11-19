package br.org.catolicasc.model;

import java.security.Signature;

public class Category implements BaseDaoClass {

    private int id;
    private String name;
    private Status status;

    public Category(String name) {
        this.name = name;
        this.status = Status.ACTIVE;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", status=" + status +
                '}';
    }
}
