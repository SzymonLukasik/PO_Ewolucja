package zad1.parametry.roba;

public class ParamRobaOstateczne {

    private int ileDajeJedzenie;
    private int kosztTury;
    private float prPowielania;
    private int limitPowielania;
    private float ułamekEnergiiRodzica;

    //Gettery

    public int ileDajeJedzenie() {
        return ileDajeJedzenie;
    }

    public int kosztTury() {
        return kosztTury;
    }

    public float prPowielania() {
        return prPowielania;
    }

    public int limitPowielania() {
        return limitPowielania;
    }

    public float ułamekEnergiiRodzica() {
        return ułamekEnergiiRodzica;
    }

    //Settery

    public void ustawIleDajeJedzenie(int ileDajeJedzenie) {
        this.ileDajeJedzenie = ileDajeJedzenie;
    }

    public void ustawKosztTury(int kosztTury) {
        this.kosztTury = kosztTury;
    }

    public void ustawPrPowielania(float prPowielania) {
        this.prPowielania = prPowielania;
    }

    public void ustawLimitPowielania(int limitPowielania) {
        this.limitPowielania = limitPowielania;
    }

    public void ustawUłamekEnergiiRodzica(float ułamekEnergiiRodzica) {
        this.ułamekEnergiiRodzica = ułamekEnergiiRodzica;
    }
}
