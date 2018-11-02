package br.org.catolicasc.model;

public class Company {
    //TODO esse id o ideal seria nós controlarmos, mas esquece
    private int id;
    private String name;
    private String CNPJ;
    private String address;
    private String neighborhood;
    private String city;
    private State state;
    private String email;

    public Company() {
    }

    public Company(int id, String name, String CNPJ, String address, String neighborhood, String city, State state, String email) {
        this.id = id;
        this.name = name;
        this.CNPJ = CNPJ;
        this.address = address;
        this.neighborhood = neighborhood;
        this.city = city;
        this.state = state;
        this.email = email;
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

    public String getCNPJ() {
        return CNPJ;
    }

    public void setCNPJ(String CNPJ) {
        this.CNPJ = CNPJ;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
