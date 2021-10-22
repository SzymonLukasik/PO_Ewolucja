package zad1.czytnik;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class CzytnikPlanszy extends Czytnik {

    private ArrayList<char[]> listaWierszy;
    private int szerokość;

    @Override
    protected void inicjujPolaPomocnicze() {
        this.listaWierszy = new ArrayList<>();
        this.szerokość = -1;
    }

    public CzytnikPlanszy(File plik) {
        super(plik);
    }

    public char[][] wczytajPlanszę() {
        try {
            wczytajWiersze();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        return listaWierszy.toArray(new char[0][]);
    }

    private static void sprawdźFormatWiersza(String wiersz) {
        if(!Pattern.matches("[Ox ]+", wiersz))
            try {
                throw new IOException("Błędny format wejścia. " +
                        "Wiersz planszy może zawierać tylko znaki ' ', 'O' oraz 'x'. ");
            } catch(IOException e) {
                e.printStackTrace();
                System.exit(1);
            }
    }

    @Override
    protected void wczytajWiersz() throws IOException {

        String wiersz = skaner.nextLine();

        sprawdźFormatWiersza(wiersz);

        if(szerokość == -1) {
            szerokość = wiersz.length();
            listaWierszy.add(wiersz.toCharArray());
        }
        else if(szerokość == wiersz.length()) {
            listaWierszy.add(wiersz.toCharArray());
        }
        else {
            throw new IOException("Błędny format wejścia. " +
                    "Wiersze planszy powinny mieć jednakową długość.");
        }
    }
}
