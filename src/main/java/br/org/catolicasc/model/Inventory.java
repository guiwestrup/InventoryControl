package br.org.catolicasc.model;

public class Inventory implements BaseDaoClass {
    private int id;
    private Product product;
    private int quantity;
    private InsertWithdraw insertWithdraw;
    private Status status;

    public Inventory(){
        status = Status.ACTIVE;
    }

    public Inventory(int id, Product product, int quantity, InsertWithdraw insertWithdraw, Status status) {
        this.id = id;
        this.product = product;
        this.quantity = quantity;
        this.insertWithdraw = insertWithdraw;
        this.status = status;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public InsertWithdraw getInsertWithdraw() {
        return insertWithdraw;
    }

    public void setInsertWithdraw(InsertWithdraw insertWithdraw) {
        this.insertWithdraw = insertWithdraw;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
