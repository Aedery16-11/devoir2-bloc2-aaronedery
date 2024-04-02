package sio.devoir2sio1b.Model;

public class Trader
{
    private int idTrader;
    private String nomTrader;

    public Trader(int idTrader, String nomTrader) {
        this.idTrader = idTrader;
        this.nomTrader = nomTrader;
    }

    public int getIdTrader() {
        return idTrader;
    }

    public String getNomTrader() {
        return nomTrader;
    }
}
