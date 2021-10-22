package zad1.czytnik;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public abstract class Czytnik {

    protected Scanner skaner;

    protected void inicjujSkaner(File plik) {
        try {
            skaner = new Scanner(plik);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }

        skaner.useDelimiter("\n");
    }

    protected abstract void inicjujPolaPomocnicze();

    protected Czytnik(File plik) {
        inicjujSkaner(plik);
        inicjujPolaPomocnicze();
    }

    protected abstract void wczytajWiersz() throws  IOException;

    protected void wczytajWiersze() throws IOException {

        if(!skaner.hasNextLine()) {
            throw new IOException("Błędny format wejścia. Pusty plik");
        }

        while (skaner.hasNextLine()) {
            try {
                wczytajWiersz();
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(1);
            }
        }

        skaner.close();
    }
}
