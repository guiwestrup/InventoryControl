package br.org.catolicasc.model;

import java.util.ArrayList;
import java.util.List;

public class InvoiceEntries implements BaseDaoClass{

    //TODO provável que esteja faltando campo
    private int id;
    private String numberInvoice;
    private float totalValue;

    private String description;
    private List<InvoiceProducts> listProducts;

    private Vendor vendor;
    private User user;

    public InvoiceEntries(){
        listProducts = new ArrayList<>();
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public String getNumberInvoice() {
        return numberInvoice;
    }

    public void setNumberInvoice(String numberInvoice) {
        this.numberInvoice = numberInvoice;
    }

    public float getTotalValue() { return totalValue; }

    public void setTotalValue(float totalValue) {
        this.totalValue = totalValue;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<InvoiceProducts> getListProducts() {
        return listProducts;
    }

    public void setListProducts(List<InvoiceProducts> listProducts) {
        this.listProducts = listProducts;
    }
}
