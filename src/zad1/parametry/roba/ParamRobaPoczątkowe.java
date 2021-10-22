package zad1.parametry.roba;

import zad1.program.Program;

public class ParamRobaPoczątkowe {

    private final Program poczProgram;
    private int poczEnergia;

    //Gettery

    public ParamRobaPoczątkowe() {
        this.poczProgram = new Program();
    }

    public Program poczProgram() {
        return poczProgram;
    }

    public int poczEnergia() {
        return poczEnergia;
    }

    //Settery

    public void ustawPoczEnergia(int poczEnergia) {
        this.poczEnergia = poczEnergia;
    }
}
