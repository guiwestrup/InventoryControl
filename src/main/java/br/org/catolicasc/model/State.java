package br.org.catolicasc.model;

public enum State{
    AC(8), AL(20), AM(9), AP(12), BA(22), CE(16), DF(27), ES(23), GO(26), MA(14), MG(6), MS(24), MT(25), PA(11), PB(18), PE(19), PI(15), PR(2), RJ(5), RN(17), RO(7), RR(10), RS(3), SC(1), SE(21), SP(4), TO(13);

    private final int value;

    State(int value) {
        this.value = value;
    }

    public int getValue(){
        return value;
    }
}
