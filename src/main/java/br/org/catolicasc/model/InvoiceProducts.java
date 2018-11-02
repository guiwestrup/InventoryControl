package br.org.catolicasc.model;

public class InvoiceProducts {

    //TODO revisar se falta campo
    private int id;
    private InvoiceEntries invoiceEntries;
    private Product product;
    private int quantity;
    private float costValue;
    private float total;
    private Status status;

    public InvoiceProducts() {
    }

    public InvoiceProducts(int id, InvoiceEntries invoiceEntries, Product product, int quantity, float costValue, float total, Status status) {
        this.id = id;
        this.invoiceEntries = invoiceEntries;
        this.product = product;
        this.quantity = quantity;
        this.costValue = costValue;
        this.total = total;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
