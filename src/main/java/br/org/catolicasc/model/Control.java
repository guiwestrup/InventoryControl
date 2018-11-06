package br.org.catolicasc.model;

import java.util.Date;

public class Control {

    private int id;
    private String description;
    private InvoiceEntries invoiceEntries;
    private Product product;
    private User user;
    private Type type;
    private int quatity;
    private float value;
    private InsertWithdraw insertwithdraw;
    //TODO definir qual date
    private Date date;
    private Status status;

    public Control() {
    }

    public Control(int id, String description, InvoiceEntries invoiceEntries, Product product, User user, Type type, int quatity, float value, InsertWithdraw insertwithdraw, Date date, Status status) {
        this.id = id;
        this.description = description;
        this.invoiceEntries = invoiceEntries;
        this.product = product;
        this.user = user;
        this.type = type;
        this.quatity = quatity;
        this.value = value;
        this.insertwithdraw = insertwithdraw;
        this.date = date;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public InvoiceEntries getInvoiceEntries() {
        return invoiceEntries;
    }

    public void setInvoiceEntries(InvoiceEntries invoiceEntries) {
        this.invoiceEntries = invoiceEntries;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public int getQuatity() {
        return quatity;
    }

    public void setQuatity(int quatity) {
        this.quatity = quatity;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public InsertWithdraw getInsertwithdraw() {
        return insertwithdraw;
    }

    public void setInsertwithdraw(InsertWithdraw insertwithdraw) {
        this.insertwithdraw = insertwithdraw;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
