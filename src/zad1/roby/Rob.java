package zad1.roby;

import zad1.parametry.roba.ParamRobaOstateczne;
import zad1.plansza.Plansza;
import zad1.kierunek.KierunekProsty;
import zad1.pola.Pole;
import zad1.program.Instrukcja;
import zad1.program.Program;

import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Stream;

public class Rob implements Comparable<Rob> {

    private final Plansza plansza;
    private final int identyfikator;
    private final Program program;
    private final ParamRobaOstateczne parametry;
    private final int turaUrodzin;
    private Pole pole;
    private KierunekProsty zwrot;
    private int energia;
    private int staraEnergia;

    public Rob(Plansza plansza, int identyfikator,
               Program program, ParamRobaOstateczne parametry,
               Pole pole, KierunekProsty zwrot, int pocz_energia) {

        this.plansza = plansza;
        this.identyfikator = identyfikator;
        this.program = program;
        this.parametry = parametry;
        this.turaUrodzin = plansza.dane().nrTury();
        this.pole = pole;
        this.zwrot = zwrot;
        this.energia = pocz_energia;
        this.staraEnergia = energia;

        pole.przydzielRoba(this);
        plansza.dane().rejestrujNarodziny(this);
    }

    @Override
    public int compareTo(Rob rob) {
        return Integer.compare(this.identyfikator, rob.identyfikator);
    }

    @Override
    public String toString() {
        return "Stan roba " + identyfikator +
                ": " + "pole - " + pole +
                ", zwrot - " + zwrot +
                ", program - " + program +
                ", energia - " + energia +
                ", wiek - " + (plansza.dane().nrTury() - turaUrodzin) +
                " tury" + "\n";
    }

    public void zjedz() {
        this.energia += parametry.ileDajeJedzenie();
    }

    private void wejdźNa(Pole pole) {
        this.pole.usuńRoba(this);
        this.pole = pole;
        pole.wpuśćRoba(this);
    }

    private void wykonajInstrukcję(Instrukcja instrukcja) {
        switch (instrukcja) {
            case PRAWO: {
                obróćPrawo();
                break;
            }
            case LEWO: {
                obróćLewo();
                break;
            }
            case IDŹ: {
                idź();
                break;
            }
            case WĄCHAJ: {
                wąchaj();
                break;
            }
            case JEDZ: {
                jedz();
                break;
            }
        }
        this.energia--;
    }

    private void jedz() {
        Optional<Pole> opt = Stream.concat(
                pole.dajSąsiadówPr().values().stream(),
                pole.dajSąsiadówSk().values().stream())
                .filter(Pole::czyMaPożywienie).findFirst();

        opt.ifPresent(this::wejdźNa);
    }

    private void wąchaj() {
        Optional<Map.Entry<KierunekProsty, Pole>> opt = pole
                .dajSąsiadówPr().entrySet().stream()
                .filter(p -> p.getValue().czyMaPożywienie())
                .findFirst();

        opt.ifPresent(kierunek -> this.zwrot = kierunek.getKey());
    }

    private void idź() {
        wejdźNa(pole.dajSąsiada(zwrot));
    }

    private void obróćLewo() {
        this.zwrot = this.zwrot.obróćLewo() ;
    }

    private void obróćPrawo() {
        this.zwrot = this.zwrot.obróćPrawo();
    }

    private void powiel() {
        Rob powielony = new Rob(plansza,
                                plansza.dane().LiczbaPowstałychRobów() + 1,
                                program.zmutuj(), parametry,
                                pole, zwrot.przeciwny(),
                                (int) (parametry.ułamekEnergiiRodzica() * energia));

        this.energia -= powielony.energia;
        plansza.dodajDoPowielonych(powielony);
    }

    private boolean jużMartwy() {
        if(energia < 0) {
            umrzyj();
            return true;
        }
        return false;
    }

    private void umrzyj() {
        pole.usuńRoba(this);
        plansza.usuńRoba(this);
        plansza.dane().rejestrujŚmierć(this);
    }

    // Klasa EnergiaRobów korzysta z pola staraEnergia w
    // w celu zbierania informacji o energii robów.
    private void aktualizujEnergię() {
        plansza.dane().energiaRobów().usuńWartość(this);
        staraEnergia = energia;
        plansza.dane().energiaRobów().dodajWartość(this);
    }

    public void przyjmijObrażenia(int obrażenia) {
        staraEnergia = this.energia;

        this.energia -= obrażenia;
        if(jużMartwy())
            return;

        aktualizujEnergię();
    }

    public void wykonajTurę() {
        staraEnergia = this.energia;

        this.energia -= parametry.kosztTury();
        if(jużMartwy())
            return;

        for(Instrukcja instr : program.kod()) {
            wykonajInstrukcję(instr);
            if(jużMartwy())
                return;
        }

        if(energia >= parametry.limitPowielania()) {
            Random random = new Random();
            if (random.nextFloat() <= parametry.prPowielania()) {
                powiel();
                if(jużMartwy())
                    return;
            }
        }

        aktualizujEnergię();
    }

    //Gettery

    public Program program() {
        return program;
    }

    public int turaUrodzin() {
        return turaUrodzin;
    }

    public int staraEnergia() {
        return staraEnergia;
    }
}
