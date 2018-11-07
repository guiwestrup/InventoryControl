package br.org.catolicasc.model;

public class Product implements BaseDaoClass{

    private int id;
    private String name;
    private String cean;
    private String ncmcode;
    private String aliquota;
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
        return ncmcode;
    }

    public void setNcmcode(String ncmcode) {
        this.ncmcode = ncmcode;
    }

    public String getAliquota() {
        return aliquota;
    }

    public void setAliquota(String aliquota) {
        this.aliquota = aliquota;
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
