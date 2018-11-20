package br.org.catolicasc.model;

public class Inventory implements BaseDaoClass {
    private int id;
    private Product product;
    private int quantity;
    private InsertWithdraw insertWithdraw;

    public Inventory(){

    }

    public Inventory(int id, Product product, int quantity, InsertWithdraw insertWithdraw) {
        this.id = id;
        this.product = product;
        this.quantity = quantity;
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
}
