package br.org.catolicasc.model;

public class Product implements BaseDaoClass{

    private int id;
    private String name;
    private String cean;
    private String marca;
    private Category categoria;
    private Float costValue;
    private UnitType unit;

    public Product() {
    }

    public Product(String name, String cean, String marca, Category categoria,Float costValue,UnitType unit) {
        this.name = name;
        this.cean = cean;
        this.marca = marca;
        this.categoria = categoria;
        this.costValue = costValue;
        this.unit = unit;
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

    public String getCean() {
        return cean;
    }

    public void setCean(String cean) {
        this.cean = cean;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Category getCategoria() {
        return categoria;
    }

    public void setCategoria(Category categoria) {
        this.categoria = categoria;
    }

    public Float getCostValue() {
        return costValue;
    }

    public void setCostValue(Float costValue) {
        this.costValue = costValue;
    }

    public UnitType getUnit() {
        return unit;
    }

    public void setUnit(UnitType unit) {
        this.unit = unit;
    }
}
