package zad1.parametry;

import zad1.program.SpisInstrukcji;

public class ParamProgramu {

    private float prUsunięciaInstr;
    private float prDodaniaInstr;
    private float prZmianyInstr;
    private SpisInstrukcji spisInstrukcji;

    //Gettery

    public float prUsunięciaInstr() {
        return prUsunięciaInstr;
    }

    public float prDodaniaInstr() {
        return prDodaniaInstr;
    }

    public float prZmianyInstr() {
        return prZmianyInstr;
    }

    public SpisInstrukcji spisInstrukcji() {
        return spisInstrukcji;
    }

    //Settery

    public void ustawPrUsunięciaInstr(float prUsunięciaInstr) {
        this.prUsunięciaInstr = prUsunięciaInstr;
    }

    public void ustawPrDodaniaInstr(float prDodaniaInstr) {
        this.prDodaniaInstr = prDodaniaInstr;
    }

    public void ustawPrZmianyInstr(float prZmianyInstr) {
        this.prZmianyInstr = prZmianyInstr;
    }

    public void ustawSpisInstrukcji(SpisInstrukcji spisInstrukcji) {
        this.spisInstrukcji = spisInstrukcji;
    }
}
