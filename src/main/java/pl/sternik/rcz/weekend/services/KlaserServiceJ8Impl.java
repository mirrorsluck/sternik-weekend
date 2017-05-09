package pl.sternik.rcz.weekend.services;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import pl.sternik.rcz.weekend.entities.Gra;
import pl.sternik.rcz.weekend.entities.Status;
import pl.sternik.rcz.weekend.repositories.GraAlreadyExistsException;
import pl.sternik.rcz.weekend.repositories.GryRepository;
import pl.sternik.rcz.weekend.repositories.NoSuchGraException;


@Service
@Primary
public class KlaserServiceJ8Impl implements KlaserService {

    @Autowired
    @Qualifier("lista")
    private GryRepository gry;

    @Override
    public List<Gra> findAll() {
        return gry.findAll();
    }

    @Override
    public List<Gra> findLatest3() {
        return gry.findAll().stream().sorted((a, b) -> b.getDataNabycia().compareTo(a.getDataNabycia())).limit(5)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Gra> findById(Long id) {
        try {
            return Optional.of(gry.readById(id));
        } catch (NoSuchGraException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Gra> create(Gra gra) {
        try {
            return Optional.of(gry.create(gra));
        } catch (GraAlreadyExistsException e) {
            try {
                return Optional.of(gry.readById(gra.getNumerKatalogowy()));
            } catch (NoSuchGraException e1) {
                return Optional.empty();
            }
        }

    }

    @Override
    public Optional<Gra> edit(Gra gra) {
        try {
            return Optional.of(gry.update(gra));
        } catch (NoSuchGraException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Boolean> deleteById(Long id) {
        try {
            gry.deleteById(id);
            return Optional.of(Boolean.TRUE);
        } catch (NoSuchGraException e) {
            return Optional.of(Boolean.FALSE);
        }
    }

    @Override
    public List<Gra> findAllToSell() {
        return gry.findAll().stream().filter(p -> Objects.equals(p.getStatus(), Status.DO_SPRZEDANIA))
                .collect(Collectors.toList());
    }

	@Override
	public List<Gra> findAllDublety() {
	      return gry.findAll().stream().filter(p -> Objects.equals(p.getStatus(), Status.DUBLET))
	                .collect(Collectors.toList());
	}
}
