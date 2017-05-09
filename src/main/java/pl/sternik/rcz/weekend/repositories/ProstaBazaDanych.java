package pl.sternik.rcz.weekend.repositories;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import pl.sternik.rcz.weekend.entities.Gra;
import pl.sternik.rcz.weekend.entities.Status;


@Repository
@Qualifier("tablica")
public class ProstaBazaDanych implements GryRepository {

    private Gra[] baza;

    public ProstaBazaDanych() {
        baza = new Gra[15];
      

    }

    public ProstaBazaDanych(int rozmiarBazy) {
        baza = new Gra[rozmiarBazy];
    }

    @Override
    public Gra create(Gra gra) throws GraAlreadyExistsException {
        if (gra.getNumerKatalogowy() != null && baza[gra.getNumerKatalogowy().intValue()] != null) {
            if (gra.getNumerKatalogowy().equals(baza[gra.getNumerKatalogowy().intValue()].getNumerKatalogowy())) {
                throw new GraAlreadyExistsException("Już jest gra o takim numerze.");
            }
        }
        for (int i = 0; i < baza.length; i++) {
            if (baza[i] == null) {
                baza[i] = gra;
                gra.setNumerKatalogowy((long) i);
                return gra;
            }
        }
        throw new RuntimeException("Brak miejsca w tablicy");
    }

    @Override
    public void deleteById(Long id) throws NoSuchGraException {
        int numerKatalogowy = id.intValue();
        if (!sprawdzPoprawnoscNumeruKatalogowego(numerKatalogowy)) {
            throw new NoSuchGraException("Niepoprawny numer katologowy");
        }
        baza[numerKatalogowy] = null;
    }

    @Override
    public Gra update(Gra gra) throws NoSuchGraException {
        int numerKatalogowy = gra.getNumerKatalogowy().intValue();
        if (!sprawdzPoprawnoscNumeruKatalogowego(numerKatalogowy)) {
            throw new NoSuchGraException("Niepoprawny numer katologowy");
        }

        Gra m = baza[gra.getNumerKatalogowy().intValue()];
        if (m == null) {
            throw new NoSuchGraException("Brak takiej gry.");
        } else {
            baza[gra.getNumerKatalogowy().intValue()] = gra;
        }
        return gra;
    }

    @Override
    public Gra readById(Long numerKatalogowy) throws NoSuchGraException {
        int id = numerKatalogowy.intValue();
        if (!sprawdzPoprawnoscNumeruKatalogowego(id) || czyWolne(id)) {
            throw new NoSuchGraException();
        }
        return baza[id];
    }

    private boolean czyWolne(int id) {
        if(baza[id]!= null)
            return false;
        return true;
    }

    @Override
    public List<Gra> findAll() {
        List<Gra> tmp = new ArrayList<>();
        for (int i = 0; i < baza.length; i++) {
            if (baza[i] != null)
                tmp.add(baza[i]);
        }
        return tmp;
    }

    public void wyswietlBaze() {
        for (int i = 0; i < baza.length; i++) {
            System.out.println("" + i + ":" + baza[i]);
        }
    }

    private boolean sprawdzPoprawnoscNumeruKatalogowego(int numerKatalogowy) {
        if (numerKatalogowy < 0 || numerKatalogowy >= baza.length) {
            System.out.println("Zły numer katalogowy");
            return false;
        }
        return true;
    }

}
