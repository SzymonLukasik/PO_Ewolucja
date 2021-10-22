package zad1.program;

import zad1.parametry.ParamProgramu;

import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Collector;

public class Program {

    private ArrayList<Instrukcja> kod;
    private ParamProgramu parametry;

    public Program() {
        this.kod = null;
        this.parametry = new ParamProgramu();
    }

    public Program(ParamProgramu parametry, ArrayList<Instrukcja> kod) {
        this.parametry = parametry;
        this.kod = kod;
    }

    public Program zmutuj() {
        ArrayList<Instrukcja> kod = new ArrayList<>(this.kod);
        Random random = new Random();
        float pr = random.nextFloat();

        if(!kod.isEmpty() && pr <= parametry.prUsunięciaInstr())
            kod.remove(kod.size() - 1);
        if(pr <= parametry.prDodaniaInstr())
            kod.add(parametry.spisInstrukcji().dajLosową());
        if(!kod.isEmpty() && pr <= parametry.prZmianyInstr())
            kod.set(random.nextInt(kod.size()), parametry.spisInstrukcji().dajLosową());

        return new Program(this.parametry, kod);
    }

    public ArrayList<Instrukcja> kod() {
        return kod;
    }

    public ParamProgramu parametry() {
        return parametry;
    }

    public void ustawKod(ArrayList<Instrukcja> kod) {
        this.kod = kod;
    }

    public void ustawParametry(ParamProgramu parametry) {
        this.parametry = parametry;
    }

    @Override
    public String toString() {
        return kod.stream().map(i -> new StringBuilder(i.toString()))
                .collect(Collector.of(StringBuilder::new,
                        StringBuilder::append,
                        StringBuilder::append)).toString();
    }
}
