package zad1.parametry;

import zad1.parametry.pól.ParamPól;
import zad1.parametry.roba.ParamRoba;

public class ParamSymulacji {

    private int ileTur;
    private int poczIleRobów;
    private int coIleWypisz;
    private final ParamPól paramPola;
    private final ParamRoba paramRoba;
    private final ParamProgramu paramProgramu;


    public ParamSymulacji() {
        this.ileTur = 0;
        this.poczIleRobów = 0;
        this.coIleWypisz = 0;
        this.paramPola = new ParamPól();
        this.paramRoba = new ParamRoba();
        this.paramProgramu = new ParamProgramu();
    }

    //Gettery

    public int ile_tur() {
        return ileTur;
    }

    public int pocz_ile_robów() {
        return poczIleRobów;
    }

    public int co_ile_wypisz() {
        return coIleWypisz;
    }

    public ParamPól paramPola() {
        return paramPola;
    }

    public ParamProgramu paramProgramu() {
        return paramProgramu;
    }

    public ParamRoba paramRoba() {
        return paramRoba;
    }

    //Settery

    public void ustawIle_tur(int ile_tur) {
        this.ileTur = ile_tur;
    }

    public void ustawPocz_ile_robów(int pocz_ile_robów) {
        this.poczIleRobów = pocz_ile_robów;
    }

    public void ustawCo_ile_wypisz(int co_ile_wypisz) {
        this.coIleWypisz = co_ile_wypisz;
    }

}

