package br.org.catolicasc.model;

import sun.security.util.Password;

public class User implements BaseDaoClass{

    private int id;
    private String name;
    private String password;
    private String address;
    private String city;
    private State state;
    private Role role;

    public User(){

    }

    public User(String name, String address, String city, String password, State state, Role role)
    {
        this.name = name;
        this.address = address;
        this.city = city;
        this.password = password;
        this.state = state;
        this.role = role;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
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

    @Override
    public String toString() {
        return "UserUi{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", state=" + state +
                ", role=" + role +
                '}';
    }
}
