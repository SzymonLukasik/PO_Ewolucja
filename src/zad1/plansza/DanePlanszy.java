package zad1.plansza;

import zad1.plansza.statystyka.DłProgramuRobów;
import zad1.plansza.statystyka.EnergiaRobów;
import zad1.plansza.statystyka.WiekRobów;

import zad1.roby.Rob;

public class DanePlanszy {
    private int liczbaRobów;
    private int liczbaPólZŻywnością;
    private final WiekRobów wiekRobów;
    private final DłProgramuRobów dłProgramuRobów;
    private final EnergiaRobów energiaRobów;

    private int liczbaPowstałychRobów;
    private int nrTury;

    public DanePlanszy(Plansza plansza) {
        liczbaRobów = 0;
        liczbaPólZŻywnością = 0;
        liczbaPowstałychRobów = 0;
        nrTury = 0;
        wiekRobów = new WiekRobów(plansza);
        dłProgramuRobów = new DłProgramuRobów(plansza);
        energiaRobów = new EnergiaRobów(plansza);
    }

    //Settery

    public void nowaTura() {
        this.nrTury++;
    }

    public void rejestrujNarodziny(Rob rob) {
        liczbaRobów++;
        liczbaPowstałychRobów++;
        wiekRobów.dodajWartość(rob);
        dłProgramuRobów.dodajWartość(rob);
        energiaRobów.dodajWartość(rob);
    }

    public void rejestrujŚmierć(Rob rob) {
        liczbaRobów--;
        wiekRobów.usuńWartość(rob);
        dłProgramuRobów.usuńWartość(rob);
        energiaRobów.usuńWartość(rob);
    }

    public void dodajPoleZŻywnością(){
        liczbaPólZŻywnością++;
    }

    public void usuńPoleZŻywnością() {
        liczbaPólZŻywnością--;
    }


    //Gettery

    public int liczbaRobów() {
        return liczbaRobów;
    }

    public int nrTury() {
        return  nrTury;
    }

    public EnergiaRobów energiaRobów() {
        return energiaRobów;
    }

    public int LiczbaPowstałychRobów() {
        return liczbaPowstałychRobów;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();

        s.append("Numer tury: ").append(nrTury).append("\n")
         .append("Liczba robów: ").append(liczbaRobów).append("\n")
         .append("Liczba pól z żywnością: ").append(liczbaPólZŻywnością).append("\n");
        if(liczbaRobów > 0)
            s.append(dłProgramuRobów).append(energiaRobów).append(wiekRobów);
        return s.toString();
    }
}
