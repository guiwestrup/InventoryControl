package br.org.catolicasc.model;

public class Product implements BaseDaoClass{

    private int id;
    private String name;
    private String cean;
    private String marca;
    private String categoria;
    private int CFOP;
    private int sittributaria;
    private int codcest;
    private UnitType unit;

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

    public String getNcmcode() {
        return marca;
    }

    public void setNcmcode(String marca) {
        this.marca = marca;
    }

    public String getAliquota() {
        return categoria;
    }

    public void setAliquota(String categoria) {
        this.categoria = categoria;
    }

    public int getCFOP() {
        return CFOP;
    }

    public void setCFOP(int CFOP) {
        this.CFOP = CFOP;
    }

    public int getSittributaria() {
        return sittributaria;
    }

    public void setSittributaria(int sittributaria) {
        this.sittributaria = sittributaria;
    }

    public int getCodcest() {
        return codcest;
    }

    public void setCodcest(int codcest) {
        this.codcest = codcest;
    }

    public UnitType getUnit() {
        return unit;
    }

    public void setUnit(UnitType unit) {
        this.unit = unit;
    }
}
