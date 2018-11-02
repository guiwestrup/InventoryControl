package br.org.catolicasc.model;

public class Vendor {

    private int id;
    //company = razão social
    private String company;
    //trade = nome fantasia
    private String trade;
    private String CNPJ;
    private String address;
    private String phone;
    private String email;
    private String zipcode;
    private String city;
    private State state;
    private Category category;
    private Status status;

    public Vendor(){

    }

    public Vendor(String company, String trade, String CNPJ, String address, String phone, String email, String zipcode, String city, State state, Category category, Status status){
        this.company = company;
        this.trade = trade;
        this.CNPJ = CNPJ;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.zipcode = zipcode;
        this.city = city;
        this.state = state;
        this.category = category;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getTrade() {
        return trade;
    }

    public void setTrade(String trade) {
        this.trade = trade;
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


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}

