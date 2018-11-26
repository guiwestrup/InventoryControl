package br.org.catolicasc.model;

public class InvoiceProducts implements BaseDaoClass{

    //TODO revisar se falta campo
    private int id;
    private Product product;
    private int quantity;
    private float costValue;
    private float total;
    private int idInvoiceEntrie;

    public InvoiceProducts() {

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

    public float getCostValue() {
        return costValue;
    }

    public void setCostValue(float costValue) {
        this.costValue = costValue;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public int getIdInvoiceEntrie() {
        return idInvoiceEntrie;
    }

    public void setIdInvoiceEntrie(int idInvoiceEntrie) {
        this.idInvoiceEntrie = idInvoiceEntrie;
    }
}
