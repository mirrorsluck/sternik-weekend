package pl.sternik.rcz.weekend.repositories;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import pl.sternik.rcz.weekend.entities.gra;
import pl.sternik.rcz.weekend.entities.Status;


@Repository
@Qualifier("tablica")
public class ProstaBazaDanych implements gryRepository {

    private gra[] baza;

    public ProstaBazaDanych() {
        baza = new gra[15];
      

    }

    public ProstaBazaDanych(int rozmiarBazy) {
        baza = new gra[rozmiarBazy];
    }

    @Override
    public gra create(gra gra) throws graAlreadyExistsException {
        if (gra.getNumerKatalogowy() != null && baza[gra.getNumerKatalogowy().intValue()] != null) {
            if (gra.getNumerKatalogowy().equals(baza[gra.getNumerKatalogowy().intValue()].getNumerKatalogowy())) {
                throw new graAlreadyExistsException("Już jest gra o takim numerze.");
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
    public void deleteById(Long id) throws NoSuchgraException {
        int numerKatalogowy = id.intValue();
        if (!sprawdzPoprawnoscNumeruKatalogowego(numerKatalogowy)) {
            throw new NoSuchgraException("Niepoprawny numer katologowy");
        }
        baza[numerKatalogowy] = null;
    }

    @Override
    public gra update(gra gra) throws NoSuchgraException {
        int numerKatalogowy = gra.getNumerKatalogowy().intValue();
        if (!sprawdzPoprawnoscNumeruKatalogowego(numerKatalogowy)) {
            throw new NoSuchgraException("Niepoprawny numer katologowy");
        }

        gra m = baza[gra.getNumerKatalogowy().intValue()];
        if (m == null) {
            throw new NoSuchgraException("Brak takiej gry.");
        } else {
            baza[gra.getNumerKatalogowy().intValue()] = gra;
        }
        return gra;
    }

    @Override
    public gra readById(Long numerKatalogowy) throws NoSuchgraException {
        int id = numerKatalogowy.intValue();
        if (!sprawdzPoprawnoscNumeruKatalogowego(id) || czyWolne(id)) {
            throw new NoSuchgraException();
        }
        return baza[id];
    }

    private boolean czyWolne(int id) {
        if(baza[id]!= null)
            return false;
        return true;
    }

    @Override
    public List<gra> findAll() {
        List<gra> tmp = new ArrayList<>();
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
