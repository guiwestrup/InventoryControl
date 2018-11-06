package br.org.catolicasc.model;

import sun.security.util.Password;

public class User {

    private int id;
    private String name;
    //TODO revisar esse Password
    private Password password;
    //TODO teste
    private String address;
    private String city;
    private State state;
    private Role role;
    private Status status;

    public User(){}

    public User(String name, Password password, String address, String city, State state, Role role, Status status) {
        this.name = name;
        this.password = password;
        this.address = address;
        this.city = city;
        this.state = state;
        this.role = role;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Password getPassword() {
        return password;
    }

    public void setPassword(Password password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
