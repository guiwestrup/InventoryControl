package br.org.catolicasc.model;

public enum UnitType {
    AMPOLA("Ampola"),
    CX("CX"),
    EMBALAGEM("Embalagem"),
    FARDO("Fardo"),
    FRASCO("Frasco"),
    GALAO("Galão"),
    KG("KG"),
    LITRO("Litro"),
    M("Metro"),
    PACOTE("Pacote"),
    PC("Peça"),
    SACO("Saco"),
    UN("Unidade");

    private String unityType;

    private UnitType(String unityType){
        this.unityType = unityType;
    }

    private String getUnityType(){
        return this.unityType;
    }

    //Sobrescrevendo o toString
    @Override
    public String toString() {
        return unityType;
    }
}
