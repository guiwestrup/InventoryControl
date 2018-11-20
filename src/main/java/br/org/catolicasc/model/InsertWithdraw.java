package br.org.catolicasc.model;

public enum InsertWithdraw {
    INSERT(1),WITHDRAW(2);

    private final int value;

    InsertWithdraw(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}