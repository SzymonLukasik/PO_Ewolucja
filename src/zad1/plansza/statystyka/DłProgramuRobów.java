package zad1.plansza.statystyka;

import zad1.plansza.Plansza;
import zad1.roby.Rob;

public class DłProgramuRobów extends Statystyka {

    public DłProgramuRobów(Plansza plansza) {
        super(plansza);
    }

    @Override
    protected int dajWartość(Object rob) {
        return ((Rob) rob).program().kod().size();
    }

    @Override
    public String toString() {
        return  "Dł programu robów: " + super.toString();
    }
}
