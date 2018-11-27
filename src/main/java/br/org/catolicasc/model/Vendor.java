package br.org.catolicasc.model;

//fornecedor
public class Vendor implements BaseDaoClass{

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

    public Vendor(){

    }

    public Vendor(String company, String trade, String CNPJ, String address, String phone, String email, String zipcode, String city, State state) {
        this.company = company;
        this.trade = trade;
        this.CNPJ = CNPJ;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.zipcode = zipcode;
        this.city = city;
        this.state = state;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
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
}

