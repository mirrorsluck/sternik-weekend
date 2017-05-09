package pl.sternik.rcz.weekend.repositories;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import pl.sternik.rcz.weekend.entities.Gra;
import pl.sternik.rcz.weekend.entities.Status;


@Service
@Qualifier("lista")
public class GryRepositoryJ8Impl implements GryRepository {

    private List<Gra> gry = new ArrayList<Gra>() {
        {
            add(Gra.produceGra(1L, "CD Projekt", 18L, "Cyfrowy", "Wiedzmin 3: Dziki Gon", new Date(), new BigDecimal("129.99"),
                    Status.NOWA));
            add(Gra.produceGra(2L, "Codemasters", 3L, "CD", "Colin McRae Rally 2.0", new Date(), new BigDecimal("19.99"),
                    Status.DO_SPRZEDANIA));
            add(Gra.produceGra(3L, "Activision", 16L, "DVD", "Call of Duty 4: Modern Warfare", new Date(), new BigDecimal("39.99"), Status.DO_SPRZEDANIA));
            add(Gra.produceGra(4L, "Atari", 3L, "CD", "RollerCoaster Tycoon II", new Date(), new BigDecimal("14.99"),
                    Status.DUBLET));
            add(Gra.produceGra(5L, "Atari", 3L, "CD", "RollerCoaster Tycoon II", new Date(), new BigDecimal("19.99"),
                    Status.DUBLET));
            add(Gra.produceGra(6L, "Rockstar Games", 18L, "Cyfrowy", "Grand Theft Auto V", new Date(), new BigDecimal("149.99"), Status.NOWA));
        }
    };

    @Override
    public List<Gra> findAll() {
        return this.gry;
    }

    @Override
    public Gra readById(Long id) throws NoSuchGraException {
        return this.gry.stream().filter(p -> Objects.equals(p.getNumerKatalogowy(), id)).findFirst()
                .orElseThrow(NoSuchGraException::new);
    }

    @Override
    public Gra create(Gra gra) {
        if (!gry.isEmpty()) {
            gra.setNumerKatalogowy(
                    this.gry.stream().mapToLong(p -> p.getNumerKatalogowy()).max().getAsLong() + 1);
        } else {
            gra.setNumerKatalogowy(1L);
        }
        this.gry.add(gra);
        return gra;
    }

    @Override
    public Gra update(Gra gra) throws NoSuchGraException {
        for (int i = 0; i < this.gry.size(); i++) {
            if (Objects.equals(this.gry.get(i).getNumerKatalogowy(), gra.getNumerKatalogowy())) {
                this.gry.set(i, gra);
                return gra;
            }
        }
        throw new NoSuchGraException("Nie ma takiej gry: " + gra.getNumerKatalogowy());
    }

    @Override
    public void deleteById(Long id) throws NoSuchGraException {
        for (int i = 0; i < this.gry.size(); i++) {
            if (Objects.equals(this.gry.get(i).getNumerKatalogowy(), id)) {
                this.gry.remove(i);
            }
        }
        throw new NoSuchGraException("Nie ma takiej gry: " + id);
    }

}
